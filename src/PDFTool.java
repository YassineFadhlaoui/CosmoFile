import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;


public class PDFTool {
	String [] SupportedFormat={".png",".gif",".tiff",".tif",".html"};
	
	void ConvertPPTToHTML(String Path,String Target){
		try{
			ConvertPDFToPic(Path,Target,".png");
			File file = new File(Path);
			String tar =Target+"/"+file.getName()+"Images";
		 	 
		     

			
			File tmpdir = new File(tar);
		String[] PicsList=tmpdir.list();
		
		
		BufferedWriter bwcss = new BufferedWriter(new FileWriter(tar+"/style.css"));
		BufferedWriter bw = new BufferedWriter(new FileWriter(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")+".html"));
		bw.write("<!DOCTYPE html> \n <html> \n <head> \n <title> \n </title>");
		bw.write("<link rel=\"stylesheet\" type=\"text/css\" href=\""+tar+"/style.css\" />");
		bw.write(" \n </head> \n <body> \n");
		
		BufferedImage BI = null;
		int topi =10;
		
		for(int i=0;i<PicsList.length;i++){
			if(PicsList[i].toString().contains(".png")){
			if(i>0){
				
			BI=ImageIO.read(new File(tar+"/page_"+(i)+".png"));
			
			 topi+=BI.getHeight()+50;//
			}
			else if(i==0){
				topi=5;
				
			}
			
			bw.write("\n <p> <img src=" +"\""+tar+"/"+"page_"+String.valueOf(i)+".png"+"\""+" alt=\"\""+"class=\"size"+i+"\""+" />");	
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
	
	
	
	
	
	public void ConvertPDFToPic(String Path,String Target,String ext) throws IOException{
		 File file = new File(Path);
	        RandomAccessFile raf = new RandomAccessFile(file, "r");
	        FileChannel channel = raf.getChannel();
	        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
	        PDFFile pdffile = new PDFFile(buf);
	        int numPgs = pdffile.getNumPages();
	        boolean makedir= new File(Target+"/"+file.getName()+"Images").mkdirs();
	        if(makedir==true){
	        	String tar =Target+"/"+file.getName()+"Images";
	        for (int i = 0; i < numPgs; i++) {
	            // draw the first page to an image
	            PDFPage page = pdffile.getPage(i);
	            // get the width and height for the doc at the default zoom
	            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());
	            // generate the image
	            Image img = page.getImage(rect.width, rect.height, // width & height
	                    rect, // clip rect
	                    null, // null for the ImageObserver
	                    true, // fill background with white
	                    true // block until drawing is done
	                    );
	            // save it as a file
	            BufferedImage bImg = toBufferedImage(img);
	            File yourImageFile = new File(tar+"/page_" + i + ext);
	            ImageIO.write(bImg, ext.replace(".",""), yourImageFile);
	        }
	        }else{
	        	@SuppressWarnings("unused")
				ErrorClass er = new ErrorClass(null, "Error",true,"fatal error while ","creationg folder");
	        }
	        raf.close();
	    }

	    // This method returns a buffered image with the contents of an image
	    public static BufferedImage toBufferedImage(Image image) {
	        if (image instanceof BufferedImage) {
	            return (BufferedImage) image;
	        }
	        // This code ensures that all the pixels in the image are loaded
	        image = new ImageIcon(image).getImage();
	        // Determine if the image has transparent pixels; for this method's
	        // implementation, see e661 Determining If an Image Has Transparent
	        // Pixels
	        boolean hasAlpha = hasAlpha(image);
	        // Create a buffered image with a format that's compatible with the
	        // screen
	        BufferedImage bimage = null;
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        try {
	            // Determine the type of transparency of the new buffered image
	            int transparency = Transparency.OPAQUE;
	            if (hasAlpha) {
	                transparency = Transparency.BITMASK;
	            }
	            // Create the buffered image
	            GraphicsDevice gs = ge.getDefaultScreenDevice();
	            GraphicsConfiguration gc = gs.getDefaultConfiguration();
	            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
	        } catch (HeadlessException e) {
	            // The system does not have a screen
	        }
	        if (bimage == null) {
	            // Create a buffered image using the default color model
	            int type = BufferedImage.TYPE_INT_RGB;
	            if (hasAlpha) {
	                type = BufferedImage.TYPE_INT_ARGB;
	            }
	            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
	        }
	        // Copy image to buffered image
	        Graphics g = bimage.createGraphics();
	        // Paint the image onto the buffered image
	        g.drawImage(image, 0, 0, null);
	        g.dispose();
	        return bimage;
	    }

	    public static boolean hasAlpha(Image image) {
	        // If buffered image, the color model is readily available
	        if (image instanceof BufferedImage) {
	            BufferedImage bimage = (BufferedImage) image;
	            return bimage.getColorModel().hasAlpha();
	        }
	        // Use a pixel grabber to retrieve the image's color model;
	        // grabbing a single pixel is usually sufficient
	        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
	        try {
	            pg.grabPixels();
	        } catch (InterruptedException e) {
	        }
	        // Get the image's color model
	        ColorModel cm = pg.getColorModel();
	        return cm.hasAlpha();
	    }
	    public void ConvertThisPdf(String Path,String Target,String ext){
	    	if(ext.equalsIgnoreCase(".png")||ext.equalsIgnoreCase(".jpg")||ext.equalsIgnoreCase(".jpeg")||ext.equalsIgnoreCase(".bmp")
	    		||ext.equalsIgnoreCase(".gif")||ext.equalsIgnoreCase(".tiff")||ext.equalsIgnoreCase(".tif")){
	    		try{
	    			ConvertPDFToPic(Path, Target, ext);
	    		}catch(IOException e){
	    			@SuppressWarnings("unused")
					ErrorClass er = new ErrorClass(null, "Error",true,"An error occurred while ","converting the file");
	    		}
	    		
	    		}
	    	else if(ext.equalsIgnoreCase(".html")){
    			ConvertPPTToHTML(Path, Target);
    		}
	    }
	    public  void InisilizeBox(Vector<String> format,String Extension){
	    	format.removeAllElements();
			for(int i =0;i<SupportedFormat.length;i++){
				format.addElement(SupportedFormat[i]);}
	 }
	    
	
	
}
