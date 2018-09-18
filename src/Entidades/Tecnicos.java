/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author a1711989
 */
@Entity
@Table(name = "tecnicos")
@NamedQueries({
    @NamedQuery(name = "Tecnicos.findAll", query = "SELECT t FROM Tecnicos t")})
public class Tecnicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tecnicos")
    private Integer idTecnicos;
    @Column(name = "nome_tecnicos")
    private String nomeTecnicos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tecnicosIdTecnicos")
    private List<Laboratorio> laboratorioList;

    public Tecnicos() {
    }

    public Tecnicos(Integer idTecnicos) {
        this.idTecnicos = idTecnicos;
    }

    public Integer getIdTecnicos() {
        return idTecnicos;
    }

    public void setIdTecnicos(Integer idTecnicos) {
        this.idTecnicos = idTecnicos;
    }

    public String getNomeTecnicos() {
        return nomeTecnicos;
    }

    public void setNomeTecnicos(String nomeTecnicos) {
        this.nomeTecnicos = nomeTecnicos;
    }

    public List<Laboratorio> getLaboratorioList() {
        return laboratorioList;
    }

    public void setLaboratorioList(List<Laboratorio> laboratorioList) {
        this.laboratorioList = laboratorioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTecnicos != null ? idTecnicos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tecnicos)) {
            return false;
        }
        Tecnicos other = (Tecnicos) object;
        if ((this.idTecnicos == null && other.idTecnicos != null) || (this.idTecnicos != null && !this.idTecnicos.equals(other.idTecnicos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Tecnicos[ idTecnicos=" + idTecnicos + " ]";
    }
    
}
