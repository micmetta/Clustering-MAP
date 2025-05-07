package mining;
import java.io.Serializable;

import data.Data;
import data.OutOfRangeSampleSize;


/**
 * Classe che include l'implementazione dell'algoritmo k-means.
 * 
 * @author Michele Metta
 *
*/
public class KMeansMiner implements Serializable {
	
	private static final long serialVersionUID = 1L;
	ClusterSet C;
	
	public KMeansMiner(int k){
		C = new ClusterSet(k);
	}
	
	public ClusterSet getC() {
		return C;
	}
	
	public int kmeans(Data data) throws OutOfRangeSampleSize{
		
		int numberOfIterations=0;
		//STEP 1
		C.initializeCentroids(data); //inizializzo i centroidi
		boolean changedCluster=false;
		
		do{
			numberOfIterations++;
			//STEP 2
			changedCluster=false;
			
			for(int i=0;i<data.getNumberOfExamples();i++){
				
				Cluster nearestCluster = C.nearestCluster(data.getItemSet(i));//vedo qual è il cluster piu' vicino
				//alla tupla che sto considerando in un determinato momento.
				
				Cluster oldCluster = C.currentCluster(i);//vedo qual è il cluster in cui la tupla si trova 
				//in questo istante
				
				boolean currentChange = nearestCluster.addData(i);//aggiungo la tupla al cluster che è ad essa piu'
				//vicino. il metodo add restituisce true se la tupla non era già presente in questo cluster
				//altrimenti restituisce false.
				
				if(currentChange)
					changedCluster=true;
				//rimuovo la tupla dal vecchio cluster
				if(currentChange && oldCluster!=null)
					//il nodo va rimosso dal suo vecchio cluster
					oldCluster.removeTuple(i);
					
			}			
			//STEP 3
			C.updateCentroids(data);//Ricalcolo i centroidi del cluster.
		}
		while(changedCluster);
		
		return numberOfIterations;//restituisco il numero di iterazioni effettuate.

	}

}
