package com.revature.course_registration.menus;

import java.io.BufferedReader;

import com.revature.course_registration.models.User;
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
				String menu = "1) Update First Name\n" + 
							"2) Update Last Name\n" + 
							"3) Update Email\n" + 
							"4) Update Username\n" +
							"5) Update Password\n" + 
							"6) Back\n" + 
							"> ";

				System.out.print(menu);

				String userSelection = consoleReader.readLine();
				
				User updatedUser = userService.getSessionUser();
				
				String userInput;

				switch (userSelection) {
				case "1":
					System.out.print("New first name: ");
					userInput = consoleReader.readLine();
					updatedUser.setFirstName(userInput);
					userService.updateUser(updatedUser);
					
					break;
				case "2":
					System.out.print("New last name: ");
					userInput = consoleReader.readLine();
					updatedUser.setLastName(userInput);
					userService.updateUser(updatedUser);
					
					break;
				case "3":
					System.out.print("New email: ");
					userInput = consoleReader.readLine();
					updatedUser.setEmail(userInput);
					userService.updateUser(updatedUser);
					
					break;
				case "4":
					System.out.print("New username: ");
					userInput = consoleReader.readLine();
					updatedUser.setUsername(userInput);
					userService.updateUser(updatedUser);
					
					break;
				case "5":
					System.out.print("New password: ");
					userInput = consoleReader.readLine();
					updatedUser.setPassword(userInput);
					userService.updateUser(updatedUser);
					
					break;
				case "6":
					router.transfer("/profile");
					break;
				default:
					System.out.println("The user made an invalid selection.");
				}

			}

	

}
