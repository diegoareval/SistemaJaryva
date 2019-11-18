/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles;

import com.devs.auxiliar.TextFieldEvent;
import com.devs.vistas.frmMostrar;
import com.mxrck.autocompleter.TextAutoCompleter;
import dao.AfpDao;
import dao.BancoDao;
import dao.CargoDao;
import dao.DepartamentoDao;
import dao.PersonalDao;
import dao.ProgramacionDao;
import ds.desktop.notify.DesktopNotify;
import entities.Afp;
import entities.Bancos;
import entities.Cargos;
import entities.CatPersonal;
import entities.Departamentos;
import entities.Programacion;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class pnlAndroid extends javax.swing.JPanel {
//metodo que permite determinar si se hara modificacion o no
TextFieldEvent evento=new TextFieldEvent();
    Boolean modificar = false;

    //instancias con diferentes dao para cargar los modelos a seleccionar
    ProgramacionDao prdao = new ProgramacionDao();
    AfpDao afpdao = new AfpDao();
    CargoDao cardao = new CargoDao();
    DepartamentoDao depdao = new DepartamentoDao();
    BancoDao bandao = new BancoDao();
    PersonalDao perDao = new PersonalDao();
    
    //id publico del banco
    //Bancos bancost=null;

    //probando con autocompletar textbox
    private TextAutoCompleter autocompletebuscador;

    //Modelos para cargar los diferentes objetos
    DefaultComboBoxModel<Programacion> modelopr = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Cargos> modelocargo = new DefaultComboBoxModel<Cargos>();
    DefaultComboBoxModel<Afp> modeloafp = new DefaultComboBoxModel<Afp>();
    DefaultComboBoxModel<Bancos> modelobanco = new DefaultComboBoxModel<Bancos>();
    DefaultComboBoxModel<Departamentos> modelodepartamento = new DefaultComboBoxModel<Departamentos>();

    public pnlAndroid() {
        //cargar los modlos en el momento que el panel se inicia
        cargarModeloprogramacion();
        cargarModeloafp();
        cargarModelocargos();
        cargarModelobanco();
        cargarModelodepartamento();

        //prueba autocomplete
        //AutoCompleteDecorator.decorate(jcprogramacion);
        initComponents();
        desactivartxt();
        autocomplete();
    }

    private void cargarModeloprogramacion() {
        limpiarModelos(modelopr);
        List<Programacion> lista = prdao.listAll();

        for (Programacion a : lista) {
            modelopr.addElement(a);
        }
        //para que no esté seleccionado
        modelopr.setSelectedItem(null);
    }

    public void autocomplete() {
        //autocomplete=new TextAutoCompleter(codjaryva);
        autocompletebuscador = new TextAutoCompleter(txtbuscar);
        List<CatPersonal> lista = perDao.listAll();
        if (lista.size() > 0) {
            for (CatPersonal cats : lista) {
                //utocomplete.addItem(cats.getCodJaryva());
                autocompletebuscador.addItem(cats.getNombres());
            }

        }
    }
    
    //generar excel
//    private void generarExcel() {
//        HSSFWorkbook workbook = new ExcelUtils.ExcelGenerator().generateExcel();
//        JFileChooser fileChooser = new JFileChooser();
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos Excel (*.xls)", "xls");
//        fileChooser.setAcceptAllFileFilterUsed(false);
//        fileChooser.setFileFilter(filter);
//        fileChooser.setDialogTitle("GUARDAR ARCHIVO");
//        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
//
//            try {
//                File archivo = new File(fileChooser.getSelectedFile().getAbsolutePath());
//
//                OutputStream out = null;
//                if (getFileExtension(archivo)) {
//                    out = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath());
//                } else {
//                    out = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath() + ".xls");
//                }
//
//                JOptionPane.showMessageDialog(this, "Archivo generado con éxito");
//
//                workbook.write(out);
//                workbook.close();
//                out.flush();
//                out.close();
//            } catch (IOException ex) {
//                Logger.getLogger(pnlAndroid.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//    }
//    //obtener la extension del archivo
//    private boolean getFileExtension(File file) {
//        String ext = null;
//        String s = file.getName();
//        int i = s.lastIndexOf('.');
//
//        if (i > 0 && i < s.length() - 1) {
//            ext = s.substring(i + 1).toLowerCase();
//        }
//
//        if (ext != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    //metodo Buscar
    public void buscar(String codjaryvat) {
        List<CatPersonal> lista = perDao.Buscar(codjaryvat);
        CatPersonal cat = null;
        if (lista.size() > 0) {
            modificar = true;
            for (CatPersonal cats : lista) {
                cat = cats;
            }
            txtnombre.setText(cat.getNombres());
            txtapellidos.setText(cat.getApellidos());
            txtcuentabanc.setText(cat.getCtaBancaria());
            txtdireccion.setText(cat.getDireccion());
            txtdui.setText(cat.getDui());
            txtelefono1.setText(cat.getTelefono1());
            txttelefono2.setText(cat.getTelefono2());
            txtiss.setText(cat.getIsss());
            txtnit.setText(cat.getNit());
            txtnup.setText(cat.getNup());
            txtcuentabanc.setText(cat.getCtaBancaria());
            txtobserbaciones.setText(cat.getObserbaciones());
            txtnombrecompleto.setText(cat.getNombres() + " " + cat.getApellidos());
            txtnombrenormal.setText(cat.getNombres() + " " + cat.getApellidos());
            txtnombreinverso.setText(cat.getApellidos() + " " + cat.getNombres());
            jcingreso.setDate(cat.getFechaIngreso());
            jcnacimiento.setDate(cat.getFechaNacimiento());
            modelodepartamento.setSelectedItem(cat.getDepartamentos());
            modeloafp.setSelectedItem(cat.getAfp());
            modelocargo.setSelectedItem(cat.getCargos());
            modelopr.setSelectedItem(cat.getProgramacion());
            jcestado.setSelectedItem(cat.getEstadoCivil());
            jcgenero.setSelectedItem(cat.getGenero());
            jccategoriapago.setSelectedItem(cat.getCatPago());
            modelobanco.setSelectedItem(cat.getBancos());
            modificar = true;
            btnguardar.setText("Eliminar");

        } else {
            DesktopNotify.showDesktopMessage("Informacion", "No se encontro el registro con el codigo" + this.codjaryva.getText(), DesktopNotify.ERROR);
            modificar = false;
        }
    }

    //Buscar por nombre y apellido
    public void buscarpornombre(String cad) {
        List<CatPersonal> lista = perDao.Buscarpornombreyapellido(cad);
        CatPersonal cat = null;
        if (lista.size() > 0) {
            modificar = true;
            for (CatPersonal cats : lista) {
                cat = cats;
            }
            txtnombre.setText(cat.getNombres());
            codjaryva.setText(cat.getCodJaryva());
            txtapellidos.setText(cat.getApellidos());
            txtcuentabanc.setText(cat.getCtaBancaria());
            txtdireccion.setText(cat.getDireccion());
            txtdui.setText(cat.getDui());
            txtelefono1.setText(cat.getTelefono1());
            txttelefono2.setText(cat.getTelefono2());
            txtiss.setText(cat.getIsss());
            txtnit.setText(cat.getNit());
            txtnup.setText(cat.getNup());
            txtcuentabanc.setText(cat.getCtaBancaria());
            txtobserbaciones.setText(cat.getObserbaciones());
            txtnombrecompleto.setText(cat.getNombres() + " " + cat.getApellidos());
            txtnombrenormal.setText(cat.getNombres() + " " + cat.getApellidos());
            txtnombreinverso.setText(cat.getApellidos() + " " + cat.getNombres());
            jcingreso.setDate(cat.getFechaIngreso());
            jcnacimiento.setDate(cat.getFechaNacimiento());
            modelodepartamento.setSelectedItem(cat.getDepartamentos());
            modeloafp.setSelectedItem(cat.getAfp());
            modelocargo.setSelectedItem(cat.getCargos());
            modelopr.setSelectedItem(cat.getProgramacion());
            jcestado.setSelectedItem(cat.getEstadoCivil());
            jcgenero.setSelectedItem(cat.getGenero());
            jccategoriapago.setSelectedItem(cat.getCatPago());
            int idbanco=cat.getBancos().getCodBanco();
            
            List<Bancos> listado=bandao.buscarporID(idbanco);
            Bancos catba = null;
        if (listado.size() > 0) {
            modificar = true;
            for (Bancos bancost : listado) {
                catba = bancost;
                System.out.println("banco enc: "+catba.getDescripcion());
            }
            }
        modelobanco.setSelectedItem(catba);
            
            System.out.println("id: "+idbanco);
            //modelobanco.setSelectedItem(cat.getBancos());
            
            modificar = true;
            btnguardar.setText("Eliminar");

        } else {
            DesktopNotify.showDesktopMessage("Informacion", "No se encontro el registro con el codigo" + this.codjaryva.getText(), DesktopNotify.ERROR);
            modificar = false;
        }
    }

    public void nuevo() {
        txtnit.setText("");
        codjaryva.setText("");
        txtnombre.setText("");
        txtnombrecompleto.setText("");
        txtnombreinverso.setText("");
        txtnombrenormal.setText("");
        txtnup.setText("");
        txtdireccion.setText("");
        txtobserbaciones.setText("");
        txtapellidos.setText("");
        txtcuentabanc.setText("");
        txtdui.setText("");
        txtelefono1.setText("");
        txttelefono2.setText("");
        txtiss.setText("");
        modelocargo.setSelectedItem(null);
        modeloafp.setSelectedItem(null);
        modelobanco.setSelectedItem(null);
        jcnacimiento.setDate(null);
        jcingreso.setDate(null);
        modificar = false;
        btnguardar.setText("Guardar");
    }

    private void cargarModelocargos() {
        limpiarModelos(modelocargo);
        List<Cargos> lista = cardao.listAll();

        for (Cargos a : lista) {
            modelocargo.addElement(a);
        }
        //para que no esté seleccionado
        modelocargo.setSelectedItem(null);
    }

    private void cargarModeloafp() {
        limpiarModelos(modeloafp);
        List<Afp> lista = afpdao.listAll();

        for (Afp a : lista) {
            modeloafp.addElement(a);
        }
        //para que no esté seleccionado
        modeloafp.setSelectedItem(null);
    }

    private void cargarModelobanco() {
        limpiarModelos(modelobanco);
        List<Bancos> lista = bandao.listAll();

        for (Bancos a : lista) {
            modelobanco.addElement(a);
        }
        //para que no esté seleccionado
        modelobanco.setSelectedItem(null);
    }

    private void desactivartxt() {
        txtnombrecompleto.setEditable(false);
        txtnombrenormal.setEditable(false);
        txtnombreinverso.setEditable(false);
    }

    private void cargarModelodepartamento() {
        limpiarModelos(modelodepartamento);
        List<Departamentos> lista = depdao.listAll();

        for (Departamentos a : lista) {
            modelodepartamento.addElement(a);
        }
        //para que no esté seleccionado
        modelodepartamento.setSelectedItem(null);
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

    public boolean validar() {

        if (jcprogramacion.getSelectedItem() == null) {
            DesktopNotify.showDesktopMessage("Info", "Debes seleccionar un registro Programacion", DesktopNotify.INFORMATION);
        }
        if (codjaryva.getText().isEmpty()) {
            DesktopNotify.showDesktopMessage("Info", "Debes agregar codigo de jaryva", DesktopNotify.INFORMATION);
        }
        if (jccategoriapago.getSelectedItem() == null) {
            DesktopNotify.showDesktopMessage("Info", "Debes seleccionar una categoria de pago", DesktopNotify.INFORMATION);
        }
        if (jcestado.getSelectedItem() == null) {
            DesktopNotify.showDesktopMessage("Info", "Debes seleccionar el estado civil", DesktopNotify.INFORMATION);
        }
        if (txtnombre.getText().isEmpty()) {
            DesktopNotify.showDesktopMessage("Info", "Debes agregar el nombre", DesktopNotify.INFORMATION);
        }
        if (txtapellidos.getText().isEmpty()) {
            DesktopNotify.showDesktopMessage("Info", "Debes seleccionar el apellido", DesktopNotify.INFORMATION);
        }
        if (txtdireccion.getText().isEmpty()) {
            DesktopNotify.showDesktopMessage("Info", "Debes seleccionar un registro Banco", DesktopNotify.INFORMATION);
        }
        if (jcingreso.getDate() == null) {
            DesktopNotify.showDesktopMessage("Info", "Debes ingresar una fecha de ingreso", DesktopNotify.INFORMATION);
        }
        if (jcnacimiento.getDate() == null) {
            DesktopNotify.showDesktopMessage("Info", "Debes ingresar una fechade nacimiento", DesktopNotify.INFORMATION);
        }
        if (txtdui.getText().isEmpty()) {
            DesktopNotify.showDesktopMessage("Info", "Debes agregar unnumero de dui", DesktopNotify.INFORMATION);
        }
        if (txtnit.getText().isEmpty()) {
            DesktopNotify.showDesktopMessage("Info", "Debes agregar el numero de nit", DesktopNotify.INFORMATION);
        }
        if (txtiss.getText().isEmpty()) {
            DesktopNotify.showDesktopMessage("Info", "Debes agregar el numero de isss", DesktopNotify.INFORMATION);
        }
        if (txtnup.getText().isEmpty()) {
            DesktopNotify.showDesktopMessage("Info", "Debes agregar un nup", DesktopNotify.INFORMATION);
        }
        if (jcgenero.getSelectedItem() == null) {
            DesktopNotify.showDesktopMessage("Info", "Debes seleccionar un genero", DesktopNotify.INFORMATION);
        }
        if (jcdepartamento.getSelectedItem() == null) {
            DesktopNotify.showDesktopMessage("Info", "Debes seleccionar un departamento", DesktopNotify.INFORMATION);
        }
        if (jcafp.getSelectedItem() == null) {
            DesktopNotify.showDesktopMessage("Info", "Debes seleccionar un AFP", DesktopNotify.INFORMATION);
        }

        if (cbcarg.getSelectedItem() == null) {
            DesktopNotify.showDesktopMessage("Info", "Debes seleccionar un Cargo", DesktopNotify.INFORMATION);
        }
        return true;

    }

    public void guardar() {
        if (modificar == false) {
            CatPersonal persona = new CatPersonal();
            if (validar()) {
                Date fechaIngreso;
                Date fechanac;

                try {
                    Calendar cal;
                    int d, m, a;
                    //fecha ingreso
                    cal = jcingreso.getCalendar();
                    d = cal.get(Calendar.DAY_OF_MONTH);
                    m = cal.get(Calendar.MONTH);
                    a = cal.get(Calendar.YEAR) - 1900;

                    fechaIngreso = new java.sql.Date(a, m, d);
                    //fecha salida
                    cal = jcnacimiento.getCalendar();
                    d = cal.get(Calendar.DAY_OF_MONTH);
                    m = cal.get(Calendar.MONTH);
                    a = cal.get(Calendar.YEAR) - 1900;

                    fechanac = new java.sql.Date(a, m, d);
                } catch (Exception e) {
                    DesktopNotify.showDesktopMessage("Error", "Error en las fechas", DesktopNotify.ERROR);
                    return;
                }

                try {
                    //cargos
                    Cargos cargo = new Cargos();
                    cargo = (Cargos) cbcarg.getSelectedItem();
                    //afp
                    Afp afpp = new Afp();
                    afpp = (Afp) jcafp.getSelectedItem();
                    //programacion
                    Programacion prog = new Programacion();
                    prog = (Programacion) jcprogramacion.getSelectedItem();

                    //departamentos
                    Departamentos deps = new Departamentos();
                    deps = (Departamentos) jcdepartamento.getSelectedItem();

                    //Bancos
                    Bancos bancoo = new Bancos();
                    bancoo = (Bancos) jcbanco.getSelectedItem();

                    //agregar atributos al objeto
                    persona.setApellidos(txtapellidos.getText());
                    persona.setNombres(txtnombre.getText());
                    persona.setNit(txtnit.getText());
                    persona.setNup(txtnup.getText());
                    persona.setDireccion(txtdireccion.getText());
                    persona.setDui(txtdui.getText());
                    persona.setTelefono1(txtelefono1.getText());
                    persona.setTelefono2(txttelefono2.getText());
                    persona.setIsss(txtiss.getText());
                    persona.setCtaBancaria(txtcuentabanc.getText());
                    persona.setObserbaciones(txtobserbaciones.getText());
                    persona.setGenero(jcgenero.getSelectedItem().toString());
                    persona.setCatPago(jccategoriapago.getSelectedItem().toString());
                    persona.setEstadoCivil(jcestado.getSelectedItem().toString());
                    persona.setCodJaryva(codjaryva.getText());

                    //agregar foranes al objeto
                    persona.setDepartamentos(deps);
                    persona.setAfp(afpp);
                    persona.setBancos(bancoo);
                    persona.setProgramacion(prog);
                    persona.setCargos(cargo);

                    //agregar fechas
                    persona.setFechaIngreso(fechaIngreso);
                    persona.setFechaNacimiento(fechanac);
                    perDao.Save(persona);
                    DesktopNotify.showDesktopMessage("Exito", "Registro Guardado con exito", DesktopNotify.SUCCESS);
                } catch (Exception e) {
                    DesktopNotify.showDesktopMessage("Error", "No se pudo guardar el Registro", DesktopNotify.ERROR);
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } else {
            eliminar();
        }
    }

    public void eliminar() {
        CatPersonal cat = null;
        List<CatPersonal> lista = perDao.Buscar(codjaryva.getText());
        if (lista.size() > 0) {
            for (CatPersonal catpersona : lista) {
                cat = catpersona;
            }
            perDao.Delete(cat);
            DesktopNotify.showDesktopMessage("Exito", "Registro Eliminado Correctamente", DesktopNotify.SUCCESS);

        } else {
            DesktopNotify.showDesktopMessage("Info", "Debe Seleccionar un Registro de programacion", DesktopNotify.INFORMATION);
        }
    }

    public void modificar() {
        if (modificar) {
            CatPersonal cat = null;

            if (validar()) {
                List<CatPersonal> lista = perDao.Buscar(codjaryva.getText());
                if (lista.size() > 0) {
                    for (CatPersonal catpersona : lista) {
                        cat = catpersona;
                    }
                    try {
                        Cargos cargo = new Cargos();
                        cargo = (Cargos) cbcarg.getSelectedItem();
                        //afp
                        Afp afpp = new Afp();
                        afpp = (Afp) jcafp.getSelectedItem();
                        //programacion
                        Programacion prog = new Programacion();
                        prog = (Programacion) jcprogramacion.getSelectedItem();

                        //departamentos
                        Departamentos deps = new Departamentos();
                        deps = (Departamentos) jcdepartamento.getSelectedItem();

                        Date fechaIngreso;
                        Date fechanac;

                        try {
                            Calendar cal;
                            int d, m, a;
                            //fecha ingreso
                            cal = jcingreso.getCalendar();
                            d = cal.get(Calendar.DAY_OF_MONTH);
                            m = cal.get(Calendar.MONTH);
                            a = cal.get(Calendar.YEAR) - 1900;

                            fechaIngreso = new java.sql.Date(a, m, d);
                            //fecha salida
                            cal = jcnacimiento.getCalendar();
                            d = cal.get(Calendar.DAY_OF_MONTH);
                            m = cal.get(Calendar.MONTH);
                            a = cal.get(Calendar.YEAR) - 1900;

                            fechanac = new java.sql.Date(a, m, d);
                        } catch (Exception e) {
                            DesktopNotify.showDesktopMessage("Error", "Error en las fechas", DesktopNotify.ERROR);
                            return;
                        }

                        //Bancos
                        Bancos bancoo = new Bancos();
                        bancoo = (Bancos) jcbanco.getSelectedItem();
                        cat.setApellidos(txtapellidos.getText());
                        cat.setNombres(txtnombre.getText());
                        cat.setNit(txtnit.getText());
                        cat.setNup(txtnup.getText());
                        cat.setDireccion(txtdireccion.getText());
                        cat.setDui(txtdui.getText());
                        cat.setTelefono1(txtelefono1.getText());
                        cat.setTelefono2(txttelefono2.getText());
                        cat.setIsss(txtiss.getText());
                        cat.setCtaBancaria(txtcuentabanc.getText());
                        cat.setObserbaciones(txtobserbaciones.getText());
                        cat.setGenero(jcgenero.getSelectedItem().toString());
                        cat.setCatPago(jccategoriapago.getSelectedItem().toString());
                        cat.setEstadoCivil(jcestado.getSelectedItem().toString());

                        //agregar foranes al objeto
                        cat.setDepartamentos(deps);
                        cat.setAfp(afpp);
                        cat.setBancos(bancoo);
                        cat.setProgramacion(prog);
                        cat.setCargos(cargo);

                        //agregar fechas
                        cat.setFechaIngreso(fechaIngreso);
                        cat.setFechaNacimiento(fechanac);
                        perDao.Update(cat);
                        DesktopNotify.showDesktopMessage("Exito", "Registro Actualizado Correctamente", DesktopNotify.INFORMATION);
                    } catch (Exception e) {
                        DesktopNotify.showDesktopMessage("Error", "No se pudo Ingresar el registro", DesktopNotify.INFORMATION);
                    }

                }
            }
        } else {
            DesktopNotify.showDesktopMessage("Info", "No hay Registro seleccionado", DesktopNotify.INFORMATION);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jcprogramacion = new javax.swing.JComboBox<>();
        codjaryva = new app.bolivia.swing.JCTextField();
        txtnombrecompleto = new app.bolivia.swing.JCTextField();
        jLabel2 = new javax.swing.JLabel();
        jccategoriapago = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jcestado = new javax.swing.JComboBox<>();
        txtnombre = new app.bolivia.swing.JCTextField();
        txtapellidos = new app.bolivia.swing.JCTextField();
        txtdireccion = new app.bolivia.swing.JCTextField();
        jLabel4 = new javax.swing.JLabel();
        jcgenero = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jcingreso = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jcnacimiento = new com.toedter.calendar.JDateChooser();
        txtelefono1 = new app.bolivia.swing.JCTextField();
        txttelefono2 = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        jcdepartamento = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtdui = new app.bolivia.swing.JCTextField();
        txtnit = new app.bolivia.swing.JCTextField();
        txtiss = new app.bolivia.swing.JCTextField();
        txtnup = new app.bolivia.swing.JCTextField();
        txtcuentabanc = new app.bolivia.swing.JCTextField();
        txtnombrenormal = new app.bolivia.swing.JCTextField();
        txtnombreinverso = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        jcafp = new javax.swing.JComboBox<>();
        jcbanco = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtobserbaciones = new app.bolivia.swing.JCTextField();
        btnnuevo = new rojeru_san.complementos.ButtonHover();
        btnnuevo1 = new rojeru_san.complementos.ButtonHover();
        btnnuevo2 = new rojeru_san.complementos.ButtonHover();
        btnguardar = new rojeru_san.complementos.ButtonHover();
        cbcarg = new javax.swing.JComboBox<>();
        txtbuscar = new app.bolivia.swing.JCTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Catalogo de Personal"));

        jLabel1.setText(" Programacion");

        jcprogramacion.setModel(modelopr);

        codjaryva.setToolTipText("Codigo Jaryva");
        codjaryva.setPlaceholder("Codigo segunJaryva");
        codjaryva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codjaryvaActionPerformed(evt);
            }
        });
        codjaryva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codjaryvaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codjaryvaKeyTyped(evt);
            }
        });

        txtnombrecompleto.setToolTipText("Codigo Jaryva");
        txtnombrecompleto.setPlaceholder("Nombre Completo");

        jLabel2.setText("Categoria de Pago");

        jccategoriapago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));

        jLabel3.setText("Estado Civil:");

        jcestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soltero(a)", "Casado(a)", "viudo(a)", "Divorciado(a)", "Acompañado(a)" }));

        txtnombre.setToolTipText("Nombre");
        txtnombre.setPlaceholder("Nombre(s)");
        txtnombre.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtnombrePropertyChange(evt);
            }
        });
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        txtapellidos.setToolTipText("Nombre");
        txtapellidos.setPlaceholder("Apellido(s)");
        txtapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtapellidosKeyReleased(evt);
            }
        });

        txtdireccion.setToolTipText("Nombre");
        txtdireccion.setPlaceholder("Direccion");

        jLabel4.setText("Genero:");

        jcgenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));

        jLabel6.setText("Fecha Ingreso");

        jcingreso.setForeground(new java.awt.Color(102, 102, 255));
        jcingreso.setToolTipText("Periodo desde");
        jcingreso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jcingresoPropertyChange(evt);
            }
        });

        jLabel7.setText("Fecha Nacimiento");

        jcnacimiento.setForeground(new java.awt.Color(102, 102, 255));
        jcnacimiento.setToolTipText("Periodo desde");
        jcnacimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jcnacimientoPropertyChange(evt);
            }
        });

        txtelefono1.setToolTipText("Nombre");
        txtelefono1.setPlaceholder("Telefono 1");

        txttelefono2.setToolTipText("Nombre");
        txttelefono2.setPlaceholder("Telefono 2");

        jLabel8.setText("Depto");

        jcdepartamento.setModel(modelodepartamento);

        jLabel9.setText("Cargo");

        txtdui.setToolTipText("Nombre");
        txtdui.setPlaceholder("DUI");
        txtdui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtduiKeyReleased(evt);
            }
        });

        txtnit.setToolTipText("Nombre");
        txtnit.setPlaceholder("NIT");

        txtiss.setToolTipText("Nombre");
        txtiss.setPlaceholder("ISSS");

        txtnup.setToolTipText("Nombre");
        txtnup.setPlaceholder("NUP");

        txtcuentabanc.setToolTipText("Nombre");
        txtcuentabanc.setPlaceholder("Cuenta Bancaria");

        txtnombrenormal.setToolTipText("Nombre");
        txtnombrenormal.setPlaceholder("Nombre");

        txtnombreinverso.setToolTipText("Nombre");
        txtnombreinverso.setPlaceholder("Nombre");

        jLabel10.setText("AFP");

        jcafp.setModel(modeloafp);

        jcbanco.setModel(modelobanco);

        jLabel11.setText("Banco");

        txtobserbaciones.setToolTipText("Nombre");
        txtobserbaciones.setPlaceholder("Obserbaciones");

        btnnuevo.setBackground(new java.awt.Color(51, 51, 51));
        btnnuevo.setText("Nuevo");
        btnnuevo.setToolTipText("Nuevo Registro");
        btnnuevo.setColorHover(new java.awt.Color(153, 153, 153));
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnnuevo1.setBackground(new java.awt.Color(51, 51, 51));
        btnnuevo1.setText("Mostrar");
        btnnuevo1.setToolTipText("Nuevo Registro");
        btnnuevo1.setColorHover(new java.awt.Color(153, 153, 153));
        btnnuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevo1ActionPerformed(evt);
            }
        });

        btnnuevo2.setBackground(new java.awt.Color(51, 51, 51));
        btnnuevo2.setText("Modificar");
        btnnuevo2.setToolTipText("Nuevo Registro");
        btnnuevo2.setColorHover(new java.awt.Color(153, 153, 153));
        btnnuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevo2ActionPerformed(evt);
            }
        });

        btnguardar.setBackground(new java.awt.Color(51, 51, 51));
        btnguardar.setText("Guardar");
        btnguardar.setToolTipText("Nuevo Registro");
        btnguardar.setColorHover(new java.awt.Color(153, 153, 153));
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        cbcarg.setModel(modelocargo);

        txtbuscar.setToolTipText("Buscar Registros");
        txtbuscar.setPlaceholder("Buscar Registros");
        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscarKeyPressed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnnuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnnuevo2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jcprogramacion, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(codjaryva, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jccategoriapago, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jcestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtnombrecompleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcingreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jcnacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jcgenero, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdui, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtiss, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtnup, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtcuentabanc, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnombrenormal, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnombreinverso, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtobserbaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel10))
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jcafp, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcbanco, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbcarg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jcdepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnuevo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcprogramacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombrecompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codjaryva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jccategoriapago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txttelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(jcnacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcgenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jcdepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbcarg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jcafp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel11))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtdui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtiss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtcuentabanc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtnombrenormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnombreinverso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtobserbaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        codjaryva.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void jcingresoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jcingresoPropertyChange

    }//GEN-LAST:event_jcingresoPropertyChange

    private void jcnacimientoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jcnacimientoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jcnacimientoPropertyChange

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        nuevo();

    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnnuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevo1ActionPerformed
        frmMostrar mostrar = new frmMostrar();
        mostrar.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnnuevo1ActionPerformed

    private void btnnuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevo2ActionPerformed
        modificar();
    }//GEN-LAST:event_btnnuevo2ActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        guardar();

    }//GEN-LAST:event_btnguardarActionPerformed

    private void txtnombrePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtnombrePropertyChange

    }//GEN-LAST:event_txtnombrePropertyChange

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        String nombre = txtnombre.getText();
        txtnombrecompleto.setText(nombre);
        txtnombrenormal.setText(nombre);
        txtnombreinverso.setText(nombre);
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtapellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidosKeyReleased

        String nombre = txtnombre.getText();
        String apellido = txtapellidos.getText();
        txtnombrecompleto.setText(nombre + " " + apellido);
        txtnombrenormal.setText(nombre + " " + apellido);
        txtnombreinverso.setText(apellido + " " + nombre);
    }//GEN-LAST:event_txtapellidosKeyReleased

    private void codjaryvaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codjaryvaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            buscar(codjaryva.getText());
            modificar = true;
        }
    }//GEN-LAST:event_codjaryvaKeyPressed

    private void codjaryvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codjaryvaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_codjaryvaKeyTyped

    private void txtbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            buscarpornombre(txtbuscar.getText());
            modificar = true;
        }
    }//GEN-LAST:event_txtbuscarKeyPressed

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyTyped

    private void codjaryvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codjaryvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codjaryvaActionPerformed

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void txtduiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtduiKeyReleased
       String texto="";
        if(txtdui.getText().length()==9){
            texto=txtdui.getText();
        txtdui.setText(texto+"-");
       }
    }//GEN-LAST:event_txtduiKeyReleased

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
       evento.textKeyPress(evt);
    }//GEN-LAST:event_txtnombreKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.ButtonHover btnguardar;
    private rojeru_san.complementos.ButtonHover btnnuevo;
    private rojeru_san.complementos.ButtonHover btnnuevo1;
    private rojeru_san.complementos.ButtonHover btnnuevo2;
    private javax.swing.JComboBox<Cargos> cbcarg;
    private app.bolivia.swing.JCTextField codjaryva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<Afp> jcafp;
    private javax.swing.JComboBox<Bancos> jcbanco;
    private javax.swing.JComboBox<String> jccategoriapago;
    private javax.swing.JComboBox<Departamentos> jcdepartamento;
    private javax.swing.JComboBox<String> jcestado;
    private javax.swing.JComboBox<String> jcgenero;
    private com.toedter.calendar.JDateChooser jcingreso;
    private com.toedter.calendar.JDateChooser jcnacimiento;
    private javax.swing.JComboBox<Programacion> jcprogramacion;
    private app.bolivia.swing.JCTextField txtapellidos;
    private app.bolivia.swing.JCTextField txtbuscar;
    private app.bolivia.swing.JCTextField txtcuentabanc;
    private app.bolivia.swing.JCTextField txtdireccion;
    private app.bolivia.swing.JCTextField txtdui;
    private app.bolivia.swing.JCTextField txtelefono1;
    private app.bolivia.swing.JCTextField txtiss;
    private app.bolivia.swing.JCTextField txtnit;
    private app.bolivia.swing.JCTextField txtnombre;
    private app.bolivia.swing.JCTextField txtnombrecompleto;
    private app.bolivia.swing.JCTextField txtnombreinverso;
    private app.bolivia.swing.JCTextField txtnombrenormal;
    private app.bolivia.swing.JCTextField txtnup;
    private app.bolivia.swing.JCTextField txtobserbaciones;
    private app.bolivia.swing.JCTextField txttelefono2;
    // End of variables declaration//GEN-END:variables
}
