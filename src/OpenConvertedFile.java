import java.io.File;

import javax.swing.JOptionPane;


public class OpenConvertedFile {
JOptionPane jop = new JOptionPane();
public void OpenFile(String File){
	
	
	
	try {
   	 
 		if ((new File(File)).exists()) {
  
 			Process p = Runtime
 			   .getRuntime()
 			   .exec("rundll32 url.dll,FileProtocolHandler "+File);
 			p.waitFor();
  
 		} else {
  
 			JOptionPane.showMessageDialog(null,"File not Exists", "File not found", JOptionPane.WARNING_MESSAGE, null);
  
 		}
  
 		
  
   	  } catch (Exception ex) {
   		JOptionPane.showMessageDialog(null,"An unknown error occurred","Warning",JOptionPane.WARNING_MESSAGE,null);
 	  }
}
}
