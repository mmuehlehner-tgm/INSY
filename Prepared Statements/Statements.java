import java.sql.*;

public class Statements
{

    private Connection c;

    /**
     * Konstruktor
     * @param con
     */
    public Statements(Connection con)
    {
	this.c = con;
    }

    /**
     * Methode zum Erstellen von Datens�tze in einer Tabelle, mittels prepared Statements
     */
    public void create()
    {
	String createPostgre = "INSERT INTO number VALUES(?);";//String in dem der PostgreSQL Befehl gespeichert wird. Die ? stehen f�r Platzhalter die man sp�ter einsetzten kann
	PreparedStatement create;
	for (int x = 0; x <= 10000; x++)
	{
	    try
	    {
		create = c.prepareStatement(createPostgre);//Erstellen eines prepared Statement Objektes
		create.setInt(1, x);//Die 1, steht f�r das 1. ? in dem Postgre Befehl, der 2. Parameter, ist der, welcher statt dem ? eingesetzt wird
		create.execute();//Ausf�hren des Statements 
		c.close();//Gibt die Connection und JDBC Ressourcen frei
	    } catch (SQLException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    /**
     * Methode zum Auslesen von Datens�tzen und Ausgeben via Konsole, mithilfe von prepared Statements
     */
    public void read()
    {
	String readPostgre = "SELECT * FROM number;";
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
	    c.close();
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Methode zum Uptaden von Datens�tzen mithilfe von prepared Statements
     */
    public void update()
    {
	String updatePostgre = "UPDATE number SET nummer = ? WHERE nummer = ?;";
	PreparedStatement update;
	for (int x = 1; x <= 10000; x++)
	{
	    try
	    {
		update = c.prepareStatement(updatePostgre);
		update.setInt(1, x + 1);
		update.setInt(2, x);
		update.executeUpdate();
		update.close();
	    } catch (SQLException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    /**
     * Methode zum L�schen von Datens�tzen mithilfe von prepared Statements
     */
    public void delete()
    {
	String rdeletePostgre = "DELETE FROM number;";
	PreparedStatement delete;
	try
	{
	    delete = c.prepareStatement(rdeletePostgre);
	    delete.execute();
	    c.close();
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
