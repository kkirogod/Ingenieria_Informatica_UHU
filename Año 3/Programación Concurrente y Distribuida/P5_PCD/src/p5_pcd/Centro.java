package p5_pcd;

public class Centro {

    private boolean VLibre;
    private boolean MLibre;
    private boolean FLibre;
    private int colaRehab;

    public Centro() {
        VLibre = true;
        MLibre = true;
        FLibre = true;
        colaRehab = 0;
    }

    public synchronized char entraMasaje() throws InterruptedException {

        while (!MLibre && (!FLibre || colaRehab > 0)) {
            wait();
        }

        char empleado;

        if (MLibre) {
            MLibre = false;
            empleado = 'm';
        } else {
            FLibre = false;
            empleado = 'f';
        }
        return empleado;
    }

    public synchronized void saleMasaje(char empleado) throws InterruptedException {
        
        while (!VLibre) {
            wait();
        }
        
        if(empleado == 'm')
            MLibre = true;
        else
            FLibre = true;
        
        VLibre = false;
        
        notifyAll();
    }

    public synchronized void entraRehabilitacion() throws InterruptedException {
        
        colaRehab++;
        
        while (!FLibre) {
            wait();
        }
        
        FLibre = false;
        colaRehab--;
    }

    public synchronized void saleRehabilitacion() throws InterruptedException {
        
        while (!VLibre) {
            wait();
        }
        
        FLibre = true;
        VLibre = false;
        
        notifyAll();
    }

    public synchronized void termina() {
        
        VLibre = true;
        
        notifyAll();
    }
}
