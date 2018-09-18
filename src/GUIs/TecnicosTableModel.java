package GUIs;

import Entidades.Tecnicos;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

public class TecnicosTableModel extends AbstractTableModel {

//  ------------------------------------------------------------------------------------------------------ 
    private final Class classes[] = new Class[]{Integer.class, String.class};
    private final String colunas[] = new String[]{"ID", "Nome"};
    private List<Tecnicos> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
    public TecnicosTableModel(List<Tecnicos> dados) {
        this.dados = dados;
    }

    public void setDados(List<Tecnicos> dados) {
        this.dados = dados;
    }

    public List<Tecnicos> getDados() {
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

        Tecnicos tecnicos = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tecnicos.getIdTecnicos();
            case 1:
                return tecnicos.getNomeTecnicos();
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
        for (Tecnicos x : dados) {
            if (x.getIdTecnicos().equals(chave)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aaa[];
        Tecnicos tecnicos = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!chaveExiste((String) aValue)) {
                    tecnicos.setIdTecnicos((Integer) aValue);
                }
                break;
            case 1:
                tecnicos.setNomeTecnicos((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public Tecnicos getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(Tecnicos tecnicos) {
        return dados.indexOf(tecnicos);
    }

    public void onAdd(Tecnicos tecnicos) {
        dados.add(tecnicos);
        fireTableRowsInserted(indexOf(tecnicos), indexOf(tecnicos));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}
