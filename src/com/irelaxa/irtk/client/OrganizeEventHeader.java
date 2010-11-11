package com.irelaxa.irtk.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class OrganizeEventHeader extends Composite {
	private FlexTable datingTable = new FlexTable();
	FlexCellFormatter cellFormatter = datingTable.getFlexCellFormatter();
   
	private Button myDatingGoalsLink = new Button("My Events");
    private Button myDatingProblemsLink = new Button("Attendees");
    private Button myDatingDiaryLink = new Button("Sponsorships");
    private Button myDatingBudgetLink = new Button("Cross-Promotion");
    private Button myMakeADateLink = new Button("Partnerships");
    private Button myGroupDateLink = new Button("Deals");
    
    private static OrganizeEventHeader instance = null;
	protected OrganizeEventHeader(){
		initWidget(datingTable);
		datingTable.setWidth("48em");
		datingTable.setCellSpacing(5);
		datingTable.setCellPadding(3);
		myDatingGoalsLink.setStyleName("gwt-irelaxa-Button");
		myDatingProblemsLink.setStyleName("gwt-irelaxa-Button");
		myDatingDiaryLink.setStyleName("gwt-irelaxa-Button");
		myDatingBudgetLink.setStyleName("gwt-irelaxa-Button");
		myMakeADateLink.setStyleName("gwt-irelaxa-Button");
		myGroupDateLink.setStyleName("gwt-irelaxa-Button");
		
		
		/*cellFormatter.setHorizontalAlignment(0, 1,
		        HasHorizontalAlignment.ALIGN_LEFT);
		datingTable.getFlexCellFormatter().setColSpan(1, 0, 2);*/
		datingTable.setWidget(0, 0, myDatingGoalsLink);
		datingTable.setWidget(0, 1, myDatingProblemsLink);
		datingTable.setWidget(0, 2, myDatingDiaryLink);
		datingTable.setWidget(0, 3, myDatingBudgetLink);
		datingTable.setWidget(0, 4, myMakeADateLink);
		datingTable.setWidget(0, 5, myGroupDateLink);
		
		
		
		myDatingGoalsLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 DatingGoalsView1.getInstance().getDatingGoalsTable());
		      }
		    });
		myDatingProblemsLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		myDatingDiaryLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		myDatingBudgetLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		myMakeADateLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 DateMakeView.getInstance());
		      }
		    });
		myGroupDateLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		
		
	}
		
		 public static OrganizeEventHeader getInstance() {
		     if(instance == null) {
		        instance = new OrganizeEventHeader();
		     }
		     return instance;
		 }
		
	
	
}
