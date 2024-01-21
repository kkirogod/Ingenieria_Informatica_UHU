package p9_pcd_servidor;

import IRemoto.ICentro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Centro extends UnicastRemoteObject implements ICentro {

    private final Lock mutex = new ReentrantLock();
    private final Condition colaEfectivo = mutex.newCondition();
    private final Condition colaTarjeta = mutex.newCondition();
    private boolean pagandoEfectivo;
    private int clientesPagando;
    private int esperaEfectivo;
    private CanvasCentro cv;

    public Centro(CanvasCentro cv) throws RemoteException {
        super();
        esperaEfectivo = 0;
        clientesPagando = 0;
        pagandoEfectivo = false;
        this.cv = cv;
    }

    @Override
    public void entraEfectivo(int id) throws RemoteException, InterruptedException {
        mutex.lock();

        try {

            cv.encolaEfectivo(id);

            esperaEfectivo++;

            if (clientesPagando == 4 || pagandoEfectivo) {
                colaEfectivo.await();
            }

            esperaEfectivo--;

            cv.desencolaEfectivo(id);

            pagandoEfectivo = true;
            clientesPagando++;

            cv.enCaja(id, 'E');

        } finally {
            mutex.unlock();
        }
    }

    @Override
    public void saleEfectivo(int id) throws RemoteException {
        mutex.lock();

        try {

            pagandoEfectivo = false;
            clientesPagando--;

            cv.fueraCaja(id, 'E');

            if (esperaEfectivo > 0) {
                colaEfectivo.signal();
            } else {
                colaTarjeta.signal();
            }

        } finally {
            mutex.unlock();
        }
    }

    @Override
    public void entraTarjeta(int id) throws RemoteException, InterruptedException {
        mutex.lock();

        try {
            cv.encolaTarjeta(id);

            if (clientesPagando == 4 || (esperaEfectivo > 0 && !pagandoEfectivo && clientesPagando == 3)) {
                colaTarjeta.await();
            }

            cv.desencolaTarjeta(id);

            clientesPagando++;

            cv.enCaja(id, 'T');

        } finally {
            mutex.unlock();
        }
    }

    @Override
    public void saleTarjeta(int id) throws RemoteException {
        mutex.lock();

        try {

            clientesPagando--;
            
            cv.fueraCaja(id, 'T');

            if (esperaEfectivo > 0 && !pagandoEfectivo) {
                colaEfectivo.signal();
            } else {
                colaTarjeta.signal();
            }

        } finally {
            mutex.unlock();
        }
    }
}
