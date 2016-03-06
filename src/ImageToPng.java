import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
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
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageToPng  extends JDialog{
	public static Image makeColorTransparent(BufferedImage im, final Color color) {
		ImageFilter filter = new RGBImageFilter() {
			public int markerRGB = color.getRGB() | 0xFF000000;
			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					return 0x00FFFFFF & rgb;
				} else {
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}
	private static BufferedImage imageToBufferedImage(Image image) {

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

		return bufferedImage;

	}
	private static final long serialVersionUID = -5811010802083626705L;
	public ImageToPng(JFrame parent,String title,boolean modal) {
		super(parent,title,modal);
	    setSize(210,150);
	    setLocation(600,250);
	    ImageIcon imgicon = new ImageIcon("file/blackrect2.png");
	    setIconImage(imgicon.getImage());
	    initComponent();
	    setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setVisible(true);
	  }

	private void initComponent() {
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
    	path.setBounds(40,20,130,25);
    	Panel.add(path);
    	
    	JButton dob = new JButton("Do");
    	dob.setEnabled(false);;
    	dob.setBounds(150,70,50,20);
    	dob.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	Panel.add(dob);
    	
    	JLabel label = new JLabel("Remove Background :");
    	label.setBackground(Color.gray);
    	label.setForeground(Color.white);
    	label.setBounds(10,70,180,20);
    		Panel.add(label);
    		JLabel bg = new JLabel();
	    	bg.setBounds(-5,-10,243,300);
	    	bg.setIcon(new ImageIcon("file/blueWindow2.png"));
	    	Panel.add(bg);
	    	dob.setEnabled(false);
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
					} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"An unknown error occurred while converting the file","Warning",JOptionPane.WARNING_MESSAGE,null);
					}
				}
			});
	    	dob.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dob.setEnabled(false);
					try {
					File file = new File(path.getText());
					if(!file.exists() || path.getText().equalsIgnoreCase("null")){
						ErrorClass er = new ErrorClass(null, "Choose a file",true,"make sure that you have ","choose a file");
					}else{
						File in = new File(path.getText());
						BufferedImage source;
						
							source = ImageIO.read(in);
						int color = source.getRGB(0,0);
						Image image = makeColorTransparent(source, new Color(color));
						BufferedImage transparent = imageToBufferedImage(image);
						File out = new File(file.getAbsolutePath().replace(new GetFileExtension().getExtension(file.getName()),".png"));
						ImageIO.write(transparent, "PNG", out);
						}
					} catch (IOException e) {
						ErrorClass er = new ErrorClass(null, "Choose a file",true,"Please","choose a file");
					}finally{
						dob.setEnabled(true);
					}
					
				}
			});
    		this.setContentPane(Panel);
		
	}
}
