package p5_pcd;

import java.security.SecureRandom;

public class Rehabilita implements Runnable {

    CanvasFisio cv;
    Centro c;

    public Rehabilita(CanvasFisio cv, Centro c) {
        this.cv = cv;
        this.c = c;
    }

    @Override
    public void run() {
        
        SecureRandom r = new SecureRandom();
        int id = (int) Thread.currentThread().getId();
        
        try {
            System.out.println("\nSoy el cliente(R) " + id + " | " + Thread.currentThread().getName() + "\n");
            
            cv.encolaclienteF(id);
            
            c.entraRehabilitacion();
            
            cv.desencolaclienteF(id);
            cv.enFisio(id, 'f');
            
            System.out.println("                        Cliente(R) " + id  + " | " + Thread.currentThread().getName() + "             --> FISIO");

            Thread.sleep((r.nextInt(3)+2)*1000);
            //Thread.sleep(4000);
            
            c.saleRehabilitacion();
            cv.fueraFisio(id, 'f');
            
            System.out.println("                                        Cliente(R) " + id  + " | " + Thread.currentThread().getName() + "             --> VESTUARIO");
            
            Thread.sleep(2000);
            //Thread.sleep(4000);
            
            c.termina();
            cv.fueraVestuario();
            
            System.out.println("TERMINA --> Cliente(R) " + id  + " | " + Thread.currentThread().getName());
            
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
