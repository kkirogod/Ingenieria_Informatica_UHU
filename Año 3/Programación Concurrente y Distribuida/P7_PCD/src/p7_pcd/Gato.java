package p7_pcd;

import java.security.SecureRandom;

public class Gato implements Runnable {

    CanvasComedero cv;
    Comedero c;

    public Gato(CanvasComedero cv, Comedero c) {
        this.cv = cv;
        this.c = c;
    }

    @Override
    public void run() {
        
        SecureRandom r = new SecureRandom();
        int id = (int) Thread.currentThread().getId();

        try {
            
            cv.encolaGato(id);
            
            System.out.println("GATO --> SALUDA (" + id + ")");
            
            c.entraGato();
            
            cv.desencolaGato(id);
            cv.enComedero(id, 'g');
            
            System.out.println("GATO --> \t\tENTRA (" + id + ")");

            Thread.sleep((r.nextInt(3) + 1) * 1000);
            //Thread.sleep(4000);/////////////////////////////
            
            c.saleGato();
            
            cv.fueraComedero(id, 'g');
            
            System.out.println("GATO --> \t\t\t\tSALE (" + id + ")");

        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
