package p4_pcd;

import java.security.SecureRandom;

public class Consumidor implements Runnable{

    private volatile PilaLenta lapila;

    public Consumidor(PilaLenta p) {
        lapila = p;
    }

    public void consumir() {
        
        SecureRandom r1 = new SecureRandom();
        
        for (int i = 0; i < 15; i++) {

            int espera = (r1.nextInt(3) + 1);

            try {
                Thread.sleep(espera);
                
                lapila.Desapila();

            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void run() {
        consumir();
    }
}
