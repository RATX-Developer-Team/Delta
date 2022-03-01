package com.daw.delta.utils;

import com.daw.delta.DAO.ArticuloJpaController;
import com.daw.delta.DAO.CategoriasJpaController;
import com.daw.delta.DAO.OpinionJpaController;
import com.daw.delta.DAO.RespuestasJpaController;
import com.daw.delta.DAO.SubcategoriasJpaController;
import com.daw.delta.DAO.UsuarioJpaController;
import com.daw.delta.DTO.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.spec.KeySpec;
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
    
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private final String myEncryptionKey = "4rG#rm#j%i5wf8!F%m4GiGc*";
    private final String myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
    byte[] arrayBytes;
    private final KeySpec ks;
    private final SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
    private final Cipher cipher;
    SecretKey key;

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
    
    public Usuario cuenta() {
        return ctrUsuario.findUsuario(mail);
    }
}
