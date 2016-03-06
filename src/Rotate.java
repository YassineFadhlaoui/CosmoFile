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

import net.coobird.thumbnailator.Thumbnails;


public class Rotate extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9061337189260710573L;
	/**
	 * 
	 */

	public Rotate(JFrame parent,String title,boolean modal) {
		  super(parent,title,modal);
	    setSize(245,209);
	    setLocation(600,250);
	    ImageIcon imgicon = new ImageIcon("file/Rotate.png");
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
	    	
	    	
	    	
	    	path.setText("Choose an Image To rotate");
	    	path.setForeground(Color.WHITE);
	    	path.setBounds(40,20,190,25);
	    	
	    	
	    	Panel.add(path);
	    	JButton Rotate = new JButton("Rotate");
	    	Rotate.setEnabled(false);;
	    	Rotate.setBounds(75,120,80,30);
	    	Rotate.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	Panel.add(Rotate);
	    	
	    	JLabel ResizeLabel = new JLabel("Angle of rotation :");
	    	ResizeLabel.setForeground(Color.white);
	    	ResizeLabel.setBounds(20,72,120,25);
	    	Panel.add(ResizeLabel);
	    	
	    	
	    	
	    	JLabel deg= new JLabel("° degree");
	    	deg.setForeground(Color.white);
	    	deg.setBounds(180,70,60,25);
	    	Panel.add(deg);
	    	
	    	JTextField x = new JTextField();
	    	
	    	x.setBounds(125,70,45,25);
	    	
	    	
	    	Panel.add(x);
	    	
	    	
	    	JLabel bg = new JLabel();
	    	bg.setBounds(-5,-3,243,190);
	    	bg.setIcon(new ImageIcon("file/blueWindow.png"));
	    	Panel.add(bg);
	    	
	    	Choose.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Choose a file", "jpg", "gif","jpeg","bmp","tiff","png","tif");
						BrowseDirectory BD = new BrowseDirectory(filter);
						BD.BrowseFile();
						path.setText(BD.getReader());
						Rotate.setEnabled(true);
					} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"an error occurred","Warning",JOptionPane.WARNING_MESSAGE,null);
					}
	
				}
			});
	    	
	    	Rotate.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Rotate.setEnabled(false);
					try{
				int alpha=Integer.parseInt(x.getText())	;
					
				if(path.getText().isEmpty() || path.getText().equalsIgnoreCase("null")){
					JOptionPane.showMessageDialog(null,"Please choose a file","Warning",JOptionPane.WARNING_MESSAGE,null);
					
				}else{
					String Path = path.getText();
					 File file=new File(path.getText());
					 String fileName= file.getName();
					 String ext= new GetFileExtension().getExtension(fileName);
					 if(ext.equalsIgnoreCase(".png")
							 ||ext.equalsIgnoreCase(".jpg")||ext.equalsIgnoreCase(".bmp")||ext.equalsIgnoreCase(".jpeg")
							 ||ext.equalsIgnoreCase(".gif")
							 ){
					
						 BufferedImage bi = ImageIO.read(new File(Path));
							for (int i : new int[] {alpha}) {
							    Thumbnails.of(new File(Path))
							            .size( bi.getWidth(),bi.getHeight())
							            .rotate(i)
							            .toFile(new File(file.getAbsolutePath().replace(new GetFileExtension().getExtension(file.getName()),"Rotated"+new GetFileExtension().getExtension(file.getName()))));
							}
						 
						 
			       // .toFile(file.getAbsolutePath().replace(new GetFileExtension().getExtension(file.getName()),"Resized"+new GetFileExtension().getExtension(file.getName())));
				}else if(ext.equalsIgnoreCase(".tiff")){
					TIFFTool tiff = new TIFFTool();
					tiff.TiffConverter(path.getText(),new getSystemInfos().getTmpDir(),".png");
					
			        BufferedImage bi = ImageIO.read(new File(Path));
							for (int i : new int[] {alpha}) {
								Thumbnails.of("C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),".png"))
							            .size( bi.getWidth(),bi.getHeight())
							            .rotate(i)
			        .toFile("C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),"Rotated"+alpha+"°"+".png"));
							}
					String name ="C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),"Rotated"+alpha+"°"+".png");
					PNGTool png =new PNGTool();
					png.PngConverter(name, file.getParent(),".tiff");
					File f=new File(name);
					f.deleteOnExit();
				}else if(ext.equalsIgnoreCase(".tif")){
					TIFFTool tiff = new TIFFTool();
					tiff.TiffConverter(path.getText(),new getSystemInfos().getTmpDir(),".png");
					BufferedImage bi = ImageIO.read(new File(Path));
					for (int i : new int[] {alpha}) {
					
					Thumbnails.of("C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),".png"))
			        .size( bi.getWidth(),bi.getHeight())
					.rotate(i)
			        .toFile("C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),"Rotated"+alpha+"°"+".png"));
					}
					String name ="C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),"Rotated"+alpha+"°"+".png");
					PNGTool png =new PNGTool();
					png.PngConverter(name, file.getParent(),".tif");
					
				}
				
				
				}
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null,"some fields are invalid please verify your inputs","Warning",JOptionPane.WARNING_MESSAGE,null);
					}catch( IOException d){
						JOptionPane.showMessageDialog(null,"file not found \n plese"
								+ "choose a valid file \n"
								+ "and try again","Warning",JOptionPane.WARNING_MESSAGE,null);
					}
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					Rotate.setEnabled(true);	
					}
				
			});
	    	this.setContentPane(Panel);
	    	
}
}
