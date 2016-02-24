
import org.apache.commons.cli.*;

public class CLI {
	Options options = new Options();
	String serverName, databaseName, userName, password;

	public CLI(String[] args) {
		options.addOption("N", true, "Server Name");
		options.addOption("D", true, "Database Name");
		options.addOption("U", true, "Username");
		options.addOption("P", true, "Password");

		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("N")) {
				serverName = line.getOptionValue("N");
			}

			if (line.hasOption("D")) {
				databaseName = line.getOptionValue("D");
			}

			if (line.hasOption("U")) {
				userName = line.getOptionValue("U");
			}

			if (line.hasOption("P")) {
				password = line.getOptionValue("P");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] returnArgs() {
		String[] sa = { serverName, databaseName, userName, password };
		return sa;
	}
}
