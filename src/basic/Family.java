package basic;

import java.util.UUID;
import java.util.ArrayList;

public class Family {

	ArrayList<User> pool;
	UUID familyId;
	ArrayList<User> owners;
	ArrayList<User> parents;
	ArrayList<User> children;
	
	public Family(User user) { 
		this.pool.add(user);
		generateFamilyId();
	}
	
	public Family(ArrayList<User> users) { 
		this.pool.addAll(users);
		generateFamilyId();
	}
	
	public int getFamilySize() {
		return pool.size();
	}
	
	private void generateFamilyId() {
		this.familyId = UUID.randomUUID();
	}
	
}
