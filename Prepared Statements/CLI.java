

import org.apache.commons.cli.*;

public class CLI {
	Options options = new Options();
	String serverName, databaseName, userName, password;
	
	public CLI(){
		options.addOption("N", true, "Server Name");
		options.addOption("D", true, "Database Name");
		options.addOption("U", true, "Username");
		options.addOption("P", true, "Password");
		
		String[] args = {"N", "D", "U", "P"};
		CommandLineParser parser = new BasicParser();
		try {
			CommandLine line = parser.parse(options, args);
			
			if(line.hasOption("N")){
				serverName = line.getOptionValue("N");
			}
			
			if(line.hasOption("D")){
				databaseName = line.getOptionValue("D");
			}
			
			if(line.hasOption("U")){
				userName = line.getOptionValue("U");
			}
			
			if(line.hasOption("P")){
				password = line.getOptionValue("P");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		new CLI();
	}
}
