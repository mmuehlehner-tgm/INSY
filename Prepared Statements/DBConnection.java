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
    private String table;

    /**
     * Konstruktor der die Datenquelle mit den Inhalten des Properties Files initialisiert
     * 
     * @param properties
     *            (Bekommt einen Pfad bzw. Filenamen des Properties File als String)
     * @throws Exception
     */
    public DBConnection(String properties) throws Exception
    {
	prop = new Properties();
	ds = new PGSimpleDataSource();

	try
	{

	    input = DBConnection.class.getResourceAsStream(properties);

	    // Properties File laden
	    prop.load(input);

	    // Property value einsetzen
	    ds.setServerName(prop.getProperty("server"));
	    ds.setDatabaseName(prop.getProperty("database"));
	    ds.setUser(prop.getProperty("dbuser"));
	    ds.setPassword(prop.getProperty("dbpassword"));
	    try
	    {
		ds.setPortNumber(Integer.parseInt(prop.getProperty("dbport")));
	    } catch (Exception e) //Wenn sich der String nicht zu Integer parsen lässt, wird der Standard-Port verwendet
	    {
		ds.setPortNumber(5432); //5432 ist der Standard-Port von Postgres
	    }
	    if (prop.getProperty("table").matches(".*[a-zA-Z]+.*"))
	    {
		table = prop.getProperty("table");
	    } else
	    {
		System.err.println("Keine Tabelle angegeben!");
		throw new Exception();
	    }

	} catch (IOException ex)
	{
	    System.out.println(ex.getMessage());
	} finally
	{
	    if (input != null)
	    {
		try
		{
		    input.close();
		} catch (IOException e)
		{
		    System.out.println(e.getMessage());
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
	try
	{
	    ds.setPortNumber(Integer.parseInt(argsstring[4]));
	} catch (Exception e)
	{
	    ds.setPortNumber(5432);
	}
	table = argsstring[5];
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
	    System.out.println(e.getMessage());
	}
	return null;
    }

    /**
     * 
     * @return Tabelle welche für Querys verwendet werden soll
     */
    public String getTable()
    {
	return this.table;
    }
}