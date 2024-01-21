package p5_pcd;

import java.awt.Color;
import java.security.SecureRandom;

public class Generador extends java.awt.Frame {

    public Generador() {
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

    public static void main(String args[]) throws InterruptedException {

        final int ancho = 800, alto = 600;
        
        Generador f = new Generador();
        f.setSize(ancho, alto);
        CanvasFisio c = new CanvasFisio(ancho, alto);
        f.add(c);
        f.setVisible(true);
        
        Centro centro = new Centro();
        SecureRandom rnd = new SecureRandom();
        Thread[] cliente = new Thread[10];

        for (int i = 0; i < 10; i++) {
            if (rnd.nextInt(100) < 60) {
                cliente[i] = new Masaje(c, centro);
            } else {
                cliente[i] = new Thread(new Rehabilita(c, centro));
            }
            cliente[i].start();

            Thread.sleep((rnd.nextInt(2) + 1)*1000);
            //Thread.sleep(4000);
        }
        
        for (int i = 0; i < 10; i++) {
            cliente[i].join();
        }

        Thread.sleep(3000);
        System.exit(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
