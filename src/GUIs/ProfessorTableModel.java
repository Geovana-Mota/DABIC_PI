package GUIs;

import Entidades.Professor;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

public class ProfessorTableModel extends AbstractTableModel {

//  ------------------------------------------------------------------------------------------------------ 
    private final Class classes[] = new Class[]{Integer.class, String.class, String.class};
    private final String colunas[] = new String[]{"Id", "Nome", "descrição"};
    private List<Professor> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
    public ProfessorTableModel(List<Professor> dados) {
        this.dados = dados;
    }

    public void setDados(List<Professor> dados) {
        this.dados = dados;
    }

    public List<Professor> getDados() {
        return this.dados;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Professor professor = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return professor.getIdProfessor();
            case 1:
                return professor.getNomeProfessor();
            case 2:
                return professor.getDescricaoProfessor();
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

// Para impedir que exista duplicidade na chave primaria
    public boolean chaveExiste(String chave) {
        for (Professor x : dados) {
            if (x.getIdProfessor().equals(chave)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aaa[];
        Professor professor = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!chaveExiste((String) aValue)) {
                    professor.setIdProfessor((Integer) aValue);
                }
                break;
            case 1:
                professor.setNomeProfessor((String) aValue);
                break;
            case 2:
                professor.setDescricaoProfessor((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public Professor getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(Professor professor) {
        return dados.indexOf(professor);
    }

    public void onAdd(Professor professor) {
        dados.add(professor);
        fireTableRowsInserted(indexOf(professor), indexOf(professor));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}
