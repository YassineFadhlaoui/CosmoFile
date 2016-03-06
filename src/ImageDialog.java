import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class ImageDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	public ImageDialog(JFrame parent,String title,boolean modal) {
		  super(parent,title,modal);
	    setSize(250, 300);
	    setLocation(500,200);
	    ImageIcon imgicon = new ImageIcon("file/Images.png");
	    setIconImage(imgicon.getImage());
	    initComponent();
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setVisible(true);
	  }
	    public void initComponent(){
	    	JPanel Panel = new JPanel();
	    	Panel.setLayout(null);
	    	Panel.setBackground(Color.gray);
	    	
	    	JLabel resize = new JLabel();
	    	resize.setBounds(20,0,90,90);
	    	resize.setIcon(new ImageIcon("file/blackrect1.png"));
	    	resize.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	
	    	JLabel resizep = new JLabel("Resize");
	    	resizep.setBounds(50,50,90,90);
	    	resizep.setForeground(Color.white);
	    	Panel.add(resizep);
	    	resize.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					resize.setSize(90,90);
					
				}
				
		
				public void mouseEntered(MouseEvent arg0) {
					resize.setSize(100,100);
					
				}
		
				public void mouseClicked(MouseEvent arg0) {
					@SuppressWarnings("unused")
					ResizeDilog RD =  new ResizeDilog(null,"Resize Image", true);
					
					
				}
			});
	    	
	    	
	    	
	    	
	    	JLabel merge = new JLabel();
	    	merge.setBounds(130,0,90,90);
	    	merge.setIcon(new ImageIcon("file/blackrect2.png"));
	    	merge.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	Panel.add(merge);
	    	Panel.add(resize);
	    	
	    	JLabel mergel = new JLabel("Remove Background");
	    	mergel.setBounds(120,85,140,20);
	    	mergel.setBackground(Color.gray);
	    	mergel.setForeground(Color.white);
	    	Panel.add(mergel);
	    	merge.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					merge.setSize(90,90);
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					merge.setSize(100,100);
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					@SuppressWarnings("unused")
					ImageToPng ITP = new ImageToPng(null, "Remove Background",true);
					
				}
			});
	    	
	    	JLabel rotate = new JLabel();
	    	 rotate.setBounds(20,120,90,90);
	    	 rotate.setIcon(new ImageIcon("file/Rotate.png"));
	    	 rotate.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	Panel.add(rotate);
	    	JLabel  rotatep = new JLabel("Rotate");
	    	rotatep.setBounds(50,170,90,90);
	    	
	    	rotatep.setForeground(Color.white);
	    	Panel.add(rotatep);
	    	rotate.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					rotate.setSize(90,90);
					
				}
				
		
				public void mouseEntered(MouseEvent arg0) {
					rotate.setSize(100,100);
					
				}
		
				@SuppressWarnings("unused")
				public void mouseClicked(MouseEvent arg0) {
					Rotate r = new Rotate(null,"Rotate",true);
					
					
					
				}
			});
	    	
	    	JLabel gray = new JLabel();
	    	gray.setBounds(130,120,90,90);
	    	gray.setIcon(new ImageIcon("file/gray.png"));
	    	gray.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	Panel.add(gray);
	    	JLabel  grayp = new JLabel("Change Colors");
	    	grayp.setBounds(150,170,100,90);
	    	
	    	grayp.setForeground(Color.white);
	    	Panel.add(grayp);
	    	gray.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {	
				}
				@Override
				public void mousePressed(MouseEvent arg0) {	
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					gray.setSize(90,90);	
				}
				public void mouseEntered(MouseEvent arg0) {
					gray.setSize(100,100);	
				}
		
				@SuppressWarnings("unused")
				public void mouseClicked(MouseEvent arg0) {
					ImageColors IC = new ImageColors(null, "Change Colors", true);	
				}
			});
	    	
	    	
	    	
	    	
	    	this.getContentPane().add(Panel);
	    }
}
