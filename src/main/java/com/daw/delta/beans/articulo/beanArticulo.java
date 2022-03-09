package com.daw.delta.beans.articulo;

import com.daw.delta.DTO.Articulo;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="beanArticulo")
@SessionScoped 
public class beanArticulo {
    private Articulo articulo;
    
    public beanArticulo() {
    }

    public Articulo getArticulo() {
        cargar();
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    
    public void cargar() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession sesion = (HttpSession)ctx.getSession(true);
        articulo = (Articulo)sesion.getAttribute("Articulo");
    }
    
    public static void redireccionar (String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (IOException ex) {}
    }
    
    public static void logout(String surl) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ((HttpSession) ctx.getSession(false)).invalidate();
            ctx.redirect(surl);
        } catch (IOException ex) {}
    }
}
