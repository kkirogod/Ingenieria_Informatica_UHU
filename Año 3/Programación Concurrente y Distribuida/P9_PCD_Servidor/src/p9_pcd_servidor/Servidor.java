package p9_pcd_servidor;

import java.awt.Color;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.SecureRandom;

public class Servidor extends java.awt.Frame {

    public Servidor() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setSize(new java.awt.Dimension(600, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    public static void main(String args[]) throws InterruptedException, RemoteException, IOException {

        final int ancho = 1700, alto = 1000;

        Servidor server = new Servidor();
        server.setSize(ancho, alto);
        CanvasCentro cv = new CanvasCentro(ancho, alto);
        server.add(cv);
        server.setVisible(true);

        Centro centro = new Centro(cv);

        SecureRandom rnd = new SecureRandom();
        
        //
        Registry registro = LocateRegistry.createRegistry(2023);
        registro.rebind("centroRemoto", centro);
        //
        
        System.out.println("Servidor Funcionando ....");
        System.out.println("pulsa una tecla para finalizar");

        System.in.read();

        System.out.println("Saliendo del servidor ...");

        Thread.sleep(2000);
        System.exit(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
