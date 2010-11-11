package com.irelaxa.irtk.client;

import com.google.gwt.user.client.ui.Composite;


public class DatingRomanticMomentsView extends Composite {
	private static DatingRomanticMomentsView instance = null;
	
	protected DatingRomanticMomentsView(){
		//1.show the list of all romantic moments so far in ur life
		//2.who when what where how much
		
		
			}
	 public static DatingRomanticMomentsView getInstance() {
	     if(instance == null) {
	        instance = new DatingRomanticMomentsView();
	     }
	     return instance;
	 }
	
	
}