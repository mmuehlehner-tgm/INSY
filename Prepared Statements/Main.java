import java.sql.*;

public class Main {

	public static void main(String[] args) {
		
		DBConnection dbcon=new DBConnection("dbconnection.properties");
		Connection c=dbcon.connect();
		CLI cliargs=new CLI();
		String[] argsstring=cliargs.returnArgs();
		System.out.println(argsstring[0]);
		Statements s=new Statements(c);
		Statement st;
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery("select * from number");
			while(rs.next()){
				String tmp = rs.getString(1);
				System.out.println(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		

	}

}
