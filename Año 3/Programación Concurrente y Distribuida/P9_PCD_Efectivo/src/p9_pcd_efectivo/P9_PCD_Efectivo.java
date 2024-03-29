package p9_pcd_efectivo;

import IRemoto.ICentro;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.SecureRandom;

public class P9_PCD_Efectivo {

    public static void main(String[] args) {

        try {
            int id = (int) (System.currentTimeMillis()%1000);
            
            SecureRandom rnd = new SecureRandom();
            int espera = rnd.nextInt(4) + 5; //entre 5 y 8 segundos

            ICentro centroRemoto = (ICentro) Naming.lookup("rmi://localhost:2023/centroRemoto");
                  
            Thread.sleep(10000); // espera 10 segundos para que de tiempo a ver el funcionamiento

            System.out.println("Cliente (E) --> SALUDA (" + id + ")");

            centroRemoto.entraEfectivo(id);

            System.out.println("Cliente (E) --> \t\tENTRA (" + id + ")");

            Thread.sleep(espera * 1000);

            centroRemoto.saleEfectivo(id);

            System.out.println("Cliente (E) --> \t\t\t\tSALE (" + id + ")");

        } catch (NotBoundException | MalformedURLException | RemoteException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
