package p6_pcd;

import java.util.concurrent.Semaphore;

public class Camarero extends Thread {

    private CanvasCongreso cv;
    private Semaphore leche;
    private Semaphore cafe;

    public Camarero(CanvasCongreso cv, Semaphore leche, Semaphore cafe) {
        this.cv = cv;
        this.leche = leche;
        this.cafe = cafe;
    }

    @Override
    public void run() {

        while (true) {

            try {
                rellenar();

            } catch (InterruptedException ex) {
                //System.out.println(ex.getMessage());
                break;
            }
        }
    }

    public void rellenar() throws InterruptedException {
        
        System.out.println("CAMARERO --> Rellenando cafetera...");
        
        cv.camarero('L');
        leche.release(5);
        
        //Thread.sleep(2000); /////////////////
        
        cv.camarero('C');
        cafe.release(5);
        
        //Thread.sleep(2000); /////////////////
        
        cv.fincamarero();

        Thread.sleep(6000);
    }
}
