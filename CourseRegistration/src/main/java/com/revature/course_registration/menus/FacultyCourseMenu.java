package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.models.Course;
import com.revature.course_registration.services.CourseService;
import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;
import com.revature.course_registration.util.collections.List;
import com.revature.course_registration.util.logging.Logger;

public class FacultyCourseMenu extends Menu {

	private final UserService userService;
	private final CourseService courseService;
	private final Logger logger;

	public FacultyCourseMenu(BufferedReader consoleReader, MenuRouter router,
			UserService userService, CourseService courseService) {
		super("Faculty Course Menu", "/faculty-course-menu", consoleReader, router);
		this.userService = userService;
		this.courseService = courseService;
		this.logger = Logger.getLogger(true);
	}

	@Override
	public void render() throws Exception {

		// TODO: Work on implementing sessions & dashboard functionality
		
		// check for user permission to see if they can modify courses or enroll in
		// courses (instructor vs student)
		
		String menu = "1) View My Courses\n" + 
					"2) Create course\n" + 
					"3) Remove Course\n" + 
					"4) Modify Course\n" +
					"5) <<Back\n" +
					"> ";

		System.out.print(menu);

		String userSelection = consoleReader.readLine();

		switch (userSelection) {
		case "1":
			System.out.println("My Courses: ");
			List<Course> userCourses = courseService.findUserCourses(userService.getSessionUser());
			// TODO: print header row for readability
			// System.out.println();
			for (int i = 0; i < userCourses.size(); i++) {
				System.out.println(userCourses.get(i).toString());
			}
			router.transfer("/faculty-course-menu");
			break;
		case "2":
			System.out.println("Enter Course Information");
			Course newCourse = new Course();
			
			System.out.print("Course Name: ");
			String courseData = consoleReader.readLine();
			newCourse.setCourseName(courseData);
			
			System.out.print("Course Description: ");
			courseData = consoleReader.readLine();
			newCourse.setCourseDescription(courseData);
			
			newCourse.setCourseInstructor(Integer.parseInt(userService.getSessionUser().getUserId()));
			
			System.out.print("Course Maximum Seats: ");
			courseData = consoleReader.readLine();
			newCourse.setCourseSeatsMAX(Integer.parseInt(courseData));
			
			System.out.print("Course Seats Available: ");
			courseData = consoleReader.readLine();
			newCourse.setCourseSeatsTaken(Integer.parseInt(courseData));
			
			if(newCourse.getCourseSeatsTaken() == newCourse.getCourseSeatsMAX()) {
				newCourse.setFull(true);
			}
			else {
				newCourse.setFull(false);
			}
			courseService.createNewCourse(newCourse);
			logger.log("New course added to catalog.");
			router.transfer("/faculty-course-menu");
			
			break;
		case "3":
			System.out.println("Enter Course Number \n(COURSE WILL BE REMOVED)");
			System.out.print("Course Number: ");
			String deletedCourse = consoleReader.readLine();
			courseService.deleteCourse(deletedCourse);
			logger.log("Course removed from catalog. All students unenrolled from deleted course.");
			router.transfer("/faculty-course-menu");
			break;
		case "4":
			router.transfer("/modify-course-menu");
			break;
		case "5":
			router.transfer("/dashboard");
			break;
		default:
			System.out.println("The user made an invalid selection");
		}
	}

}
