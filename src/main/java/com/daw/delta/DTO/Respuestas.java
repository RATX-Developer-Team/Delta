package com.daw.delta.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "respuestas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuestas.findAll", query = "SELECT r FROM Respuestas r"),
    @NamedQuery(name = "Respuestas.findByCodRespuesta", query = "SELECT r FROM Respuestas r WHERE r.codRespuesta = :codRespuesta")})
public class Respuestas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_respuesta")
    private Integer codRespuesta;
    @JoinColumn(name = "cod_opinion", referencedColumnName = "cod_opinion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Opinion codOpinion;
    @JoinColumn(name = "cod_engancha", referencedColumnName = "cod_opinion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Opinion codEngancha;

    public Respuestas() {
    }

    public Respuestas(Integer codRespuesta) {
        this.codRespuesta = codRespuesta;
    }
    
    public Respuestas(Integer codRespuesta,Opinion codO, Opinion codE) {
        this.codRespuesta = codRespuesta;
        this.codOpinion = codO;
        this.codEngancha = codE;
    }

    public Integer getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(Integer codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    public Opinion getCodOpinion() {
        return codOpinion;
    }

    public void setCodOpinion(Opinion codOpinion) {
        this.codOpinion = codOpinion;
    }

    public Opinion getCodEngancha() {
        return codEngancha;
    }

    public void setCodEngancha(Opinion codEngancha) {
        this.codEngancha = codEngancha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRespuesta != null ? codRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestas)) {
            return false;
        }
        Respuestas other = (Respuestas) object;
        return !((this.codRespuesta == null && other.codRespuesta != null) || (this.codRespuesta != null && !this.codRespuesta.equals(other.codRespuesta)));
    }

    @Override
    public String toString() {
        return "com.daw.delta.DTO.Respuestas[ codRespuesta=" + codRespuesta + " ]";
    }

}
