
package com.devs.vistas;

import com.devs.auxiliar.Conexion;
import dao.ProgramacionDao;
import ds.desktop.notify.DesktopNotify;
import entities.Programacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import rojerusan.RSNotifyFade;


public class frmProgramacion extends javax.swing.JFrame {
    ProgramacionDao progDao=new ProgramacionDao();
 DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
 Programacion progselect=null;
    
    public frmProgramacion() {
        cargarColumnas();
        cargarModeloTabla();
        initComponents();
        this.setResizable(false);
        popup();
        DesControlesInicio();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(1);
        this.setTitle("Mantenimiento de Programaciones");
    }
 private void DesControlesInicio(){
        btnEliminar.setEnabled(false);
        btnmodificar.setEnabled(false);
        txtid.setEditable(false);
    }
 private void limpiarTabla() {
        int numFila = modeloTabla.getRowCount(); // cantidad de filas de la tabla
        if (numFila > 0) {
            // debe de ser i mayor o igual a cero
            for (int i = numFila - 1; i >= 0; i--) { // recorre de mayor a menor
                modeloTabla.removeRow(i); // borra la fila encontrada en la iteracion
            }
        }
    }
  private void cargarColumnas() {
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Decripcion"); 
    }
  private void cargarModeloTabla() {
        limpiarTabla();

        List<Programacion> lista = progDao.listAll();//agregarlos registros a la lista
        int cantidadLista = lista.size();//cantidad de la lista
        modeloTabla.setRowCount(cantidadLista);//agregar la cantidad al modelo

        for (int i = 0; i < lista.size(); i++) {

            Programacion p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getDescripcion(), i, 1);
           
        }

    }
  
  //popup o ventana emergente
     public void popup(){
      JPopupMenu popup=new JPopupMenu();
      popup.setBorderPainted(true);
     
      JMenuItem item=new JMenuItem("Eliminar");
      
      JMenuItem item2=new JMenuItem("Modificar");
      
      item.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              eliminar();
              
          }
      }
      );
      item2.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              modificar();
              
          }
      }
      );
      
      
      popup.add(item);
      popup.add(item2);
      tablaprog.setComponentPopupMenu(popup);
  }
     public void eliminar(){
         if(progselect!=null){
            try {
                progDao.Delete(progselect);
                cargarModeloTabla();
                new rojerusan.RSNotifyFade("Proceso Completado", "El Registro fue Eliminado Correctamente: "+progselect.getDescripcion(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                //JOptionPane.showMessageDialog(null,"El Registro fue Eliminado Correctamente: "+cargoselect.getDescripcion() );
                limpiartextbox();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null,"No se pudo eliminar el Registro" );
                DesktopNotify.showDesktopMessage("Error", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
                System.out.println("Error: "+e.getMessage());
            }

        }else{
            DesktopNotify.showDesktopMessage("Informe", "Debes seleccionar un Registro", DesktopNotify.INFORMATION);
        }
     }

  
  public void limpiartextbox(){
         txtid.setText("");
         txtdescripcion.setText("");
         progselect=null;
         btnEliminar.setEnabled(false);
         btnmodificar.setEnabled(false);
         btnguardar.setEnabled(true);
     }
   public void guardar(){
    Programacion prog=new Programacion();
        try {
            if(txtdescripcion.getText().length()==0){
                txtdescripcion.requestFocus();
                
                txtdescripcion.setToolTipText("Debe Ingresar una Descripcion del cargos");
                //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                //new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.BottomLeft, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
            }else{
                txtdescripcion.setToolTipText("Descripcion");
                
                prog.setDescripcion(txtdescripcion.getText());
                progDao.Save(prog);
                cargarModeloTabla();
                limpiartextbox();
                new rojerusan.RSNotifyFade("Proceso Completo", "Registro Agregado exitosamente: "+prog.getDescripcion(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                //JOptionPane.showMessageDialog(null,"Se ha Agregado Correctamente el Registro: "+cargos.getDescripcion() );
                
            }
            
        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("Registros Incompletos", "No se pudo Agregar el Registro", DesktopNotify.ERROR);
            System.out.println("error: "+e.getMessage());
        }
}
   public void modificar() {
        if(progselect!=null){
            try {
                progselect.setDescripcion(txtdescripcion.getText());
                progDao.Update(progselect);
                cargarModeloTabla();
                new rojerusan.RSNotifyFade("Proceso Completado", "Registro Modificado Correctamente a: "+progselect.getDescripcion(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                //JOptionPane.showMessageDialog(null,"El Registro fue Modificado Correctamente a: "+cargoselect.getDescripcion() );
                limpiartextbox();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null,"No se pudo modificar el Registro" );
                 DesktopNotify.showDesktopMessage("Error", "No se pudo Modificar el Registro", DesktopNotify.ERROR);
                System.out.println("Error: "+e.getMessage());
            }
            
        }else{
            //JOptionPane.showMessageDialog(null,"Debe de Seleccionar un Registro" );
            DesktopNotify.showDesktopMessage("Informe", "Debes seleccionar un Registro", DesktopNotify.INFORMATION);
        }
    }
   
    public void buscar(){
        String parametro = this.txtbuscar.getText();
        List<Programacion> lista = progDao.listAllParameter(parametro);

        int numFila = lista.size();

        modeloTabla.setNumRows(numFila);

        for (int i = 0; i < lista.size(); i++) {
            Programacion p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getDescripcion(), i, 1);
            
        }
     }   
  
  public void imprimireporte(){
         Connection cnn = Conexion.getConnection();

        Map p = new HashMap(); // para parametros
        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/reportes/reporteprogramacion.jrxml");
            print = JasperFillManager.fillReport(report, p, cnn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte General de Programacion");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
  
  
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnnuevo = new rojeru_san.complementos.ButtonHover();
        txtid = new app.bolivia.swing.JCTextField();
        txtdescripcion = new app.bolivia.swing.JCTextField();
        btnguardar = new rojeru_san.complementos.ButtonHover();
        btnmodificar = new rojeru_san.complementos.ButtonHover();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaprog = new rojeru_san.complementos.TableMetro();
        txtbuscar = new app.bolivia.swing.JCTextField();
        btnEliminar = new rojeru_san.complementos.ButtonHover();
        btnimprimir = new rojeru_san.complementos.ButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnnuevo.setBackground(new java.awt.Color(0, 204, 204));
        btnnuevo.setText("Nuevo");
        btnnuevo.setToolTipText("Nuevo Registro");
        btnnuevo.setColorHover(new java.awt.Color(102, 255, 255));
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        txtid.setToolTipText("Codigo del Banco");
        txtid.setPlaceholder("ID");

        txtdescripcion.setToolTipText("Ingrese la Descripcion");
        txtdescripcion.setPlaceholder("Descripcion");
        txtdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyTyped(evt);
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

        tablaprog.setModel(modeloTabla);
        tablaprog.setColorBackgoundHead(new java.awt.Color(0, 61, 113));
        tablaprog.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        //Este codigo se coloca en la tabla en su propiedad Post-Init-Code
        tablaprog.getSelectionModel().addListSelectionListener( // capturamos la linea seleccionada

            new ListSelectionListener(){ // Instanciamos

                public void valueChanged (ListSelectionEvent event){ // evento de la tabla
                    if(!event.getValueIsAdjusting() && (tablaprog.getSelectedRow()>=0) ) {
                        int filaSeleccionada = tablaprog.getSelectedRow(); // tomamos la fila seleccionda
                        /*creamos el obj y le pasamos la fila seleccionada y la columna 1 xq ayi 
                        esta alojado el obj marca en el campo nombre....
                        */     
                        progselect = (Programacion) modeloTabla.getValueAt(filaSeleccionada,0); 

                        // LLenamos los textBoxs atraves del objeto ...
                        txtid.setText(progselect.getCodProgramacion().toString());
                        txtdescripcion.setText(progselect.getDescripcion());

                        //abilitar boton para actualizar
                        btnguardar.setEnabled(false);
                        btnmodificar.setEnabled(true);
                        btnEliminar.setEnabled(true);

                    }

                }
            }

        );
        jScrollPane1.setViewportView(tablaprog);

        txtbuscar.setToolTipText("Buscar Registros");
        txtbuscar.setPlaceholder("Realice una Busqueda");
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarKeyTyped(evt);
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

        btnimprimir.setBackground(new java.awt.Color(0, 204, 204));
        btnimprimir.setText("Imprimir");
        btnimprimir.setToolTipText("Nuevo Registro");
        btnimprimir.setColorHover(new java.awt.Color(102, 255, 255));
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        limpiartextbox();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtdescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyPressed
        if(progselect==null){
            if(evt.getKeyCode()==evt.VK_ENTER){
                guardar();
            }
        }else if(progselect!=null){
            if(evt.getKeyCode()==evt.VK_ENTER){
                modificar();
            }
        }
    }//GEN-LAST:event_txtdescripcionKeyPressed

    private void txtdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyTyped

    }//GEN-LAST:event_txtdescripcionKeyTyped

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        limpiarTabla();
        buscar();
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        if(txtbuscar.getText().length()>=2){
            limpiarTabla();
            buscar();
        }
    }//GEN-LAST:event_txtbuscarKeyTyped

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
      imprimireporte();
    }//GEN-LAST:event_btnimprimirActionPerformed

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
            java.util.logging.Logger.getLogger(frmProgramacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProgramacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProgramacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProgramacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmProgramacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.ButtonHover btnEliminar;
    private rojeru_san.complementos.ButtonHover btnguardar;
    private rojeru_san.complementos.ButtonHover btnimprimir;
    private rojeru_san.complementos.ButtonHover btnmodificar;
    private rojeru_san.complementos.ButtonHover btnnuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.TableMetro tablaprog;
    private app.bolivia.swing.JCTextField txtbuscar;
    private app.bolivia.swing.JCTextField txtdescripcion;
    private app.bolivia.swing.JCTextField txtid;
    // End of variables declaration//GEN-END:variables
}
