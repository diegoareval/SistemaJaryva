
package paneles;

import dao.CargoDao;
import dao.DetalleMovProgramacionDao;
import dao.MovProgramacionDao;
import dao.PersonalDao;
import dao.PlanillaDao;
import ds.desktop.notify.DesktopNotify;
import entities.Cargos;
import entities.CatPersonal;
import entities.DetalleMovimientoProgramacion;
import entities.Movimientoprogramacion;
import entities.Planillas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.*;

public class pnlWeb extends javax.swing.JPanel {
    // <editor-fold defaultstate="collapsed" desc="Instancias">
    //instancias a usar de planillas
    PlanillaDao plDao=new PlanillaDao();
    Planillas planilla=new Planillas();
    DefaultComboBoxModel<Planillas> modelocombo = new DefaultComboBoxModel<Planillas>();
    Planillas planillaseleccionada=null;
    
    //instancia de empleados
    CatPersonal personal=new CatPersonal();
    PersonalDao perDao=new PersonalDao();
    DefaultComboBoxModel<CatPersonal> modeloper = new DefaultComboBoxModel<CatPersonal>();
    CatPersonal catper=null;
    
    
    
    //instancia de actividades
    Cargos car=new Cargos();
    CargoDao carDao=new CargoDao();
    DefaultComboBoxModel<Cargos> modelocargo = new DefaultComboBoxModel<Cargos>();
    Cargos carg=null;
    
    
    //instancia de movimientos
    Movimientoprogramacion mprog=new Movimientoprogramacion();
    MovProgramacionDao mprDao=new MovProgramacionDao();
    DefaultComboBoxModel<Movimientoprogramacion> modelomovimiento = new DefaultComboBoxModel<Movimientoprogramacion>();
    Movimientoprogramacion movimiento=null;
    
    //instancia detalle movimiento Programacion
    DetalleMovProgramacionDao dmvDao=new DetalleMovProgramacionDao();
    DetalleMovimientoProgramacion dmv=null;
    DetalleMovimientoProgramacion detallem=null;
    
    //Modelo tabla
    DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    // </editor-fold>
    
  
    public pnlWeb() {
        cargarColumnas();
        cargarModeloTabla();
        cargarModeloSegmento();
        cargarmodelopersonal();
        cargarmodelocargos();
        cargarmodelomovimiento();
        
        initComponents();
        desactivarcomponentes();
        popup();
        
        
    }
     // <editor-fold defaultstate="collapsed" desc="Logica">
    //popup
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
          guardarmovimiento();
                 
              
          }
      }
      );
      
      
      popup.add(item);
      popup.add(item2);
      tablaevento.setComponentPopupMenu(popup);
  }
    //metodo eliminar
    public void eliminar(){
        if(detallem!=null){
            dmvDao.Delete(detallem);
            cargarModeloTabla();
             DesktopNotify.showDesktopMessage("Informacion", "Registro eliminado correctamente",DesktopNotify.SUCCESS);
        }else{
            DesktopNotify.showDesktopMessage("Informacion", "Debes seleccionar un registro",DesktopNotify.INFORMATION);
        }
    }
    
    //cargar modelo tabla
     private void cargarModeloTabla() {
        limpiarTabla();

        List<DetalleMovimientoProgramacion> lista = dmvDao.listAll();//agregarlos registros a la lista
        int cantidadLista = lista.size();//cantidad de la lista
        modeloTabla.setRowCount(cantidadLista);//agregar la cantidad al modelo

        for (int i = 0; i < lista.size(); i++) {

            DetalleMovimientoProgramacion p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getFecha(), i, 1);
            modeloTabla.setValueAt(p.getCatPersonal().getNombres(), i, 2);
            modeloTabla.setValueAt(p.getCargos(), i, 3);
            modeloTabla.setValueAt(p.getHoras(), i, 4);
            modeloTabla.setValueAt(p.getPrecio(), i, 5);
            modeloTabla.setValueAt(p.getValor(), i, 6);

        }

    }
    
    
    //cargar columnas de la tabla
     private void cargarColumnas() {
        modeloTabla.addColumn("Guia");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Empleado");
        modeloTabla.addColumn("Actividad");
        modeloTabla.addColumn("Horas");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Valor");

    }
     
     //limpiar modelo tabla
     private void limpiarTabla() {
        int numFila = modeloTabla.getRowCount(); // cantidad de filas de la tabla
        if (numFila > 0) {
            // debe de ser i mayor o igual a cero
            for (int i = numFila - 1; i >= 0; i--) { // recorre de mayor a menor
                modeloTabla.removeRow(i); // borra la fila encontrada en la iteracion
            }
        }
    }
    //cargar modelo cargos
    private void cargarmodelocargos(){
         limpiarModelos(modelocargo);
         List<Cargos> lista = carDao.listAll();
          for (Cargos c : lista) {
            modelocargo.addElement(c);
        }

        modelocargo.setSelectedItem(null);

        if (carg != null) {
            for (Cargos c1 : lista) {

                if (carg.getCodCargo() == c1.getCodCargo()) {
                    modelocargo.setSelectedItem(c1.toString());

                }
            }

        }
    }
    
    //cargar modelo movimiento programacion
     private void cargarmodelomovimiento(){
         limpiarModelos(modelomovimiento);
         List<Movimientoprogramacion> lista = mprDao.listAll();
          for (Movimientoprogramacion c : lista) {
            modelomovimiento.addElement(c);
        }

        modelomovimiento.setSelectedItem(null);

        if (movimiento != null) {
            for (Movimientoprogramacion c1 : lista) {

                if (movimiento.getId() == c1.getId()) {
                    modelomovimiento.setSelectedItem(c1.toString());

                }
            }

        }
    }
    
    
    //cargar modelo personal
    private void cargarmodelopersonal(){
         limpiarModelos(modeloper);
         List<CatPersonal> lista = perDao.listAll();
          for (CatPersonal c : lista) {
            modeloper.addElement(c);
        }

        modeloper.setSelectedItem(null);

        if (catper != null) {
            for (CatPersonal c1 : lista) {

                if (catper.getCodPersonal() == c1.getCodPersonal()) {
                    modeloper.setSelectedItem(c1.toString());

                }
            }

        }
    }
    
    
     private void cargarModeloSegmento() {

        limpiarModelos(modelocombo); // limpiamos el modelo por las dudas
        // crea una List para recibir ls clases
        List<Planillas> lista = plDao.listAll();

        for (Planillas c : lista) {
            modelocombo.addElement(c);
        }

        modelocombo.setSelectedItem(null);

        if (planillaseleccionada != null) {
            for (Planillas c1 : lista) {

                if (planillaseleccionada.getCodigoplanilla() == c1.getCodigoplanilla()) {
                    modelocombo.setSelectedItem(c1.toString());

                }
            }

        }
    }
     //limpiar modelo de combos
     private void limpiarModelos(DefaultComboBoxModel modelo) {
        int numFila = modelo.getSize(); // cantidad de filas
        if (numFila > 0) {
            // debe de ser i mayor o igual a cero
            for (int i = numFila - 1; i >= 0; i--) { // recorre de mayor a menor
                modelo.removeElementAt(i); // borra la fila
            }
        }
    }
     
     //metodo guardar
     public void guardar(){
         if(jcplanilla.getSelectedItem()==null){
             DesktopNotify.showDesktopMessage("Informacion", "Debes seleccionar una planilla",DesktopNotify.INFORMATION);
         }else{
             try {
                 Movimientoprogramacion mov=new Movimientoprogramacion();
                 mov.setPlanillas((Planillas)jcplanilla.getSelectedItem());
                 mprDao.Save(mov);
                 cargarmodelomovimiento();
                DesktopNotify.showDesktopMessage("Exito", "El registro se ha ingresado correctamente",DesktopNotify.SUCCESS);
                
                 
             } catch (Exception e) {
                 System.out.println("Eror: "+e.getMessage());
                 DesktopNotify.showDesktopMessage("Informacion", "No se pudo crear el movimiento",DesktopNotify.ERROR);
             }
         }
         
     }
//desactivar componentes
     public void desactivarcomponentes(){
         txtid.setEditable(false);
         txtguia.setEditable(false);
         jcempleado.setEditable(true);
         AutoCompleteDecorator.decorate(this.jcempleado);
         AutoCompleteDecorator.decorate(this.jcactividades);
         jcactividades.setEditable(true);
         txtvalor.setEditable(false);
     }
     //agregar movimientos
     public void guardarmovimiento(){
         if(detallem==null){
             if(validar()){
                 addmovimiento();  
             }else{
                  DesktopNotify.showDesktopMessage("Info", "Debes llenar todos los campos",DesktopNotify.INFORMATION);
             }
             
         }else{
             if(validar()){
                 updatemovimiento(); 
             }else{
                 DesktopNotify.showDesktopMessage("Info", "Debes llenar todos los campos",DesktopNotify.INFORMATION);
             }
            
         }
     }
     //agregar movimiento 
     public void addmovimiento(){
          BigDecimal precio=null;
           Double horas=null;
            Date fecha=null;
         try {
             try {
                  horas=Double.parseDouble(String.valueOf(txthora.getText()));
             } catch (Exception e) {
                 DesktopNotify.showDesktopMessage("Error", "Ingrese hora valida",DesktopNotify.ERROR);
             }
             try {
                
                  precio=BigDecimal.valueOf(Double.parseDouble(String.valueOf(txtprecio.getText())));
             } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "Ingrese precio valida",DesktopNotify.ERROR);
             }
             
             try {
                 Calendar cal;
                int d, m, a;
                //fecha ingreso
                cal = jcfecha.getCalendar();
                d = cal.get(Calendar.DAY_OF_MONTH);
                m = cal.get(Calendar.MONTH);
                a = cal.get(Calendar.YEAR) - 1900;

                fecha = new java.sql.Date(a, m, d);
             } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "Ingrese fecha valida",DesktopNotify.ERROR);
             }
             
            Double valore=Double.parseDouble(precio.toString())*horas;
            BigDecimal valvigint=BigDecimal.valueOf(Double.parseDouble(String.valueOf(valore)));
             
             DetalleMovimientoProgramacion det=new DetalleMovimientoProgramacion();
             det.setCargos((Cargos)jcactividades.getSelectedItem());
             det.setCatPersonal((CatPersonal)jcempleado.getSelectedItem());
             det.setMovimientoprogramacion((Movimientoprogramacion)jcmovimiento.getSelectedItem());
             det.setPrecio(precio);
             det.setHoras(horas);
             det.setFecha(fecha);
             det.setValor(valvigint);
             dmvDao.Save(det);
             cargarModeloTabla();
             DesktopNotify.showDesktopMessage("Exito", "Registro Ingresado correctamente");
             
             
         } catch (Exception e)
         {
             DesktopNotify.showDesktopMessage("Error", "No se pudo agregar el registro",DesktopNotify.ERROR);
             System.out.println("error: "+e.getMessage());
         }
         
     }
     //actualizar movimiento
     public void updatemovimiento(){ 
          BigDecimal precio=null;
           Double horas=null;
            Date fecha=null;
            
            
         try {
             try {
                  horas=Double.parseDouble(String.valueOf(txthora.getText()));
             } catch (Exception e) {
                 DesktopNotify.showDesktopMessage("Error", "Ingrese hora valida",DesktopNotify.ERROR);
             }
             try {
                
                  precio=BigDecimal.valueOf(Double.parseDouble(String.valueOf(txtprecio.getText())));
             } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "Ingrese precio valida",DesktopNotify.ERROR);
             }
             
             try {
                 Calendar cal;
                int d, m, a;
                //fecha ingreso
                cal = jcfecha.getCalendar();
                d = cal.get(Calendar.DAY_OF_MONTH);
                m = cal.get(Calendar.MONTH);
                a = cal.get(Calendar.YEAR) - 1900;

                fecha = new java.sql.Date(a, m, d);
             } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "Ingrese fecha valida",DesktopNotify.ERROR);
             }
             
            Double valore=Double.parseDouble(precio.toString())*horas;
            BigDecimal valvigint=BigDecimal.valueOf(Double.parseDouble(String.valueOf(valore)));
             
            
             detallem.setCargos((Cargos)jcactividades.getSelectedItem());
             detallem.setCatPersonal((CatPersonal)jcempleado.getSelectedItem());
             detallem.setMovimientoprogramacion((Movimientoprogramacion)jcmovimiento.getSelectedItem());
             detallem.setPrecio(precio);
             detallem.setHoras(horas);
             detallem.setFecha(fecha);
             detallem.setValor(valvigint);
             dmvDao.Update(detallem);
             cargarModeloTabla();
             DesktopNotify.showDesktopMessage("Exito", "Registro Modificado correctamente correctamente");
             
             
         } catch (Exception e)
         {
             DesktopNotify.showDesktopMessage("Error", "No se pudo agregar el registro",DesktopNotify.ERROR);
             System.out.println("error: "+e.getMessage());
         }
            
            
      }
      Boolean valido=false;
     public Boolean validar(){
        
         if(jcactividades.getSelectedItem()==null){
             DesktopNotify.showDesktopMessage("Info", "Debes elegir una actividad",DesktopNotify.INFORMATION);
         }
         if(jcfecha.getDate()==null){
              DesktopNotify.showDesktopMessage("Info", "Debes ingresar una fecha",DesktopNotify.INFORMATION);
         }
         if(jcempleado.getSelectedItem()==null){
              DesktopNotify.showDesktopMessage("Info", "Debes elegir un empleado",DesktopNotify.INFORMATION);
         }
         if(jcmovimiento.getSelectedItem()==null){
            DesktopNotify.showDesktopMessage("Info", "Debes elegir seleccionar un movimiento",DesktopNotify.INFORMATION);  
         }
         if(txthora.getText().isEmpty()){
              DesktopNotify.showDesktopMessage("Info", "Debes ingresar horas",DesktopNotify.INFORMATION);
         }
         if(txtprecio.getText().isEmpty()){
              DesktopNotify.showDesktopMessage("Info", "Debes ingresar precio",DesktopNotify.INFORMATION);
         }
         valido=true;
         return valido;
     }
     
     
     public void limpiartextbox(){
         txtguia.setText("");
         txthora.setText("");
         txtvalor.setText("");
         txtprecio.setText("");
         jcfecha.setDate(null);
         jcmovimiento.setSelectedItem(null);
         jcempleado.setSelectedItem(null);
         jcactividades.setSelectedItem(null);
         detallem=null;
     }
     
      public void buscar() {
        String parametro = this.txtbuscar.getText();
        List<DetalleMovimientoProgramacion> lista = dmvDao.listAllParameter(parametro);

        int numFila = lista.size();

        modeloTabla.setNumRows(numFila);

        for (int i = 0; i < lista.size(); i++) {
            DetalleMovimientoProgramacion p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getFecha(), i, 1);
            modeloTabla.setValueAt(p.getCatPersonal().getNombres(), i, 2);
            modeloTabla.setValueAt(p.getCargos(), i, 3);
            modeloTabla.setValueAt(p.getHoras(), i, 4);
            modeloTabla.setValueAt(p.getPrecio(), i, 5);
            modeloTabla.setValueAt(p.getValor(), i, 6);

        }
    }
     // </editor-fold> 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        txtid = new app.bolivia.swing.JCTextField();
        jLabel1 = new javax.swing.JLabel();
        jcplanilla = new javax.swing.JComboBox<>();
        btnagregarmovimientos = new rojeru_san.complementos.ButtonHover();
        txtguia = new app.bolivia.swing.JCTextField();
        jLabel2 = new javax.swing.JLabel();
        jcempleado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jcfecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jcactividades = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jcmovimiento = new javax.swing.JComboBox<>();
        txthora = new app.bolivia.swing.JCTextField();
        txtprecio = new app.bolivia.swing.JCTextField();
        txtvalor = new app.bolivia.swing.JCTextField();
        btnok = new rojeru_san.complementos.ButtonHover();
        btnmovimientos = new rojeru_san.complementos.ButtonHover();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaevento = new rojeru_san.complementos.TableMetro();
        btnnuevo = new rojeru_san.complementos.ButtonHover();
        txtbuscar = new app.bolivia.swing.JCTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(38, 86, 186));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Movimiento Programacion");

        txtid.setToolTipText("Codigo del Banco");
        txtid.setPlaceholder("ID");

        jLabel1.setText("Planilla:");

        jcplanilla.setModel(modelocombo);

        btnagregarmovimientos.setBackground(new java.awt.Color(0, 0, 204));
        btnagregarmovimientos.setText("Agregar Movimiento");
        btnagregarmovimientos.setToolTipText("Guardar Registro");
        btnagregarmovimientos.setColorHover(new java.awt.Color(51, 51, 255));
        btnagregarmovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarmovimientosActionPerformed(evt);
            }
        });

        txtguia.setToolTipText("Guia");
        txtguia.setPlaceholder("Guia");

        jLabel2.setText("Gestion de Movimiento de Programaciones");

        jcempleado.setModel(modeloper);

        jLabel3.setText("Emp:");

        jcfecha.setForeground(new java.awt.Color(102, 102, 255));
        jcfecha.setToolTipText("Periodo desde");
        jcfecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jcfechaPropertyChange(evt);
            }
        });

        jLabel4.setText("Fecha:");

        jcactividades.setModel(modelocargo);

        jLabel6.setText("Actividades:");

        jLabel7.setText("Movimiento:");

        jcmovimiento.setModel(modelomovimiento);

        txthora.setToolTipText("Ingrese las Horas");
        txthora.setPlaceholder("Horas");
        txthora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txthoraKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthoraKeyTyped(evt);
            }
        });

        txtprecio.setToolTipText("Ingrese las Horas");
        txtprecio.setPlaceholder("Precio");
        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprecioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });

        txtvalor.setToolTipText("Ingrese las Horas");
        txtvalor.setPlaceholder("Valor");
        txtvalor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtvalorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtvalorKeyTyped(evt);
            }
        });

        btnok.setBackground(new java.awt.Color(0, 0, 204));
        btnok.setText("OK?");
        btnok.setToolTipText("Guardar Registro");
        btnok.setColorHover(new java.awt.Color(51, 51, 255));
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });

        btnmovimientos.setBackground(new java.awt.Color(0, 0, 204));
        btnmovimientos.setText("Movimientos");
        btnmovimientos.setToolTipText("Guardar Registro");
        btnmovimientos.setColorHover(new java.awt.Color(51, 51, 255));
        btnmovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmovimientosActionPerformed(evt);
            }
        });

        tablaevento.setModel(modeloTabla);
        tablaevento.setColorBackgoundHead(new java.awt.Color(0, 61, 113));
        tablaevento.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        //Este codigo se coloca en la tabla en su propiedad Post-Init-Code
        tablaevento.getSelectionModel().addListSelectionListener( // capturamos la linea seleccionada

            new ListSelectionListener(){ // Instanciamos

                public void valueChanged (ListSelectionEvent event){ // evento de la tabla
                    if(!event.getValueIsAdjusting() && (tablaevento.getSelectedRow()>=0) ) {
                        int filaSeleccionada = tablaevento.getSelectedRow(); // tomamos la fila seleccionda
                        /*creamos el obj y le pasamos la fila seleccionada y la columna 1 xq ayi 
                        esta alojado el obj marca en el campo nombre....
                        */     
                        detallem = (DetalleMovimientoProgramacion) modeloTabla.getValueAt(filaSeleccionada,0);

                        // LLenamos los textBoxs atraves del objeto ...
                        txtguia.setText(detallem.getId().toString());
                        jcempleado.setSelectedItem((CatPersonal)detallem.getCatPersonal());
                        jcmovimiento.setSelectedItem((Movimientoprogramacion)detallem.getMovimientoprogramacion());

                        jcfecha.setDate(detallem.getFecha());
                        jcactividades.setSelectedItem((Cargos)detallem.getCargos());
                        txtprecio.setText(detallem.getPrecio().toString());
                        txthora.setText(String.valueOf(detallem.getHoras()));
                        txtvalor.setText(detallem.getValor().toString());

                        //abilitar boton para actualizar
                        btnok.setEnabled(true);
                        btnmovimientos.setEnabled(false);
                        btnagregarmovimientos.setEnabled(true);

                        txtid.setEditable(false);

                    }

                }
            }

        );
        jScrollPane1.setViewportView(tablaevento);

        btnnuevo.setBackground(new java.awt.Color(0, 204, 204));
        btnnuevo.setText("Nuevo");
        btnnuevo.setToolTipText("Nuevo Registro");
        btnnuevo.setColorHover(new java.awt.Color(102, 255, 255));
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txtguia, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jcempleado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jcplanilla, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnagregarmovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)
                                        .addComponent(btnmovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                            .addComponent(jcfecha, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jcactividades, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(35, 35, 35)
                                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(270, 270, 270)
                                .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnok, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(291, 291, 291)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jcplanilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnagregarmovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtguia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(jcfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcactividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jcmovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarmovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarmovimientosActionPerformed
        guardar();
    }//GEN-LAST:event_btnagregarmovimientosActionPerformed

    private void jcfechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jcfechaPropertyChange

    }//GEN-LAST:event_jcfechaPropertyChange

    private void txthoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthoraKeyPressed

    }//GEN-LAST:event_txthoraKeyPressed

    private void txthoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthoraKeyTyped

    }//GEN-LAST:event_txthoraKeyTyped

    private void txtprecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioKeyPressed

    private void txtprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyTyped
       
       
                
    }//GEN-LAST:event_txtprecioKeyTyped

    private void txtvalorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorKeyPressed

    private void txtvalorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorKeyTyped

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
        guardarmovimiento();
    }//GEN-LAST:event_btnokActionPerformed

    private void btnmovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmovimientosActionPerformed
        
    }//GEN-LAST:event_btnmovimientosActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        limpiartextbox();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        limpiarTabla();
        buscar();
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        if (txtbuscar.getText().length() >= 2) {
            limpiarTabla();
            buscar();
        }
    }//GEN-LAST:event_txtbuscarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.ButtonHover btnagregarmovimientos;
    private rojeru_san.complementos.ButtonHover btnmovimientos;
    private rojeru_san.complementos.ButtonHover btnnuevo;
    private rojeru_san.complementos.ButtonHover btnok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<Cargos> jcactividades;
    private javax.swing.JComboBox<CatPersonal> jcempleado;
    private com.toedter.calendar.JDateChooser jcfecha;
    private javax.swing.JComboBox<Movimientoprogramacion> jcmovimiento;
    private javax.swing.JComboBox<Planillas> jcplanilla;
    private rojeru_san.complementos.TableMetro tablaevento;
    private app.bolivia.swing.JCTextField txtbuscar;
    private app.bolivia.swing.JCTextField txtguia;
    private app.bolivia.swing.JCTextField txthora;
    private app.bolivia.swing.JCTextField txtid;
    private app.bolivia.swing.JCTextField txtprecio;
    private app.bolivia.swing.JCTextField txtvalor;
    // End of variables declaration//GEN-END:variables
}
