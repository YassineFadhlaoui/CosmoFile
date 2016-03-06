import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
public class HTMLConverter {
String [] SupportedFormat={".pdf"};
JOptionPane jop = new JOptionPane();

private void createPDF (String Path,String Target) throws IOException{
	 File file = new File(Path);
	
	BufferedReader br = new BufferedReader(new FileReader(Path));
	 String line;
	 int max=0;
	 
	 while((line=br.readLine())!=null){
		 if(line.length()>max){
			 max=line.length();
		 }
	 }
	   br.close();
	 String Name =Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),".pdf");
	  PdfWriter pdfWriter = null;
	  Document document = new Document();
	  if(max>540 && max<14400){
	  com.itextpdf.text.Rectangle rec = new com.itextpdf.text.Rectangle(max*4, 800);
	    document.setPageSize(rec);
	  }else if(max>=14400){
		  com.itextpdf.text.Rectangle rec = new com.itextpdf.text.Rectangle(14400, 800);
		    document.setPageSize(rec); 
	  }
	  try {
		  pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(Name));
	   document.open();
	   String File_To_Convert =Path;
	   FileInputStream fis = new FileInputStream(File_To_Convert);
	   XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
	   worker.parseXHtml(pdfWriter, document, fis);
	   document.close();
	   pdfWriter.close();
	  }   
	 
	  catch (FileNotFoundException e) {
	   e.printStackTrace();
	  } catch (IOException e1) {
	   e1.printStackTrace();
	  } catch (DocumentException e2) {
		  
	   e2.printStackTrace();
	  }       
	 
}

public void HtmlToPdf(String Path,String Target){
	try{
		File file = new File(Path);
		  String pdfFilename;
		 file.getName().replace(".html",".pdf");
		  pdfFilename = Path.trim();
		  createPDF(pdfFilename,Target);
	}catch(Exception e){
		JOptionPane.showMessageDialog(null,"An unknown error occurred while converting the file","Warning",JOptionPane.WARNING_MESSAGE,null);	
	e.printStackTrace();
	}
}
public void HTMLTool(String Path,String Target,String Extension){
	if(Extension.equalsIgnoreCase(".pdf")){HtmlToPdf(Path, Target);}
}
public void TxtcombBox(Vector<String> format,String Extension){
	if(Extension.equalsIgnoreCase(".html")){
		format.removeAllElements();
		for(int i =0;i<SupportedFormat.length;i++){
			format.addElement(SupportedFormat[i]);
		}
		}
		
}
}
