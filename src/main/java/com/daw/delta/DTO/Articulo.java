package com.daw.delta.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "Articulo.findByCuerpoNoticia", query = "SELECT a FROM Articulo a WHERE a.cuerpoNoticia = :cuerpoNoticia"),
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
    @Column(name = "cuerpoNoticia")
    private String cuerpoNoticia;
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
    @JoinColumn(name = "cod_art", referencedColumnName = "cod_art", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Opinion opinion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codArt", fetch = FetchType.LAZY)
    private List<Opinion> opinionList;

    public Articulo() {
    }

    public Articulo(Integer codArt) {
        this.codArt = codArt;
    }

    public Articulo(Integer codArt, String titular, String cuerpoNoticia, Date fechaPubli, int prioridadBase) {
        this.codArt = codArt;
        this.titular = titular;
        this.cuerpoNoticia = cuerpoNoticia;
        this.fechaPubli = fechaPubli;
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

    public String getCuerpoNoticia() {
        return cuerpoNoticia;
    }

    public void setCuerpoNoticia(String cuerpoNoticia) {
        this.cuerpoNoticia = cuerpoNoticia;
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

    public Opinion getOpinion() {
        return opinion;
    }

    public void setOpinion(Opinion opinion) {
        this.opinion = opinion;
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

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        return !((this.codArt == null && other.codArt != null) || (this.codArt != null && !this.codArt.equals(other.codArt)));
    }

    @Override
    public String toString() {
        return "com.daw.delta.DTO.Articulo[ codArt=" + codArt + " ]";
    }

}
