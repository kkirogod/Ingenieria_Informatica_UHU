package p10_pcd;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannel;

public class Controlador extends Thread {

    private int numGatos;
    private int numPerros;
    private Any2OneChannel entraperro, saleperro, entragato, salegato;
    private One2OneChannel[] permiso;
    private final CanvasComedero cv;
    
    public Controlador(CanvasComedero cv, Any2OneChannel entraperro, Any2OneChannel saleperro, Any2OneChannel entragato, Any2OneChannel salegato, One2OneChannel[] permiso) {
        numGatos = 0;
        numPerros = 0;
        this.entraperro = entraperro;
        this.saleperro = saleperro;
        this.entragato = entragato;
        this.salegato = salegato;
        this.permiso = permiso;
        this.cv = cv;
    }

    @Override
    public void run() {

        int id;
        
        Guard[] guardas_or = new Guard[4];
        guardas_or[0] = entraperro.in();
        guardas_or[1] = saleperro.in();
        guardas_or[2] = entragato.in();
        guardas_or[3] = salegato.in();

        boolean[] preCondition = new boolean[guardas_or.length];

        Alternative selector = new Alternative(guardas_or);

        while (true) {

            preCondition[0] = !((numGatos + numPerros == 4) || numGatos == 3 || (numGatos == 1 && numPerros == 2));
            preCondition[1] = true;
            preCondition[2] = !((numGatos + numPerros == 4) || numPerros == 3 || (numPerros == 1 && numGatos == 2));
            preCondition[3] = true;
            
            int index = selector.select(preCondition);
            
            switch (index) {
                case 0:
                    //System.out.println("Entra perro");
                    id = (int) entraperro.in().read();
                    numPerros++;
                    permiso[id].out().write(id);
                    break;
                case 1:
                    //System.out.println("Sale perro");
                    id = (int) saleperro.in().read();
                    numPerros--;
                    cv.fueraComedero(id, 'p');
                    break;
                case 2:
                    //System.out.println("Entra gato");
                    id = (int) entragato.in().read();
                    numGatos++;
                    permiso[id].out().write(id);
                    break;
                case 3:
                    //System.out.println("Sale gato");
                    id = (int) salegato.in().read();
                    cv.fueraComedero(id, 'g');
                    numGatos--;
                    break;
                default:
                    System.out.println("ERROR en el SELECT");
            }
        }
    }
}
