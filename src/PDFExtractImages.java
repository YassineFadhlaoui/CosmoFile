

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
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfImageObject;

public class PDFExtractImages extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1545039825330504765L;
	/**
	 * 
	 */
	
	

	public PDFExtractImages(JFrame parent,String title,boolean modal) {
		super(parent,title,modal);
	    setSize(245,209);
	    setLocation(600,250);
	    ImageIcon imgicon = new ImageIcon("file/extract.png");
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
    	
    	
    	
    	path.setText("Choose a PDF File");
    	path.setForeground(Color.WHITE);
    	path.setBounds(40,20,190,25);
    	Panel.add(path);
    	
    	JButton Extract = new JButton("Extract");
    	Extract.setEnabled(false);;
    	Extract.setBounds(75,120,80,30);
    	Extract.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	Panel.add(Extract);
    	JLabel bg = new JLabel();
    	bg.setBounds(-5,-3,243,190);
    	bg.setIcon(new ImageIcon("file/blueWindow.png"));
    	Panel.add(bg);
    	
    	Choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Choose a PDF File","pdf");
					BrowseDirectory BD = new BrowseDirectory(filter);
					BD.BrowseFile();
					path.setText(BD.getReader());
					Extract.setEnabled(true);
				} catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(null,"An unknown error occurred","Warning",JOptionPane.WARNING_MESSAGE,null);
				}

			}
		});
    	
    	
    	Extract.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String ext=new GetFileExtension().getExtension(path.getText());
				if(path.getText().equalsIgnoreCase("null") || ext.equalsIgnoreCase(".pdf")){
					try {
						
						Extract.setEnabled(false);
						File pdffile = new File(path.getText());
				        PdfReader pr=new PdfReader(path.getText());
				PRStream pst;
				PdfImageObject pio;
				PdfObject po;
				boolean ImDir = new File((pdffile.getAbsolutePath().replace(new GetFileExtension().getExtension(pdffile.getName())," pdf"))+"Images").mkdirs();
				if(ImDir==true){
				int n=pr.getXrefSize(); //number of objects in pdf document
				for(int i=0;i<n;i++){
				po=pr.getPdfObject(i); //get the object at the index i in the objects collection
				if(po==null || !po.isStream()) //object not found so continue
				continue;
				pst=(PRStream)po; //cast object to stream
				PdfObject type=pst.get(PdfName.SUBTYPE); //get the object type
				//check if the object is the image type object
				if(type!=null && type.toString().equals(PdfName.IMAGE.toString())){
				pio=new PdfImageObject(pst); //get the image  
				BufferedImage bi=pio.getBufferedImage(); //convert the image to buffered image
				ImageIO.write(bi, "png", new File((pdffile.getAbsolutePath().replace(new GetFileExtension().getExtension(pdffile.getName())," pdf"))+"Images"+"/"+pdffile.getName()+" image"+i+".png")); //write the buffered image
				//to local disk
				}
				   }
				}else{
					JOptionPane.showMessageDialog(null,"Unable to create directory  Is its parent directory is not writable by the current user? ","Warning",JOptionPane.WARNING_MESSAGE,null);
				}
						
						
					
					} catch (IOException e) {
					
						JOptionPane.showMessageDialog(null,"Fatal error encountred please try again","Warning",JOptionPane.WARNING_MESSAGE,null);
					}
				}
				Extract.setEnabled(true);
			}
			
		});
    	this.setContentPane(Panel);
	}

	protected void extractPics(String filename) throws IOException {
	
}
}
