package basic;

import java.util.UUID;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPostgres extends PostgresHelper{

	static String HOST = "jdbc:postgresql://localhost:5432/";
	static String DB_NAME = "testdb";
	static String TABLE_NAME = "USERS";
	//User user;
	
	public UserPostgres() {
		super(HOST, DB_NAME);
	}

	public void insertInto(User user) {
		
		String sql = "INSERT INTO " + TABLE_NAME + " (ID, EMAIL, PASSWORD, USERNAME) VALUES ('"+user.userId+"','"+user.email+"','"+user.passHash+"','"+user.userName+"');";
		execute(sql);
	}
	
	public void update(User user) {
		StringBuilder set = new StringBuilder();
		if(user.fName != null) {
			set.append("FIRSTNAME = '"+user.fName+"',");
		}
		if(user.lName != null) {
			set.append(" LASTNAME = '"+user.lName+"',");
		}
		String sql = "UPDATE " + TABLE_NAME + " SET " + set.substring(0, set.length()-1).toString() + " WHERE EMAIL ilike '"+user.email+"';";
		execute(sql);
	}
	
	public String getPassword(String uName) {
		String sql = "SELECT password from " + TABLE_NAME + " where username ilike '" + uName + "'";
		ResultSet rs = execute2(sql);
		String pass = "";
		try {
			if(rs.next()) {
				pass = rs.getString("password");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pass.toString();
	}
	
	public User getAll(String uName) {
		String sql = "SELECT * from " + TABLE_NAME + " where username ilike '" + uName + "'";
		ResultSet rs = execute2(sql);
		User user = new User();
		try {
			if(rs.next()) {
				user.passHash = rs.getString("password");
				user.email = rs.getString("email");
				user.fName = rs.getString("firstname");
				user.lName = rs.getString("lastname");
				user.userId = UUID.fromString(rs.getString("id"));
				user.userName = rs.getString("username");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
