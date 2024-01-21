// FALTA:
// ARREGLAR GESTION MONITORES
// METODO VISTAMENSAJE.MENSAJE
// TABLA GESTION SOCIOS
package Controlador;

import Modelo.Actividad;
import Modelo.ActividadDAO;
import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.*;
import java.awt.CardLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ControladorPrincipal implements ActionListener {

    private Session sesion;
    private Transaction tr;
    private VistaPrincipal vPrincipal;
    private PanelPrincipal pPrincipal;
    private PanelSocio vSocio;
    private PanelMonitor vMonitor;
    private UtilTablas uTablas;
    private SessionFactory sessionFactory;
    private VistaMensaje vMensaje;
    private VistaActualizacionCategorias vActualizacionCat;
    private VistaConsultaActividad vConsultaActividad;
    private char panel;

    public ControladorPrincipal(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;

        vConsultaActividad = new VistaConsultaActividad();
        vActualizacionCat = new VistaActualizacionCategorias();
        vMensaje = new VistaMensaje();
        vPrincipal = new VistaPrincipal();
        pPrincipal = new PanelPrincipal();
        vSocio = new PanelSocio();
        vMonitor = new PanelMonitor();
        uTablas = new UtilTablas();

        addListeners();

        vPrincipal.getContentPane().setLayout(new CardLayout());
        vPrincipal.add(pPrincipal);
        vPrincipal.add(vSocio);
        vPrincipal.add(vMonitor);

        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.setVisible(true);

        panel = 'p';

        muestraPanel(pPrincipal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "salirAplicacion":
                vPrincipal.dispose();
                System.exit(0);
                break;
            case "gestionMonitores":
                panel = 'm';
                muestraPanel(vMonitor);
                ControladorMonitor cMonitor = new ControladorMonitor(sessionFactory, vPrincipal, vMonitor, uTablas);
                break;
            case "gestionSocios":
                panel = 's';
                muestraPanel(vSocio);
                ControladorSocio cSocio = new ControladorSocio(sessionFactory, vPrincipal, vSocio, uTablas);
                break;
            case "actualizacionCategorias":
                uTablas.inicializarTablaCategorias(vActualizacionCat);

                vActualizacionCat.setLocationRelativeTo(null);
                vActualizacionCat.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                vActualizacionCat.setResizable(false);

                cargarTablaCategorias();

                vActualizacionCat.setVisible(true);
                break;

            case "subirCategoria":

                switch (vMensaje.mensajeDialogo(vActualizacionCat, "¿Seguro que desea incrementar la categoría a todos los socios?")) {
                    case JOptionPane.YES_OPTION:

                        sesion = sessionFactory.openSession();
                        tr = sesion.beginTransaction();

                        try {
                            ArrayList<Socio> lSocios = SocioDAO.listaSociosSQL(sesion);

                            for (Socio s : lSocios) {
                                switch (s.getCategoria()) {
                                    case 'B':
                                        s.setCategoria('A');
                                        break;
                                    case 'C':
                                        s.setCategoria('B');
                                        break;
                                    case 'D':
                                        s.setCategoria('C');
                                        break;
                                    case 'E':
                                        s.setCategoria('D');
                                        break;
                                }
                                SocioDAO.altaSocio(sesion, s);
                            }

                            tr.commit();
                        } catch (Exception ex) {
                            tr.rollback();
                            vMensaje.mensajeVentana(vPrincipal, "error", "Error en la petición de socios\n" + ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }

                        cargarTablaCategorias();

                        if (panel == 's') {
                            muestraPanel(vSocio);
                            cSocio = new ControladorSocio(sessionFactory, vPrincipal, vSocio, uTablas);
                            break;
                        }

                        break;

                    default:
                        break;
                }
                break;

            case "bajarCategoria":

                switch (vMensaje.mensajeDialogo(vActualizacionCat, "¿Seguro que desea decrementar la categoría a todos los socios?")) {
                    case JOptionPane.YES_OPTION:

                        sesion = sessionFactory.openSession();
                        tr = sesion.beginTransaction();

                        try {
                            ArrayList<Socio> lSocios = SocioDAO.listaSociosSQL(sesion);

                            for (Socio s : lSocios) {
                                switch (s.getCategoria()) {
                                    case 'A':
                                        s.setCategoria('B');
                                        break;
                                    case 'B':
                                        s.setCategoria('C');
                                        break;
                                    case 'C':
                                        s.setCategoria('D');
                                        break;
                                    case 'D':
                                        s.setCategoria('E');
                                        break;
                                }
                                SocioDAO.altaSocio(sesion, s);
                            }

                            tr.commit();
                        } catch (Exception ex) {
                            tr.rollback();
                            vMensaje.mensajeVentana(vPrincipal, "error", "Error en la petición de socios\n" + ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }

                        cargarTablaCategorias();

                        if (panel == 's') {
                            muestraPanel(vSocio);
                            cSocio = new ControladorSocio(sessionFactory, vPrincipal, vSocio, uTablas);
                            break;
                        }

                        break;

                    default:
                        break;
                }
                break;

            case "cuotaActividad":
                vConsultaActividad.setLocationRelativeTo(null);
                vConsultaActividad.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                vConsultaActividad.setResizable(false);

                vConsultaActividad.LabelCuotaMensual1.setVisible(false);
                vConsultaActividad.LabelCuotaMensualDescuentos1.setVisible(false);
                vConsultaActividad.LabelMonitor1.setVisible(false);
                vConsultaActividad.LabelNumSocios1.setVisible(false);
                vConsultaActividad.LabelPrecioBase1.setVisible(false);
                
                vConsultaActividad.TextFieldCodActividad.setText("");

                vConsultaActividad.setVisible(true);
                break;

            case "verCuota":
                try {
                sesion = sessionFactory.openSession();
                tr = sesion.beginTransaction();

                if (ActividadDAO.existeActividad(sesion, vConsultaActividad.TextFieldCodActividad.getText())) {

                    Actividad act = sesion.get(Actividad.class, vConsultaActividad.TextFieldCodActividad.getText());

                    vConsultaActividad.LabelMonitor1.setText(act.getMonitorResponsable().getNombre());
                    vConsultaActividad.LabelNumSocios1.setText(String.valueOf(act.getSocios().size()));
                    vConsultaActividad.LabelPrecioBase1.setText(String.valueOf(act.getPrecioBaseMes()));
                    vConsultaActividad.LabelCuotaMensual1.setText(String.valueOf(act.getSocios().size() * act.getPrecioBaseMes()));

                    float cuotaDescuentos = 0;

                    for (Socio s : act.getSocios()) {
                        switch (s.getCategoria()) {
                            case 'A':
                                cuotaDescuentos += act.getPrecioBaseMes();
                                break;
                            case 'B':
                                cuotaDescuentos += (act.getPrecioBaseMes()-((float) act.getPrecioBaseMes()*0.1));
                                break;
                            case 'C':
                                cuotaDescuentos += (act.getPrecioBaseMes()-((float) act.getPrecioBaseMes()*0.2));
                                break;
                            case 'D':
                                cuotaDescuentos += (act.getPrecioBaseMes()-((float) act.getPrecioBaseMes()*0.3));
                                break;
                            case 'E':
                                cuotaDescuentos += (act.getPrecioBaseMes()-((float) act.getPrecioBaseMes()*0.4));
                                break;
                        }
                    }
                    
                    vConsultaActividad.LabelCuotaMensualDescuentos1.setText(String.valueOf(cuotaDescuentos));
                    
                    vConsultaActividad.LabelMonitor1.setVisible(true);
                    vConsultaActividad.LabelNumSocios1.setVisible(true);
                    vConsultaActividad.LabelPrecioBase1.setVisible(true);
                    vConsultaActividad.LabelCuotaMensual1.setVisible(true);
                    vConsultaActividad.LabelCuotaMensualDescuentos1.setVisible(true);
                    
                } else {
                    vMensaje.mensajeVentana(vConsultaActividad, "error", "El código introducido no existe en la BD");
                }

                tr.commit();

            } catch (HibernateException ex) {

                tr.rollback();
                vMensaje.mensajeVentana(vConsultaActividad, "error", "Error al consultar la actividad\n" + ex.getMessage());

            } finally {

                if (sesion != null && sesion.isOpen()) {
                    sesion.close();
                }
            }
            break;

            case "salirHome":
                panel = 'p';
                muestraPanel(pPrincipal);
                break;
        }
    }

    private void addListeners() {
        vActualizacionCat.ButtonSubirCategoria.addActionListener(this);
        vActualizacionCat.ButtonBajarCategoria.addActionListener(this);

        vConsultaActividad.ButtonVerCuota.addActionListener(this);

        vPrincipal.menuItemCuotaActividad.addActionListener(this);
        vPrincipal.menuItemActualizacionCategorias.addActionListener(this);
        vPrincipal.MenuItemGestionMonitores.addActionListener(this);
        vPrincipal.MenuItemSalirAplicacion.addActionListener(this);
        vPrincipal.MenuItemGestionSocios.addActionListener(this);
        vPrincipal.MenuItemHome.addActionListener(this);
    }

    private void muestraPanel(JPanel panel) {

        if (panel.equals(pPrincipal)) {

            pPrincipal.setVisible(true);
            vMonitor.setVisible(false);
            vSocio.setVisible(false);

        } else if (panel.equals(vMonitor)) {

            uTablas.inicializarTabla(vMonitor);

            pPrincipal.setVisible(false);
            vMonitor.setVisible(true);
            vSocio.setVisible(false);

        } else if (panel.equals(vSocio)) {

            uTablas.inicializarTabla(vSocio);

            pPrincipal.setVisible(false);
            vMonitor.setVisible(false);
            vSocio.setVisible(true);
        }
    }

    private void cargarTablaCategorias() {

        uTablas.dibujarTablaCategorias(vActualizacionCat);
        sesion = sessionFactory.openSession();
        tr = sesion.beginTransaction();

        try {
            ArrayList<Socio> lSocios = SocioDAO.listaSociosSQL(sesion);

            uTablas.vaciarTabla();
            uTablas.rellenarTablaCategorias(lSocios);
            UtilTablas.resizeColumnWidth(vActualizacionCat.TableActualizacionCategorias);
            tr.commit();
        } catch (Exception ex) {
            tr.rollback();
            vMensaje.mensajeVentana(vPrincipal, "error", "Error en la petición de socios\n" + ex.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static boolean validarDNI(String dni) {
        // Definir el patrón para el formato de DNI español
        String patronDNI = "\\d{8}[A-HJ-NP-TV-Z]";

        // Compilar la expresión regular
        Pattern pattern = Pattern.compile(patronDNI);

        // Crear un objeto Matcher para comparar el patrón con el DNI proporcionado
        Matcher matcher = pattern.matcher(dni);

        // Comprobar si el DNI coincide con el patrón
        return matcher.matches();
    }

    public static boolean validarCorreo(String correo) {
        // Patrón para validar la dirección de correo electrónico
        String patronCorreo = "^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$";

        // Compilar la expresión regular en un objeto Pattern
        Pattern pattern = Pattern.compile(patronCorreo);

        // Crear un objeto Matcher para comparar la cadena de entrada con el patrón
        Matcher matcher = pattern.matcher(correo);

        // Devolver true si la cadena coincide con el patrón, de lo contrario, false
        return matcher.matches();
    }
}
