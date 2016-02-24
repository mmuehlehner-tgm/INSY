import java.sql.*;

/**
 * 
 * @author Moritz Mühlehner
 * @version 1.0
 */

public class Main
{

    /**
     * Beinhaltet den Ablauf des Programms
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
	DBConnection dbcon;
	Statements s;
	CLI cliargs = new CLI(args);
	String[] argsstring = cliargs.returnArgs();
	Connection c;
	dbcon = new DBConnection("dbconnection.properties");
	// Wenn die Verbindung funktioniert (also nicht null zurück liefert, sondern ein Connection Objekt) wird die
	// Conncetion gespeichert
	if (dbcon.connect() != null)
	{
	    c = dbcon.connect();
	} else
	{ // Wenn null, wird versucht sich durch die CLI-Arguments mit der Datenbank zu verbinden
	    try
	    {
		dbcon = new DBConnection(argsstring); // Dafür wird ein neues DBConnection Objekt erzeugt, welchem die
						      // Argumente als String-Array übergeben werden
		c = dbcon.connect();
	    } catch (Exception ex)
	    { // Wenn das auch nicht geht, wird der Benutzer durch eine Ausgabe informiert

		System.err.println("Kein/ungültiges Properties File und keine/ungültige Arguments vorhanden!");
		throw new Exception("Kein/ungültiges Properties File und keine/ungültige Arguments vorhanden!");
	    }

	}
	s = new Statements(c); // Dem Statements Objekt wird die Connection übergeben
	s.create("number"); // erzeugen von 10000 Datensätzen
	s.read(); // lesen der Daten
	// Update fehlt noch
	s.read(); // erneutes lesen nach Update
	s.delete(); // löschen aller Daten

    }

}
