package GUIs;

import DAOs.*;
import Entidades.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;

import myUtil.UsarGridBagLayout;

public class GUIAluno extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeNext = new ImageIcon(getClass().getResource("/icones/next.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    ImageIcon iconeImagem = new ImageIcon(getClass().getResource("/icones/newEditando.png"));
    JButton btnNext = new JButton(iconeNext);
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);

    private JPanel pnProjetos = new JPanel();
    private DefaultListModel modeloListaProjetos = new DefaultListModel();
    private JList listaDeProjetos = new JList(modeloListaProjetos);
    private JScrollPane scrollListaProjetos = new JScrollPane(listaDeProjetos);
    JButton btnProjetos = new JButton("Projetos Cadastrados");
    JLabel labelProjetos = new JLabel("Projetos");
    JTextField textFieldProjetos = new JTextField(20);

    JLabel labelIdAluno = new JLabel("Id: ");
    JTextField textFieldIdAluno = new JTextField(20);
    JLabel labelNomeAluno = new JLabel("Nome: ");
    JTextField textFieldNomeAluno = new JTextField(20);
    private JButton btAbrirImagem = new JButton(iconeImagem);
    JTextField tfCaminho = new JTextField(20);
    private String caminho;
    private Image imagemAux;
    private ImageIcon icone;
    private JLabel rotulo = new JLabel("Selecionar Imagem:");
    JPanel pnImagem = new JPanel();

    JPanel pnCentro = new JPanel(new GridLayout(3,1));
    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOAluno daoAluno = new DAOAluno();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Aluno aluno;

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

    private void habilitarAtributos(boolean idAluno, boolean nomeAluno, boolean foto) {
        if (idAluno) {
            textFieldIdAluno.requestFocus();
            textFieldIdAluno.selectAll();
        }
        textFieldIdAluno.setEnabled(idAluno);
        textFieldIdAluno.setEditable(idAluno);
        textFieldNomeAluno.setEditable(nomeAluno);
        tfCaminho.setEditable(foto);

    }

    public void zerarAtributos() {
        textFieldNomeAluno.setText("");
        tfCaminho.setText("");
    }
    Color corPadrao = labelIdAluno.getBackground();

    public GUIAluno(Point posicao, Dimension dimensao) {
        setTitle("Aluno");
        setSize(dimensao);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnNext.setToolTipText("Próximo novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        btAbrirImagem.setToolTipText("Abrir Imagem");

        try {
            String caminho = "";
            tfCaminho.setText(caminho);
            icone = new ImageIcon(getClass().getResource(caminho));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            rotulo.setIcon(icone);
        } catch (Exception err) {
            System.out.println("erro " + err.getLocalizedMessage());
        }

        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdAluno);
        Toolbar1.add(textFieldIdAluno);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnNext);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
//        Toolbar1.add(btAbrirImagem);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

//atritubos não chave, todos no painel centro
        JPanel centro = new JPanel();
        UsarGridBagLayout usarGridBagLayout = new UsarGridBagLayout(centro);
        usarGridBagLayout.add(labelNomeAluno, textFieldNomeAluno, corPadrao);
        usarGridBagLayout.add(rotulo, tfCaminho, corPadrao);
        pnImagem.add(btAbrirImagem);
        pnAvisos.add(labelAviso);
        //        pnAvisos.setBackground(Color.blue);
        
        pnCentro.add(centro);
        pnCentro.add(pnImagem);
        pnCentro.add(pnProjetos);
        
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);


        cp.add(pnProjetos, BorderLayout.EAST);
        pnProjetos.add(btnProjetos, BorderLayout.NORTH);
        pnProjetos.add(scrollListaProjetos);
        pnProjetos.setBackground(Color.decode("#7FFFD4"));

        textFieldIdAluno.requestFocus();
        textFieldIdAluno.selectAll();
        textFieldIdAluno.setBackground(Color.GREEN);
        labelAviso.setText("Digite um Id e clic [Pesquisar]");

//--------------- listeners ----------------- 
        textFieldIdAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnProjetos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloListaProjetos.clear();
                List<Projetos> lpp = new DAOProjetos().list();
//                List<Projetos> lpp = new DAOProjetos().listById(Integer.valueOf(textFieldIdAluno.getText()));
                int cont = 0;
                for (Projetos projetos : lpp) {
                    modeloListaProjetos.add(cont, projetos.getIdProjetos()
                            + " - " + projetos.getNomeProjetos());
                    cont++;
                }

            }
        });

        listaDeProjetos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    labelProjetos.setText(String.valueOf(listaDeProjetos.getSelectedValue()));
                    textFieldProjetos.requestFocus();
                    textFieldProjetos.selectAll();

                }
            }
        });

        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aluno = new Aluno();
                textFieldIdAluno.setText(textFieldIdAluno.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdAluno.getText().equals("")) {
                    List<String> listaAuxiliar = daoAluno.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdAluno.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            caminho = aluno.getFoto();
                            tfCaminho.setText(caminho);
                            icone = new ImageIcon(caminho);
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            rotulo.setIcon(icone);
                            textFieldIdAluno.requestFocus();
                            textFieldIdAluno.selectAll();
                        }
                        btAbrirImagem.setEnabled(false);
                    }

                    textFieldIdAluno.requestFocus();
                    textFieldIdAluno.selectAll();
                } else {
                    try {
                        aluno.setIdAluno(Integer.valueOf(textFieldIdAluno.getText()));
                        aluno = daoAluno.obter(aluno.getIdAluno());
                        if (aluno != null) { //se encontrou na lista
                            textFieldNomeAluno.setText(String.valueOf(aluno.getNomeAluno()));
                            tfCaminho.setText(String.valueOf(aluno.getFoto()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
//                            List<Projetos> lpd = new DAOProjetos().listById(Integer.valueOf(textFieldIdAluno.getText()));
                            List<Projetos> pp = aluno.getProjetosList();
                            if (pp != null) {
                                labelAviso.setText("Aluno está inscrito no Projeto: " + pp.get(0).getNomeProjetos());
                                System.out.println(pp.get(0).getNomeProjetos());
                            }
//                            int cont = 0;
//                            for (Projetos projetos : lpd) {
//                                modeloListaProjetos.add(cont, projetos.getIdProjetos()
//                                        + " - " + projetos.getNomeProjetos());
//                                cont++;
//                            }
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdAluno.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdAluno.setOpaque(true);
                        textFieldIdAluno.selectAll();
                        textFieldIdAluno.requestFocus();
                        textFieldIdAluno.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true, true);
                textFieldNomeAluno.requestFocus();
                mostrarBotoes(false);
                btAbrirImagem.setEnabled(true);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

        // ---------------------- botao next ------------------------------
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int prox = daoAluno.autoIdAluno();
                textFieldIdAluno.setText(String.valueOf(prox));
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
                    aluno = new Aluno();
                }
                try {
                    aluno.setIdAluno(Integer.valueOf((textFieldIdAluno.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldIdAluno.setBackground(Color.red);
                }
                aluno.setNomeAluno(String.valueOf(textFieldNomeAluno.getText()));
                aluno.setFoto(String.valueOf(tfCaminho.getText()));
                
                List<Projetos> lp = aluno.getProjetosList();
                lp.clear();
                lp.add(new DAOProjetos().obter(Integer.valueOf(labelProjetos.getText().split("-")[0].trim())));
                aluno.setProjetosList(lp);
                
                caminho = tfCaminho.getText();
                aluno.setFoto(caminho);
                caminho = "";
                icone = new ImageIcon(caminho);
                rotulo.setIcon(icone);
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoAluno.inserir(aluno);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoAluno.atualizar(aluno);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true, false, false);
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
                habilitarAtributos(true, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIAlunoListagem guiAlunoListagem = new GUIAlunoListagem(daoAluno.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                btAbrirImagem.setEnabled(true);
                habilitarAtributos(false, true, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + aluno.getNomeAluno() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoAluno.remover(aluno);
                    zerarAtributos();
                    String caminho = "";

                    icone = new ImageIcon(caminho);
                    rotulo.setIcon(icone);

                    tfCaminho.setText("");
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNomeAluno.requestFocus();
                    textFieldNomeAluno.selectAll();
                }
            }
        });
        btAbrirImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(pnAvisos) == JFileChooser.APPROVE_OPTION) {
                    File img = fc.getSelectedFile();
                    String caminho = fc.getSelectedFile().getAbsolutePath();
                    try {
                        tfCaminho.setText(caminho);
                        icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                        imagemAux = icone.getImage();
                        icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                        rotulo.setIcon(icone);
                    } catch (Exception ex) {
                        System.out.println("Erro: " + ex.getMessage());
                    }
                }
            }
        });

// ----------------   Janela Pesquisar para FKs -----------------
        textFieldNomeAluno.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeAluno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeAluno.setBackground(corPadrao);
            }
        });
        tfCaminho.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfCaminho.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfCaminho.setBackground(corPadrao);
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
        new GUIAluno(new Point(880, 250), new Dimension(800, 600));
    }
}
