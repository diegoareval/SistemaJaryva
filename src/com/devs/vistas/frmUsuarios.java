/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devs.vistas;

import com.devs.auxiliar.Conexion;
import dao.UsuarioDao;
import ds.desktop.notify.DesktopNotify;
import entities.Usuarios;
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

/**
 *
 * @author DiegoArevalo
 */
public class frmUsuarios extends javax.swing.JFrame {
    UsuarioDao uDao=new UsuarioDao();
    Usuarios usuarioselect=new Usuarios();
     DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
     
    
    public frmUsuarios() {
         cargarColumnas();
        cargarModeloTabla();
        initComponents();
        DesControlesInicio();
       popup();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(1);
        this.setTitle("Mantenimiento de Usuarios");
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
public void eliminar(){
         if(usuarioselect!=null){
            try {
                uDao.Delete(usuarioselect);
                cargarModeloTabla();
 new rojerusan.RSNotifyFade("Proceso Completado", "El Registro fue Eliminado Correctamente: "+usuarioselect.getNombre(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                //JOptionPane.showMessageDialog(null,"El Registro fue Eliminado Correctamente: "+cargoselect.getDescripcion() );
                limpiartextbox();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null,"No se pudo eliminar el Registro" );
                 DesktopNotify.showDesktopMessage("Error", "No se pudo registrar", DesktopNotify.ERROR);
                System.out.println("Error: "+e.getMessage());
            }

        }else{
            DesktopNotify.showDesktopMessage("Informe", "Debes seleccionar un Registro", DesktopNotify.INFORMATION);
        }
  }
 public void limpiartextbox(){
         txtid.setText("");
         txtnmbre.setText("");
         usuarioselect=null;
         txtclave.setText("");
         txtusuario.setText("");
         popup();
         btnEliminar.setEnabled(false);
         btnmodificar.setEnabled(false);
         btnguardar.setEnabled(true);
     }

 
  public void guardar(){
    Usuarios usuario=new Usuarios();
        try {
            if(txtusuario.getText().length()==0){
                txtusuario.requestFocus();
                
                txtusuario.setToolTipText("Debe Ingresar un usuario");
                //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
               // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
            }
            else if(txtclave.getText().length()==0){
                txtclave.requestFocus();
                
                txtclave.setToolTipText("Debe Ingresar una clave");
                //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
               // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
            }
            else  if(txtnmbre.getText().length()==0){
                txtnmbre.requestFocus();
                
                txtnmbre.setToolTipText("Debe Ingresar un nombre completo");
                //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                new rojerusan.RSNotifyFade("Descripcion", "Debes agregar un nombre", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
               // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
            }
            else{
                txtusuario.setToolTipText("Usuario");
                
                
                usuario.setNombre(txtnmbre.getText());
                usuario.setClave(txtclave.getText());
                usuario.setUsuario(txtusuario.getText());
                usuario.setNivelAcceso(jComboBox1.getSelectedItem().toString());
                uDao.Save(usuario);
                cargarModeloTabla();
                limpiartextbox();
                new rojerusan.RSNotifyFade("Proceso Completo", "Registro Agregado exitosamente: "+usuario.getNombre(), 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                //JOptionPane.showMessageDialog(null,"Se ha Agregado Correctamente el Registro: "+cargos.getDescripcion() );
                
            }
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"No se pudo Agregar el Registro" );
             DesktopNotify.showDesktopMessage("Registros Incompletos", "No se pudo Agregar el Registro", DesktopNotify.ERROR);
            System.out.println("error: "+e.getMessage());
        }
}
  


 public void buscar(){
        String parametro = this.txtbuscar.getText();
        List<Usuarios> lista = uDao.listAllParameter(parametro);

        int numFila = lista.size();

        modeloTabla.setNumRows(numFila);

        for (int i = 0; i < lista.size(); i++) {
            Usuarios p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getNombre(), i, 3);
            modeloTabla.setValueAt(p.getClave(), i, 2);
            modeloTabla.setValueAt(p.getUsuario(), i, 1);
             modeloTabla.setValueAt(p.getNivelAcceso(), i, 4);
            
            
        }
     } 
 
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
      tablausuarios.setComponentPopupMenu(popup);
  }
    
 
 
 
 private void cargarColumnas() {
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Clave");
        modeloTabla.addColumn("Usuario");
        modeloTabla.addColumn("Nivel Acceso");
        
    }
    
    private void cargarModeloTabla() {
        limpiarTabla();

        List<Usuarios> lista = uDao.listAll();//agregarlos registros a la lista
        int cantidadLista = lista.size();//cantidad de la lista
        modeloTabla.setRowCount(cantidadLista);//agregar la cantidad al modelo

        for (int i = 0; i < lista.size(); i++) {

            Usuarios p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getNombre(), i, 3);
            modeloTabla.setValueAt(p.getClave(), i, 2);
            modeloTabla.setValueAt(p.getUsuario(), i, 1);
             modeloTabla.setValueAt(p.getNivelAcceso(), i, 4);
           
        }

    }
    
     public void modificar() {
        if(usuarioselect!=null){
            try {
                usuarioselect.setNombre(txtnmbre.getText());
                usuarioselect.setUsuario(txtusuario.getText());
                usuarioselect.setClave(txtclave.getText());
                usuarioselect.setNivelAcceso(jComboBox1.getSelectedItem().toString());
                uDao.Update(usuarioselect);
                cargarModeloTabla();
                new rojerusan.RSNotifyFade("Proceso Completado", "Registro Modificado Correctamente a: "+usuarioselect.getNombre(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
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
     public void imprimireporte(){
         Connection cnn = Conexion.getConnection();

        Map p = new HashMap(); // para parametros
        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/reportes/reporteusuarios.jrxml");
            print = JasperFillManager.fillReport(report, p, cnn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte General de Usuarios");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtid = new app.bolivia.swing.JCTextField();
        txtbuscar = new app.bolivia.swing.JCTextField();
        btnnuevo = new rojeru_san.complementos.ButtonHover();
        btnguardar = new rojeru_san.complementos.ButtonHover();
        btnmodificar = new rojeru_san.complementos.ButtonHover();
        btnEliminar = new rojeru_san.complementos.ButtonHover();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablausuarios = new rojeru_san.complementos.TableMetro();
        txtnmbre = new app.bolivia.swing.JCTextField();
        txtusuario = new app.bolivia.swing.JCTextField();
        txtclave = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnimprimir = new rojeru_san.complementos.ButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtid.setToolTipText("Codigo del Banco");
        txtid.setPlaceholder("ID");

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

        tablausuarios.setModel(modeloTabla);
        tablausuarios.setColorBackgoundHead(new java.awt.Color(0, 61, 113));
        tablausuarios.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        //Este codigo se coloca en la tabla en su propiedad Post-Init-Code
        tablausuarios.getSelectionModel().addListSelectionListener( // capturamos la linea seleccionada

            new ListSelectionListener(){ // Instanciamos

                public void valueChanged (ListSelectionEvent event){ // evento de la tabla
                    if(!event.getValueIsAdjusting() && (tablausuarios.getSelectedRow()>=0) ) {
                        int filaSeleccionada = tablausuarios.getSelectedRow(); // tomamos la fila seleccionda
                        /*creamos el obj y le pasamos la fila seleccionada y la columna 1 xq ayi 
                        esta alojado el obj marca en el campo nombre....
                        */     
                        usuarioselect = (Usuarios) modeloTabla.getValueAt(filaSeleccionada,0); 

                        // LLenamos los textBoxs atraves del objeto ...
                        txtid.setText(String.valueOf(usuarioselect.getCodigo()));
                        txtusuario.setText(usuarioselect.getUsuario());
                        txtclave.setText(usuarioselect.getClave());
                        txtnmbre.setText(usuarioselect.getNombre());
                        jComboBox1.setSelectedItem(usuarioselect.getNivelAcceso());

                        //abilitar boton para actualizar
                        btnguardar.setEnabled(false);
                        btnmodificar.setEnabled(true);
                        btnEliminar.setEnabled(true);

                    }

                }
            }

        );
        jScrollPane1.setViewportView(tablausuarios);

        txtnmbre.setToolTipText("Ingrese el nombre");
        txtnmbre.setPlaceholder("Nombre Completo");
        txtnmbre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnmbreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnmbreKeyTyped(evt);
            }
        });

        txtusuario.setToolTipText("Codigo del Banco");
        txtusuario.setPlaceholder("Usuario");

        txtclave.setToolTipText("Codigo del Banco");
        txtclave.setPlaceholder("Clave");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Nivel de Acceso");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Total", "Consulta", "Operador" }));

        btnimprimir.setBackground(new java.awt.Color(51, 51, 51));
        btnimprimir.setText("Imprimir");
        btnimprimir.setToolTipText("Nuevo Registro");
        btnimprimir.setColorHover(new java.awt.Color(153, 153, 153));
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtnmbre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36)
                                                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(45, 45, 45))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtnmbre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
       
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

    private void txtnmbreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnmbreKeyPressed
     if(usuarioselect==null){
            if(evt.getKeyCode()==evt.VK_ENTER){
                guardar();
            }
        }else if(usuarioselect!=null){
            if(evt.getKeyCode()==evt.VK_ENTER){
                modificar();
            }
        }
    }//GEN-LAST:event_txtnmbreKeyPressed

    private void txtnmbreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnmbreKeyTyped

    }//GEN-LAST:event_txtnmbreKeyTyped

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
            java.util.logging.Logger.getLogger(frmUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.ButtonHover btnEliminar;
    private rojeru_san.complementos.ButtonHover btnguardar;
    private rojeru_san.complementos.ButtonHover btnimprimir;
    private rojeru_san.complementos.ButtonHover btnmodificar;
    private rojeru_san.complementos.ButtonHover btnnuevo;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.TableMetro tablausuarios;
    private app.bolivia.swing.JCTextField txtbuscar;
    private app.bolivia.swing.JCTextField txtclave;
    private app.bolivia.swing.JCTextField txtid;
    private app.bolivia.swing.JCTextField txtnmbre;
    private app.bolivia.swing.JCTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
