package com.revature.course_registration.services;

import com.revature.course_registration.daos.RegistrationDAO;
import com.revature.course_registration.exceptions.ResourcePersistenceException;
import com.revature.course_registration.models.Registration;

public class RegistrationService {
	private final RegistrationDAO registrationDAO;
	
	public RegistrationService(RegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	
	public Registration enrollCourse(Registration newRegistration) {
		
		
		//check for duplicate registration
		boolean enrollmentExists = registrationDAO.findEnrollment(newRegistration.getStudent_id(), newRegistration.getCourse_id()) == null;
		if(enrollmentExists) {
			throw new ResourcePersistenceException("This student is already enrolled in specified course."); 
		}
		Registration persistedRegistration = registrationDAO.create(newRegistration);
		
		if(persistedRegistration == null) {
			throw new ResourcePersistenceException("The enrollment could not be persisted.");
		}
		return persistedRegistration;
	}

	public void dropCourse(String courseSelection) {
		registrationDAO.delete(courseSelection);
		
	}
	
}
