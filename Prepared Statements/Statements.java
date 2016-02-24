import java.sql.*;

public class Statements {

	private Connection c;
	public Statements(Connection con){
		this.c = con;
	}
	
	public void create(){
		String createPostgre = "INSERT INTO number VALUES(?);";
		PreparedStatement create;
		for(int x = 0; x<=10000;x++){
			try {
				create = c.prepareStatement(createPostgre);
				create.setInt(1,x);
				create.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void read(){
		String readPostgre = "SELECT * FROM number";
		PreparedStatement read;
		ResultSet rs = null;
		try {
			read = c.prepareStatement(readPostgre);
			rs = read.executeQuery();
			while(rs.next()){
				String s = rs.getString(1);
				System.out.println(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(){
		String updatePostgre = "UPDATE number SET nummer = ? WHERE nummer = ?";
		PreparedStatement update;
		for(int x = 0; x<=10000;x++){
			try {
				update = c.prepareStatement(updatePostgre);
				update.setInt(1,x+1);
				update.setInt(2,x);
				update.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void delete(){
		String deletePostgre = "DELETE * FROM number";
		PreparedStatement delete;
		try {
			delete = c.prepareStatement(deletePostgre);
			delete.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
