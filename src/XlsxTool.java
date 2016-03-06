import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class XlsxTool {
	String [] SupportedFormat = {".html",".txt",".pdf"};
	private void XlsxToHtml(String Path,String Target) {
		File file = new File(Path);
		XLS2HTMLParser parser = new XLS2HTMLParser(file,Target);
		parser.run();
	}
	void getTextFromXlsx(String Path,String Target)  {
		try{
		File file = new File(Path);
		BufferedWriter writer;
		Workbook workbook;
			workbook = new XSSFWorkbook(new FileInputStream(file));
		writer = new BufferedWriter(new FileWriter(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),".txt")));
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		Iterator<Cell> cells = null;
		while (rows.hasNext()) {
			Row row = rows.next();
			cells = row.cellIterator();
			writer.newLine();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				writer.write(cell.toString()+"	");
			} 
		}
		writer.newLine();
		writer.close();
		workbook.close();
		}catch(Exception e){
			
		}
	}
	public void XLSXToPDF(String Path,String Target){
		XlsxToHtml(Path,new getSystemInfos().getTmpDir());
		File file = new File(Path);
		HTMLConverter HTML = new HTMLConverter();
		HTML.HtmlToPdf(new getSystemInfos().getTmpDir()+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),".html"), Target);
	}
	public void XlsxConverter(String Path,String Target,String SelectedExt){
		if(SelectedExt.equalsIgnoreCase(".html")){XlsxToHtml(Path, Target);}
		else if(SelectedExt.equalsIgnoreCase(".txt")){getTextFromXlsx(Path,Target);}
		else if(SelectedExt.equalsIgnoreCase(".pdf")){
			XLSXToPDF(Path,Target);
		}
	}
	
	public void setSupportedFormats(Vector<String> comboBoxItems, String extension) {
		if(extension.equalsIgnoreCase(".xlsx")){
			comboBoxItems.removeAllElements();
			for(int i =0;i<SupportedFormat.length;i++){
				comboBoxItems.addElement(SupportedFormat[i]);
			}
}
		
	}
	
}
