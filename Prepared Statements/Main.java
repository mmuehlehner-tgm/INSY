import java.sql.*;

public class Main
{

    public static void main(String[] args) throws Exception
    {
	DBConnection dbcon;
	Statements s;
	CLI cliargs = new CLI(args);
	String[] argsstring = cliargs.returnArgs();
	Connection c;
	dbcon = new DBConnection("dbconnection.properties");
	// Wenn die Verbindung funktioniert (also nicht null zurück liefert) wird die Conncetion gespeichert
	if (dbcon.connect() != null)
	{
	    c = dbcon.connect();
	} else
	{ // Wenn nicht, wird versucht sich durch die Arguments mit der Datenbank zu verbinden
	    try
	    {
		dbcon = new DBConnection(argsstring);
		c = dbcon.connect();
	    } catch (Exception ex)
	    { // Wenn das auch nicht geht, wird der Benutzer durch eine Ausgabe informiert

		System.err.println("Kein/ungültiges Properties File und keine/ungültige Arguments vorhanden!");
		throw new Exception("Kein/ungültiges Properties File und keine/ungültige Arguments vorhanden!");
	    }

	}
	s = new Statements(c);
	s.create();
	s.read();
	s.update();
	s.delete();

    }

}
