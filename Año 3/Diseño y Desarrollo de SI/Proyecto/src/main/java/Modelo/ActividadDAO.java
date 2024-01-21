
package Modelo;

import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;

public class ActividadDAO {
    
    // comprueba si existe en la BD la actividad cuyo id se pasa por parametro
    static public boolean existeActividad(Session sesion, String id) {
        return sesion.get(Actividad.class, id) != null;
    }
    
    // devuelve la lista de los nombres de las actividades que SI realiza el socio cuyo número se pasa por parámetro
    static public ArrayList<String> listaIdSIActividadesSocio(Session sesion, String numSocio) {
        
        Query consulta = sesion.createNativeQuery("SELECT a.idActividad FROM ACTIVIDAD a INNER JOIN REALIZA r ON a.idActividad = r.idActividad INNER JOIN SOCIO s ON r.numeroSocio = s.numeroSocio WHERE s.numeroSocio = :soc").setParameter("soc", numSocio);
        ArrayList<String> idActividades = (ArrayList<String>) consulta.getResultList();
        
        return idActividades;
    }
    
    // devuelve la lista de los nombres de las actividades que NO realiza el socio cuyo número se pasa por parámetro
    static public ArrayList<String> listaIdNOActividadesSocio(Session sesion, String numSocio) {
        
        Query consulta = sesion.createNativeQuery("SELECT DISTINCT a.idActividad FROM ACTIVIDAD a INNER JOIN REALIZA r ON a.idActividad = r.idActividad INNER JOIN SOCIO s ON r.numeroSocio = s.numeroSocio WHERE s.numeroSocio <> :soc AND a.idActividad NOT IN(SELECT a.idActividad FROM ACTIVIDAD a INNER JOIN REALIZA r ON a.idActividad = r.idActividad INNER JOIN SOCIO s ON r.numeroSocio = s.numeroSocio WHERE s.numeroSocio = :soc)").setParameter("soc", numSocio);
        ArrayList<String> idActividades = (ArrayList<String>) consulta.getResultList();
        
        return idActividades;
    }
    
    // devuelve el id asociado a una catividad cuyo nombre se pasa por parametro
    static public ArrayList<String> getId(Session sesion, String nombre){
        Query consulta = sesion.createNativeQuery("SELECT a.idActividad FROM ACTIVIDAD a WHERE a.nombre = :nombre").setParameter("nombre", nombre);
        ArrayList<String> idActividad = (ArrayList<String>) consulta.getResultList();
        return idActividad;
    }
}
