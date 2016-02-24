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
 *         Dowloaden des postgres Drivers (JAR-Datei)
 *         https://jdbc.postgresql.org/ Einfügen in die Referenced Libraries
 *         (Build Path->Add External Archives)
 * 
 *         Postgres Config ändern: postgresql.conf: listen_addresses = '*'
 *         pg_hba.conf: host
 *         <Datenbank> <User> <Netz> <Authentifikationsmethode>
 * 
 *         Benötigte Schritte: DriverManager oder DataSource Connection
 *         Statement ResultSet SQLException
 * 
 */
public class DBConnection
{

    private Properties prop;
    private InputStream input;
    private PGSimpleDataSource ds;

    public DBConnection(String properties)
    {
	prop = new Properties();
	input = null;

	// Datenquelle erzeugen und konfigurieren
	ds = new PGSimpleDataSource();

	try
	{

	    input = new FileInputStream(properties);

	    // Properties File laden
	    prop.load(input);

	    // Propertie value eisetzen
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

    public DBConnection(String[] argsstring)
    {

	// Datenquelle erzeugen und konfigurieren
	ds = new PGSimpleDataSource();

	// Propertie value eisetzen
	ds.setServerName(argsstring[0]);
	ds.setDatabaseName(argsstring[1]);
	ds.setUser(argsstring[2]);
	ds.setPassword(argsstring[3]);

    }

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