import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;




public class OpenDir {

	JOptionPane jop = new JOptionPane();
	BrowseDirectory bd =new BrowseDirectory();
	
	
public  void DirOpener(JLabel label,String dir){
	
	label.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        	
	try{
		String s= System.getProperty("line.separator");
		String tmp = dir;
		 String line = "\\n";
		    String sep="\\r\\n";
		    String sep3="\\r";

		    boolean verif=false;
		    if(dir.contains(line)){
		    	tmp=(dir.replace(line, "*"));
		    	
		        verif=true;
		        }
		        if(dir.contains(sep)){
		        	tmp=(dir.replace(line, "*"));
		        	
		        	verif=true;
		            }
		        if(dir.contains(sep3)){
		        	tmp=(dir.replace(line, "*"));
		        	verif=true;
		        	System.out.println("1");
		            }
		        if(dir.contains(s)){
		        	tmp=(dir.replace(s, "*"));
		        	verif=true;
		        	System.out.println("1");
		        }
		        if(verif==true){
		        	 int i=tmp.length()-1;
		        	  String str="";
		        	  while(i>=0 && tmp.charAt(i) != '*'){
		        		str +=tmp.charAt(i);
		        		i--;
		        	  }
		        	 
		        	  tmp=str;
		        	  str="";
		        	  while(i>=0 && tmp.charAt(i) != '*'){
		        			str +=tmp.charAt(i);
		        			i--;
		        		  }	
		        }
		       
		Desktop.getDesktop().open(new File(label.getText()));
		
	}catch(Exception arg0){
		JOptionPane.showMessageDialog(null,"An unknown error occurred","Warning",JOptionPane.WARNING_MESSAGE,null);
	}
	}
    });
}
	
}
