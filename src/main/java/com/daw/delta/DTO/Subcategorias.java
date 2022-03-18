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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "subcategorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subcategorias.findAll", query = "SELECT s FROM Subcategorias s"),
    @NamedQuery(name = "Subcategorias.findByCodSubcategoria", query = "SELECT s FROM Subcategorias s WHERE s.codSubcategoria = :codSubcategoria"),
    @NamedQuery(name = "Subcategorias.findByNombre", query = "SELECT s FROM Subcategorias s WHERE s.nombre = :nombre")})
public class Subcategorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_subcategoria")
    private Integer codSubcategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "cod_categoria", referencedColumnName = "cod_categoria")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categorias codCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codSubcategoria", fetch = FetchType.LAZY)
    private List<Articulo> articuloList;

    public Subcategorias() {
    }

    public Subcategorias(Integer codSubcategoria) {
        this.codSubcategoria = codSubcategoria;
    }

    public Subcategorias(Integer codSubcategoria, String nombre) {
        this.codSubcategoria = codSubcategoria;
        this.nombre = nombre;
    }
    
    public Subcategorias(Integer codSubcategoria, String nombre, Categorias codCategoria) {
        this.codSubcategoria = codSubcategoria;
        this.nombre = nombre; 
        this.codCategoria = codCategoria;
    }

    public Integer getCodSubcategoria() {
        return codSubcategoria;
    }

    public void setCodSubcategoria(Integer codSubcategoria) {
        this.codSubcategoria = codSubcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categorias getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Categorias codCategoria) {
        this.codCategoria = codCategoria;
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
        hash += (codSubcategoria != null ? codSubcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcategorias)) {
            return false;
        }
        Subcategorias other = (Subcategorias) object;
        return !((this.codSubcategoria == null && other.codSubcategoria != null) || (this.codSubcategoria != null && !this.codSubcategoria.equals(other.codSubcategoria)));
    }

    @Override
    public String toString() {
        return "com.daw.delta.DTO.Subcategorias[ codSubcategoria=" + codSubcategoria + " ]";
    }

}
