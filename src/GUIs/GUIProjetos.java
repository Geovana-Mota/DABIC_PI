package GUIs;

import DAOs.*;
import Entidades.*;
import myUtil.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

import myUtil.UsarGridBagLayout;

public class GUIProjetos extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeNext = new ImageIcon(getClass().getResource("/icones/next.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnNext = new JButton(iconeNext);
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);

    JLabel labelIdProjetos = new JLabel("Id: ");
    JTextField textFieldIdProjetos = new JTextField(20);
    JLabel labelNomeProjetos = new JLabel("Nome: ");
    JTextField textFieldNomeProjetos = new JTextField(20);
    JLabel labelDescricao = new JLabel("Descrição: ");
    JTextField textFieldDescricao = new JTextField(20);
    JLabel labelProfessoridProfessor = new JLabel("Professor: ");
    JTextField textFieldProfessoridProfessor = new JTextField(20);
    JLabel labelLaboratorioidLaboratorio = new JLabel("Laboratório: ");
    JTextField textFieldLaboratorioidLaboratorio = new JTextField(20);

//Daos para FK
    DAOLaboratorio daoLaboratorio = new DAOLaboratorio();
    DAOProfessor daoProfessor = new DAOProfessor();

//Entidades para FK
    Laboratorio laboratorio = new Laboratorio();
    Professor professor = new Professor();

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOProjetos daoProjetos = new DAOProjetos();
    Projetos projetos;

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnNext.setEnabled(r);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnNext.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean idProjetos, boolean nomeProjetos, boolean quantidadeNoEstoque, boolean quantidadeMaximaEstoque, boolean statusIdLaboratorio) {
        if (idProjetos) {
            textFieldIdProjetos.requestFocus();
            textFieldIdProjetos.selectAll();
        }
        textFieldIdProjetos.setEnabled(idProjetos);
        textFieldIdProjetos.setEditable(idProjetos);
        textFieldNomeProjetos.setEditable(nomeProjetos);
        textFieldDescricao.setEditable(quantidadeNoEstoque);
        textFieldProfessoridProfessor.setEditable(quantidadeMaximaEstoque);
        textFieldLaboratorioidLaboratorio.setEditable(statusIdLaboratorio);

    }

    public void zerarAtributos() {
        textFieldNomeProjetos.setText("");
        textFieldDescricao.setText("");
        textFieldProfessoridProfessor.setText("");
        textFieldLaboratorioidLaboratorio.setText("");
    }
    Color corPadrao = labelIdProjetos.getBackground();

    public GUIProjetos(Point posicao, Dimension dimensao) {
        setTitle("Projetos");
        setSize(dimensao);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnNext.setToolTipText("Próximo novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdProjetos);
        Toolbar1.add(textFieldIdProjetos);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnNext);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

//atritubos não chave, todos no painel centro
        JPanel centro = new JPanel();
        UsarGridBagLayout usarGridBagLayout = new UsarGridBagLayout(centro);
        usarGridBagLayout.add(labelNomeProjetos, textFieldNomeProjetos, corPadrao);
        usarGridBagLayout.add(labelDescricao, textFieldDescricao, corPadrao);
        usarGridBagLayout.add(labelProfessoridProfessor, textFieldProfessoridProfessor, corPadrao);
        usarGridBagLayout.add(labelLaboratorioidLaboratorio, textFieldLaboratorioidLaboratorio, Color.yellow);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdProjetos.requestFocus();
        textFieldIdProjetos.selectAll();
        textFieldIdProjetos.setBackground(Color.GREEN);
        labelAviso.setText("Digite um Id e clic [Pesquisar]");

//--------------- listeners ----------------- 
        textFieldIdProjetos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                projetos = new Projetos();
                professor = new Professor();
                textFieldIdProjetos.setText(textFieldIdProjetos.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdProjetos.getText().equals("")) {
                    List<String> listaAuxiliar = daoProjetos.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdProjetos.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdProjetos.requestFocus();
                            textFieldIdProjetos.selectAll();
                        }
                    }

                    textFieldIdProjetos.requestFocus();
                    textFieldIdProjetos.selectAll();
                } else {
                    try {
                        projetos.setIdProjetos(Integer.valueOf(textFieldIdProjetos.getText()));
                        projetos = daoProjetos.obter(projetos.getIdProjetos());
                        if (projetos != null) { //se encontrou na lista
                            textFieldNomeProjetos.setText(String.valueOf(projetos.getNomeProjetos()));
                            textFieldDescricao.setText(String.valueOf(projetos.getDescricaoProjetos()));
                            textFieldProfessoridProfessor.setText(String.valueOf(projetos.getProfessoridProfessor()));
                            textFieldLaboratorioidLaboratorio.setText(String.valueOf(projetos.getLaboratorioidLaboratorio()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdProjetos.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdProjetos.setOpaque(true);
                        textFieldIdProjetos.selectAll();
                        textFieldIdProjetos.requestFocus();
                        textFieldIdProjetos.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true, true, true, true);
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

        // ---------------------- botao next ------------------------------
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int prox = daoProjetos.autoIdProjetos();
                textFieldIdProjetos.setText(String.valueOf(prox));
                btnRetrieve.doClick();
                btnCreate.doClick();
            }
        });

//-----------------------------  SAVE ------------------------------------------
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (acao.equals("insert")) {
                    projetos = new Projetos();
                }
                try {
                    projetos.setIdProjetos(Integer.valueOf((textFieldIdProjetos.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldIdProjetos.setBackground(Color.red);
                }
               
                projetos.setNomeProjetos(String.valueOf(textFieldNomeProjetos.getText()));
                try {
                    projetos.setDescricaoProjetos(String.valueOf((textFieldDescricao.getText())));
                } catch (Exception erro5) {
                    deuRuim = true;
                    textFieldDescricao.setBackground(Color.red);
                }
                
                
                projetos.setProfessoridProfessor(daoProfessor.obter(Integer.valueOf(textFieldProfessoridProfessor.getText().split("-")[0])));
                projetos.setLaboratorioidLaboratorio(daoLaboratorio.obter(Integer.valueOf(textFieldLaboratorioidLaboratorio.getText().split("-")[0])));
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoProjetos.inserir(projetos);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoProjetos.atualizar(projetos);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIProjetosListagem guiProjetosListagem = new GUIProjetosListagem(daoProjetos.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + projetos.getIdProjetos()+ ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoProjetos.remover(projetos);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
        textFieldLaboratorioidLaboratorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoLaboratorio.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldLaboratorioidLaboratorio.getBounds().y + textFieldLaboratorioidLaboratorio.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldLaboratorioidLaboratorio.setText(selectedItem);

                        //preparar para salvar
                        laboratorio = daoLaboratorio.obter(Integer.valueOf(aux[0]));
                        professor = daoProfessor.obter(Integer.valueOf(aux[0]));

                    } else {
                        textFieldLaboratorioidLaboratorio.requestFocus();
                        textFieldLaboratorioidLaboratorio.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum projetos cadastrado.");
                }
            }
        });
        
        textFieldNomeProjetos.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeProjetos.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeProjetos.setBackground(corPadrao);
            }
        });
        textFieldDescricao.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDescricao.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDescricao.setBackground(corPadrao);
            }
        });
        textFieldDescricao.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDescricao.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDescricao.setBackground(corPadrao);
            }
        });
        textFieldProfessoridProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldProfessoridProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldProfessoridProfessor.setBackground(corPadrao);
            }
        });
        textFieldLaboratorioidLaboratorio.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldLaboratorioidLaboratorio.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldLaboratorioidLaboratorio.setBackground(Color.yellow);
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai   
                dispose();
            }
        });

        pack();
        setModal(true);
        setLocation(posicao);
        setVisible(true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new GUIProjetos(new Point(880, 250), new Dimension(800, 600));
    }
}
