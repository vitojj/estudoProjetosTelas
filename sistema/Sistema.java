package sistema;

import javax.swing.*;

import sistema.telas.CargosConsultar;
import sistema.telas.Inicio;
import sistema.telas.Login;
import sistema.telas.cargosInserir;
//import sistema.telas.RelatorioCargos;

public class Sistema {
    public static JPanel tela;
    public static JFrame frame;

    public static void main(String[] args) throws ClassNotFoundException {
        criarComponentes();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }
    private static void criarComponentes(){
        frame = new JFrame("Sistema");
        frame.setSize(700,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //Navegador.login();
        tela = new cargosInserir();
        tela.setVisible(true);
        frame.add(tela);
        frame.setVisible(true);
    }
}
