package p6_pcd;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.concurrent.Semaphore;

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

        final int ancho = 850, alto = 650, numAsistentes = 30;

        Generador f = new Generador();
        f.setSize(ancho, alto);    
        CanvasCongreso c = new CanvasCongreso(ancho, alto);
        f.add(c);
        f.setVisible(true);

        SecureRandom rnd = new SecureRandom();
        
        Semaphore leche = new Semaphore(10);
        Semaphore cafe = new Semaphore(10);
        Semaphore salaLeche = new Semaphore(3);
        Semaphore salaCafe = new Semaphore(3);
        Semaphore papelera = new Semaphore(1);

        Thread[] asistente = new Thread[numAsistentes];
        Thread camarero = new Camarero(c, leche, cafe);

        camarero.start();

        for (int i = 0; i < numAsistentes; i++) {
            if (rnd.nextInt(100) < 50) {
                asistente[i] = new Cortado(c, leche, cafe, salaLeche, salaCafe, papelera);
            } else {
                asistente[i] = new Thread(new Manchado(c, leche, cafe, salaLeche, salaCafe, papelera));
            }
            asistente[i].start();

            Thread.sleep(500);
            //Thread.sleep(5000); /////////////////
        }

        for (int i = 0; i < numAsistentes; i++) {
            asistente[i].join();
        }
        
        camarero.interrupt();
        camarero.join();

        Thread.sleep(3000);
        System.exit(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
