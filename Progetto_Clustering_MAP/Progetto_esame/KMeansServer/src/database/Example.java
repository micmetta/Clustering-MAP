package database;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe che modella una transazione letta dalla base di dati.
 * 
 * @author Michele Metta
 *
 */
public class Example implements Comparable<Example>{
	
	/**
	 * Attributo di classe che rappresenta la tupla letta dalla base di dati.
	 */
	private List<Object> example = new ArrayList<Object>();

	/**
	 * Aggiunge l'oggetto specificato come parametro alla lista example.
	 * 
	 * @param o Oggetto da aggiungere alla transazione
	 */
	public void add(Object o){
		example.add(o);
	}
	
	
	/**
	 * Restituisce l'oggetto presente nella tupla all'indice specificato come paramentro.
	 * L'indice i verra' utilizzato per considerare un elemento della transazione rappresentata 
	 * dalla lista example.
	 * 
	 * @return Un oggetto istanza della classe Object appartanente alla transazione
	 * letta dalla base di dati
	 */
	public Object get(int i){
		return example.get(i);
	}
	
	
	/**
	 * Metodo implementato dall'interfaccia Comparable.
	 * Grazie a tale metodo e' possibile confrontare due transazioni lette dal database.
	 */
	public int compareTo(Example ex) {
		
		int i=0;
		
		for(Object o:ex.example){
			
			if(!o.equals(this.example.get(i)))
				return ((Comparable<Object>)o).compareTo(example.get(i));
			i++;
		}
		return 0;
	}
	
	
	/**
	 * Metodo che crea una stringa equivalente al risultato della chiamata
	 * del metodo toString dei singoli oggetti appartenenti alla lista example.
	 */
	public String toString(){
		String str="";
		for(Object o:example)
			str+=o.toString()+ " ";
		return str;
	}
	
}