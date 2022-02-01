package main.java.com.revature.course_registration.models;

public class Course {
	String courseID;
	String courseName;
	String courseDescription;
	String courseInstructor;
	boolean isFull;
	int courseSeats;
	
	public Course() {
		super();
	}

	public Course(String courseID, String courseName, String courseDescription, String courseInstructor, boolean isFull,
			int courseSeats) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseInstructor = courseInstructor;
		this.isFull = isFull;
		this.courseSeats = courseSeats;
	}
	
	

}
