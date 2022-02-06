package com.revature.course_registration.models;

public class Course {
	private String courseID;
	private String courseName;
	private String courseDescription;
	private int courseInstructor;
	private boolean isFull;
	private int courseSeatsMAX;
	private int courseSeatsTaken;
	
	public Course() {
		super();
	}

	public Course(String courseName, String courseDescription, int courseInstructor, int courseSeatsMAX) {
		super();
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseInstructor = courseInstructor;
		this.courseSeatsMAX = courseSeatsMAX;
	}

	public Course(String courseID, String courseName, String courseDescription, int courseInstructor, boolean isFull,
			int courseSeatsMAX, int courseSeatsTaken) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseInstructor = courseInstructor;
		this.isFull = isFull;
		this.courseSeatsMAX = courseSeatsMAX;
		this.courseSeatsTaken = courseSeatsTaken;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public int getCourseInstructor() {
		return courseInstructor;
	}

	public void setCourseInstructor(int courseInstructor) {
		this.courseInstructor = courseInstructor;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	public int getCourseSeatsMAX() {
		return courseSeatsMAX;
	}

	public void setCourseSeatsMAX(int courseSeatsMAX) {
		this.courseSeatsMAX = courseSeatsMAX;
	}

	public int getCourseSeatsTaken() {
		return courseSeatsTaken;
	}

	public void setCourseSeatsTaken(int courseSeatsTaken) {
		this.courseSeatsTaken = courseSeatsTaken;
	}

	@Override
	public String toString() {
		String output = "";
		output = output.concat(this.getCourseID());
		output = output.concat(" ");
		output = output.concat(this.getCourseName());
		output = output.concat(", ");
		output = output.concat(String.valueOf(this.getCourseSeatsTaken()));
		output = output.concat(", ");
		output = output.concat(String.valueOf(this.getCourseSeatsMAX()));
		output = output.concat(", ");
		output = output.concat(this.getCourseDescription());
	
		return output;
	}
}
