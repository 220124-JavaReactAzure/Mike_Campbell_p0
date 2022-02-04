package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;

public class DashboardMenu extends Menu {

	private final UserService userService;

	public DashboardMenu(BufferedReader consoleReader, MenuRouter router,
			UserService userService) {
		super("Dashboard", "/dashboard", consoleReader, router);
		this.userService = userService;
	}

	@Override
	public void render() throws Exception {

		// TODO: Work on implementing sessions & dashboard functionality
		
		// check for user permission to see if they can modify courses or enroll in
		// courses (instructor vs student)
		
		String menu = "1) View/edit my profile information\n" + 
					"2) View/edit/create course(s)\n" + 
					"3) Logout\n" + 
					"> ";

		System.out.print(menu);

		String userSelection = consoleReader.readLine();

		switch (userSelection) {
		case "1":
			System.out.println("View/edit profile selected");
			router.transfer("/user-profile-edit");
			break;
		case "2":
			System.out.println("View/edit/create courses selected");
			//TODO: route to course menu
			break;
		case "3":
			// TODO: Implement logout of user account
			break;
		default:
			System.out.println("The user made an invalid selection");
		}
	}

}
