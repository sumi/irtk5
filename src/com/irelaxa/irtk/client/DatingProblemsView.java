package com.irelaxa.irtk.client;

import com.google.gwt.user.client.ui.Composite;


public class DatingProblemsView extends Composite {
	private static DatingProblemsView instance = null;
	
	protected DatingProblemsView(){
		//1.what are the current problems
		//2.why current problems
		//3.solutions
		// 4.action plans for current problems
		
			}
	 public static DatingProblemsView getInstance() {
	     if(instance == null) {
	        instance = new DatingProblemsView();
	     }
	     return instance;
	 }
	
	
}