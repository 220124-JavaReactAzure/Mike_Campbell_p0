package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.services.CourseService;
import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;
import com.revature.course_registration.util.logging.Logger;

public class StudentCourseMenu extends Menu {

	private final UserService userService;
	private final CourseService courseService;
	private final Logger logger;
	
	public StudentCourseMenu(BufferedReader consoleReader, MenuRouter router,
			UserService userService, CourseService courseService) {
		super("Student Course Menu", "/student-course-menu", consoleReader, router);
		this.userService = userService;
		this.courseService = courseService;
		this.logger = Logger.getLogger(true);
		
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
			System.out.println("My Courses:\n");
			//TODO: Implement view my courses
			break;
		case "2":
			System.out.println("Course Catalog:\n");
			//TODO: Implement view courses with open seats
			break;
		case "3":
			System.out.println("Enroll in a new course.\nEnter Course Number: ");
			// TODO:  Implement add course (insert row enrollment)
			break;
		case "4":
			System.out.println("Drop a course.\nEnter Course Number: ");
			// TODO: Implement remove course (delete row enrollment)
			break;
		case "5":
			router.transfer("/dashboard");
			break;
		default:
			System.out.println("The user made an invalid selection");
		}
	}

}
