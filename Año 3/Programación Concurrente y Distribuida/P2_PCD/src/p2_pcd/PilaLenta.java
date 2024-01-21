package p2_pcd;

public class PilaLenta implements IPila{
    
    private int cima;
    private int capacidad;
    private int numelementos;
    private Object datos[];
    
    public PilaLenta(int capacidad) {
        cima = -1;
        numelementos = 0;
        this.capacidad = capacidad;
        datos = new Object[capacidad];
    }
    
    @Override
    public int GetNum() {
        return numelementos;
    }
    
    @Override
    public synchronized void Apila(Object elemento) throws Exception{
        if(numelementos < capacidad) {
            Thread.sleep(100);
            cima++;
            datos[cima]=elemento;
            numelementos++;
        }
        else
            throw new Exception("ERROR: Pila llena");
    }
    
    @Override
    public synchronized Object Desapila() throws Exception{
        if(cima > -1) {
            Thread.sleep(100);
            cima--;
            numelementos--;
            return datos[cima+1];
        }
        else
            throw new Exception("ERROR: Pila vacía");
    }
    
    @Override
    public Object Primero() throws Exception{
        if(cima != -1)
            return datos[cima];
        else
            throw new Exception("ERROR: Pila vacía");
    }
    
    private boolean pilavacia() {
        if(numelementos == 0)
            return true;
        else
            return false;
    }
    
    private boolean pilallena() {
        if(numelementos == capacidad)
            return true;
        else
            return false;
    }
    
    public void mostrarPila() {
        
        System.out.println("#################");
        
        for(int i=0; i<numelementos; i++) {
            System.out.println(datos[i]);
        }
        
        System.out.println("Numero de elementos: " + numelementos);
        System.out.println("#################");
    }
}
