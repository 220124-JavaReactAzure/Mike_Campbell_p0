package main.java.com.revature.course_registration.models;

import java.io.Serializable;

import main.java.com.revature.course_registration.util.ArrayList;

@SuppressWarnings("serial")
public class User implements Serializable {
	// Attributes/Variables
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private int userPermission;

	// Constructor
	public User() {
		super();
	}

	public User(String firstName, String lastName, String email, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(int userPermission) {
		this.userPermission = userPermission;
	}

	public String toFileString() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addCourse(ArrayList<Course> courseSchedule) {
		// TODO
	}

	public void removeCourse(ArrayList<Course> courseSchedule) {
		// TODO
	}

}
