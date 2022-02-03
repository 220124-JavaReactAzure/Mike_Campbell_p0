package main.java.com.revature.course_registration.menus;

import java.io.BufferedReader;
import main.java.com.revature.course_registration.exceptions.InvalidRequestException;
import main.java.com.revature.course_registration.models.User;
import main.java.com.revature.course_registration.services.UserService;
import main.java.com.revature.course_registration.util.MenuRouter;


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

//				System.out.printf("Provided by user: firstName: %s, lastName: %s, email: %s, username: %s, password: %s", firstName, lastName, email, username, password).println();

				User student = new User(firstName, lastName, email, username, password);

				System.out.printf("Provided by user: %s\n", student.toString()).println();

				try {
					studentService.registerNewStudent(student);
				} catch (InvalidRequestException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace(); 
					System.out.println("YOU HAVE PROVIDED INVALID DATA PLEASE TRY AGAIN\n\n\n");

					router.transfer("/welcome");
				}
			}
		
	}
