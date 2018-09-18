package DAOs;

import Entidades.Laboratorio;
import java.util.ArrayList;
import java.util.List;

public class DAOLaboratorio extends DAOGenerico<Laboratorio> {

    private List<Laboratorio> lista = new ArrayList<>();

    public DAOLaboratorio() {
        super(Laboratorio.class);
    }

    public int autoIdLaboratorio() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idLaboratorio) FROM Laboratorio e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Laboratorio> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Laboratorio e WHERE e.idLaboratorio LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Laboratorio> listById(int id) {
        return em.createQuery("SELECT e FROM Laboratorio + e WHERE e.nomeLaboratorio= :id").setParameter("id", id).getResultList();
    }

    public List<Laboratorio> listInOrderNome() {
        return em.createQuery("SELECT e FROM Laboratorio e ORDER BY e.nomeLaboratorio").getResultList();
    }

    public List<Laboratorio> listInOrderId() {
        return em.createQuery("SELECT e FROM Laboratorio e ORDER BY e.idLaboratorio").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Laboratorio> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdLaboratorio() + "-" + lf.get(i).getNomeLaboratorio());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOLaboratorio daoLaboratorio = new DAOLaboratorio();
        List<Laboratorio> listaLaboratorio = daoLaboratorio.list();
        for (Laboratorio laboratorio : listaLaboratorio) {
            System.out.println(laboratorio.getIdLaboratorio() + "-" + laboratorio.getNomeLaboratorio());
        }
    }
}
