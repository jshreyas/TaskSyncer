package basic;
import java.sql.SQLException;

//import basic.DbContract;

public class Main {

	public static void main(String[] args) {
		
		String HOST = "jdbc:postgresql://localhost:5432/";
		String DB_NAME = "testdb";
		
		//Database db = new Database("yo");
		//PostgresHelper client = new PostgresHelper(HOST, db);
		
//		if (client.createDatabase()) {
//			System.out.println("DB created");
//		}
//		if (client.dropDatabase()) {
//			System.out.println("DB dropped");
//		}
		
		//User u = new User("test@test.com", "test");
		//u.save();
		
		User u = new User("a@a.c", "a");
		u.client.getPassword("a@a.c");
		
	}

}