
import org.apache.commons.cli.*;

public class CLI
{
    Options options = new Options();
    String serverName, databaseName, userName, password, port, table;

    /**
     * Konstruktor
     * @param args
     */
    public CLI(String[] args)
    {
	options.addOption("N", true, "Server Name");
	options.addOption("D", true, "Database Name");
	options.addOption("U", true, "Username");
	options.addOption("P", true, "Password");
	options.addOption("p", true, "Port");
	options.addOption("t", true, "Table");

	CommandLineParser parser = new DefaultParser();
	try
	{
	    CommandLine line = parser.parse(options, args);

	    if (line.hasOption("N"))
	    {
		serverName = line.getOptionValue("N");
	    }

	    if (line.hasOption("D"))
	    {
		databaseName = line.getOptionValue("D");
	    }

	    if (line.hasOption("U"))
	    {
		userName = line.getOptionValue("U");
	    }

	    if (line.hasOption("P"))
	    {
		password = line.getOptionValue("P");
	    }
	    
	    if (line.hasOption("p")){
	    port = line.getOptionValue("p");
	    }
	    
	    if (line.hasOption(table)){
	    table = line.getOptionValue("table");	
	    }
	} catch (ParseException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Methode zum Zurückgeben der Attribute
     * @return
     */
    public String[] returnArgs()
    {
	String[] sa = { serverName, databaseName, userName, password, port };
	return sa;
    }
    
    public String returnTable(){
    	return table;
    }
}
