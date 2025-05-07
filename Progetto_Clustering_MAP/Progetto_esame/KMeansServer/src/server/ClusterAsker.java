package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Classe che estende la classe madre JFrame.
 * Genera un dialog utile al programma per capire quanti cluster generare a partire dalla base di dati.
 * 
 * @author Michele Metta
 *
 */
public class ClusterAsker extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClusterAsker frame = new ClusterAsker();
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
	public ClusterAsker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	
	public int Asking() {
		
		String ritorno = JOptionPane.showInputDialog("Inserisci il numero di cluster da generare: ");
		return Integer.parseInt(ritorno);
	}

}
