package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;
import com.revature.course_registration.util.logging.Logger;
import com.revature.course_registration.exceptions.AuthenticationException;


public class LoginMenu extends Menu {

	private final UserService userService;
	private final Logger logger;

	public LoginMenu(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Login", "/login", consoleReader, router);
		this.userService = userService;
		logger = Logger.getLogger(true);

	}

	@Override
	public void render() throws Exception {
		System.out.println("\nPlease enter your credentials for you account.");
		System.out.print("Username: ");
		String username = consoleReader.readLine();
		System.out.print("Password: ");
		String password = consoleReader.readLine();

		try {
			userService.authenticateUser(username, password);
			router.transfer("/dashboard");
		} catch (AuthenticationException e) {
			//logger.log("Incorrect credentials provided! No matching user account found.");
			logger.log(e.getMessage());
			router.transfer("/welcome");
		}

	}

}
