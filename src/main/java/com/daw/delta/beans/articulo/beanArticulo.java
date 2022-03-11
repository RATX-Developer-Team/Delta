package com.daw.delta.beans.articulo;

import com.daw.delta.DTO.Articulo;
import com.daw.delta.DTO.Opinion;
import com.daw.delta.DTO.Respuestas;
import com.daw.delta.utils.Utilidades;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="beanArticulo")
@SessionScoped 
public class beanArticulo {
    private Articulo articulo;
    private List<Opinion> listaComentarios;
    private ArrayList listaEnganchados;
    
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

    public ArrayList getListaEnganchados() {
        return listaEnganchados;
    }

    public void setListaEnganchados(ArrayList listaEnganchados) {
        this.listaEnganchados = listaEnganchados;
    }

    public List<Opinion> getListaComentarios() {
        listaEnganchados = new ArrayList();
        listaComentarios = articulo.getOpinionList();
        for (Opinion o:listaComentarios) {
            if (!o.getRespuestasList().isEmpty()) {
                ArrayList aux = new ArrayList();
                for (Respuestas x:o.getRespuestasList()) {
                    aux.add(x.getCodRespuesta(),x);
                    listaEnganchados.add(o.getCodOpinion(),aux);
                    listaComentarios.remove(x);
                }
            }
        }
        return listaComentarios;
    }

    public void setListaComentarios(List<Opinion> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }
    
    public ArrayList cargaEngancha(int c) {
        return (ArrayList)listaEnganchados.get(c);
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
