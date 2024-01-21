package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;

public class ControladorSocio implements ActionListener {

    private Session sesion;
    private Transaction tr;
    private VistaPrincipal vPrincipal;
    private PanelSocio vSocio;
    private UtilTablas uTablas;
    private SessionFactory sessionFactory;
    private VistaMensaje vMensaje;
    private VistaNuevoSocio vNuevoSocio;
    private VistaGestionInscripciones vInscripciones;
    private Date fechaActual;
    private String numSocioSeleccionado;
    static int inicio = 0;

    public ControladorSocio(SessionFactory sessionFactory, VistaPrincipal vPrincipal, PanelSocio vSocio, UtilTablas uTablas) {

        this.sessionFactory = sessionFactory;
        this.vPrincipal = vPrincipal;
        this.vSocio = vSocio;
        this.uTablas = uTablas;

        vMensaje = new VistaMensaje();
        vNuevoSocio = new VistaNuevoSocio();
        vInscripciones = new VistaGestionInscripciones();
        fechaActual = new Date();

        numSocioSeleccionado = null;

        vSocio.ComboBoxCategoriaSocio.setSelectedIndex(0);
        cargarTablaSocios();

        if (inicio == 0) {
            addListeners();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "nuevoSocio":
                
                try {

                sesion = sessionFactory.openSession();
                tr = sesion.beginTransaction();

                vNuevoSocio.setLocationRelativeTo(null);
                vNuevoSocio.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                vNuevoSocio.setResizable(false);

                vNuevoSocio.TextFieldNumero.setText(nuevoCodigo());

                tr.commit();

            } catch (Exception ex) {
                tr.rollback();
                vMensaje.mensajeVentana(vPrincipal, "error", "Error en la petición de socios\n" + ex.getMessage());
            } finally {
                if (sesion != null && sesion.isOpen()) {
                    sesion.close();
                }
            }

            vNuevoSocio.LabelAsteriscoNombre.setVisible(false);
            vNuevoSocio.LabelAsteriscoCategoria.setVisible(false);
            vNuevoSocio.LabelAsteriscoDNI.setVisible(false);
            vNuevoSocio.LabelAsteriscoTlf.setVisible(false);
            vNuevoSocio.LabelAsteriscoCorreo.setVisible(false);
            vNuevoSocio.LabelAsteriscoFEnt.setVisible(false);
            vNuevoSocio.LabelAsteriscoFNac.setVisible(false);

            vaciarcampos();

            vNuevoSocio.ButtonInsertarSocio.setText("Insertar");

            vNuevoSocio.setVisible(true);

            break;

            case "actualizaSocio":

                if (numSocioSeleccionado == null) {
                    vMensaje.mensajeVentana(vSocio, "error", "Debes seleccionar la fila del socio que deseas actualizar.");
                } else {
                    try {
                        sesion = sessionFactory.openSession();
                        tr = sesion.beginTransaction();

                        Socio s = sesion.get(Socio.class, numSocioSeleccionado);

                        tr.commit();

                        if (s != null) {

                            Date dateFechaNacimiento = null, dateFechaEntrada = null;
                            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                            try {
                                dateFechaNacimiento = formatoFecha.parse(s.getFechaNacimiento());
                                dateFechaEntrada = formatoFecha.parse(s.getFechaEntrada());

                            } catch (ParseException ex) {
                                vMensaje.mensajeVentana(vSocio, "error", "Error al transformar las fechas\n" + ex.getMessage());
                            }

                            vNuevoSocio.setLocationRelativeTo(null);
                            vNuevoSocio.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            vNuevoSocio.setResizable(false);

                            vNuevoSocio.ButtonInsertarSocio.setText("Actualizar");

                            vNuevoSocio.TextFieldNumero.setText(numSocioSeleccionado);
                            vNuevoSocio.TextFieldNombre.setText(s.getNombre());
                            vNuevoSocio.TextFieldDNI.setText(s.getDni());
                            vNuevoSocio.TextFieldTelefono.setText(s.getTelefono());
                            vNuevoSocio.TextFieldCorreo.setText(s.getCorreo());
                            vNuevoSocio.ComboBoxCategoriaSocio.setSelectedItem(s.getCategoria());
                            vNuevoSocio.DateChooserFechaNacimiento.setDate(dateFechaNacimiento);
                            vNuevoSocio.DateChooserFechaEntrada.setDate(dateFechaEntrada);

                            vNuevoSocio.LabelAsteriscoNombre.setVisible(false);
                            vNuevoSocio.LabelAsteriscoCategoria.setVisible(false);
                            vNuevoSocio.LabelAsteriscoDNI.setVisible(false);
                            vNuevoSocio.LabelAsteriscoTlf.setVisible(false);
                            vNuevoSocio.LabelAsteriscoCorreo.setVisible(false);
                            vNuevoSocio.LabelAsteriscoFEnt.setVisible(false);
                            vNuevoSocio.LabelAsteriscoFNac.setVisible(false);

                            vNuevoSocio.setVisible(true);

                        } else {
                            vMensaje.mensajeVentana(vSocio, "error", "Debes seleccionar la fila del socio que deseas actualizar.");
                        }

                    } catch (HibernateException ex) {
                        tr.rollback();
                        vMensaje.mensajeVentana(vSocio, "error", "Error al abrir la ventana de actualización de socio\n" + ex.getMessage());

                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                }
                break;

            case "insertarSocio":
                
                try {

                sesion = sessionFactory.openSession();
                tr = sesion.beginTransaction();

                boolean insertar = true;

                String numeroSocio, nombre, dni, telefono, correo, fechaNacimiento = null, fechaEntrada = null;
                Character categoria = vNuevoSocio.ComboBoxCategoriaSocio.getSelectedItem().toString().charAt(0);
                Date dateFechaNacimiento, dateFechaEntrada;

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                numeroSocio = vNuevoSocio.TextFieldNumero.getText();
                nombre = vNuevoSocio.TextFieldNombre.getText();
                dni = vNuevoSocio.TextFieldDNI.getText();
                telefono = vNuevoSocio.TextFieldTelefono.getText();
                correo = vNuevoSocio.TextFieldCorreo.getText();

                dateFechaNacimiento = vNuevoSocio.DateChooserFechaNacimiento.getDate();
                if (dateFechaNacimiento != null) {
                    fechaNacimiento = formatoFecha.format(dateFechaNacimiento);
                }

                // CONTROL DE ENTRADAS DE DATOS NO VÁLIDOS (las fechas y la categoría también se asignan aquí)
                if (nombre.equals("")) {
                    vMensaje.mensajeVentana(vNuevoSocio, "error", "El campo NOMBRE es obligatorio.");
                    vNuevoSocio.LabelAsteriscoNombre.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoSocio.LabelAsteriscoNombre.setVisible(false);
                }

                if (!ControladorPrincipal.validarDNI(dni) && dni.length() != 0) {
                    vMensaje.mensajeVentana(vNuevoSocio, "error", "El valor del campo DNI no es válido.");
                    vNuevoSocio.LabelAsteriscoDNI.setVisible(true);
                    insertar = false;
                } else if (dni.equals("")) {
                    vMensaje.mensajeVentana(vNuevoSocio, "error", "El campo DNI es obligatorio.");
                    vNuevoSocio.LabelAsteriscoDNI.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoSocio.LabelAsteriscoDNI.setVisible(false);
                }

                if (telefono.length() != 9 && telefono.length() != 0) {
                    vMensaje.mensajeVentana(vNuevoSocio, "error", "El valor del campo TELÉFONO no es válido.");
                    vNuevoSocio.LabelAsteriscoTlf.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoSocio.LabelAsteriscoTlf.setVisible(false);
                }

                if (!ControladorPrincipal.validarCorreo(correo) && correo.length() != 0) {
                    vMensaje.mensajeVentana(vNuevoSocio, "error", "El valor del campo CORREO no es válido.");
                    vNuevoSocio.LabelAsteriscoCorreo.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoSocio.LabelAsteriscoCorreo.setVisible(false);
                }

                if (dateFechaNacimiento.after(fechaActual)) {
                    vMensaje.mensajeVentana(vNuevoSocio, "error", "La FECHA DE NACIMIENTO no puede ser posterior a la fecha actual.");
                    vNuevoSocio.LabelAsteriscoFNac.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoSocio.LabelAsteriscoFNac.setVisible(false);
                }

                // para obtener la fecha de hace 18 años
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaActual);

                int anio = calendar.get(Calendar.YEAR) - 18;
                calendar.set(Calendar.YEAR, anio);

                Date fechaActualMenos18Anios = calendar.getTime();
                //
                if (dateFechaNacimiento.after(fechaActualMenos18Anios)) {
                    vMensaje.mensajeVentana(vNuevoSocio, "error", "Sólo se admiten socios mayores de 18 años.");
                    vNuevoSocio.LabelAsteriscoFNac.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoSocio.LabelAsteriscoFNac.setVisible(false);
                }

                dateFechaEntrada = vNuevoSocio.DateChooserFechaEntrada.getDate();
                if (dateFechaEntrada == null) {
                    vMensaje.mensajeVentana(vNuevoSocio, "error", "El campo FECHA DE ENTRADA es obligatorio.");
                    vNuevoSocio.LabelAsteriscoFEnt.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoSocio.LabelAsteriscoFEnt.setVisible(false);
                    fechaEntrada = formatoFecha.format(dateFechaEntrada);
                }

                if (dateFechaEntrada.after(fechaActual)) {
                    vMensaje.mensajeVentana(vNuevoSocio, "error", "La FECHA DE ENTRADA no puede ser posterior a la fecha actual.");
                    vNuevoSocio.LabelAsteriscoFEnt.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoSocio.LabelAsteriscoFEnt.setVisible(false);
                }

                if (dateFechaNacimiento.after(dateFechaEntrada)) {
                    vMensaje.mensajeVentana(vNuevoSocio, "error", "La FECHA DE NACIMIENTO no puede ser posterior a la de entrada.");
                    vNuevoSocio.LabelAsteriscoFNac.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoSocio.LabelAsteriscoFNac.setVisible(false);
                }

                ////////////////////////////////////////////////////////////////
                if (insertar) {
                    Socio s = new Socio(numeroSocio, nombre, dni, fechaNacimiento, telefono, correo, fechaEntrada, categoria);

                    SocioDAO.altaSocio(sesion, s);

                    if (vNuevoSocio.ButtonInsertarSocio.getText().equals("Insertar")) {
                        vMensaje.mensajeVentana(vNuevoSocio, "info", s.getNombre() + " ha sido insertado con éxito.");
                    } else {
                        vMensaje.mensajeVentana(vNuevoSocio, "info", s.getNombre() + " ha sido actualizado con éxito.");
                    }

                    vNuevoSocio.dispose();
                }

                tr.commit();

                if (insertar) {
                    vSocio.ComboBoxCategoriaSocio.setSelectedIndex(0);
                    cargarTablaSocios();
                }

            } catch (HibernateException ex) {
                tr.rollback();
                vMensaje.mensajeVentana(vSocio, "error", "Error en la inserción de socios\n" + ex.getMessage());
            } finally {
                if (sesion != null && sesion.isOpen()) {
                    sesion.close();
                }
            }

            break;

            case "cancelarInsertarSocio":
                vNuevoSocio.dispose();
                break;

            case "bajaSocio":
                if (numSocioSeleccionado == null) {
                    vMensaje.mensajeVentana(vSocio, "error", "Debes seleccionar la fila del socio que deseas dar de baja.");
                } else {
                    try {
                        boolean eliminado = false;

                        sesion = sessionFactory.openSession();
                        tr = sesion.beginTransaction();

                        Socio s = sesion.get(Socio.class, numSocioSeleccionado);

                        if (s != null) {

                            switch (vMensaje.mensajeDialogo(vSocio, "¿Seguro que quieres dar de baja al socio " + s.getNombre() + "?")) {

                                case JOptionPane.YES_OPTION:

                                    SocioDAO.bajaSocio(sesion, s);
                                    eliminado = true;
                                    break;

                                default:
                                    break;
                            }
                        } else {
                            vMensaje.mensajeVentana(vSocio, "error", "Debes seleccionar la fila del socio que deseas dar de baja.");
                        }
                        tr.commit();

                        if (eliminado) {
                            vSocio.ComboBoxCategoriaSocio.setSelectedIndex(0);
                            cargarTablaSocios();
                            numSocioSeleccionado = null;
                        }

                    } catch (HibernateException ex) {
                        tr.rollback();
                        vMensaje.mensajeVentana(vSocio, "error", "Error al dar de baja a un socio\n" + ex.getMessage());

                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                }
                break;

            case "gestionarInscripciones":

                if (numSocioSeleccionado == null) {
                    vMensaje.mensajeVentana(vSocio, "error", "Debes seleccionar la fila de un socio.");
                } else {

                    vInscripciones.setLocationRelativeTo(null);
                    vInscripciones.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    vInscripciones.setResizable(false);

                    vInscripciones.LabelNumeroSocio.setText(numSocioSeleccionado);

                    vInscripciones.LabelActividades.setVisible(false);
                    vInscripciones.ComboBoxSeleccionActividad.setVisible(false);
                    vInscripciones.ButtonConfirmarAltaBaja.setVisible(false);

                    vInscripciones.setVisible(true);
                }
                break;

            case "altaActividad":

                vInscripciones.ComboBoxSeleccionActividad.removeAllItems();

                try {

                    sesion = sessionFactory.openSession();
                    tr = sesion.beginTransaction();

                    ArrayList<String> idActividades = ActividadDAO.listaIdNOActividadesSocio(sesion, numSocioSeleccionado);

                    for (String id : idActividades) {
                        Actividad a = sesion.get(Actividad.class, id);
                        vInscripciones.ComboBoxSeleccionActividad.addItem(a.getNombre());
                    }

                    vInscripciones.ComboBoxSeleccionActividad.setSelectedIndex(0);
                    vInscripciones.ButtonConfirmarAltaBaja.setText("Confirmar alta");

                    vInscripciones.LabelActividades.setVisible(true);
                    vInscripciones.ComboBoxSeleccionActividad.setVisible(true);
                    vInscripciones.ButtonConfirmarAltaBaja.setVisible(true);

                    tr.commit();

                } catch (HibernateException ex) {

                    tr.rollback();
                    vMensaje.mensajeVentana(vInscripciones, "error", "Error en la petición de actividades" + ex.getMessage());

                } finally {

                    if (sesion != null && sesion.isOpen()) {
                        sesion.close();
                    }
                }
                break;

            case "bajaActividad":

                vInscripciones.ComboBoxSeleccionActividad.removeAllItems();

                try {

                    sesion = sessionFactory.openSession();
                    tr = sesion.beginTransaction();

                    ArrayList<String> idActividades = ActividadDAO.listaIdSIActividadesSocio(sesion, numSocioSeleccionado);

                    for (String id : idActividades) {
                        Actividad a = sesion.get(Actividad.class, id);
                        vInscripciones.ComboBoxSeleccionActividad.addItem(a.getNombre());
                    }

                    if (vInscripciones.ComboBoxSeleccionActividad.getItemCount() > 0) {
                        vInscripciones.ComboBoxSeleccionActividad.setSelectedIndex(0);
                    }
                    vInscripciones.ButtonConfirmarAltaBaja.setText("Confirmar baja");

                    vInscripciones.LabelActividades.setVisible(true);
                    vInscripciones.ComboBoxSeleccionActividad.setVisible(true);
                    vInscripciones.ButtonConfirmarAltaBaja.setVisible(true);

                    tr.commit();

                } catch (HibernateException ex) {

                    tr.rollback();
                    vMensaje.mensajeVentana(vInscripciones, "error", "Error en la petición de actividades" + ex.getMessage());

                } finally {

                    if (sesion != null && sesion.isOpen()) {
                        sesion.close();
                    }
                }
                break;

            case "confirmarAltaBaja":

                if (vInscripciones.ComboBoxSeleccionActividad.getItemCount() > 0) {

                    String nombreActividad = (String) (vInscripciones.ComboBoxSeleccionActividad.getSelectedItem());

                    try {

                        sesion = sessionFactory.openSession();
                        tr = sesion.beginTransaction();

                        ArrayList<String> id = ActividadDAO.getId(sesion, nombreActividad);
                        String idActividad = id.get(0);

                        Actividad act = sesion.get(Actividad.class, idActividad);
                        Socio soc = sesion.get(Socio.class, numSocioSeleccionado);

                        if (vInscripciones.ButtonConfirmarAltaBaja.getText().equals("Confirmar alta")) {
                            act.altaSocio(soc);
                        } else {
                            act.bajaSocio(soc);
                        }

                        sesion.saveOrUpdate(act);

                        if (vInscripciones.ButtonConfirmarAltaBaja.getText().equals("Confirmar alta")) {
                            vMensaje.mensajeVentana(vNuevoSocio, "info", soc.getNombre() + " ha sido inscrit@ con éxito en " + act.getNombre() + ".");
                        } else {
                            vMensaje.mensajeVentana(vNuevoSocio, "info", soc.getNombre() + " ha sido borrado@ con éxito de " + act.getNombre() + ".");
                        }

                        vInscripciones.dispose();

                        tr.commit();

                    } catch (HibernateException ex) {

                        tr.rollback();
                        vMensaje.mensajeVentana(vInscripciones, "error", "Error en la modificación de la actividad\n" + ex.getMessage());

                    } finally {

                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                } else {
                    if (vInscripciones.ButtonConfirmarAltaBaja.getText().equals("Confirmar baja")) {
                        vMensaje.mensajeVentana(vInscripciones, "error", "El socio seleccionado no está inscrito en ninguna actividad.");
                    } else {
                        vMensaje.mensajeVentana(vInscripciones, "error", "El socio seleccionado ya está inscrito en todas las actividades disponibles.");
                    }
                }

                break;

            case "comboBoxCategoriaSocio":
                cargarTablaSocios();
                break;
        }
    }

    private void addListeners() {

        vInscripciones.ButtonAltaActividad.addActionListener(this);
        vInscripciones.ButtonBajaActividad.addActionListener(this);
        vInscripciones.ButtonConfirmarAltaBaja.addActionListener(this);
        vNuevoSocio.ButtonCancelarInsertarSocio.addActionListener(this);
        vNuevoSocio.ButtonInsertarSocio.addActionListener(this);
        vSocio.ComboBoxCategoriaSocio.addActionListener(this);
        vSocio.ButtonNuevoSocio.addActionListener(this);
        vSocio.ButtonBajaSocio.addActionListener(this);
        vSocio.ButtonActualizacionSocio.addActionListener(this);
        vSocio.ButtonGestionarInscripciones.addActionListener(this);
        vSocio.TableSocios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Obtener la fila y columna en la que se hizo clic
                int row = vSocio.TableSocios.rowAtPoint(e.getPoint());
                int col = vSocio.TableSocios.columnAtPoint(e.getPoint());

                // Recoger el contenido de la primera columna de la fila
                if (row != -1 && col != -1) {
                    Object numSocio = vSocio.TableSocios.getValueAt(row, 0);
                    numSocioSeleccionado = (String) numSocio;
                } else {
                    numSocioSeleccionado = null;
                }
            }
        });
        inicio = 1;
    }

    private void cargarTablaSocios() {

        uTablas.dibujarTabla(vSocio);
        sesion = sessionFactory.openSession();
        tr = sesion.beginTransaction();

        try {
            ArrayList<Socio> lSocios = SocioDAO.listaSociosHQL(sesion);
            char cat = vSocio.ComboBoxCategoriaSocio.getSelectedItem().toString().charAt(0);
            
            if (cat != '*') {
                lSocios = SocioDAO.listaSociosPorCategoria(sesion, cat);
            }
            
            uTablas.vaciarTabla();
            uTablas.rellenarTablaSocios(lSocios);
            uTablas.resizeColumnWidth(vSocio.TableSocios);
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

    private String nuevoCodigo() {

        ArrayList<String> valor = SocioDAO.ultimoSocio(sesion);
        String codigo = valor.get(0);

        String prefijo = codigo.substring(0, codigo.length() - 3);
        int numero = Integer.parseInt(codigo.substring(codigo.length() - 3));
        numero++;

        codigo = prefijo + String.format("%03d", numero);

        return codigo;
    }

    private void vaciarcampos() {

        vNuevoSocio.TextFieldNombre.setText("");
        vNuevoSocio.TextFieldDNI.setText("");
        vNuevoSocio.TextFieldTelefono.setText("");
        vNuevoSocio.TextFieldCorreo.setText("");
        vNuevoSocio.ComboBoxCategoriaSocio.setSelectedIndex(0);
        vNuevoSocio.DateChooserFechaNacimiento.setDate(fechaActual);
        vNuevoSocio.DateChooserFechaEntrada.setDate(fechaActual);
    }
}
