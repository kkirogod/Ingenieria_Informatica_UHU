package p8_pcd;

import java.security.SecureRandom;
import java.util.concurrent.Callable;

public class Efectivo implements Callable<Integer> {

    CanvasCentro cv;
    Centro c;
    int id;

    public Efectivo(CanvasCentro cv, Centro c, int id) {
        this.cv = cv;
        this.c = c;
        this.id = id;
    }

    @Override
    public Integer call() throws Exception {

        SecureRandom rnd = new SecureRandom();

        int espera = rnd.nextInt(2) + 1; //entre 1 y 2 segundos

        cv.encolaEfectivo(id);

        System.out.println("Cliente (E) --> SALUDA (" + id + ")");

        c.entraEfectivo();

        cv.desencolaEfectivo(id);
        cv.enCaja(id, 'E');

        System.out.println("Cliente (E) --> \t\tENTRA (" + id + ")");

        Thread.sleep(espera*1000);

        c.saleEfectivo();

        cv.fueraCaja(id, 'E');

        System.out.println("Cliente (E) --> \t\t\t\tSALE (" + id + ")");

        return espera;
    }
}
