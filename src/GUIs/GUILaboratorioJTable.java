package GUIs;

import myUtil.CentroDoMonitorMaior;
import DAOs.DAOLaboratorio;
import Entidades.Laboratorio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.AbstractCellEditor;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import java.awt.Dimension;
import java.awt.Point;
import DAOs.DAOTecnicos;
import Entidades.Tecnicos;

public class GUILaboratorioJTable extends JDialog {

//  ------------------------------------------------------------------------------------------------------ 
    private Container cp;
    private final JPanel painelAvisos = new JPanel();
    private final JButton btnAdd = new JButton("Adicionar");
    private final JButton btnRem = new JButton("Remover");
    private final JButton btnCarregar = new JButton("Carregar dados");

    private JTable table = new JTable();
    private LaboratorioTableModel tableModel;

    private DAOLaboratorio daoLaboratorio = new DAOLaboratorio();

    public GUILaboratorioJTable(Point posicao, Dimension dimensao) {

        setTitle("Laboratório");
        setLayout(new FlowLayout());
        setSize(dimensao);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(BorderLayout.NORTH, painelAvisos);

        List< Laboratorio> lista = new ArrayList<>();
        tableModel = new LaboratorioTableModel(lista);
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        cp.add(scrollPane);

        painelAvisos.add(new JLabel("Tecle INS para Inserir"));
        painelAvisos.add(new JLabel("   --   "));
        painelAvisos.add(new JLabel("Tecla DEL para Excluir"));
        painelAvisos.setBackground(Color.cyan);

//-------------- FK Tecnicos ------------------------
        TableColumn tipoColumn0 = table.getColumnModel().getColumn(3);
        JComboBox comboBox0 = new JComboBox();
        List<Tecnicos> ltc0 = new DAOTecnicos().list();
        for (int i = 0; i < ltc0.size(); i++) {
            comboBox0.addItem(ltc0.get(i).getIdTecnicos() + "-" + ltc0.get(i).getNomeTecnicos());;
        }
        tipoColumn0.setCellEditor(new DefaultCellEditor(comboBox0));

        table.setDefaultEditor(Date.class, new DateEditor());
        table.setDefaultRenderer(Date.class, new DateRenderer());

        // É necessário clicar antes na tabela para o código funcionar
        InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = table.getActionMap();

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0);
        im.put(enterKey, "Action.insert");

        actionMap.put("Action.insert", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnAdd.doClick();
            }
        });

//---------------------------------- button delete -----------------------------
        KeyStroke delKey = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        im.put(delKey, "Action.delete");

        actionMap.put("Action.delete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (table.getSelectedRow() >= 0) {

                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(cp,
                            "Confirma a exclusão do Laboratório [" + 
                                    tableModel.getValue(table.getSelectedRow()).getIdLaboratorio() + " - "
                            + tableModel.getValue(table.getSelectedRow()).getLocalizacao() + "]?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                    
                        )) {
                        btnRem.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela a Laboratório a ser excluída");
                }
            }
        });

//========================================== fechar a janela ============================================
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                dispose();
            }
        });
//========================================== botão add ============================================

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Laboratorio laboratorio = new Laboratorio();
                laboratorio.setIdLaboratorio(daoLaboratorio.autoIdLaboratorio());
                laboratorio.setNomeLaboratorio("");
                laboratorio.setLocalizacao("");
                List<Tecnicos> listaTecnicos = new DAOTecnicos().list();
                Tecnicos tecnicos;
                if (listaTecnicos.size() > 0) {
                    tecnicos = listaTecnicos.get(0);
                    laboratorio.setTecnicosIdTecnicos(tecnicos);
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há Tecnicos cadastrado(a), Laboratório depende de Técnicos. Cadastre.");
                }
                daoLaboratorio.inserir(laboratorio);
                tableModel.onAdd(laboratorio);
                tableModel.fireTableDataChanged();
            }
        });//============================================ botao remover =======================================================

        btnRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    Laboratorio laboratorio = tableModel.getValue(table.getSelectedRow());
                    daoLaboratorio.remover(laboratorio);
                    tableModel.onRemove(table.getSelectedRows());

                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela a conta a ser excluída");
                    table.requestFocus();
                }
                tableModel.fireTableDataChanged();
            }
        });//============================================ botao carregar =======================================================

        btnCarregar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DAOLaboratorio daoLaboratorio = new DAOLaboratorio();
                try {
                    List<Laboratorio> lc = daoLaboratorio.list();
                    tableModel.setDados(lc);
                    tableModel.fireTableDataChanged();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro ao carregar os dados..." + ex.getMessage());
                }
            }

        });
//============================================ listener table =======================================================

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // if (tableModel.mudou) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    Laboratorio c = tableModel.getValue(table.getSelectedRow());
                    daoLaboratorio.atualizar(c);
                }
                //}
            }
        });//============================================ fim do construtor gui =======================================================

        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();

        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        btnCarregar.doClick();//carrega os dados 

        setModal(true);
        setVisible(true);

    } //fim do construtor da GUI

//============================================ date render =======================================================
    private static class DateRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!(value instanceof Date)) {
                return this;
            }
            DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
            setText(DATE_FORMAT.format((Date) value));
            return this;
        }
    }

//============================================ date editor =======================================================
    private static class DateEditor extends AbstractCellEditor implements TableCellEditor {

        private static final long serialVersionUID = 1L;
        private final JSpinner theSpinner;
        private Object value;

        DateEditor() {
            theSpinner = new JSpinner(new SpinnerDateModel());
            theSpinner.setOpaque(true);
            theSpinner.setEditor(new JSpinner.DateEditor(theSpinner, "dd/MM/yyyy"));
        }

        @Override
        public Object getCellEditorValue() {
            return theSpinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            theSpinner.setValue(value);
            if (isSelected) {
                theSpinner.setBackground(table.getSelectionBackground());
            } else {
                theSpinner.setBackground(table.getBackground());
            }
            return theSpinner;
        }
    }

    public static void main(String[] args) {
        new GUILaboratorioJTable(new Point(880, 250), new Dimension(800, 600));
    }
}
