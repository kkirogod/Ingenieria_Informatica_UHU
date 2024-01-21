package p8_pcd;

import java.security.SecureRandom;
import java.util.concurrent.Callable;

public class Tarjeta implements Callable<Integer> {

    CanvasCentro cv;
    Centro c;
    int id;

    public Tarjeta(CanvasCentro cv, Centro c, int id) {
        this.cv = cv;
        this.c = c;
        this.id = id;
    }
    
    @Override
    public Integer call() throws Exception {

        SecureRandom rnd = new SecureRandom();

        int espera = rnd.nextInt(2) + 1; //entre 1 y 2 segundos

        cv.encolaTarjeta(id);

        System.out.println("Cliente (T) --> SALUDA (" + id + ")");

        c.entraTarjeta();

        cv.desencolaTarjeta(id);
        cv.enCaja(id, 'T');

        System.out.println("Cliente (T) --> \t\tENTRA (" + id + ")");

        Thread.sleep(espera*1000);

        c.saleTarjeta();

        cv.fueraCaja(id, 'T');

        System.out.println("Cliente (T) --> \t\t\t\tSALE (" + id + ")");

        return espera;
    }
}
