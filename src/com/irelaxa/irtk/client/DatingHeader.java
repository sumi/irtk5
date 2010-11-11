package com.irelaxa.irtk.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.smartgwt.client.widgets.layout.VLayout;

public class DatingHeader extends Composite {
	  
	private FlexTable datingTable = new FlexTable();
	FlexCellFormatter cellFormatter = datingTable.getFlexCellFormatter();
   
	private Button myDatingGoalsLink = new Button("My Dating Goals");
    private Button myDatingProblemsLink = new Button("Dating Problems");
    private Button myDatingDiaryLink = new Button("My Dating Diary");
    private Button myDatingBudgetLink = new Button("My Dating & Spending");
    private Button myMakeADateLink = new Button("Date Invitation");
    private Button myGroupDateLink = new Button("Group Date Invitation");
    private Button myDatingNeeds = new Button("My Dating Needs");
    private Button myDatingBoundaries = new Button("My Dating Boundaries");
    private Button yourIdealPartner = new Button("My Ideal Partner");
    private Button yourRomanticMusic = new Button("My Romantic Music");
    private Button yourRomanticMovies = new Button("My Romantic Movies");
    private Button yourRomanticMoments = new Button("My Romantic Moments");
    private Button yourRomanceEI = new Button("Romance Emotional Intelligence");
    private Button yourRomanceWellbeing = new Button("Romance and Wellbeing");
    private Button yourRomanceActivities = new Button("Romantic Activities");
    private static DatingHeader instance = null;
   
    //VerticalPanel vPanel = new VerticalPanel();
    
	protected DatingHeader(){
		  
		 VLayout vLayout = new VLayout();  
       /* vLayout.addMember(new Anchor("Test1"), 0);  
        vLayout.addMember(new Anchor("Test2"));  
        vLayout.addMember(new Anchor("Test3"));  */
       // layout.addMember(vLayout);  
		initWidget(vLayout);
		 
		
     vLayout.setShowEdges(true);  
     vLayout.setWidth(150);  
     vLayout.setMembersMargin(1);  
     vLayout.setLayoutMargin(10); 
     vLayout.addMember(myDatingGoalsLink);
     
    
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
		yourRomanticMovies.setStyleName("gwt-irelaxa-Button");
		yourRomanticMoments.setStyleName("gwt-irelaxa-Button");
		yourRomanceEI.setStyleName("gwt-irelaxa-Button");
		yourRomanceWellbeing.setStyleName("gwt-irelaxa-Button");
		yourRomanceActivities.setStyleName("gwt-irelaxa-Button");
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
		datingTable.setWidget(1, 4, yourRomanticMoments);
		datingTable.setWidget(1, 5, yourRomanceEI);
		datingTable.setWidget(1, 6, yourRomanceWellbeing);
		datingTable.setWidget(2, 3, yourRomanceActivities);
		
		
	  
	        
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
		yourRomanticMoments.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
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
		yourRomanceActivities.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  RootPanel.get("flexTable").clear(); 
			    	 RootPanel.get("flexTable").add(
			    			 DatingActivitiesView.getInstance());
		      }
		    });
		
	}
		
		 public static DatingHeader getInstance() {
		     if(instance == null) {
		        instance = new DatingHeader();
		     }
		     return instance;
		 }
		
	
	
}
