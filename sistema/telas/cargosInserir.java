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

public class cargosInserir extends JPanel {
    JLabel labelTitulo, labelCargo;
    JTextField campoCargo;
    JButton botaoConfirmar;

    public cargosInserir(){
        criarComponentes();
        criarEventos();
    }
    public void criarComponentes(){
        setLayout(null);

        labelTitulo = new JLabel("Cadastro do cargo", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelCargo = new JLabel("Nome do cargo", JLabel.LEFT);
        campoCargo = new JTextField();
        botaoConfirmar = new JButton("Salvar");

        labelTitulo.setBounds(20,20,660,40);
        labelCargo.setBounds(150,120,400,20);
        campoCargo.setBounds(150,140,400,20);
        botaoConfirmar.setBounds(250,380,200,40);

        add(labelTitulo);
        add(labelCargo);
        add(campoCargo);
        add(botaoConfirmar);

        setVisible(true);
    }
    private void criarEventos(){
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Cargo novoCargo = new Cargo();
                novoCargo.setNome(campoCargo.getText());

                sqlInserirCargo(novoCargo);
            }
        });
    }
    private void sqlInserirCargo(Cargo novoCargo){
        if(campoCargo.getText().length()<=3){
            JOptionPane.showMessageDialog(null, "Por favor preencha corretamente");
            return;
        }

        Connection conexao;
        Statement instrucaoSQL;
        ResultSet resultados;
        try{
            conexao = DriverManager.getConnection(BancoDeDados.urlConexao, BancoDeDados.usuario, BancoDeDados.senha);
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            instrucaoSQL.executeUpdate("INSERT INTO cargos (nome) VALUES ('"+novoCargo.getNome()+"'");
            JOptionPane.showMessageDialog(null, "Cargo adicionado com suceso");

        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Ocorreu um erro ao adicionar o cargo");
            Logger.getLogger(cargosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Cargo() {
        String Name;

    }
}

