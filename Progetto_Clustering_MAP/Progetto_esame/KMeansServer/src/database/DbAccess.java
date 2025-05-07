package database;
import java.sql.*;

/**
 * La classe DbAccess realizza l'accesso alla base di dati.
 * La classe si occupa di effettuare una connessione alla base di dati sfruttando i driver di
 * connessione mysql.
 * La connessione e' effettuata in locale attraverso una url del tipo:
 * "jdbc:mysql://localhost:PORT/MapDB"
 * Dove PORT sara' la porta in cui e' disponibile il servizio mysql.
 * Per la connessione saranno necessaria anche uno userid e una password.
 * 
 * @author Michele Metta
 *
 */
public class DbAccess {

	private static final String DBMS = "jdbc:mysql";
	private static final String SERVER = "localhost";
	private static final String DATABASE = "MapDB";
	private static final String PORT = "3306";
	private static final String USER_ID = "MapUser";
	private static final String PASSWORD = "map";
	private static Connection conn;//oggetto di tipo Connection
	
	public static void initConnection() throws DatabaseConnectionException {
		
		try {
			
			conn = DriverManager.getConnection(DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",USER_ID, PASSWORD);
		
		} catch (SQLException e) {
			
			e.printStackTrace();//viene sollevata l'eccezione di tipo SQLExeption qualora si verificasse
			//un errore durante la connessione al database
		}
		
	
	}
	
	public static Connection getConnection() {
		
		return conn;
	}
	
	public static void closeConnection() {
		
		try {
			
			conn.close();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
