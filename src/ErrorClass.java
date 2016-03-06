import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorClass extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8328250573700631359L;
	JPanel QPanel=new JPanel();
	ImageIcon Background = new ImageIcon("file/inf.ter");
	JButton OK = new JButton();
	JLabel BackgroundLabel = new JLabel();
	JLabel QuiText = new JLabel();
	JLabel QuiText1 = new JLabel();
	ImageIcon OKIcon = new ImageIcon("file/OKIcon.ter");
	

	public  ErrorClass(JFrame parent,String title,boolean modal,String str1,String str2){

		super(parent,title,modal);
		this.setSize(210,160);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent(str1,str2);
		ImageIcon imgicon = new ImageIcon("file/inf.ter");
	    setIconImage(imgicon.getImage());
		this.setVisible(true);
		
	}

	public void initComponent(String labelText1,String labelText2){
		QPanel.setBounds(0,0,200,150);
		QPanel.setLayout(null);
		QuiText.setText(labelText1);
		QuiText1.setText(labelText2);
		
		QuiText.setForeground(Color.white);
		QuiText.setBounds(60,30,190,25);
		QPanel.add(QuiText);
		QuiText1.setForeground(Color.white);
		QuiText1.setBounds(60,50,90,25);
		QPanel.add(QuiText1);
		QPanel.add(OK);
		OK.setIcon(OKIcon);
		OK.setBounds(70,80,56,23);
		OK.setCursor(new Cursor(Cursor.HAND_CURSOR));
		BackgroundLabel.setIcon(Background);
		BackgroundLabel.setBounds(0,10,80,80);
		QPanel.add(BackgroundLabel);
		QPanel.setBackground(Color.gray);
		this.getContentPane().add(QPanel);
		OK.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				
			}
			
		});
		
}
}
