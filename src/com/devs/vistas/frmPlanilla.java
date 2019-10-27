/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devs.vistas;

import com.devs.auxiliar.Conexion;
import dao.PlanillaDao;
import ds.desktop.notify.DesktopNotify;
import entities.Grupofamiliar;
import entities.Planillas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.joda.time.DateTime;
import org.joda.time.Weeks;
import rojerusan.RSNotifyFade;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author DiegoArevalo
 */
public class frmPlanilla extends javax.swing.JFrame {
    PlanillaDao pladao=new PlanillaDao();
    Planillas plaselect=null;
    DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    public frmPlanilla() {
         cargarColumnas();
         cargarModeloTabla();
        initComponents();
        popup();
        this.setResizable(false);
        DesControlesInicio();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(1);
        this.setTitle("Mantenimiento de Planillas");
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
      tablaplanilla.setComponentPopupMenu(popup);
  }
    
    
     private void DesControlesInicio(){
        btnEliminar.setEnabled(false);
        btnmodificar.setEnabled(false);
        //txtid.setEditable(false);
    }
      private void cargarModeloTabla() {
        limpiarTabla();

        List<Planillas> lista = pladao.listAll();//agregarlos registros a la lista
        int cantidadLista = lista.size();//cantidad de la lista
        modeloTabla.setRowCount(cantidadLista);//agregar la cantidad al modelo

        for (int i = 0; i < lista.size(); i++) {

            Planillas p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getDesde(), i, 1);
             modeloTabla.setValueAt(p.getHasta(), i, 2);
              modeloTabla.setValueAt(p.getStatus(), i, 3);
           
        }

    }
      
       public void buscar(){
        String parametro = this.txtbuscar.getText();
        List<Planillas> lista = pladao.listAllParameter(parametro);

        int numFila = lista.size();

        modeloTabla.setNumRows(numFila);

        for (int i = 0; i < lista.size(); i++) {
            Planillas p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getDesde(), i, 1);
             modeloTabla.setValueAt(p.getHasta(), i, 2);
              modeloTabla.setValueAt(p.getStatus(), i, 3);
            
        }
     } 
      
       
       public void guardar(){
           Date ddesde=null;
           Date dhasta=null;
    Planillas planilla=new Planillas();
        try {
            if(txtid.getText().length()==0){
                txtid.requestFocus();
                
                txtid.setToolTipText("Debe Ingresar una ID de la planilla");
                //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                new rojerusan.RSNotifyFade("Agregar", "Debes agregar ID", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
               // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
            }else{
                txtid.setToolTipText("ID");
                //Fecha
                  //Date ddesde;
            //Date dhasta;
            
            //probando calculo de numero de semana
//            DateTime dateTime1 = new DateTime((DateCell) ddesde);
//DateTime dateTime2 = new DateTime((DateCell) dhasta);
//
//int weeks = Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
            DateTime dateTime1 = new DateTime(ddesde);
DateTime dateTime2 = new DateTime(dhasta);

int weeks = Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
                System.out.println("numero de semana: "+weeks);
            
               try {
                Calendar cal;
                int d, m, a;
                //fecha ingreso
                cal = desded.getCalendar();
                d = cal.get(Calendar.DAY_OF_MONTH);
                m = cal.get(Calendar.MONTH);
                a = cal.get(Calendar.YEAR) - 1900;

                ddesde = new java.sql.Date(a, m, d);
                //fecha salida
                cal = hastad.getCalendar();
                d = cal.get(Calendar.DAY_OF_MONTH);
                m = cal.get(Calendar.MONTH);
                a = cal.get(Calendar.YEAR) - 1900;

                dhasta = new java.sql.Date(a, m, d);
            } catch (Exception e) {
                DesktopNotify.showDesktopMessage("Error", "Hay Errores en las fechas", DesktopNotify.ERROR);
                return;
            }
           
                
                planilla.setCodigoplanilla(txtid.getText());
                planilla.setDesde(ddesde);
                planilla.setHasta(dhasta);
                planilla.setStatus(txtstatus.getText());
                pladao.Save(planilla);
                cargarModeloTabla();
                limpiartextbox();
                new rojerusan.RSNotifyFade("Proceso Completo", "Registro Agregado exitosamente: ", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
                //JOptionPane.showMessageDialog(null,"Se ha Agregado Correctamente el Registro: "+cargos.getDescripcion() );
                
            }
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"No se pudo Agregar el Registro" );
             DesktopNotify.showDesktopMessage("Registros Incompletos", "No se pudo Agregar el Registro", DesktopNotify.ERROR);
            System.out.println("error: "+e.getMessage());
        }
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
         if(plaselect!=null){
            try {
                pladao.Delete(plaselect);
                cargarModeloTabla();
                new rojerusan.RSNotifyFade("Proceso Completado", "El Registro fue Eliminado Correctamente: "+plaselect.getCodigoplanilla(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                //JOptionPane.showMessageDialog(null,"El Registro fue Eliminado Correctamente: "+cargoselect.getDescripcion() );
                limpiartextbox();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null,"No se pudo eliminar el Registro" );
                DesktopNotify.showDesktopMessage("Error", "No se pudo eliminar el registro", DesktopNotify.ERROR);
                System.out.println("Error: "+e.getMessage());
            }

        }else{
            DesktopNotify.showDesktopMessage("Informe", "Debes seleccionar un Registro", DesktopNotify.INFORMATION);
        }
    }
    
     public void limpiartextbox(){
         txtid.setText("");
         txtstatus.setText("");
         txtid.setEditable(true);
         desded.setDate(null);
         hastad.setDate(null);
         plaselect=null;
         btnEliminar.setEnabled(false);
         btnmodificar.setEnabled(false);
         btnguardar.setEnabled(true);
     }
     private void cargarColumnas() {
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Desde");
         modeloTabla.addColumn("Hasta");
          modeloTabla.addColumn("Status");
    }
public void modificar() {
        if(plaselect!=null){
            try {
                
                    Date ddesde;
            Date dhasta;
               try {
                Calendar cal;
                int d, m, a;
                //fecha ingreso
                cal = desded.getCalendar();
                d = cal.get(Calendar.DAY_OF_MONTH);
                m = cal.get(Calendar.MONTH);
                a = cal.get(Calendar.YEAR) - 1900;

                ddesde = new java.sql.Date(a, m, d);
                //fecha salida
                cal = hastad.getCalendar();
                d = cal.get(Calendar.DAY_OF_MONTH);
                m = cal.get(Calendar.MONTH);
                a = cal.get(Calendar.YEAR) - 1900;

                dhasta = new java.sql.Date(a, m, d);
                
                   calcularnumsemana();
            } catch (Exception e) {
                DesktopNotify.showDesktopMessage("Error", "Hay Errores en las fechas", DesktopNotify.ERROR);
                return;
            }
              // plaselect.setCodigoplanilla(txtid.getText());
                plaselect.setStatus(txtstatus.getText());
                plaselect.setDesde(ddesde);
                plaselect.setHasta(dhasta);
                pladao.Update(plaselect);
                cargarModeloTabla();
                new rojerusan.RSNotifyFade("Proceso Completado", "Registro Modificado Correctamente a: "+plaselect.getCodigoplanilla(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
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
                    + "/src/reportes/reporteplanillas.jrxml");
            print = JasperFillManager.fillReport(report, p, cnn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte General de Planillas");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtstatus = new app.bolivia.swing.JCTextField();
        txtid = new app.bolivia.swing.JCTextField();
        txtbuscar = new app.bolivia.swing.JCTextField();
        btnnuevo = new rojeru_san.complementos.ButtonHover();
        btnguardar = new rojeru_san.complementos.ButtonHover();
        btnmodificar = new rojeru_san.complementos.ButtonHover();
        btnEliminar = new rojeru_san.complementos.ButtonHover();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaplanilla = new rojeru_san.complementos.TableMetro();
        desded = new com.toedter.calendar.JDateChooser();
        hastad = new com.toedter.calendar.JDateChooser();
        btnimprimir = new rojeru_san.complementos.ButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtstatus.setToolTipText("Ingrese la Descripcion");
        txtstatus.setPlaceholder("Status");
        txtstatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtstatusKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstatusKeyTyped(evt);
            }
        });

        txtid.setToolTipText("Codigo del Banco");
        txtid.setPlaceholder("ID");

        txtbuscar.setToolTipText("Buscar Registros");
        txtbuscar.setPlaceholder("Realice una Busqueda");
        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
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

        tablaplanilla.setModel(modeloTabla);
        tablaplanilla.setColorBackgoundHead(new java.awt.Color(0, 61, 113));
        tablaplanilla.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        //Este codigo se coloca en la tabla en su propiedad Post-Init-Code
        tablaplanilla.getSelectionModel().addListSelectionListener( // capturamos la linea seleccionada

            new ListSelectionListener(){ // Instanciamos

                public void valueChanged (ListSelectionEvent event){ // evento de la tabla
                    if(!event.getValueIsAdjusting() && (tablaplanilla.getSelectedRow()>=0) ) {
                        int filaSeleccionada = tablaplanilla.getSelectedRow(); // tomamos la fila seleccionda
                        /*creamos el obj y le pasamos la fila seleccionada y la columna 1 xq ayi 
                        esta alojado el obj marca en el campo nombre....
                        */     
                        plaselect = (Planillas) modeloTabla.getValueAt(filaSeleccionada,0);

                        // LLenamos los textBoxs atraves del objeto ...
                        txtid.setText(plaselect.getCodigoplanilla());
                        txtstatus.setText(plaselect.getStatus());

                        desded.setDate(plaselect.getDesde());
                        hastad.setDate(plaselect.getHasta());

                        //abilitar boton para actualizar
                        btnguardar.setEnabled(false);
                        btnmodificar.setEnabled(true);
                        btnEliminar.setEnabled(true);
                        txtid.setEditable(false);

                    }

                }
            }

        );
        jScrollPane1.setViewportView(tablaplanilla);

        desded.setForeground(new java.awt.Color(102, 102, 255));
        desded.setToolTipText("Periodo desde");
        desded.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                desdedPropertyChange(evt);
            }
        });

        hastad.setForeground(new java.awt.Color(102, 102, 255));
        hastad.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                hastadPropertyChange(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(desded, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hastad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtstatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(desded, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hastad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        desded.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtstatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstatusKeyPressed
        if(plaselect==null){
            if(evt.getKeyCode()==evt.VK_ENTER){
                guardar();
            }
        }else if(plaselect!=null){
            if(evt.getKeyCode()==evt.VK_ENTER){
                modificar();
            }
        }
    }//GEN-LAST:event_txtstatusKeyPressed

    private void txtstatusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstatusKeyTyped

    }//GEN-LAST:event_txtstatusKeyTyped

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

    public void calcularnumsemana(){
      Date date=new Date();
        Calendar calendar = Calendar.getInstance();
calendar.setFirstDayOfWeek( Calendar.MONDAY);
calendar.setMinimalDaysInFirstWeek( 4 );
calendar.setTime(date);
int numberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
int numer=numberWeekOfYear;
        System.out.println("quincena "+numer/2);
        
    }
    public void fecha(){
        Date fechaActual = new Date();
        System.out.println(fechaActual);
        System.out.println("---------------------------------------------");
        
        //Formateando la fecha:
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy");
        System.out.println("Son las: "+formatoHora.format(fechaActual)+" de fecha: "+formatoFecha.format(fechaActual));
        
        //Fecha actual desglosada:
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String siglayear=String.valueOf(año).substring(2, 4);
                

        System.out.println("Fecha Actual: "+ dia + "/" + (mes) + "/" + año);
        System.out.printf("Hora Actual: %02d:%02d:%02d %n", hora, minuto, segundo);
        System.out.println("-------------Fecha desglosada----------------");
        System.out.println("El año es: "+ año);
         System.out.println("El año es: "+ siglayear);
        System.out.println("El mes es: "+ mes);
        System.out.println("El día es: "+ dia);
        System.out.printf("La hora es: %02d %n", hora);
        System.out.printf("El minuto es: %02d %n", minuto);
        System.out.printf("El segundo es: %02d %n", segundo);
    }
    public Date sumarrestardias(Date fecha,int dias){
        Calendar calendar=null;
        calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }
    public Date sumarrestarmeses(Date fecha,int meses){
          Calendar calendar=null;
        calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(calendar.MONTH, meses);
        return calendar.getTime();
    }
    public Date sumarrestaraños(Date fecha,int años){
         Calendar calendar=null;
        calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(calendar.YEAR, años);
        return calendar.getTime();
    }
    public void edad(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate fechaNac = LocalDate.parse("04/10/1996", fmt);

LocalDate ahora = LocalDate.now();

Period periodo = Period.between(fechaNac, ahora);
System.out.printf("Tu edad es: %s años, %s meses y %s días",
                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
    }
    
    
    
    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
       limpiartextbox();
        //calcularnumsemana();
        //fecha();
        edad();
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

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void desdedPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_desdedPropertyChange
       
    }//GEN-LAST:event_desdedPropertyChange

    private void hastadPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_hastadPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_hastadPropertyChange

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
            java.util.logging.Logger.getLogger(frmPlanilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPlanilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPlanilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPlanilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPlanilla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.ButtonHover btnEliminar;
    private rojeru_san.complementos.ButtonHover btnguardar;
    private rojeru_san.complementos.ButtonHover btnimprimir;
    private rojeru_san.complementos.ButtonHover btnmodificar;
    private rojeru_san.complementos.ButtonHover btnnuevo;
    private com.toedter.calendar.JDateChooser desded;
    private com.toedter.calendar.JDateChooser hastad;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.TableMetro tablaplanilla;
    private app.bolivia.swing.JCTextField txtbuscar;
    private app.bolivia.swing.JCTextField txtid;
    private app.bolivia.swing.JCTextField txtstatus;
    // End of variables declaration//GEN-END:variables
}
