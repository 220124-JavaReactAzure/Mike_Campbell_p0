package main.java.com.revature.course_registration.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import main.java.com.revature.course_registration.daos.UserDAO;
import main.java.com.revature.course_registration.services.UserService;

import main.java.com.revature.course_registration.menus.RegisterMenu;
import main.java.com.revature.course_registration.menus.WelcomeMenu;
import main.java.com.revature.course_registration.menus.DashboardMenu;
import main.java.com.revature.course_registration.menus.LoginMenu;

public class AppState {

	private static boolean isRunning;
	private final MenuRouter router;
	
	public AppState() {
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		
		UserDAO userDAO = new UserDAO();
		UserService userService = new UserService(userDAO);
		router.addMenu(new WelcomeMenu(consoleReader, router));
		router.addMenu(new RegisterMenu(consoleReader, router, userService));
		router.addMenu(new LoginMenu(consoleReader, router, userService));
		router.addMenu(new DashboardMenu(consoleReader, router, userService));
	}
	
	
	public void startup() {
		try {
			while(isRunning) {
				router.transfer("/welcome");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void shutdown() {
		isRunning = false;
	}
	
}
