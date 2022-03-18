package com.daw.delta.DTO;

import com.daw.delta.utils.Utilidades;
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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a"),
    @NamedQuery(name = "Articulo.findByCodArt", query = "SELECT a FROM Articulo a WHERE a.codArt = :codArt"),
    @NamedQuery(name = "Articulo.findByTitular", query = "SELECT a FROM Articulo a WHERE a.titular = :titular"),
    @NamedQuery(name = "Articulo.findByDescripcion", query = "SELECT a FROM Articulo a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Articulo.findByImagen", query = "SELECT a FROM Articulo a WHERE a.imagen = :imagen"),
    @NamedQuery(name = "Articulo.findByFechaPubli", query = "SELECT a FROM Articulo a WHERE a.fechaPubli = :fechaPubli"),
    @NamedQuery(name = "Articulo.findByNVisitas", query = "SELECT a FROM Articulo a WHERE a.nVisitas = :nVisitas"),
    @NamedQuery(name = "Articulo.findByPrioridadBase", query = "SELECT a FROM Articulo a WHERE a.prioridadBase = :prioridadBase")})
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_art")
    private Integer codArt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "titular")
    private String titular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "cuerpoNoticia")
    private String cuerpoNoticia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "imagen")
    private String imagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_publi")
    @Temporal(TemporalType.DATE)
    private Date fechaPubli;
    @Column(name = "n_visitas")
    private Integer nVisitas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prioridadBase")
    private int prioridadBase;
    @JoinColumn(name = "cod_categoria", referencedColumnName = "cod_categoria")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categorias codCategoria;
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codUsuario;
    @JoinColumn(name = "cod_subcategoria", referencedColumnName = "cod_subcategoria")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Subcategorias codSubcategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codArt", fetch = FetchType.LAZY)
    private List<Opinion> opinionList;

    public Articulo() {
    }

    public Articulo(Integer codArt) {
        this.codArt = codArt;
    }

    public Articulo(Integer codArt, String titular, String descripcion, String cuerpoNoticia, String imagen, Date fechaPubli, int prioridadBase) {
        this.codArt = codArt;
        this.titular = titular;
        this.descripcion = descripcion;
        this.cuerpoNoticia = cuerpoNoticia;
        this.imagen = imagen;
        this.fechaPubli = fechaPubli;
        this.prioridadBase = prioridadBase;
    }
    
    public Articulo(Integer codArt, Usuario codUsuario, Categorias codCategoria, Subcategorias codSubcategoria, String titular, String descripcion, String cuerpoNoticia, String imagen, Date fechaPubli, int visitas, int prioridadBase) {
        this.codArt = codArt;
        this.codUsuario = codUsuario;
        this.codCategoria = codCategoria;
        this.codSubcategoria = codSubcategoria;
        this.titular = titular;
        this.descripcion = descripcion;
        this.cuerpoNoticia = cuerpoNoticia;
        this.imagen = imagen;
        this.fechaPubli = fechaPubli;
        this.nVisitas = visitas;
        this.prioridadBase = prioridadBase;
    }

    public Integer getCodArt() {
        return codArt;
    }

    public void setCodArt(Integer codArt) {
        this.codArt = codArt;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCuerpoNoticia() {
        return cuerpoNoticia;
    }

    public void setCuerpoNoticia(String cuerpoNoticia) {
        this.cuerpoNoticia = cuerpoNoticia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaPubli() {
        return fechaPubli;
    }

    public void setFechaPubli(Date fechaPubli) {
        this.fechaPubli = fechaPubli;
    }

    public Integer getNVisitas() {
        return nVisitas;
    }
    
    public Integer nvisit() {
        return nVisitas;
    }

    public void setNVisitas(Integer nVisitas) {
        this.nVisitas = nVisitas;
    }

    public int getPrioridadBase() {
        return prioridadBase;
    }

    public void setPrioridadBase(int prioridadBase) {
        this.prioridadBase = prioridadBase;
    }

    public Categorias getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Categorias codCategoria) {
        this.codCategoria = codCategoria;
    }

    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Subcategorias getCodSubcategoria() {
        return codSubcategoria;
    }

    public void setCodSubcategoria(Subcategorias codSubcategoria) {
        this.codSubcategoria = codSubcategoria;
    }

    @XmlTransient
    public List<Opinion> getOpinionList() {
        return opinionList;
    }

    public void setOpinionList(List<Opinion> opinionList) {
        this.opinionList = opinionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codArt != null ? codArt.hashCode() : 0);
        return hash;
    }

    public List<Opinion> getComentarios() {
        List<Opinion> lista = new ArrayList();
        int aux = 0;
        for (Opinion o:this.opinionList) {
            if (o.getRespuestasList().isEmpty()) {
                lista.add(aux, o);
                aux++;
            }
        }
        return lista;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        return !((this.codArt == null && other.codArt != null) || (this.codArt != null && !this.codArt.equals(other.codArt)));
    }
    
    public void incre() {
        this.nVisitas++;
    }

    @Override
    public String toString() {
        try {
            Utilidades utils_ = new Utilidades();
            List<Usuario> usu = utils_.getCtrUsuario().findById_(codUsuario.getCodUsuario());
            return "{" +
                    "\"codArt\":\"" + codArt + '\"' +
                    ",\"codUsuario\":\"" +usu.get(0).getNombre()+" "+usu.get(0).getApellidos()+ '\"' +
                    ",\"codCategoria\":\"" + codCategoria.getCodCategoria() + '\"' +
                    ",\"categoria\":\"" + codCategoria.getCategoria() + '\"' +
                    ",\"codSubCategoria\":\"" + codSubcategoria.getCodSubcategoria() + '\"' +
                    ",\"subcategoria\":\"" + codSubcategoria.getNombre() + '\"' +
                    ",\"titular\":\"" + titular + '\"' +
                    ",\"descripcion\":\"" + descripcion + '\"' +
                    ",\"cuerpoNoticia\":\"" + cuerpoNoticia + '\"' +
                    ",\"imagen\":\"" + imagen + '\"' +
                    ",\"fechaPubli\":\"" + fechaPubli.toLocaleString() + '\"' +
                    ",\"nVisitas\":\"" + nVisitas + '\"' +
                    ",\"prioridad\":\"" + utils_.calculaPrioridad(this) + '\"' +
                    '}';
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "null";
    }

}
