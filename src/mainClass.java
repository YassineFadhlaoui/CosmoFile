import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
public class mainClass {
	
	public static void main(String[] args) throws IOException, InvalidFormatException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException| IllegalAccessException | UnsupportedLookAndFeelException e1) {} 
		
	
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try{
					
						File file= new File("file");
					    Process p = Runtime.getRuntime().exec("attrib +h " + file.getPath());
					    p.waitFor(); // p.waitFor() important, so that the file really appears as hidden immediately after function exit.
					
				}catch(Exception lastOne){
					
				}
				
				try{
					String[] filelist= new File(new getSystemInfos().getTmpDir()).list();
					for(int i=0;i<filelist.length;i++){
						boolean delete = new File(new getSystemInfos().getTmpDir()+"/"+filelist[i]).delete();
					}	
					
				}catch(Exception e1){
					
				};
				try {
					getSystemInfos sys = new getSystemInfos();
					sys.MakeTmpDir();
					sys.FirstRun();
					
						Graphic frame = new Graphic();
				} catch (Exception e) {
					
				}
			}
		});
		
		
	
		

	}
}

