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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "opinion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opinion.findAll", query = "SELECT o FROM Opinion o"),
    @NamedQuery(name = "Opinion.findByCodOpinion", query = "SELECT o FROM Opinion o WHERE o.codOpinion = :codOpinion"),
    @NamedQuery(name = "Opinion.findByHora", query = "SELECT o FROM Opinion o WHERE o.hora = :hora"),
    @NamedQuery(name = "Opinion.findByFechaPubli", query = "SELECT o FROM Opinion o WHERE o.fechaPubli = :fechaPubli"),
    @NamedQuery(name = "Opinion.findByContenido", query = "SELECT o FROM Opinion o WHERE o.contenido = :contenido")})
public class Opinion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_opinion")
    private Integer codOpinion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_publi")
    @Temporal(TemporalType.DATE)
    private Date fechaPubli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "contenido")
    private String contenido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codOpinion", fetch = FetchType.LAZY)
    private List<Respuestas> respuestasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEngancha", fetch = FetchType.LAZY)
    private List<Respuestas> respuestasList1;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario email;
    @JoinColumn(name = "cod_art", referencedColumnName = "cod_art")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo codArt;

    public Opinion() {
    }

    public Opinion(Integer codOpinion) {
        this.codOpinion = codOpinion;
    }

    public Opinion(Integer codOpinion, Date hora, Date fechaPubli, String contenido) {
        this.codOpinion = codOpinion;
        this.hora = hora;
        this.fechaPubli = fechaPubli;
        this.contenido = contenido;
    }
    
    public Opinion(Integer codOpinion, Usuario email, Articulo codArt, Date hora, Date fechaPubli, String contenido) {
        this.codOpinion = codOpinion;
        this.email = email;
        this.codArt = codArt;
        this.hora = hora;
        this.fechaPubli = fechaPubli;
        this.contenido = contenido;
    }

    public Integer getCodOpinion() {
        return codOpinion;
    }

    public void setCodOpinion(Integer codOpinion) {
        this.codOpinion = codOpinion;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Date getFechaPubli() {
        return fechaPubli;
    }

    public void setFechaPubli(Date fechaPubli) {
        this.fechaPubli = fechaPubli;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @XmlTransient
    public List<Respuestas> getRespuestasList() {
        return respuestasList;
    }

    public void setRespuestasList(List<Respuestas> respuestasList) {
        this.respuestasList = respuestasList;
    }

    @XmlTransient
    public List<Respuestas> getRespuestasList1() {
        return respuestasList1;
    }

    public void setRespuestasList1(List<Respuestas> respuestasList1) {
        this.respuestasList1 = respuestasList1;
    }

    public Usuario getEmail() {
        return email;
    }

    public void setEmail(Usuario email) {
        this.email = email;
    }

    public Articulo getCodArt() {
        return codArt;
    }

    public void setCodArt(Articulo codArt) {
        this.codArt = codArt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codOpinion != null ? codOpinion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opinion)) {
            return false;
        }
        Opinion other = (Opinion) object;
        return !((this.codOpinion == null && other.codOpinion != null) || (this.codOpinion != null && !this.codOpinion.equals(other.codOpinion)));
    }

    @Override
    public String toString() {
        return "com.daw.delta.DTO.Opinion[ codOpinion=" + codOpinion + " ]";
    }

}
