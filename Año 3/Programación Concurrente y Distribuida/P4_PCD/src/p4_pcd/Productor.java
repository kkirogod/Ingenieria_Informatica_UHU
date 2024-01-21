package p4_pcd;

import java.security.SecureRandom;

public class Productor extends Thread{

    private volatile PilaLenta lapila;

    public Productor(PilaLenta p) {
        lapila = p;
    }

    public void producir() {
        
        int num1;
        
        SecureRandom r1;
        SecureRandom r2 = new SecureRandom();

        for (int i = 0; i < 15; i++) {
            
            int espera = (r2.nextInt(3) + 1);

            try {
                Thread.sleep(espera);
                
                r1 = new SecureRandom();
                
                num1 = r1.nextInt(100);
                
                lapila.Apila(num1);
                //System.out.println("Apilando: " + num);

            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
    }
    
    @Override
    public void run() {
        producir();
    }
}
