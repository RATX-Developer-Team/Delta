package com.daw.delta.utils;

import com.daw.delta.DAO.ArticuloJpaController;
import com.daw.delta.DAO.CategoriasJpaController;
import com.daw.delta.DAO.NewletterJpaController;
import com.daw.delta.DAO.OpinionJpaController;
import com.daw.delta.DAO.RespuestasJpaController;
import com.daw.delta.DAO.SubcategoriasJpaController;
import com.daw.delta.DAO.UsuarioJpaController;
import com.daw.delta.DTO.Articulo;
import com.daw.delta.DTO.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.spec.KeySpec;
import java.util.Date;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.codec.binary.Base64;


public class Utilidades  {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("deltaPU");
    private final ArticuloJpaController ctrArticulo = new ArticuloJpaController(emf);
    private final CategoriasJpaController ctrCategorias = new CategoriasJpaController(emf);
    private final OpinionJpaController ctrOpinion = new OpinionJpaController(emf);
    private final RespuestasJpaController ctrRespuestas = new RespuestasJpaController(emf);
    private final SubcategoriasJpaController ctrSubcategorias = new SubcategoriasJpaController(emf);
    private final UsuarioJpaController ctrUsuario = new UsuarioJpaController(emf);
    private final NewletterJpaController ctrNewletter = new NewletterJpaController(emf);
    
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private final String myEncryptionKey = "4rG#rm#j%i5wf8!F%m4GiGc*";
    private final String myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
    byte[] arrayBytes;
    private final KeySpec ks;
    private final SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
    private final Cipher cipher;
    SecretKey key;

    private final String STMP_host = "smtp-mail.outlook.com";
    private final String STMP_port = "587";
    private final String STMP_user = "periodicodelta@outlook.com";
    private final String STMP_pass = "NxjzMahP4agq";

    @ManagedProperty("#{beanLogin.email}")
    private String mail;
    
    public Utilidades() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, UnsupportedEncodingException {
        this.arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        this.ks = new DESedeKeySpec(arrayBytes);
        this.key = skf.generateSecret(ks);
        this.cipher = Cipher.getInstance(myEncryptionScheme);
    }
    
    public ArticuloJpaController getCtrArticulo() {
        return ctrArticulo;
    }

    public CategoriasJpaController getCtrCategorias() {
        return ctrCategorias;
    }

    public OpinionJpaController getCtrOpinion() {
        return ctrOpinion;
    }

    public NewletterJpaController getCtrNewletter() {
        return ctrNewletter;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public RespuestasJpaController getCtrRespuestas() {
        return ctrRespuestas;
    }

    public SubcategoriasJpaController getCtrSubcategorias() {
        return ctrSubcategorias;
    }

    public UsuarioJpaController getCtrUsuario() {
        return ctrUsuario;
    }

    public String getSTMP_host() {
        return STMP_host;
    }

    public String getSTMP_port() {
        return STMP_port;
    }

    public String getSTMP_user() {
        return STMP_user;
    }

    public String getSTMP_pass() {
        return STMP_pass;
    }
        
    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (UnsupportedEncodingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
        }
        return decryptedText;
    }
    
    public int lastId() {
        List<Usuario> v = ctrUsuario.findUsuarioEntities();
        int i=0;
        for(Usuario o:v) {
            Usuario x = o;
            if (x.getCodUsuario()>i) {
                i = x.getCodUsuario();
            }
        }
        return i;
    }
    
    public Usuario cuenta() {
        return ctrUsuario.findUsuario(mail);
    }
    
    public int calculaPrioridad(Articulo v) {
        Articulo v_ = v;
        Date date = new Date();
        int prioridad = v_.getPrioridadBase();
        
        int numVisitas = v_.getNVisitas();
        if (numVisitas<10) {
            prioridad+=5;
        } else if (numVisitas>10 && numVisitas<20) {
            prioridad+=10;
        } else if (numVisitas>20 && numVisitas<30) {
            prioridad+=15;
        } else if (numVisitas>30 && numVisitas<50) {
            prioridad+=20;
        } else {
            prioridad+=25;
        }
        
        if (date.before(v_.getFechaPubli())) {
            prioridad+=5;
        } else if (date.equals(v_.getFechaPubli())){
            prioridad+=10;
        } else if (date.after(v_.getFechaPubli())) {
            prioridad+=15;
        }
        
        
        return prioridad;
    }
}
