
package paneles;


import com.mxrck.autocompleter.TextAutoCompleter;
import dao.EventoDao;
import dao.MovProgramacionDao;
import dao.PlanillaDao;
import dao.ProgramacionDao;
import dao.ProgramacionesDao;
import dao.SalonDao;
import dao.detalle_act_programacionesDao;
import ds.desktop.notify.DesktopNotify;
import entities.DetalleActProgramaciones;
import entities.Eventos;
import entities.Movimientoprogramacion;
import entities.Planillas;
import entities.Programacion;
import entities.Programaciones;
import entities.Salones;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;






public class mipanel extends javax.swing.JPanel {
//Combos Model
     DefaultComboBoxModel<Planillas> modeloplanilla = new DefaultComboBoxModel<Planillas>();
      DefaultComboBoxModel<Programacion> modeloprogramacion = new DefaultComboBoxModel<Programacion>();
      DefaultComboBoxModel<Salones> modelosalones = new DefaultComboBoxModel<Salones>();
      
      //******unoficial object model 
       DefaultComboBoxModel<Eventos> modeloactividades1 = new DefaultComboBoxModel<Eventos>();
       DefaultComboBoxModel<Eventos> modeloactividades2 = new DefaultComboBoxModel<Eventos>();
       DefaultComboBoxModel<Eventos> modeloactividades3 = new DefaultComboBoxModel<Eventos>();
       DefaultComboBoxModel<Eventos> modeloactividades4 = new DefaultComboBoxModel<Eventos>();
       DefaultComboBoxModel<Eventos> modeloactividades6 = new DefaultComboBoxModel<Eventos>();
       DefaultComboBoxModel<Eventos> modeloactividades7 = new DefaultComboBoxModel<Eventos>();
       DefaultComboBoxModel<Eventos> modeloactividades8 = new DefaultComboBoxModel<Eventos>();
       DefaultComboBoxModel<Eventos> modeloactividades9 = new DefaultComboBoxModel<Eventos>();
       //variable actividades
       EventoDao evDao=new EventoDao();
       Eventos evento=new Eventos();
       Eventos eventoseleccionado=null;
      
      //variables planilla
    PlanillaDao plDao=new PlanillaDao();
    Planillas planilla=new Planillas();
    Planillas planillaseleccionada=null;
    
    Planillas plSelect =null;
    
    //variables programacion
    ProgramacionDao prDao=new ProgramacionDao();
    Programacion programacion=new Programacion();
    Programacion programacionseleccionada=null;
    
    //variables salones
    SalonDao salDao=new SalonDao();
    Salones salon=new Salones();
    Salones salonseleccionado=null;
    
    Salones Salonselect=null;
    
    //movimiento programacion
    MovProgramacionDao mvprDao=new MovProgramacionDao();
    
    //programaciones
    ProgramacionesDao programacionesDao=new ProgramacionesDao();
    Programaciones programaciones=null;
    
    //autocompletar campos
    private TextAutoCompleter autocompletebuscador;
    
    //variables detalleactividadesprogramaciones
    DetalleActProgramaciones actividad=new DetalleActProgramaciones();
    detalle_act_programacionesDao daPrDao=new detalle_act_programacionesDao();
    
    public mipanel() {
        cargarModeloPlanilla();
        cargarModeloProgramacion();
        cargarModelosalones();
        //cagando actividades
        cargarModeloActividades(modeloactividades1);
        cargarModeloActividades(modeloactividades2);
        cargarModeloActividades(modeloactividades3);
        cargarModeloActividades(modeloactividades4);
        cargarModeloActividades(modeloactividades6);
        cargarModeloActividades(modeloactividades7);
        cargarModeloActividades(modeloactividades8);
        cargarModeloActividades(modeloactividades9);
       
        initComponents();
         autocomplete();
    }
    // <editor-fold defaultstate="collapsed" desc="Actividades">
     //cargar modelo actividad
      private void cargarModeloActividades(DefaultComboBoxModel modelo) {
 
        
        // crea una List para recibir ls clases
        List<Eventos> lista = evDao.listAll();

        for (Eventos c : lista) {
            modelo.addElement(c);
        }

        modelo.setSelectedItem(null);

        if (eventoseleccionado != null) {
            for (Eventos c1 : lista) {

                if (eventoseleccionado.getIdevento() == c1.getIdevento()) {
                    modelo.setSelectedItem(c1.toString());

                }
            }

        }
    }
      
      
    
     
    
       // </editor-fold> 
    
    //Cargar modelos
    //cargar modelo planilla
     private void cargarModeloPlanilla() {

        limpiarModelos(modeloplanilla); // limpiamos el modelo por las dudas
        // crea una List para recibir ls clases
        List<Planillas> lista = plDao.listAll();

        for (Planillas c : lista) {
            modeloplanilla.addElement(c);
        }

        modeloplanilla.setSelectedItem(null);

        if (planillaseleccionada != null) {
            for (Planillas c1 : lista) {

                if (planillaseleccionada.getCodigoplanilla() == c1.getCodigoplanilla()) {
                    modeloplanilla.setSelectedItem(c1.toString());

                }
            }

        }
    }
     //cargar modelo programacion
      private void cargarModeloProgramacion() {

        limpiarModelos(modeloprogramacion); // limpiamos el modelo por las dudas
        // crea una List para recibir ls clases
        List<Programacion> lista = prDao.listAll();

        for (Programacion c : lista) {
            modeloprogramacion.addElement(c);
        }

        modeloprogramacion.setSelectedItem(null);

        if (programacionseleccionada != null) {
            for (Programacion c1 : lista) {

                if (programacionseleccionada.getCodProgramacion() == c1.getCodProgramacion()) {
                    modeloprogramacion.setSelectedItem(c1.toString());

                }
            }

        }
    }
      //Cargar modelo salones
       private void cargarModelosalones() {

        limpiarModelos(modelosalones); // limpiamos el modelo por las dudas
        // crea una List para recibir ls clases
        List<Salones> lista = salDao.listAll();

        for (Salones c : lista) {
            modelosalones.addElement(c);
        }

        modelosalones.setSelectedItem(null);

        if (salonseleccionado != null) {
            for (Salones c1 : lista) {

                if (salonseleccionado.getIdsalon() == c1.getIdsalon()) {
                    modelosalones.setSelectedItem(c1.toString());

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
     
     //autocompletar programacion
      public void autocomplete() {
        //autocomplete=new TextAutoCompleter(codjaryva);
        autocompletebuscador = new TextAutoCompleter(txtprogramacion);
        List<Movimientoprogramacion> lista = mvprDao.listAll();
        if (lista.size() > 0) {
            for (Movimientoprogramacion mov : lista) {
                //utocomplete.addItem(cats.getCodJaryva());
                autocompletebuscador.addItem(mov.getId());
            }

        }
    }
      //guardar registros
      public void guardar(){
          Programaciones progras=new Programaciones();
           Date fecha=null;
          if(validarguardado()){
              try {
                  
                   progras.setCapitan(txtcapitan.getText());
                   progras.setDescripcion(txtdescripcion.getText());
                   progras.setPax(txtpax.getText());
                   progras.setEntrada(txtentrada.getText());
                   progras.setFecha(validarFecha());
                   progras.setPlanillas((Planillas)jcplanilla.getSelectedItem());
                   progras.setSalones((Salones)jcsalon.getSelectedItem());
                   progras.setProgramacion((Programacion)jctipoprogramacion.getSelectedItem());
                 
                   int idmovimiento=Integer.parseInt(txtprogramacion.getText());
                   if(getmovimiento(idmovimiento)!=null){
                       progras.setMovimientoprogramacion(getmovimiento(idmovimiento));
                   }else{
                       System.out.println("no se encontro registro de movimientos");
                       return;
                   }
                   Programaciones prog=new Programaciones();
                   prog=progras;
                   programacionesDao.Save(progras);
                   SaveActividades(prog);
                   limpiarCampos();
                    DesktopNotify.showDesktopMessage("Exito", "Registro Agregado exitosamente",DesktopNotify.SUCCESS);
                   
                   
     
                  
              } catch (Exception e) {
                   DesktopNotify.showDesktopMessage("Error", "No se pudo agregar el registro",DesktopNotify.ERROR);
                  System.out.println("error: "+e.getMessage());
              }
              
          }      
      }
      //buscar movimiento
      public Movimientoprogramacion getmovimiento(int id){
          Movimientoprogramacion mov=null;
           List<Movimientoprogramacion> lista = mvprDao.Buscar(id);
           if (lista.size() > 0) {
              for (Movimientoprogramacion movimiento : lista) {
                mov=movimiento;
                return mov;
            }  
           }
           else{
               mov=null;
           }
           return mov;
      }
      
      //validar fecha
      public Date validarFecha(){
         Date fecha=null; 
          //validar fecha
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
                      fecha=null;
                       System.out.println("error: "+ e.getMessage());
                  }
                   return fecha;
      }
      
      public boolean validarguardado(){
    Boolean respuesta=false;
     if(jctipoprogramacion.getSelectedItem()==null){
              DesktopNotify.showDesktopMessage("Info", "Seleccione una programacion");
          }
          else if(jcplanilla.getSelectedItem()==null){
              DesktopNotify.showDesktopMessage("Info", "Seleccione una planilla");
   
          }else if(txtprogramacion.getText().isEmpty()){
              DesktopNotify.showDesktopMessage("Info", "Datos obligatorios");

          }else if(jcfecha.getDate()==null){
              DesktopNotify.showDesktopMessage("Info", "Seleccione una fecha");

          }
              if(txtprogramacion.getText().isEmpty()){
              DesktopNotify.showDesktopMessage("Info", "Datos obligatorios");

          }else  if(txtdescripcion.getText().isEmpty()){
              DesktopNotify.showDesktopMessage("Info", "Datos obligatorios");

          }else if(jcsalon.getSelectedItem()==null){
              DesktopNotify.showDesktopMessage("Info", "Seleccione un Salon");
   
          }else  if(txtpax.getText().isEmpty()){
              DesktopNotify.showDesktopMessage("Info", "Datos obligatorios");
   
          }else  if(txtentrada.getText().isEmpty()){
              DesktopNotify.showDesktopMessage("Info", "Datos obligatorios");

          }else  if(txtcapitan.getText().isEmpty()){
              DesktopNotify.showDesktopMessage("Info", "Datos obligatorios");

          } 
          else{
              
               respuesta=true;
              
          }
    return respuesta;
}
      //guardar actividades
      public void SaveActividades(Programaciones programaciones){
          if(jcactividad1.getSelectedItem()!=null){
              try {
                  
                  actividad.setEventos((Eventos)jcactividad1.getSelectedItem());
        
                  actividad.setProgramaciones(programaciones);
                  daPrDao.Save(actividad);
                  DesktopNotify.showDesktopMessage("Info", "Registro de actividades agregado exitosamente",DesktopNotify.SUCCESS);
              } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "no se pudo agregar el registro",DesktopNotify.ERROR);
                  System.out.println("error: "+e.getMessage());
              }
              
          }
          if(jcactividad2.getSelectedItem()!=null){
               try {
                  
                  actividad.setEventos((Eventos)jcactividad2.getSelectedItem());
        
                  actividad.setProgramaciones(programaciones);
                  daPrDao.Save(actividad);
                  DesktopNotify.showDesktopMessage("Info", "Registro de actividades 2 agregado exitosamente",DesktopNotify.SUCCESS);
              } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "no se pudo agregar el registro 2",DesktopNotify.ERROR);
                  System.out.println("error: "+e.getMessage());
              }  
      }if(jcactividad3.getSelectedItem()!=null){
               try {
                  
                  actividad.setEventos((Eventos)jcactividad3.getSelectedItem());
        
                  actividad.setProgramaciones(programaciones);
                  daPrDao.Save(actividad);
                  DesktopNotify.showDesktopMessage("Info", "Registro de actividades 3 agregado exitosamente",DesktopNotify.SUCCESS);
              } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "no se pudo agregar el registro 3",DesktopNotify.ERROR);
                  System.out.println("error: "+e.getMessage());
              }  
      }
      if(jcactividad4.getSelectedItem()!=null){
               try {
                  
                  actividad.setEventos((Eventos)jcactividad4.getSelectedItem());
        
                  actividad.setProgramaciones(programaciones);
                  daPrDao.Save(actividad);
                  DesktopNotify.showDesktopMessage("Info", "Registro de actividades 4 agregado exitosamente",DesktopNotify.SUCCESS);
              } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "no se pudo agregar el registro 4",DesktopNotify.ERROR);
                  System.out.println("error: "+e.getMessage());
              }  
      }
      if(jcactividad6.getSelectedItem()!=null){
               try {
                  
                  actividad.setEventos((Eventos)jcactividad6.getSelectedItem());
        
                  actividad.setProgramaciones(programaciones);
                  daPrDao.Save(actividad);
                  DesktopNotify.showDesktopMessage("Info", "Registro de actividades 6 agregado exitosamente",DesktopNotify.SUCCESS);
              } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "no se pudo agregar el registro 6",DesktopNotify.ERROR);
                  System.out.println("error: "+e.getMessage());
              }  
      }if(jcactividad7.getSelectedItem()!=null){
               try {
                  
                  actividad.setEventos((Eventos)jcactividad7.getSelectedItem());
        
                  actividad.setProgramaciones(programaciones);
                  daPrDao.Save(actividad);
                  DesktopNotify.showDesktopMessage("Info", "Registro de actividades 7 agregado exitosamente",DesktopNotify.SUCCESS);
              } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "no se pudo agregar el registro 7",DesktopNotify.ERROR);
                  System.out.println("error: "+e.getMessage());
              }  
      }if(jcactividad8.getSelectedItem()!=null){
               try {
                  
                  actividad.setEventos((Eventos)jcactividad8.getSelectedItem());
        
                  actividad.setProgramaciones(programaciones);
                  daPrDao.Save(actividad);
                  DesktopNotify.showDesktopMessage("Info", "Registro de actividades 8 agregado exitosamente",DesktopNotify.SUCCESS);
              } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "no se pudo agregar el registro 8",DesktopNotify.ERROR);
                  System.out.println("error: "+e.getMessage());
              }  
      }if(jcactividad9.getSelectedItem()!=null){
               try {
                  
                  actividad.setEventos((Eventos)jcactividad9.getSelectedItem());
        
                  actividad.setProgramaciones(programaciones);
                  daPrDao.Save(actividad);
                  DesktopNotify.showDesktopMessage("Info", "Registro de actividades 9 agregado exitosamente",DesktopNotify.SUCCESS);
              } catch (Exception e) {
                  DesktopNotify.showDesktopMessage("Error", "no se pudo agregar el registro 9",DesktopNotify.ERROR);
                  System.out.println("error: "+e.getMessage());
              }  
      }
        
      }
      
      public void limpiarCampos(){
          jcactividad1.setSelectedItem(null);
          jcactividad2.setSelectedItem(null);
          jcactividad3.setSelectedItem(null);
          jcactividad4.setSelectedItem(null);
          jcactividad6.setSelectedItem(null);
          jcactividad7.setSelectedItem(null);
          jcactividad8.setSelectedItem(null);
           jcactividad9.setSelectedItem(null);
           jcplanilla.setSelectedItem(null);
           jctipoprogramacion.setSelectedItem(null);
           jcsalon.setSelectedItem(null);
           txtpax.setText("");
           txtcapitan.setText("");
           txtdescripcion.setText("");
           txtentrada.setText("");
           txtidplanilla.setText("");
           txtsalon.setText("");
           txtprecioactividad1.setText("");
           txtprecioactividad2.setText("");
           txtprecioactividad3.setText("");
           txtprecioactividad4.setText("");
           txtprecioactividad6.setText("");
           txtprecioactividad7.setText("");
           txtprecioactividad8.setText("");
           txtprecioactividad9.setText("");
           jcfecha.setDate(null);
           
      }
      
      public void findSalonbyId(String cod){
          List<Salones>lista = salDao.findbyId(cod);
          if(lista.size()>0){
              for (Salones salones : lista) {
                  Salonselect=salones;
              }
             modelosalones.setSelectedItem(Salonselect);
          }else{
              DesktopNotify.showDesktopMessage("info", "No existe el registro con ese codigo");
          }
          
      }
      public void findPlanillabyId(String cod){
           List<Planillas>lista = plDao.findbyId(cod);
          if(lista.size()>0){
              for (Planillas planillas : lista) {
                  plSelect=planillas;
              }
             modeloplanilla.setSelectedItem(plSelect);
          }else{
              DesktopNotify.showDesktopMessage("info", "No existe el registro con ese codigo");
          }
      }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jLabel3 = new javax.swing.JLabel();
        jctipoprogramacion = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtidplanilla = new app.bolivia.swing.JCTextField();
        jcplanilla = new javax.swing.JComboBox<>();
        txtprogramacion = new app.bolivia.swing.JCTextField();
        jcfecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtdescripcion = new app.bolivia.swing.JCTextField();
        txtsalon = new app.bolivia.swing.JCTextField();
        jcsalon = new javax.swing.JComboBox<>();
        txtpax = new app.bolivia.swing.JCTextField();
        txtentrada = new app.bolivia.swing.JCTextField();
        txtcapitan = new app.bolivia.swing.JCTextField();
        jLabel2 = new javax.swing.JLabel();
        btnguardar = new rojeru_san.complementos.ButtonHover();
        jcactividad1 = new javax.swing.JComboBox<>();
        jcactividad2 = new javax.swing.JComboBox<>();
        jcactividad3 = new javax.swing.JComboBox<>();
        jcactividad4 = new javax.swing.JComboBox<>();
        jcactividad6 = new javax.swing.JComboBox<>();
        jcactividad7 = new javax.swing.JComboBox<>();
        jcactividad8 = new javax.swing.JComboBox<>();
        jcactividad9 = new javax.swing.JComboBox<>();
        txtprecioactividad1 = new app.bolivia.swing.JCTextField();
        txtprecioactividad3 = new app.bolivia.swing.JCTextField();
        txtprecioactividad4 = new app.bolivia.swing.JCTextField();
        txtprecioactividad2 = new app.bolivia.swing.JCTextField();
        txtprecioactividad6 = new app.bolivia.swing.JCTextField();
        txtprecioactividad8 = new app.bolivia.swing.JCTextField();
        txtprecioactividad9 = new app.bolivia.swing.JCTextField();
        txtprecioactividad7 = new app.bolivia.swing.JCTextField();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("jMenu3");
        jMenuBar1.add(jMenu3);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Programaciones");

        jctipoprogramacion.setModel(modeloprogramacion);

        jLabel1.setText("Tipo Programacion:");

        txtidplanilla.setToolTipText("Codigo de Planilla");
        txtidplanilla.setPlaceholder("ID Planilla");
        txtidplanilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtidplanillaMousePressed(evt);
            }
        });
        txtidplanilla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidplanillaKeyPressed(evt);
            }
        });

        jcplanilla.setModel(modeloplanilla);

        txtprogramacion.setToolTipText("Codigo de Planilla");
        txtprogramacion.setPlaceholder("Programacion");

        jcfecha.setForeground(new java.awt.Color(102, 102, 255));
        jcfecha.setToolTipText("Periodo desde");
        jcfecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jcfechaPropertyChange(evt);
            }
        });

        jLabel4.setText("Fecha:");

        txtdescripcion.setToolTipText("Codigo de Planilla");
        txtdescripcion.setPlaceholder("Descripcion");

        txtsalon.setToolTipText("Codigo de Planilla");
        txtsalon.setPlaceholder("Id Salon");
        txtsalon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsalonKeyPressed(evt);
            }
        });

        jcsalon.setModel(modelosalones);

        txtpax.setToolTipText("Codigo de Planilla");
        txtpax.setPlaceholder("Pax");

        txtentrada.setToolTipText("Codigo de Planilla");
        txtentrada.setPlaceholder("Entrada");

        txtcapitan.setToolTipText("Codigo de Planilla");
        txtcapitan.setPlaceholder("Capitan");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Actividades");

        btnguardar.setBackground(new java.awt.Color(0, 0, 204));
        btnguardar.setText("Guardar");
        btnguardar.setToolTipText("Guardar Registro");
        btnguardar.setColorHover(new java.awt.Color(51, 51, 255));
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        jcactividad1.setModel(modeloactividades1);
        jcactividad1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcactividad1ItemStateChanged(evt);
            }
        });

        jcactividad2.setModel(modeloactividades2);
        jcactividad2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcactividad2ItemStateChanged(evt);
            }
        });

        jcactividad3.setModel(modeloactividades3);
        jcactividad3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcactividad3ItemStateChanged(evt);
            }
        });

        jcactividad4.setModel(modeloactividades4);
        jcactividad4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcactividad4ItemStateChanged(evt);
            }
        });

        jcactividad6.setModel(modeloactividades6);
        jcactividad6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcactividad6ItemStateChanged(evt);
            }
        });

        jcactividad7.setModel(modeloactividades7);
        jcactividad7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcactividad7ItemStateChanged(evt);
            }
        });

        jcactividad8.setModel(modeloactividades8);
        jcactividad8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcactividad8ItemStateChanged(evt);
            }
        });

        jcactividad9.setModel(modeloactividades9);
        jcactividad9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcactividad9ItemStateChanged(evt);
            }
        });

        txtprecioactividad1.setToolTipText("Codigo del Banco");
        txtprecioactividad1.setPlaceholder("Precio");

        txtprecioactividad3.setToolTipText("Codigo del Banco");
        txtprecioactividad3.setPlaceholder("Precio");

        txtprecioactividad4.setToolTipText("Codigo del Banco");
        txtprecioactividad4.setPlaceholder("Precio");

        txtprecioactividad2.setToolTipText("Codigo del Banco");
        txtprecioactividad2.setPlaceholder("Precio");

        txtprecioactividad6.setToolTipText("Codigo del Banco");
        txtprecioactividad6.setPlaceholder("Precio");

        txtprecioactividad8.setToolTipText("Codigo del Banco");
        txtprecioactividad8.setPlaceholder("Precio");

        txtprecioactividad9.setToolTipText("Codigo del Banco");
        txtprecioactividad9.setPlaceholder("Precio");

        txtprecioactividad7.setToolTipText("Codigo del Banco");
        txtprecioactividad7.setPlaceholder("Precio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtprogramacion, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1)
                                            .addComponent(txtsalon, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(jcsalon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(jcfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jctipoprogramacion, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jcactividad1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36)
                                                .addComponent(txtprecioactividad1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jcactividad6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jcactividad2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(36, 36, 36)
                                                        .addComponent(txtprecioactividad2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jcactividad4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jcactividad3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(36, 36, 36)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(txtprecioactividad3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtprecioactividad4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtprecioactividad9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtprecioactividad8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtprecioactividad7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtprecioactividad6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jcactividad7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jcactividad8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(jcactividad9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(95, 95, 95)))))))
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtidplanilla, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jcplanilla, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(txtpax, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(txtentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtcapitan, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jctipoprogramacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidplanilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcplanilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtprogramacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jcfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsalon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcsalon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(txtcapitan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcactividad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcactividad6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecioactividad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecioactividad6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcactividad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtprecioactividad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcactividad7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtprecioactividad7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcactividad3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecioactividad3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcactividad8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecioactividad8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcactividad4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecioactividad4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcactividad9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecioactividad9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcfechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jcfechaPropertyChange

    }//GEN-LAST:event_jcfechaPropertyChange

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
       guardar();
      
     
          
    }//GEN-LAST:event_btnguardarActionPerformed

    private void txtsalonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsalonKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            findSalonbyId(txtsalon.getText());    
        }
    }//GEN-LAST:event_txtsalonKeyPressed

    private void txtidplanillaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtidplanillaMousePressed
        
    }//GEN-LAST:event_txtidplanillaMousePressed

    private void txtidplanillaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidplanillaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            findPlanillabyId(txtidplanilla.getText());    
        }
    }//GEN-LAST:event_txtidplanillaKeyPressed

    private void jcactividad1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcactividad1ItemStateChanged
        Eventos ev=(Eventos)jcactividad1.getSelectedItem();
        txtprecioactividad1.setText(ev.getPrecioA().toString());
    }//GEN-LAST:event_jcactividad1ItemStateChanged

    private void jcactividad2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcactividad2ItemStateChanged
         Eventos ev=(Eventos)jcactividad2.getSelectedItem();
        txtprecioactividad2.setText(ev.getPrecioA().toString());
    }//GEN-LAST:event_jcactividad2ItemStateChanged

    private void jcactividad3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcactividad3ItemStateChanged
        Eventos ev=(Eventos)jcactividad3.getSelectedItem();
        txtprecioactividad3.setText(ev.getPrecioA().toString());
    }//GEN-LAST:event_jcactividad3ItemStateChanged

    private void jcactividad4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcactividad4ItemStateChanged
        Eventos ev=(Eventos)jcactividad4.getSelectedItem();
        txtprecioactividad4.setText(ev.getPrecioA().toString());
    }//GEN-LAST:event_jcactividad4ItemStateChanged

    private void jcactividad6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcactividad6ItemStateChanged
        Eventos ev=(Eventos)jcactividad6.getSelectedItem();
        txtprecioactividad6.setText(ev.getPrecioA().toString());
    }//GEN-LAST:event_jcactividad6ItemStateChanged

    private void jcactividad7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcactividad7ItemStateChanged
        Eventos ev=(Eventos)jcactividad7.getSelectedItem();
        txtprecioactividad7.setText(ev.getPrecioA().toString());
    }//GEN-LAST:event_jcactividad7ItemStateChanged

    private void jcactividad8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcactividad8ItemStateChanged
         Eventos ev=(Eventos)jcactividad8.getSelectedItem();
        txtprecioactividad8.setText(ev.getPrecioA().toString());
    }//GEN-LAST:event_jcactividad8ItemStateChanged

    private void jcactividad9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcactividad9ItemStateChanged
        Eventos ev=(Eventos)jcactividad9.getSelectedItem();
        txtprecioactividad9.setText(ev.getPrecioA().toString());
    }//GEN-LAST:event_jcactividad9ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.ButtonHover btnguardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JComboBox<Eventos> jcactividad1;
    private javax.swing.JComboBox<Eventos> jcactividad2;
    private javax.swing.JComboBox<Eventos> jcactividad3;
    private javax.swing.JComboBox<Eventos> jcactividad4;
    private javax.swing.JComboBox<Eventos> jcactividad6;
    private javax.swing.JComboBox<Eventos> jcactividad7;
    private javax.swing.JComboBox<Eventos> jcactividad8;
    private javax.swing.JComboBox<Eventos> jcactividad9;
    private com.toedter.calendar.JDateChooser jcfecha;
    private javax.swing.JComboBox<Planillas> jcplanilla;
    private javax.swing.JComboBox<Salones> jcsalon;
    private javax.swing.JComboBox<Programacion> jctipoprogramacion;
    private app.bolivia.swing.JCTextField txtcapitan;
    private app.bolivia.swing.JCTextField txtdescripcion;
    private app.bolivia.swing.JCTextField txtentrada;
    private app.bolivia.swing.JCTextField txtidplanilla;
    private app.bolivia.swing.JCTextField txtpax;
    private app.bolivia.swing.JCTextField txtprecioactividad1;
    private app.bolivia.swing.JCTextField txtprecioactividad2;
    private app.bolivia.swing.JCTextField txtprecioactividad3;
    private app.bolivia.swing.JCTextField txtprecioactividad4;
    private app.bolivia.swing.JCTextField txtprecioactividad6;
    private app.bolivia.swing.JCTextField txtprecioactividad7;
    private app.bolivia.swing.JCTextField txtprecioactividad8;
    private app.bolivia.swing.JCTextField txtprecioactividad9;
    private app.bolivia.swing.JCTextField txtprogramacion;
    private app.bolivia.swing.JCTextField txtsalon;
    // End of variables declaration//GEN-END:variables
}
