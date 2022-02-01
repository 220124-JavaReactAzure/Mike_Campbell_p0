package main.java.com.revature.course_registration.models;

public class Instructor {
	private String instructorId;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	
	
	public Instructor() {
		super();
	}


	public Instructor(String instructorId, String firstName, String lastName, String email, String username,
			String password) {
		super();
		this.instructorId = instructorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}


	public String getInstructorId() {
		return instructorId;
	}


	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
