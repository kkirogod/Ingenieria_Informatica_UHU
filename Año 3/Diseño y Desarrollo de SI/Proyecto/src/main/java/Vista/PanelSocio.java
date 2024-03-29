/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

/**
 *
 * @author migue
 */
public class PanelSocio extends javax.swing.JPanel {

    /**
     * Creates new form PanelSocio
     */
    public PanelSocio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneSocios = new javax.swing.JScrollPane();
        TableSocios = new javax.swing.JTable();
        LabelGestionSocios = new javax.swing.JLabel();
        ButtonNuevoSocio = new javax.swing.JButton();
        ButtonBajaSocio = new javax.swing.JButton();
        ButtonActualizacionSocio = new javax.swing.JButton();
        ButtonGestionarInscripciones = new javax.swing.JButton();
        LabelCategoria = new javax.swing.JLabel();
        ComboBoxCategoriaSocio = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(0, 0, 51));

        TableSocios.setBackground(new java.awt.Color(204, 204, 204));
        TableSocios.setForeground(new java.awt.Color(0, 0, 0));
        TableSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneSocios.setViewportView(TableSocios);

        LabelGestionSocios.setBackground(new java.awt.Color(255, 255, 255));
        LabelGestionSocios.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LabelGestionSocios.setText("GESTIÓN DE SOCIOS");
        LabelGestionSocios.setToolTipText("");

        ButtonNuevoSocio.setBackground(new java.awt.Color(0, 153, 51));
        ButtonNuevoSocio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonNuevoSocio.setText("Nuevo Socio");
        ButtonNuevoSocio.setActionCommand("nuevoSocio");
        ButtonNuevoSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonNuevoSocioActionPerformed(evt);
            }
        });

        ButtonBajaSocio.setBackground(new java.awt.Color(204, 51, 0));
        ButtonBajaSocio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonBajaSocio.setText("Baja de Socio");
        ButtonBajaSocio.setActionCommand("bajaSocio");
        ButtonBajaSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBajaSocioActionPerformed(evt);
            }
        });

        ButtonActualizacionSocio.setBackground(new java.awt.Color(204, 204, 204));
        ButtonActualizacionSocio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonActualizacionSocio.setForeground(new java.awt.Color(0, 0, 0));
        ButtonActualizacionSocio.setText("Actualización de Socio");
        ButtonActualizacionSocio.setActionCommand("actualizaSocio");
        ButtonActualizacionSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonActualizacionSocioActionPerformed(evt);
            }
        });

        ButtonGestionarInscripciones.setBackground(new java.awt.Color(51, 51, 51));
        ButtonGestionarInscripciones.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonGestionarInscripciones.setForeground(new java.awt.Color(255, 255, 255));
        ButtonGestionarInscripciones.setText("Gestionar inscripciones");
        ButtonGestionarInscripciones.setActionCommand("gestionarInscripciones");
        ButtonGestionarInscripciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonGestionarInscripcionesActionPerformed(evt);
            }
        });

        LabelCategoria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LabelCategoria.setText("Categoría:");

        ComboBoxCategoriaSocio.setBackground(new java.awt.Color(204, 204, 204));
        ComboBoxCategoriaSocio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ComboBoxCategoriaSocio.setForeground(new java.awt.Color(0, 0, 0));
        ComboBoxCategoriaSocio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "A", "B", "C", "D", "E" }));
        ComboBoxCategoriaSocio.setActionCommand("comboBoxCategoriaSocio");
        ComboBoxCategoriaSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCategoriaSocioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneSocios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonNuevoSocio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ButtonBajaSocio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ButtonActualizacionSocio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonGestionarInscripciones)
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelGestionSocios)
                .addGap(108, 108, 108)
                .addComponent(LabelCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxCategoriaSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelGestionSocios)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComboBoxCategoriaSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonNuevoSocio)
                    .addComponent(ButtonBajaSocio)
                    .addComponent(ButtonActualizacionSocio)
                    .addComponent(ButtonGestionarInscripciones))
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonNuevoSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNuevoSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonNuevoSocioActionPerformed

    private void ButtonBajaSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBajaSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonBajaSocioActionPerformed

    private void ButtonActualizacionSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonActualizacionSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonActualizacionSocioActionPerformed

    private void ButtonGestionarInscripcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonGestionarInscripcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonGestionarInscripcionesActionPerformed

    private void ComboBoxCategoriaSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCategoriaSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxCategoriaSocioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton ButtonActualizacionSocio;
    public javax.swing.JButton ButtonBajaSocio;
    public javax.swing.JButton ButtonGestionarInscripciones;
    public javax.swing.JButton ButtonNuevoSocio;
    public javax.swing.JComboBox<String> ComboBoxCategoriaSocio;
    public javax.swing.JLabel LabelCategoria;
    public javax.swing.JLabel LabelGestionSocios;
    public javax.swing.JTable TableSocios;
    public javax.swing.JScrollPane jScrollPaneSocios;
    // End of variables declaration//GEN-END:variables
}
