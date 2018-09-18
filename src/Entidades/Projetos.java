/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author a1711989
 */
@Entity
@Table(name = "projetos")
@NamedQueries({
    @NamedQuery(name = "Projetos.findAll", query = "SELECT p FROM Projetos p")})
public class Projetos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_projetos")
    private Integer idProjetos;
    @Column(name = "nome_projetos")
    private String nomeProjetos;
    @Column(name = "descricao_projetos")
    private String descricaoProjetos;
    @ManyToMany(mappedBy = "projetosList")
    private List<Aluno> alunoList;
    @JoinColumn(name = "laboratorio_idLaboratorio", referencedColumnName = "id_laboratorio")
    @ManyToOne(optional = false)
    private Laboratorio laboratorioidLaboratorio;
    @JoinColumn(name = "professor_idProfessor", referencedColumnName = "id_professor")
    @ManyToOne(optional = false)
    private Professor professoridProfessor;

    public Projetos() {
    }

    public Projetos(Integer idProjetos) {
        this.idProjetos = idProjetos;
    }

    public Integer getIdProjetos() {
        return idProjetos;
    }

    public void setIdProjetos(Integer idProjetos) {
        this.idProjetos = idProjetos;
    }

    public String getNomeProjetos() {
        return nomeProjetos;
    }

    public void setNomeProjetos(String nomeProjetos) {
        this.nomeProjetos = nomeProjetos;
    }

    public String getDescricaoProjetos() {
        return descricaoProjetos;
    }

    public void setDescricaoProjetos(String descricaoProjetos) {
        this.descricaoProjetos = descricaoProjetos;
    }

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    public Laboratorio getLaboratorioidLaboratorio() {
        return laboratorioidLaboratorio;
    }

    public void setLaboratorioidLaboratorio(Laboratorio laboratorioidLaboratorio) {
        this.laboratorioidLaboratorio = laboratorioidLaboratorio;
    }

    public Professor getProfessoridProfessor() {
        return professoridProfessor;
    }

    public void setProfessoridProfessor(Professor professoridProfessor) {
        this.professoridProfessor = professoridProfessor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProjetos != null ? idProjetos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projetos)) {
            return false;
        }
        Projetos other = (Projetos) object;
        if ((this.idProjetos == null && other.idProjetos != null) || (this.idProjetos != null && !this.idProjetos.equals(other.idProjetos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Projetos[ idProjetos=" + idProjetos + " ]";
    }
    
}
