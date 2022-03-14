package com.daw.delta.beans.login;

import com.daw.delta.DTO.Usuario;
import com.daw.delta.utils.Utilidades;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


public class beanLogin {

    private String email;
    private String passwd;
    
    private String error;
    
    private String nomUsu;
    
    public beanLogin() {
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String esUsuario() {
        try {
            Utilidades utils_ = new Utilidades();
            String mail_    = this.email;
            String passwd_ = this.passwd;
            error="";
            if (mail_ != null && passwd_ != null) {
                List<Usuario> usuarios_ = utils_.getCtrUsuario().findUsuarioEntities();
                for(Usuario o:usuarios_) {
                    Usuario v = o;
                    if (v.getEmail().equals(mail_)) {
                        if (utils_.decrypt(v.getPassword()).equals(passwd_)) {
                            buscaUsu();
                            return "true";
                        } else {
                            return error = "Contraseña Errónea";
                        }
                    }
                }
            } else {
                return error="Email y Contraseña Incorrectos";
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error="Email y Contraseña Incorrectos";
    }
    
    public void buscaUsu(){
        try {
            Utilidades utils_ = new Utilidades();
            Usuario miUsu = utils_.getCtrUsuario().findUsuario(email);
            nomUsu = miUsu.getNombre();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Usuario buscaUsuario(){
        try {
            Utilidades utils_ = new Utilidades();
            return utils_.getCtrUsuario().findUsuario(email);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void logout() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        ((HttpSession) ctx.getSession(false)).invalidate();
    }
    
}
