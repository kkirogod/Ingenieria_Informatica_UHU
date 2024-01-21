package p5_pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;

class cliente {

    int id;
    char tipo;

    public cliente(int id, char tipo) {
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
}

public class CanvasFisio extends Canvas {

    private String mensaje = "";
    private Image fisioimg, masajistaimg, clienteMimg, clienteFimg, vestuarioimg;
    private ArrayList<Integer> colaclientesM = new ArrayList();
    private ArrayList<Integer> colaclientesF = new ArrayList();
    private cliente rehab, masaje, vestuario;

    public CanvasFisio(int ancho, int alto) throws InterruptedException {

        this.setSize(ancho, alto);
        this.setBackground(Color.white);

        fisioimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/fisioimg.png"));
        masajistaimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/masajistaimg.png"));
        clienteMimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/clienteMimg.png"));
        clienteFimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/clienteFimg.png"));
        vestuarioimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/vestuarioimg.png"));

        rehab = new cliente(-1, 'n');
        masaje = new cliente(-1, 'n');
        vestuario = new cliente(-1, 'n');

        MediaTracker dibu = new MediaTracker(this);

        dibu.addImage(fisioimg, 0);
        dibu.waitForID(0);
        dibu.addImage(masajistaimg, 1);
        dibu.waitForID(1);
        dibu.addImage(clienteMimg, 2);
        dibu.waitForID(2);
        dibu.addImage(clienteFimg, 3);
        dibu.waitForID(3);
        dibu.addImage(vestuarioimg, 4);
        dibu.waitForID(4);
    }

    public synchronized void encolaclienteM(int id) {
        colaclientesM.add(id);
        repaint();
    }

    public synchronized void desencolaclienteM(int id) {
        colaclientesM.remove((Object) id);
        repaint();
    }

    public synchronized void encolaclienteF(int id) {
        colaclientesF.add(id);
        repaint();
    }

    public synchronized void desencolaclienteF(int id) {
        colaclientesF.remove((Object) id);
        repaint();
    }

    public synchronized void enFisio(int id, char tipo) {
        rehab.setid(id);
        rehab.settipo(tipo);
        repaint();
    }

    public synchronized void fueraFisio(int id, char tipo) {
        rehab.setid(-1);
        rehab.settipo('n');
        vestuario.setid(id);
        vestuario.settipo(tipo);
        repaint();
    }

    public synchronized void enMasajista(int id, char tipo) {
        masaje.setid(id);
        masaje.settipo(tipo);
        repaint();
    }

    public synchronized void fueraMasajista(int id, char tipo) {
        masaje.setid(-1);
        masaje.settipo('n');
        vestuario.setid(id);
        vestuario.settipo(tipo);
        repaint();
    }

    public synchronized void fueraVestuario() {
        vestuario.setid(-1);
        vestuario.settipo('n');
        repaint();
    }

    public void representa(Object datos[], int cima, int numelementos) {

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

        Image img = createImage(getWidth(), getHeight());
        Graphics og = img.getGraphics();
        Font f1 = new Font("Arial", Font.BOLD | Font.ITALIC, 20);
        Font f2 = new Font("Arial", Font.BOLD, 20);

        og.setFont(f1);

        og.setColor(Color.cyan);
        og.fillRect(280, 50, 600, 80);
        og.setColor(Color.red);

        for (int i = 0; i < colaclientesM.size(); i++) {
            og.drawString(" " + colaclientesM.get(i), 310 + 80 * i, 80);
            og.drawImage(clienteMimg, 300 + 80 * i, 80, null);
        }

        og.setColor(Color.pink);
        og.fillRect(280, 250, 600, 80);
        og.setColor(Color.blue);

        for (int i = 0; i < colaclientesF.size(); i++) {
            og.drawString(" " + colaclientesF.get(i), 310 + 80 * i, 280);
            og.drawImage(clienteFimg, 300 + 80 * i, 280, null);
        }
        
        og.setFont(f2);
        og.setColor(Color.black);
        og.drawString("MASAJISTA", 60, 20);
        og.drawImage(masajistaimg, 40, 30, null);
        og.drawString("FISIO", 80, 200);
        og.drawImage(fisioimg, 40, 200, null);
        og.drawImage(vestuarioimg, 310, 400, null);
        og.setFont(f1);

        if (rehab.getid() != -1) {
            if (rehab.gettipo() == 'f') {
                og.setColor(Color.blue);
                og.drawImage(clienteFimg, 40, 230, null);

            } else {
                og.setColor(Color.red);
                og.drawImage(clienteMimg, 40, 230, null);
            }

            og.drawString("" + rehab.getid(), 40, 230);
        }

        if (masaje.getid() != -1) {
            og.setColor(Color.red);
            og.drawImage(clienteMimg, 40, 60, null);

            og.drawString("" + masaje.getid(), 40, 60);
        }

        if (vestuario.getid() != -1) {
            if (vestuario.gettipo() == 'f') {
                og.setColor(Color.blue);
                og.drawImage(clienteFimg, 350, 470, null);
            } else {
                og.setColor(Color.red);
                og.drawImage(clienteMimg, 350, 470, null);
            }
            og.drawString("" + vestuario.getid(), 350, 470);
        }

        g.drawImage(img, 0, 0, null);
    }

    public void avisa(String mensaje) {

        this.mensaje = mensaje;

        repaint();
    }
}
