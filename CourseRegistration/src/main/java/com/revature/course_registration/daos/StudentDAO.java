package main.java.com.revature.course_registration.daos;

import java.io.File;
import java.io.FileWriter;

import com.revature.course_registration.models.Student;
import com.revature.course_registration.util.List;

public class StudentDAO implements CrudDAO<Student> {

	
	@Override
	public Student create(Student newStudent) {
		
		File studentsFile = new File("resources/data.txt");
		
		try(FileWriter fileWriter = new FileWriter(studentsFile, true);) {
			fileWriter.write(newStudent.toFileString() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error persistin user to file");
		}
		return null;
	}
	
	
	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
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
