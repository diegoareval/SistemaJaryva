
package ExcelUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
public class ExcelGenerator {
    // Excel work book
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
	
        public HSSFWorkbook generateExcel() {
		
		// Initialize rowIndex
		rowIndex = 4;
		
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
		HSSFSheet sheet = workbook.createSheet("PERSONAS");
		
		// Table header
		HSSFRow      headerRow    = sheet.createRow( rowIndex++ );
		List<String> headerValues = DataProvider.getTableHeaders();
		
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
                
		List<List<String>> contentRowValues = DataProvider.getTableContent(20);
                contentRow=sheet.createRow(0);
                contentCell=contentRow.createCell(0);
                contentCell.setCellValue( "JARYVA, SA DE CV" );
                 contentRow=sheet.createRow(1);
                contentCell=contentRow.createCell(0);
                Date fecha=new Date();
                SimpleDateFormat fechaformat=new SimpleDateFormat();
                String fec=fechaformat.format(fecha);
              
                
                contentCell.setCellValue( "LISTADO MAESTRO DE EMPLEADOS: "+fec);
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
    
}
