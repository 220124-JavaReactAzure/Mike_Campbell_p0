package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.services.CourseService;
import com.revature.course_registration.services.UserService;
import com.revature.course_registration.util.MenuRouter;

public class ModifyCourseMenu extends Menu {

	public ModifyCourseMenu(BufferedReader consoleReader, MenuRouter router,
			UserService userService, CourseService courseService) {
		super("Modify Course Menu", "/modify-course-menu", consoleReader, router);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render() throws Exception {
		// TODO Auto-generated method stub

	}

}
