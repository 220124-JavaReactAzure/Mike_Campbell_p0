package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;
import com.revature.course_registration.util.logging.Logger;

public class UserProfileEditMenu extends Menu {
	private final UserService userService;
	private final Logger logger;

	public UserProfileEditMenu(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Profle Edit Menu", "/user-profile-edit", consoleReader, router);
		this.userService = userService;
		this.logger = Logger.getLogger(true);
	}

	@Override
	public void render() throws Exception {
		// TODO Auto-generated method stub

	}

}
