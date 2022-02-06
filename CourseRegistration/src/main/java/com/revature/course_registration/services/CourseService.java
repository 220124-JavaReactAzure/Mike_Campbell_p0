package com.revature.course_registration.services;

import com.revature.course_registration.daos.CourseDAO;
import com.revature.course_registration.exceptions.AuthenticationException;
import com.revature.course_registration.exceptions.InvalidRequestException;
import com.revature.course_registration.exceptions.ResourcePersistenceException;
import com.revature.course_registration.models.Course;
import com.revature.course_registration.util.collections.List;

public class CourseService {
	private final CourseDAO courseDao;

	public CourseService(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}

	public Course registerNewCourse(Course newCourse) {
		if (!isCourseValid(newCourse)) {
			throw new InvalidRequestException("Invalid user data provider");
		}
		/*
		// logic that verifies the new users information isn't duplicated in the system
		boolean usernameAvailable = courseDao.findByUsername(newCourse.getCourseName()) == null;
		boolean emailAvailable = courseDao.findByUsername(newCourse.getEmail()) == null;

		if (!usernameAvailable || !emailAvailable) {
			if (!usernameAvailable && emailAvailable) {
				throw new ResourcePersistenceException("The provided username was already taken in the database");
			} else if (usernameAvailable) {
				throw new ResourcePersistenceException("The provided email was already taken in the database");
			} else {
				throw new ResourcePersistenceException(
						"The provided username and email were already taken in the database");
			}
		}
		 */
		Course persistedCourse = courseDao.create(newCourse);

		if (persistedCourse == null) {
			throw new ResourcePersistenceException("The user could not be persisted");
		}

		return persistedCourse;
	}

	public List<Course> getAllCourses() {
		return courseDao.findAll();
	}

	public boolean isCourseValid(Course newCourse) {
		if (newCourse == null) {
			return false;
			}
		if (newCourse.getCourseName() == null || newCourse.getCourseName().trim().equals("")) {
			return false;
		}
			
		if (newCourse.getCourseSeatsMAX() == 0) {
			return false;
		}
		if (newCourse.getCourseSeatsTaken() == newCourse.getCourseSeatsMAX()){
			return false;
		}
			
		return true;

	}

}