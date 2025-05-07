package data;

import java.io.Serializable;

/**
 * Classe astratta che modella l'entità attributo.
 * 
 * @author Michele Metta
 *
 */
public abstract class Attribute implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String name; //NOME SIMBOLICO DELL'ATTRIBUTO
	protected int index;  //IDENTIFICATIVO NUMERICO DELL'ATTRIBUTO
	
	
	//METODO COSTRUTTORE CHE SARÀ RICHIAMATO DALLE CLASSI CHE ESTENDONO TALE 
	//CLASSE ASTRATTA
	Attribute(String name, int index) {
		this.name= name;		
		this.index= index;
	}
	
	//METODO CHE RESTITUISCE IL NOME ASSOCIATO ALL'ATTRIBUTO
	String getName() {
		return name;
	}
	
	//METODO CHE RESTITUISCE L'INDICE DELL'ATTRIBUTO
	int getIndex() {
		return index;
	}
	
	
	public String toString() {
		return name;
	}

}
