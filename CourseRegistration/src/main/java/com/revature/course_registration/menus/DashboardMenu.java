package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;

public class DashboardMenu extends Menu {

	private final UserService userService;

	public DashboardMenu(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Dashboard", "/dashboard", consoleReader, router);
		this.userService = userService;
	}

	@Override
	public void render() throws Exception {

		// TODO: Work on implementing sessions & dashboard functionality

		String menu = "1) View/edit my profile information\n" + 
					"2) Manage Courses\n" +
					"3) View Course Catalog\n" + 
					"4) Logout\n" + 
					"> ";

		System.out.print(menu);

		String userSelection = consoleReader.readLine();

		switch (userSelection) {
		case "1":
			System.out.println("View/edit profile selected");
			router.transfer("/user-profile-edit");
			break;
		case "2":
			if (userService.getSessionUser().getUserPermission() == 0) {
				router.transfer("/student-course-menu");
			} else if (userService.getSessionUser().getUserPermission() == 1) {
				router.transfer("/faculty-course-menu");
			} else {
				router.transfer("/welcome");
			}

			break;
		case "3":
			// TODO: Implement view course catalog
			break;
		case "4":
			// TODO: Implement logout
			break;
		default:
			System.out.println("The user made an invalid selection");
		}
	}

}
