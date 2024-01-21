package p1_pcd;

import java.util.Random;

public class UsaPila {

    public static void main(String[] args) {

        Pila p = new Pila(10);
        Random r = new Random(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {

            int num = r.nextInt(100);

            if (num % 2 == 0) { //si es par
                try {
                    p.Apila(num);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                try {
                    p.Desapila();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            /*          
            try {
                System.out.println(p.Primero() + " - " + p.GetNum());

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            */
        }
    }
}
