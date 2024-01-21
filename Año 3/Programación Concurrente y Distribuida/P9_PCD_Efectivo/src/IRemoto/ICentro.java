
package IRemoto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICentro extends Remote {

    void entraEfectivo(int id) throws RemoteException, InterruptedException;

    void entraTarjeta(int id) throws RemoteException, InterruptedException;

    void saleEfectivo(int id) throws RemoteException;

    void saleTarjeta(int id) throws RemoteException;
    
}
