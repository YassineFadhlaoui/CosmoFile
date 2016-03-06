import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.IllegalFormatException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
public class PDFAddImage extends JDialog{
/**
	 * 
	 */
	private static final long serialVersionUID = 772446035369295068L;
public PDFAddImage(JFrame parent,String title,boolean modal) {
	
		super(parent,title,modal);
	    setSize(350,250);
	    setLocation(600,250);
	    ImageIcon imgicon = new ImageIcon("file/addanimg.png");
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
	    	
	    	
	    	JButton dob = new JButton("Do");
	    	dob.setEnabled(false);;
	    	dob.setBounds(100,150,50,20);
	    	dob.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	Panel.add(dob);
	    	
	    	JLabel gl= new JLabel("Choose a Pdf File :");
	    	gl.setForeground(Color.white);
	    	gl.setBounds(10,20,150,25);
	    	Panel.add(gl);
	    	
	    	JButton Choose = new JButton();
	    	Choose.setBounds(120,20,25,25);
	    	Choose.setIcon(new ImageIcon("file/folder.conv"));
	    	Panel.add(Choose);
	    	
	    	JLabel path = new JLabel ();
	    	path.setText("");
	    	path.setForeground(Color.WHITE);
	    	path.setBounds(150,20,200,25);
	    	Panel.add(path);
	    	
	    	JLabel rl= new JLabel("Choose an Image :");
	    	rl.setForeground(Color.white);
	    	rl.setBounds(10,50,150,25);
	    	Panel.add(rl);
	    	
	    	JButton Choose1 = new JButton();
	    	Choose1.setBounds(120,50,25,25);
	    	Choose1.setIcon(new ImageIcon("file/folder.conv"));
	    	Panel.add(Choose1);
	    	
	    	JLabel path1 = new JLabel ();
	    	path1.setText("");
	    	path1.setForeground(Color.WHITE);
	    	path1.setBounds(150,50,190,25);
	    	Panel.add(path1);
	    	
	    	JLabel page= new JLabel("Choose a page:");
	    	page.setForeground(Color.white);
	    	page.setBounds(10,80,150,25);
	    	Panel.add(page);
	    	
	    	JTextField num= new JTextField("0");
	    	num.setBounds(110,80,30,20);
	    	Panel.add(num);
	    	
	    	JLabel pos= new JLabel("Position :");
	    	pos.setForeground(Color.white);
	    	pos.setBounds(10,110,150,25);
	    	Panel.add(pos);
	    	
	    	JTextField x= new JTextField("0");
	    	x.setBounds(100,110,30,20);
	    	Panel.add(x);
	    	
	    	JLabel cr= new JLabel("x");
	    	cr.setForeground(Color.white);
	    	cr.setBounds(145,110,20,25);
	    	Panel.add(cr);
	    	
	    	JTextField y= new JTextField("0");
	    	y.setBounds(170,110,30,20);
	    	Panel.add(y);
	    	
Choose.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"*.PDF", "pdf","PDF","Pdf");
						BrowseDirectory BD = new BrowseDirectory(filter);
						BD.BrowseFile();
						path.setText(BD.getReader());
						if(path1.getText() !="null" && !path1.getText().isEmpty() )
						dob.setEnabled(true);
						
					} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"An unknown error occurred please try again","Warning",JOptionPane.WARNING_MESSAGE,null);
					}
	
				}
			});
	    	
	    	
Choose1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Choose an Image", "jpg", "gif","jpeg","bmp","tiff","png","tif");
						BrowseDirectory BD = new BrowseDirectory(filter);
						BD.BrowseFile();
						path1.setText(BD.getReader());
						if(path.getText() !="null" && !path.getText().isEmpty() )
						dob.setEnabled(true);
						
					} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"an error occured","Warning",JOptionPane.WARNING_MESSAGE,null);
					}
	
				}
			});


dob.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		File pdfFile = new File(path.getText());
		File ImageFile = new File(path.getText());
		dob.setEnabled(false);
		if(!pdfFile.exists() || !ImageFile.exists()){

			@SuppressWarnings("unused")
			ErrorClass er = new ErrorClass(null, "Error", true,"File not Found !", "");
		}else{
			int number,xp,yp;
			try{
			number=Integer.parseInt(num.getText());
			xp=Integer.parseInt(x.getText());
			yp=Integer.parseInt(y.getText());
			
			
				PdfReader pdfReader = new PdfReader(path.getText());
				if(pdfReader.getNumberOfPages()<number || number<=0){
					@SuppressWarnings("unused")
					ErrorClass er = new ErrorClass(null, "Error", true,"Page not Found !", "");	
				}else{
					
					 try {
					   

					      PdfStamper pdfStamper = new PdfStamper(pdfReader,
					            new FileOutputStream(pdfFile.getAbsolutePath().replace(pdfFile.getName(),"modified "+pdfFile.getName())));

					      Image image = Image.getInstance(path1.getText());


					          PdfContentByte content = pdfStamper.getUnderContent(number);

					          image.setAbsolutePosition((float)xp, (float)yp);

					          content.addImage(image);
					      

					      pdfStamper.close();

					File f = new File(pdfFile.getAbsolutePath().replace(pdfFile.getName(),"modified "+pdfFile.getName()));
					if(f.exists() && (f.length()/1024)>0){
						@SuppressWarnings("unused")
						ErrorClass er = new ErrorClass(null, "Error", true,"      Done !", "");	
					}
					    } catch (Exception e) {
					    	@SuppressWarnings("unused")
							ErrorClass er = new ErrorClass(null, "Error", true,"Fatal error ", "encountred  !");
					    	e.printStackTrace();
					    }
				};
			} catch (IOException e) {
				
				@SuppressWarnings("unused")
				ErrorClass er = new ErrorClass(null, "Error", true,"File not Found !", "");
			
			}catch(NumberFormatException  | IllegalFormatException   e){
				@SuppressWarnings("unused")
				ErrorClass er = new ErrorClass(null, "Error", true,"Number Format ", "Exception !");
			}
	
		}
		dob.setEnabled(true);
	}
});    	
	    	this.setContentPane(Panel);
	    }
	    

}
