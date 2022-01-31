package com.revature.monster_lab.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.revature.monster_lab.menus.RegisterMenu;
import com.revature.monster_lab.menus.WelcomeMenu;
import com.revature.monster_lab.services.ScientistService;

public class AppState {

	private static boolean isRunning;
	private final MenuRouter router;
	
	public AppState() {
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		
		ScientistService scientistService = new ScientistService();
		router.addMenu(new WelcomeMenu(consoleReader, router));
		router.addMenu(new RegisterMenu(consoleReader, router, scientistService));
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
