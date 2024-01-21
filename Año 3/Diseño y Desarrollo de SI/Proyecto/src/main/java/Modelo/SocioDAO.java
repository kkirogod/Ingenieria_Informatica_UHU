package Modelo;

import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;

public class SocioDAO {

    // devuelve una lista de todos los socios de la BD
    static public ArrayList<Socio> listaSociosHQL(Session sesion) {

        Query consulta = sesion.createQuery("SELECT s FROM Socio s", Socio.class);
        ArrayList<Socio> socios = (ArrayList<Socio>) consulta.getResultList();

        return socios;
    }

    // devuelve una lista de todos los socios de la BD
    static public ArrayList<Socio> listaSociosSQL(Session sesion) {

        Query consulta = sesion.createNativeQuery("SELECT * FROM SOCIO S", Socio.class);
        ArrayList<Socio> socios = (ArrayList<Socio>) consulta.getResultList();

        return socios;
    }

    // devuelve una lista de todos los socios de la BD
    static public ArrayList<Socio> listaSociosNamedQuery(Session sesion) {

        Query consulta = sesion.createNamedQuery("Socio.findAll", Socio.class);
        ArrayList<Socio> socios = (ArrayList<Socio>) consulta.getResultList();

        return socios;
    }

    
    // devuelve una lista de todos los nombres y telefonos de los socios de la BD
    static public ArrayList<Object[]> listaNombreTlfSocios(Session sesion) {

        Query consulta = sesion.createQuery("SELECT s.nombre, s.telefono FROM Socio s");
        ArrayList<Object[]> socios = (ArrayList<Object[]>) consulta.getResultList();

        return socios;
    }
    
    
    // devuelve una lista de los nombres y categoría de los socios de la BD cuya categoría se pasa por parámetro
    static public ArrayList<Object[]> listaNombreCategoriaSocios(Session sesion, char cat) {

        Query consulta = sesion.createQuery("SELECT s.nombre, s.categoria FROM Socio s WHERE s.categoria = :cat").setParameter("cat", cat);
        ArrayList<Object[]> socios = (ArrayList<Object[]>) consulta.getResultList();

        return socios;
    }
    
    
    // devuelve una lista de los socios de la BD cuya categoría se pasa por parámetro
    static public ArrayList<Socio> listaSociosPorCategoria(Session sesion, char cat) {

        Query consulta = sesion.createQuery("SELECT s FROM Socio s WHERE s.categoria = :cat").setParameter("cat", cat);
        ArrayList<Socio> socios = (ArrayList<Socio>) consulta.getResultList();

        return socios;
    }

    
    // devuelve una lista de los nombres de los socios inscritos en la actividad cuya id se pasa por parámetro
    static public ArrayList<String> listaSociosInscritos(Session sesion, String id) {
        
        Query consulta = sesion.createNativeQuery("SELECT s.nombre FROM SOCIO s INNER JOIN REALIZA r ON s.numeroSocio = r.numeroSocio INNER JOIN ACTIVIDAD a ON r.idActividad = a.idActividad WHERE a.idActividad = :act").setParameter("act", id);
        ArrayList<String> nombreSocios = (ArrayList<String>) consulta.getResultList();
        
        return nombreSocios;
    }
    
    // da de alta al socio pasado por parametro
    static public void altaSocio(Session sesion, Socio s) {
        sesion.saveOrUpdate(s);
    }
    
    // da de baja al socio pasado por parametro
    static public void bajaSocio(Session sesion, Socio s) {
        sesion.delete(s);
    }
    
    // comprueba si el socio cuyo número se pasa por parámetro existe en la BD
    static public boolean existeSocio(Session sesion, String numeroSocio) {
        return sesion.get(Socio.class, numeroSocio) != null;
    }
    
    /*
    // devuelve el socio cuyo número se pasa por parámetro, y si no existe: NULL
    static public Socio obtenerSocio(Session sesion, String numeroSocio) {
        return sesion.get(Socio.class, numeroSocio);
    }
    */
    
    // devuelve el número del último socio de la BD
    static public ArrayList<String> ultimoSocio(Session sesion){
        Query consulta = sesion.createNativeQuery("SELECT MAX(numeroSocio) AS max_valor FROM SOCIO");
        ArrayList<String> codSocio = (ArrayList<String>) consulta.getResultList();
        return codSocio;
    }
}
