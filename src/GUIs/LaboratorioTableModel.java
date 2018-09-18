package GUIs;

import Entidades.Laboratorio;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import Entidades.Tecnicos;
import DAOs.DAOTecnicos;

public class LaboratorioTableModel extends AbstractTableModel {

//  ------------------------------------------------------------------------------------------------------ 
    private final Class classes[] = new Class[]{Integer.class, String.class, String.class, Tecnicos.class};
    private final String colunas[] = new String[]{"Id", "Nome", "Localização", "Técnicos"};
    private List<Laboratorio> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
    public LaboratorioTableModel(List<Laboratorio> dados) {
        this.dados = dados;
    }

    public void setDados(List<Laboratorio> dados) {
        this.dados = dados;
    }

    public List<Laboratorio> getDados() {
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

        Laboratorio laboratorio = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return laboratorio.getIdLaboratorio();
            case 1:
                return laboratorio.getNomeLaboratorio();
            case 2:
                return laboratorio.getLocalizacao();
            case 3:
                return laboratorio.getTecnicosIdTecnicos().getIdTecnicos();
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
        for (Laboratorio x : dados) {
            if (x.getIdLaboratorio().equals(chave)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aaa[];
        Laboratorio laboratorio = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!chaveExiste((String) aValue)) {
                    laboratorio.setIdLaboratorio((Integer) aValue);
                }
                break;
            case 1:
                laboratorio.setNomeLaboratorio((String) aValue);
                break;
            case 2:
                laboratorio.setLocalizacao((String) aValue);
                break;
            case 3:
                aaa = String.valueOf(aValue).split("-");
                Tecnicos vTecnicos = new DAOTecnicos().obter(Integer.valueOf(aaa[0].trim()));
                laboratorio.setTecnicosIdTecnicos(vTecnicos);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public Laboratorio getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(Laboratorio laboratorio) {
        return dados.indexOf(laboratorio);
    }

    public void onAdd(Laboratorio laboratorio) {
        dados.add(laboratorio);
        fireTableRowsInserted(indexOf(laboratorio), indexOf(laboratorio));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}
