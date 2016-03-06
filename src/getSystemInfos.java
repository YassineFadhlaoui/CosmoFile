import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class getSystemInfos {
	private  String defaultDirectory()
	{
	    String OS = System.getProperty("os.name").toUpperCase();
	    if (OS.contains("WIN"))
	        return System.getenv("APPDATA");
	    else if (OS.contains("MAC"))
	        return System.getProperty("user.home") +"/Library/Application "
	                + "Support";
	    else if (OS.contains("NUX"))
	        return System.getProperty("user.home");
	    return System.getProperty("user.dir");
	}
	public  void MakeTmpDir(){
		 String OS = System.getProperty("os.name").toUpperCase();
			String s=defaultDirectory();
			File fi = new File(s);
			if(OS.contains("WIN")){
			File v = new File(fi.getParent()+"/Local");
			
		
			if(!new File(v+"/CosmoFile/tmp").exists()){
			@SuppressWarnings("unused")
			boolean maketmpdir=new File(v+"/CosmoFile/tmp").mkdirs();}}
			else if(OS.contains("MAC")){
				String mac=System.getProperty("user.home") +"/Library/Application "
		                + "Support";
				if(!new File(mac+"/CosmoFile/tmp").exists()){
				@SuppressWarnings("unused")
				boolean maketmpdir=new File(mac+"/CosmoFile/tmp").mkdirs();}
			}else if(OS.contains("NUX")){
				
				String linux=System.getProperty("user.home");
				if(! new File(linux+"/CosmoFile/tmp").exists()){
				@SuppressWarnings("unused")
				boolean maketmpdir=new File(linux+"/CosmoFile/tmp").mkdirs();}
			}	
	}
	public String DefaultTarget(){
		String Default = null;
		String OS = System.getProperty("os.name").toUpperCase();
		if(OS.contains("WIN")){
			Default=System.getenv("USERPROFILE")+"\\";
		}else if(OS.contains("MAC")){
			Default=System.getProperty("user.home") +"\\";
		}else if(OS.contains("NUX")){
			Default=System.getProperty("user.home") +"\\";
		}
		return Default;
		
	}
	public  void FirstRun(){
	
		File fr = new File(getTmpDir()).getParentFile();
		
		File f = new File(fr+"/Access.dll");
		if(!f.exists()){
			 try {
                 Desktop.getDesktop().browse(new URI("http://wsdevelopment.blogspot.com/"));
                 Desktop.getDesktop().browse(new URI("https://www.facebook.com/pages/Software_Development/1145545418817585"));
                 f.createNewFile();
         } catch (URISyntaxException | IOException ex) {  
                 
         }
		}
	}
	public String getTmpDir(){
		
		String OS = System.getProperty("os.name").toUpperCase();
		String s=defaultDirectory();
		String tmpd=null;
		File fi = new File(s);
		if(OS.contains("WIN")){
		File v = new File(fi.getParent()+"\\Local");
		tmpd=v+"\\CosmoFile\\tmp";}
		else if(OS.contains("MAC")){
			String mac=System.getProperty("user.home") +"\\Library\\Application "
	                + "Support";
			
		 tmpd=mac+"\\CosmoFile\\tmp";
		}else if(OS.contains("NUX")){
			String linux=System.getProperty("user.home");
			tmpd=linux+"\\CosmoFile\\tmp";
		}	
		return tmpd;
		
	}
	
	
	}


