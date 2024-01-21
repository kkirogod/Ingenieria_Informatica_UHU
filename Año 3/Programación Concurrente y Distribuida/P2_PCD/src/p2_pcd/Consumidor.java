package p2_pcd;

public class Consumidor implements Runnable{

    private volatile PilaLenta lapila;

    public Consumidor(PilaLenta p) {
        lapila = p;
    }

    public void consumir() {
        
        for (int i = 0; i < 10; i++) {

            try {
                lapila.Desapila();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @Override
    public void run() {
        consumir();
    }
}
