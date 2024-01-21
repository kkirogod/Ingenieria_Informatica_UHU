package p8_pcd;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        final int ancho = 1700, alto = 1000;

        Generador g = new Generador();
        g.setSize(ancho, alto);
        CanvasCentro cv = new CanvasCentro(ancho, alto);
        g.add(cv);
        g.setVisible(true);

        Centro centro = new Centro();

        SecureRandom rnd = new SecureRandom();

        ExecutorService thpTarjeta = Executors.newFixedThreadPool(10);
        ExecutorService thpEfectivo = Executors.newFixedThreadPool(10);
        ArrayList<Future<Integer>> tiempoTarjeta = new ArrayList();
        ArrayList<Future<Integer>> tiempoEfectivo = new ArrayList();

        for (int i = 0; i < 50; i++) {
            if (rnd.nextInt(100) < 50) {

                Tarjeta t = new Tarjeta(cv, centro, i);
                System.out.println("\nLanzando cliente (T) --> " + i + "\n");

                Future<Integer> f = thpTarjeta.submit(t);
                tiempoTarjeta.add(f);
            } else {
                Efectivo e = new Efectivo(cv, centro, i);
                System.out.println("\nLanzando cliente (E) --> " + i + "\n");

                Future<Integer> f = thpEfectivo.submit(e);
                tiempoEfectivo.add(f);
            }
            Thread.sleep(500);
        }

        thpTarjeta.shutdown();
        thpEfectivo.shutdown();

        int sumaTarjeta = 0, sumaEfectivo = 0;

        for (int i = 0; i < tiempoTarjeta.size(); i++) {
            try {
                Future<Integer> f = tiempoTarjeta.get(i);
                sumaTarjeta += f.get();

            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int i = 0; i < tiempoEfectivo.size(); i++) {
            try {
                Future<Integer> f = tiempoEfectivo.get(i);
                sumaEfectivo += f.get();

            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Tiempo total de espera TARJETA: " + sumaTarjeta);
        System.out.println("Tiempo total de espera EFECTIVO: " + sumaEfectivo);

        Thread.sleep(3000);
        System.exit(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
