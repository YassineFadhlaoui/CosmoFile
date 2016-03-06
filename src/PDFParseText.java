import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.IllegalFormatException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;


public class PDFParseText extends JDialog {
/**
	 * 
	 */
	private static final long serialVersionUID = -921064505627988624L;

public PDFParseText(JFrame parent,String title,boolean modal) {
		
		super(parent,title,modal);
	    setSize(330,120);
	    setLocation(600,250);
	    ImageIcon imgicon = new ImageIcon("file/addtext.png");
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
    	
    	JButton dob = new JButton("Extract Text");
    	dob.setEnabled(false);;
    	dob.setBounds(120,60,100,20);
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
		JOptionPane.showMessageDialog(null,"An unknown error occurred while opening the folder","Warning",JOptionPane.WARNING_MESSAGE,null);
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
			try{
		
				@SuppressWarnings("unused")
				PdfReader pdfReader = new PdfReader(path.getText());
				
					
					 try {
						
						 PdfReader reader = new PdfReader(path.getText());
					        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
					        PrintWriter out = new PrintWriter(new FileOutputStream(pdfFile.getAbsolutePath().replace(pdfFile.getName(),"modified "+pdfFile.getName())+".txt"));
					        TextExtractionStrategy strategy;
					        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
					            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
					            out.println(strategy.getResultantText());
					        }
					        out.flush();
					        out.close();
					        reader.close();
					     

					File f = new File(pdfFile.getAbsolutePath().replace(pdfFile.getName(),"modified "+pdfFile.getName()+".txt"));
					if(f.exists() && (f.length()/1024)>0){
						@SuppressWarnings("unused")
						ErrorClass er = new ErrorClass(null, "Error", true,"      Done !", "");	
					}
					    } catch (Exception e) {
					    	@SuppressWarnings("unused")
							ErrorClass er = new ErrorClass(null, "Error", true,"fatal error ", "  encountered  !");
					    	e.printStackTrace();
					    }
				
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
