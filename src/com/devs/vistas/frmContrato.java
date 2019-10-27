
package com.devs.vistas;

import com.devs.auxiliar.numletras;
import com.mxrck.autocompleter.TextAutoCompleter;
import dao.ContratoDao;
import dao.GrupofamiliarDao;
import dao.ObligacionDao;
import dao.PersonalDao;
import ds.desktop.notify.DesktopNotify;
import entities.CatPersonal;
import entities.ContratoTrabajo;
import entities.Grupofamiliar;
import entities.ObligacionesContrato;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import rojerusan.RSNotifyFade;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
/**
 *
 * @author DiegoArevalo
 */
public class frmContrato extends javax.swing.JFrame {
     //documentos word
    private XWPFDocument document=new XWPFDocument();
    //fin doc word
    ContratoDao contDao=new ContratoDao();
    ContratoTrabajo contrato=null;
     PersonalDao perDao = new PersonalDao();
     CatPersonal perencontrada=null;
     Grupofamiliar grupfencontrado=null;
     GrupofamiliarDao gfDao=new GrupofamiliarDao();
     
     //dao de obligaciones
     ObligacionDao dao=new ObligacionDao();
ObligacionesContrato obligaciones=new ObligacionesContrato();
     
     //catpersonal encontrada por el enter
      CatPersonal cat = null;
      
      
      //autocomplete
      //probando con autocompletar textbox
    private TextAutoCompleter autocompletebuscador;
     
    
    
    //boolean que permite modificar o no un registro
    Boolean modificar=false;
    int contratobuscar=0;
   
     DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    
    
    public frmContrato() {
        cargarColumnas();
        cargarModeloTabla();
        initComponents();
       configdatefechacontrato();
       autocomplete();
       unbutton();
        this.setResizable(false);
        //DesControlesInicio();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(1);
        this.setTitle("Contrato Individual de Trabajo");
    }
    //<editor-fold defaultstate="collapsed" desc="word">
     public void crearword(){
         //validamos si el contrato no es nulo
        if(contrato!=null){
            //agregamos la extension doc de word como filtro
            javax.swing.filechooser.FileNameExtensionFilter filtroWord=new FileNameExtensionFilter("Microsoft Word 2019", "docx");
        //instanciamos una clase filechooser
            final JFileChooser miArchivo=new JFileChooser();
            //agregamos el filtro a el archivo
        miArchivo.setFileFilter(filtroWord);
        //obtenemos l resultado si se ha seleccionado o no un archivo
        int aceptar=miArchivo.showSaveDialog(null);
        miArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //si se ha escogido un archivo
        if(aceptar==JFileChooser.APPROVE_OPTION){
            //obtenemos el archivo escogido
            File fileWord=miArchivo.getSelectedFile();
            //obtenemos el nombre del archivo en una cadena de texto
            String nombre=fileWord.getName();
            //si el nombre del documento contiene la extensio .docx
            if(nombre.indexOf('.')==-1){
                //si no contiene la extension debemos añadirla
                nombre+=".docx";
                fileWord=new File(fileWord.getParentFile(), nombre);
            }
            try {
                //creamos un flujo para añadir el archivo
                FileOutputStream output=new FileOutputStream(fileWord);
                //FileOutputStream output=new FileOutputStream("documento de prueba.docx");
               //hacemos una instancia a un parrafo
                XWPFParagraph paragraphTitulo=document.createParagraph();
                //creamos una clase para añadir un titulo al objeto run
                XWPFRun runTitulo=paragraphTitulo.createRun();
               //agregamos una propiedad a el titulo para poder centrarlo
                paragraphTitulo.setAlignment(ParagraphAlignment.CENTER);
                //agregamos el titulo en letra negrita
                runTitulo.setBold(true);
                //agregamos el tamaño de la fuente del documento
                runTitulo.setFontSize(15);
                //agregamos interlineado de palabras
                runTitulo.setUnderline(UnderlinePatterns.WORDS);
                //agregamos un titulo para el texto
                runTitulo.setText("CONTRATO INDIVIDUAL DE TRABAJO");
                //runTitulo.setColor("2f66f2");
                //agregamos un color a el titulo
                runTitulo.setColor("000000");
                //agregamos un salto de linea
                runTitulo.addBreak();
               //creamos un parrafo 
                XWPFParagraph paragraph=document.createParagraph();
                
                XWPFRun run=paragraph.createRun();
                //agregamos informacion proveniente de la base de datos a el parrafo a el parrafo
                run.setText("GENERALES DEL CONTRATANTE PATRONAL              GENERALES DEL TRABAJADOR                                ");
                run.setText("Nombre: Diego Arevalo                           Nombre: "+contrato.getCatPersonal().getNombres()+" "+contrato.getCatPersonal().getApellidos());
                run.setText("  Sexo: Masculino                                     Sexo: "+contrato.getCatPersonal().getGenero());
                run.setText("   Edad en años: cuarenta y cinco años                                Edad en Años: "+edad(contrato.getCatPersonal().getFechaNacimiento()));
                run.addBreak();
                 run.setText("   Estado Civil: Casado                               Estado Civil: "+contrato.getCatPersonal().getEstadoCivil());
                 run.setText("   Profesion: Empresario                               Estado Civil: "+contrato.getCatPersonal().getCargos().getDescripcion());
                run.setText("   Residencial: Hotel Crowne Plaza                           Estado Civil: "+contrato.getCatPersonal().getDireccion());
                run.setText("   Nacionalidad: Salvadoreña                           Estado Civil: "+contrato.getNacionalidad());
                run.setText("   Numero DUI: 01660234-9                           Estado Civil: "+contrato.getCatPersonal().getDui());
                run.setText("   Expedido En: "+contrato.getExtendido()+"                           Estado Civil: "+contrato.getExtendido());
                 run.setText("   Fecha Expedido En: "+contrato.getFechacontrato()+"                           Estado Civil: "+contrato.getFechacontrato());
                 run.setText("   NOSOTROS: MANUEL DE JESUS MARIN PANAMEÑO Y SAUL ERNESTO RODRIGUEZ JIMENEZ  "
                         + " , de las generales antes expresadas, actuando el primero en calidad  de  Representante  Patronal  de                                                                                                                          \n" +
"       JARDINES Y VARIOS, SOCIEDAD ANONIMA DE CAPITAL VARIABLE-----------, del  domicilio  de  San                                                                                                                          \n" +
"       Salvador; y el segundo, actuando por sí, quienes en  lo  sucesivo  seremos  denominados  la                                                                                                                          \n" +
"       'Empresa' y el 'Empleado' respectivamente, en el carácter en que actuamos, hemos  convenido                                                                                                                          \n" +
"       en celebrar el  presente  contrato  individual  de  trabajo  sujeto  a  las  estipulaciones                                                                                                                          \n" +
"       siguientes: "  );
                 run.addBreak();
                 //agregamos las obligaciones que pertnecen al respectivo contrato seleccionado proveniente de la base de datos
                  List<ObligacionesContrato> lista = dao.BuscarporContrato(contrato.getIdcontrato());//agregarlos registros a la lista
        //int cantidadLista = lista.size();//cantidad de la lista
        //modeloTabla.setRowCount(cantidadLista);//agregar la cantidad al modelo

        for (int i = 0; i < lista.size(); i++) {

            ObligacionesContrato p = lista.get(i);
run.setText(p.getObligaciones() );
run.addBreak();
           // modeloTabla.setValueAt(p, i, 0);
           // modeloTabla.setValueAt(p.getIdcontrato(), i, 1);
           // modeloTabla.setValueAt(p.getObligaciones(), i, 2);
           
        }
                 run.addBreak();
                 run.setText(" I. DEL TRABAJO.  El empleado prestará sus servicios en forma exclusiva a la Empresa, en las                                                                                                                          \n" +
"       oficinas, plantas e instalaciones que ésta tiene establecidas en la dirección:                                                                                                                                       \n" +
"       "+contrato.getLugartrabajo()+"-------------------------------------------------------------------------                                                                                                                          \n" +
"                                                                                                                                                                                                                            \n" +
"       Para la prestacion de servicios; siendo entendido que su cargo asignado será de:                                                                                                                                     \n" +
"       "+contrato.getCatPersonal().getCargos().getDescripcion()+"-------------------------------. Además de las obligaciones que le impongan las leyes laborales y sus reglamentos, el contrato colectivo, si lo hubiere, y el reglamento  interno                                                                                                                          \n" +
"       de trabajo, tendrá como obligaciones propias de su cargo las siguientes:                                                                                                                                             \n" +
"                                                                                                                                                                                                                            \n" +
"                                                                                                                                                                                                                            \n" +
"       Es expresamente entendido que el Empleado no podrá contratar o sub-contratar a otra persona                                                                                                                          \n" +
"       ya sea empleada o no del Patrono, para que éstas  presten  parte  o  la  totalidad  de  los                                                                                                                          \n" +
"       servicios objeto del presente contrato. ");
                 run.addBreak();
//                 run.setText("        II. DEL CONTRATO. "+contrato.getDevengos()+" .                                                                                                                                                  ");
run.addBreak();
run.setText("        III. DEL HORARIO.  El Número de horas que laborará  el  empleado  dentro  o  fuera  de  las                                                                                                                          \n" +
"       instalaciones será de ocho horas en la jornada diurna  y  de  siete  horas  en  la  jornada                                                                                                                          \n" +
"       nocturna. Podra trabajar tiempo extraordinario previa autorización del jefe de departamento                                                                                                                          \n" +
"       correspondiente.");
run.addBreak();
run.setText("        IV. DEL SALARIO: FORMA, PERIODO Y LUGAR DE PAGO.                                                                                                                                                                     \n" +
"       El pago del salario se hará en moneda de curso legal, por medio de depósito  en  cuenta  de                                                                                                                          \n" +
"       ahorro en uno de los Bancos del Sistema Financiero del país, por  quincenas  vencidas,  los                                                                                                                          \n" +
"       días quince y último de cada mes.");
run.addBreak();
run.setText("        V.  DE  LAS  OBLIGACIONES  DEL  EMPLEADO.   El  empleado  estará  obligado  a  observar  las                                                                                                                         \n" +
"       disposiciones del Reglamento Interno de la Empresa, a cumplir con las obligaciones  que  le                                                                                                                          \n" +
"       imponen las leyes laborales, y a prestar sus servicios  en  la  forma  que  la  Empresa  le                                                                                                                          \n" +
"       indique. Adicionalmente estará obligado a prestar su auxilio, servicio  o  colaboración  en                                                                                                                          \n" +
"       cualquier hora y tiempo que se necesite sin retribución obligatoria de la  Empresa,  cuando                                                                                                                          \n" +
"       por siniestro o riesgo inmediato, peligre la vida de  sus  jefes,  compañeros  de  trabajo,                                                                                                                          \n" +
"       materiales, edificios o los intereses de la Empresa en general.");
run.addBreak();
run.setText("        VI. DESIGNACION ESPECIAL. Para los efectos legales, a continuación se detallan las personas                                                                                                                          \n" +
"       y su parentesco, que dependen económicamente del Empleado:                                                                                                                                                         "
        + "                                         ");
run.addBreak();
run.setText("        VII. ESTIPULACION ESPECIAL. El Empleado se obliga en forma irrevocable ante el patrono a no                                                                                                                          \n" +
"       revelar, divulgar o difundir, facilitar, transmitir, bajo cualquier forma a ninguna persona                                                                                                                          \n" +
"       física o jurídica, sea esta pública o privada, y a no utilizar para su propio  beneficio  o                                                                                                                          \n" +
"       para beneficio de cualquier otra persona física o jurídica,  publica  o  privada,  toda  la                                                                                                                          \n" +
"       información relacionada con la Empresa, así como tambien las políticas y/o cualquiera  otra                                                                                                                          \n" +
"       información vinculada con sus funciones y/o el giro de la Empresa.  El  Empleado  asume  la                                                                                                                          \n" +
"       obligación de confidencialidad acordada en el presente por todo el  plazo  de  la  relación                                                                                                                          \n" +
"       laboral y por un plazo adicional de dos años contados a partir de la extinción del Contrato                                                                                                                          \n" +
"       individual  de  Trabajo.   La  violación  o  el  incumplimiento   de   la   obligación   de                                                                                                                          \n" +
"       confidencialidad a cargo del Empleado, así como la falsedad de la información  que  pudiere                                                                                                                          \n" +
"       brindar a terceros, facultará a la Empresa  para  disponer  a  la  extinción  del  presente                                                                                                                          \n" +
"       contrato con justa causa.");
run.addBreak();
run.setText(" VIII.En el presente contrato se entenderán incluidos, según el caso, los derechos y deberes                                                                                                                          \n" +
"       laborales establecidos  por  las  leyes  y  reglamentos  de  trabajo  pertinentes,  por  el                                                                                                                          \n" +
"       reglamento interno y por el o los contratos colectivos de trabajo que celebre  la  Empresa,                                                                                                                          \n" +
"       los reconocidos en las sentencias que resuelven conflictos  colectivos  de  trabajo  en  la                                                                                                                          \n" +
"       Empresa, por las políticas y lineamientos de la Empresa y los consagrados por la costumbre.");
run.addBreak();
run.setText("        IX. Este contrato sustituye cualquier otro convenio individual de trabajo anterior, ya  sea                                                                                                                          \n" +
"       escrito o verbal, que haya estado vigente entre la Empresa y el Empleado.                                                                                                                                            \n" +
"                                                                                                                                                                                                                            \n" +
"       En fe de lo cual firmamos el presente contrato por triplicado en San Salvador, en la  fecha                                                                                                                          \n" +
"       "+contrato.getFechacontrato()+"-- de mes------ del dos mil diecinueve(año)--.");
run.addBreak();
run.setText(" F.___________________________________        "+" F.___________________________________ ");
run.addBreak();
run.setText("      JARYVA, S.A. DE C.V.                             "+contrato.getCatPersonal().getNombres()+" "+contrato.getCatPersonal().getApellidos());
//                for(int i=0;i<10;i++){
//                    XWPFParagraph paragraphLista=document.createParagraph();
//                    XWPFRun runLista=paragraphLista.createRun();
//                    runLista.setText("para Jaryva "+i);
//                    paragraphLista.setNumID(BigInteger.ONE);
//                }

                document.write(output);
                
                output.close();
                DesktopNotify.showDesktopMessage("Exito", "El Contrato Individual de Trabajo ha sido Creado con Exito", DesktopNotify.SUCCESS);
            }
            //capturamos la excepcion
            catch (Exception e) {
               // JOptionPane.showMessageDialog(null, e.getMessage().toString());
                System.out.println("error "+e.getMessage());
                e.printStackTrace();
                DesktopNotify.showDesktopMessage("Error", "Ha ocurrido un Problema", DesktopNotify.ERROR);
            }
            //si estamos en linux a{adimos la respectiva extension a el respectivo gestor de documentos
            finally{
                try {
                    if(System.getProperty("os.name").equals("Linux")){
                        Runtime.getRuntime().exec("libreoffice"+fileWord.getAbsolutePath());
                    }
                    else{
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+fileWord.getAbsolutePath());
                    }
                }
                //si ocurre una excepcion
                catch (IOException ex) {
                    Logger.getLogger(frmContrato.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
            
            
        }else{
            DesktopNotify.showDesktopMessage("Informacion", "Debe de Seleccionar un Empleado", DesktopNotify.INFORMATION);
        }
    }
     public void retornaaño(Date date){
         DateFormat formatofecha=new SimpleDateFormat("dd/MM/yyy");
         String date1=formatofecha.format(date);
         System.out.println("dateee"+ date1);
         String dia=date1.substring(0, 1);
         System.out.println("dai"+dia);
         
         
     }
    
     public String edad(Date fecha){
         //format inicial 
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         String fechaTexto = formatter.format(fecha);
         System.out.println("fechatexto "+fechaTexto);
         
         
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // System.out.println("fecg "+fecha);

LocalDate fechaNac = LocalDate.parse(fechaTexto, fmt);
         //System.out.println("fechanac "+fechaNac);
LocalDate ahora = LocalDate.now();
//
Period periodo = Period.between(fechaNac, ahora);
System.out.printf("Tu edad es: %s años, %s meses y %s días",
                   periodo.getYears(), periodo.getMonths(), periodo.getDays());
numletras num=new numletras();
String edadd=num.Convertir(String.valueOf(periodo.getYears()), false);
         System.out.println("edad letras: "+edadd);
         return edadd;
    
     }
    
    
    // </editor-fold>
     //<editor-fold defaultstate="collapsed" desc="manipulacion">
     public void configdatefechacontrato(){
    Date fecha=new Date();
    jcfechacontrato.setDate(fecha);
    jcfechacontrato.setEnabled(false);
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
     
     
     private void cargarModeloTabla() {
        limpiarTabla();

        List<ContratoTrabajo> lista = contDao.listAll();//agregarlos registros a la lista
        int cantidadLista = lista.size();//cantidad de la lista
        modeloTabla.setRowCount(cantidadLista);//agregar la cantidad al modelo

        for (int i = 0; i < lista.size(); i++) {

            ContratoTrabajo p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getCatPersonal().getNombres()+" "+p.getCatPersonal().getApellidos(), i, 1);
             modeloTabla.setValueAt(p.getFechacontrato(), i, 2);
              modeloTabla.setValueAt(p.getFinalizacioncontrato(), i, 3);
               modeloTabla.setValueAt(p.getNacionalidad(), i, 4);
                modeloTabla.setValueAt(p.getCatPersonal().getDui(), i, 5);
                 modeloTabla.setValueAt(p.getExtendido(), i, 6);
                  modeloTabla.setValueAt(p.getLugartrabajo(), i, 7);
           
        }

    }
     
     
     
     private void cargarColumnas() {
         modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Nombre Completo");
        modeloTabla.addColumn("Fecha Contrato");
         modeloTabla.addColumn("Finalizacion");
          modeloTabla.addColumn("Nacionalidad");
          modeloTabla.addColumn("Numero DUI");
          modeloTabla.addColumn("Extendido");
          modeloTabla.addColumn("Lugar de Trabajo");
          
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
   
   public void limpiartextbox(){
//       txtdevengo.setText("");
       txtdui.setText("");
       txtextendido.setText("");
       txtgrupofam1.setText("");
//       txtgrupofam2.setText("");
      
       txtlugar.setText("");
       txtnacionalidad.setText("");
      // txtobligaciones.setText("");
      // txtobligaciones1.setText("");
       txtprofesion.setText("");
       btneliminar.setEnabled(false);
       grupfencontrado=null;
       contrato=null;
       btnguardar.setText("Guardar");
       modificar=false;
   }
   
   //desactivar buuttoms
    public void unbutton(){
        txtnacionalidad.setEditable(false);
            txtprofesion.setEditable(false);
            txtdui.setEditable(false);
    }
    
     
   
    public CatPersonal buscarpornombre(String cad) {
        List<CatPersonal> lista = perDao.Buscarpornombreyapellido(cad);
       
        if (lista.size() > 0) {
            
            for (CatPersonal cats : lista) {
                cat = cats;
            }
            txtdui.setText(cat.getDui());
            txtprofesion.setText(cat.getCargos().getDescripcion());
            txtnacionalidad.setText("Salvadoreña");
           // buscarcontrato(cat.getCodPersonal());
           return cat;

        } else {
            DesktopNotify.showDesktopMessage("Informacion", "No se encontro el registro con el codigo", DesktopNotify.ERROR);
            modificar = false;
            return null;
        }
    }
    
    public Grupofamiliar buscargrupofamiliar(int codigo){
        Grupofamiliar grp=null;
        List<Grupofamiliar> lista = gfDao.Buscar(codigo);
        if (lista.size() > 0) {
              for (Grupofamiliar gfam : lista) {
                grupfencontrado = gfam;
            }
            txtgrupofam1.setText(grupfencontrado.getGrupofamiliar());
            parentezco.setText(grupfencontrado.getParentezco());
        }
        return grp;
    }
    public ContratoTrabajo buscarcontrato(int codpersonal){
        ContratoTrabajo ctr=null;
        
        List<ContratoTrabajo> lista = contDao.Buscar(codpersonal);
        if (lista.size() > 0) {
           
             for (ContratoTrabajo ctrabajo : lista) {
                ctr = ctrabajo;
                contrato=ctr;
            }
             //txtdevengo.setText(ctr.getDevengos());
             txtextendido.setText(ctr.getExtendido());
             txtlugar.setText(ctr.getLugartrabajo());
             txtnacionalidad.setText(ctr.getNacionalidad());
            // txtobligaciones.setText(ctr.getObligacioneslab1());
            // txtobligaciones1.setText(ctr.getObligacioneslab2());
             jcenFecha.setDate(ctr.getEnfecha());
             jccontratofin.setDate(ctr.getFinalizacioncontrato());
             buscargrupofamiliar(ctr.getIdcontrato());
              modificar = true;
              btneliminar.setEnabled(true);
              btnguardar.setText("Modificar");
        }
            modificar=false;
            //return null;
        
        return ctr;
        
    }
public void guardar(){
    //personal
    
     ContratoTrabajo ctrabajo=new ContratoTrabajo();
    
      if(!txtbuscar.getText().isEmpty()){
     cat=buscarpornombre(txtbuscar.getText());
        System.out.println("cat "+cat.getNombres());
      if(cat!=null){
          try {
               System.out.println("cat dentro try" +cat.getNombres());
       
        
    ctrabajo.setCatPersonal(cat);
   // ctrabajo.setDevengos(txtdevengo.getText());
    ctrabajo.setEnfecha(jcenFecha.getDate());
    ctrabajo.setExtendido(txtextendido.getText());
    ctrabajo.setFechacontrato(jcfechacontrato.getDate());
    ctrabajo.setFinalizacioncontrato(jccontratofin.getDate());
    ctrabajo.setNacionalidad(txtnacionalidad.getText());
   // ctrabajo.setObligacioneslab1(txtobligaciones.getText());
    //ctrabajo.setObligacioneslab2(txtobligaciones1.getText());
    ctrabajo.setProfesion(txtprofesion.getText());
    ctrabajo.setLugartrabajo(txtlugar.getText());
    //ctrabajo.setLugartrabajo2(txtlugar.getText());
    contDao.Save(ctrabajo); 
    cargarModeloTabla();
    limpiartextbox();
     DesktopNotify.showDesktopMessage("Informacion", "El registro de contrato se ha ingresado correctamente", DesktopNotify.SUCCESS);
          } catch (Exception e) {
              System.out.println("error "+e.getMessage());
               DesktopNotify.showDesktopMessage("Informacion", "El registro de contrato no se ha ingresado correctamente", DesktopNotify.ERROR);
          }
      
    
        try {
             Grupofamiliar gffamiliar=new Grupofamiliar();
    gffamiliar.setGrupofamiliar(txtgrupofam1.getText());
    gffamiliar.setParentezco(parentezco.getText());
    gffamiliar.setContratoTrabajo(ctrabajo);
    gfDao.Save(gffamiliar); 
     limpiartextbox();
     DesktopNotify.showDesktopMessage("Informacion", "El registro de familia se ha ingresado correctamente", DesktopNotify.SUCCESS);
     
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
            DesktopNotify.showDesktopMessage("Informacion", "El registro de familia no se ha ingresado correctamente", DesktopNotify.ERROR);
        }
    
//    if(!txtgrupofam2.getText().isEmpty()){
//        GrupofamiliarDao gfd=new GrupofamiliarDao();
//        Grupofamiliar gfamiliar2=new Grupofamiliar();
//        gfamiliar2.setContratoTrabajo(ctrabajo);
//        gfamiliar2.setGrupofamiliar(txtgrupofam2.getText());
//        gfamiliar2.setParentezco(parentezco1.getText());
//        gfd.Save(gfamiliar2);
//    }
   
     DesktopNotify.showDesktopMessage("Informacion", "El registro de contrato se ha ingresado correctamente", DesktopNotify.SUCCESS);
   
    }else{
         DesktopNotify.showDesktopMessage("Informacion", "No se encontro el registro con el codigo", DesktopNotify.ERROR);
    }
    }
       
    
   
     
     
    
//    else{
//      
//       modificar();
//    }
    
     //CatPersonal cper=cat;   
    
   
   
}
    public void modificar(){
         
     //cat=buscarpornombre(txtbuscar.getText());
  
        try {
           // contrato.setCatPersonal(cat);
    //contrato.setDevengos(txtdevengo.getText());
    contrato.setEnfecha(jcenFecha.getDate());
    contrato.setExtendido(txtextendido.getText());
    contrato.setFechacontrato(jcfechacontrato.getDate());
    contrato.setFinalizacioncontrato(jccontratofin.getDate());
    contrato.setNacionalidad(txtnacionalidad.getText());
   // contrato.setObligacioneslab1(txtobligaciones.getText());
    //contrato.setObligacioneslab2(txtobligaciones1.getText());
    contrato.setProfesion(txtprofesion.getText());
    contrato.setLugartrabajo(txtlugar.getText());
   // contrato.setLugartrabajo2(txtlugar.getText());
   edad(contrato.getCatPersonal().getFechaNacimiento());
    
    contDao.Update(contrato);
   
    
    DesktopNotify.showDesktopMessage("Informacion", "El registro  se ha modificado correctamente", DesktopNotify.SUCCESS);
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
             DesktopNotify.showDesktopMessage("Informacion", "El registro  no se ha modificado correctamente", DesktopNotify.ERROR);
        }
        try {
             grupfencontrado.setGrupofamiliar(txtgrupofam1.getText());
    grupfencontrado.setParentezco(parentezco.getText());
    gfDao.Update(grupfencontrado);
    cargarModeloTabla();
    limpiartextbox();
        } catch (Exception e) {
             System.out.println("error: "+e.getMessage());
             DesktopNotify.showDesktopMessage("Informacion", "El registro  no se ha modificado correctamente", DesktopNotify.ERROR);
        }
 
    
    }
     public void modificar1(){
         if(contrato!=null && grupfencontrado!=null){
            try {
               // contrato.setDevengos(txtdevengo.getText());
    contrato.setEnfecha(jcenFecha.getDate());
    contrato.setExtendido(txtextendido.getText());
    contrato.setFechacontrato(jcfechacontrato.getDate());
    contrato.setFinalizacioncontrato(jccontratofin.getDate());
    contrato.setNacionalidad(txtnacionalidad.getText());
   // contrato.setObligacioneslab1(txtobligaciones.getText());
    //contrato.setObligacioneslab2(txtobligaciones1.getText());
    contrato.setProfesion(txtprofesion.getText());
    contrato.setLugartrabajo(txtlugar.getText());
    //contrato.setLugartrabajo2(txtlugar.getText());
    
      grupfencontrado.setGrupofamiliar(txtgrupofam1.getText());
    grupfencontrado.setParentezco(parentezco.getText());
    gfDao.Update(grupfencontrado);
    cargarModeloTabla();
                
                cargarModeloTabla();
                new rojerusan.RSNotifyFade("Proceso Completado", "El Registro fue modificado Correctamente: "+contrato.getCatPersonal().getNombres(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                //JOptionPane.showMessageDialog(null,"El Registro fue Eliminado Correctamente: "+cargoselect.getDescripcion() );
                limpiartextbox();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null,"No se pudo eliminar el Registro" );
                DesktopNotify.showDesktopMessage("Error", "No se pudo modificar el registro", DesktopNotify.ERROR);
                System.out.println("Error: "+e.getMessage());
            }

        }else{
            DesktopNotify.showDesktopMessage("Informe", "Debes seleccionar un Registro", DesktopNotify.INFORMATION);
        }
    }
    
    
    public void eliminar(){
         if(contrato!=null && grupfencontrado!=null){
            try {
               gfDao.Delete(grupfencontrado);
                contDao.Delete(contrato);
                
                cargarModeloTabla();
                limpiartextbox();
                new rojerusan.RSNotifyFade("Proceso Completado", "El Registro fue Eliminado Correctamente: "+contrato.getCatPersonal().getNombres(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
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
   
   
      // </editor-fold> 

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        estiloTablaHeader1 = new rojeru_san.complementos.EstiloTablaHeader();
        txtdui = new app.bolivia.swing.JCTextField();
        txtextendido = new app.bolivia.swing.JCTextField();
        txtnacionalidad = new app.bolivia.swing.JCTextField();
        txtprofesion = new app.bolivia.swing.JCTextField();
        jcenFecha = new com.toedter.calendar.JDateChooser();
        jcfechacontrato = new com.toedter.calendar.JDateChooser();
        jccontratofin = new com.toedter.calendar.JDateChooser();
        txtbuscar = new app.bolivia.swing.JCTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtlugar = new app.bolivia.swing.JCTextField();
        jLabel4 = new javax.swing.JLabel();
        txtgrupofam1 = new app.bolivia.swing.JCTextField();
        parentezco = new app.bolivia.swing.JCTextField();
        btnimprimir = new rojeru_san.complementos.ButtonHover();
        btnimprimir1 = new rojeru_san.complementos.ButtonHover();
        btneliminar = new rojeru_san.complementos.ButtonHover();
        btnguardar = new rojeru_san.complementos.ButtonHover();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablacontrato = new rojeru_san.complementos.TableMetro();
        btnimprimir2 = new rojeru_san.complementos.ButtonHover();
        btndevengos = new rojeru_san.complementos.ButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        txtdui.setToolTipText("Numero DUI");
        txtdui.setPlaceholder("Numero de DUI");

        txtextendido.setToolTipText("Numero DUI");
        txtextendido.setPlaceholder("Extendido En");

        txtnacionalidad.setToolTipText("Numero DUI");
        txtnacionalidad.setPlaceholder("Nacionalidad");

        txtprofesion.setToolTipText("Numero DUI");
        txtprofesion.setPlaceholder("Profesion");

        jcenFecha.setForeground(new java.awt.Color(102, 102, 255));
        jcenFecha.setToolTipText("En Fecha");
        jcenFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jcenFechaPropertyChange(evt);
            }
        });

        jcfechacontrato.setForeground(new java.awt.Color(102, 102, 255));
        jcfechacontrato.setToolTipText("Fecha Contrato");
        jcfechacontrato.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jcfechacontratoPropertyChange(evt);
            }
        });

        jccontratofin.setForeground(new java.awt.Color(102, 102, 255));
        jccontratofin.setToolTipText("Finalizacion de Contrato");
        jccontratofin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jccontratofinPropertyChange(evt);
            }
        });

        txtbuscar.setToolTipText("Buscar Persona");
        txtbuscar.setPlaceholder("Buscar...");
        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtbuscarPropertyChange(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscarKeyPressed(evt);
            }
        });

        jLabel1.setText("Finalizacion de Contrato");

        jLabel2.setText("Fecha Contrato");

        jLabel3.setText("En Fecha");

        txtlugar.setToolTipText("Numero DUI");
        txtlugar.setPlaceholder("Lugar de Trabajo");

        jLabel4.setText("Grupo Familiar");

        txtgrupofam1.setToolTipText("Numero DUI");
        txtgrupofam1.setPlaceholder("Grupo Familiar");

        parentezco.setToolTipText("Numero DUI");
        parentezco.setPlaceholder("Parentezco");

        btnimprimir.setBackground(new java.awt.Color(0, 0, 204));
        btnimprimir.setText("PDF");
        btnimprimir.setToolTipText("Guardar Registro");
        btnimprimir.setColorHover(new java.awt.Color(51, 51, 255));
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        btnimprimir1.setBackground(new java.awt.Color(0, 0, 204));
        btnimprimir1.setText("Imprimir Word");
        btnimprimir1.setToolTipText("Guardar Registro");
        btnimprimir1.setColorHover(new java.awt.Color(51, 51, 255));
        btnimprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir1ActionPerformed(evt);
            }
        });

        btneliminar.setBackground(new java.awt.Color(0, 0, 204));
        btneliminar.setText("Eliminar");
        btneliminar.setToolTipText("Guardar Registro");
        btneliminar.setColorHover(new java.awt.Color(51, 51, 255));
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
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
                        contrato = (ContratoTrabajo) modeloTabla.getValueAt(filaSeleccionada,0);

                        // LLenamos los textBoxs atraves del objeto ...
                        jcfechacontrato.setDate(contrato.getFechacontrato());
                        jcfechacontrato.setDate(contrato.getFinalizacioncontrato());
                        jcenFecha.setDate(contrato.getFinalizacioncontrato());
                        txtlugar.setText(contrato.getLugartrabajo());
                        //txtdevengo.setText(contrato.getDevengos());
                        txtprofesion.setText(contrato.getProfesion());
                        //txtobligaciones.setText(contrato.getObligacioneslab1());
                        //txtobligaciones1.setText(contrato.getObligacioneslab2());
                        txtnacionalidad.setText(contrato.getNacionalidad());
                        txtdui.setText(contrato.getCatPersonal().getDui());
                        txtextendido.setText(contrato.getExtendido());
                        buscargrupofamiliar(contrato.getIdcontrato());
                        jccontratofin.setDate(contrato.getFinalizacioncontrato());

                        //abilitar boton para actualizar
                        //btnguardar.setEnabled(false);
                        btnguardar.setText("Modificar");
                        modificar=true;
                        btneliminar.setEnabled(true);

                    }

                }
            }

        );
        jScrollPane1.setViewportView(tablacontrato);

        btnimprimir2.setBackground(new java.awt.Color(0, 0, 204));
        btnimprimir2.setText("Ag. Obligaciones");
        btnimprimir2.setToolTipText("Guardar Registro");
        btnimprimir2.setColorHover(new java.awt.Color(51, 51, 255));
        btnimprimir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir2ActionPerformed(evt);
            }
        });

        btndevengos.setBackground(new java.awt.Color(0, 0, 204));
        btndevengos.setText("Ag. Devengos");
        btndevengos.setToolTipText("Guardar Registro");
        btndevengos.setColorHover(new java.awt.Color(51, 51, 255));
        btndevengos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndevengosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnimprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(txtlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgrupofam1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtextendido, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parentezco, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcfechacontrato, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jccontratofin, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtprofesion, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtdui, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcenFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btndevengos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                .addComponent(btnimprimir2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcfechacontrato, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprofesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndevengos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtlugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtextendido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtgrupofam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(parentezco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnimprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jccontratofin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcenFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcenFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jcenFechaPropertyChange

    }//GEN-LAST:event_jcenFechaPropertyChange

    private void jcfechacontratoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jcfechacontratoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jcfechacontratoPropertyChange

    private void jccontratofinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jccontratofinPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jccontratofinPropertyChange

    private void txtbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyPressed
       if (evt.getKeyCode() == evt.VK_ENTER) {
            buscarpornombre(txtbuscar.getText());
            modificar = true;
        }
    }//GEN-LAST:event_txtbuscarKeyPressed

    private void txtbuscarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtbuscarPropertyChange
        
    }//GEN-LAST:event_txtbuscarPropertyChange

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
//modificar();
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void btnimprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir1ActionPerformed
        crearword();
        //retornaaño(jcenFecha.getDate());
    }//GEN-LAST:event_btnimprimir1ActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
      if(contrato!=null && grupfencontrado!=null){
           modificar();
       }
      //else if(contrato==null && grupfencontrado==null){
//           guardar();  
        else{
            guardar();
        }
       
    }//GEN-LAST:event_btnguardarActionPerformed

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void btnimprimir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir2ActionPerformed
        if(contrato!=null){
            frmvistaobligaciones vista=new frmvistaobligaciones();
        vista.setVisible(true);
        vista.setcontrato(contrato);
        }else{
            DesktopNotify.showDesktopMessage("Informe", "Debes seleccionar un Registro", DesktopNotify.INFORMATION);
        }
    }//GEN-LAST:event_btnimprimir2ActionPerformed

    private void btndevengosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndevengosActionPerformed
         if(contrato!=null){
            frmvistadevengos vista=new frmvistadevengos();
        vista.setVisible(true);
        vista.setcontrato(contrato);
        }else{
            DesktopNotify.showDesktopMessage("Informe", "Debes seleccionar un Registro", DesktopNotify.INFORMATION);
        }
    }//GEN-LAST:event_btndevengosActionPerformed

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
            java.util.logging.Logger.getLogger(frmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmContrato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.ButtonHover btndevengos;
    private rojeru_san.complementos.ButtonHover btneliminar;
    private rojeru_san.complementos.ButtonHover btnguardar;
    private rojeru_san.complementos.ButtonHover btnimprimir;
    private rojeru_san.complementos.ButtonHover btnimprimir1;
    private rojeru_san.complementos.ButtonHover btnimprimir2;
    private rojeru_san.complementos.EstiloTablaHeader estiloTablaHeader1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jccontratofin;
    private com.toedter.calendar.JDateChooser jcenFecha;
    private com.toedter.calendar.JDateChooser jcfechacontrato;
    private app.bolivia.swing.JCTextField parentezco;
    private rojeru_san.complementos.TableMetro tablacontrato;
    private app.bolivia.swing.JCTextField txtbuscar;
    private app.bolivia.swing.JCTextField txtdui;
    private app.bolivia.swing.JCTextField txtextendido;
    private app.bolivia.swing.JCTextField txtgrupofam1;
    private app.bolivia.swing.JCTextField txtlugar;
    private app.bolivia.swing.JCTextField txtnacionalidad;
    private app.bolivia.swing.JCTextField txtprofesion;
    // End of variables declaration//GEN-END:variables
}
