
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings({ "serial" })
public class Graphic extends JFrame {
	JMenuBar menu=new JMenuBar();
	JMenu Pdf=new JMenu("PDF");
	JMenu ImagesMenu = new JMenu("Images");
	JTextField path = new JTextField();
	ImageIcon ok = new ImageIcon("file/ok.png");
	JMenuItem AddImage=new JMenuItem("Add an Image");
	JMenuItem ExtractImages=new JMenuItem("Extract Images");
	JMenuItem GetText=new JMenuItem("Extract text");
	JMenuItem ReplaceText=new JMenuItem("Replace Text");

	JMenuItem Resize= new JMenuItem("Resize");
	JMenuItem Removebg= new JMenuItem("Remove Background");
	JMenuItem Changecolors= new JMenuItem("Change Image Colors");
	JMenuItem Rotate= new JMenuItem("Rotate");
	
	
	JMenuItem help= new JMenuItem("Help");
	JMenu Help=new JMenu("Help");
	
	 JLabel twitter=new JLabel();
	    JLabel Gmail = new JLabel();
	
	
	 JButton openFile = new JButton();
	 JLabel loadingLabel1 = new JLabel();
	
	
	JPanel Panel = new JPanel();
	
	ImageIcon convertStatus= new ImageIcon("file/convertbg.bg");
	
	JComboBox <String> combo ;
	
	JButton Convert = new JButton();
	
	JProgressBar progression;
	
	JCheckBox checkDir = new JCheckBox("same directory as original file");
    JLabel distinationDir ;
    
    String direction="";
    JLabel loadingLabel = new JLabel();
    OpenDir openthisDir = new OpenDir();

	  static final int MINIMUM=0;
	  
	  class browseListener implements ActionListener{
			public synchronized void actionPerformed(ActionEvent arg0){
				BrowseDirectory choose = new BrowseDirectory();
				try{
					choose.BrowseFile();
					
				path.setText(String.valueOf(choose.getReader()));
				choose.getExtension();
				file=String.valueOf(choose.getReader());
				direction=choose.getOriginalDir();
			
			
			Extension=choose.getExtension();
			
			
			
			if(Extension.equalsIgnoreCase(".tiff")){
				comboBoxItems.removeAllElements();
				TIFFTool TIFFT = new TIFFTool();
				TIFFT.InisilizeBox(comboBoxItems, Extension);
			}
			
			else if (Extension.equalsIgnoreCase(".png")){
				comboBoxItems.removeAllElements();
				PNGTool PNGT = new PNGTool();
			PNGT.InisilizeBox(comboBoxItems, Extension);}
			
			else if(Extension.equalsIgnoreCase(".jpg")){
				comboBoxItems.removeAllElements();
				JPGTool JPG = new JPGTool();
			JPG.InisilizeBox(comboBoxItems, Extension);}
			else if(Extension.equalsIgnoreCase(".jpeg")){
				comboBoxItems.removeAllElements();
				JPEGTool JPEG =new JPEGTool();
				JPEG.InisilizeBox(comboBoxItems, Extension);
			}
			else if(Extension.equalsIgnoreCase(".gif")){
				comboBoxItems.removeAllElements();
				GIFTool GIF = new GIFTool();
				GIF.InisilizeBox(comboBoxItems, Extension);
				}
			else if(Extension.equalsIgnoreCase(".bmp")){
				comboBoxItems.removeAllElements();
				BMPTool BMPT= new BMPTool();
				BMPT.InisilizeBox(comboBoxItems, Extension);
			}
			
			else if(Extension.equalsIgnoreCase(".svg")){
				comboBoxItems.removeAllElements();
				SVGTool SVG = new SVGTool();
				SVG.InisilizeBox(comboBoxItems, Extension);
			}
			else if(Extension.equalsIgnoreCase(".tif")){
				comboBoxItems.removeAllElements();
				TIFTool tif = new TIFTool();
				tif.InisilizeBox(comboBoxItems, Extension);
			}
			else if(Extension.equalsIgnoreCase(".pptx")){
				comboBoxItems.removeAllElements();
				PPTXTool PPTT = new PPTXTool();
				PPTT.PPTcombBox(comboBoxItems, Extension);	
			}
			else if(Extension.equalsIgnoreCase(".txt")){
				comboBoxItems.removeAllElements();
				TextTool text = new TextTool();
				text.TxtcombBox(comboBoxItems, Extension);
			}
			else if(Extension.equalsIgnoreCase(".html")){
				comboBoxItems.removeAllElements();
			HTMLConverter htm = new HTMLConverter();
				htm.TxtcombBox(comboBoxItems, Extension);}
			else if(Extension.equalsIgnoreCase(".xlsx")){
				comboBoxItems.removeAllElements();
			XlsxTool XT = new XlsxTool();
			XT.setSupportedFormats(comboBoxItems, Extension);}
			else if(Extension.equalsIgnoreCase(".xls")){
				comboBoxItems.removeAllElements();
				XLSTOOL xls =new XLSTOOL();
				xls.setSupportedFormats(comboBoxItems, Extension);
				
			}else if(Extension.equalsIgnoreCase(".ico")){
				comboBoxItems.removeAllElements();
				ICOTool ico = new ICOTool();
				ico.InisilizeBox(comboBoxItems, Extension);
			}else if(Extension.equalsIgnoreCase(".ppt")){
				PPTTool ppt = new PPTTool();
				ppt.PPTcombBox(comboBoxItems, Extension);
			}else if(Extension.equalsIgnoreCase(".pdf")){
				PDFTool pdf = new PDFTool();
				pdf.InisilizeBox(comboBoxItems, Extension);
			}
				}catch(FileNotFoundException e){
					
				}catch(Exception e){
					
				}
			}
		}
	  
	  
	
@SuppressWarnings("unchecked")
public  Graphic(){

	this.setTitle("CosmoFile");
	this.setSize(600,487);
	this.setLocation(350,100);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
    this.setVisible(true);
    this.setResizable(true);
    ImageIcon imgicon = new ImageIcon("file/file.am");
    setIconImage(imgicon.getImage());
    distinationDir= new JLabel(new getSystemInfos().DefaultTarget());
    
    Panel.setLayout(null);
    this.Pdf.add(AddImage);
    AddImage.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
		new PDFAddImage(null,"Add an Image to existing PDF file",true);
			
		}
	});
    
    this.Pdf.add(ExtractImages);
    ExtractImages.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new PDFExtractImages(null, "Extract Images From Pdf File",true);
			
		}
	});
    this.Pdf.add(ReplaceText);
    
    ReplaceText.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new PDFReplaceText(null,"Replace Text",true);
			
		}
	});
    this.Pdf.add(GetText);
    GetText.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new PDFParseText(null, "Extract text", true);
			
		}
	});
    this.ImagesMenu.add(Resize);
    Resize.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new ResizeDilog(null,"Resize Image", true);
			
		}
	});
    this.ImagesMenu.add(Removebg);
    Removebg.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new ImageToPng(null, "Remove Background",true);
			
		}
	});
    this.ImagesMenu.add(Rotate);
    Rotate.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new Rotate(null,"Rotate",true);
			
		}
	});
    this.ImagesMenu.add(Changecolors);
    Changecolors.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new ImageColors(null, "Change Colors", true);
			
		}
	});
    this.Help.add(help).addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
                Desktop.getDesktop().browse(new URI("http://wsdevelopment.blogspot.com/2015/07/cosmofile.html"));
        } catch (URISyntaxException | IOException ex) {  
                
        }
			
			
		}
	});;
    this.menu.add(Pdf);
    this.menu.add(ImagesMenu);
   
    //DOCs
    
    Footer footer = new Footer();
    footer.MakeFooter(Panel);
   path.setEditable(false); 
    //Pics
   // this.Help.add(option1 );
    this.menu.add(Help);
    this.setJMenuBar(menu);
    
    
    
    ImageIcon logoI = new ImageIcon("file/file.am");
    JLabel logo = new JLabel();
    logo.setIcon(logoI);
    Panel.add(logo);
    logo.setBounds(05, -15, 105, 105);
    
    ImageIcon facebook = new ImageIcon("file/b.jp");
    JButton share = new JButton();
    share.setIcon(facebook);
    share.setBounds(460,20,100,29);
    share.setCursor(new Cursor(Cursor.HAND_CURSOR));
    share.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
				try {
                    Desktop.getDesktop().browse(new URI("https://www.blogger.com/share-post.g?blogID=7265112850111286502&postID=7499838596024429838&target=facebook"));
            } catch (URISyntaxException | IOException ex) {  
                    
            }
		}
	});
    Panel.add(share);
    
     JLabel header = new JLabel();
    header.setBounds(0,-37,600,151);
    header.setIcon(new ImageIcon("file/header.PNG"));
    Panel.add(header);
    
    JLabel filechoose= new JLabel("Choose a file :");
    filechoose.setBounds(117,105,100,22);
    filechoose.setForeground(Color.white);
    Panel.add(filechoose);
    
    path.setBounds(250, 110, 300, 20);
    Panel.add(path);
    
    ImageIcon ic = new ImageIcon("file/folder.conv");
    JButton browse = new JButton("",ic);
    browse.setCursor(new Cursor(Cursor.HAND_CURSOR));
    Panel.add(browse);
    browse.setBounds(210, 107, 	25, 25);
    
    JLabel chooseFormat= new JLabel("Choose a format :");
    chooseFormat.setBounds(117,150,100,22);
    chooseFormat.setForeground(Color.white);
    Panel.add(chooseFormat);
 
    comboBoxItems.add("Available File Formats");
    JPanel p3 = new JPanel();
    p3.setBounds(245, 150,210,26);
    final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(comboBoxItems);
    combo= new JComboBox<String>(model);
   combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    combo.addItemListener(new ItemState());
    p3.setLayout(null);
    combo.setBounds(30,3,150,20);
    p3.setBackground(Color.gray);
    p3.add(combo);
    Panel.add(p3);
    
    JLabel chooseDir= new JLabel("Save To :");
    chooseDir.setBounds(117,195,100,22);
    chooseDir.setForeground(Color.white);
    Panel.add(chooseDir);
    
    JButton browseDir = new JButton("",ic);
    browseDir.setCursor(new Cursor(Cursor.HAND_CURSOR));
    Panel.add(browseDir);
    browseDir.setBounds(210, 195, 	25, 25);
    

    
    distinationDir.setForeground(Color.BLUE);
    distinationDir.setBounds(245, 195, 250, 25);
    distinationDir.setForeground(Color.white);
    distinationDir.setCursor(new Cursor(Cursor.HAND_CURSOR));
    Panel.add(distinationDir);
    distinationDir.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			openthisDir.DirOpener(distinationDir,direction);
			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			
			
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			distinationDir.setFont(chooseDir.getFont());;
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			
			Font fo = new Font(getFont().getName(),10,12);
			distinationDir.setFont(fo);
			
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			
		}
	});
    
    
    checkDir.setCursor(new Cursor(Cursor.HAND_CURSOR));
    checkDir.setBounds(380,220,170,20);
    checkDir.addActionListener(new checkDirListener());
    checkDir.setForeground(Color.white);
    checkDir.setBackground(Color.gray);
    Panel.add(checkDir);
    
 
    
    
    ImageIcon ico = new ImageIcon("file/converti.cv");
    Convert.setIcon(ico);
    Convert.setCursor(new Cursor(Cursor.HAND_CURSOR));
    Convert.setBounds(270,250,100,27);
    Panel.add(Convert);
    
    
 
 
    //PROCESSED WHEN CONVERT IS CLICKED
   
    GoTo gm = new GoTo();
    loadingLabel.setBounds(220,290,60,64);
    loadingLabel.setIcon(new ImageIcon("file/pub/fb.png"));
    gm.Go(loadingLabel, "https://www.facebook.com/pages/Software_Development/1145545418817585?ref=hl");
    
    twitter.setIcon(new ImageIcon("file/pub/twitter.png"));
    twitter.setBounds(290,290,60,64);
    Panel.add(twitter);
  
    twitter.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			twitter.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			twitter.setCursor(new Cursor(Cursor.HAND_CURSOR));	
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
                Desktop.getDesktop().browse(new URI("https://twitter.com/SoftDeveloping"));
        } catch (URISyntaxException | IOException ex) {  
                
        }

		}
	});
    Gmail.setIcon(new ImageIcon("file/pub/gmail.png"));
    Gmail.setBounds(360,290,60,64);
    Gmail.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			Gmail.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			Gmail.setCursor(new Cursor(Cursor.HAND_CURSOR));	
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
                Desktop.getDesktop().browse(new URI("http://wsdevelopment.blogspot.com/2015/07/blog-post.html"));
        } catch (URISyntaxException | IOException ex) {  
                
        }

		}
	});
    JLabel Youtube = new JLabel();
    Youtube.setBounds(495,340,60,60);
    Youtube.setIcon(new ImageIcon("file/pub/youtube.png"));
    gm.Go(Youtube, "https://youtu.be/Mw2gM7bDc_U");
    
    JLabel tuto = new JLabel("How to use !");
    tuto.setForeground(Color.WHITE);
    tuto.setBounds(496,397,120,20);
 
    Font font = tuto.getFont();
    @SuppressWarnings("rawtypes")
	Map attributes = font.getAttributes();
    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    tuto.setFont(font.deriveFont(attributes));
    
    gm.Go(tuto, "https://youtu.be/Mw2gM7bDc_U");
    Panel.add(tuto);
    
    Panel.add(Youtube);
    Panel.add(Gmail);
   Panel.add(loadingLabel);
  
 
   
    ImageIcon openicon =new  ImageIcon("file/open.cv");
    openFile.setIcon(openicon);
    openFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
    openFile.setBounds(282,380,88,25);
    Panel.add(openFile);
    openFile.setEnabled(false);
    openFile.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			File original = new File(file);
			  File verify = new File(distinationDir.getText()+"/"+original.getName().replace(new GetFileExtension().getExtension(file),SelectedExt));
			  
			  	new	OpenConvertedFile().OpenFile(verify.getAbsolutePath());
		}
	});
    
    
    JLabel About = new JLabel("About");
    About.setBounds(105, 410, 35, 15);
    Panel.add(About);
    About.setForeground(Color.BLUE);
    
    GoTo about = new GoTo(About,"http://wsdevelopment.blogspot.com/2015/07/cosmofile.html");
    about.Go(About,"http://wsdevelopment.blogspot.com/2015/07/cosmofile.html");	
    
    JLabel separator1 = new JLabel("|");
    separator1.setBounds(143,410,5,15);
    Panel.add(separator1);
    
    JLabel Help = new JLabel("Help");
    Help.setBounds(150,410, 30, 15);
    Panel.add(Help);
    Help.setForeground(Color.BLUE);
    
    GoTo help = new GoTo(Help,"http://wsdevelopment.blogspot.com/2015/07/cosmofile.html");
    help.Go(Help,"http://wsdevelopment.blogspot.com/2015/07/cosmofile.html");
    
    JLabel separator2 = new JLabel("|");
    separator2.setBounds(181,410,5,15);
    Panel.add(separator2);
    
    
    JLabel contact = new JLabel("Contact us");
    contact.setBounds(186,410,62,15);
    Panel.add(contact);
    contact.setForeground(Color.BLUE);
    GoTo contactus = new GoTo(contact,"http://wsdevelopment.blogspot.com/2015/07/blog-post.html");
    contactus.Go(contact,"http://wsdevelopment.blogspot.com/2015/07/blog-post.html");	
    
    
    
    Panel.setBackground(Color.gray);
    Panel.setForeground(Color.white);
   
    
    
  
    browse.addActionListener(new browseListener());
    Convert.addActionListener(new ConvertListener());
    this.setContentPane(Panel);
    browseDir.addActionListener(new browseDirListener());
   
}

String test="";
String file=null;
String Extension;
String Ext;
JOptionPane jop = new JOptionPane();
Vector<String> comboBoxItems = new Vector<String>();
JLabel pro = new JLabel("");
ChooseDir cr = new ChooseDir();

 class checkDirListener implements ActionListener{
	public synchronized void actionPerformed(ActionEvent e){
	if(checkDir.isSelected()){
		distinationDir.setText(direction);
			
	}
	}
}
class browseDirListener implements ActionListener{
	public void actionPerformed(ActionEvent arg0){
		
		try {
			cr.BrowseDir();
			distinationDir.setText(cr.getSelectedDirectory());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"please select a folder","Warning",JOptionPane.WARNING_MESSAGE,null);
		}
	}
}
String SelectedExt=null;
String SelectedDir;
class ItemState implements ItemListener{
	public void itemStateChanged(ItemEvent e){
		SelectedExt=String.valueOf(e.getItem());
			try {
				BufferedWriter br = new BufferedWriter(new FileWriter(new getSystemInfos().getTmpDir()+"/finalext.dll"));
				br.write(String.valueOf(e.getItem()));
				br.close();
			} catch (IOException e1) {
				
			}
	}
}
class ConvertListener implements ActionListener{
	
	 ImageIcon loadingIcon=new ImageIcon("file/loading.gif");

	public void actionPerformed(ActionEvent arg0){
	
		loadingLabel.setVisible(false);
	   loadingLabel1.setIcon(loadingIcon);
	    loadingLabel1.setBounds(210,267,80,80);
	   Panel.add(loadingLabel1);
	   JLabel ok1 = new JLabel();
	   Panel.add(ok1);
	   JTextArea Status = new JTextArea();
	   Status.setEditable(false);
	   Status.setBackground(Color.GRAY);
	   Status.setForeground(Color.white);
	   Status.setBounds(295,280,130,25);
	   Panel.add(Status);
	   
	   JTextArea Configuration = new JTextArea();
	   Configuration.setEditable(false);
	   
	   Configuration.setBackground(Color.GRAY);
	   Configuration.setForeground(Color.white);
	   Configuration.setBounds(295,305,150,25);
	  // Configuration.setVisible(false);
	   Panel.add( Configuration);
	   
	   
	   JTextArea Converting = new JTextArea();
	   Converting.setEditable(false);
	   Converting.setBackground(Color.GRAY);
	   Converting.setForeground(Color.white);
	   Converting.setBounds(295,330,150,25);
	   Panel.add(Converting);
	  
	   
	   JLabel ok2 = new JLabel();
	   JLabel ok3 = new JLabel();
	   ok3.setBounds(481,320,20,20);
	   ok3.setIcon(ok);
	   ok3.setVisible(false);
	   Panel.add(ok3);
	   Panel.add(ok2);
	  
	  try {
		BufferedReader  bre = new BufferedReader(new FileReader("tmp/finalext.dll"));
		SelectedExt=bre.readLine();
		bre.close();
		File f = new File("tmp/finalext.dll");
		f.delete();
	} catch (Exception e) {
		
	}
	  
	  Thread ConvertThread = new Thread(){
			public void run(){
			if(Extension.equalsIgnoreCase(".txt")){TextTool TT = new TextTool();TT.ConvertThisTextTo(file,SelectedExt,distinationDir.getText()); }
			else if(Extension.equalsIgnoreCase(".pptx")){PPTXTool PPTT=new PPTXTool();PPTT.ConvertThisPptxTo(file,distinationDir.getText(),SelectedExt);}
			else if(Extension.equalsIgnoreCase(".xlsx")){XlsxTool XT = new XlsxTool();XT.XlsxConverter(file, distinationDir.getText(), SelectedExt);}
			else if(Extension.equalsIgnoreCase(".html")){HTMLConverter HT = new HTMLConverter();HT.HTMLTool(file,distinationDir.getText(), SelectedExt);}
			else if(Extension.equalsIgnoreCase(".png")){PNGTool PNGT = new PNGTool();PNGT.PngConverter(file,distinationDir.getText(), SelectedExt);}
			else if(Extension.equalsIgnoreCase(".jpg")){JPGTool JPGT = new JPGTool();JPGT.JpgConverter(file,distinationDir.getText(), SelectedExt);}
			else if(Extension.equalsIgnoreCase(".svg")){SVGTool SVG = new SVGTool();SVG.SVGConverter(file,distinationDir.getText(),SelectedExt);}
			else if(Extension.equalsIgnoreCase(".JPEG")){JPEGTool JPEG = new JPEGTool();JPEG.JpegConverter(file,distinationDir.getText(), SelectedExt);}
			else if(Extension.equalsIgnoreCase(".gif")){GIFTool GIF = new GIFTool();GIF.GifConverter(file,distinationDir.getText() , SelectedExt);}
			else if(Extension.equalsIgnoreCase(".bmp")){BMPTool BMP = new BMPTool();BMP.BMPConverter(file,distinationDir.getText(),SelectedExt);}
			else if(Extension.equalsIgnoreCase(".tiff")){TIFFTool TIFF = new TIFFTool();TIFF.TiffConverter(file,distinationDir.getText(), SelectedExt);}
			else if(Extension.equalsIgnoreCase(".tif")){TIFTool TIF = new TIFTool();TIF.TifConverter(file, distinationDir.getText(), SelectedExt);}
			else if(Extension.equalsIgnoreCase(".xls")){XLSTOOL xls = new XLSTOOL();xls.XlsxConverter(file, distinationDir.getText(), SelectedExt);}
			else if(Extension.equalsIgnoreCase(".ico")){ICOTool ico = new ICOTool();ico.IcoConverter(file, distinationDir.getText(), SelectedExt);}
			else if(Extension.equalsIgnoreCase(".ppt")){PPTTool ppt = new PPTTool();ppt.ConvertThisPptxTo(file,distinationDir.getText(), SelectedExt);}
			else if(Extension.equalsIgnoreCase(".pdf")){PDFTool pdf = new PDFTool();pdf.ConvertThisPdf(file, distinationDir.getText(),SelectedExt);}
			}};
		  
		  Thread ConvertStatus = new Thread(){
			  public void run(){
			  try{
				  
			  while(!String.valueOf(ConvertThread.getState()).equals("TERMINATED")){
				  Converting.setText("Converting");
				  sleep(300);
				  Converting.setText("Converting.");
				  sleep(300);
				  Converting.setText("Converting..");
				  sleep(300);
				  Converting.setText("Converting...");
				  sleep(300);
				  if(String.valueOf(ConvertThread.getState()).equals("TERMINATED")){
					 File original = new File(file);
					  File verify = new File(distinationDir.getText()+"/"+original.getName().replace(new GetFileExtension().getExtension(file),SelectedExt));
					  if(verify.exists() && verify.length() >0){
						  Converting.setText("Converting.OK");
						  ok3.setVisible(true);
						  loadingLabel1.setVisible(true);
						  loadingLabel1.setIcon(new ImageIcon("file/okpic.jpg"));
						  openFile.setEnabled(true);
						  new ErrorClass(null, "done !", true,"Done !","");
						  Converting.setText("");
						  Configuration.setText("");
						  Status.setText("");ok1.setVisible(false); ok2.setVisible(false); ok3.setVisible(false);
						  loadingLabel1.setVisible(false);
						  sleep(200);
						  
						  loadingLabel.setVisible(true);
						  sleep(200);
						  twitter.setVisible(true);
						  sleep(200);
						  Gmail.setVisible(true); 
						 
						 
					  }else{
						  if(SelectedExt.equalsIgnoreCase(".png") || SelectedExt.equalsIgnoreCase(".jpg") || SelectedExt.equalsIgnoreCase(".gif") || SelectedExt.equalsIgnoreCase(".tif")|| SelectedExt.equalsIgnoreCase(".tiff")|| SelectedExt.equalsIgnoreCase(".jpeg") ){
							  File f= new File(file);
							  
							  if(new GetFileExtension().getExtension(file).equalsIgnoreCase(".pdf") && new File(distinationDir.getText()+"/"+f.getName()+"Images").exists() && new File(distinationDir.getText()+"/"+f.getName()+"Images").length()>1024){
								  
								  Converting.setText("Converting.OK");
								  ok3.setVisible(true);
								  loadingLabel1.setVisible(true);
								  loadingLabel1.setIcon(new ImageIcon("file/okpic.jpg"));
								  openFile.setEnabled(true); 
								  new ErrorClass(null, "done !", true,"Done !","");
								  Converting.setText("");
								  Configuration.setText("");
								  Status.setText("");ok1.setVisible(false); ok2.setVisible(false); ok3.setVisible(false);
								  loadingLabel1.setVisible(false);
								  sleep(200);
								 
								  loadingLabel.setVisible(true);
								  sleep(200);
								  twitter.setVisible(true);
								  sleep(200);
								  Gmail.setVisible(true); 
							  }else if( new GetFileExtension().getExtension(file).equalsIgnoreCase(".ppt")   && new File(distinationDir.getText()+"/"+f.getName().replace(new GetFileExtension().getExtension(f.getName()),"")).exists() && new File(distinationDir.getText()+"/"+f.getName().replace(new GetFileExtension().getExtension(f.getName()),"")).length()>1024){
								  
								  Converting.setText("Converting.OK");
								  ok3.setVisible(true);
								  loadingLabel1.setVisible(true);
								  loadingLabel1.setIcon(new ImageIcon("file/okpic.jpg"));
								  openFile.setEnabled(true); 
								  new ErrorClass(null, "done !", true,"Done !","try to convert an other file");
								  Converting.setText("");
								  Configuration.setText("");
								  Status.setText("");ok1.setVisible(false); ok2.setVisible(false); ok3.setVisible(false);
								  loadingLabel1.setVisible(false);
								  sleep(200);
								  
								  loadingLabel.setVisible(true);
								  sleep(200);
								  twitter.setVisible(true);
								  sleep(200);
								  Gmail.setVisible(true); 
							  }else if( new GetFileExtension().getExtension(file).equalsIgnoreCase(".pptx")   && new File(distinationDir.getText()+"/"+f.getName().replace(new GetFileExtension().getExtension(f.getName()),"")).exists() && new File(distinationDir.getText()+"/"+f.getName().replace(new GetFileExtension().getExtension(f.getName()),"")).length()>1024){
									  
								  Converting.setText("Converting.OK");
								  ok3.setVisible(true);
								  loadingLabel1.setVisible(true);
								  loadingLabel1.setIcon(new ImageIcon("file/okpic.jpg"));
								  openFile.setEnabled(true);
								  new ErrorClass(null, "done !", true,"Done !","");
								  Converting.setText("");
								  Configuration.setText("");
								  Status.setText("");
								  ok1.setVisible(false); ok2.setVisible(false); ok3.setVisible(false);
								  loadingLabel1.setVisible(false);
								  sleep(200);
								  
								  loadingLabel.setVisible(true);
								  sleep(200);
								  twitter.setVisible(true);
								  sleep(200);
								  Gmail.setVisible(true); 
								  
							  }
							  
							  
							  else if(new GetFileExtension().getExtension(file).equalsIgnoreCase(".txt") && SelectedExt.equalsIgnoreCase(".jpg")){
								  Converting.setText("Converting.OK");
								  ok3.setVisible(true);
								  loadingLabel1.setVisible(true);
								  loadingLabel1.setIcon(new ImageIcon("file/okpic.jpg"));
								  openFile.setEnabled(true);  
								  new ErrorClass(null, "done !", true,"Done !","try to convert an other file");
								  Converting.setText("");
								  Configuration.setText("");
								  Status.setText("");ok1.setVisible(false); ok2.setVisible(false); ok3.setVisible(false);
								  loadingLabel1.setVisible(false);
								  sleep(200);
								  
								  loadingLabel.setVisible(true);
								  sleep(200);
								  twitter.setVisible(true);
								  sleep(200);
								  Gmail.setVisible(true); 
							  }
						  }else{
						  Converting.setText("Error");
						  ok3.setIcon(new ImageIcon("file/no.png"));
						  ok3.setVisible(true);
						  loadingLabel1.setIcon(new ImageIcon("file/no.png"));	
						  
						  new ErrorClass(null, "Not done!", true,"Not done !","");
						  Converting.setText("");
						  Configuration.setText("");
						  Status.setText("");ok1.setVisible(false); ok2.setVisible(false); ok3.setVisible(false);
						  loadingLabel1.setVisible(false);
						  sleep(200);
						  
						  loadingLabel.setVisible(true);
						  sleep(200);
						  twitter.setVisible(true);
						  sleep(200);
						  Gmail.setVisible(true); 
						  
						  }
						  
					  }
					 
					  
					  
					  
					 
				  }
			  }
			  }catch(Exception e){
				  
			  }
			  }
		  };
	  Thread getFile = new Thread(){
			
			 public void run(){
				 
				 try {
					
					 loadingLabel1.setVisible(true);
					 twitter.setVisible(false);
					  Gmail.setVisible(false);
					 Configuration.setText("");
					 String str="Getting File";
					
					 Status.setText(str);
					sleep(100);
					Status.setText(str+".");
					sleep(100);
					Status.setText(str+"..");
					sleep(100);
					Status.setText(str+"...");
					sleep(100);
					if(file != null){
					File VerifyFile = new File(file);
					 
					 if(VerifyFile.exists()){
						 
						ImageIcon ok = new ImageIcon("file/ok.png");
						ok1.setVisible(true);
						  ok1.setIcon(ok);
						  ok1.setBounds(481, 278,20,20);
						  Status.setText("Getting File.OK");
						 
			 				Configuration.setText("Configurations");
			 				sleep(100);
			 				Configuration.setText("Configurations.");
			 				sleep(100);
			 				Configuration.setText("Configurations..");
			 				sleep(100);
			 				Configuration.setText("Configurations...");
			 				sleep(100);
			 				if(SelectedExt==null){
			 					 new ErrorClass(null, "Error",true,"Please Choose an","available Format");
			 					Converting.setText("");
								  Configuration.setText("");
								  Status.setText("");ok1.setVisible(false); ok2.setVisible(false); ok3.setVisible(false);
								  loadingLabel1.setVisible(false);
								  sleep(200);
								  
								  loadingLabel.setVisible(true);
								  sleep(200);
								  twitter.setVisible(true);
								  sleep(200);
								  Gmail.setVisible(true); 
			 					 
			 				}else{
			 				Configuration.setText("Configurations.OK");
							ok2.setVisible(true);
							  ok2.setIcon(ok);
							  ok2.setBounds(481, 300,20,20);
							  ConvertStatus.start();
								ConvertThread.start();	}
					 }
					 }else  if(file==null){
				
						  new ErrorClass(null, "Error",true,"file not found","");
						  Converting.setText("");
						  Configuration.setText("");
						  Status.setText("");ok1.setVisible(false); ok2.setVisible(false); ok3.setVisible(false);
						  loadingLabel1.setVisible(false);
						  sleep(200);
						  
						  loadingLabel.setVisible(true);
						  sleep(200);
						  twitter.setVisible(true);
						  sleep(200);
						  Gmail.setVisible(true); 
					  }
				} catch (Exception e) {
					
				}	
			 }
		 };
	  	getFile.start();

	  }
	 
	}

	
}

