package com.revature.course_registration.models;

import java.io.Serializable;

import com.revature.course_registration.util.collections.ArrayList;

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
	
	public User(String firstName, String lastName, String email, String username, String password, int userPermission) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.userPermission = userPermission;
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

	@Override
	public String toString() {
		String output = "";
		output = output.concat(this.getFirstName());
		output = output.concat(" ");
		output = output.concat(this.getLastName());
		output = output.concat(", ");
		output = output.concat(this.getEmail());
		output = output.concat(", ");
		
		if(this.getUserPermission() == 0) {
			output = output.concat("Student");
		}
		else if(this.getUserPermission() == 1) {
			output = output.concat("Instructor");
		}
		else {
			output = output.concat("User");
		}
		
		return output;
	}

}
