import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PPTTool {
	String [] SupportedFormat = {".pdf",".html",".JPEG"};
	
	public void ConvertPPTToPDF(String Path,String Target){
		try{
		File file = new File(Path);
		FileInputStream inputStream = new FileInputStream(Path);
	    double zoom = 3;
	    AffineTransform at = new AffineTransform();
	    at.setToScale(zoom, zoom);
	    Document pdfDocument = new Document();
	    PdfWriter pdfWriter = PdfWriter.getInstance(pdfDocument, new FileOutputStream(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(Path),".pdf")));
	    PdfPTable table = new PdfPTable(1);
	    pdfWriter.open();
	    pdfDocument.open();
	    Dimension pgsize = null;
	    Image slideImage = null;
	    BufferedImage img = null;
	    
	    
	        SlideShow ppt = new SlideShow(inputStream);
	        pgsize = ppt.getPageSize();
	        Slide[] slide = ppt.getSlides();
	        pdfDocument.setPageSize(new Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
	        pdfWriter.open();
	        pdfDocument.open();
	        for (int i = 0; i < slide.length; i++) {
	            img = new BufferedImage((int) Math.ceil(pgsize.width * zoom), (int) Math.ceil(pgsize.height * zoom), BufferedImage.TYPE_INT_RGB);
	            Graphics2D graphics = img.createGraphics();
	            graphics.setTransform(at);
	            			
	            graphics.setPaint(Color.white);
	            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
	            slide[i].draw(graphics);
	            
	            graphics.getPaint();
	            slideImage = Image.getInstance(img, null);
	            table.addCell(new PdfPCell(slideImage, true));
	        }
	    
	    pdfDocument.add(table);
	    pdfDocument.close();
	    pdfWriter.close();
	    
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	String folderName;
	public void ConvertPPTtoPic(String Path,String Target) {
		try{
			File file = new File(Path);
			new File(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")).mkdirs();
		 	 folderName= String.valueOf(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),""));
		     System.out.println(folderName);
		FileInputStream is = new FileInputStream(Path);
		SlideShow ppt = new SlideShow(is);
		 is.close();

		 Dimension pgsize = ppt.getPageSize();

		Slide[] slide = ppt.getSlides();
		 for (int i = 0; i < slide.length; i++) {
			
		 BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, 1);

		 Graphics2D graphics = img.createGraphics();
		 graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		 graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		 RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		 graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
		 RenderingHints.VALUE_FRACTIONALMETRICS_ON);

		 graphics.setColor(Color.white);
		 graphics.clearRect(0, 0, pgsize.width, pgsize.height);
		 graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

		 // render
		 slide[i].draw(graphics);

		 // save the output
		 FileOutputStream out = new FileOutputStream(folderName+"/"+"slide" +(i + 1) +".jpeg");
		 javax.imageio.ImageIO.write(img, "jpeg", out);
		 out.close();
		 } 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void ConvertPPTtoPicForHtml(String Path,String Target,String Extension) {
		try{
			File file = new File(Path);
			new File(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")+"html_res").mkdirs();
		 	 folderName= String.valueOf(Target+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")+"html_res");
		     System.out.println(folderName);
		FileInputStream is = new FileInputStream(Path);
		SlideShow ppt = new SlideShow(is);
		 is.close();

		 Dimension pgsize = ppt.getPageSize();

		Slide[] slide = ppt.getSlides();
		 for (int i = 0; i < slide.length; i++) {
			
		 BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, 1);

		 Graphics2D graphics = img.createGraphics();
		 graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		 graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		 RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		 graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
		 RenderingHints.VALUE_FRACTIONALMETRICS_ON);

		 graphics.setColor(Color.white);
		 graphics.clearRect(0, 0, pgsize.width, pgsize.height);
		 graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

		 // render
		 slide[i].draw(graphics);

		 // save the output
		 FileOutputStream out = new FileOutputStream(folderName+"/"+"slide" +(i + 1) +Extension);
		 javax.imageio.ImageIO.write(img, "jpeg", out);
		 out.close();
		 } 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void ConvertPPTToHTML(String Path,String Target){
		try{
			ConvertPPTtoPicForHtml(Path,Target,".jpeg");
			File file = new File(Path);
			
		 	 
		     

			String dir=file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")+"html_res";
			String tmp= String.valueOf(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")+"html_res");
			File tmpdir = new File(folderName);
		String[] PicsList=tmpdir.list();
		
		
		BufferedWriter bwcss = new BufferedWriter(new FileWriter(tmp+"/style.css"));
		BufferedWriter bw = new BufferedWriter(new FileWriter(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")+".html"));
		bw.write("<!DOCTYPE html> \n <html> \n <head> \n <title> \n </title>");
		bw.write("<link rel=\"stylesheet\" type=\"text/css\" href=\""+dir+"/style.css\" />");
		bw.write(" \n </head> \n <body> \n");
		
		BufferedImage BI = null;
		int topi =10;
		
		for(int i=0;i<PicsList.length;i++){
			if(PicsList[i].toString().contains("slide")){
			if(i>0){
				
			BI=ImageIO.read(new File(folderName+"/slide"+(i+1)+".jpeg"));
			
			 topi+=BI.getHeight()+50;//
			}
			else if(i==0){
				topi=5;
				
			}
			
			bw.write("\n <p> <img src=" +"\""+dir+"/"+"slide"+String.valueOf(i+1)+".jpeg"+"\""+" alt=\"\""+"class=\"size"+i+"\""+" />");	
			bw.newLine();
			bwcss.write(".size"+i+" {\n position: absolute; \n left:250px;\n top: "+topi+"px;\n}");
			bwcss.newLine();
		}
		
		}
		bwcss.close();
		bw.write("\n </body> \n </html>");
		bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	void ConvertThisPptxTo(String Path,String Target,String Extension){
		if(Extension.equalsIgnoreCase(".pdf")){ConvertPPTToPDF(Path,Target);}
		if(Extension.equalsIgnoreCase(".JPEG")){ConvertPPTtoPic(Path,Target);}
		if(Extension.equalsIgnoreCase(".html")){ConvertPPTToHTML(Path,Target);}
		
	}
	
	public void PPTcombBox(Vector<String> comboBoxItems, String extension) {
		if(extension.equalsIgnoreCase(".ppt")){
			comboBoxItems.removeAllElements();
			for(int i =0;i<SupportedFormat.length;i++)
				comboBoxItems.addElement(SupportedFormat[i]);
}
		
	}
}

