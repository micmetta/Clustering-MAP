package data;


/**
 * Classe che modella un'eccezione controllata da considerare qualora il numero di cluster 
 * inserito fosse maggiore rispetto al numero di centroidi generabili o minore di 0.
 * 
 * @author Michele Metta
 *
 */
public class OutOfRangeSampleSize extends Exception {
	
	private static final long serialVersionUID = 1L;
	public String error;
	
	public OutOfRangeSampleSize() {
		error = "ERRORE: Numero inserito non valido!";
	}
}
