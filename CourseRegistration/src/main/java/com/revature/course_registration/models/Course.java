package com.revature.course_registration.models;

public class Course {
	String courseID;
	String courseName;
	String courseDescription;
	String courseInstructor;
	boolean isFull;
	int courseSeatsMAX;
	int courseSeatsTaken;
	
	public Course() {
		super();
	}

	public Course(String courseName, String courseDescription, String courseInstructor, int courseSeatsMAX) {
		super();
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseInstructor = courseInstructor;
		this.courseSeatsMAX = courseSeatsMAX;
	}

	public Course(String courseID, String courseName, String courseDescription, String courseInstructor, boolean isFull,
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

	public String getCourseInstructor() {
		return courseInstructor;
	}

	public void setCourseInstructor(String courseInstructor) {
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
		// TODO Auto-generated method stub
		return super.toString();
	}

	
	

}
