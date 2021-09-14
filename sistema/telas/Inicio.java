package sistema.telas;

import javax.swing.*;

public class Inicio extends JPanel {
    JLabel labelTitulo;
    public Inicio(){
        criarComponentes();
        criarEventos();
    }
    public void criarComponentes(){
        setLayout(null);
        labelTitulo = new JLabel("Bem vindo ao sistema!", JLabel.CENTER);
        labelTitulo.setBounds(20,100,660,40);
        add(labelTitulo);
        setVisible(true);
    }
    public void criarEventos(){

    }

}

