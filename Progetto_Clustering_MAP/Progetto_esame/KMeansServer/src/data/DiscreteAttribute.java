package data;
import java.io.Serializable;

import utility.ArraySet;


/**
 * La classe estende la classe madre Attribute e modella un attributo discreto.
 * 
 * @author Michele Metta
 *
 */
public class DiscreteAttribute extends Attribute implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String values[]; //ARRAY DI STRINGHE RAPPRESENTANTI I VALORI ASSUMIBILI DA UN ATTRIBUTO CATEGORICO
	
	/**
	 * Costruttore di DiscreteAttribute che chiama prima il costruttore della classe base e poi 
	 * ordina il vettore che contiene i possibili valori che l'attributo puo' assumere.
	 * 
	 * @param name
	 * @param index
	 * @param values
	 */
	//METODO COSTRUTTORE PER INIZIALIZZARE UN ATTRIBUTO DI TIPO DISCRETO
	public DiscreteAttribute(String name, int index, String values[]) {
		super (name, index);
		
		//ORDINAMENTO LESSICOGRAFICO DEI VALORI DELL'ATTRIBUTO 
		//TRAMITE ALGORITMO DI SELECTION SORT
		for (int i=0; i<values.length-1; i++) {
		     
			for (int j=i+1; j<values.length; j++) {
		        
				if (values[i].compareTo(values[j]) > 0) {
		           
					String temp = values[j]; values[j] = values[i]; values[i] = temp;
		        }
		     }
		  }
		this.values = new String[values.length];
		
		//INSERIMENTO DEI VALORI ORDINATI
		//NEL VETTORE VALUES DELL'OGGETTO ISTANZIATO ".THIS"
		for(int t=0;t<values.length;t++) {
			this.values[t] = values[t];
		}
		
	}

	/**
	 * 
	 * @return La dimensione del vettore che contiene i possibili valori che l'attributo può
	 * assumere.
	 */
	//METODO GETTER PER LA RESTITUZIONE DELLA DIMENSIONE DELL'ARRAY DI STRINGHE (VALORI ASSUMIBILI DALL'ATTRIBUTO)
	int getNumberOfDistinctValues() {
		return this.values.length;
	}
	
	//METODO GETTER CHE RESTITUISCE IL VALORE DELL'ARRAY DI STRINGHE ASSOCIATO ALL'INDICE I (PASSATO IN INPUT) 
	String getValue(int i) {
		return this.values[i];
	}
	
	/**
	 * Metodo che permette di calcolare l'occorrenza di un particolare valore di un attributo presente
	 * in Data.
	 * 
	 * @param data
	 * @param idList
	 * @param v
	 * @return
	 */
	//METODO CHE DETERMINA L'OCCORRENZA DI UN PARTICOLARE VALORE DI UN ATTRIBUTO PRESENTE IN DATA
	public int frequency(Data data,ArraySet idList,String v) {
		
		int occ=0;
		
		for(int j=0; j<getNumberOfDistinctValues();j++) {
			
			for(int i=0; i<data.getNumberOfExamples();i++) {
				
				if(idList.get(i)) {
					
					if(data.getAttributeValue(i, j).equals(v)) {
						occ=occ+1;
					}
				}
			}
		}
		return occ;
	}
}