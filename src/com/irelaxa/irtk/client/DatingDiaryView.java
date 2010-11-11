package com.irelaxa.irtk.client;

import com.google.gwt.user.client.ui.Composite;


public class DatingDiaryView extends Composite {
	private static DatingDiaryView instance = null;
	
	protected DatingDiaryView(){
		
		//1.keep track of ur dates
		//2.the person, the date, the time, the activity, the amount 
		//3.the feeling, the emotion.
		
			}
	 public static DatingDiaryView getInstance() {
	     if(instance == null) {
	        instance = new DatingDiaryView();
	     }
	     return instance;
	 }
	
	
}