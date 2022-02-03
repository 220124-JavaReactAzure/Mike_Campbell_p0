package main.java.com.revature.course_registration.daos;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.revature.course_registration.models.Student;
import com.revature.course_registration.util.List;
import main.java.com.revature.course_registration.util.ArrayList;
import main.java.com.revature.course_registration.util.ConnectionFactory;



public class StudentDAO implements CrudDAO<Student> {

	// TODO: Implement Authentication
	public Student findByUsernameAndPassword(String username, String password) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	// TODO: Implement FindByEmail
	public Student findByEmail(String email) {
		return null;
	}

	// TODO: Implement FindByUsername
	public Student findByUsername(String username) {
		return null;
	}

	@Override
	public Student create(Student newStudent) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			// random unique ID generation & assignment
			// newStudent.setScientistId(UUID.randomUUID().toString());

			String sql = "insert into scientists (scientist_id, first_name, last_name, email, username, password) values (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, newStudent.getStudentId());
			ps.setString(2, newStudent.getFirstName());
			ps.setString(3, newStudent.getLastName());
			ps.setString(4, newStudent.getEmail());
			ps.setString(5, newStudent.getUsername());
			ps.setString(6, newStudent.getPassword());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return newStudent;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Student> findAll() {
		List<Student> studentsList = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from scientists";
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				Student student = new Student();
				student.setStudentId(resultSet.getString("student_id"));
				student.setFirstName(resultSet.getString("first_name"));
				student.setLastName(resultSet.getString("last_name"));
				student.setEmail(resultSet.getString("email"));
				student.setUsername(resultSet.getString("username"));
				student.setPassword(resultSet.getString("password"));

				studentsList.add(student);
			}

			return studentsList;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Student findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Student updatedStudent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
