package sistema.telas;

import sistema.BancoDeDados;
import sistema.entidades.Cargo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CargosConsultar extends JPanel {

    Cargo cargoAtual;
    JLabel labelTitulo, labelCargo;
    JTextField campoCargo;
    JButton botaoPesquisar, botaoEditar, botaoExcluir;
    DefaultListModel<Cargo> listasCargoModelo = new DefaultListModel();
    JList<Cargo> listaCargos;

    public CargosConsultar(){
        criarComponentes();
        criarEventos();
    }
    public void criarComponentes(){
        setLayout(null);
        labelTitulo = new JLabel("Consulta de cargos", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelCargo = new JLabel("Nome do cargo: ", JLabel.LEFT);
        campoCargo = new JTextField();
        botaoPesquisar = new JButton("Pesquisar cargo");
        botaoEditar = new JButton("Editar");
        botaoEditar.setEnabled(false);
        botaoExcluir = new JButton("Excluir");
        botaoExcluir.setEnabled(false);
        listaCargos = new JList();
        listaCargos.setModel(listasCargoModelo);
        listaCargos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        labelTitulo.setBounds(20,20,660,40);
        labelCargo.setBounds(150,120,400,20);
        campoCargo.setBounds(150,150,400,40);
        botaoPesquisar.setBounds(560,140,130,40);
        botaoEditar.setBounds(560,360,130,40);
        listaCargos.setBounds(150,200,400,240);
        botaoExcluir.setBounds(560,400,130,40);

        add(labelTitulo);
        add(labelCargo);
        add(campoCargo);
        add(botaoPesquisar);
        add(botaoEditar);
        add(botaoExcluir);
        add(listaCargos);

        setVisible(true);

    }
    private void criarEventos(){
        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlPesquisarCargos(campoCargo.getText());

            }
        });
        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sqlDeletarCargo();
            }
        });
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargoAtual = listaCargos.getSelectedValue();
                if(cargoAtual == null){
                    botaoEditar.setEnabled(false);
                    botaoExcluir.setEnabled(false);
                }
                else{
                    botaoEditar.setEnabled(true);
                    botaoExcluir.setEnabled(true);
                }
            }
        });
    }
    private void sqlPesquisarCargos(String text){
        Connection conexao;
        Statement instrucaoSQL;
        ResultSet resultados;

        try{
            conexao = DriverManager.getConnection(BancoDeDados.urlConexao, BancoDeDados.usuario, BancoDeDados.senha);
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * FROM cargos WHERE nome like '%"+listaCargos.getSelectedValue()+"%'");
            listasCargoModelo.clear();

            while (resultados.next()){
                Cargo cargo = new Cargo();
                cargo.setId(resultados.getInt("id"));
                cargo.setNome(resultados.getString("nome"));

                listasCargoModelo.addElement(cargo);

            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ocorreu um erro");
            Logger.getLogger(cargosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
