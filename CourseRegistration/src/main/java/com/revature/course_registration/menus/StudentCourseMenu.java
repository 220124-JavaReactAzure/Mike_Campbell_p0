package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;

public class StudentCourseMenu extends Menu {

	private final UserService userService;

	public StudentCourseMenu(BufferedReader consoleReader, MenuRouter router,
			UserService userService) {
		super("Student Course Menu", "/student-course-menu", consoleReader, router);
		this.userService = userService;
		
	}

	@Override
	public void render() throws Exception {

		// TODO: Work on implementing sessions & dashboard functionality
		
		String menu = "1) View My Courses\n" + 
					"2) View Available Courses\n" + 
					"3) Add course\n" + 
					"4) Remove Course\n" + 
					"5) <<Back\n" +
					"> ";

		System.out.print(menu);

		String userSelection = consoleReader.readLine();

		switch (userSelection) {
		case "1":
			System.out.println("My Courses: ");
			//TODO: Implement view courses
			break;
		case "2":
			System.out.println("Enter Course Information");
			//TODO: Implement add course
			break;
		case "3":
			System.out.println("Enter Course Number \n(COURSE WILL BE REMOVED)");
			// TODO: Implement remove course
			break;
		case "4":
			System.out.println("Enter Course Number");
			// TODO: Implement modify course
			break;
		case "5":
			//TODO: Implement return to last menu
			break;
		default:
			System.out.println("The user made an invalid selection");
		}
	}

}
