package com.devs.vistas;


import com.devs.auxiliar.Conexion;
import dao.EventoDao;
import dao.ProgramacionDao;
import ds.desktop.notify.DesktopNotify;
import entities.Eventos;
import entities.Programacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
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

public class frmEventos extends javax.swing.JFrame {

    EventoDao evDao = new EventoDao();
    Programacion progg = new Programacion();
    ProgramacionDao progDao = new ProgramacionDao();
    DefaultComboBoxModel<Programacion> modelocombo = new DefaultComboBoxModel<Programacion>();
    Eventos eveselect = null;
    Programacion progSelecionada = null;
    DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public frmEventos() {
        cargarColumnas();
        cargarModeloTabla();
        cargarModeloSegmento();
        initComponents();
        popup();
        this.setResizable(false);
        DesControlesInicio();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(1);
        this.setTitle("Mantenimiento de Eventos o Actividades");
    }

    private void DesControlesInicio() {
        btnEliminar.setEnabled(false);
        btnmodificar.setEnabled(false);
        txtid.setEditable(false);
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
      tablaevento.setComponentPopupMenu(popup);
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
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Precio A");
        modeloTabla.addColumn("Precio B");
        modeloTabla.addColumn("Precio C");
        modeloTabla.addColumn("Tipo Programacion");

    }

    private void cargarModeloTabla() {
        limpiarTabla();

        List<Eventos> lista = evDao.listAll();//agregarlos registros a la lista
        int cantidadLista = lista.size();//cantidad de la lista
        modeloTabla.setRowCount(cantidadLista);//agregar la cantidad al modelo

        for (int i = 0; i < lista.size(); i++) {

            Eventos p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getDescripcion(), i, 1);
            modeloTabla.setValueAt(p.getPrecioA(), i, 2);
            modeloTabla.setValueAt(p.getPrecioB(), i, 3);
            modeloTabla.setValueAt(p.getPrecioC(), i, 4);
            modeloTabla.setValueAt(p.getProgramacion().getDescripcion(), i, 5);

        }

    }
    //metodo eliminar
    public void eliminar(){
          if (eveselect != null) {
            try {
                evDao.Delete(eveselect);
                cargarModeloTabla();
                DesktopNotify.showDesktopMessage("Exito", "Registro Eliminado Correctamente " + eveselect.getDescripcion(), DesktopNotify.SUCCESS);
                //new rojerusan.RSNotifyFade("Proceso Completado", "El Registro fue Eliminado Correctamente: "+eveselect.getDescripcion(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                //JOptionPane.showMessageDialog(null,"El Registro fue Eliminado Correctamente: "+cargoselect.getDescripcion() );
                limpiartextbox();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null,"No se pudo eliminar el Registro" );
                DesktopNotify.showDesktopMessage("Error", "No se pudo Eliminar el Registro", DesktopNotify.ERROR);
                System.out.println("Error: " + e.getMessage());
            }

        } else {
            DesktopNotify.showDesktopMessage("Informe", "Debes seleccionar un Registro", DesktopNotify.INFORMATION);
        }
    }

    public void limpiartextbox() {
        txtid.setText("");
        txtdescripcion.setText("");
        precioa.setText("");
        preciob.setText("");
        precioc.setText("");
        eveselect = null;
        btnEliminar.setEnabled(false);
        btnmodificar.setEnabled(false);
        btnguardar.setEnabled(true);
    }

    public void buscar() {
        String parametro = this.txtbuscar.getText();
        List<Eventos> lista = evDao.listAllParameter(parametro);

        int numFila = lista.size();

        modeloTabla.setNumRows(numFila);

        for (int i = 0; i < lista.size(); i++) {
            Eventos p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getDescripcion(), i, 1);
            modeloTabla.setValueAt(p.getPrecioA(), i, 2);
            modeloTabla.setValueAt(p.getPrecioB(), i, 3);
            modeloTabla.setValueAt(p.getPrecioC(), i, 4);
            modeloTabla.setValueAt(p.getProgramacion().getDescripcion(), i, 5);

        }
    }

    private void limpiarModelos(DefaultComboBoxModel modelo) {
        int numFila = modelo.getSize(); // cantidad de filas
        if (numFila > 0) {
            // debe de ser i mayor o igual a cero
            for (int i = numFila - 1; i >= 0; i--) { // recorre de mayor a menor
                modelo.removeElementAt(i); // borra la fila
            }
        }
    }

    private void cargarModeloSegmento() {

        limpiarModelos(modelocombo); // limpiamos el modelo por las dudas
        // crea una List para recibir ls clases
        List<Programacion> lista = progDao.listAll();

        for (Programacion c : lista) {
            modelocombo.addElement(c);
        }

        modelocombo.setSelectedItem(null);

        if (progSelecionada != null) {
            for (Programacion c1 : lista) {

                if (progSelecionada.getCodProgramacion() == c1.getCodProgramacion()) {
                    modelocombo.setSelectedItem(c1.toString());

                }
            }

        }
    }

    public void guardar() {
        Eventos evento = new Eventos();

        try {
            if (this.modelocombo.getSelectedItem() != null) {
                Programacion dato = (Programacion)this.modelocombo.getSelectedItem();
                

               

                    
                        if (txtdescripcion.getText().length() == 0) {
                            txtdescripcion.requestFocus();

                            txtdescripcion.setToolTipText("Debe Ingresar una Descripcion del cargos");
                            //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                            new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                            // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
                        }
                        if (precioa.getText().length() == 0) {
                            precioa.requestFocus();

                            //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                            new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                            // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
                        }
                        if (preciob.getText().length() == 0) {
                            preciob.requestFocus();

                            //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                            new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                            // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
                        }
                        if (precioc.getText().length() == 0) {
                            precioc.requestFocus();

                            //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                            new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                            // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
                        }
                        Double vprecioa = 0.0, vpreciob = 0.0, vprecioc = 0.0;
                        try {

                            vprecioa = Double.parseDouble(precioa.getText());
                        } catch (Exception e) {
                            DesktopNotify.showDesktopMessage("Numero Incorrecto", "El Precio A debe ser Numerico", DesktopNotify.ERROR);
                        }
                        try {

                            vpreciob = Double.parseDouble(preciob.getText());
                        } catch (Exception e) {
                            DesktopNotify.showDesktopMessage("Numero Incorrecto", "El Precio B debe ser Numerico", DesktopNotify.ERROR);
                        }
                        try {

                            vprecioc = Double.parseDouble(precioc.getText());
                        } catch (Exception e) {
                            DesktopNotify.showDesktopMessage("Numero Incorrecto", "El Precio c debe ser Numerico", DesktopNotify.ERROR);
                        }

                        evento.setDescripcion(txtdescripcion.getText());
                        evento.setPrecioA(Double.parseDouble(precioa.getText()));
                        evento.setPrecioB(Double.parseDouble(preciob.getText()));
                        evento.setPrecioC(Double.parseDouble(precioc.getText()));
                        evento.setProgramacion(dato);
                        //codprogra=c.getCodProgramacion();
                        evDao.Save(evento);
                        cargarModeloTabla();
                        DesktopNotify.showDesktopMessage("Exito", "Registro Ingresado Correctamente", DesktopNotify.INFORMATION);
                    

                

            } else {
                DesktopNotify.showDesktopMessage("Seleccione un Item", "Debe Seleccionar un Item", DesktopNotify.INFORMATION);
                return;
            }
        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("Error", "No se Pudo Agregar el Registro", DesktopNotify.ERROR);
            System.out.println("exc: " + e.getMessage());
        }
        try {
            if (txtdescripcion.getText().length() == 0) {
                txtdescripcion.requestFocus();
                //txtdescripcion.setToolTipText("Debe Ingresar una Descripcion del cargos");
                //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                //new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.BottomLeft, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.INFORMATION);
            }
            if (precioa.getText().length() == 0) {
                precioa.requestFocus();

                //txtdescripcion.setToolTipText("Debe Ingresar una Descripcion del cargos");
                //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                //new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.BottomLeft, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar un Precio", DesktopNotify.INFORMATION);
            }

        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("Error", "No se pudo ingresar el registro", DesktopNotify.ERROR);
        }

    }
    //modificar los Registros
    public void modificar(){
               if(eveselect!=null){
            try {
                 if (this.modelocombo.getSelectedItem() != null) {
                     Programacion dato =(Programacion) this.modelocombo.getSelectedItem();
                     System.out.println("este es el dato: "+dato);
               
                         //Ejecutar el metodo aqui
                        System.out.println("esta es la programacion encontrada: "+dato.getDescripcion());
                        if (txtdescripcion.getText().length() == 0) {
                            txtdescripcion.requestFocus();

                            txtdescripcion.setToolTipText("Debe Ingresar una Descripcion del cargos");
                            //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                            new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                            // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
                        }
                        if (precioa.getText().length() == 0) {
                            precioa.requestFocus();

                            //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                            new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                            // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
                        }
                        if (preciob.getText().length() == 0) {
                            preciob.requestFocus();

                            //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                            new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                            // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
                        }
                        if (precioc.getText().length() == 0) {
                            precioc.requestFocus();

                            //JOptionPane.showMessageDialog(null,"Debe Ingresar una Descripcion del cargos" );
                            new rojerusan.RSNotifyFade("Descripcion", "Debes agregar una Descripcion", 6, RSNotifyFade.PositionNotify.TopRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                            // DesktopNotify.showDesktopMessage("Registros Incompletos", "Debes Ingresar una Descripcion", DesktopNotify.ERROR);
                        }
                         Double vprecioa = 0.0, vpreciob = 0.0, vprecioc = 0.0;
                        try {

                            vprecioa = Double.parseDouble(precioa.getText());
                        } catch (Exception e) {
                            DesktopNotify.showDesktopMessage("Numero Incorrecto", "El Precio A debe ser Numerico", DesktopNotify.ERROR);
                        }
                        try {

                            vpreciob = Double.parseDouble(preciob.getText());
                        } catch (Exception e) {
                            DesktopNotify.showDesktopMessage("Numero Incorrecto", "El Precio B debe ser Numerico", DesktopNotify.ERROR);
                        }
                        try {

                            vprecioc = Double.parseDouble(precioc.getText());
                        } catch (Exception e) {
                            DesktopNotify.showDesktopMessage("Numero Incorrecto", "El Precio c debe ser Numerico", DesktopNotify.ERROR);
                        }
                        
                        //System.out.println("hola "+programacionencontrada.getDescripcion());
                         eveselect.setDescripcion(txtdescripcion.getText());
                        eveselect.setPrecioA(Double.parseDouble(precioa.getText()));
                        eveselect.setPrecioB(Double.parseDouble(preciob.getText()));
                        eveselect.setPrecioC(Double.parseDouble(precioc.getText()));
                        eveselect.setProgramacion(dato);
                        evDao.Update(eveselect);
                        // System.out.println("hola2 "+programacionencontrada.getDescripcion());
                        
                        cargarModeloTabla();

                        DesktopNotify.showDesktopMessage("Exito", "Registro Modificado Correctamente", DesktopNotify.INFORMATION);
                                                limpiartextbox();
                                                 System.out.println("hola3 "+dato.getDescripcion());
                        
                                    
           
                     
                 }else{
                    DesktopNotify.showDesktopMessage("Alerta", "No se ha Seleccionado un Item de Programacion", DesktopNotify.ERROR); 
                 }
        
            } catch (Exception e) {
                DesktopNotify.showDesktopMessage("Error", "No se Pudo Modificar el Registro", DesktopNotify.ERROR);
                System.out.println("error: "+e.getMessage());
            }
        }else{
             DesktopNotify.showDesktopMessage("Seleccione un Registro", "Antes debes Seleccionar un Registro", DesktopNotify.INFORMATION);
        }
    }
 public void imprimireporte(){
         Connection cnn = Conexion.getConnection();

        Map p = new HashMap(); // para parametros
        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/reportes/reporteeventos.jrxml");
            print = JasperFillManager.fillReport(report, p, cnn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte General de Eventos");
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
        tablaevento = new rojeru_san.complementos.TableMetro();
        txtbuscar = new app.bolivia.swing.JCTextField();
        btnEliminar = new rojeru_san.complementos.ButtonHover();
        precioa = new app.bolivia.swing.JCTextField();
        preciob = new app.bolivia.swing.JCTextField();
        precioc = new app.bolivia.swing.JCTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
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

        txtid.setToolTipText("Codigo del Evento");
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

        btnmodificar.setBackground(new java.awt.Color(153, 153, 0));
        btnmodificar.setForeground(new java.awt.Color(0, 0, 0));
        btnmodificar.setText("Modificar");
        btnmodificar.setToolTipText("Modificar Registro");
        btnmodificar.setColorHover(new java.awt.Color(204, 204, 0));
        btnmodificar.setColorTextHover(new java.awt.Color(0, 0, 0));
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
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
                        eveselect = (Eventos) modeloTabla.getValueAt(filaSeleccionada,0); 
                        // setPrivilegios(usuarioSelect.getPrivilegios());
                        String descripcion1=eveselect.getProgramacion().getDescripcion();

                        txtid.setText(eveselect.getIdevento().toString());
                        txtdescripcion.setText(eveselect.getDescripcion());
                        precioa.setText(eveselect.getPrecioA().toString());
                        preciob.setText(eveselect.getPrecioB().toString());
                        precioc.setText(eveselect.getPrecioC().toString());
                        modelocombo.setSelectedItem(eveselect.getProgramacion());

                        // LLenamos los textBoxs atraves del objeto ...

                        //abilitar boton para actualizar
                        btnguardar.setEnabled(false);
                        btnmodificar.setEnabled(true);
                        btnEliminar.setEnabled(true);

                    }

                }
            }

        );
        jScrollPane1.setViewportView(tablaevento);

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

        precioa.setToolTipText("Precio A");
        precioa.setPlaceholder("Precio A");

        preciob.setToolTipText("Ingrese Precio  A");
        preciob.setPlaceholder("Precio B");

        precioc.setToolTipText("Precio A");
        precioc.setPlaceholder("Precio C");

        jComboBox1.setModel(modelocombo);

        jLabel1.setText("Tipo de Programacion:");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(precioa, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(preciob, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(precioc, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(88, 88, 88)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(precioa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(preciob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precioc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        limpiartextbox();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtdescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyPressed

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
        if (txtbuscar.getText().length() >= 2) {
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
            java.util.logging.Logger.getLogger(frmEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmEventos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.ButtonHover btnEliminar;
    private rojeru_san.complementos.ButtonHover btnguardar;
    private rojeru_san.complementos.ButtonHover btnimprimir;
    private rojeru_san.complementos.ButtonHover btnmodificar;
    private rojeru_san.complementos.ButtonHover btnnuevo;
    private javax.swing.JComboBox<Programacion> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private app.bolivia.swing.JCTextField precioa;
    private app.bolivia.swing.JCTextField preciob;
    private app.bolivia.swing.JCTextField precioc;
    private rojeru_san.complementos.TableMetro tablaevento;
    private app.bolivia.swing.JCTextField txtbuscar;
    private app.bolivia.swing.JCTextField txtdescripcion;
    private app.bolivia.swing.JCTextField txtid;
    // End of variables declaration//GEN-END:variables
}
