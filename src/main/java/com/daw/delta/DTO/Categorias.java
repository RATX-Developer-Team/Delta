package com.daw.delta.DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"),
    @NamedQuery(name = "Categorias.findByCodCategoria", query = "SELECT c FROM Categorias c WHERE c.codCategoria = :codCategoria"),
    @NamedQuery(name = "Categorias.findByCategoria", query = "SELECT c FROM Categorias c WHERE c.categoria = :categoria")})
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_categoria")
    private Integer codCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "categoria")
    private String categoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCategoria", fetch = FetchType.LAZY)
    private List<Subcategorias> subcategoriasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCategoria", fetch = FetchType.LAZY)
    private List<Articulo> articuloList;

    public Categorias() {
    }

    public Categorias(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public Categorias(Integer codCategoria, String categoria) {
        this.codCategoria = codCategoria;
        this.categoria = categoria;
    }

    public Integer getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @XmlTransient
    public List<Subcategorias> getSubcategoriasList() {
        return subcategoriasList;
    }

    public void setSubcategoriasList(List<Subcategorias> subcategoriasList) {
        this.subcategoriasList = subcategoriasList;
    }

    @XmlTransient
    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCategoria != null ? codCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        return !((this.codCategoria == null && other.codCategoria != null) || (this.codCategoria != null && !this.codCategoria.equals(other.codCategoria)));
    }

    @Override
    public String toString() {
        return "com.daw.delta.DTO.Categorias[ codCategoria=" + codCategoria + " ]";
    }

}
