import java.sql.*;

/**
 * 
 * @author Moritz M�hlehner
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
	DBConnection dbcon = null;
	Statements s;
	Connection c = null;
	try
	{
	    dbcon = new DBConnection("dbconnection.properties"); // Dem DBConnection Konstruktor wird ein String mit dem
								 // Properties File �bergeben
	    c = dbcon.connect();
	} catch (Exception e)
	{
	    // Wenn die Verbindung mit Properties nicht funktioniert,
	    // wird versucht sich durch die CLI-Arguments mit der Datenbank zu verbinden
	    System.out.println("Connection durch Properties fehlgeschlagen! Versuche CLI-Arguments...");
	    CLI cliargs = new CLI(args); // Dem CLI Konstruktor werden die Arguments �bergeben
	    String[] argsstring = cliargs.returnArgs(); // Die Arguments werden richtig geordnet in einem String Array
							// zur�ckgegeben
	    dbcon = new DBConnection(argsstring); // Daf�r wird ein neues DBConnection Objekt erzeugt, welchem die
						  // geordneten Argumente als String-Array �bergeben werden
	    if (dbcon.connect() != null) // connect() gibt null bei fehlgeschlagener Connection zur�ck
	    {
		c = dbcon.connect();
	    } else
	    {
		// Wenn das nicht geht, wird der Benutzer durch eine Ausgabe informiert
		System.err.println("Kein/ung�ltiges Properties File und keine/ung�ltige Arguments vorhanden!");
		throw new Exception("Kein/ung�ltiges Properties File und keine/ung�ltige Arguments vorhanden!");
	    }

	}

	s = new Statements(c); // Dem Statements Objekt wird die Connection �bergeben
	s.create(dbcon.getTable()); // erzeugen von 10000 Datens�tzen
	s.read(dbcon.getTable()); // lesen der Daten
	s.update(dbcon.getTable());// Update der Daten
	s.read(dbcon.getTable()); // erneutes lesen nach Update
	s.delete(dbcon.getTable()); // l�schen aller Daten

    }

}
