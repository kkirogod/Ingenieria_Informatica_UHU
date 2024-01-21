package p2_pcd;

public class UsaPila {

    public static void main(String[] args) {

        PilaLenta p = new PilaLenta(20);

        Productor hilo1 = new Productor(p);
        Productor hilo2 = new Productor(p);

        Thread hilo3 = new Thread(new Consumidor(p));
        Thread hilo4 = new Thread(new Consumidor(p));

        try {
            hilo1.start();
            hilo2.start();

            hilo1.join();
            hilo2.join();

            p.mostrarPila();

            hilo3.start();
            hilo4.start();

            hilo3.join();
            hilo4.join();

            p.mostrarPila();

        } catch (InterruptedException ex) {
            System.out.println("Capturada execpcion de join " + ex.getMessage());
        }
    }
}
