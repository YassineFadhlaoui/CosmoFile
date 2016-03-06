import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;




public class TIFTool {


	String [] SupportedFormat={".png",".jpg",".bmp",".gif",".jpeg",".tiff",".raw",".pnm",".pdf",".html",".ico"};
	ImageTool IT = new ImageTool(); 
	
	public String TiffToOtherFormat(String Path) {
		String tmpPath = null;
		try {
			File file = new File(Path);
			BufferedImage bufferedImage = ImageIO.read(new File(Path));
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
					bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
					newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
					ImageIO.write(newBufferedImage,"png", new File(new getSystemInfos().getTmpDir()+"/"+file.getName().replace(new GetFileExtension().getExtension(Path),".png")));
					 tmpPath=new getSystemInfos().getTmpDir()+"/"+file.getName().replace(new GetFileExtension().getExtension(Path),".png");
					
		} catch (IOException e) {
				e.printStackTrace();	 
			}
	   
		return tmpPath;	
	}
	
	public void ConvertTiffToIco(String Path,String Target){
	
			try {
				String tmpico=TiffToOtherFormat(Path);
				ImageTool IT = new ImageTool();
				IT.ConvertToIco(tmpico, Target);
				
				File tmpicofile = new File(tmpico);
				
				tmpicofile.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	
	public void TifConverter(String Path,String Target,String Extension){
		
		if(Extension.equalsIgnoreCase(".png")||Extension.equalsIgnoreCase(".bmp")||Extension.equalsIgnoreCase(".jpg")||Extension.equalsIgnoreCase(".gif")||Extension.equalsIgnoreCase(".pnm")||Extension.equalsIgnoreCase(".tiff")||Extension.equalsIgnoreCase(".JPEG")||Extension.equalsIgnoreCase(".raw")){
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
				
				try {
					String tmpPath=null;
					File file = new File(Path);
					BufferedImage bufferedImage = ImageIO.read(new File(Path));
					BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
							bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
							newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
							boolean dirhtml = new File(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName())," HTML")).mkdirs();
							String htmlPATH= Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName())," HTML");
							if(dirhtml==true){
							ImageIO.write(newBufferedImage,"png", new File(htmlPATH+"/"+file.getName().replace(new GetFileExtension().getExtension(Path),".png")));
							 tmpPath=htmlPATH+"/"+file.getName().replace(new GetFileExtension().getExtension(Path),".png");
							 IT.ConvertImgToHtml(tmpPath, Target);
							}
				} catch (IOException e) {
							 
					}
			}catch(Exception e){}
		}else if(Extension.equalsIgnoreCase(".ico")){
			ConvertTiffToIco(Path,Target);
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

