package p4_pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class CanvasPila extends Canvas {

    private int cima;
    private int capacidad;
    private int numelementos;
    private Object datos[];
    private String mensaje = "";

    public CanvasPila(int ancho, int alto, int capacidad) {

        this.setSize(ancho, alto);
        this.setBackground(Color.black);
        this.capacidad = capacidad;
    }

    public void representa(Object datos[], int cima, int numelementos) {

        this.datos = datos;
        this.cima = cima;
        this.numelementos = numelementos;

        repaint();
    }

    /* El update original del canvas, borra el canvas y llama a paint. Si queremos 
     sobreescribir  lo que hay pintado, sobrecargamos update y hacemos que llame 
     paint. Así no borra lo anterior, y no se produce parpadeo
     */
    @Override
    public void update(Graphics g) {

        paint(g);
    }

    @Override
    public void paint(Graphics g) {

        // Se crea un buffer intermedio para que montar la salida completa
        // y luego pintarla de una vez, evitando el parpadeo
        Image img = createImage(getWidth(), getHeight());

        Graphics og1 = img.getGraphics();
        Graphics og2 = img.getGraphics();
        Graphics og3 = img.getGraphics();
        Graphics og4 = img.getGraphics();

        Font f1 = new Font("Arial", Font.BOLD | Font.ITALIC, 30);
        Font f2 = new Font("Arial", Font.CENTER_BASELINE, 15);

        String pila = "";

        for (int i = 0; i < numelementos; i++) {

            if ((int) datos[i] < 10) {

                pila = pila + "0" + datos[i] + " ";

            } else {
                pila = pila + datos[i] + " ";
            }
        }

        og1.setFont(f1);
        og1.setColor(Color.green);

        og2.setFont(f1);
        og2.setColor(Color.red);

        og3.setFont(f1);
        og3.setColor(Color.yellow);
        
        og4.setFont(f2);
        og4.setColor(Color.cyan);
        //og.fillOval(20, 30, 20, 20);
        og1.drawString("PILA:  " + pila, 50, 70);

        if (numelementos > 0) {
            for (int i = 1; i <= numelementos; i++) {
                og4.drawRect(102 + (i * 42), 35, 42, 50);
            }
            og4.drawString("CIMA", 105 + (numelementos * 42), 30);
        }

        og2.drawString("AVISOS: " + mensaje, 50, 145);
        og3.drawString("NÚMERO DE ELEMENTOS: " + numelementos, 50, 220);

        g.drawImage(img, 0, 0, null);
    }

    public void avisa(String mensaje) {

        this.mensaje = mensaje;

        repaint();
    }
}
