package main.java.com.revature.course_registration.services;

import main.java.com.revature.course_registration.daos.UserDAO;
import main.java.com.revature.course_registration.exceptions.InvalidRequestException;
import main.java.com.revature.course_registration.exceptions.AuthenticationException;
import main.java.com.revature.course_registration.models.User;
import main.java.com.revature.course_registration.exceptions.ResourcePersistenceException;


public class UserService {

	private final UserDAO userDao;

	public UserService(UserDAO userDao) {
		this.userDao = userDao;

	}

	public User registerNewStudent(User newUser) {
		if (!isStudentValid(newUser)) {
			throw new InvalidRequestException("Invalid user data provider");
		}

		// TODO: Write logic that verifies the new users information isn't duplicated
		// in the system
		User persistedUser = userDao.create(newUser);

		if (persistedUser == null) {
			throw new ResourcePersistenceException("The user could not be persisted");
		}

		return persistedUser;
	}

	// TODO: Implement authentication
	public User authenticateUser(String username, String password) {
		
		if(username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
		}
		
		User authenticatedUser = userDao.findByUsernameAndPassword(username, password);
		
		if(authenticatedUser == null) {
			throw new AuthenticationException("Unauthenticated user, information provided was not found in our database.");
		}
		return authenticatedUser;
	}

	public boolean isStudentValid(User newUser) {
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
		return newUser.getPassword() != null && !newUser.getPassword().trim().equals("");

	}

}