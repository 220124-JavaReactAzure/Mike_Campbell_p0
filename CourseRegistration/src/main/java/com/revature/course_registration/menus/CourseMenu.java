package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;

public class CourseMenu extends Menu {

	private final UserService userService;

	public CourseMenu(String name, String route, BufferedReader consoleReader, MenuRouter router,
			UserService userService) {
		super(name, route, consoleReader, router);
		this.userService = userService;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render() throws Exception {

		// TODO: Work on implementing sessions & dashboard functionality
		
		// check for user permission to see if they can modify courses or enroll in
		// courses (instructor vs student)
		
		String menu = "1) View My Courses\n" + 
					"2) Add course\n" + 
					"3) Remove Course\n" + 
					"4) Modify Course\n" +
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
