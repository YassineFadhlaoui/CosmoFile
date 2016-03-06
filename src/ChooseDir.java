import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class ChooseDir {
	JFileChooser fc = new JFileChooser();
	 JFrame frame=new JFrame();
	 String S,st,t;
	 public ChooseDir() {
			
			
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}

	

public void BrowseDir() throws FileNotFoundException{
	ImageIcon imgicon = new ImageIcon("file/Images.png");
    frame.setIconImage(imgicon.getImage());
	fc.showOpenDialog(frame);
	
	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
	st = String.valueOf(((fc.getSelectedFile()).getAbsolutePath()));
	t = st+"\\";
}

public String getSelectedDirectory() {
	
	return t ;
}




}
