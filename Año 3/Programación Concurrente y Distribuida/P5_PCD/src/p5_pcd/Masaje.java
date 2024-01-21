package p5_pcd;

import java.security.SecureRandom;

public class Masaje extends Thread {

    CanvasFisio cv;
    Centro c;

    public Masaje(CanvasFisio cv, Centro c) {
        this.cv = cv;
        this.c = c;
    }

    @Override
    public void run() {

        SecureRandom r = new SecureRandom();
        int id = (int) Thread.currentThread().getId();

        try {

            System.out.println("\nSoy el cliente(M) " + id + " | " + Thread.currentThread().getName() + "\n");

            cv.encolaclienteM(id);

            char empleado = c.entraMasaje();

            cv.desencolaclienteM(id);

            if (empleado == 'm') {
                cv.enMasajista(id, 'm');
                System.out.println("        Cliente(M) " + id + " | " + Thread.currentThread().getName() + "             --> MASAJISTA");
            } else {
                cv.enFisio(id, 'm');
                System.out.println("                        Cliente(M) " + id + " | " + Thread.currentThread().getName() + "             --> FISIO");
            }

            Thread.sleep((r.nextInt(3) + 2) * 1000);
            //Thread.sleep(4000);

            c.saleMasaje(empleado);

            if (empleado == 'm') {
                cv.fueraMasajista(id, 'm');
            } else {
                cv.fueraFisio(id, 'm');
            }

            System.out.println("                                        Cliente(M) " + id + " | " + Thread.currentThread().getName() + "             --> VESTUARIO");

            Thread.sleep(2000);
            //Thread.sleep(4000);

            c.termina();
            cv.fueraVestuario();

            System.out.println("TERMINA --> Cliente(M) " + id  + " | " + Thread.currentThread().getName());

        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
