
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ImageColors extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9061337189260710573L;
	/**
	 * 
	 */
	
	public static void toGray(BufferedImage image,float r,float g ,float b) {
	    int width = image.getWidth();
	    int height = image.getHeight();
	    for(int i=0; i<height; i++){
	      for(int j=0; j<width; j++){
	        Color c = new Color(image.getRGB(j, i));
	        int red = (int)(c.getRed() * r);
	        int green = (int)(c.getGreen() *g);
	        int blue = (int)(c.getBlue() *b);
	        int sum = red + green + blue;
	        Color newColor = new Color(sum,sum,sum);
	        image.setRGB(j,i,newColor.getRGB());
	      }
	    }
	  }
	public static void ExtractRed(BufferedImage image,int val) {
	    int width = image.getWidth();
	    int height = image.getHeight();
	  
	    for(int i=0; i<height; i++){
	      for(int j=0; j<width; j++){
	        Color c = new Color(image.getRGB(j, i));
	        
	        int red = (int)(c.getRed());
	        int green = (int)(c.getGreen());
	        int blue = (int)(c.getBlue());
	        int sum = (int) (red*0.21 + green*0.72 + blue*0.07);
	       
	        Color newColor; 
	       if(red<blue || red <green){
	        	newColor = new Color(sum,sum,sum);
	        	image.setRGB(j,i,newColor.getRGB());
	        }
	       else if(red >val){
	        	
	        	newColor= new Color(red,green,blue);
	        	image.setRGB(j,i,newColor.getRGB());
	       }else if(red-blue<3 || red-green <2){
	    	   newColor = new Color(sum,sum,sum);
	        	image.setRGB(j,i,newColor.getRGB());  
	       }
	        else{
	        	newColor = new Color(sum,sum,sum);
	        	image.setRGB(j,i,newColor.getRGB());	
	        }
	        
	        
	      }
	    }
	  }
	
	
	public static void ExtractBlue(BufferedImage image,int val) {
	    int width = image.getWidth();
	    int height = image.getHeight();
	   
	    for(int i=0; i<height; i++){
	      for(int j=0; j<width; j++){
	        Color c = new Color(image.getRGB(j, i));
	        
	        int red = (int)(c.getRed());
	        int green = (int)(c.getGreen());
	        int blue = (int)(c.getBlue());
	        int sum = (int) (red*0.21 + green*0.72 + blue*0.07);
	       
	        Color newColor; 
	       if(blue<red || blue <green){
	        	newColor = new Color(sum,sum,sum);
	        	image.setRGB(j,i,newColor.getRGB());
	        }
	       else if(blue >val){
	        	
	        	newColor= new Color(red,green,blue);
	        	image.setRGB(j,i,newColor.getRGB());
	       }else if(blue-red<20 || blue-green <2){
	    	   newColor = new Color(sum,sum,sum);
	        	image.setRGB(j,i,newColor.getRGB());  
	       }
	        else{
	        	newColor = new Color(sum,sum,sum);
	        	image.setRGB(j,i,newColor.getRGB());	
	        }
	        
	        
	      }
	    }
	  }
	
	
	public static void ExtractGreen(BufferedImage image,int val) {
	    int width = image.getWidth();
	    int height = image.getHeight();
	   
	    for(int i=0; i<height; i++){
	      for(int j=0; j<width; j++){
	        Color c = new Color(image.getRGB(j, i));
	        
	        int red = (int)(c.getRed());
	        int green = (int)(c.getGreen());
	        int blue = (int)(c.getBlue());
	        int sum = (int) (red*0.21 + green*0.72 + blue*0.07);
	       
	        Color newColor; 
	       if(green<red || green <blue){
	        	newColor = new Color(sum,sum,sum);
	        	image.setRGB(j,i,newColor.getRGB());
	        }
	       else if(green >val){
	        	
	        	newColor= new Color(red,green,blue);
	        	image.setRGB(j,i,newColor.getRGB());
	       }else if(green-red<10 || green-red <10){
	    	   newColor = new Color(sum,sum,sum);
	        	image.setRGB(j,i,newColor.getRGB());  
	       }
	        else{
	        	newColor = new Color(sum,sum,sum);
	        	image.setRGB(j,i,newColor.getRGB());	
	        }
	        
	        
	      }
	    }
	  }
	
	public ImageColors(JFrame parent,String title,boolean modal) {
		super(parent,title,modal);
	    setSize(243,302);
	    setLocation(600,250);
	    ImageIcon imgicon = new ImageIcon("file/gray.png");
	    setIconImage(imgicon.getImage());
	    initComponent();
	    setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setVisible(true);
	  }
	    public void initComponent(){
	    	JPanel Panel  = new JPanel();
	    	Panel.setBackground(Color.GRAY);
	    	Panel.setLayout(null);
	    	JButton Choose = new JButton();
	    	Choose.setBounds(10,20,25,25);
	    	Choose.setIcon(new ImageIcon("file/folder.conv"));
	    	Panel.add(Choose);
	    	JLabel path = new JLabel ();
	    	path.setText("Choose an Image");
	    	path.setForeground(Color.WHITE);
	    	path.setBounds(40,20,190,25);
	    	Panel.add(path);
	    	
	    	JButton dob = new JButton("Do");
	    	dob.setEnabled(false);;
	    	dob.setBounds(85,165,50,20);
	    	dob.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	Panel.add(dob);
	    	
	    	
	    	
	    	JLabel rl= new JLabel("Extract Red :");
	    	rl.setForeground(Color.white);
	    	rl.setBounds(10,70,100,25);
	    	Panel.add(rl);
	    	
	    	JLabel gl= new JLabel("Extract Green :");
	    	gl.setForeground(Color.white);
	    	gl.setBounds(10,100,100,25);
	    	Panel.add(gl);
	    	
	    	JLabel bl= new JLabel("Extract Blue :");
	    	bl.setForeground(Color.white);
	    	bl.setBounds(10,130,100,25);
	    	Panel.add(bl);
	    	
	    	JLabel redv= new JLabel("Red threshold value");
	    	redv.setForeground(Color.white);
	    	redv.setBounds(125,70,100,25);
	    	Panel.add(redv);
	    	 
	    	JLabel greenv= new JLabel("green threshold value");
	    	greenv.setForeground(Color.white);
	    	greenv.setBounds(125,100,110,25);
	    	Panel.add(greenv);
	    	
	    	
	    	JLabel bluev= new JLabel("blue threshold value");
	    	bluev.setForeground(Color.white);
	    	bluev.setBounds(125,130,100,25);
	    	Panel.add(bluev);
	    	
	    	JTextField x = new JTextField("60");
	    	JTextField y = new JTextField("0");
	    	JTextField z = new JTextField("0");
	    	x.setBounds(85,73,35,18);
	    	y.setBounds(85,103,35,18);
	    	z.setBounds(85,133,35,18);
	    	Panel.add(x);
	    	Panel.add(y);
	    	Panel.add(z);
	    	
	    	
	    	JLabel redp= new JLabel("red");
	    	redp.setForeground(Color.white);
	    	redp.setBounds(05,208,40,20);
	    	Panel.add(redp);
	    	
	    	JLabel greenp= new JLabel("% | green");
	    	 greenp.setForeground(Color.white);
	    	 greenp.setBounds(60,208,50,20);
	    	Panel.add(greenp);
	    	
	    	JLabel bluep= new JLabel("% | blue");
	    	bluep.setForeground(Color.white);
	    	bluep.setBounds(147,208,50,20);
	    	Panel.add(bluep);
	    	
	    	JLabel grayimg= new JLabel("Convert to grayscale :");
	    	grayimg.setForeground(Color.white);
	    	grayimg.setBounds(5,185,120,25);
	    	Panel.add(grayimg);
	    	
	    	JLabel per= new JLabel("%");
	    	per.setForeground(Color.white);
	    	per.setBounds(222,208,20,20);
	    	Panel.add(per);
	    	
	    	JTextField redp1 = new JTextField("21");
	    	JTextField greenp1 = new JTextField("72");
	    	JTextField bluep1 = new JTextField("7");
	    	redp1.setBounds(28,210,30,18);
	    	greenp1.setBounds(115,210,30,18);
	    	bluep1.setBounds(190,210,30,18);
	    	Panel.add(redp1);
	    	Panel.add(greenp1);
	    	Panel.add(bluep1);
	    	
	    	JButton Do= new JButton("Do");
	    	Do.setBounds(85,235,50,20);
	    	Do.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	Panel.add(Do);
	    	
	    	JLabel bg = new JLabel();
	    	bg.setBounds(-5,-10,243,300);
	    	bg.setIcon(new ImageIcon("file/blueWindow2.png"));
	    	Panel.add(bg);
	    	Do.setEnabled(false);
	    	Do.addActionListener(new ActionListener(){
	    		@SuppressWarnings("unused")
				@Override
				public void actionPerformed(ActionEvent arg0) {
	    			
	    			setCursor(new Cursor(Cursor.WAIT_CURSOR));
					dob.setEnabled(false);	
					Do.setEnabled(false);
	    			
	    			try{
	    			int red = (int) Double.parseDouble(redp1.getText());
	    			int green = (int) Double.parseDouble(greenp1.getText());
	    			int blue =(int) Double.parseDouble(bluep1.getText());
	    			File file = new File(path.getText());
	    			if(!file.exists() || path.getText().equalsIgnoreCase("null")){
						
						ErrorClass er = new ErrorClass(null, "Choose a File",true,"Please choose "," a file");
					}else{
						if((red+green+blue) != 100){
							ErrorClass er = new ErrorClass(null, "Error",true,"sum of cells must "," equal 100");
						}else{
							File filea=new File(path.getText());
							 String fileName= filea.getName();
							 String ext= new GetFileExtension().getExtension(fileName);
							 if(ext.equalsIgnoreCase(".png")
									 ||ext.equalsIgnoreCase(".jpg")||ext.equalsIgnoreCase(".bmp")||ext.equalsIgnoreCase(".jpeg")
									 ||ext.equalsIgnoreCase(".gif")||ext.equalsIgnoreCase(".tiff")||ext.equalsIgnoreCase(".tif")){
								 
								 File input = new File(file.getAbsolutePath());
								 BufferedImage image = ImageIO.read(input);
								    toGray(image, (float)red/100,(float) green/100,(float) blue/100);
								    File output = new File(input.getAbsolutePath().replace(new GetFileExtension().getExtension(input.getName()),"_gray_"+new GetFileExtension().getExtension(input.getName())));
								    ImageIO.write(image,ext.replace(".",""), output);
							 
							 }
						}
						
					}
	    			}catch(NumberFormatException e){
	    				ErrorClass er = new ErrorClass(null, "Number Format Exception",true,"some fields are invalid please"," verify your inputs");
	    			} catch (IOException e) {
	    				JOptionPane.showMessageDialog(null,"file not found \n please"
								+ "choose a valid file \n"
								+ "and try again","Warning",JOptionPane.WARNING_MESSAGE,null);
					}
	    			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					dob.setEnabled(true);	
					Do.setEnabled(true);

	    		}	
	    		
	    	});
	    	
	    	Choose.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Choose a file", "jpg", "gif","jpeg","bmp","tiff","png","tif");
						BrowseDirectory BD = new BrowseDirectory(filter);
						BD.BrowseFile();
						path.setText(BD.getReader());
						dob.setEnabled(true);
						Do.setEnabled(true);
					} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"An unknown error occurred while opening the folder","Warning",JOptionPane.WARNING_MESSAGE,null);
					}
	
				}
			});
	    	
	    	dob.addActionListener(new ActionListener() {
				
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					dob.setEnabled(false);
					Do.setEnabled(false);
					try{
				int redval=Integer.parseInt(x.getText())	;
				int greenval=Integer.parseInt(y.getText())	;
				int blueval=Integer.parseInt(z.getText())	;	
				File fileq = new File(path.getText());
    			if(!fileq.exists() || path.getText().equalsIgnoreCase("null")){
					JOptionPane.showMessageDialog(null,"Please choose a file","Warning",JOptionPane.WARNING_MESSAGE,null);
					
				}else{
					
					 File file=new File(path.getText());
					 String fileName= file.getName();
					 String ext= new GetFileExtension().getExtension(fileName);
					 if(ext.equalsIgnoreCase(".png")
							 ||ext.equalsIgnoreCase(".jpg")||ext.equalsIgnoreCase(".bmp")||ext.equalsIgnoreCase(".jpeg")
							 ||ext.equalsIgnoreCase(".gif")||ext.equalsIgnoreCase(".tiff")||ext.equalsIgnoreCase(".tif")){
						 
						 File input = new File(file.getAbsolutePath());
						 if(redval>0){
						    BufferedImage image = ImageIO.read(input);
						    ExtractRed(image,redval);
						    File output = new File(input.getAbsolutePath().replace(new GetFileExtension().getExtension(input.getName()),"_threshold value_"+x.getText()+new GetFileExtension().getExtension(input.getName())));
						    ImageIO.write(image,ext.replace(".",""), output);
						    }
						 if(greenval>0){
							 BufferedImage image = ImageIO.read(input);
							    ExtractGreen(image,greenval);
							    File output = new File(input.getAbsolutePath().replace(new GetFileExtension().getExtension(input.getName()),"_threshold value_"+y.getText()+new GetFileExtension().getExtension(input.getName())));
							    ImageIO.write(image,ext.replace(".",""), output);
							     
						 }
						 if(blueval>0){
							 BufferedImage image = ImageIO.read(input);
							    ExtractBlue(image,blueval);
							    File output = new File(input.getAbsolutePath().replace(new GetFileExtension().getExtension(input.getName()),"_threshold value_"+z.getText()+new GetFileExtension().getExtension(input.getName())));
							    ImageIO.write(image,ext.replace(".",""), output);
							     
						 }
						 
						 
					 
				}
				}
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null,"some fields are invalid please verify your inputs","Warning",JOptionPane.WARNING_MESSAGE,null);
					}catch( IOException d){
						JOptionPane.showMessageDialog(null,"file not found \n please"
								+ "choose a valid file \n"
								+ "and try again","Warning",JOptionPane.WARNING_MESSAGE,null);
					}
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					dob.setEnabled(true);	
					Do.setEnabled(true);
					}
				
			});
	    	
	    	
	    	
	    	this.setContentPane(Panel);
	    	
}
}
