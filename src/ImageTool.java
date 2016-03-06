import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class ImageTool {

	public  BufferedImage resizeImage(String Path,int x,int y) throws IOException{
		 BufferedImage originalImage = ImageIO.read(new File(Path));
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_RGB : originalImage.getType();
			BufferedImage resizedImage = new BufferedImage(x,y,type);
	 	Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, x,y, null);
	 	g.dispose();
	 	
		return resizedImage;
	 }
	    public void ConvertToIco(String Path,String Target) throws IOException {
	    	try {
	    		File Image = new File(Path) ;
	    		BufferedImage BI;
	    		BufferedImage BI256=resizeImage(Path,256,256);
	    		BufferedImage BI128=resizeImage(Path,128,128);
	    		BufferedImage BI64=resizeImage(Path,64,64);
	    		BufferedImage BI32=resizeImage(Path,32,32);
	    		
	    		BI=ImageIO.read(Image);
	    		BufferedImage resizeImage = BI;
	    		 int height=BI.getHeight();
		         int wedith=BI.getWidth();
		            if(height>256 && wedith > 256){resizeImage = resizeImage(Path,256,256);}
		            else if(height > 256 && wedith <256){resizeImage = resizeImage(Path,wedith,256);
		            }else if(wedith>256 && height<256){resizeImage = resizeImage(Path,256,height);}
		          java.util.List<java.awt.image.BufferedImage> images2 = new java.util.ArrayList<java.awt.image.BufferedImage>(1);  
		          images2.add(resizeImage);    
		          java.io.File outFile = new java.io.File(Target+"/"+Image.getName().replace(new GetFileExtension().getExtension(Image.getName()),"Original.ico"));    
		          net.sf.image4j.codec.ico.ICOEncoder.write(images2, outFile);
		         
		          if(height>256 && wedith>256){
		          java.util.List<java.awt.image.BufferedImage> images256 = new java.util.ArrayList<java.awt.image.BufferedImage>(1);  
		          images256.add(BI256);    
		          java.io.File outFile256 = new java.io.File(Target+"/"+Image.getName().replace(new GetFileExtension().getExtension(Image.getName()),"256x256.ico"));    
		          net.sf.image4j.codec.ico.ICOEncoder.write(images256, outFile256); }
		          
		          if(height>128 && wedith>128){
		          java.util.List<java.awt.image.BufferedImage> images128 = new java.util.ArrayList<java.awt.image.BufferedImage>(1);  
		          images128.add(BI128);    
		          java.io.File outFile128 = new java.io.File(Target+"/"+Image.getName().replace(new GetFileExtension().getExtension(Image.getName()),"128x128.ico"));    
		          net.sf.image4j.codec.ico.ICOEncoder.write(images128, outFile128); }
		          
		          if(height>64 && wedith>64){
		          java.util.List<java.awt.image.BufferedImage> images64 = new java.util.ArrayList<java.awt.image.BufferedImage>(1);  
		          images64.add(BI64);    
		          java.io.File outFile64 = new java.io.File(Target+"/"+Image.getName().replace(new GetFileExtension().getExtension(Image.getName()),"64x64.ico"));    
		          net.sf.image4j.codec.ico.ICOEncoder.write(images64, outFile64); } 
		          
		          if(height>32 && wedith>32){
		          java.util.List<java.awt.image.BufferedImage> images32 = new java.util.ArrayList<java.awt.image.BufferedImage>(1);  
		          images32.add(BI32);    
		          java.io.File outFile32 = new java.io.File(Target+"/"+Image.getName().replace(new GetFileExtension().getExtension(Image.getName()),"32x32.ico"));    
		          net.sf.image4j.codec.ico.ICOEncoder.write(images32, outFile32);
		          }
		          System.exit(0);
		        } catch (IOException ex) {  
		          ex.printStackTrace();  
		        } 	
	    }
	
	
	
public void ConvertImgToHtml(String Path,String Target){
	try{
	File file = new File(Path);
	boolean htmldir = new File(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")).mkdirs();
	if(htmldir==true){
		new Copy().copyFile(file,new File(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")+"/"+file.getName()));
	String NewName=String.valueOf(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),".html"));
	BufferedWriter bwcss = new BufferedWriter(new FileWriter(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")+"/style.css"));
	bwcss.write(".size {\n position: absolute; \n left: "+200+"px;\n top: "+10+"px;\n}");
	bwcss.close();
	BufferedWriter bw = new BufferedWriter(new FileWriter(NewName));
	bw.write("<!DOCTYPE html> \n <html> \n <head> \n <title> \n </title>");
	bw.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
	bw.write(" \n </head> \n <body> \n");
	bw.write("\n <img src=" +"\""+Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")+"/"+file.getName()+"\""+" alt=\""+file.getName()+"\""+"class=\"size\""+" />");
	bw.write("\n </body> \n </html>");
	bw.close();}
	}catch(Exception e){
		e.printStackTrace();
	}
	
}
void IcoToPng(String Path,String Target){
	try {        
        java.io.File inFile = new java.io.File(Path);  
    	String NewName=String.valueOf(Target+"/"+inFile.getName().replace(new GetFileExtension().getExtension(inFile.getName()),""));
    	       
        // load and decode ICO file  
          
        java.util.List<java.awt.image.BufferedImage> images = net.sf.image4j.codec.ico.ICODecoder.read(inFile);           
        String format = "png";     
        for (int j = 0; j < images.size(); j++) {  
          java.awt.image.BufferedImage img = images.get(j);  
          String name = NewName + "-"+ j;  
          java.io.File pngFile = new java.io.File(name+".png");  
          javax.imageio.ImageIO.write(img, format, pngFile);           
        } 
	}catch(Exception e){
		e.printStackTrace();
	}
}
public void ConvertImgToPdfFile(String Path,String Target) {
	try {
		File file = new File(Path);
		String NewName=String.valueOf(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),""));
		Document document=new Document();
		 BufferedImage bufferedImage;
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(NewName+".pdf"));
		Image image1 = Image.getInstance(Path); 
		 bufferedImage = ImageIO.read(new File(Path));
			float absci=bufferedImage.getWidth();
			float ord=bufferedImage.getHeight();
	document.setPageSize(new Rectangle(absci,ord));
		document.open();
		image1.setAbsolutePosition(0f,0f);
 	    document.add(image1);
 	    document.close();
 	    writer.close();
	} catch (Exception e) {
		//ERROR CLASS HERE 
	}
	 	
}



 
}
