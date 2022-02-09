package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.exceptions.InvalidRequestException;
import com.revature.course_registration.models.Course;
import com.revature.course_registration.services.CourseService;
import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;
import com.revature.course_registration.util.logging.Logger;

public class ModifyCourseMenu extends Menu {
	private final UserService userService;
	private final CourseService courseService;
	private final Logger logger;

	public ModifyCourseMenu(BufferedReader consoleReader, MenuRouter router, UserService userService,
			CourseService courseService) {
		super("Modify Course Menu", "/modify-course-menu", consoleReader, router);
		this.courseService = courseService;
		this.userService = userService;
		this.logger = Logger.getLogger(true);
	}

	@Override
	public void render() throws Exception {

		System.out.print("Enter course number: ");
		String courseId = consoleReader.readLine();
		
		Course updatedCourse = courseService.findCourseByID(courseId);
		
		if(updatedCourse.getCourseInstructor() != Integer.parseInt(userService.getSessionUser().getUserId())) {
			throw new InvalidRequestException("You do not have permission to modify another instructor's course.");
		}

		String menu = "1) Change Course Name\n" + "2) Change Course Description\n" + "3) Change Course Maximum Seats\n"
				+ "4) <<Back\n" + "> ";

		System.out.print(menu);

		String userSelection = consoleReader.readLine();
		String courseData;
		

		switch (userSelection) {
		case "1":
			System.out.print("New course name: ");
			courseData = consoleReader.readLine();
			updatedCourse.setCourseName(courseData);
			courseService.updateCourse(updatedCourse);
			logger.log("Course name updated.");
			router.transfer("/faculty-course-menu");
			break;
		case "2":
			System.out.print("New course description: ");
			courseData = consoleReader.readLine();
			updatedCourse.setCourseDescription(courseData);
			courseService.updateCourse(updatedCourse);
			logger.log("Course description updated.");
			router.transfer("/faculty-course-menu");
			break;
		case "3":
			System.out.print("New course maximum seats: ");
			courseData = consoleReader.readLine();
			updatedCourse.setCourseSeatsMAX(Integer.parseInt(courseData));
			courseService.updateCourse(updatedCourse);
			logger.log("Course maximum seats updated.");
			router.transfer("/faculty-course-menu");
			break;
		case "4":
			router.transfer("/faculty-course-menu");
			break;
		default:
			System.out.println("The user made an invalid selection");
		}

	}

}
