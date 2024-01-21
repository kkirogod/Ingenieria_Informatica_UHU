package p2_pcd;

import java.util.Random;

public class Productor extends Thread{

    private volatile PilaLenta lapila;

    public Productor(PilaLenta p) {
        lapila = p;
    }

    public void producir() {
        Random r = new Random(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {

            int num = r.nextInt(100);

            try {
                lapila.Apila(num);
                //System.out.println("Apilando: " + num);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    @Override
    public void run() {
        producir();
    }
}
