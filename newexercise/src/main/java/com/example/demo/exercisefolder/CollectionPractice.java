package com.example.demo.exercisefolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionPractice {
	
	public static void main(String[] args) {
		
		List l = new ArrayList();
		
		l.add(10);
		l.add(20);
		l.add(50);
		
		Iterator itr = l.iterator();
		
		while(itr.hasNext()) {
			String s = (String) itr.next();
			System.out.println(s);
		}
		
	}

}
