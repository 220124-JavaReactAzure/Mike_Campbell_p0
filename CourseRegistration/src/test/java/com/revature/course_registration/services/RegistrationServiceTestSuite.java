package com.revature.course_registration.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.course_registration.daos.RegistrationDAO;
import com.revature.course_registration.models.Registration;

public class RegistrationServiceTestSuite {

	
	
	RegistrationService sut;
	RegistrationDAO mockRegistrationDAO;
	
	@Before
	public void testPrep() {
		mockRegistrationDAO = mock(RegistrationDAO.class);
		sut = new RegistrationService(mockRegistrationDAO);
	}
	@Test
	public void test_isRegistrationValid_returnsTrue_givenValidRegistration() {
		Registration validRegistration = new Registration(1,1);
		
		boolean actualResult = sut.isRegistrationValid(validRegistration);
		
		
		
		
	}

}
