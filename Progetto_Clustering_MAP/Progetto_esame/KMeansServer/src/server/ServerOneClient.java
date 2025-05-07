package server;
import java.net.*;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import java.nio.charset.MalformedInputException;
import data.Data;
import data.OutOfRangeSampleSize;
import database.EmptySetException;
import mining.KMeansMiner;

import java.io.*;

/**
* ServerOneClient implementa l'interfaccia Runnable,
* la quale la rende in grado di poter essere eseguita essa stessa come un thread,
* in questa vengono gestite la richiesta di lettura e scoperta di cluster.
* 
* 
* La classe modella un Thread in grado di ascoltare le richieste da un singolo client.
* E' istanziata da MultiServer non appena si verifica un connessione..
* La classe si occupa di effettuare particolari operazioni in base alle richieste da parte del client.
* La gestione delle richieste è effettuata nel metodo run.
* 
* @author Michele Metta
*/

public class ServerOneClient implements Runnable {
	
	Scanner tastiera = new Scanner(System.in);
	private Socket socket;
 	private ObjectInputStream in;
	private ObjectOutputStream out;
	private KMeansMiner kmeans;
	private ServerSocket s;

/**
* Costruttore di classe. Inizializza l'attributo socket.
* Solleva un'eccezione di tipo IOException nel caso in cui risultano esserci dei problemi
* nella creazione del socket.
*/
	public ServerOneClient(ServerSocket ss) throws IOException {
	
		socket = new Socket();//istanzio l'oggetto socket che verrà utilizzato quando il client effettuerà la richiesta
		//e il metodo accept restituirà un oggetto di tipo socket dal quale poter memorizzare gli stream di input e 
		//di output per effettuare la comunicazione con il client.
		System.out.println("Aspettando la connessione del client..");
		this.s = ss;
		
		
	}

/**
* Sovrascrive il metodo run della classe Thread.
* Il metodo run si occupa di gestire le richieste da parte del client che sono suddivise in
* due categorie:
* - Una di scoperta dei cluster sul database.
* - Una di lettura di un risultato precedente di scoperta da file.
* Nel primo caso il client invia il valore 1.
* Una volta letto tale valore il metodo effettuerà la vera e propria attività di scoperta che avviene tramite il 
* metodo mining.Kmeans.kmeans(Data) chiamata in questo caso.
* Il metodo letto tale comando effettuera' la creazione dell'oggetto data.Data. 
* Nel caso in cui il client invia il valore 2 invece, viene effettuata la lettura del file nel quale sono
* presenti i cluster da leggere e dopodiche' sara' possibile tramite il tasto Carica Cluster caricare i cluster
* che vi sono memorizzati all'interno della text area situata nella parte destra dell'interfaccia. 
* 
*/
	public void run() {
		
		
		try {
			
			this.socket = this.s.accept();//l'oggetto di tipo ServerSocket passato dalla classe Multiserver
			//si mette in ascolto sulla porta 9090 e appena viene fatta richiesta restituisce l'oggetto di tipo
			//socket.
		
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} 

		BufferedReader input = null;

		try {
			
			PrintWriter output = new PrintWriter(this.socket.getOutputStream(),true);//memorizzo lo stream di uscita
			//del socket che è in comune tra server e client
			input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));//memorizzo lo stream
			//di input dello stesso socket
			int valoreDecisione = input.read();
	
				
				if(valoreDecisione==1) {
				
				//Se il valore di decisione è uguale a 1 allora vuol dire che l'utente ha premuto 
                //sull'interfaccia grafica il pulsante scoperta.					
					
				ClusterAsker asker = new ClusterAsker();//viene creata l'istanza della classe ClusterAsker affinchè
				//si possa far appararire la schermata dove viene chiesto il numero di cluster da generare 
				int nCluster = asker.Asking();//qui viene chiamato il metodo per la schermata detta pocanzi
				TableAsker tableAsk = new TableAsker();//viene creata l'istanza della classe TableAsker
				String table = tableAsk.Asking();//viene invocato il metodo Asking di tableAsk che fara' apparire
				//la finestra dove sara' chiesto il nome del database che dovra' essere utilizzato per prelevare
				//la tabella su cui applicare l'algoritmo k-means
				FileAsker fileAsk = new FileAsker();//viene istanziato l'oggetto della classe FileAsker
				String nomeFile = fileAsk.Asking();//verrà fatta apparire la finestra dove si chiede il nome del
				//file su cui salvare i cluster generati. 
				
				
				kmeans = new KMeansMiner(nCluster);//creazione istanza di KMeansMiner a cui viene passato il numero
				//di cluster da generare
				Data dati = new Data(table);//creazione dell'istanza di Data passando come parametro il nome della
				//tabella presente sul database
				int nIterazioni = kmeans.kmeans(dati);//viene applicato l'algoritmo k-means sulla tabella dati
				//ottenuta prima
				
				//serializzazione in un file
				FileOutputStream fileOutput = new FileOutputStream(nomeFile+".dat");
				this.out = new ObjectOutputStream(fileOutput);//viene creato lo stream di uscita per poter memorizzare
				//le informazioni sul file creato precedentemente
				out.writeObject(dati);//memorizzazione della tabella sul file
				out.writeObject(kmeans);//memorizzazione dei cluster generati dal k-means sul file.
				out.close();
			
			}
			
			if(valoreDecisione==2) {
				
				//FileNameOpener fnOpener = new FileNameOpener();
				//String lookupName = fnOpener.fileNameAsker();
				
				JFileChooser jfc = new JFileChooser();//creo l'istanza di JFileChooser per poter aprire la finestra
				//in cui l'utente sceglie da quale file presente sul file system prelevare i cluster.
				int n = jfc.showOpenDialog(jfc);//il metodo showOpenDialog restituisce un valore intero in base a quale
				//pulsante della finestra è stato premuto (apri, annulla o cancella).
				File file = jfc.getSelectedFile();//leggo il nome del file da cui prelevare i dati
				String lookupName = file.getName();//memorizzo il nome del file
				FileInputStream fileInput = new FileInputStream(lookupName);
				this.in = new ObjectInputStream(fileInput);//creo lo stream di lettura per leggere il file selezionato
				KMeansMiner read;
				Data readData;
				readData = (Data) this.in.readObject();//leggo l'oggetto data e lo memorizzo in una variabile
				read = (KMeansMiner) this.in.readObject();//leggo l'oggetto k-means e lo memorizzo in un'altra variabile
				//di quel tipo
				ObjectOutputStream trasferimento = new ObjectOutputStream(this.socket.getOutputStream());//viene memorizzato
				//lo stream di uscita del socket che verrà utilizzato per trasferire informazioni al client
				trasferimento.writeObject(readData);
				trasferimento.writeObject(read);
				this.in.close();
			}
			
	
			
			String terminato = "Programma lato-Server terminato!";
			output.print(terminato);
			output.flush();
			output.close();
			input.close();
			
				} catch (MalformedInputException ex) {
					ex.printStackTrace();
					System.out.println("skipping binary file");//errore che può essere sollevato quando istanzio new bufferedreader
			
				} catch (IOException | SQLException | EmptySetException | OutOfRangeSampleSize e) {
			
			e.printStackTrace();
			System.out.println("Errore di I/O");
		} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		
	}
	
	
}
