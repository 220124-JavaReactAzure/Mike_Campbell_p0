package com.revature.course_registration.menus;

import java.io.BufferedReader;
import com.revature.course_registration.exceptions.InvalidRequestException;
import com.revature.course_registration.models.User;
import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;

public class RegisterMenu extends Menu {

	UserService studentService;

	public RegisterMenu(BufferedReader consoleReader, MenuRouter router, UserService studentService) {
		super("Register", "/register", consoleReader, router);
		this.studentService = studentService;
	}

	@Override
	public void render() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("The User selected Register");

		User user;

		// Things to obtain from user: first name, last name, email,username, password

		System.out.println("Please provided us with some basic information");
		System.out.print("First Name: ");
		String firstName = consoleReader.readLine();

		System.out.print("Last Name: ");
		String lastName = consoleReader.readLine();

		System.out.print("Email: ");
		String email = consoleReader.readLine();

		System.out.print("Username: ");
		String username = consoleReader.readLine();

		System.out.print("Password: ");
		String password = consoleReader.readLine();

		System.out.print("Instructor? Y/N: ");
		String instructorCheck = consoleReader.readLine();

		if (instructorCheck.equals("y") || instructorCheck.equals("Y") || instructorCheck.equals("yes")
				|| instructorCheck.equals("Yes")) {
			System.out.print("Please enter instructor code: ");
			// check for valid instructor code
			// for now just 1 for instructor
			String instructorCode = consoleReader.readLine();
			if (instructorCode.equals("1")) {
				user = new User(firstName, lastName, email, username, password, 1);
			} else {
				System.out.println("Invalid Instructor Code. Returning to Welcome Menu.");
				router.transfer("/welcome");
				return;
			}

		} else if (instructorCheck.equals("n") || instructorCheck.equals("N") || instructorCheck.equals("no")
				|| instructorCheck.equals("No")) {
			user = new User(firstName, lastName, email, username, password, 0);
		} else {
			System.out.println("Invalid selection, returning to Welcome Menu.");
			router.transfer("/welcome");
			return;
		}

		try {
			studentService.registerNewUser(user);
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("YOU HAVE PROVIDED INVALID DATA PLEASE TRY AGAIN\n\n\n");

			router.transfer("/welcome");
		}
	}

}
