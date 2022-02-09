package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;
import com.revature.course_registration.util.logging.Logger;

public class ProfileMenu extends Menu {
	private final UserService userService;
	private final Logger logger;

	public ProfileMenu(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Profile", "/profile", consoleReader, router);
		this.userService = userService;
		this.logger = Logger.getLogger(true);
	}

	@Override
	public void render() throws Exception {
		
		String menu = "1) View my profile\n" + 
					"2) Edit my profile\n" + 
					"3) Back\n" + 
					"> ";

		System.out.print(menu);

		String userSelection = consoleReader.readLine();

		switch (userSelection) {
		case "1":
			System.out.println("My profile: ");
			System.out.println(userService.getSessionUser().toString());
			//TODO use printf to format output of profile
			//System.out.printf(userSelection, null).println();
			router.transfer("/profile");
			break;
		case "2":
			System.out.println("Edit my profile");
			router.transfer("/user-profile-edit");
			break;
		case "3":
			router.transfer("/dashboard");
			break;
		default:
			System.out.println("The user made an invalid selection.");
		}

	}
}
