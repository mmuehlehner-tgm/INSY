import java.sql.*;

public class Statements
{

    private Connection c;

    /**
     * Konstruktor
     * 
     * @param con
     */
    public Statements(Connection con)
    {
	this.c = con;
    }

    /**
     * Methode zum Erstellen von Datensätze in einer Tabelle, mittels prepared Statements
     */
    public void create(String table)
    {
	String createPostgre = "INSERT INTO " + table + " VALUES(?);";// String in dem der PostgreSQL Befehl gespeichert
								      // wird. Die ? stehen für Platzhalter die man
								      // später einsetzten kann
	PreparedStatement create;
	for (int x = 1; x <= 10000; x++)
	{
	    try
	    {
		create = c.prepareStatement(createPostgre);// Erstellen eines prepared Statement Objektes
		create.setInt(1, x);// Die 1, steht für das 1. ? in dem Postgre Befehl, der 2. Parameter, ist der,
				    // welcher statt dem ? eingesetzt wird
		create.execute();// Ausführen des Statements
		create.close();// Gibt das Statement Objekt frei
	    } catch (SQLException e)
	    {
		System.out.println(e.getMessage());
	    }
	}
    }

    /**
     * Methode zum Auslesen von Datensätzen und Ausgeben via Konsole, mithilfe von prepared Statements
     */
    public void read(String table)
    {
	String readPostgre = "SELECT * FROM " + table + ";";
	PreparedStatement read;
	ResultSet rs = null;
	try
	{
	    read = c.prepareStatement(readPostgre);
	    rs = read.executeQuery();
	    while (rs.next())
	    {
		String s = rs.getString(1);
		System.out.println(s);
	    }
	    read.close();
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	}
    }

    /**
     * Methode zum Uptaden von Datensätzen mithilfe von prepared Statements
     */
    public void update(String table)
    {
	String updatePostgre = "UPDATE " + table + " SET nummer = ? WHERE nummer = ?;";
	PreparedStatement update;
	for (int x = 1; x <= 10000; x++)
	{
	    try
	    {
		update = c.prepareStatement(updatePostgre);
		update.setInt(1, x + 10000);
		update.setInt(2, x);
		update.executeUpdate();
		update.close();
	    } catch (SQLException e)
	    {
		System.out.println(e.getMessage());
	    }
	}
    }

    /**
     * Methode zum Löschen von Datensätzen mithilfe von prepared Statements
     */
    public void delete(String table)
    {
	String rdeletePostgre = "DELETE FROM " + table + ";";
	PreparedStatement delete;
	try
	{
	    delete = c.prepareStatement(rdeletePostgre);
	    delete.execute();
	    delete.close();
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	}
    }
}
