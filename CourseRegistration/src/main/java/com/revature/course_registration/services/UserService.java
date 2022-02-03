package main.java.com.revature.course_registration.services;

import main.java.com.revature.course_registration.daos.UserDAO;
import main.java.com.revature.course_registration.exceptions.InvalidRequestException;
import main.java.com.revature.course_registration.models.User;

public class UserService {

	private final UserDAO userDao;
	
	public UserService(UserDAO userDao) {
		this.userDao = userDao;
		
	}

	public boolean registerNewStudent(User newUser) {
		if (!isStudentValid(newUser)) {
			throw new InvalidRequestException("Invalid user data provider");
		}

		// TODO: Write logic that verifies the new users information isn't duplicated
		// in the system
		userDao.create(newUser);

		return true;
	}

	// TODO: Implement authentication
	public User authenticateStudent(String username, String password) {

		// check username and password against recorded entries in database
		userDao.findByUsernameAndPassword(username, password);

		return null;
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
		return newUser.getPassword() != null || !newUser.getPassword().trim().equals("");

	}

}