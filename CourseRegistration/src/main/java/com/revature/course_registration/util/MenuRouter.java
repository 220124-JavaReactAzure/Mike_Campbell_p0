package main.java.com.revature.course_registration.util;


import main.java.com.revature.course_registration.menus.Menu;

public class MenuRouter {
	private final ArrayList<Menu> menus;
	
	public MenuRouter() {
		menus = new ArrayList<>();
	}
	
	public void addMenu(Menu menu) {
		menus.add(menu);
	}
	
	public void transfer(String route) throws Exception{
		for(int i = 0; i < menus.size(); i++) {
			Menu currentMenu = menus.get(i);
			if(currentMenu.getRoute().equals(route)) {
				currentMenu.render();
			}
		}
	}
	
}