package p1_pcd;

public class Pila implements IPila{
    
    private int cima;
    private int capacidad;
    private int numelementos;
    private Object datos[];
    
    public Pila(int capacidad) {
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
    public void Apila(Object elemento) throws Exception{
        if(numelementos < capacidad) {
            cima++;
            datos[cima]=elemento;
            numelementos++;
        }
        else
            throw new Exception("ERROR: Pila llena");
    }
    
    @Override
    public Object Desapila() throws Exception{
        if(cima != -1) {
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
}
