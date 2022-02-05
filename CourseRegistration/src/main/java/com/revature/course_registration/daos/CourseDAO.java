package com.revature.course_registration.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.course_registration.models.Course;
import com.revature.course_registration.models.User;
import com.revature.course_registration.util.ArrayList;
import com.revature.course_registration.util.ConnectionFactory;
import com.revature.course_registration.util.List;
import com.revature.course_registration.util.logging.Logger;

public class CourseDAO implements CrudDAO<Course> {

	private final Logger logger = Logger.getLogger(true);

	@Override
	public Course create(Course newCourse) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {


			String sql = "insert into course (course_name, course_description, instructor_id, "
					+ "course_seats_max, course_seats_taken, course_is_full) values (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			// ps.setString(1, newCourse.getCourseID());
			ps.setString(1, newCourse.getCourseName());
			ps.setString(2, newCourse.getCourseDescription());
			ps.setString(3, newCourse.getCourseInstructor());
			ps.setInt(4, newCourse.getCourseSeatsMAX());
			ps.setInt(5, newCourse.getCourseSeatsTaken());
			ps.setBoolean(6, newCourse.isFull());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return newCourse;
			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return null;
	}

	@Override
	public List<Course> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Course updatedCourse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	// TODO: implement find courses by user
	public List<Course> findUserCourses(User user) {
		List<Course> results = new ArrayList<>();

		return results;
	}

}
