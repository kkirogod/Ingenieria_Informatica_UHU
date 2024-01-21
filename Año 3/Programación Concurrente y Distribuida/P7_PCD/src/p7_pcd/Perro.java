package p7_pcd;

import java.security.SecureRandom;

public class Perro extends Thread {

    CanvasComedero cv;
    Comedero c;

    public Perro(CanvasComedero cv, Comedero c) {
        this.cv = cv;
        this.c = c;
    }

    @Override
    public void run() {

        SecureRandom r = new SecureRandom();
        int id = (int) Thread.currentThread().getId();

        try {
            
            cv.encolaPerro(id);
            
            System.out.println("PERRO --> SALUDA (" + id + ")");
            
            c.entraPerro();
            
            cv.desencolaPerro(id);
            cv.enComedero(id, 'p');
            
            System.out.println("PERRO --> \t\tENTRA (" + id + ")");

            Thread.sleep((r.nextInt(3) + 1) * 1000);
            //Thread.sleep(4000);/////////////////////////////
            
            c.salePerro();
            
            cv.fueraComedero(id, 'p');
            
            System.out.println("PERRO --> \t\t\t\tSALE (" + id + ")");

        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
