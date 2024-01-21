package p4_pcd;

public class PilaLenta implements IPila {

    private int cima;
    private int capacidad;
    private int numelementos;
    private Object datos[];
    private CanvasPila canvas;

    public PilaLenta(int capacidad, CanvasPila canvas) {
        cima = -1;
        numelementos = 0;
        this.capacidad = capacidad;
        datos = new Object[capacidad];
        this.canvas = canvas;

        canvas.avisa("PILA VACÍA");
    }

    @Override
    public int GetNum() {
        return numelementos;
    }

    @Override
    public synchronized void Apila(Object elemento) throws InterruptedException {

        int contador = 0;
        boolean apilado = false;

        while (contador < 3 && !apilado) {

            if (!pilallena()) {

                Thread.sleep(500);

                cima++;
                datos[cima] = elemento;
                numelementos++;

                canvas.representa(datos, cima, numelementos);

                if (pilallena()) {
                    canvas.avisa("PILA LLENA");
                } else {
                    canvas.avisa("");
                }

                System.out.println("Apilando: " + datos[cima]);

                notifyAll();

                apilado = true;

            } else {

                canvas.avisa("PILA LLENA");

                System.out.println("Esperando para apilar... (Contador = " + (contador + 1) + ")");

                wait();
                contador++;
            }
        }

        if (contador == 3) {
            throw new InterruptedException("No se ha podido apilar");
        }
    }

    @Override
    public synchronized Object Desapila() throws InterruptedException {

        int contador = 0;

        while (contador < 3) {

            if (!pilavacia()) {

                Thread.sleep(500);

                cima--;
                numelementos--;

                canvas.representa(datos, cima, numelementos);
                canvas.avisa("");

                System.out.println("Desapilando: " + datos[cima + 1]);

                notifyAll();

                return datos[cima + 1];

            } else {

                canvas.avisa("PILA VACÍA");

                System.out.println("Esperando para desapilar... (Contador = " + contador + ")");

                wait();
                contador++;

                if (contador == 3) {
                    throw new InterruptedException("No se ha podido desapilar");
                }
            }
        }
        return null;
    }

    @Override
    public Object Primero() {

        if (cima != -1) {
            return datos[cima];
        } else {
            canvas.avisa("PILA VACÍA");

            return null;
        }
    }

    private boolean pilavacia() {
        return numelementos == 0;
    }

    private boolean pilallena() {
        return numelementos == capacidad;
    }

    public void mostrarPila() {

        System.out.println("\n##### PILA: #####");

        for (int i = 0; i < numelementos; i++) {
            System.out.println(datos[i]);
        }

        System.out.println("Número de elementos: " + numelementos);
        System.out.println("#################");
    }
}
