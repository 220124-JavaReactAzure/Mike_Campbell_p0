package com.revature.course_registration.services;

import com.revature.course_registration.daos.UserDAO;
import com.revature.course_registration.exceptions.InvalidRequestException;
import com.revature.course_registration.exceptions.AuthenticationException;
import com.revature.course_registration.models.User;
import com.revature.course_registration.util.collections.List;
import com.revature.course_registration.exceptions.ResourcePersistenceException;

public class UserService {

	private final UserDAO userDao;
	private User sessionUser;

	public UserService(UserDAO userDao) {
		this.userDao = userDao;
		this.sessionUser = null;

	}

	public User getSessionUser() {
		return sessionUser;
	}

	public User registerNewUser(User newUser) {
		if (!isUserValid(newUser)) {
			throw new InvalidRequestException("Invalid user data provider");
		}

		// logic that verifies the new users information isn't duplicated in the system
		boolean usernameAvailable = userDao.findByUsername(newUser.getUsername()) == null;
		boolean emailAvailable = userDao.findByUsername(newUser.getEmail()) == null;

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

		User persistedUser = userDao.create(newUser);

		if (persistedUser == null) {
			throw new ResourcePersistenceException("The user could not be persisted");
		}

		return persistedUser;
	}

	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	public void authenticateUser(String username, String password) {

		if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException(
					"Either username or password is an invalid entry. Please try logging in again");
		}

		User authenticatedUser = userDao.findByUsernameAndPassword(username, password);

		if (authenticatedUser == null) {
			throw new AuthenticationException(
					"Authentication Failed: Information provided does not match a user in our database.");
		}
		sessionUser = authenticatedUser;
	}

	public boolean isUserValid(User newUser) {
		//could simplify code by using pattern matching tools
		
		if (newUser == null)
			return false;
		if (newUser.getFirstName() == null || newUser.getFirstName().trim().equals(""))
			return false;
		if (newUser.getLastName() == null || newUser.getLastName().trim().equals(""))
			return false;
		if (newUser.getEmail() == null || newUser.getEmail().trim().equals(""))
			return false;
		if (newUser.getUsername() == null || newUser.getUsername().trim().equals(""))
			return false;
		if (newUser.getPassword() == null && newUser.getPassword().trim().equals(""))
			return false;
		//prohibit special characters that may allow sql injection
		if (newUser.getFirstName().contains(";") || newUser.getLastName().contains(";")
				|| newUser.getEmail().contains(";") || newUser.getUsername().contains(";")
				|| newUser.getPassword().contains(";")) {
			return false;
		}
		if (newUser.getFirstName().contains("\"") || newUser.getLastName().contains("\"")
				|| newUser.getEmail().contains("\"") || newUser.getUsername().contains("\"")
				|| newUser.getPassword().contains("\"")) {
			return false;
		}

		return true;

	}

	public void logout() {
		sessionUser = null;
	}

	public boolean isSessionActive() {
		return sessionUser != null;
	}

	public void updateUser(User updatedUser) {
		if (!userDao.update(updatedUser)) {
			throw new ResourcePersistenceException("Failure updating user.");
		}
		sessionUser = updatedUser;
	}
}