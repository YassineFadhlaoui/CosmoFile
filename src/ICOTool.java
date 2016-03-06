import java.io.File;
import java.util.Vector;


public class ICOTool {
	String [] SupportedFormat={".png",".jpg",".bmp",".gif",".pdf",".html",".tiff",".tif"};
	ImageTool IT = new ImageTool(); 
	@SuppressWarnings("unused")
	String IcoToPng(String Path,String Target,String ext){
		File file = new File(Path);
        String name =file.getName().replace(new GetFileExtension().getExtension(file.getName()),"");
		try { 
			
	        java.io.File inFile = new java.io.File(Path);  
	    	java.util.List<java.awt.image.BufferedImage> images = net.sf.image4j.codec.ico.ICODecoder.read(inFile);           
	        String format ="png";     
	        for (int j = 0; j < images.size(); j++) {  
	          java.awt.image.BufferedImage img = images.get(j);  
	          
	          java.io.File pngFile = new java.io.File(new getSystemInfos().getTmpDir()+"/"+name+ext);
	          javax.imageio.ImageIO.write(img, format,pngFile);           
	        } 
		}catch(Exception e){
			ErrorClass er = new ErrorClass(null, "Error",true,"An unknown error occurred while","converting the File");
		}
		return new getSystemInfos().getTmpDir()+"/"+name+ext;
	}
	
	public void IcoConverter(String Path,String Target,String ext){
		if(ext.equalsIgnoreCase(".png")){
			IcoToPngDirectly(Path,Target);
		}else{
			String newPath=IcoToPng(Path,Target,ext);
			PNGTool png = new PNGTool();
			png.PngConverter(newPath, Target, ext);
		}
	}
	
	private void IcoToPngDirectly(String Path, String Target) {
		
		File file = new File(Path);
        String name =file.getName().replace(new GetFileExtension().getExtension(file.getName()),"");
		try { 
			
	        java.io.File inFile = new java.io.File(Path);  
	    	java.util.List<java.awt.image.BufferedImage> images = net.sf.image4j.codec.ico.ICODecoder.read(inFile);           
	        String format ="png";     
	        for (int j = 0; j < images.size(); j++) {  
	          java.awt.image.BufferedImage img = images.get(j);  
	          
	          java.io.File pngFile = new java.io.File(Target+"/"+name+".png");
	          javax.imageio.ImageIO.write(img, format,pngFile);           
	        } 
		}catch(Exception e){
			@SuppressWarnings("unused")
			ErrorClass er = new ErrorClass(null, "Error",true,"An unknown error occurred while ","converting the File");
		}
		
	}

	public  void InisilizeBox(Vector<String> format,String Extension){
		format.removeAllElements();
		for(int i =0;i<SupportedFormat.length;i++){
			format.addElement(SupportedFormat[i]);}
 }
}
