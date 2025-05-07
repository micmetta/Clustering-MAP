package database;
import java.sql.*;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * La classe si occupa di ottenere in maniera appropriata la tuple dalla base di dati e renderle
 * disponibili al sistema.
 * In particolare le tuple vengono estratte singolarmente, quindi ci saranno tuple univoche nel
 * sistema, nonostante la base di dati possa possedere duplicati.
 * Lo schema originale della tabella (che quindi equivale a quello della base di dati)
 * e' ottenuto grazie alla classe database.TableSchema.
 * Il risultato fornito da questa classe viene trattato dai metodi qui presenti. In particolare 
 * grazie ai metodi:
 * TableData.getDistinctTransazioni(String), 
 * TableData.getDistinctColumnValues(String, TableSchema.Column) e 
 * TableData.getAggregateColumnValue(String, TableSchema.Column, QUERY_TYPE) lo schema
 * verra' utilizzato per portare a termine particolari operazioni.
 * 
 * @author Michele Metta
 * 
 */
public class TableData {

	DbAccess db;

	public TableData(DbAccess db) {
		this.db=db;
	}

	
	/**
	 * Ricava lo schema della tabella con nome table. Esegue una interrogazione per estrarre le 
	 * tuple distinte da tale tabella. Per ogni tupla del resultset, si crea un oggetto, istanza 
	 * della classe Example, il cui riferimento va incluso nella lista da restituire.
	 * In particolare, per la tupla corrente nel resultset, si estraggono i valori dei singoli 
	 * campi (usando getFloat() o getString()), e li si aggiungono all'oggetto istanza della classe 
	 * Example che si sta costruendo.
	 * 
	*/
	public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException, DatabaseConnectionException {
		
		DbAccess.initConnection();
		Connection connessione = DbAccess.getConnection();
		Statement state = connessione.createStatement();
		ResultSet rs = state.executeQuery("select distinct * from " + table);
		
                
        ArrayList<Example> esempioLista = new ArrayList<Example>(14);
		
        int indice = 0;
		while(rs.next()) {
			/*
			System.out.println(rs.getString("outlook") + "| " + rs.getString("temperature") + 
				"| " + rs.getString("humidity") + "| " + rs.getString("wind") + "| " + rs.getString("play"));
			*/ //QUESTA È LA STAMPA DEI VALORI
			
			Example temp = new Example();
			temp.add(rs.getString("outlook"));
			temp.add(rs.getFloat("temperature"));
			temp.add(rs.getString("humidity"));
			temp.add(rs.getString("wind"));
			temp.add(rs.getString("play"));
			esempioLista.add(indice,temp);
			indice++;
		}
		
		return esempioLista;
		
	}

	/**
	 * Formula ed esegue una interrogazione SQL per estrarre i valori distinti ordinati ascendentemente di 
	 * column e popolare un insieme da restituire. L'insieme restituito sara' un oggetto
	 * istanza della classe TreeSet.
	 * Il metodo puo' propagare un eccezione di tipo SQLException (in presenza di errori 
	 * nell' esecuzione della query).
	 * 
	 */
	public  Set<Object>getDistinctColumnValues(String table,String column) throws SQLException{
		
		TreeSet<Object> nuovoSet = new TreeSet<Object>();
		
		try {
			
			DbAccess.initConnection();
			Connection connessione = DbAccess.getConnection();
			Statement state = connessione.createStatement();
			ResultSet rs = state.executeQuery("select distinct " + column + "from " +table);
			while(rs.next()) {
				nuovoSet.add(rs.getObject(column));
			}
			
			return nuovoSet;
			
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		}
		
		
	return nuovoSet;

	}
	
	
	/**
	 * Formula ed esegue una interrogazione SQL per estrarre il valore aggregato (valore minimo 
	 * o valore massimo) cercato nella colonna di nome column della tabella di nome table.
	 * Il metodo solleva e propaga una NoValueException se il resultset e' vuoto o il valore 
	 * calcolato e' pari a null.
	 * 
	 * @param table	La tabella a partire della quale si effettuera' l'interrogazione.
	 * @param column La colonna da considerare per la tabella specificata.
	 * @param aggregate	Valore enumerativo (MAX o MIN) per determinare se effettuare una query
	 * per estrarre il valore minimo o il valore massimo
	 * 
	 * @return Il valore di massimo o minimo calcolato per la colonna specificata come parametro.
	 * 
	 * @throws SQLException L'eccezione sara' sollevata e propagata in presenza di errori 
	 * 					    nella esecuzione della query.
	 * 
	 * @throws NoValueException	Eccezione sollevata se il resultset sara' vuoto oppure se 
	 * il valore calcolato e' uguale a null.
	 */
	public  Object getAggregateColumnValue(String table,String column,QUERY_TYPE aggregate) throws SQLException,NoValueException{
		
		try {
			DbAccess.initConnection();
			Connection connessione = DbAccess.getConnection();
			Statement state = connessione.createStatement();
			ResultSet rs = state.executeQuery("select (" +aggregate+ ")" + column +  " from " +table);
			Object aggregato = rs.next();
			
			return aggregato;
			
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		}
		
		return null;
	
	}



}
