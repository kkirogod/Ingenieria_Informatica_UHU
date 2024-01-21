package Controlador;

import Config.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.hibernate.SessionFactory;

public class ControladorLogin implements ActionListener {

    private SessionFactory sessionFactory;
    private VistaMensaje vMensaje;
    private VistaLogin vLogin;
    private ControladorPrincipal controladorP;
    private String server;

    public ControladorLogin() {
        vMensaje = new VistaMensaje();
        vLogin = new VistaLogin();
        
        addListeners();
        
        vLogin.setLocationRelativeTo(null);
        vLogin.setVisible(true);
        
        vLogin.ComboBoxBD.setSelectedItem(0);
    }

    private void conectarBD() {
        try {
            server = (String) (vLogin.ComboBoxBD.getSelectedItem());
            
            if (server.equals("Oracle")) {
                sessionFactory = HibernateUtilOracle.getSessionFactory();
            } else if (server.equals("MariaDB")) {
                sessionFactory = HibernateUtilMariaDB.getSessionFactory();
            }
            vMensaje.mensajeConsola("Conexión Correcta con Hibernate");
            
            JOptionPane.showMessageDialog(vLogin, "La conexión a " + server + " ha tenido éxito");
            
        } catch (ExceptionInInitializerError e) {
            Throwable cause = e.getCause();
            //System.out.println("Error en la conexión. Revise el fichero .cfg.xml: " + cause.getMessage());
            vMensaje.mensajeVentana(vLogin, "error", "Error en la conexion. Revise el fichero .cfg.xml" + cause.getMessage());
            System.exit(0);
        }
    }
    
    private void addListeners() {
        vLogin.ButtonConectar.addActionListener(this);
        vLogin.ButtonSalirLogin.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {
            case "conectar":
                conectarBD();
                vLogin.dispose();
                controladorP = new ControladorPrincipal(sessionFactory);
                break;
            case "salirLogin":
                vLogin.dispose();
                System.exit(0);
                break;
        }
    }
}