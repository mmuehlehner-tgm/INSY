
import java.sql.*;

public class Statements {

	private Connection c;

	public Statements(Connection con) {
		this.c = con;
	}

	public void create() {
		String createPostgre = "INSERT INTO number VALUES(?);";
		PreparedStatement create;
		for (int x = 0; x <= 10000; x++) {
			try {
				create = c.prepareStatement(createPostgre);
				create.setInt(1, x);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void read() {
		String readPostgre = "SELECT * FROM number";
		PreparedStatement read;
		try {
			read = c.prepareStatement(readPostgre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update() {
		String updatePostgre = "UPDATE number SET nummer = ? WHERE nummer = ?";
		PreparedStatement update;
		for (int x = 0; x <= 10000; x++) {
			try {
				update = c.prepareStatement(updatePostgre);
				update.setInt(1, x + 1);
				update.setInt(2, x);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void delete() {
		String rdeletePostgre = "DELETE * FROM number";
		PreparedStatement delete;
		try {
			delete = c.prepareStatement(rdeletePostgre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
