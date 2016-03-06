
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;

public class GoTo {
JLabel label;
String link;
public GoTo(JLabel label, String link) {
	super();
	this.label = label;
	this.link = link;
}
public GoTo() {
	// TODO Auto-generated constructor stub
}
public void Go(JLabel label,String link){
	
    label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    label.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
                try {
                        Desktop.getDesktop().browse(new URI(link));
                } catch (URISyntaxException | IOException ex) {  
                        
                }
        }
    });
}

	
}
