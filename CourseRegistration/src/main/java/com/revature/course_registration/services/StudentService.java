package main.java.com.revature.course_registration.services;

import java.io.File;
import java.io.FileWriter;
import com.revature.course_registration.daos.StudentDAO;
import com.revature.course_registration.exceptions.InvalidRequestException;
import com.revature.course_registration.models.Student;

public class StudentService {

	private StudentDAO studentDao = new StudentDAO();

	public boolean registerNewStudent(Student newStudent) {
		if (!isStudentValid(newStudent)) {
			throw new InvalidRequestException("Invalid user data provider");
		}

		// TODO: Write logic that verifies the new users information isn't duplicated
		// in the system
		studentDao.create(newStudent);

		return true;
	}

	// TODO: Implement authentication
	public Student authenticateStudent(String username, String password) {

		// check username and password against recorded entries in database
		studentDao.findByUsernameAndPassword(username, password);

		return null;
	}

	public boolean isStudentValid(Student newStudent) {
		if (newStudent == null)
			return false;
		if (newStudent.getFirstName() == null || newStudent.getFirstName().trim().equals(""))
			return false;
		if (newStudent.getLastName() == null || newStudent.getLastName().trim().equals(""))
			return false;
		if (newStudent.getEmail() == null || newStudent.getEmail().trim().equals(""))
			return false;
		if (newStudent.getUsername() == null || newStudent.getUsername().trim().equals(""))
			return false;
		return newStudent.getPassword() != null || !newStudent.getPassword().trim().equals("");

	}

}