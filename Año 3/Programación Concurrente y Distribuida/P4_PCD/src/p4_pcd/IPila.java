package p4_pcd;

public interface IPila {

    public int GetNum();

    public void Apila(Object elemento) throws Exception;

    public Object Desapila() throws Exception;

    public Object Primero() throws Exception;
}
