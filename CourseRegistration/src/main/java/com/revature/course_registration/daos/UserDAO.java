package main.java.com.revature.course_registration.daos;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import main.java.com.revature.course_registration.models.User;
import main.java.com.revature.course_registration.util.List;
import main.java.com.revature.course_registration.util.ArrayList;
import main.java.com.revature.course_registration.util.ConnectionFactory;

public class UserDAO implements CrudDAO<User> {

	// TODO: Implement Authentication
	public User findByUsernameAndPassword(String username, String password) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	// TODO: Implement FindByEmail
	public User findByEmail(String email) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			// check user table first, instructor has higher permissions, so if they used to
			// be a student, they shouldn't be locked out of those permissions
			String sql = "select * from instructor where email = ".concat(email);
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			if (resultSet != null) {
				User foundUser = new User();

				foundUser.setUserId(resultSet.getString("user_id"));
				foundUser.setFirstName(resultSet.getString("first_name"));
				foundUser.setLastName(resultSet.getString("last_name"));
				foundUser.setEmail(resultSet.getString("email"));
				foundUser.setUsername(resultSet.getString("username"));
				foundUser.setPassword(resultSet.getString("password"));
				foundUser.setUserPermission((resultSet.getInt("permission")));
				return foundUser;
			} 
			//if no instructor match, check student table
			else {
				sql = "select * from student where email = ".concat(email);
				s = conn.createStatement();

				resultSet = s.executeQuery(sql);

				if (resultSet != null) {
					User foundUser = new User();

					foundUser.setUserId(resultSet.getString("user_id"));
					foundUser.setFirstName(resultSet.getString("first_name"));
					foundUser.setLastName(resultSet.getString("last_name"));
					foundUser.setEmail(resultSet.getString("email"));
					foundUser.setUsername(resultSet.getString("username"));
					foundUser.setPassword(resultSet.getString("password"));
					foundUser.setUserPermission((resultSet.getInt("permission")));
					return foundUser;
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// TODO: Implement FindByUsername
	public User findByUsername(String username) {
		return null;
	}

	@Override
	public User create(User newStudent) {

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
	public List<User> findAll() {
		List<User> studentsList = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from scientists";
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				User student = new User();
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
	public User findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User updatedStudent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
