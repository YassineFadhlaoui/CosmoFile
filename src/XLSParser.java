import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

final class XLSParser  {
 
	private static final String NEW_LINE = "\n";
	private static final String HTML_FILE_EXTENSION = ".html";
	private static final String TEMP_FILE_EXTENSION = ".tmp";
	private static final String HTML_SNNIPET_1 = "<!DOCTYPE html><html><head><title>";
	private static final String HTML_SNNIPET_2 = "</title></head><body><table>";
	private static final String HTML_SNNIPET_3 = "</table></body></html>";
	private static final String HTML_TR_S = "<tr>";
	private static final String HTML_TR_E = "</tr>";
	private static final String HTML_TD_S = "<td>";
	private static final String HTML_TD_E = "</td>";
 
	 File file;
	 String Target;
	XLSParser(File file,String Target) {
		this.file = file;
		this.Target=Target;
	}
 
	public void run() {
		try {
			parse(file,Target);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	private void parse(File file,String Target) throws FileNotFoundException, IOException {
		BufferedWriter writer;
		Workbook workbook;
		String fileName = file.getName();
		String folderName = Target;
			workbook = new HSSFWorkbook(new FileInputStream(file));
		File tempFile = File.createTempFile(fileName + '-', HTML_FILE_EXTENSION+ TEMP_FILE_EXTENSION, new File(folderName));
		writer = new BufferedWriter(new FileWriter(tempFile));
		writer.write(HTML_SNNIPET_1);
		writer.write(fileName);
		writer.write(HTML_SNNIPET_2);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		Iterator<Cell> cells = null;
		while (rows.hasNext()) {
			Row row = rows.next();
			cells = row.cellIterator();
			writer.write(NEW_LINE);
			writer.write(HTML_TR_S);
			while (cells.hasNext()) {
				Cell cell = cells.next();
				writer.write(HTML_TD_S);
				writer.write(cell.toString());
				writer.write(HTML_TD_E);
			}
			writer.write(HTML_TR_E);
		}
		writer.write(NEW_LINE);
		writer.write(HTML_SNNIPET_3);
		writer.close();
		File newFile = new File(folderName + '\\' + fileName.replace(new GetFileExtension().getExtension(file.getAbsolutePath()),"") + HTML_FILE_EXTENSION);
		tempFile.renameTo(newFile);
		workbook.close();
	}
 
}

