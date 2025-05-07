import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * Crea un area di testo su cui può essere effettuato lo "scrolling".
 * 
 * @author Michele Metta
 *
 */

public class ScrollablePane extends JTextPane {

	public ScrollablePane() {
		super();
		this.setEditable(false);
		this.setBounds(335, 36, 511, 297);
		this.setVisible(true);
		
	}
	
}
