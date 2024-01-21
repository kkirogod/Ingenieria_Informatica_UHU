package p8_pcd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Centro {

    private final ReentrantLock mutex = new ReentrantLock();
    private final Condition colaEfectivo = mutex.newCondition();
    private final Condition colaTarjeta = mutex.newCondition();
    private boolean pagandoEfectivo;
    private int clientesPagando;
    private int esperaEfectivo;

    public Centro() {
        esperaEfectivo = 0;
        clientesPagando = 0;
        pagandoEfectivo = false;
    }

    public void entraEfectivo() throws InterruptedException {
        mutex.lock();

        try {
            
            esperaEfectivo++;

            if (clientesPagando == 4 || pagandoEfectivo) {
                colaEfectivo.await();
            }

            esperaEfectivo--;
            pagandoEfectivo = true;
            clientesPagando++;

        } finally {
            mutex.unlock();
        }
    }

    public void saleEfectivo() {
        mutex.lock();

        try {

            pagandoEfectivo = false;
            clientesPagando--;

            if (esperaEfectivo > 0) {
                colaEfectivo.signal();
            } else {
                colaTarjeta.signal();
            }

        } finally {
            mutex.unlock();
        }
    }

    public void entraTarjeta() throws InterruptedException {
        mutex.lock();

        try {

            if (clientesPagando == 4 || (esperaEfectivo > 0 && !pagandoEfectivo && clientesPagando == 3)) {
                colaTarjeta.await();
            }

            clientesPagando++;

        } finally {
            mutex.unlock();
        }
    }

    public void saleTarjeta() {
        mutex.lock();

        try {

            clientesPagando--;

            colaTarjeta.signal();

        } finally {
            mutex.unlock();
        }
    }
}
