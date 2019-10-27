/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devs.vistas;

import com.devs.auxiliar.ConexionBD;
import static com.devs.vistas.frmCargos.cn;
import dao.PersonalDao;
import ds.desktop.notify.DesktopNotify;
import entities.CatPersonal;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import rojerusan.RSNotifyFade;

/**
 *
 * @author DiegoArevalo
 */
public class frmMostrar extends javax.swing.JFrame {
  // Excel work book
     public static ConexionBD cl = new ConexionBD();
    public static Connection cn = cl.conexion();
    public static PreparedStatement ps;
    
    
	private HSSFWorkbook workbook;
	
	// Fonts
	private HSSFFont headerFont;
	private HSSFFont contentFont;
	
	// Styles
	private HSSFCellStyle headerStyle;
	private HSSFCellStyle oddRowStyle;
	private HSSFCellStyle evenRowStyle;
	
	// Integer to store the index of the next row
	private int rowIndex;
    
    
    
    CatPersonal catsel = null;
    PersonalDao perdao = new PersonalDao();
    DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }
    };

    /**
     * Creates new form frmMostrar
     */
    public frmMostrar() {
        cargarColumnas();
        cargarModeloTabla();

        initComponents();

        this.setResizable(false);
        //DesControlesInicio();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(1);
        this.setTitle("Mantenimiento de Personal");
    }
    //<editor-fold defaultstate="collapsed" desc="test">
    public HSSFWorkbook generateExcel() {
		
		// Initialize rowIndex
		rowIndex = 0;
		
		// New Workbook
		workbook = new HSSFWorkbook();
		
		// Generate fonts
		headerFont  = createFont(HSSFColor.WHITE.index, (short)14, true);
		contentFont = createFont(HSSFColor.BLACK.index, (short)10, true);
		
		// Generate styles
		headerStyle  = createStyle(headerFont,  HSSFCellStyle.ALIGN_CENTER, HSSFColor.BLUE.index, true, HSSFColor.WHITE.index);
		oddRowStyle  = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT,   HSSFColor.GREY_25_PERCENT.index, true, HSSFColor.GREY_80_PERCENT.index);
		evenRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT,   HSSFColor.GREY_40_PERCENT.index, true, HSSFColor.GREY_80_PERCENT.index);
		
		// New sheet
		HSSFSheet sheet = workbook.createSheet("Cargos");
		
		// Table header
		HSSFRow      headerRow    = sheet.createRow( rowIndex++ );
		List<String> headerValues = getTableHeaders();
		
		HSSFCell headerCell = null;
		for (int i = 0; i < headerValues.size(); i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellStyle(headerStyle);
			headerCell.setCellValue( headerValues.get(i) );
		}
		
		
		// Table content
		HSSFRow  contentRow  = null;
		HSSFCell contentCell = null;
		
		// Obtain table content values
		List<List<String>> contentRowValues =getTableContent(20);
		for (List<String> rowValues : contentRowValues) {
			
			// At each row creation, rowIndex must grow one unit
			contentRow = sheet.createRow( rowIndex++ );
			for (int i = 0; i < rowValues.size(); i++) {
				contentCell = contentRow.createCell(i);
				contentCell.setCellValue( rowValues.get(i) );
				
				// Style depends on if row is odd or even
				contentCell.setCellStyle( rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle );
			}
		}
		
		
		// Autosize columns
		for (int i = 0; i < headerValues.size(); sheet.autoSizeColumn(i++));
		
		return workbook;
	}
	
	
	/**
	 * Create a new font on base workbook
	 * 
	 * @param fontColor       Font color (see {@link HSSFColor})
	 * @param fontHeight      Font height in points
	 * @param fontBold        Font is boldweight (<code>true</code>) or not (<code>false</code>)
	 * 
	 * @return New cell style
	 */
	private HSSFFont createFont(short fontColor, short fontHeight, boolean fontBold) {
		
		HSSFFont font = workbook.createFont();
		font.setBold(fontBold);
		font.setColor(fontColor);
		font.setFontName("Arial");
		font.setFontHeightInPoints(fontHeight);
		
		return font;
	}
	
	
	/**
	 * Create a style on base workbook
	 * 
	 * @param font            Font used by the style
	 * @param cellAlign       Cell alignment for contained text (see {@link HSSFCellStyle})
	 * @param cellColor       Cell background color (see {@link HSSFColor})
	 * @param cellBorder      Cell has border (<code>true</code>) or not (<code>false</code>)
	 * @param cellBorderColor Cell border color (see {@link HSSFColor})
	 * 
	 * @return New cell style
	 */
	private HSSFCellStyle createStyle(HSSFFont font, short cellAlign, short cellColor, boolean cellBorder, short cellBorderColor) {

		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(cellAlign);
		style.setFillForegroundColor(cellColor);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		if (cellBorder) {
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			style.setTopBorderColor(cellBorderColor);
			style.setLeftBorderColor(cellBorderColor);
			style.setRightBorderColor(cellBorderColor);
			style.setBottomBorderColor(cellBorderColor);
		}
		
		return style;
	}
        
        public static List<String> getTableHeaders() {
        List<String> tableHeader = new ArrayList<String>();
        //TITULOS DE LAS COLUMNAS
        tableHeader.add("Cod Programacion");
        tableHeader.add("Cod Jaryva");
        tableHeader.add("primer nombre");
        tableHeader.add("segundo nombre");
        tableHeader.add("segundo apellido");
        tableHeader.add("segundo segundo");
        tableHeader.add("direccion");
        tableHeader.add("genero");
        tableHeader.add("estado civil");
        tableHeader.add("telefono 1");
        tableHeader.add("telefono 2");
        tableHeader.add("telefono 3");
         tableHeader.add("Ingreso");
          tableHeader.add("Nacimiento");
           tableHeader.add("Depto");
            tableHeader.add("Descripcion");
             tableHeader.add("Cargo");
              tableHeader.add("Descripcion");
               tableHeader.add("Dui");
                tableHeader.add("Nombre s/ DUI");
                 tableHeader.add("NIT");
                  tableHeader.add("Nombre s NIT");
                  tableHeader.add("ISSS");
                  tableHeader.add("Nombre s ISSS");
                  tableHeader.add("NUP");
                  tableHeader.add("AFP");
                  tableHeader.add("Cuenta Bancaria");
                  tableHeader.add("Banco");
                  tableHeader.add("Obserbacion1");
                  tableHeader.add("Oberbacion2");
                  tableHeader.add("Obserbacion 3");
                  
                  
       

        return tableHeader;
    }
        
//        public static List<List<String>> getTableContent(int numberOfRows) {
//        try {
//            if (numberOfRows <= 0) {
//                throw new IllegalArgumentException("The number of rows must be a positive integer");
//            }
//CargoDao carg=new CargoDao();
//            List<List<String>> tableContent = new ArrayList<List<String>>();
//           List<Cargos> lista=carg.listAll();
//            List<String> row = null;
//          // int cantidadLista = lista.size();//cantidad de la lista
//       for (int i = 0; i < lista.size(); i++) {
//           Cargos p = lista.get(i);
//            row.add(p.getCodCargo().toString());
//                row.add(p.getDescripcion());
//                tableContent.add(row = new ArrayList<String>());
//           //tableContent.add(lista = new ArrayList<Cargos>());
//          
//       }
//           
//            
//            return tableContent;
//        } catch (Exception ex) {
//            System.out.println("error "+ex.getMessage());
//        }
//
//        return null;
//    }
         private void generarExcel() {
        HSSFWorkbook workbook = generateExcel();
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos Excel (*.xls)", "xls");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("GUARDAR ARCHIVO");
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

            try {
                File archivo = new File(fileChooser.getSelectedFile().getAbsolutePath());

                OutputStream out = null;
                if (getFileExtension(archivo)) {
                    out = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath());
                } else {
                    out = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath() + ".xls");
                }

                JOptionPane.showMessageDialog(this, "Archivo generado con éxito");

                workbook.write(out);
                workbook.close();
                out.flush();
                out.close();
            } catch (IOException ex) {
                System.out.println("error"+ex.getMessage());
            }

        }
        
    }
           private boolean getFileExtension(File file) {
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }

        if (ext != null) {
            return true;
        } else {
            return false;
        }
    }
           public static List<List<String>> getTableContent(int numberOfRows) {
        try {
            if (numberOfRows <= 0) {
                throw new IllegalArgumentException("The number of rows must be a positive integer");
            }

            List<List<String>> tableContent = new ArrayList<List<String>>();

            String SQL = "SELECT * FROM cat_personal";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            int i = 0;
            List<String> row = null;
            while (rs.next()) {
                tableContent.add(row = new ArrayList<String>());

                row.add(rs.getString("cod_cargo"));
                row.add(rs.getString("descripcion"));
               

                i++;
            }
            return tableContent;
        } catch (SQLException ex) {
            System.out.println("error"+ex.getMessage());
        }

        return null;
    }



        // </editor-fold> 

    //realizar busqueda
    public void buscar() {
        String parametro = this.txtbuscar.getText();
        List<CatPersonal> lista = perdao.listAllParameter(parametro);

        int numFila = lista.size();

        modeloTabla.setNumRows(numFila);

        for (int i = 0; i < lista.size(); i++) {
            CatPersonal p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getCodJaryva(), i, 1);
            modeloTabla.setValueAt(p.getNombres() + " " + p.getApellidos(), i, 2);
            modeloTabla.setValueAt(p.getDireccion(), i, 3);
            modeloTabla.setValueAt(p.getFechaNacimiento(), i, 4);
            modeloTabla.setValueAt(p.getTelefono1(), i, 5);
            modeloTabla.setValueAt(p.getDui(), i, 6);
            modeloTabla.setValueAt(p.getNit(), i, 7);
            modeloTabla.setValueAt(p.getIsss(), i, 8);
            modeloTabla.setValueAt(p.getFechaIngreso(), i, 9);
            modeloTabla.setValueAt(p.getGenero(), i, 10);
            //modeloTabla.setValueAt(p.getDepartamentos(), i, 11);
            modeloTabla.setValueAt(p.getAfp(), i, 11);
            modeloTabla.setValueAt(p.getCargos(), i, 12);
            modeloTabla.setValueAt(p.getEstadoCivil(), i, 13);

        }
    }

    //limpiar tabla
    private void limpiarTabla() {
        int numFila = modeloTabla.getRowCount(); // cantidad de filas de la tabla
        if (numFila > 0) {
            // debe de ser i mayor o igual a cero
            for (int i = numFila - 1; i >= 0; i--) { // recorre de mayor a menor
                modeloTabla.removeRow(i); // borra la fila encontrada en la iteracion
            }
        }
    }
    //cargar modelo de la tabla

    private void cargarModeloTabla() {
        limpiarTabla();

        List<CatPersonal> lista = perdao.listAll();//agregarlos registros a la lista
        int cantidadLista = lista.size();//cantidad de la lista
        modeloTabla.setRowCount(cantidadLista);//agregar la cantidad al modelo

        for (int i = 0; i < lista.size(); i++) {

            CatPersonal p = lista.get(i);

            modeloTabla.setValueAt(p, i, 0);
            modeloTabla.setValueAt(p.getCodJaryva(), i, 1);
            modeloTabla.setValueAt(p.getNombres() + " " + p.getApellidos(), i, 2);
            modeloTabla.setValueAt(p.getDireccion(), i, 3);
            modeloTabla.setValueAt(p.getFechaNacimiento(), i, 4);
            modeloTabla.setValueAt(p.getTelefono1(), i, 5);
            modeloTabla.setValueAt(p.getDui(), i, 6);
            modeloTabla.setValueAt(p.getNit(), i, 7);
            modeloTabla.setValueAt(p.getIsss(), i, 8);
            modeloTabla.setValueAt(p.getFechaIngreso(), i, 9);
            modeloTabla.setValueAt(p.getGenero(), i, 10);
            //modeloTabla.setValueAt(p.getDepartamentos(), i, 11);
            modeloTabla.setValueAt(p.getAfp(), i, 11);
            modeloTabla.setValueAt(p.getCargos(), i, 12);
            modeloTabla.setValueAt(p.getEstadoCivil(), i, 13);

        }

    }
//     private void ocultarcolumns(){
//         tablapersonal.getColumnModel().getColumn(0).setPreferredWidth(0);
//     }

    public void popup() {
        JPopupMenu popup = new JPopupMenu();
        popup.setBorderPainted(true);

        JMenuItem item = new JMenuItem("Eliminar");

        JMenuItem item2 = new JMenuItem("Reporte");

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

            }
        }
        );

        popup.add(item);
        popup.add(item2);
        tablapersonal.setComponentPopupMenu(popup);
    }

    //cargar las cabeceras de la tabla
    private void cargarColumnas() {
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Cod. Jaryva");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Direccion");
        modeloTabla.addColumn("Fecha Nac.");
        modeloTabla.addColumn("Tel.");
        modeloTabla.addColumn("DUI");
        modeloTabla.addColumn("NIT");
        modeloTabla.addColumn("ISSS");
        modeloTabla.addColumn("Fecha Ingreso");
        modeloTabla.addColumn("Genero");
        //modeloTabla.addColumn("Departamento");
        modeloTabla.addColumn("AFP");
        modeloTabla.addColumn("Cargo");
        modeloTabla.addColumn("Estado");
    }

    public void eliminar() {
        if (catsel != null) {
            try {
                perdao.Delete(catsel);
                cargarModeloTabla();
                new rojerusan.RSNotifyFade("Proceso Completado", "El Registro fue Eliminado Correctamente: " + catsel.getNombres() + " " + catsel.getApellidos(), 6, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.INFORMATION).setVisible(true);
                //JOptionPane.showMessageDialog(null,"El Registro fue Eliminado Correctamente: "+cargoselect.getDescripcion() );

            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null,"No se pudo eliminar el Registro" );
                DesktopNotify.showDesktopMessage("Error", "No se pudo eliminar el registro", DesktopNotify.ERROR);

                System.out.println("Error: " + e.getMessage());
            }

        } else {
            DesktopNotify.showDesktopMessage("Informe", "Debes seleccionar un Registro", DesktopNotify.INFORMATION);
        }
    }
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablapersonal = new rojeru_san.complementos.TableMetro();
        txtbuscar = new app.bolivia.swing.JCTextField();
        btnEliminar = new rojeru_san.complementos.ButtonHover();
        btnrepexcel = new rojeru_san.complementos.ButtonHover();
        btnreportesistema = new rojeru_san.complementos.ButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablapersonal.setModel(modeloTabla);
        tablapersonal.setColorBackgoundHead(new java.awt.Color(0, 61, 113));
        tablapersonal.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        //Este codigo se coloca en la tabla en su propiedad Post-Init-Code
        tablapersonal.getSelectionModel().addListSelectionListener( // capturamos la linea seleccionada

            new ListSelectionListener(){ // Instanciamos

                public void valueChanged (ListSelectionEvent event){ // evento de la tabla
                    if(!event.getValueIsAdjusting() && (tablapersonal.getSelectedRow()>=0) ) {
                        int filaSeleccionada = tablapersonal.getSelectedRow(); // tomamos la fila seleccionda
                        /*creamos el obj y le pasamos la fila seleccionada y la columna 1 xq ayi 
                        esta alojado el obj marca en el campo nombre....
                        */     
                        catsel = (CatPersonal) modeloTabla.getValueAt(filaSeleccionada,0);

                        //abilitar boton para actualizar

                        btnEliminar.setEnabled(true);

                    }

                }
            }

        );
        tablapersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablapersonalKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablapersonal);

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

        btnEliminar.setBackground(new java.awt.Color(204, 0, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("Eliminar Registro");
        btnEliminar.setColorHover(new java.awt.Color(255, 51, 51));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnrepexcel.setBackground(new java.awt.Color(204, 0, 0));
        btnrepexcel.setText("Reporte");
        btnrepexcel.setToolTipText("Eliminar Registro");
        btnrepexcel.setColorHover(new java.awt.Color(255, 51, 51));
        btnrepexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepexcelActionPerformed(evt);
            }
        });

        btnreportesistema.setBackground(new java.awt.Color(204, 0, 0));
        btnreportesistema.setText("Sistema");
        btnreportesistema.setToolTipText("Eliminar Registro");
        btnreportesistema.setColorHover(new java.awt.Color(255, 51, 51));
        btnreportesistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreportesistemaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(220, Short.MAX_VALUE)
                .addComponent(btnrepexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(btnreportesistema, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(204, 204, 204))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrepexcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreportesistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        limpiarTabla();
        buscar();
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
//        if(txtbuscar.getText().length()>=2){
//            limpiarTabla();
//            buscar();
//        }
    }//GEN-LAST:event_txtbuscarKeyTyped

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tablapersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablapersonalKeyReleased
        if (evt.getKeyCode() == evt.VK_ENTER) {
            try {

                CatPersonal cper = new CatPersonal();
                cper = (CatPersonal) tablapersonal.getValueAt(tablapersonal.getSelectedRow(), 0);
                String nombre = tablapersonal.getValueAt(tablapersonal.getSelectedRow(), 2).toString();
                //String apellido=tablapersonal.getValueAt(tablapersonal.getSelectedRow(),2).toString();
                String direccion = tablapersonal.getValueAt(tablapersonal.getSelectedRow(), 3).toString();
                String telefono = tablapersonal.getValueAt(tablapersonal.getSelectedRow(), 5).toString();
                String nit = tablapersonal.getValueAt(tablapersonal.getSelectedRow(), 7).toString();
                String iss = tablapersonal.getValueAt(tablapersonal.getSelectedRow(), 8).toString();;
                String dui = tablapersonal.getValueAt(tablapersonal.getSelectedRow(), 6).toString();
                String codjaryva = tablapersonal.getValueAt(tablapersonal.getSelectedRow(), 1).toString();

                cper.setNombres(nombre);
                cper.setCodJaryva(codjaryva);
                cper.setNit(nit);
                cper.setDui(dui);
                cper.setIsss(iss);
                cper.setDireccion(direccion);
                cper.setTelefono1(telefono);
                perdao.Update(cper);
                DesktopNotify.showDesktopMessage("Exito", "Registro Actualizado Correctamente", DesktopNotify.SUCCESS);
            } catch (Exception e) {
                DesktopNotify.showDesktopMessage("Informe", "Ese Dato ingresado no puede ser modificado ", DesktopNotify.INFORMATION);
                System.out.println("error: " + e.getMessage());

            }

        } else if (evt.getKeyCode() == evt.VK_ESCAPE) {
            try {
                CatPersonal cper = new CatPersonal();
                cper = (CatPersonal) tablapersonal.getValueAt(tablapersonal.getSelectedRow(), 0);
                perdao.Delete(cper);

                cargarModeloTabla();
                DesktopNotify.showDesktopMessage("Informe", "Haz Eliminado Correctamente el registro" + cper.getNombres(), DesktopNotify.INFORMATION);
                //cper=null;
            } catch (Exception e) {
                DesktopNotify.showDesktopMessage("Informe", "No se ha podido eliminar el registro", DesktopNotify.ERROR);
                System.out.println("error: " + e.getMessage());
            }

        }
    }//GEN-LAST:event_tablapersonalKeyReleased

    private void btnrepexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepexcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnrepexcelActionPerformed

    private void btnreportesistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreportesistemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnreportesistemaActionPerformed

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
            java.util.logging.Logger.getLogger(frmMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMostrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.ButtonHover btnEliminar;
    private rojeru_san.complementos.ButtonHover btnrepexcel;
    private rojeru_san.complementos.ButtonHover btnreportesistema;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.TableMetro tablapersonal;
    private app.bolivia.swing.JCTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
