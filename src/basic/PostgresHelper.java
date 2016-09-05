package basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresHelper {

	private String DEFAULTDB = "postgres";
	
	private Connection defaultConn;
	private Connection conn;
	private String host;
	private Database db;
	private Statement statement;
	
	public PostgresHelper(String host, Database db) {
		this.host = host;
		this.db = db;
		//defaultConnect();
	}
	
	public PostgresHelper(String host, String dbName) {
		this.host = host;
		this.db = new Database(dbName);
		//defaultConnect();
	}
	
	public PostgresHelper(String host, String dbName, String user, String pass) {
		this.host = host;
		this.db = new Database(dbName, user, pass);
		//defaultConnect();
	}
	
	private void defaultConnect() {
		try {
			this.defaultConn = DriverManager.getConnection(this.host + DEFAULTDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean connect() {
		if (host.isEmpty()) {
			try {
				throw new SQLException("Host name missing");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			if (db.getUser() == null && db.getPass() == null) {
				this.conn = DriverManager.getConnection(this.host + this.db.getDbName());
			}
			else if (db.getUser() != null && db.getPass() != null){
				this.conn = DriverManager.getConnection(this.host + this.db.getDbName(),
														this.db.getUser(), this.db.getPass());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		createStatement();
		return true;
	}
	
	public void createTable(String tName) {
		
		String sql = "CREATE TABLE " + tName;
		execute(sql);
	}
	
	public void addRow(String tName) {
		
		String sql = "CREATE TABLE " + tName;
		execute(sql);
	}
	
	protected void execute(String sql) {
		try {
			this.statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected ResultSet execute2(String sql) {
		ResultSet rs = null;
		try {
			rs = this.statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	private void createStatement() {
		try {
			this.statement = this.conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public boolean createDatabase() {
		try {
			Statement statement = this.defaultConn.createStatement();
			String sql = "CREATE DATABASE "+ this.db.getDbName();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// Should clean all tables before dropping it!
	public boolean dropDatabase() {
		try {
			Statement statement = this.defaultConn.createStatement();
			String sql = "DROP DATABASE "+ this.db.getDbName();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}

class Database {
	
	private String dbName;
	private String user;
	private String pass;
	
	Database(String dbName) {
		this.dbName = dbName;
	}
	
	Database(String dbName, String user, String pass) {
		this.dbName = dbName;
		this.user = user;
		this.pass = pass;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
