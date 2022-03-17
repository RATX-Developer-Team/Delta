package com.daw.delta.beans.administracion;

import com.daw.delta.DAO.exceptions.IllegalOrphanException;
import com.daw.delta.DAO.exceptions.NonexistentEntityException;
import com.daw.delta.DTO.Articulo;
import com.daw.delta.DTO.Categorias;
import com.daw.delta.DTO.Subcategorias;
import com.daw.delta.DTO.Usuario;
import com.daw.delta.utils.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="beanAdministracion")
@SessionScoped
public class beanAdministracion implements Serializable{
    
    @ManagedProperty("#{beanLogin.usu}")
    private Usuario usu;
    
    /*Listas BaseDatos*/
    private List<Usuario> listaUsuarios;
    private List<Subcategorias> listaSubCategorias;
    private List<Categorias> listaCategorias;
    private List<Articulo> listaArticulos;
    
    /*Tablas Respaldo*/
    private HtmlDataTable tablaUsuarios;
    private HtmlDataTable tablaSubCategorias;
    private HtmlDataTable tablaCategorias;
    private HtmlDataTable tablaArticulo;
    
    public beanAdministracion() {
        listaUsuarios = new ArrayList<>();
    }

    public Usuario getUsu() {
        if (this.usu==null) {
            redireccionar("logreg.jsp");
        } else if (this.usu.getPermiso()==null || this.usu.getPermiso()==0) {
            redireccionar("index.jsp");
        }
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public HtmlDataTable getTablaUsuarios() {
        return tablaUsuarios;
    }

    public List<Articulo> getListaArticulos() {
        try {
            final Utilidades utils_ = new Utilidades();
            listaArticulos = utils_.getCtrArticulo().findArticuloEntities();
            return listaArticulos;
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaArticulos;
    }

    public void setListaArticulos(List<Articulo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    public HtmlDataTable getTablaArticulo() {
        return tablaArticulo;
    }

    public void setTablaArticulo(HtmlDataTable tablaArticulo) {
        this.tablaArticulo = tablaArticulo;
    }
    
    public void setTablaUsuarios(HtmlDataTable tablaUsuarios) {
        this.tablaUsuarios = tablaUsuarios;
    }

    public List<Categorias> getListaCategorias() {
        try {
            final Utilidades utils_ = new Utilidades();
            listaCategorias = utils_.getCtrCategorias().findCategoriasEntities();
            return listaCategorias;
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCategorias;
    }

    public void setListaCategorias(List<Categorias> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public HtmlDataTable getTablaCategorias() {
        return tablaCategorias;
    }

    public void setTablaCategorias(HtmlDataTable tablaCategorias) {
        this.tablaCategorias = tablaCategorias;
    }

    public HtmlDataTable getTablaSubCategorias() {
        return tablaSubCategorias;
    }

    public void setTablaSubCategorias(HtmlDataTable tablaSubCategorias) {
        this.tablaSubCategorias = tablaSubCategorias;
    }

    public List<Subcategorias> getListaSubCategorias() {
        try {
            final Utilidades utils_ = new Utilidades();
            listaSubCategorias = utils_.getCtrSubcategorias().findSubcategoriasEntities();
            return listaSubCategorias;
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaSubCategorias;
    }

    public void setListaSubCategorias(List<Subcategorias> listaSubCategorias) {
        this.listaSubCategorias = listaSubCategorias;
    }

    public List<Usuario> getListaUsuarios() {
        try {
            final Utilidades utils_ = new Utilidades();
            listaUsuarios = utils_.getCtrUsuario().findUsuarioEntities();
            return listaUsuarios;
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public boolean isAdmin() {
        return this.usu.getPermiso()!=null && this.usu.getPermiso()!=0;
    }

    public void eliminarArt() {
        try {
            final Utilidades utils_ = new Utilidades();
            Articulo o_ = (Articulo) tablaUsuarios.getRowData();
            utils_.getCtrArticulo().destroy(o_.getCodArt());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException | IllegalOrphanException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarCat() {
        try {
            final Utilidades utils_ = new Utilidades();
            Categorias o_ = (Categorias) tablaUsuarios.getRowData();
            utils_.getCtrCategorias().destroy(o_.getCodCategoria());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException | IllegalOrphanException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarSub() {
        try {
            final Utilidades utils_ = new Utilidades();
            Subcategorias o_ = (Subcategorias) tablaUsuarios.getRowData();
            utils_.getCtrSubcategorias().destroy(o_.getCodSubcategoria());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException | IllegalOrphanException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarUsu() {
        try {
            final Utilidades utils_ = new Utilidades();
            Usuario usu_ = (Usuario) tablaUsuarios.getRowData();
            utils_.getCtrUsuario().destroy(usu_.getEmail());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException | IllegalOrphanException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cambiarPermisoUsu() {
        try {
            final Utilidades utils_ = new Utilidades();
            Usuario usu_ = (Usuario) tablaUsuarios.getRowData();
            if (null == usu_.getPermiso()) {
                usu_.setPermiso(1);
            } else switch (usu_.getPermiso()) {
                case 0:
                    usu_.setPermiso(1);
                    break;
                case 1:
                    usu_.setPermiso(2);
                    break;
                case 2:
                    usu_.setPermiso(0);
                    break;
                default:
                    break;
            }
            utils_.getCtrUsuario().edit(usu_);
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException | IllegalOrphanException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void redireccionar (String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (IOException ex) {}
    }

}
