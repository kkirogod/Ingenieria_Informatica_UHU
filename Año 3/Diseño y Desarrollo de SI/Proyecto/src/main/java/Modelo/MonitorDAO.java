package Modelo;

import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;

public class MonitorDAO {

    // devuleve la lista de monitores responsables de una actividad cuyo id se pasa por parametro
    static public ArrayList<Object[]> listaResponsables(Session sesion, String id) {

        Query consulta = sesion.createNativeQuery("SELECT m.codMonitor, m.nombre, m.dni, m.telefono, m.correo, m.fechaEntrada, m.nick FROM MONITOR m INNER JOIN ACTIVIDAD a ON m.codMonitor = a.monitorResponsable WHERE a.idActividad = :id").setParameter("id", id); 
        ArrayList<Object[]> monitores = (ArrayList<Object[]>) consulta.getResultList();

        return monitores;
    }
    
    // devuelve la lista de todos los monitores de la BD 
    static public ArrayList<Monitor> listaMonitoresSQL(Session sesion) {

        Query consulta = sesion.createNativeQuery("SELECT * FROM MONITOR M", Monitor.class);
        ArrayList<Monitor> monitores = (ArrayList<Monitor>) consulta.getResultList();

        return monitores;
    }
    
    // devuelve una lista de todos los monitores de la BD
    static public ArrayList<Monitor> listaMonitoresNamedQuery(Session sesion) {

        Query consulta = sesion.createNamedQuery("Monitor.findAll", Monitor.class);
        ArrayList<Monitor> monitores = (ArrayList<Monitor>) consulta.getResultList();

        return monitores;
    }
    
    // devuelve el codigo del ultimo monitor de la BD
    static public ArrayList<String> ultimoMonitor(Session sesion){
        Query consulta = sesion.createNativeQuery("SELECT MAX(codMonitor) AS max_valor FROM MONITOR");
        ArrayList<String> codMonitor = (ArrayList<String>) consulta.getResultList();
        return codMonitor;
    }

    // da de alta al monitor pasado por parametro
    public static void altaMonitor(Session sesion, Monitor m) {
        sesion.saveOrUpdate(m);
    }

    // da de baja al monitor pasado por parametro
    public static void bajaMonitor(Session sesion, Monitor m) {
        sesion.delete(m);
    }
}
