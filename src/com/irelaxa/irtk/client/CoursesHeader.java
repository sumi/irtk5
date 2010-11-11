package com.irelaxa.irtk.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class CoursesHeader extends Composite {
	private FlexTable datingTable = new FlexTable();
	FlexCellFormatter cellFormatter = datingTable.getFlexCellFormatter();
   
	private Button myDatingGoalsLink = new Button("My Course Goals");
    private Button hoursSpent = new Button ("Time Spent");
    private static CoursesHeader instance = null;
	protected CoursesHeader(){
		initWidget(datingTable);
		datingTable.setWidth("48em");
		datingTable.setCellSpacing(5);
		datingTable.setCellPadding(3);
		
		myDatingGoalsLink.setStyleName("gwt-irelaxa-Button");
		hoursSpent.setStyleName("gwt-irelaxa-Button");
		
		/*cellFormatter.setHorizontalAlignment(0, 1,
		        HasHorizontalAlignment.ALIGN_LEFT);
		datingTable.getFlexCellFormatter().setColSpan(1, 0, 2);*/
		datingTable.setWidget(0, 0, myDatingGoalsLink);
		datingTable.setWidget(0, 1, hoursSpent);
		
		
		
		
		myDatingGoalsLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 DatingGoalsView1.getInstance().getDatingGoalsTable());
		      }
		    });
		hoursSpent.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
	}		 
	public static CoursesHeader getInstance() {
		     if(instance == null) {
		        instance = new CoursesHeader();
		     }
		     return instance;
		 }
		
	
	
}
