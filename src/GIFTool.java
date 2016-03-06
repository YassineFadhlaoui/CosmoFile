import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;


public class GIFTool {


	String [] SupportedFormat={".png",".jpeg",".bmp",".jpg",".tiff",".tif",".raw",".pnm",".pdf",".html",".ico"};
	ImageTool IT = new ImageTool(); 
	
	public void GifConverter(String Path,String Target,String Extension){
		
		if(Extension.equalsIgnoreCase(".png")||Extension.equalsIgnoreCase(".bmp")||Extension.equalsIgnoreCase(".jpeg")||Extension.equalsIgnoreCase(".jpg")||Extension.equalsIgnoreCase(".pnm")||Extension.equalsIgnoreCase(".tif")||Extension.equalsIgnoreCase(".tiff")||Extension.equalsIgnoreCase(".raw")){
			BufferedImage bufferedImage;
			File file = new File(Path);
		try {
			bufferedImage = ImageIO.read(new File(Path));
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
					bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
					newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
					ImageIO.write(newBufferedImage, Extension.replace(".",""), new File(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(Path),Extension)));
			} catch (IOException e) {
				e.printStackTrace();	 
			}
		}else if(Extension.equalsIgnoreCase(".html")){
			try{
			IT.ConvertImgToHtml(Path, Target);
			}catch(Exception e){}
		}else if(Extension.equalsIgnoreCase(".ico")){
			try {
				IT.ConvertToIco(Path, Target);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(Extension.equalsIgnoreCase(".svg")){
		
		}else if(Extension.equalsIgnoreCase(".pdf")){
			IT.ConvertImgToPdfFile(Path, Target);
		}
	}
	public  void InisilizeBox(Vector<String> format,String Extension){
		format.removeAllElements();
		for(int i =0;i<SupportedFormat.length;i++){
			format.addElement(SupportedFormat[i]);}
 }
}
