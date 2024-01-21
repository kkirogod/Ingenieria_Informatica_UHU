package Controlador;

import Modelo.Monitor;
import Modelo.MonitorDAO;
import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.*;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;

public class ControladorMonitor implements ActionListener {

    private Session sesion;
    private Transaction tr;
    private VistaPrincipal vPrincipal;
    private PanelMonitor vMonitor;
    private UtilTablas uTablas;
    private SessionFactory sessionFactory;
    private VistaMensaje vMensaje;
    private VistaNuevoMonitor vNuevoMonitor;
    private Date fechaActual;
    private String numMonitorSeleccionado;
    static int inicio = 0;

    public ControladorMonitor(SessionFactory sessionFactory, VistaPrincipal vPrincipal, PanelMonitor vMonitor, UtilTablas uTablas) {

        this.sessionFactory = sessionFactory;
        this.vPrincipal = vPrincipal;
        this.vMonitor = vMonitor;
        this.uTablas = uTablas;

        vMensaje = new VistaMensaje();
        vNuevoMonitor = new VistaNuevoMonitor();
        fechaActual = new Date();

        numMonitorSeleccionado = null;

        cargarTablaMonitores();

        if (inicio == 0) {
            addListeners();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "nuevoMonitor":
                
                try {

                sesion = sessionFactory.openSession();
                tr = sesion.beginTransaction();

                vNuevoMonitor.setLocationRelativeTo(null);
                vNuevoMonitor.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                vNuevoMonitor.setResizable(false);

                vNuevoMonitor.TextFieldCodigo.setText(nuevoCodigo());

                tr.commit();

            } catch (HibernateException ex) {
                tr.rollback();
                vMensaje.mensajeVentana(vPrincipal, "error", "Error en la petición de monitores\n" + ex.getMessage());
            } finally {
                if (sesion != null && sesion.isOpen()) {
                    sesion.close();
                }
            }

            vNuevoMonitor.LabelAsteriscoNombre.setVisible(false);
            vNuevoMonitor.LabelAsteriscoDNI.setVisible(false);
            vNuevoMonitor.LabelAsteriscoFEnt.setVisible(false);
            vNuevoMonitor.LabelAsteriscoTlf.setVisible(false);
            vNuevoMonitor.LabelAsteriscoCorreo.setVisible(false);

            vaciarcampos();

            vNuevoMonitor.ButtonInsertarMonitor.setText("Insertar");

            vNuevoMonitor.setVisible(true);

            break;

            case "actualizaMonitor":

                if (numMonitorSeleccionado == null) {
                    vMensaje.mensajeVentana(vMonitor, "error", "Debes seleccionar la fila del monitor que deseas actualizar.");
                } else {
                    try {
                        sesion = sessionFactory.openSession();
                        tr = sesion.beginTransaction();

                        Monitor m = sesion.get(Monitor.class, numMonitorSeleccionado);
                        
                        tr.commit();

                        if (m != null) {

                            Date dateFechaEntrada = null;
                            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                            try {
                                dateFechaEntrada = formatoFecha.parse(m.getFechaEntrada());

                            } catch (ParseException ex) {
                                vMensaje.mensajeVentana(vMonitor, "error", "Error al transformar la fecha de entrada\n" + ex.getMessage());
                            }

                            vNuevoMonitor.setLocationRelativeTo(null);
                            vNuevoMonitor.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            vNuevoMonitor.setResizable(false);

                            vNuevoMonitor.ButtonInsertarMonitor.setText("Actualizar");

                            vNuevoMonitor.TextFieldCodigo.setText(numMonitorSeleccionado);
                            vNuevoMonitor.TextFieldNombre.setText(m.getNombre());
                            vNuevoMonitor.TextFieldDNI.setText(m.getDni());
                            vNuevoMonitor.TextFieldTelefono.setText(m.getTelefono());
                            vNuevoMonitor.TextFieldCorreo.setText(m.getCorreo());
                            vNuevoMonitor.TextFieldNick.setText(m.getNick());
                            vNuevoMonitor.DateChooserFechaEntrada.setDate(dateFechaEntrada);

                            vNuevoMonitor.LabelAsteriscoNombre.setVisible(false);
                            vNuevoMonitor.LabelAsteriscoDNI.setVisible(false);
                            vNuevoMonitor.LabelAsteriscoFEnt.setVisible(false);
                            vNuevoMonitor.LabelAsteriscoTlf.setVisible(false);
                            vNuevoMonitor.LabelAsteriscoCorreo.setVisible(false);

                            vNuevoMonitor.setVisible(true);

                        } else {
                            vMensaje.mensajeVentana(vMonitor, "error", "Debes seleccionar la fila del monitor que deseas actualizar.");
                        }

                    } catch (HibernateException ex) {
                        tr.rollback();
                        vMensaje.mensajeVentana(vMonitor, "error", "Error al abrir la ventana de actualización de monitor\n" + ex.getMessage());

                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                }
                break;

            case "insertarMonitor":
                
                try {

                sesion = sessionFactory.openSession();
                tr = sesion.beginTransaction();

                boolean insertar = true;

                String codMonitor, nombre, dni, telefono, correo, nick, fechaEntrada = null;

                Date dateFechaEntrada;

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                codMonitor = vNuevoMonitor.TextFieldCodigo.getText();
                nombre = vNuevoMonitor.TextFieldNombre.getText();
                dni = vNuevoMonitor.TextFieldDNI.getText();
                telefono = vNuevoMonitor.TextFieldTelefono.getText();
                correo = vNuevoMonitor.TextFieldCorreo.getText();
                nick = vNuevoMonitor.TextFieldNick.getText();

                // CONTROL DE ENTRADAS DE DATOS NO VÁLIDOS (las fechas y la categoría también se asignan aquí)
                if (nombre.equals("")) {
                    vMensaje.mensajeVentana(vNuevoMonitor, "error", "El campo NOMBRE es obligatorio.");
                    vNuevoMonitor.LabelAsteriscoNombre.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoMonitor.LabelAsteriscoNombre.setVisible(false);
                }

                if (!ControladorPrincipal.validarDNI(dni) && dni.length() != 0) {
                    vMensaje.mensajeVentana(vNuevoMonitor, "error", "El valor del campo DNI no es válido.");
                    vNuevoMonitor.LabelAsteriscoDNI.setVisible(true);
                    insertar = false;
                } else if (dni.equals("")){
                    vMensaje.mensajeVentana(vNuevoMonitor, "error", "El campo DNI es obligatorio.");
                    vNuevoMonitor.LabelAsteriscoDNI.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoMonitor.LabelAsteriscoDNI.setVisible(false);
                }
                
                if (telefono.length()!=9 && telefono.length() != 0) {
                    vMensaje.mensajeVentana(vNuevoMonitor, "error", "El valor del campo TELÉFONO no es válido.");
                    vNuevoMonitor.LabelAsteriscoTlf.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoMonitor.LabelAsteriscoTlf.setVisible(false);
                }

                if (!ControladorPrincipal.validarCorreo(correo) && correo.length() != 0) {
                    vMensaje.mensajeVentana(vNuevoMonitor, "error", "El valor del campo CORREO no es válido.");
                    vNuevoMonitor.LabelAsteriscoCorreo.setVisible(true);
                    insertar = false;
                } else {
                    vNuevoMonitor.LabelAsteriscoCorreo.setVisible(false);
                }

                dateFechaEntrada = vNuevoMonitor.DateChooserFechaEntrada.getDate();
                if (dateFechaEntrada == null) {
                    vMensaje.mensajeVentana(vNuevoMonitor, "error", "El valor del campo FECHA DE ENTRADA no puede ser NULL.");
                    vNuevoMonitor.LabelAsteriscoFEnt.setVisible(true);
                    insertar = false;
                } else {
                    if (dateFechaEntrada.after(fechaActual)) {
                        vMensaje.mensajeVentana(vNuevoMonitor, "error", "La FECHA DE ENTRADA no puede ser posterior a la fecha actual.");
                        vNuevoMonitor.LabelAsteriscoFEnt.setVisible(true);
                        insertar = false;
                    } else {
                        vNuevoMonitor.LabelAsteriscoFEnt.setVisible(false);
                        fechaEntrada = formatoFecha.format(dateFechaEntrada);
                    }
                }

                ////////////////////////////////////////////////////////////////
                if (insertar) {
                    Monitor m = new Monitor(codMonitor, nombre, dni, telefono, correo, fechaEntrada, nick);

                    MonitorDAO.altaMonitor(sesion, m);

                    if (vNuevoMonitor.ButtonInsertarMonitor.getText().equals("Insertar")) {
                        vMensaje.mensajeVentana(vNuevoMonitor, "info", m.getNombre() + " ha sido insertado con éxito.");
                    } else {
                        vMensaje.mensajeVentana(vNuevoMonitor, "info", m.getNombre() + " ha sido actualizado con éxito.");
                    }

                    vNuevoMonitor.dispose();
                }

                tr.commit();

                if (insertar) {
                    cargarTablaMonitores();
                }

            } catch (HibernateException ex) {
                tr.rollback();
                vMensaje.mensajeVentana(vMonitor, "error", "Error en la inserción de monitores\n" + ex.getMessage());
            } finally {
                if (sesion != null && sesion.isOpen()) {
                    sesion.close();
                }
            }

            break;

            case "cancelarInsertarMonitor":
                vNuevoMonitor.dispose();
                break;

            case "bajaMonitor":
                if (numMonitorSeleccionado == null) {
                    vMensaje.mensajeVentana(vMonitor, "error", "Debes seleccionar la fila del monitor que deseas dar de baja.");
                } else {
                    try {
                        boolean eliminado = false;

                        sesion = sessionFactory.openSession();
                        tr = sesion.beginTransaction();

                        Monitor m = sesion.get(Monitor.class, numMonitorSeleccionado);

                        if (m != null) {

                            switch (vMensaje.mensajeDialogo(vMonitor, "¿Seguro que quieres dar de baja al monitor " + m.getNombre() + "?")) {

                                case JOptionPane.YES_OPTION:

                                    MonitorDAO.bajaMonitor(sesion, m);
                                    eliminado = true;
                                    break;

                                default:
                                    break;
                            }
                        } else {
                            vMensaje.mensajeVentana(vMonitor, "error", "Debes seleccionar la fila del monitor que deseas dar de baja.");
                        }
                        tr.commit();

                        if (eliminado) {
                            cargarTablaMonitores();
                            numMonitorSeleccionado = null;
                        }

                    } catch (HibernateException ex) {
                        tr.rollback();
                        vMensaje.mensajeVentana(vMonitor, "error", "Error al dar de baja a un monitor\n" + ex.getMessage());

                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                }
                break;
        }
    }

    private void addListeners() {

        vNuevoMonitor.ButtonCancelarInsertarMonitor.addActionListener(this);
        vNuevoMonitor.ButtonInsertarMonitor.addActionListener(this);
        vMonitor.ButtonNuevoMonitor.addActionListener(this);
        vMonitor.ButtonBajaMonitor.addActionListener(this);
        vMonitor.ButtonActualizacionMonitor.addActionListener(this);
        vMonitor.TableMonitores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Obtener la fila y columna en la que se hizo clic
                int row = vMonitor.TableMonitores.rowAtPoint(e.getPoint());
                int col = vMonitor.TableMonitores.columnAtPoint(e.getPoint());

                // Recoger el contenido de la primera columna de la fila
                if (row != -1 && col != -1) {
                    Object codMonitor = vMonitor.TableMonitores.getValueAt(row, 0);
                    numMonitorSeleccionado = (String) codMonitor;
                } else {
                    numMonitorSeleccionado = null;
                }
            }
        });
        inicio = 1;
    }

    private void cargarTablaMonitores() {

        uTablas.dibujarTabla(vMonitor);
        sesion = sessionFactory.openSession();
        tr = sesion.beginTransaction();

        try {
            ArrayList<Monitor> lMonitores = MonitorDAO.listaMonitoresNamedQuery(sesion);
            uTablas.vaciarTabla();
            uTablas.rellenarTablaMonitores(lMonitores);
            uTablas.resizeColumnWidth(vMonitor.TableMonitores);
            tr.commit();
        } catch (Exception ex) {
            tr.rollback();
            vMensaje.mensajeVentana(vPrincipal, "error", "Error en la petición de monitores\n" + ex.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private String nuevoCodigo() {

        ArrayList<String> valor = MonitorDAO.ultimoMonitor(sesion);
        String codigo = valor.get(0);

        String prefijo = codigo.substring(0, codigo.length() - 3);
        int numero = Integer.parseInt(codigo.substring(codigo.length() - 3));
        numero++;

        codigo = prefijo + String.format("%03d", numero);

        return codigo;
    }

    private void vaciarcampos() {

        vNuevoMonitor.TextFieldNombre.setText("");
        vNuevoMonitor.TextFieldDNI.setText("");
        vNuevoMonitor.TextFieldTelefono.setText("");
        vNuevoMonitor.TextFieldCorreo.setText("");
        vNuevoMonitor.TextFieldNick.setText("");
        vNuevoMonitor.DateChooserFechaEntrada.setDate(fechaActual);
    }
}
