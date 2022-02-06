package com.revature.course_registration.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.revature.course_registration.models.User;
import com.revature.course_registration.util.logging.Logger;
import com.revature.course_registration.util.ConnectionFactory;
import com.revature.course_registration.util.collections.ArrayList;
import com.revature.course_registration.util.collections.List;

public class UserDAO implements CrudDAO<User> {

	private final Logger logger = Logger.getLogger(true);

	// Authenticate by username and password
	public User findByUsernameAndPassword(String username, String password) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from users where user_username = ? and user_password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				User foundUser = new User();
				foundUser.setUserId(rs.getString("user_id"));
				foundUser.setFirstName(rs.getString("user_fname"));
				foundUser.setLastName(rs.getString("user_lname"));
				foundUser.setEmail(rs.getString("user_email"));
				foundUser.setUsername(rs.getString("user_username"));
				foundUser.setPassword(rs.getString("user_password"));
				foundUser.setUserPermission((rs.getInt("user_permission")));

				return foundUser;
			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return null;
	}

	public User findByEmail(String email) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from users where user_email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				User foundUser = new User();

				foundUser.setUserId(resultSet.getString("user_id"));
				foundUser.setFirstName(resultSet.getString("user_fname"));
				foundUser.setLastName(resultSet.getString("user_lname"));
				foundUser.setEmail(resultSet.getString("user_email"));
				foundUser.setUsername(resultSet.getString("user_username"));
				foundUser.setPassword(resultSet.getString("user_password"));
				foundUser.setUserPermission((resultSet.getInt("user_permission")));
				return foundUser;
			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}
		return null;
	}

	public User findByUsername(String username) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from users where user_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				User foundUser = new User();

				foundUser.setUserId(resultSet.getString("user_id"));
				foundUser.setFirstName(resultSet.getString("user_fname"));
				foundUser.setLastName(resultSet.getString("user_lname"));
				foundUser.setEmail(resultSet.getString("user_email"));
				foundUser.setUsername(resultSet.getString("user_username"));
				foundUser.setPassword(resultSet.getString("user_password"));
				foundUser.setUserPermission((resultSet.getInt("user_permission")));
				return foundUser;

			}
		} catch (SQLException e) {
			logger.log(e.getSQLState());

		}
		return null;
	}

	@Override
	public User create(User newUser) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			// random unique ID generation & assignment
			// newStudent.setScientistId(UUID.randomUUID().toString());

			// TODO use returning keyword to pop ID back to course object

			String sql = "insert into users (user_fname, user_lname, user_email, user_username, user_password, user_permission) values (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			// ps.setString(1, newUser.getUserId());
			ps.setString(1, newUser.getFirstName());
			ps.setString(2, newUser.getLastName());
			ps.setString(3, newUser.getEmail());
			ps.setString(4, newUser.getUsername());
			ps.setString(5, newUser.getPassword());
			ps.setInt(6, newUser.getUserPermission());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return newUser;
			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from users";
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getString("user_id"));
				user.setFirstName(resultSet.getString("user_fname"));
				user.setLastName(resultSet.getString("user_lname"));
				user.setEmail(resultSet.getString("user_email"));
				user.setUsername(resultSet.getString("user_username"));
				user.setPassword(resultSet.getString("user_password"));
				user.setUserPermission(resultSet.getInt("user_permission"));

				userList.add(user);
			}

			return userList;

		} catch (SQLException e) {
			logger.log(e.getSQLState());
		}

		return null;
	}

	@Override
	public User findById(String id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from users where user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				User foundUser = new User();

				foundUser.setUserId(resultSet.getString("user_id"));
				foundUser.setFirstName(resultSet.getString("user_fname"));
				foundUser.setLastName(resultSet.getString("user_lname"));
				foundUser.setEmail(resultSet.getString("user_email"));
				foundUser.setUsername(resultSet.getString("user_username"));
				foundUser.setPassword(resultSet.getString("user_password"));
				foundUser.setUserPermission((resultSet.getInt("user_permission")));
				return foundUser;
			}

		} catch (SQLException e) {
			logger.log(e.getSQLState());

		}
		return null;
	}

	@Override
	public boolean update(User updatedUser) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "update users set user_fname = ?, user_lname = ?, user_email = ?, "
					+ "user_username = ?, user_password = ? where user_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			// overwrite
			ps.setString(1, updatedUser.getFirstName());
			ps.setString(2, updatedUser.getLastName());
			ps.setString(3, updatedUser.getEmail());
			ps.setString(4, updatedUser.getUsername());
			ps.setString(5, updatedUser.getPassword());
			// where
			ps.setString(6, updatedUser.getUserId());

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
		// TODO Auto-generated method stub
		return false;
	}
}
