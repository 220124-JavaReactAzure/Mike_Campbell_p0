package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.services.CourseService;
import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;
import com.revature.course_registration.util.logging.Logger;

public class DashboardMenu extends Menu {

	private final UserService userService;
	private final CourseService courseService;
	private final Logger logger;
	
	public DashboardMenu(BufferedReader consoleReader, MenuRouter router, UserService userService, CourseService courseService) {
		super("Dashboard", "/dashboard", consoleReader, router);
		this.userService = userService;
		this.courseService = courseService;
		this.logger = Logger.getLogger(true);
	}

	@Override
	public void render() throws Exception {

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
				logger.log("User permission error. Returning to Welcome Menu...");
				router.transfer("/welcome");
			}

			break;
		case "3":
			// TODO: Implement view ALL courses
			break;
		case "4":
			userService.logout();
			router.transfer("/wecome");
			break;
		default:
			System.out.println("The user made an invalid selection.");
		}
	}

}
