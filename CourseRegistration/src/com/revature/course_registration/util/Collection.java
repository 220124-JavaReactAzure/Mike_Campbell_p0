package com.revature.monster_lab.util;

// Generics - they're used to help deal with multiple/varying datatypes
public interface Collection<T> {

	boolean add(T element);
	
	boolean contains(T element);
	
	boolean isEmpty();
	
	boolean remove(T element);
	
	int size();
}
