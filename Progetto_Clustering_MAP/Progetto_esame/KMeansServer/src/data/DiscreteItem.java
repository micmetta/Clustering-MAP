package data;

import java.io.Serializable;


/**
 * La classe estende la classe madre Item e rappresenta una coppia attributo discreto-valore discreto.
 * 
 * @author Michele Metta
 *
 */
public class DiscreteItem extends Item implements Serializable{
	

	private static final long serialVersionUID = 1L;

	public DiscreteItem(DiscreteAttribute attribute,String value){
		super(attribute, value);
	}
	
	public double distance(Object a) {
		if (this.getValue().equals(a.toString())) {		
			return 0.0;
		}else 
			return 1.0;
	}

}
