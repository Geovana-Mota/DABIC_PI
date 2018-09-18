package DAOs;

import Entidades.Projetos;
import java.util.ArrayList;
import java.util.List;

public class DAOProjetos extends DAOGenerico<Projetos> {

    private List<Projetos> lista = new ArrayList<>();

    public DAOProjetos() {
        super(Projetos.class);
    }

    public int autoIdProjetos() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idProjetos) FROM Projetos e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Projetos> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Projetos e WHERE e.nomeProjetos LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Projetos> listById(int id) {
        return em.createQuery("SELECT e FROM Projetos e WHERE e.idProjetos= :id").setParameter("id", id).getResultList();
    }

    public List<Projetos> listInOrderNome() {
        return em.createQuery("SELECT e FROM Projetos e ORDER BY e.nomeProjetos").getResultList();
    }

    public List<Projetos> listInOrderId() {
        return em.createQuery("SELECT e FROM Projetos e ORDER BY e.idProjetos").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Projetos> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdProjetos() + "-" + lf.get(i).getNomeProjetos());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOProjetos daoProjetos = new DAOProjetos();
        List<Projetos> listaProjetos = daoProjetos.list();
        for (Projetos aluno : listaProjetos) {
            System.out.println(aluno.getIdProjetos() + "-" + aluno.getNomeProjetos());
        }
    }
}
