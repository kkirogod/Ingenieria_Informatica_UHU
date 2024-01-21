package p6_pcd;

import java.security.SecureRandom;
import java.util.concurrent.Semaphore;

public class Cortado extends Thread {
    
    CanvasCongreso cv;
    Semaphore leche;
    Semaphore cafe;
    Semaphore salaLeche;
    Semaphore salaCafe;
    Semaphore papelera;

    public Cortado(CanvasCongreso cv, Semaphore leche, Semaphore cafe, Semaphore salaLeche, Semaphore salaCafe, Semaphore papelera) {
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

            System.out.println("CORTADO (" + id + ") --> SALUDA");
            
            cv.encolaleche(id, 'C', leche.availablePermits(), cafe.availablePermits());//Thread.sleep(5000); /////////////////
            
            salaLeche.acquire();
            
            cv.fincolaleche(id, 'C', leche.availablePermits(), cafe.availablePermits());
            cv.ensalaleche(id, 'C', leche.availablePermits(), cafe.availablePermits());
            
            System.out.println("CORTADO (" + id + ") --> Sala Leche");//Thread.sleep(5000); /////////////////
            
            leche.acquire();
            salaLeche.release();
            
            cv.finsalaleche(id, 'C', leche.availablePermits(), cafe.availablePermits());
            cv.encolacafe(id, 'C', leche.availablePermits(), cafe.availablePermits());//Thread.sleep(5000); /////////////////
            
            salaCafe.acquire();
            
            cv.fincolacafe(id, 'C', leche.availablePermits(), cafe.availablePermits());
            cv.ensalacafe(id, 'C', leche.availablePermits(), cafe.availablePermits());

            System.out.println("CORTADO (" + id + ") --> Sala Café");//Thread.sleep(5000); /////////////////
            
            cafe.acquire(2);
            salaCafe.release();

            System.out.println("CORTADO (" + id + ") --> Tomando café");

            cv.finsalacafe(id, 'C', leche.availablePermits(), cafe.availablePermits());
            cv.ensalon(id, 'C', leche.availablePermits(), cafe.availablePermits());

            Thread.sleep((rnd.nextInt(3) + 1) * 1000);
            
            cv.finsalon(id, 'C', leche.availablePermits(), cafe.availablePermits());
            cv.encolapapelera(id, 'C', leche.availablePermits(), cafe.availablePermits());//Thread.sleep(5000); /////////////////
            
            papelera.acquire();
            
            cv.fincolapapelera(id, 'C', leche.availablePermits(), cafe.availablePermits());

            System.out.println("CORTADO (" + id + ") --> Tirando taza");
            
            cv.enpapelera(id, 'C', leche.availablePermits(), cafe.availablePermits());
            
            Thread.sleep(1000);//Thread.sleep(5000); /////////////////
            
            papelera.release();
            
            cv.finpapelera(id, 'C', leche.availablePermits(), cafe.availablePermits());

            System.out.println("CORTADO (" + id + ") --> FIN");

        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
