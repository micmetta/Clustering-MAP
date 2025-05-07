package mining;
import java.io.Serializable;


import data.Data;
import data.OutOfRangeSampleSize;
import data.Tuple;

/**
 * Classe che rappresenta un insieme di cluster determinati dal k-means
 * 
 * @author Michele Metta
 *
 */

public class ClusterSet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	Cluster C[];
	int i=0;
	
	public ClusterSet(int k){
		C = new Cluster[k];
	}
	
	void add(Cluster c) {
		this.C[i]=c;
		i++;
	}
	
	public Cluster get(int i) {
		return C[i];
	}
	
	/**
	 * Sceglie i centroidi, crea un cluster per ogni centroide e lo memorizza in C.
	 * 
	 * @param data La tabella rappresentata dalla classe {@link data.Data}
	 * @throws OutOfRangeSampleSize
	 */
	public void initializeCentroids(Data data) throws OutOfRangeSampleSize{
			
		int centroidIndexes[] = data.sampling(C.length); //il metodo sampling sceglie i centroidi
			
			for(int i=0;i<centroidIndexes.length;i++)
			{
				Tuple centroidI=data.getItemSet(centroidIndexes[i]);
				add(new Cluster(centroidI));
			}
	}
	
	/**
	 * Calcola la distanza tra la tupla riferita da tuple ed il centroide di ciascun 
	 * cluster in C e restituisce il cluster piu' vicino.
	 * 
	 * @param tuple Tupla da considerare.
	 * @return Cluster piu' vicino alla tupla passata come paramentro.
	 */
	Cluster nearestCluster(Tuple tuple) {
		
		Cluster nearest = C[0];
		double dist1=0.0;
		double dist2=0.0;
		
		for(int i=1; i<C.length; i++) {
			
			dist1=tuple.getDistance(nearest.getCentroid());
			dist2=tuple.getDistance(C[i].getCentroid());
			
			if(dist2<dist1) {
				nearest = C[i];
			}
		}
		
		return nearest;
	}
	
	/**
	 * Restituisce il cluster in cui si trova la tupla id-esima di data.
	 * Se questa non e' presente in nessun cluster allora viene restituito null.
	 * 
	 * @param id
	 * @return
	 */
	Cluster currentCluster(int id) {
		
		Cluster current = null;
		//double contain=0.0;
		
		for(int i = 0;i<C.length; i++) {
			
			if(C[i].contain(id)) {
				current = C[i];
			}
		}
		
		return current;
	}
	

	/**
	 * Calcola il nuovo centroide per ciascun cluster in C
	 * 
	 * @param data La tabella rappresentata dalla classe data.Data
	 */
	public void updateCentroids(Data data) {
		for(int i=0;i<C.length;i++) {
			C[i].computeCentroid(data);
		}
	}
	
	
	/**
	 * Il metodo si occupa di restituire una stringa formata dalla
	 * concatenzione delle diverse stringhe, ottenute richiamando il metodo
	 * Cluster.toString(Data) su ciascun Cluster appartenente al vettore dei cluster
	 * C. Quindi alla fine restituiro' ogni cluster con le proprie tuple.
	 * 
	 * @param data La tabella rappresentata dalla classe data.Data
	 * 
	 */
	public String toString(Data data) {
		String str="";
		for(int i=0;i<C.length;i++){
			if (C[i]!=null){
				str+=i+":"+C[i].toString(data)+"\n";		
			}
		}
		return str;		
	}

}
