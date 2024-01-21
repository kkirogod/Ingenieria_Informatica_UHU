package p6_pcd;

import java.security.SecureRandom;
import java.util.concurrent.Semaphore;

public class Manchado implements Runnable {

    CanvasCongreso cv;
    Semaphore leche;
    Semaphore cafe;
    Semaphore salaLeche;
    Semaphore salaCafe;
    Semaphore papelera;

    public Manchado(CanvasCongreso cv, Semaphore leche, Semaphore cafe, Semaphore salaLeche, Semaphore salaCafe, Semaphore papelera) {
        this.cv = cv;
        this.leche = leche;
        this.cafe = cafe;
        this.salaLeche = salaLeche;
        this.salaCafe = salaCafe;
        this.papelera = papelera;
    }

    @Override
    public void run() {
        try {
            
            SecureRandom rnd = new SecureRandom();

            int id = (int) Thread.currentThread().getId();

            System.out.println("MANCHADO (" + id + ") --> SALUDA");
            
            cv.encolacafe(id, 'M', leche.availablePermits(), cafe.availablePermits());//Thread.sleep(5000); /////////////////
            
            salaCafe.acquire();
            
            cv.fincolacafe(id, 'M', leche.availablePermits(), cafe.availablePermits());
            cv.ensalacafe(id, 'M', leche.availablePermits(), cafe.availablePermits());

            System.out.println("MANCHADO (" + id + ") --> Sala Café");//Thread.sleep(5000); /////////////////
            
            cafe.acquire();
            salaCafe.release();
            
            cv.finsalacafe(id, 'M', leche.availablePermits(), cafe.availablePermits());
            cv.encolaleche(id, 'M', leche.availablePermits(), cafe.availablePermits());//Thread.sleep(5000); /////////////////
            
            salaLeche.acquire();
            
            cv.fincolaleche(id, 'M', leche.availablePermits(), cafe.availablePermits());
            cv.ensalaleche(id, 'M', leche.availablePermits(), cafe.availablePermits());

            System.out.println("MANCHADO (" + id + ") --> Sala Leche");//Thread.sleep(5000); /////////////////
            
            leche.acquire(2);
            salaLeche.release();
            
            cv.finsalaleche(id, 'M', leche.availablePermits(), cafe.availablePermits());
            cv.ensalon(id, 'M', leche.availablePermits(), cafe.availablePermits());

            System.out.println("MANCHADO (" + id + ") --> Tomando café");

            Thread.sleep((rnd.nextInt(3) + 1) * 1000);
            
            cv.finsalon(id, 'M', leche.availablePermits(), cafe.availablePermits());
            cv.encolapapelera(id, 'M', leche.availablePermits(), cafe.availablePermits());//Thread.sleep(5000); /////////////////
            
            papelera.acquire();
            
            cv.fincolapapelera(id, 'M', leche.availablePermits(), cafe.availablePermits());

            System.out.println("MANCHADO (" + id + ") --> Tirando taza");
            
            cv.enpapelera(id, 'M', leche.availablePermits(), cafe.availablePermits());
            
            Thread.sleep(1000);//Thread.sleep(5000); /////////////////
            
            papelera.release();
            
            cv.finpapelera(id, 'M', leche.availablePermits(), cafe.availablePermits());

            System.out.println("MANCHADO (" + id + ") --> FIN");

        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
