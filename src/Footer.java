import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Footer {
public void MakeFooter(JPanel Panel){
	JLabel pdf = new JLabel();pdf.setIcon(new ImageIcon("file/pdf.png"));pdf.setBounds(15,100,90,90);Panel.add(pdf);
	JLabel pdftool = new JLabel("PDF Tool");pdftool.setBounds(27,182,80,20);pdftool.setForeground(Color.white);Panel.add(pdftool);
	JLabel Images = new JLabel();Images.setIcon(new ImageIcon("file/Images.png"));Images.setBounds(15,200,90,90);Panel.add(Images);
	JLabel imgtool = new JLabel("Image Tool");imgtool.setBounds(27,282,80,20);imgtool.setForeground(Color.white);Panel.add(imgtool);
	JLabel Download = new JLabel();Download.setIcon(new ImageIcon("file/Download.png"));Download.setBounds(15,300,90,90);Panel.add(Download);
	JLabel dtool = new JLabel("Download");dtool.setBounds(27,382,80,20);dtool.setForeground(Color.white);Panel.add(dtool);
	pdf.addMouseListener(new MouseListener(){
		
	
		public void mouseClicked(MouseEvent arg0) {
			pdf.setBounds(5,100,90,90);
			pdf.setIcon(new ImageIcon("file/SelectedPdf.png"));
			@SuppressWarnings("unused")
			PDFOptions pdf = new PDFOptions(null,"PDF Tool",true);
			
		}

		
		public void mouseEntered(MouseEvent arg0) {
			//if(pdflis==false){
			pdf.setBounds(5,100,90,90);
		pdf.setIcon(new ImageIcon("file/rect.png"));}
		
		//}

		
		public void mouseExited(MouseEvent arg0) {
			//if(pdflis==false){
			pdf.setBounds(15,100,90,90);
			pdf.setIcon(new ImageIcon("file/pdf.png"));
			}
		//}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
			
		}
		
		
		
	});
	Images.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			Images.setBounds(15,200,90,90);
			Images.setIcon(new ImageIcon("file/Images.png"));
		}
		public void mouseEntered(MouseEvent e) {
			Images.setBounds(5,200,90,90);
			Images.setIcon(new ImageIcon("file/ImageEnt.png"));
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Images.setBounds(5,200,90,90);
			Images.setIcon(new ImageIcon("file/ImageClicked.png"));
			@SuppressWarnings("unused")
			ImageDialog ig = new ImageDialog(null,"Images",true);
			
		}
	});
	Download.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			Download.setBounds(15,300,90,90);
			Download.setIcon(new ImageIcon("file/Download.png"));
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			
			Download.setBounds(5,300,90,90);
			Download.setIcon(new ImageIcon("file/DonZone.png"));
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Download.setBounds(5,300,90,90);
			Download.setIcon(new ImageIcon("file/DownSelect.png"));
			@SuppressWarnings("unused")
			DownloadDialog manager = new DownloadDialog(null,"File Downloader",true);
		}
	});
	JLabel footer = new JLabel();
	footer.setBounds(0,25,100,450);
	footer.setIcon(new ImageIcon("file/12.PNG"));
	Panel.add(footer);
}
}
