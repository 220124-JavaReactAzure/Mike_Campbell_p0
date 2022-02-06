package com.revature.course_registration.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.revature.course_registration.daos.CourseDAO;
import com.revature.course_registration.daos.UserDAO;
import com.revature.course_registration.services.CourseService;
import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.logging.Logger;
import com.revature.course_registration.menus.RegisterMenu;
import com.revature.course_registration.menus.StudentCourseMenu;
import com.revature.course_registration.menus.WelcomeMenu;
import com.revature.course_registration.menus.DashboardMenu;
import com.revature.course_registration.menus.FacultyCourseMenu;
import com.revature.course_registration.menus.LoginMenu;
import com.revature.course_registration.menus.ProfileMenu;

public class AppState {

	private final Logger logger;
	private static boolean isRunning;
	private final MenuRouter router;
	
	public AppState() {
		
		logger = Logger.getLogger(true);
		logger.log("Application initiliazing....");
		
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		
		//instantiate services and DAOs
		CourseDAO courseDAO = new CourseDAO();
		CourseService courseService = new CourseService(courseDAO);
		UserDAO userDAO = new UserDAO();
		UserService userService = new UserService(userDAO);
		
		//instantiate menus
		router.addMenu(new WelcomeMenu(consoleReader, router));
		router.addMenu(new RegisterMenu(consoleReader, router, userService));
		router.addMenu(new LoginMenu(consoleReader, router, userService));
		router.addMenu(new DashboardMenu(consoleReader, router, userService, courseService));
		router.addMenu(new StudentCourseMenu(consoleReader, router, userService, courseService));
		router.addMenu(new FacultyCourseMenu(consoleReader, router, userService, courseService));
		router.addMenu(new ProfileMenu(consoleReader, router, userService));
		
		logger.log("Application initialized successfully.");
	}
	
	
	public void startup() {
		try {
			while(isRunning) {
				router.transfer("/welcome");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.log(e.getStackTrace().toString());
		}
	}
	
	public static void shutdown() {
		isRunning = false;
	}
	
}
