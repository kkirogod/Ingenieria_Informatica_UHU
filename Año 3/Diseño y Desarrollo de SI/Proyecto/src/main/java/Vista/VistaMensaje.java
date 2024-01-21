package Vista;

import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VistaMensaje {

    public void mensajeConsola(String s) {
        System.out.println(s);
    }

    public void mensajeVentana(Component vista, String tipo, String msg) {
        switch (tipo) {
            case "error":
                JOptionPane.showMessageDialog(vista, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
            case "info":
                JOptionPane.showMessageDialog(vista, msg, "INFO", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "warning":
                JOptionPane.showMessageDialog(vista, msg, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    public int mensajeDialogo(Component C, String texto) {
        int opcion = JOptionPane.showConfirmDialog(C, texto,
                "ATENCIÓN", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
        return opcion;
    }
}
