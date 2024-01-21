package Aplicacion;


import Controlador.ControladorLogin;
import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ProyectoDDSI {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Mensaje de error");
        }

        Controlador.ControladorLogin c = new ControladorLogin();
    }
}
