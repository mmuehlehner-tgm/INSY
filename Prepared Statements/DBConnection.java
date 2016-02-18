import java.sql.*;
import org.postgresql.ds.PGSimpleDataSource;

/**
 * 
 * @author Moritz Mühlehner
 * 
 * Dowloaden des postgres Drivers (JAR-Datei)
 * https://jdbc.postgresql.org/
 * Einfügen in die Referenced Libraries (Build Path->Add External Archives)
 * 
 * Postgres Config ändern:
 * postgresql.conf: listen_addresses = '*' 
 * pg_hba.conf:	host <Datenbank> <User> <Netz> <Authentifikationsmethode>
 * 
 * Benötigte Schritte:
 * DriverManager oder DataSource
 * Connection
 * Statement
 * ResultSet
 * SQLException
 * 
 */
public class DBConnection {

	public static void main(String[] args) {
		// Datenquelle erzeugen und konfigurieren
		PGSimpleDataSource ds = new PGSimpleDataSource();
		ds.setServerName("10.0.106.235");
		ds.setDatabaseName("schokofabrik");
		ds.setUser("schokouser");
		ds.setPassword("schokoUser");
		/*
		 * Original:
		 * 			import java.sql.*;
					public class JDBCTest {
					 public static void main(String[] args) {
					// Datenquelle erzeugen und konfigurieren
					DataSourceClass ds = new DataSourceClass();
					ds.setServerName(SERVER);
					ds.setUser(USER);
					ds.setPassword(PASSWORD);
					// Verbindung herstellen
					Connection con = ds.getConnection();
					// Abfrage vorbereiten und ausführen
					Statement st = con.createStatement(ERGEBNIS_PARAMETER);
					ResultSet rs = st.executeQuery(SELECT-QUERY);
					// Ergebnisse verarbeiten
					while (rs.next()) { // Cursor bewegen
					 TYPE wert = rs.getTYPE(ATTRIBUTNAME/INDEX);
					}
					finally{
					// aufräumen
					rs.close(); 
					st.close(); 
					con.close();
					}
					}
					}
		 */
		try ( 	// Alles in den runden Klammern wird automatisch geschlossen
				// Ab Java 7
				// Verbindung herstellen
		Connection con = ds.getConnection();
				// Abfrage vorbereiten und ausführen
				/*
				 * Statement createStatement()
                          throws SQLException
					Creates a Statement object for sending SQL statements to the database. 
					SQL statements without parameters are normally executed using Statement objects. 
					If the same SQL statement is executed many times, it may be more efficient to use 
					a PreparedStatement object.Result sets created using the returned Statement object 
					will by default be type TYPE_FORWARD_ONLY and have a concurrency level of CONCUR_READ_ONLY. 
					The holdability of the created result sets can be determined by calling getHoldability().
				 */
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from produkt");

		) {
			// Ergebnisse verarbeiten
			while (rs.next()) // Cursor bewegen
			{
				String wert = rs.getString(2);
				System.out.println(wert);
			}

		} catch (SQLException sql) {
			sql.printStackTrace(System.err);
		}
	}
}