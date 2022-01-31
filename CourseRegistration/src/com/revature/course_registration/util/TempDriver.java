package com.revature.monster_lab.util;

public class TempDriver {

	public static void main(String[] args) {
		List<String> myStringList = new LinkedList<>();
		
		myStringList.add("Hello");
		myStringList.add("There");
		myStringList.add("Ahhh");
		myStringList.add("General");
		myStringList.add("Kenobi");
		
		//System.out.println(myStringList.contains("Hello")); // true
		//System.out.println(myStringList.contains("There")); // true
		//System.out.println(myStringList.contains("AhHh"));  // true
		//System.out.println(myStringList.contains("General")); // true
		//System.out.println(myStringList.contains("Kenobi")); // true
		
		
		
		System.out.println(myStringList.remove("There"));
		
		System.out.println(myStringList.contains("There"));
		
		
		System.out.println(myStringList.get(3));
	}

	
	
}
