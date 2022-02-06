package com.revature.course_registration.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.course_registration.models.Registration;
import com.revature.course_registration.util.ConnectionFactory;
import com.revature.course_registration.util.collections.List;
import com.revature.course_registration.util.logging.Logger;

public class RegistrationDAO implements CrudDAO<Registration> {

	private final Logger logger = Logger.getLogger(true);

	public Registration findCoursesByStudentID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Registration findStudentsByCourseID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Registration create(Registration newRegistration) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "insert into enrollment (student_id, course_id) values (?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			// ps.setString(1, newCourse.getCourseID());
			ps.setInt(1, newRegistration.getStudent_id());
			ps.setInt(2, newRegistration.getCourse_id());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return newRegistration;
			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return null;
	}

	@Override
	public List<Registration> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Registration findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Registration updatedRegistration) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Registration findEnrollment(int student_id, int course_id) {
		// TODO
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from enrollment where (student_id = ? and course_id = ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, student_id);
			ps.setInt(2, course_id);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Registration foundEnrollment = new Registration();

				foundEnrollment.setEnrollment_id(resultSet.getString("enrollment_id"));
				foundEnrollment.setStudent_id(resultSet.getInt("student_id"));
				foundEnrollment.setCourse_id(resultSet.getInt("course_id"));

				return foundEnrollment;

			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return null;
	}

}
