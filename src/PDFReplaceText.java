import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


public class PDFReplaceText extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public PDFReplaceText(JFrame parent,String title,boolean modal) {
		
		super(parent,title,modal);
	    setSize(330,220);
	    setLocation(600,250);
	    ImageIcon imgicon = new ImageIcon("file/replaceImage.png");
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
    	
    	
    	JButton dob = new JButton("Do");
    	dob.setEnabled(false);;
    	dob.setBounds(140,150,50,20);
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
    	
    	
    	JLabel rl= new JLabel("Text to Replace :");
    	rl.setForeground(Color.white);
    	rl.setBounds(10,80,150,25);
    	Panel.add(rl);
    	
    	JTextField old = new JTextField();
    	old.setText("paste text to replace here*");
    	old.setForeground(Color.gray);
    	old.setBounds(100,80,190,20);
    	Panel.add(old);
    	
    	old.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {	
				if(old.getText().isEmpty()){
					old.setText("paste text to replace here*");
					old.setForeground(Color.gray);	
				}
				
			}
			
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			public void mouseClicked(MouseEvent arg0) {
				old.setText("");
				old.setForeground(Color.BLACK);
				
				
			}
		});
    	
    	
    	JLabel nt= new JLabel("New text :");
    	nt.setForeground(Color.white);
    	nt.setBounds(10,110,150,25);
    	Panel.add(nt);
    	
    	JTextField newtext = new JTextField("");
    	newtext.setText("new text here*");
    	newtext.setForeground(Color.gray);
    	newtext.setBounds(100,110,190,20);
    	Panel.add(newtext);
    	newtext.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
	
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(newtext.getText().isEmpty()){
					newtext.setText("new text here*");
					newtext.setForeground(Color.gray);	
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				newtext.setText("");
				newtext.setForeground(Color.BLACK);
				
				
			}
		});
    	JLabel page= new JLabel("Choose a page:");
    	page.setForeground(Color.white);
    	page.setBounds(10,50,150,25);
    	Panel.add(page);
    	
    	JTextField num= new JTextField("0");
    	num.setBounds(110,50,30,20);
    	Panel.add(num);
    	Choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"*.PDF", "pdf","PDF","Pdf");
					BrowseDirectory BD = new BrowseDirectory(filter);
					BD.BrowseFile();
					path.setText(BD.getReader());
					if(path.getText() !="null" && !path.getText().isEmpty() )
					dob.setEnabled(true);
					
				} catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(null,"fatal error encountered while opening the folder","Warning",JOptionPane.WARNING_MESSAGE,null);
				}

			}
		});
    	
    	
    	dob.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			File pdfFile = new File(path.getText());
    		
    			dob.setEnabled(false);
    			if(!pdfFile.exists() ){

    				@SuppressWarnings("unused")
    				ErrorClass er = new ErrorClass(null, "Error", true,"File not Found !", "");
    			}else{
    				int number;
    				try{
    				number=Integer.parseInt(num.getText());
    					PdfReader pdfReader = new PdfReader(path.getText());
    					if(pdfReader.getNumberOfPages()<number || number<=0){
    						@SuppressWarnings("unused")
    						ErrorClass er = new ErrorClass(null, "Error", true,"Page not Found !", "");	
    					}else{
    						
    						 try {
    							 PdfReader reader = new PdfReader(path.getText());
    						        PdfDictionary dict = reader.getPageN(number);
    						        PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
    						        if (object instanceof PRStream) {
    						            PRStream stream = (PRStream)object;
    						            byte[] data = PdfReader.getStreamBytes(stream);
    						            stream.setData(new String(data).replace(old.getText(), newtext.getText()).getBytes());
    						        }
    						        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(pdfFile.getAbsolutePath().replace(pdfFile.getName(),"modified "+pdfFile.getName())));
    						        stamper.close();
    						        reader.close();

    						     

    						File f = new File(pdfFile.getAbsolutePath().replace(pdfFile.getName(),"modified "+pdfFile.getName()));
    						if(f.exists() && (f.length()/1024)>0){
    							@SuppressWarnings("unused")
    							ErrorClass er = new ErrorClass(null, "Error", true,"      Done !", "");	
    						}
    						    } catch (Exception e) {
    						    	@SuppressWarnings("unused")
    								ErrorClass er = new ErrorClass(null, "Error", true,"A fatal error ", "encountered  !");
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
    	
    	
    	this.getContentPane().add(Panel);
		
	}
}
