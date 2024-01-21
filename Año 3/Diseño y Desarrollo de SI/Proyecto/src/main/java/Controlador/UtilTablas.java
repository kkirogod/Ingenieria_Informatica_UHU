package Controlador;

import Modelo.Monitor;
import Modelo.Socio;
import Vista.*;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class UtilTablas {

    private DefaultTableModel modeloTabla;

    public void inicializarTabla(JPanel vista) {

        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (vista.getClass().equals(PanelMonitor.class)) {

            PanelMonitor vMonitor = (PanelMonitor) vista;

            vMonitor.TableMonitores.setModel(modeloTabla);
        } else if (vista.getClass().equals(PanelSocio.class)) {

            PanelSocio vSocio = (PanelSocio) vista;

            vSocio.TableSocios.setModel(modeloTabla);
        }
    }

    public void inicializarTablaCategorias(JDialog vista) {

        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        VistaActualizacionCategorias vActualizacionCat = (VistaActualizacionCategorias) vista;

        vActualizacionCat.TableActualizacionCategorias.setModel(modeloTabla);

    }

    public void dibujarTabla(JPanel vista) {

        if (vista.getClass().equals(PanelMonitor.class)) {

            PanelMonitor vMonitor = (PanelMonitor) vista;

            String[] columnasTabla = {"Código", "Nombre", "DNI", "Teléfono", "Correo", "Fecha Incorporación", "Nick"};

            modeloTabla.setColumnIdentifiers(columnasTabla);

            // Para no permitir el redimensionamiento de las columnas con el ratón 
            vMonitor.TableMonitores.getTableHeader().setResizingAllowed(false);
            vMonitor.TableMonitores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

            // Así se fija el ancho de las columnas
            vMonitor.TableMonitores.getColumnModel()
                    .getColumn(0).setPreferredWidth(60);
            vMonitor.TableMonitores.getColumnModel()
                    .getColumn(1).setPreferredWidth(240);
            vMonitor.TableMonitores.getColumnModel()
                    .getColumn(2).setPreferredWidth(70);
            vMonitor.TableMonitores.getColumnModel()
                    .getColumn(3).setPreferredWidth(70);
            vMonitor.TableMonitores.getColumnModel()
                    .getColumn(4).setPreferredWidth(200);
            vMonitor.TableMonitores.getColumnModel()
                    .getColumn(5).setPreferredWidth(150);
            vMonitor.TableMonitores.getColumnModel()
                    .getColumn(6).setPreferredWidth(60);

        } else if (vista.getClass().equals(PanelSocio.class)) {

            PanelSocio vSocio = (PanelSocio) vista;

            String[] columnasTabla = {"Socio", "Nombre", "DNI", "Fecha de Nacimiento", "Teléfono", "Correo", "Fecha de entrada", "Cat."};

            modeloTabla.setColumnIdentifiers(columnasTabla);

            // Para no permitir el redimensionamiento de las columnas con el ratón 
            vSocio.TableSocios.getTableHeader().setResizingAllowed(false);
            vSocio.TableSocios.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

            // Así se fija el ancho de las columnas
            vSocio.TableSocios.getColumnModel()
                    .getColumn(0).setPreferredWidth(80);
            vSocio.TableSocios.getColumnModel()
                    .getColumn(1).setPreferredWidth(240);
            vSocio.TableSocios.getColumnModel()
                    .getColumn(2).setPreferredWidth(150);
            vSocio.TableSocios.getColumnModel()
                    .getColumn(3).setPreferredWidth(150);
            vSocio.TableSocios.getColumnModel()
                    .getColumn(4).setPreferredWidth(100);
            vSocio.TableSocios.getColumnModel()
                    .getColumn(5).setPreferredWidth(200);
            vSocio.TableSocios.getColumnModel()
                    .getColumn(6).setPreferredWidth(100);
            vSocio.TableSocios.getColumnModel()
                    .getColumn(7).setPreferredWidth(30);
        }
    }

    public void dibujarTablaCategorias(JDialog vista) {

        VistaActualizacionCategorias vActualizacionCat = (VistaActualizacionCategorias) vista;

        String[] columnasTabla = {"Nombre", "DNI", "Categoría"};

        modeloTabla.setColumnIdentifiers(columnasTabla);

        // Para no permitir el redimensionamiento de las columnas con el ratón 
        vActualizacionCat.TableActualizacionCategorias.getTableHeader().setResizingAllowed(false);
        vActualizacionCat.TableActualizacionCategorias.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        // Así se fija el ancho de las columnas
        vActualizacionCat.TableActualizacionCategorias.getColumnModel()
                .getColumn(0).setPreferredWidth(270);
        vActualizacionCat.TableActualizacionCategorias.getColumnModel()
                .getColumn(1).setPreferredWidth(70);
        vActualizacionCat.TableActualizacionCategorias.getColumnModel()
                .getColumn(2).setPreferredWidth(30);
    }

    public void rellenarTablaMonitores(ArrayList<Monitor> monitores) {

        Object[] fila = new Object[7];

        for (Monitor monitor : monitores) {

            fila[0] = monitor.getCodMonitor();
            fila[1] = monitor.getNombre();
            fila[2] = monitor.getDni();
            fila[3] = monitor.getTelefono();
            fila[4] = monitor.getCorreo();
            fila[5] = monitor.getFechaEntrada();
            fila[6] = monitor.getNick();

            modeloTabla.addRow(fila);
        }
    }

    public void rellenarTablaSocios(ArrayList<Socio> socios) {

        Object[] fila = new Object[8];

        for (Socio socio : socios) {

            fila[0] = socio.getNumeroSocio();
            fila[1] = socio.getNombre();
            fila[2] = socio.getDni();
            fila[3] = socio.getFechaNacimiento();
            fila[4] = socio.getTelefono();
            fila[5] = socio.getCorreo();
            fila[6] = socio.getFechaEntrada();
            fila[7] = socio.getCategoria();

            modeloTabla.addRow(fila);
        }
    }
    
    public void rellenarTablaCategorias(ArrayList<Socio> socios) {

        Object[] fila = new Object[3];

        for (Socio socio : socios) {

            fila[0] = socio.getNombre();
            fila[1] = socio.getDni();
            fila[2] = socio.getCategoria();

            modeloTabla.addRow(fila);
        }
    }

    public void vaciarTabla() {

        while (modeloTabla.getRowCount() > 0) {
            modeloTabla.removeRow(0);
        }
    }

    // Metodo que ajusta el ancho de las columnas de una tabla conforme a la longitud de sus cabeceras
    public static void resizeColumnWidth(JTable table) {
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
            Object headerValue = tableColumn.getHeaderValue();
            Component headerComp = headerRenderer.getTableCellRendererComponent(table, headerValue, false, false, 0, column);
            int headerWidth = headerComp.getPreferredSize().width;

            /*
            int cellWidth = 0;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Object value = table.getValueAt(row, column);
                Component cellComp = cellRenderer.getTableCellRendererComponent(table, value, false, false, row, column);
                cellWidth = Math.max(cellWidth, cellComp.getPreferredSize().width);
            }
             */
            //int preferredWidth = Math.max(headerWidth, cellWidth) + 10;
            tableColumn.setPreferredWidth(headerWidth + 10);
        }
    }

    // Metodo que ajusta el ancho de la columna de una tabla conforme a la longitud de sus datos
    /*
    public void resizeColumnWidth(JTable table) {
        //Se obtiene el modelo de la columna
        TableColumnModel columnModel = table.getColumnModel();
        //Se obtiene el total de las columnas
        for (int column = 0; column < table.getColumnCount(); column++) {
            //Establecemos un valor minimo para el ancho de la columna
            int width = 30; //Min Width
            //Obtenemos el numero de filas de la tabla
            for (int row = 0; row < table.getRowCount(); row++) {
                //Obtenemos el renderizador de la tabla
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                //Creamos un objeto para preparar el renderer
                Component comp = table.prepareRenderer(renderer, row, column);
                //Establecemos el width segun el valor maximo del ancho de la columna
                width = Math.max(comp.getPreferredSize().width + 1, width);

            }
            //Se establece una condicion para no sobrepasar el valor de 300
            //Esto es Opcional
            
            if (width > 300) {
                width = 300;
            }

            //Se establece el ancho de la columna
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
     */
}
