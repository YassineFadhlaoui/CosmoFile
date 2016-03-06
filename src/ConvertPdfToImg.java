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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
public class ConvertPdfToImg {
	 String folderName;
	 public  void ConvertPdfToPic(String Path,String Target) throws IOException{
	 	try{
	 		
	 		 
	 	 File file = new File(Path);
	 	 new File(Target+"/ "+file.getName().replace(new GetFileExtension().getExtension(file.getName()),"")).mkdirs();
	 	 folderName= String.valueOf(Target+"/ "+file.getName().replace(new GetFileExtension().getExtension(file.getName()),""));
	      @SuppressWarnings("resource")
		RandomAccessFile raf = new RandomAccessFile(file, "r");
	      FileChannel channel = raf.getChannel();
	     ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
	      PDFFile pdffile = new PDFFile(buf);
	      int numPgs = pdffile.getNumPages();
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
	         File yourImageFile = new File(folderName+"/page_" + i + ".jpg");
	         ImageIO.write(bImg, "png", yourImageFile);
	     }
	     }catch(Exception e){}
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
	 public void Final(String Target) throws IOException{
	 	File TempDir = new File(folderName);
	 	String[] PicsList=TempDir.list();
	 	BufferedWriter bwcss = new BufferedWriter(new FileWriter("style.css"));
	 	BufferedWriter bw = new BufferedWriter(new FileWriter(Target+".html"));
	 	bw.write("<!DOCTYPE html> \n <html> \n <head> \n <title> \n </title>");
	 	bw.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
	 	bw.write(" \n </head> \n <body> \n");
	 	int left=5;
	 	
	 	BufferedImage BI = null;
	 	int topi = 50;
	 	for(int i=0;i<PicsList.length;i++){
	 		
	 		if(i>0){
	 		BI=ImageIO.read(new File(folderName+"/"+PicsList[i-1]));
	 		
	 		 topi+=left+BI.getHeight()+100;}
	 		
	 		
	 		bw.write("\n <p> <img src=" +"\""+folderName+"/"+PicsList[i]+"\""+" alt=\"\""+"class=\"size"+i+"\""+" />");	
	 		bw.newLine();
	 		bwcss.write(".size"+i+" {\n position: absolute; \n left:350px;\n top: "+topi+"px;\n}");
	 		bwcss.newLine();
	 	}
	 	
	 	
	 	bwcss.close();
	 	bw.write("\n </body> \n </html>");
	 	bw.close();
	 }


	 public void ConvertPdfToHtml() throws IOException {
	    
	                 ConvertPdfToPic("test.pdf","");
	                 Final("ppppppppppppppcccsder");
	            
	 }
}
