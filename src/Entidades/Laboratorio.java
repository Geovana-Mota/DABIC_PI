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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author a1711989
 */
@Entity
@Table(name = "laboratorio")
@NamedQueries({
    @NamedQuery(name = "Laboratorio.findAll", query = "SELECT l FROM Laboratorio l")})
public class Laboratorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_laboratorio")
    private Integer idLaboratorio;
    @Column(name = "nome_laboratorio")
    private String nomeLaboratorio;
    @Column(name = "localizacao")
    private String localizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratorioidLaboratorio")
    private List<Projetos> projetosList;
    @JoinColumn(name = "tecnicos_id_tecnicos", referencedColumnName = "id_tecnicos")
    @ManyToOne(optional = false)
    private Tecnicos tecnicosIdTecnicos;

    public Laboratorio() {
    }

    public Laboratorio(Integer idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public Integer getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(Integer idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNomeLaboratorio() {
        return nomeLaboratorio;
    }

    public void setNomeLaboratorio(String nomeLaboratorio) {
        this.nomeLaboratorio = nomeLaboratorio;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public List<Projetos> getProjetosList() {
        return projetosList;
    }

    public void setProjetosList(List<Projetos> projetosList) {
        this.projetosList = projetosList;
    }

    public Tecnicos getTecnicosIdTecnicos() {
        return tecnicosIdTecnicos;
    }

    public void setTecnicosIdTecnicos(Tecnicos tecnicosIdTecnicos) {
        this.tecnicosIdTecnicos = tecnicosIdTecnicos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLaboratorio != null ? idLaboratorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Laboratorio)) {
            return false;
        }
        Laboratorio other = (Laboratorio) object;
        if ((this.idLaboratorio == null && other.idLaboratorio != null) || (this.idLaboratorio != null && !this.idLaboratorio.equals(other.idLaboratorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Laboratorio[ idLaboratorio=" + idLaboratorio + " ]";
    }
    
}
