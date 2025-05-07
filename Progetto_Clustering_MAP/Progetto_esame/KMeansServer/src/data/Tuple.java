package data;

import java.io.Serializable;

/**
 * Classe che rappresenta una tupla come sequenza di coppie attributo-valore.
 *
 * @author Michele Metta
 *
 */
public class Tuple implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	/**
	 * Array di item che rappresenta la sequenza di coppie attributo-valore
	 * caratterizzanti la tupla.
	 */
	Item[] tuple;
	

	/**
	 * Costruttore che inizializza la dimensione del vettore tuple.
	 * 
	 * @param size
	 */
	public Tuple(int size){		//numero di item che costituirà la tupla
		
		tuple=new Item[size];
	}
	
	/**
	 * 
	 * 
	 * @return Valore intero rappresentante la dimensione del vettore tuple.
	 */
	public int getLength() {
		return tuple.length;
	}
	
	/**
	 * 
	 * 
	 * @param i
	 * @return L'oggetto di tipo item presente nell'i-esima cella del vettore tuple.
	 */
	public Item get(int i) {
		return tuple[i];
	}
	
	void add(Item c,int i) {
		tuple[i] = c;
	}
	

	/**
	 * Determina la distanza tra la tupla riferita da obj e la tupla corrente (riferita da this).
	 * La distanza e' ottenuta come la somma delle distanze tra gli item in posizioni eguali nelle due 
	 * tuple.
	 * 
	 * @param obj Parametro rappresentante la tupla per cui &egrave necessario
	 * effettuare il calcolo rispetto alla tupla corrente
	 * 
	 * @return Un valore di tipo double rapprensentante la distanza tra le due tuple
	 */
	public double getDistance(Tuple obj) {
		
		double distance = 0.0;
		
		for(int i=0;i<tuple.length;i++) {
			distance=distance + tuple[i].distance(obj.get(i));
		}
		return distance;
	}
	
	
	/**
	 * Restituisce la media delle distanze tra la tupla corrente e quelle ottenibili dalle righe 
	 * della tabella riferita da data aventi indice in clusteredData.
	 * 
	 * @param data Oggetto istanza della classe Data di cui e' necessario 
	 * considerare alcune tuple.
	 * 
	 * @param clusteredData Insieme degli indici delle righe di data prese in considerazione. 
	 * 
	 * @return Un valore di tipo double contenente il calcolo della media.
	 */
	public double avgDistance(Data data, int clusteredData[]) {
		
		double p=0.0; 
		double sumD=0.0;
		
		for(int i=0; i<clusteredData.length; i++) {
			
			double d= getDistance(data.getItemSet(clusteredData[i]));
			sumD+=d;
		}
		
		p=sumD/clusteredData.length;
		return p;
	}
	
}
