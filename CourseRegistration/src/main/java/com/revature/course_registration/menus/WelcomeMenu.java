package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.util.MenuRouter;
import static com.revature.course_registration.util.AppState.shutdown;

public class WelcomeMenu extends Menu{

	public WelcomeMenu(BufferedReader consoleReader, MenuRouter router) {
		super("Welcome", "/welcome", consoleReader, router);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render() throws Exception {
		
		System.out.print(
				"\nWelcome to Course Registration!\nPlease Select an option[1-3]\n" + "1) Login\n" + "2) Register\n" + "3) Exit\n" + "> ");
		
		String userSelection = consoleReader.readLine();

		switch (userSelection) {
		case "1":
			router.transfer("/login");
			break;
		case "2":
			router.transfer("/register");
			break;
		case "3":
			shutdown();
			break;
		default:
			System.out.println("Invalid entry. Please select an option[1-3]");
			break;
		}
		
	}

}
