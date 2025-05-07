package mining;
import java.io.Serializable;

import data.Data;
import data.Tuple;
import utility.ArraySet;


/**
 * La classe si occupa di modellare un singolo Cluster.
 * Ciascun cluster e' rappresentato da una tupla (Centroide) e da un insieme
 * di interi rappresentanti le righe della tabella data.Data appartenenti al cluster.
 * Il centroide e' modellato usando la classe data.Tuple, mentre l'insieme
 * delle tuple appartenenti al cluster e' modellato usando il tipo ArraySet.
 * 
 * @author Michele Metta
 */
public class Cluster implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Tuple centroid;

	private ArraySet clusteredData; 

	Cluster(Tuple centroid){
		this.centroid=centroid;
		clusteredData=new ArraySet();
		
	}
		
	Tuple getCentroid(){
		return centroid;
	}
	
	/**
	 * Calcola il centroide per questo cluster utilizzando il metodo
	 * data.Item.update(Data, Set).
	 * data e' la tabella data.Data su cui effettuare il calcolo.
	 */
	void computeCentroid(Data data){
		for(int i=0;i<centroid.getLength();i++){
			centroid.get(i).update(data,clusteredData);
			
		}
		
	}
	//return true if the tuple is changing cluster
	boolean addData(int id){
		return clusteredData.add(id);
		
	}
	
	//verifica se una transazione è clusterizzata nell'array corrente
	boolean contain(int id){
		return clusteredData.get(id);
	}
	

	//remove the tuplethat has changed the cluster
	void removeTuple(int id){
		clusteredData.delete(id);
		
	}
	
	/**
	 * Il metodo si occupa di creare (e ritornare) una stringa contenente il centroide 
	 * di questo cluster.
	 */
	public String toString(){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i);
		str+=")";
		return str;
		
	}
	

	/**
	 * Il metodo si occupa di creare una stringa contenente il centroide di questo cluster.
	 * E per questo cluster si occupa inoltre di mostrare tutte le tuple in esso clusterizzate e
	 * la distanza massima.
	 * Cio' avviene utilizzando il riferimento passato come parametro.
	 * 
	 * @param data La tabella data.Data su cui vengono ottenute le tuple clusterizzate
	 * in questo cluster.
	 */
	public String toString(Data data){
		
		String str="Centroid=(";
		
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i)+ " ";
		str+=")\nExamples:\n";
		
		int array[]=clusteredData.toArray();
		for(int i=0;i<array.length;i++){
			str+="[";
			
			for(int j=0;j<data.getNumberOfExplanatoryAttributes();j++)
				str+=data.getAttributeValue(array[i], j)+" ";									//prima c'era getValue(...)
			str+="] dist="+getCentroid().getDistance(data.getItemSet(array[i]))+"\n";
			
		}
		
		str+="\nAvgDistance="+getCentroid().avgDistance(data, array);
		return str;
		
	}

}
