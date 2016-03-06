import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class PDFOptions extends JDialog{

	private static final long serialVersionUID = -7055660554601080041L;

public PDFOptions(JFrame parent,String title,boolean modal) {
		  super(parent,title,modal);
	    setSize(250, 300);
	    setLocation(500,200);
	    ImageIcon imgicon = new ImageIcon("file/Images.png");
	    setIconImage(imgicon.getImage());
	    initComponent();
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setVisible(true);
}

private void initComponent() {
	JPanel Panel = new JPanel();
	Panel.setLayout(null);
	Panel.setBackground(Color.gray);
	
	JLabel Extract = new JLabel();
	Extract.setBounds(20,0,90,90);
	Extract.setIcon(new ImageIcon("file/extract.png"));
	Extract.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
	JLabel Extractl = new JLabel("Extract Images");
	Extractl.setBounds(30,50,90,90);
	Extractl.setForeground(Color.white);
	Panel.add(Extractl);
	Extract.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
		
			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			Extract.setSize(90,90);
			
		}
		

		public void mouseEntered(MouseEvent arg0) {
			Extract.setSize(100,100);
			
		}

		public void mouseClicked(MouseEvent arg0) {
			
			@SuppressWarnings("unused")
			PDFExtractImages pdf = new PDFExtractImages(null, "Extract Images From Pdf",true);
			
			
		}
	});
	
	
	
	
	JLabel Add = new JLabel();
	Add.setBounds(130,0,90,90);
	Add.setIcon(new ImageIcon("file/addanimg.png"));
	Add.setCursor(new Cursor(Cursor.HAND_CURSOR));
	Panel.add(Add);
	Panel.add(Extract);
	
	JLabel addl = new JLabel("Add Images");
	addl.setBounds(150,85,140,20);
	addl.setBackground(Color.gray);
	addl.setForeground(Color.white);
	Panel.add(addl);
	Add.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			
			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			Add.setSize(90,90);
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			Add.setSize(100,100);
			
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			@SuppressWarnings("unused")
			PDFAddImage addim = new PDFAddImage(null, "Add an Image", true);
			
		}
	});
	
	JLabel rotate = new JLabel();
	 rotate.setBounds(20,120,90,90);
	 rotate.setIcon(new ImageIcon("file/addtext.png"));
	 rotate.setCursor(new Cursor(Cursor.HAND_CURSOR));
	Panel.add(rotate);
	JLabel  rotatep = new JLabel("Extract text");
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
			
			PDFParseText pdfparse = new PDFParseText(null, "Extract text", true);
			
			
		}
	});
	
	JLabel gray = new JLabel();
	gray.setBounds(130,120,90,90);
	gray.setIcon(new ImageIcon("file/replaceImage.png"));
	gray.setCursor(new Cursor(Cursor.HAND_CURSOR));
	Panel.add(gray);
	JLabel  grayp = new JLabel("Replace text");
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
			PDFReplaceText rt = new PDFReplaceText(null,"Replace Text",true);
		}
	});
	
	
	
	
	this.getContentPane().add(Panel);	
	
}



}
