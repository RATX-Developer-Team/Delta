package com.daw.delta.beans.articulo;

import com.daw.delta.DTO.Articulo;
import com.daw.delta.utils.Utilidades;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="beanArticulo")
@SessionScoped 
public class beanArticulo {
    private Articulo articulo;
    private int codigo;
    
    public beanArticulo() {
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void cargar() {
        try {
            final Utilidades utils_ = new Utilidades();
            articulo = utils_.getCtrArticulo().findArticulo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(beanArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
