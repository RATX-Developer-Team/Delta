package com.daw.delta.beans.articulo;

import com.daw.delta.DTO.Articulo;
import com.daw.delta.utils.Utilidades;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="beanArticulo")
@SessionScoped 
public class beanArticulo {
    private Articulo articulo;
    
    public beanArticulo() {
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public void buscarArticulo() {
        try {
            final Utilidades utils_ = new Utilidades();//.getRequestParameterMap().get("codigo")
            HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            articulo = utils_.getCtrArticulo().findArticulo(codigo);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
