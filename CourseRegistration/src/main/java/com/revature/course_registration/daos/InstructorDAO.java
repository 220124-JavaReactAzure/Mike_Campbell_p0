package main.java.com.revature.course_registration.daos;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import main.java.com.revature.course_registration.util.List;
import main.java.com.revature.course_registration.models.Instructor;
import main.java.com.revature.course_registration.util.ArrayList;
import main.java.com.revature.course_registration.util.ConnectionFactory;

public class InstructorDAO implements CrudDAO<Instructor> {

	// TODO: Implement Authentication
	public Instructor findByUsernameAndPassword(String username, String password) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	// TODO: Implement FindByEmail
	public Instructor findByEmail(String email) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from instructor where email = ".concat(email);
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);
			
			if(resultSet != null) {
				Instructor foundInstructor = new Instructor();
	
				foundInstructor.setInstructorId(resultSet.getString("instructor_id"));
				foundInstructor.setFirstName(resultSet.getString("first_name"));
				foundInstructor.setLastName(resultSet.getString("last_name"));
				foundInstructor.setEmail(resultSet.getString("email"));
				foundInstructor.setUsername(resultSet.getString("username"));
				foundInstructor.setPassword(resultSet.getString("password"));
				
				return foundInstructor;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// TODO: Implement FindByUsername
	public Instructor findByUsername(String username) {
		return null;
	}

	@Override
	public Instructor create(Instructor newInstructor) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			// random unique ID generation & assignment
			// newInstructor.setInstructorId(UUID.randomUUID().toString());

			String sql = "insert into instructor (instructor_id, first_name, last_name, email, username, password) values (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, newInstructor.getInstructorId());
			ps.setString(2, newInstructor.getFirstName());
			ps.setString(3, newInstructor.getLastName());
			ps.setString(4, newInstructor.getEmail());
			ps.setString(5, newInstructor.getUsername());
			ps.setString(6, newInstructor.getPassword());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return newInstructor;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Instructor> findAll() {
		List<Instructor> instructorsList = new ArrayList<Instructor>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from instructor";
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				Instructor instructor = new Instructor();
				instructor.setInstructorId(resultSet.getString("instructor_id"));
				instructor.setFirstName(resultSet.getString("first_name"));
				instructor.setLastName(resultSet.getString("last_name"));
				instructor.setEmail(resultSet.getString("email"));
				instructor.setUsername(resultSet.getString("username"));
				instructor.setPassword(resultSet.getString("password"));

				instructorsList.add(instructor);
			}

			return instructorsList;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Instructor findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Instructor updatedInstructor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
