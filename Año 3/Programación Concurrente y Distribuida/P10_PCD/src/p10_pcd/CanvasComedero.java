package p10_pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;

class Animal {

    int id;
    char tipo;

    public Animal(int id, char tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public void setid(int id) {
        this.id = id;

    }

    public void settipo(char tipo) {
        this.tipo = tipo;

    }

    public int getid() {
        return id;
    }

    public char gettipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Animal a = (Animal) obj;

        return (a.getid() == this.getid()) && (a.gettipo() == this.gettipo());
    }
}

public class CanvasComedero extends Canvas {

    private final Image imgPerro, imgGato, imgComedero;
    private ArrayList<Integer> colaPerros = new ArrayList();
    private ArrayList<Integer> colaGatos = new ArrayList();
    private Animal[] comedero = new Animal[4];

    public CanvasComedero(int ancho, int alto) throws InterruptedException {

        this.setSize(ancho, alto);
        this.setBackground(Color.white);

        imgPerro = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/imgPerro.png"));
        imgGato = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/imgGato.png"));
        imgComedero = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/imgComedero.png"));

        MediaTracker dibu = new MediaTracker(this);

        dibu.addImage(imgPerro, 0);
        dibu.waitForID(0);
        dibu.addImage(imgGato, 1);
        dibu.waitForID(1);
        dibu.addImage(imgComedero, 2);
        dibu.waitForID(2);

        for (int i = 0; i < 4; i++) {
            comedero[i] = null;
        }
    }

    public synchronized void encolaPerro(int id) {
        colaPerros.add(id);
        repaint();
    }

    public synchronized void desencolaPerro(int id) {
        colaPerros.remove((Object) id);
        repaint();
    }

    public synchronized void encolaGato(int id) {
        colaGatos.add(id);
        repaint();
    }

    public synchronized void desencolaGato(int id) {
        colaGatos.remove((Object) id);
        repaint();
    }

    public synchronized void enComedero(int id, char tipo) {

        for (int i = 0; i < 4; i++) {
            if (comedero[i] == null) {
                comedero[i] = new Animal(id, tipo);
                break;
            }
        }
        repaint();
    }

    public synchronized void fueraComedero(int id, char tipo) {

        Animal a = new Animal(id, tipo);

        for (int i = 0; i < 4; i++) {
            if (comedero[i] != null) {
                if (comedero[i].equals(a)) {
                    comedero[i] = null;
                    break;
                }
            }
        }
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
    public synchronized void paint(Graphics g) {

        Image img = createImage(getWidth(), getHeight());
        Graphics og = img.getGraphics();
        Font f1 = new Font("Arial", Font.BOLD | Font.ITALIC, 20);

        og.setFont(f1);

        og.setColor(Color.cyan);
        og.fillRect(100, 40, 600, 180);
        og.setColor(Color.red);

        for (int i = 0; i < colaPerros.size(); i++) {
            og.drawString(" " + colaPerros.get(i), 110 + 150 * i, 60);
            og.drawImage(imgPerro, 100 + 150 * i, 60, null);
        }

        og.setColor(Color.pink);
        og.fillRect(100, 250, 600, 180);
        og.setColor(Color.blue);

        for (int i = 0; i < colaGatos.size(); i++) {
            og.drawString(" " + colaGatos.get(i), 110 + 150 * i, 260);
            og.drawImage(imgGato, 100 + 150 * i, 260, null);
        }

        for (int i = 0; i < 4; i++) {
            og.drawImage(imgComedero, 100 + 150 * i, 630, null);
        }

        for (int i = 0; i < 4; i++) {

            if (comedero[i] != null) {

                if (comedero[i].gettipo() == 'p') {
                    og.setColor(Color.red);
                    og.drawImage(imgPerro, 100 + 150 * i, 480, null);

                } else {
                    og.setColor(Color.blue);
                    og.drawImage(imgGato, 100 + 150 * i, 480, null);
                }

                og.drawString("" + comedero[i].getid(), 110 + 150 * i, 480);
            }
        }

        g.drawImage(img, 0, 0, null);
    }

    public void avisa(String mensaje) {
        repaint();
    }
}
