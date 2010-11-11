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

public class DietHeader extends Composite {
	
	private FlexTable datingTable = new FlexTable();
	FlexCellFormatter cellFormatter = datingTable.getFlexCellFormatter();
   /*1. SELF AWARENESS*/
	private Button myDatingGoalsLink = new Button("My Diet Goals");//SELF AWARENESS
    private Button myDatingProblemsLink = new Button("My Breakfast Plans");//SELF AWARENESS
    private Button myDatingDiaryLink = new Button("My Lunch Plans");//SELF AWARENESS
    private Button myDatingBudgetLink = new Button("My Dinner Plans");//SELF AWARENESS
    private Button myGroupDateLink = new Button("My Body Type");//SELF AWARENESS
    private Button myDatingNeeds = new Button("My Diet Needs");//SELF AWARENESS
    private Button myDatingBoundaries = new Button("My Diet Boundaries");//SELF AWARENESS
    private Button yourIdealPartner = new Button("My Ideal Diet");//SELF AWARENESS
    private Button yourRomanceEI = new Button("Diet Emotional Intelligence");
   /*2. SELF MANAGEMENT*/
    private Button yourRomanticMusic = new Button("My Diet Recipes");//SELF MANAGEMENT
    private Button yourRomanceWellbeing = new Button("Diet and Wellbeing");
   /*3. SOCIAL AWARENESS*/
    private Button myFriendsDiet = new Button("My Friends and Diet");//SELF AWARENESS
   /*4. SOCIAL MANAGEMENT*/
    private Button myMakeADateLink = new Button("My Diet Activities");//SOCIAL MANAGEMENT
   /*5. PRODUCTS, MARKET PLACE*/
    private Button yourRomanticMovies = new Button("My Diet Deals");//PRODUCTS, MARKET PLACE
	
   
    private static DietHeader instance = null;
	protected DietHeader(){
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
		myDatingNeeds.setStyleName("gwt-irelaxa-Button");
		myDatingBoundaries.setStyleName("gwt-irelaxa-Button");
		yourIdealPartner.setStyleName("gwt-irelaxa-Button");
		yourRomanticMusic.setStyleName("gwt-irelaxa-Button");
		myFriendsDiet.setStyleName("gwt-irelaxa-Button");
		yourRomanticMovies.setStyleName("gwt-irelaxa-Button");
		yourRomanceEI.setStyleName("gwt-irelaxa-Button");
		yourRomanceWellbeing.setStyleName("gwt-irelaxa-Button");
		/*cellFormatter.setHorizontalAlignment(0, 1,
		        HasHorizontalAlignment.ALIGN_LEFT);
		datingTable.getFlexCellFormatter().setColSpan(1, 0, 2);*/
		datingTable.setWidget(0, 0, myDatingGoalsLink);
		datingTable.setWidget(0, 1, myDatingProblemsLink);
		datingTable.setWidget(0, 2, myDatingDiaryLink);
		datingTable.setWidget(0, 3, myDatingBudgetLink);
		datingTable.setWidget(0, 4, myMakeADateLink);
		datingTable.setWidget(0, 5, myGroupDateLink);
		datingTable.setWidget(0, 6, myDatingNeeds);
		datingTable.setWidget(1, 0, myDatingBoundaries);
		datingTable.setWidget(1, 1, yourIdealPartner);
		datingTable.setWidget(1, 2, yourRomanticMusic);
		datingTable.setWidget(1, 3, yourRomanticMovies);
		datingTable.setWidget(1, 4, myFriendsDiet);
		datingTable.setWidget(1, 5, yourRomanceEI);
		datingTable.setWidget(1, 6, yourRomanceWellbeing);
		
		
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
		yourIdealPartner.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		yourRomanticMusic.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		myFriendsDiet.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		yourRomanticMovies.addClickHandler(new ClickHandler() {
			//a form to be presented. that takes moie name inputs and we display the movie titles.and we add them to our databse.
		      public void onClick(ClickEvent event) {
		    	  String netflix = "<div id='myMovieLocation'>"+
		    	  "<img src='http://cdn-7.nflximg.com/us/boxshots/large/70068647.jpg' /><br />"+
		    	  "<script src='http://jsapi.netflix.com/us/api/js/api.js'>"+
		    	  "{"+
		    	     "'title_id' : 'http://api.netflix.com/catalog/movie/70068647',"+
		    	     "'button_type' : ['PLAY_BUTTON', 'ADD_BUTTON'],"+
		    	     "'show_logo' : 'true',"+
		    	     "'x' : '40',"+
		    	     "'y' : '20',"+
		    	     "'dom_id' : 'myMovieLocation',"+
		    	     "'application_id' : 'mqfsd3nwajdhbxtn7cz9f286'"+
		    	  "}"+
		    	  "</script>"+
		    	 " </div>";
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(new HTML(netflix));
		      }
		    });
		
		yourRomanceEI.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		yourRomanceWellbeing.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		
	}
		
		 public static DietHeader getInstance() {
		     if(instance == null) {
		        instance = new DietHeader();
		     }
		     return instance;
		 }
		
	
	
}
