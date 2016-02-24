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
	// Wenn die Verbindung funktioniert (also nicht null zur�ck liefert) wird die Conncetion gespeichert
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

		System.err.println("Kein/ung�ltiges Properties File und keine/ung�ltige Arguments vorhanden!");
		throw new Exception("Kein/ung�ltiges Properties File und keine/ung�ltige Arguments vorhanden!");
	    }

	}
	s = new Statements(c);
	s.create();
	s.read();
	s.update();
	s.delete();

    }

}
