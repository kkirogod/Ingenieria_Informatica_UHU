package p8_pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;

class Cliente {

    int id;
    char tipo;

    public Cliente(int id, char tipo) {
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

        final Cliente c = (Cliente) obj;

        return (c.getid() == this.getid()) && (c.gettipo() == this.gettipo());
    }
}

public class CanvasCentro extends Canvas {

    private final Image imgEfectivo, imgTarjeta, imgCaja;
    private ArrayList<Integer> colaTarjeta = new ArrayList();
    private ArrayList<Integer> colaEfectivo = new ArrayList();
    private Cliente[] caja = new Cliente[4];

    public CanvasCentro(int ancho, int alto) throws InterruptedException {

        this.setSize(ancho, alto);
        //this.setBackground(Color.white);

        imgEfectivo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/imgEfectivo.jpg"));
        imgTarjeta = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/imgTarjeta.jpg"));
        imgCaja = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/imgCaja.jpg"));

        MediaTracker dibu = new MediaTracker(this);

        dibu.addImage(imgEfectivo, 0);
        dibu.waitForID(0);
        dibu.addImage(imgTarjeta, 1);
        dibu.waitForID(1);
        dibu.addImage(imgCaja, 2);
        dibu.waitForID(2);

        for (int i = 0; i < 4; i++) {
            caja[i] = null;
        }
    }

    public synchronized void encolaEfectivo(int id) {
        colaEfectivo.add(id);
        repaint();
    }

    public synchronized void desencolaEfectivo(int id) {
        colaEfectivo.remove((Object) id);
        repaint();
    }

    public synchronized void encolaTarjeta(int id) {
        colaTarjeta.add(id);
        repaint();
    }

    public synchronized void desencolaTarjeta(int id) {
        colaTarjeta.remove((Object) id);
        repaint();
    }

    public synchronized void enCaja(int id, char tipo) {

        for (int i = 0; i < 4; i++) {
            if (caja[i] == null) {
                caja[i] = new Cliente(id, tipo);
                break;
            }
        }
        repaint();
    }

    public synchronized void fueraCaja(int id, char tipo) {

        Cliente a = new Cliente(id, tipo);

        for (int i = 0; i < 4; i++) {
            if (caja[i] != null) {
                if (caja[i].equals(a)) {
                    caja[i] = null;
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
        og.fillRect(100, 40, 1500, 200);
        og.setColor(Color.red);

        for (int i = 0; i < colaEfectivo.size(); i++) {
            og.drawImage(imgEfectivo, 100 + 150 * i, 60, null);
            og.drawString(" " + colaEfectivo.get(i), 180 + 150 * i, 80);
        }

        og.setColor(Color.pink);
        og.fillRect(100, 250, 1500, 200);
        og.setColor(Color.blue);

        for (int i = 0; i < colaTarjeta.size(); i++) {
            og.drawImage(imgTarjeta, 100 + 150 * i, 260, null);
            og.drawString(" " + colaTarjeta.get(i), 180 + 150 * i, 280);
        }

        for (int i = 0; i < 4; i++) {
            og.drawImage(imgCaja, 100 + 250 * i, 550, null);
        }

        for (int i = 0; i < 4; i++) {

            if (caja[i] != null) {

                if (caja[i].gettipo() == 'E') {
                    og.setColor(Color.red);
                    og.drawImage(imgEfectivo, 200 + 250 * i, 600, null);

                } else {
                    og.setColor(Color.blue);
                    og.drawImage(imgTarjeta, 200 + 250 * i, 600, null);
                }

                og.drawString("" + caja[i].getid(), 280 + 250 * i, 620);
            }
        }

        g.drawImage(img, 0, 0, null);
    }
}
