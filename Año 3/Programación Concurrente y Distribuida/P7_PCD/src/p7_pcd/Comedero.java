package p7_pcd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Comedero {

    private int numGatos;
    private int numPerros;
    private int esperaGatos;
    private int esperaPerros;
    private final Lock mutex = new ReentrantLock();
    private final Condition colaPerros = mutex.newCondition();
    private final Condition colaGatos = mutex.newCondition();

    public Comedero() {
        numGatos = 0;
        numPerros = 0;
        esperaGatos = 0;
        esperaPerros = 0;
    }

    public void entraPerro() throws InterruptedException {
        mutex.lock();

        try {
            
            esperaPerros++;
            
            if ((numGatos + numPerros == 4) || numGatos == 3 || (numGatos == 1 && numPerros == 2)) {
                colaPerros.await();
            }
            
            esperaPerros--;
            numPerros++;

        } finally {
            mutex.unlock();
        }
    }

    public void salePerro() {
        mutex.lock();

        try {

            numPerros--;

            if (esperaPerros > 0) {
                colaPerros.signal();
            } else if (numPerros < 3 && !(numGatos == 2 && numPerros == 1)) {
                colaGatos.signal();
            }

        } finally {
            mutex.unlock();
        }
    }

    public void entraGato() throws InterruptedException {
        mutex.lock();

        try {

            esperaGatos++;
            
            if ((numGatos + numPerros == 4) || numPerros == 3 || (numPerros == 1 && numGatos == 2)) {
                colaGatos.await();
            }

            esperaGatos--;
            numGatos++;

        } finally {
            mutex.unlock();
        }
    }

    public void saleGato() {
        mutex.lock();

        try {

            numGatos--;

            if (esperaGatos > 0) {
                colaGatos.signal();
            } else if (numGatos < 3 && !(numPerros == 2 && numGatos == 1)) {
                colaPerros.signal();
            }

        } finally {
            mutex.unlock();
        }
    }
}
