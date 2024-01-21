package p4_pcd;

import java.awt.Color;

public class PilaFrame extends java.awt.Frame {

    public PilaFrame() {
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

        final int ancho = 900, alto = 350, capacidad = 15;

        PilaFrame f = new PilaFrame();
        f.setSize(ancho, alto);
        f.setBackground(Color.PINK);
        //f.setLocation(100, 100);            //PARA QUE LA APP TE APARZCA MÁS CENTRADA EN LA PANTALLA

        CanvasPila c = new CanvasPila(ancho, alto, capacidad);
        //c.setLocation(20, 30);
        f.add(c);

        f.setVisible(true);

        PilaLenta p = new PilaLenta(capacidad, c);

        Productor hp1 = new Productor(p);
        Productor hp2 = new Productor(p);
        Productor hp3 = new Productor(p);
        Productor hp4 = new Productor(p);
        Thread hc1 = new Thread(new Consumidor(p));

        
        hp1.start();
        hp2.start();
        hp3.start();
        hp4.start();
        //Thread.sleep(1000);
        hc1.start();
        
        hc1.join();
        
        synchronized (p) {
            
            for (int i = 0; i < 3; i++) {
                
                p.notifyAll();
            }
            
            p.mostrarPila();
        }

        Thread.sleep(4000);
        System.exit(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
