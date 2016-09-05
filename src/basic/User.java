package basic;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.*;
import utils.PasswordStorage.*;

public class User {

	UUID userId;
	public String userName;
	public String email;
	public String fName;
	public String lName;
	public String passHash;

	public UserPostgres client = new UserPostgres();

	public User() {
		this.client.connect();
	}

	public User(String email, String pass) {
		if (checkEmail(email)) {
			this.email = email;
		} else {
			// not acceptable
		}
		generatePassHash(pass);
		generateUserId();
		generateUserName();
		this.client.connect();
	}

	public boolean verify(String userName, String pass) {
		PasswordStorage ps = new PasswordStorage();
		String hashFromDb = this.client.getPassword(userName);
		try {
			if (ps.verifyPassword(pass, hashFromDb)) {
				updateThisObject(userName);
				generatePassHash(pass);
				return true;
			}
		} catch (CannotPerformOperationException | InvalidHashException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateThisObject(String userName) {
		
		User u = this.client.getAll(userName);
		updateThisObject(u);
	}
	
	private void updateThisObject(User user) {
		
		if(user.email != null) this.email = user.email;
		if(user.lName != null) this.lName = user.lName;
		if(user.fName != null) this.fName = user.fName;
		if(user.userId != null) this.userId = user.userId;
		if(user.userName != null) this.userName = user.userName;
	}

	private void generatePassHash(String pass) {
		PasswordStorage ps = new PasswordStorage();
		try {
			this.passHash = ps.createHash(pass);
		} catch (CannotPerformOperationException e) {
			e.printStackTrace();
		}
	}

	private void generateUserId() {
		this.userId = UUID.randomUUID();
	}

	private void generateUserName() {
		Pattern pattern = Pattern.compile("^(.+)@.+$");
		Matcher matcher = pattern.matcher(this.email);
		if (matcher.find()) {
			this.userName = matcher.group(1);
		}
	}

	private boolean checkEmail(String email) {
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public void save() {
		this.client.insertInto(this);
	}
	
	public void updateDb(User user) {
		this.client.update(user);
		generatePassHash(user.passHash);
		//
		updateThisObject(user);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public User getUser() {
		return this;
	}

}
