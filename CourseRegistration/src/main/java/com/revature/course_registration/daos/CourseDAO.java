package com.revature.course_registration.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.course_registration.models.Course;
import com.revature.course_registration.models.User;
import com.revature.course_registration.util.ConnectionFactory;
import com.revature.course_registration.util.collections.ArrayList;
import com.revature.course_registration.util.collections.List;
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
			ps.setInt(3, newCourse.getCourseInstructor());
			ps.setInt(4, newCourse.getCourseSeatsMAX());
			ps.setInt(5, newCourse.getCourseSeatsTaken());
			ps.setBoolean(6, newCourse.isFull());

			int returnedID = ps.executeUpdate();

			if (returnedID != 0) {
				newCourse.setCourseID(String.valueOf(returnedID));
				return newCourse;
			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return null;
	}

	@Override
	public List<Course> findAll() {
		List<Course> courseList = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from course";
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				Course course = new Course();
				course.setCourseID(resultSet.getString("course_id"));
				course.setCourseName(resultSet.getString("course_name"));
				course.setCourseDescription(resultSet.getString("course_description"));
				course.setCourseInstructor(resultSet.getInt("instructor_id"));
				course.setCourseSeatsMAX(resultSet.getInt("course_seats_max"));
				course.setCourseSeatsTaken(resultSet.getInt("course_seats_taken"));
				course.setFull(resultSet.getBoolean("course_is_full"));

				courseList.add(course);
			}

			return courseList;

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return null;
	}

	@Override
	public Course findById(String id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from course where course_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Course foundCourse = new Course();

				foundCourse.setCourseID(resultSet.getString("course_id"));
				foundCourse.setCourseName(resultSet.getString("course_name"));
				foundCourse.setCourseDescription(resultSet.getString("course_description"));
				foundCourse.setCourseInstructor(resultSet.getInt("instructor_id"));
				foundCourse.setCourseSeatsMAX(resultSet.getInt("course_seats_max"));
				foundCourse.setCourseSeatsTaken(resultSet.getInt("course_seats_taken"));
				foundCourse.setFull(resultSet.getBoolean("course_is_full"));
				return foundCourse;
			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());

		}
		return null;
	}

	@Override
	public boolean update(Course updatedCourse) {
		

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "update course set course_name = ?, course_description = ?, "
					+ "course_seats_max = ?, course_seats_taken = ? where course_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			// overwrite
			ps.setString(1, updatedCourse.getCourseName());
			ps.setString(2, updatedCourse.getCourseDescription());
			ps.setInt(3, updatedCourse.getCourseSeatsMAX());
			ps.setInt(4, updatedCourse.getCourseSeatsTaken());
			// where
			ps.setString(5, updatedCourse.getCourseID());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected == 1) {
				return true;
			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return false;
	}

	@Override
	public boolean delete(String id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			//first remove any registrations for this course
			String sql = "delete from enrollment where course_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, id);
			
			//TODO use this return value to check number of registrations removed
			int rowsAffected = ps.executeUpdate();		
			
			//now delete course
			sql = "delete from course where course_id = ?";

			ps = conn.prepareStatement(sql);

			
			ps.setString(1, id);
			

			rowsAffected = ps.executeUpdate();

			if (rowsAffected == 1) {
				return true;
			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return false;

	}

	public List<Course> findUserCourses(User user) {
		List<Course> results = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from course natural join enrollment where student_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Course course = new Course();
				course.setCourseID(resultSet.getString("course_id"));
				course.setCourseName(resultSet.getString("course_name"));
				course.setCourseDescription(resultSet.getString("course_description"));
				course.setCourseInstructor(resultSet.getInt("instructor_id"));
				course.setCourseSeatsMAX(resultSet.getInt("course_seats_max"));
				course.setCourseSeatsTaken(resultSet.getInt("course_seats_taken"));
				course.setFull(resultSet.getBoolean("course_is_full"));

				results.add(course);
			}

			return results;

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return null;
	}

	public Object findByCourseName(String courseName) {
		// TODO Auto-generated method stub
		return null;
	}

	// could use refinement to find courses only applicable to specific users
	public List<Course> findAvailableCourses() {
		List<Course> availableCourses = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from course where course_seats_taken < course_seats_max";
			Statement s = conn.createStatement();
			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				Course course = new Course();
				course.setCourseID(resultSet.getString("course_id"));
				course.setCourseName(resultSet.getString("course_name"));
				course.setCourseDescription(resultSet.getString("course_description"));
				course.setCourseInstructor(resultSet.getInt("instructor_id"));
				course.setCourseSeatsMAX(resultSet.getInt("course_seats_max"));
				course.setCourseSeatsTaken(resultSet.getInt("course_seats_taken"));
				course.setFull(resultSet.getBoolean("course_is_full"));

				availableCourses.add(course);
			}

			return availableCourses;

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return null;
	}

}
