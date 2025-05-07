package server;
import java.awt.BorderLayout;
import java.awt.EventQueue;
//import java.io.File;

//import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import javax.swing.filechooser.FileSystemView;

/**
 * Classe che estende la classe madre JFrame.
 * Crea un file chooser usato per visualizzare i file disponibili.
 * 
 * @author Michele Metta
 *
 */

public class FileNameOpener extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileNameOpener frame = new FileNameOpener();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileNameOpener() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public String fileNameAsker() {
		
		String fileName = JOptionPane.showInputDialog("Inserisci il nome del file di cluster da aprire: ");
		return fileName;

	
}
}
