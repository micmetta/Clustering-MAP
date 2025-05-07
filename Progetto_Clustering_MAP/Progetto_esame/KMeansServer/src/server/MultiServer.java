package server;
import java.net.*;
import java.io.*;


/**
 * Questa classe nota come MultiServer viene impiegata per creare un Server 
 * in grado di gestire le richieste del client.
 * Per ogni richiesta viene creato un thread a parte. Sara' tale thread ad ascoltare
 * le richieste per un determinato client.
 * 
 * @author Michele Metta
 *
 */
public class MultiServer {

	private int PORT = 9090;
	
	public static void main(String[] args) throws IOException {
		
		MultiServer Server = new MultiServer(9090);//Serve per chiamare il costruttore che a sua volta invoca il 
		//metodo run
		
	}
	
	/**
	 * Metodo costruttore che inizializza la porta e chiama il metodo run.
	 * 
	 * @param port
	 * @throws IOException
	 */
	public MultiServer(int port) throws IOException {
		port = this.PORT;
		this.run();
		
	}
	
	/**
	 * Istanzia un oggetto istanza della classe ServerSocket che si occupera' di gestire le
	 * richieste da parte del client. Ad ogni nuova richiesta di connessione si istanzia ServerOneClient. 
	 */
	private void run() throws IOException {
		
		//Socket lato server
		ServerSocket ss = new ServerSocket(this.PORT);//istanzio l'oggetto della classe ServerSocket in modo tale
		//che il server possa rimanere in ascolto fino a quando un client fa richiesta.
		//Quando verrà effettuata richiesta allora il Server e il Client si metterano in comunicazione tramite un
		//socket comune dove le due parti potranno comunicare attraverso gli stream di input e di output.
		//(Questo avviene in ServerOneClient)
		
		Thread thread = new Thread(new ServerOneClient(ss));
		thread.start();//Viene fatto partire il thread che gestirà client che ha fatto richiesta.
		
		
	}
	
}
