package main.java.com.revature.course_registration.menus;

import java.io.BufferedReader;

import main.java.com.revature.course_registration.models.User;
import main.java.com.revature.course_registration.services.UserService;
import main.java.com.revature.course_registration.util.MenuRouter;
import main.java.com.revature.course_registration.exceptions.AuthenticationException;


public class LoginMenu extends Menu {

	private final UserService userService;

	public LoginMenu(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Login", "/login", consoleReader, router);
		this.userService = userService;

	}

	@Override
	public void render() throws Exception {
		System.out.println("Please enter your credentials for you account.");
		System.out.print("Username: ");
		String username = consoleReader.readLine();
		System.out.print("Password: ");
		String password = consoleReader.readLine();

		try {
			User authenticatedUser = userService.authenticateUser(username, password);
			System.out.println("Validated Login, matches user: " + authenticatedUser.getUsername());
			router.transfer("/dashboard");
		} catch (AuthenticationException e) {
			System.out.println("Incorrect credentials provided! No matching user account found.");
		}

	}

}
