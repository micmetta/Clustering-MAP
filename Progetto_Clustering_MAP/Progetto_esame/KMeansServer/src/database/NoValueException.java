package database;

/**
 * Classe che estende Exception per modellare l'assenza di un valore all'interno di un resultset.
 * 
 * @author Michele Metta
 */
public class NoValueException extends Exception {
		
	private static final long serialVersionUID = 1L;
	String messaggio;
	
	public NoValueException() {
		messaggio = "Nessun contenuto";
	}
	
}
