/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devs.vistas;

import dao.ObligacionDao;
import ds.desktop.notify.DesktopNotify;
import entities.ContratoTrabajo;
import entities.ObligacionesContrato;
import java.util.List;
import java.util.Set;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DiegoArevalo
 */
public class frmvistaobligaciones extends javax.swing.JFrame {
ContratoTrabajo contratoselect=null;
ObligacionDao dao=new ObligacionDao();
ObligacionesContrato obligaciones=new ObligacionesContrato();
ObligacionDao obdao=new ObligacionDao();

 DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    /**
     * Creates new form frmvistaobligaciones
     */
    public frmvistaobligaciones() {
        cargarColumnas();
        //cargarModeloTabla();
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        
    }
    public void limpiartextbox(){
        txtid.setText("");
        txtobligaciones.setText("");
        txtid.setEditable(false);
        btnmodificar.setEnabled(false);
        btnEliminar.setEnabled(true);
        obligaciones=null;
        btnguardar.setEnabled(true);
    }
     public void setcontrato(ContratoTrabajo contrato) {
         txtid.setEditable(false);
        this.contratoselect = contrato;
        //lb.setText("Registro de contrato para habitación " + contrato.getLugartrabajo());
        cargarModeloTabla(contratoselect.getIdcontrato());
    }
     public void guardar(){
         ObligacionesContrato obcon=new ObligacionesContrato();
         try {
             if(!txtobligaciones.getText().isEmpty()){
                 obcon.setContratoTrabajo(contratoselect);
                 obcon.setObligaciones(txtobligaciones.getText());
                 obdao.Save(obcon);
                 cargarModeloTabla(contratoselect.getIdcontrato());
                 limpiartextbox();
                 DesktopNotify.showDesktopMessage("Exito", "La Obligacion se ha ingresado correctamente", DesktopNotify.SUCCESS);
                 
             }else{
                DesktopNotify.showDesktopMessage("Informacion", "Debes agregar una obligacion para el contrato de: "+contratoselect.getCatPersonal().getNombres(), DesktopNotify.ERROR); 
             }
             
         } catch (Exception e) {
              DesktopNotify.showDesktopMessage("Error", "Ha Ocurrido un Error", DesktopNotify.ERROR);
              System.out.println("error "+e.getMessage());
         }
         
     }
     public void modificar(){
         if(obligaciones!=null){
             try {
                 obligaciones.setObligaciones(txtobligaciones.getText());
                 obdao.Update(obligaciones);
                 cargarModeloTabla(contratoselect.getIdcontrato());
                 limpiartextbox();
                 DesktopNotify.showDesktopMessage("Exito", "Registro Modificado Correctamente", DesktopNotify.SUCCESS);
             } catch (Exception e) {
                 DesktopNotify.showDesktopMessage("Error", "Ha Ocurrido un Error", DesktopNotify.ERROR);
                 System.out.println("error "+e.getMessage());
             }
         }else{
            DesktopNotify.showDesktopMessage("Informacion", "Debe seleccionar una obligacion", DesktopNotify.ERROR); 
         }
     }
    private void cargarModeloTabla(int id) {
       limpiarTabla();

        List<ObligacionesContrato> lista = dao.BuscarporContrato(id);//agregarlos registros a la lista
        int cantidadLista = lista.size();//cantidad de la lista
        modeloTabla.setRowCount(cantidadLista);//agregar la cantidad al modelo

        for (int i = 0; i < lista.size(); i++) {

            ObligacionesContrato p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getContratoTrabajo().getIdcontrato(), i, 1);
            modeloTabla.setValueAt(p.getObligaciones(), i, 2);
           
        }

    }
    public void eliminar(){
        if(obligaciones!=null){
            try {
                obdao.Delete(obligaciones);
                cargarModeloTabla(contratoselect.getIdcontrato());
                limpiartextbox();
               DesktopNotify.showDesktopMessage("Exito", "Registro Eliminado Correctamente", DesktopNotify.SUCCESS); 
                
            } catch (Exception e) {
                DesktopNotify.showDesktopMessage("Error", "Ha ocurrido un error", DesktopNotify.ERROR);
                System.out.println("error "+e.getMessage());
            }
        }else{
             DesktopNotify.showDesktopMessage("Informacion", "Debe seleccionar una obligacion", DesktopNotify.INFORMATION); 
        }
    }
   private void cargarColumnas() {
        modeloTabla.addColumn("Codigo");
         modeloTabla.addColumn("Contrato");
          modeloTabla.addColumn("Obligaciones");
    }
  private void limpiarTabla() {
        int numFila = modeloTabla.getRowCount(); // cantidad de filas
        if (numFila > 0) {
            // debe de ser i mayor o igual a cero
            for (int i = numFila - 1; i >= 0; i--) { // recorre de mayor a menor
                modeloTabla.removeRow(i); // borra la fila
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablacontrato = new rojeru_san.complementos.TableMetro();
        txtid = new app.bolivia.swing.JCTextField();
        txtobligaciones = new app.bolivia.swing.JCTextField();
        btnnuevo = new rojeru_san.complementos.ButtonHover();
        btnguardar = new rojeru_san.complementos.ButtonHover();
        btnmodificar = new rojeru_san.complementos.ButtonHover();
        btnEliminar = new rojeru_san.complementos.ButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablacontrato.setModel(modeloTabla);
        tablacontrato.setColorBackgoundHead(new java.awt.Color(0, 61, 113));
        tablacontrato.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        //Este codigo se coloca en la tabla en su propiedad Post-Init-Code
        tablacontrato.getSelectionModel().addListSelectionListener( // capturamos la linea seleccionada

            new ListSelectionListener(){ // Instanciamos

                public void valueChanged (ListSelectionEvent event){ // evento de la tabla
                    if(!event.getValueIsAdjusting() && (tablacontrato.getSelectedRow()>=0) ) {
                        int filaSeleccionada = tablacontrato.getSelectedRow(); // tomamos la fila seleccionda
                        /*creamos el obj y le pasamos la fila seleccionada y la columna 1 xq ayi 
                        esta alojado el obj marca en el campo nombre....
                        */     
                        obligaciones = (ObligacionesContrato) modeloTabla.getValueAt(filaSeleccionada,0); 

                        // LLenamos los textBoxs atraves del objeto ...
                        txtid.setText(obligaciones.getIdobligaciones().toString());
                        txtobligaciones.setText(obligaciones.getObligaciones());
                        //txtdescripcion.setText(afpselect.getDescripcion());

                        //abilitar boton para actualizar
                        btnguardar.setEnabled(false);
                        btnmodificar.setEnabled(true);
                        btnEliminar.setEnabled(true);

                    }

                }
            }

        );
        jScrollPane1.setViewportView(tablacontrato);

        txtid.setToolTipText("Codigo del Banco");
        txtid.setPlaceholder("ID");

        txtobligaciones.setToolTipText("Obligaciones");
        txtobligaciones.setPlaceholder("Obligaciones");

        btnnuevo.setBackground(new java.awt.Color(51, 51, 51));
        btnnuevo.setText("Nuevo");
        btnnuevo.setToolTipText("Nuevo Registro");
        btnnuevo.setColorHover(new java.awt.Color(153, 153, 153));
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setBackground(new java.awt.Color(0, 0, 204));
        btnguardar.setText("Guardar");
        btnguardar.setToolTipText("Guardar Registro");
        btnguardar.setColorHover(new java.awt.Color(51, 51, 255));
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnmodificar.setBackground(new java.awt.Color(255, 255, 51));
        btnmodificar.setForeground(new java.awt.Color(0, 0, 0));
        btnmodificar.setText("Modificar");
        btnmodificar.setToolTipText("Modificar Registro");
        btnmodificar.setColorHover(new java.awt.Color(255, 255, 102));
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(204, 0, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("Eliminar Registro");
        btnEliminar.setColorHover(new java.awt.Color(255, 51, 51));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtobligaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(txtobligaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        limpiartextbox();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmvistaobligaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmvistaobligaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmvistaobligaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmvistaobligaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmvistaobligaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.ButtonHover btnEliminar;
    private rojeru_san.complementos.ButtonHover btnguardar;
    private rojeru_san.complementos.ButtonHover btnmodificar;
    private rojeru_san.complementos.ButtonHover btnnuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.TableMetro tablacontrato;
    private app.bolivia.swing.JCTextField txtid;
    private app.bolivia.swing.JCTextField txtobligaciones;
    // End of variables declaration//GEN-END:variables
}
