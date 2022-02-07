package com.revature.course_registration.services;

import com.revature.course_registration.daos.CourseDAO;
import com.revature.course_registration.exceptions.InvalidRequestException;
import com.revature.course_registration.exceptions.ResourcePersistenceException;
import com.revature.course_registration.models.Course;
import com.revature.course_registration.models.User;
import com.revature.course_registration.util.collections.List;

public class CourseService {
	private final CourseDAO courseDAO;

	public CourseService(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public Course createNewCourse(Course newCourse) {
		if (!isCourseValid(newCourse)) {
			throw new InvalidRequestException("Invalid course data provided.");
		}
		/*
		 * // logic that verifies the new course information isn't duplicated in the
		 * system boolean courseWithInstructorExists =
		 * courseDao.findByCourseName(newCourse.getCourseName()) == null; boolean
		 * emailAvailable = courseDao.findByUsername(newCourse.getEmail()) == null;
		 * 
		 * if (courseNameExists || !emailAvailable) { if (!usernameAvailable &&
		 * emailAvailable) { throw new
		 * ResourcePersistenceException("The provided username was already taken in the database"
		 * ); } else if (usernameAvailable) { throw new
		 * ResourcePersistenceException("The provided email was already taken in the database"
		 * ); } else { throw new ResourcePersistenceException(
		 * "The provided username and email were already taken in the database"); } }
		 */
		Course persistedCourse = courseDAO.create(newCourse);

		if (persistedCourse == null) {
			throw new ResourcePersistenceException("The user could not be persisted");
		}

		return persistedCourse;
	}

	public List<Course> getAllCourses() {
		return courseDAO.findAll();
	}

	public boolean isCourseValid(Course newCourse) {
		// could simplify code by using pattern matching tools
		if (newCourse == null) {
			return false;
		}
		if (newCourse.getCourseName() == null || newCourse.getCourseName().trim().equals("")) {
			return false;
		}

		if (newCourse.getCourseSeatsMAX() == 0) {
			return false;
		}
		if (newCourse.getCourseSeatsTaken() > newCourse.getCourseSeatsMAX()) {
			return false;
		}
		// prohibit special characters that may allow sql injection
		if (newCourse.getCourseName().contains(";") || newCourse.getCourseDescription().contains(";")) {
			return false;
		}
		if (newCourse.getCourseName().contains("\"") || newCourse.getCourseDescription().contains("\"")) {
			return false;
		}

		return true;

	}

	public List<Course> findUserCourses(User sessionUser) {

		return courseDAO.findUserCourses(sessionUser);
	}

	public Course findCourseByID(String id) {
		return courseDAO.findById(id);
	}

	public List<Course> findOpenCourses() {

		return courseDAO.findAvailableCourses();
	}

	public void updateCourse(Course updatedCourse) {

		// check that update doesnt push seats take above seats max
		if (updatedCourse.getCourseSeatsTaken() > updatedCourse.getCourseSeatsMAX()) {
			throw new ResourcePersistenceException("Failure updating course: 'Seats Taken' may not be larger than 'Seats Max'.");
		}
		if (updatedCourse.getCourseSeatsTaken() == updatedCourse.getCourseSeatsMAX()) {
			updatedCourse.setFull(true);
		}
		if (!courseDAO.update(updatedCourse)) {
			throw new ResourcePersistenceException("Failure updating course.");
		}

	}

}