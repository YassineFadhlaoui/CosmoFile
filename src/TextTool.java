import java.util.Vector;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
public class TextTool extends Thread {
	// finshed 
	String [] SupportedFormat = {".pdf",".jpg",".html",".docx",".rtf"};
 
 public void ConvertTxtToPdf(String Path,String Target){
		Document document = new Document();
		BufferedReader br;
		try {
			File f = new File(Path);
			br = new BufferedReader(new FileReader(f));
			String str;
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(Target+"/"+f.getName().replace(new GetFileExtension().getExtension(f.getName()),".pdf")));
			document.open();
			while((str=br.readLine()) != null){
				Paragraph line =new Paragraph(str);
					document.add(line);
			}
			br.close();
			document.close();
			writer.close();
		} catch (Exception e) {
			//ERROR CLASS HERE;
		}
		
	}	
 
	public synchronized void ConvertThisTextTo(String Path,String extension,String Target){
		try{
			File f = new File(Path);
		if(extension.equalsIgnoreCase(".pdf")){
			ConvertTxtToPdf(Path,Target);}
		if(extension.equalsIgnoreCase(".jpg")){
			ConvertPdfToImg CPTI = new ConvertPdfToImg();
			ConvertTxtToPdf(Path,new getSystemInfos().getTmpDir());
			CPTI.ConvertPdfToPic(new getSystemInfos().getTmpDir()+"/"+f.getName().replace(new GetFileExtension().getExtension(f.getName()),".pdf"), Target);
		}
		if(extension.equalsIgnoreCase(".html")){
			BufferedReader br = new BufferedReader(new FileReader(Path));
			BufferedWriter bw = new BufferedWriter(new FileWriter(Target+"/"+f.getName().replace(new GetFileExtension().getExtension(f.getName()),".html")));
			String indexer;
			bw.write("<!DOCTYPE html> \n <html> \n <head> \n <title> </title> </head> \n <body>");
			while((indexer=br.readLine()) != null){
			
			bw.write("<p>"+indexer);
			}
			bw.newLine();
			bw.write("</body> \n </html>");
			bw.close();
			br.close();
		}
		if(extension.equalsIgnoreCase(".rtf")){
			BufferedReader br = new BufferedReader(new FileReader(Path));
			BufferedWriter bw = new BufferedWriter(new FileWriter(Target+"/"+f.getName().replace(new GetFileExtension().getExtension(f.getName()),".rtf")));
			String indexer;
			while((indexer=br.readLine()) != null){
			bw.newLine();
			bw.write(indexer);
			}
			bw.close();
			br.close();
		}
		if(extension.equalsIgnoreCase(".docx")){
			
			BufferedReader br = new BufferedReader(new FileReader(Path));
			String indexer;
			XWPFDocument document = new XWPFDocument();
			XWPFParagraph tmpParagraph = document.createParagraph();
			XWPFRun tmpRun = tmpParagraph.createRun();
			
			while((indexer=br.readLine()) != null){
				tmpRun.setText(indexer);
				tmpRun.setFontSize(14);
				
			}
	
			br.close();
			document.write(new FileOutputStream(new File(Target+"/"+f.getName().replace(new GetFileExtension().getExtension(f.getName()),".docx"))));
		}
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		}
		
		public void TxtcombBox(Vector<String> comboBoxItems, String extension) {
		if(extension.equalsIgnoreCase(".txt")){
			
			comboBoxItems.removeAllElements();
			for(int i =0;i<SupportedFormat.length;i++){
				comboBoxItems.addElement(SupportedFormat[i]);
			}
}
		
	}
		
}
