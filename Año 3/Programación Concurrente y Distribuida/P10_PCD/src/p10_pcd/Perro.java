package p10_pcd;

import java.security.SecureRandom;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

public class Perro extends Thread {

    private final int id;
    private final Any2OneChannel entrar;
    private final Any2OneChannel salir;
    private final One2OneChannel permiso;
    private final CanvasComedero cv;

    public Perro(int id, Any2OneChannel entrar, Any2OneChannel salir, One2OneChannel permiso, CanvasComedero cv) {
        this.id = id;
        this.entrar = entrar;
        this.salir = salir;
        this.permiso = permiso;
        this.cv = cv;
    }

    @Override
    public void run() {

        SecureRandom r = new SecureRandom();

        try {
            
            System.out.println("PERRO --> SALUDA (" + id + ")");
            
            cv.encolaPerro(id);
            
            entrar.out().write(id);
            permiso.in().read();
            
            cv.desencolaPerro(id);
            cv.enComedero(id, 'p');
            
            System.out.println("PERRO --> \t\tENTRA (" + id + ")");

            Thread.sleep((r.nextInt(3) + 1) * 1000);
            //Thread.sleep(4000);/////////////////////////////
            
            System.out.println("PERRO --> \t\t\t\tSALE (" + id + ")");
            
            salir.out().write(id);

        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
