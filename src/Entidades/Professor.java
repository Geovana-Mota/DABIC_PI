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
@Table(name = "professor")
@NamedQueries({
    @NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p")})
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_professor")
    private Integer idProfessor;
    @Column(name = "nome_professor")
    private String nomeProfessor;
    @Column(name = "descricao_professor")
    private String descricaoProfessor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professoridProfessor")
    private List<Projetos> projetosList;

    public Professor() {
    }

    public Professor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getDescricaoProfessor() {
        return descricaoProfessor;
    }

    public void setDescricaoProfessor(String descricaoProfessor) {
        this.descricaoProfessor = descricaoProfessor;
    }

    public List<Projetos> getProjetosList() {
        return projetosList;
    }

    public void setProjetosList(List<Projetos> projetosList) {
        this.projetosList = projetosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfessor != null ? idProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.idProfessor == null && other.idProfessor != null) || (this.idProfessor != null && !this.idProfessor.equals(other.idProfessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Professor[ idProfessor=" + idProfessor + " ]";
    }
    
}
