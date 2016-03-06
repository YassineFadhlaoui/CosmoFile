
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
//we make a new class 
public class BrowseDirectory {
//we declare a member class variable fc 
	final JFileChooser fc = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Choose a File From Your Computer","pdf","png", "jpg", "gif","jpeg","bmp","txt","tiff","png","html","xls","xlsx","ppt","pptx","tif","ico","svg");
	    
        JFrame frame = new JFrame();
        Scanner reader;
    	public BrowseDirectory() {
			
    		 ImageIcon imgicon = new ImageIcon("file/Images.png");
    		    frame.setIconImage(imgicon.getImage());
			fc.setFileFilter(filter);
		}
    	public BrowseDirectory(FileNameExtensionFilter f){
    		 ImageIcon imgicon = new ImageIcon("file/file.am");
    		    frame.setIconImage(imgicon.getImage());
    		fc.setFileFilter(f);
    	}
    	public void BrowseFile() throws FileNotFoundException{
		
    	fc.showOpenDialog(frame);
    	try{
    		  reader = new Scanner(fc.getSelectedFile());
    		 
    	}catch(NullPointerException e){
    		
    		
    	}	 
    	}
		public String getReader() {
			return String.valueOf((fc.getSelectedFile()));
		}
		public String getOriginalDir(){
		
			String m="";
			String j;
			m=String.valueOf((fc.getSelectedFile()).getParentFile());
    		j=m.replace('\\', '/');
    		return j;
		}
		public String getExtension(){
			String Test="";
			String tran="";
			String S=String.valueOf(fc.getSelectedFile());
			int i=(S.length())-1;
			while(i >= 0 && S.charAt(i) != '.'){
				Test +=S.charAt(i);
				i--;
			}
			int j=(Test.length())-1;
			while(j >= 0 ){
				tran +=Test.charAt(j);
				j--;
			}
			
			return "."+tran;
		}
		
    	
    }
