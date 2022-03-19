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
import java.util.Date;
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
import javax.faces.model.SelectItem;

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
    
    /*Subcategorias*/
    private String nombreSubcategoria;
    private String categoriaPadreSubcategoria;
    private ArrayList listaSelectSub;
    
    public void guardarSub() {
        try {
            final Utilidades utils_ = new Utilidades();
            Subcategorias subcategorias = new Subcategorias(null,nombreSubcategoria,utils_.getCtrCategorias().findCategorias(Integer.parseInt(categoriaPadreSubcategoria)));
            utils_.getCtrSubcategorias().create(subcategorias);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*Categorias*/
    private String nombreCategoria;
    
    public void guardarCat() {
        try {
            final Utilidades utils_ = new Utilidades();
            Categorias categorias = new Categorias(null, nombreCategoria);
            utils_.getCtrCategorias().create(categorias);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*Categorias*/
    private String titularArt;
    private String categoriaArt;
    private String subCategoriaArt;
    private String cuerpoArt;
    private ArrayList listaSubCate;
    private String descripArt;
    private String imgArt;
    private int prioridadBaseArt;
    
    public void guardarArt() {
        try {
            final Utilidades utils_ = new Utilidades();
            Articulo art = new Articulo(
                    null,
                    usu,
                    utils_.getCtrCategorias().findCategorias(Integer.parseInt(categoriaArt)),
                    utils_.getCtrSubcategorias().findSubcategorias(Integer.parseInt(subCategoriaArt)),
                    titularArt,
                    descripArt,
                    cuerpoArt.replaceAll("\"", "'"),
                    imgArt,
                    new Date(),
                    0,
                    prioridadBaseArt
            );
            utils_.getCtrArticulo().create(art);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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

    public String getTitularArt() {
        return titularArt;
    }

    public void setTitularArt(String titularArt) {
        this.titularArt = titularArt;
    }

    public String getCategoriaArt() {
        return categoriaArt;
    }

    public void setCategoriaArt(String categoriaArt) {
        this.categoriaArt = categoriaArt;
    }

    public String getSubCategoriaArt() {
        return subCategoriaArt;
    }

    public void setSubCategoriaArt(String subCategoriaArt) {
        this.subCategoriaArt = subCategoriaArt;
    }

    public String getCuerpoArt() {
        return cuerpoArt;
    }

    public void setCuerpoArt(String cuerpoArt) {
        this.cuerpoArt = cuerpoArt;
    }

    public ArrayList getListaSubCate() {
        listaSubCategorias = getListaSubCategorias();
        listaSubCate = new ArrayList();
        for(Subcategorias o : listaSubCategorias) {
            listaSubCate.add(new SelectItem(o.getCodSubcategoria(),o.getNombre()));
        }
        return listaSubCate;
    }

    public void setListaSubCate(ArrayList listaSubCate) {
        this.listaSubCate = listaSubCate;
    }

    public String getDescripArt() {
        return descripArt;
    }

    public void setDescripArt(String descripArt) {
        this.descripArt = descripArt;
    }

    public String getImgArt() {
        return imgArt;
    }

    public void setImgArt(String imgArt) {
        this.imgArt = imgArt;
    }

    public int getPrioridadBaseArt() {
        return prioridadBaseArt;
    }

    public void setPrioridadBaseArt(int prioridadBaseArt) {
        this.prioridadBaseArt = prioridadBaseArt;
    }
    
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
    public String getNombreSubcategoria() {
        return nombreSubcategoria;
    }

    public void setNombreSubcategoria(String nombreSubcategoria) {
        this.nombreSubcategoria = nombreSubcategoria;
    }

    public String getCategoriaPadreSubcategoria() {
        return categoriaPadreSubcategoria;
    }

    public void setCategoriaPadreSubcategoria(String categoriaPadreSubcategoria) {
        this.categoriaPadreSubcategoria = categoriaPadreSubcategoria;
    }

    public ArrayList getListaSelectSub() {
        listaCategorias = getListaCategorias();
        listaSelectSub = new ArrayList();
        for(Categorias o : listaCategorias) {
            listaSelectSub.add(new SelectItem(o.getCodCategoria(), o.getCategoria()));
        }
        return listaSelectSub;
    }

    public void setListaSelectSub(ArrayList listaSelectSub) {
        this.listaSelectSub = listaSelectSub;
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
            Articulo o_ = (Articulo) tablaArticulo.getRowData();
            utils_.getCtrArticulo().destroy(o_.getCodArt());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException | IllegalOrphanException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarCat() {
        try {
            final Utilidades utils_ = new Utilidades();
            Categorias o_ = (Categorias) tablaCategorias.getRowData();
            utils_.getCtrCategorias().destroy(o_.getCodCategoria());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException | IllegalOrphanException ex) {
            Logger.getLogger(beanAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarSub() {
        try {
            final Utilidades utils_ = new Utilidades();
            Subcategorias o_ = (Subcategorias) tablaSubCategorias.getRowData();
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
