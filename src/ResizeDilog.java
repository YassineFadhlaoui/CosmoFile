import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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


public class ResizeDilog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9061337189260710573L;
	/**
	 * 
	 */

	public ResizeDilog(JFrame parent,String title,boolean modal) {
		  super(parent,title,modal);
	    setSize(245,209);
	    setLocation(600,250);
	    ImageIcon imgicon = new ImageIcon("file/blackrect1.png");
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
	    	path.setText("Choose an Image To resize");
	    	path.setForeground(Color.WHITE);
	    	path.setBounds(40,20,190,25);
	    	Panel.add(path);
	    	JButton Resize = new JButton("Resize");
	    	Resize.setEnabled(false);;
	    	Resize.setBounds(75,120,80,30);
	    	Resize.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	Panel.add(Resize);
	    	
	    	JLabel ResizeLabel = new JLabel("Resize :");
	    	ResizeLabel.setForeground(Color.white);
	    	ResizeLabel.setBounds(20,72,70,25);
	    	Panel.add(ResizeLabel);
	    	
	    	JLabel xl= new JLabel("x");
	    	xl.setForeground(Color.white);
	    	xl.setBounds(110,70,20,25);
	    	Panel.add(xl);
	    	
	    	JLabel pixels= new JLabel("pixels");
	    	pixels.setForeground(Color.white);
	    	pixels.setBounds(160,70,30,25);
	    	Panel.add(pixels);
	    	
	    	JTextField x = new JTextField();
	    	JTextField y = new JTextField();
	    	x.setBounds(70,70,35,25);
	    	y.setBounds(120,70,35,25);
	    	
	    	Panel.add(x);
	    	Panel.add(y);
	    	
	    	JLabel bg = new JLabel();
	    	bg.setBounds(-5,-3,243,190);
	    	bg.setIcon(new ImageIcon("file/blueWindow.png"));
	    	Panel.add(bg);
	    	
	    	Choose.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Choose a file", "jpg", "gif","jpeg","bmp","tiff","png",".tif");
						BrowseDirectory BD = new BrowseDirectory(filter);
						BD.BrowseFile();
						path.setText(BD.getReader());
						Resize.setEnabled(true);
					} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"An unknown error occurred","Warning",JOptionPane.WARNING_MESSAGE,null);
					}
	
				}
			});
	    	
	    	Resize.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Resize.setEnabled(false);
					try{
				int height=Integer.parseInt(x.getText())	;
				int wedith=Integer.parseInt(y.getText())	;
				if(path.getText().isEmpty() || path.getText().equalsIgnoreCase("null")){
					JOptionPane.showMessageDialog(null," Please choose a file","Warning",JOptionPane.WARNING_MESSAGE,null);
					
				}else{
					
					 File file=new File(path.getText());
					 String fileName= file.getName();
					 String ext= new GetFileExtension().getExtension(fileName);
					 if(ext.equalsIgnoreCase(".png")
							 ||ext.equalsIgnoreCase(".jpg")||ext.equalsIgnoreCase(".bmp")||ext.equalsIgnoreCase(".jpeg")
							 ||ext.equalsIgnoreCase(".gif")
							 ){
					Thumbnails.of(path.getText())
			        .size(wedith,height)
			        .toFile(file.getAbsolutePath().replace(new GetFileExtension().getExtension(file.getName()),"Resized"+new GetFileExtension().getExtension(file.getName())));
				}else if(ext.equalsIgnoreCase(".tiff")){
					TIFFTool tiff = new TIFFTool();
					tiff.TiffConverter(path.getText(),new getSystemInfos().getTmpDir(),".png");
					Thumbnails.of("C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),".png"))
			        .size(wedith,height)
			        .toFile("C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),"Resized.png"));
					String name ="C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),"Resized.png");
					PNGTool png =new PNGTool();
					png.PngConverter(name, file.getParent(),".tiff");
					@SuppressWarnings("unused")
					boolean f = new File(name).delete();
				}else if(ext.equalsIgnoreCase(".tif")){
					TIFFTool tiff = new TIFFTool();
					tiff.TiffConverter(path.getText(),new getSystemInfos().getTmpDir(),".png");
					Thumbnails.of("C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),".png"))
			        .size(wedith,height)
			        .toFile("C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),"Resized.png"));
					String name ="C:/FileConverter/"+fileName.replace(new GetFileExtension().getExtension(fileName),"Resized.png");
					PNGTool png =new PNGTool();
					png.PngConverter(name, file.getParent(),".tif");
					@SuppressWarnings("unused")
					boolean f = new File(name).delete();
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
					Resize.setEnabled(true);	
					}
				
			});
	    	this.setContentPane(Panel);
	    	
}
}
