package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.models.Course;
import com.revature.course_registration.models.Registration;
import com.revature.course_registration.services.CourseService;
import com.revature.course_registration.services.RegistrationService;
import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;
import com.revature.course_registration.util.collections.List;
import com.revature.course_registration.util.logging.Logger;

public class StudentCourseMenu extends Menu {

	private final UserService userService;
	private final CourseService courseService;
	private final RegistrationService registrationService;
	private final Logger logger;
	
	public StudentCourseMenu(BufferedReader consoleReader, MenuRouter router,
			UserService userService, CourseService courseService, RegistrationService registrationService) {
		super("Student Course Menu", "/student-course-menu", consoleReader, router);
		this.userService = userService;
		this.courseService = courseService;
		this.registrationService = registrationService;
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
			System.out.println("My Courses:");
			List<Course> userCourses = courseService.findUserCourses(userService.getSessionUser());
			//TODO: print header row for readability
			//System.out.println();
			for (int i = 0; i < userCourses.size(); i++) {
				System.out.println(userCourses.get(i).toString());				
			}
			//router.transfer("/student-course-menu");
			break;
		case "2":
			System.out.println("Available Courses:");
			
			List<Course> availableCourses = courseService.findOpenCourses();
			
			//TODO: add print header row for readability
			for (int i = 0; i < availableCourses.size(); i++) {
				System.out.println(availableCourses.get(i).toString());				
			}
			//router.transfer("/student-course-menu");
			break;
		case "3":
			System.out.print("Enter course number for enrollment: ");
			String courseSelection = consoleReader.readLine();
			Registration newRegistration = new Registration(Integer.parseInt(userService.getSessionUser().getUserId()), Integer.parseInt(courseSelection));
			if (registrationService.enrollCourse(newRegistration) != null) {
				System.out.println("Enrollment in course successful.");
				router.transfer("/student-course-menu");
			}
			else {
				System.out.println("Enrollment in course failed. Either the course is full or you are already enrolled.");
				router.transfer("/student-course-menu");
			}
			
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
