import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import org.postgresql.ds.PGSimpleDataSource;

/**
 * 
 * @author Moritz Mühlehner
 * 
 *         Dowloaden des postgres Drivers (JAR-Datei) https://jdbc.postgresql.org/ Einfügen in die Referenced Libraries
 *         (Build Path->Add External Archives)
 * 
 *         Postgres Config ändern: postgresql.conf: listen_addresses = '*' pg_hba.conf: host
 *         <Datenbank> <User> <Netz> <Authentifikationsmethode>
 * 
 * 
 */
public class DBConnection
{

    private Properties prop;
    private InputStream input;
    private PGSimpleDataSource ds;

    /**
     * Konstruktor der die Datenquelle mit den Inhalten des Properties Files initialisiert
     * 
     * @param properties
     *            (Bekommt einen Pfad bzw. Filenamen des Properties File als String)
     */
    public DBConnection(String properties)
    {
	prop = new Properties();
	ds = new PGSimpleDataSource();

	try
	{

	    input = new FileInputStream(properties);

	    // Properties File laden
	    prop.load(input);

	    // Property value einsetzen
	    ds.setServerName(prop.getProperty("server"));
	    ds.setDatabaseName(prop.getProperty("database"));
	    ds.setUser(prop.getProperty("dbuser"));
	    ds.setPassword(prop.getProperty("dbpassword"));

	} catch (IOException ex)
	{
	    ex.printStackTrace();
	} finally
	{
	    if (input != null)
	    {
		try
		{
		    input.close();
		} catch (IOException e)
		{
		    e.printStackTrace();
		}
	    }
	}
    }

    /**
     * Konstruktor der die Datenquelle mit den Inhalten des Argument-String-Arrays initialisiert
     * 
     * @param argsstring
     *            (Bekommt die Argumente in einem String-Array)
     */
    public DBConnection(String[] argsstring)
    {

	// Datenquelle erzeugen
	ds = new PGSimpleDataSource();

	// Property value einsetzen
	ds.setServerName(argsstring[0]);
	ds.setDatabaseName(argsstring[1]);
	ds.setUser(argsstring[2]);
	ds.setPassword(argsstring[3]);

    }

    /**
     * Ermöglicht den Verbindungsaufbau mit einer Datenbank für ein DBConnection Objekt
     * 
     * @return eine Verbindung mit der Datenbank (Connection Objekt) bzw. null wenn dies Fehlschlägt
     */
    public Connection connect()
    {
	try
	{
	    return ds.getConnection();
	} catch (SQLException e)
	{
	    e.printStackTrace();
	}
	return null;
    }
}