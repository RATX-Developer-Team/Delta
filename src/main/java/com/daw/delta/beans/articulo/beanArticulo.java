package com.daw.delta.beans.articulo;

import com.daw.delta.DAO.exceptions.NonexistentEntityException;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="beanArticulo")
@SessionScoped 
public class beanArticulo {
    private Articulo articulo;
    private List<Opinion> listaComentarios;
    private Map listaEnganchados;
    
    @ManagedProperty("#{beanLogin.email}")
    private String mail;
    
    private String comentario;
    private String subcomentario;
    
    public beanArticulo() {
    }

    public Articulo getArticulo() {
        cargar();
        return articulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSubcomentario() {
        return subcomentario;
    }

    public void setSubcomentario(String subcomentario) {
        this.subcomentario = subcomentario;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    
    public void cargar() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession sesion = (HttpSession)ctx.getSession(true);
        articulo = (Articulo)sesion.getAttribute("Articulo");
    }

    public Map getListaEnganchados() {
        return listaEnganchados;
    }

    public void setListaEnganchados(Map listaEnganchados) {
        this.listaEnganchados = listaEnganchados;
    }

    public List<Opinion> getListaComentarios() {
        listaEnganchados = new HashMap<>();
        listaComentarios = articulo.getComentarios();
        for (Opinion o:listaComentarios) {
            if (!o.getRespuestasList1().isEmpty()) {
                ArrayList aux = new ArrayList();
                int i = 0;
                for (Respuestas x:o.getRespuestasList1()) {
                    aux.add(i,x);
                    i++;
                    listaEnganchados.put(o.getCodOpinion(),aux);
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
    
    public void incrementar() {
        try {
            final Utilidades utils_ = new Utilidades();
            this.articulo.incre();
            utils_.getCtrArticulo().edit(articulo);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(beanArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void guardarComentario() {
        if (this.comentario!=null && !"".equals(this.comentario)) {
            try {
                final Utilidades utils_ = new Utilidades();
                Date hora = new Date();
                Opinion opinion = new Opinion(null, utils_.getCtrUsuario().findUsuario(mail), articulo, hora, hora, comentario);
                utils_.getCtrOpinion().create(opinion);
            } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
                Logger.getLogger(beanArticulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void guardarSubComentario(Opinion v) {
        if (this.subcomentario!=null && !"".equals(this.subcomentario)) {
            try {
                final Utilidades utils_ = new Utilidades();
                Date hora = new Date();
                Opinion opinion = new Opinion(null, utils_.getCtrUsuario().findUsuario(mail), articulo, hora, hora, subcomentario);
                utils_.getCtrOpinion().create(opinion);
                Respuestas respuestas = new Respuestas(null,opinion,v);
                utils_.getCtrRespuestas().create(respuestas);
            } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
                Logger.getLogger(beanArticulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
