package com.irelaxa.irtk.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class FinanceHeader extends Composite {
	private FlexTable datingTable = new FlexTable();
	FlexCellFormatter cellFormatter = datingTable.getFlexCellFormatter();
	 /*1. SELF AWARENESS*/
	 private Anchor myBodyType = new Anchor("My Finance Type");
	private Anchor myFashionProblemsLink = new Anchor("Finance Problems");
	 private Anchor myFashionDos = new Anchor("Finance Do's");
	 private Anchor myBestLook = new Anchor("My Ideal Finance");
	 private Anchor yourRomanceEI = new Anchor("Finance Emotional Intelligence");
	 private Anchor yourFashionWellbeing = new Anchor("Finance and Wellbeing");
	/*2. SELF MANAGEMENT*/
	private Anchor myFashionGoalsLink = new Anchor("Finance Goals");
	private Anchor myFashionDiaryLink = new Anchor("Finance Diary");
	private Anchor myFashionBudgetLink = new Anchor("Assets");
	private Anchor myFashionBoundaries = new Anchor("Finance Don'ts");
	 private Anchor myWardrobe = new Anchor("Savings");
	 /*3. SOCIAL AWARENESS*/
	 private Anchor fashionMoviews = new Anchor("My Networth");
    
	/*5. PRODUCTS, MARKET PLACE*/
    private Anchor shopping = new Anchor("My Banks");
    private Anchor groupShopping = new Anchor("Spending");
    
   
    
   
   
    
    
    
    
    
    private static FinanceHeader instance = null;
	protected FinanceHeader(){
		initWidget(datingTable);
		datingTable.setWidth("48em");
		datingTable.setCellSpacing(5);
		datingTable.setCellPadding(3);
		/*cellFormatter.setHorizontalAlignment(0, 1,
		        HasHorizontalAlignment.ALIGN_LEFT);
		datingTable.getFlexCellFormatter().setColSpan(1, 0, 2);*/
		datingTable.setWidget(0, 0, myFashionGoalsLink);
		datingTable.setWidget(0, 1, myFashionProblemsLink);
		datingTable.setWidget(0, 2, myFashionDiaryLink);
		datingTable.setWidget(0, 3, myFashionBudgetLink);
		datingTable.setWidget(0, 4, shopping);
		datingTable.setWidget(0, 5, groupShopping);
		datingTable.setWidget(0, 6, myFashionDos);
		datingTable.setWidget(1, 0, myFashionBoundaries);
		datingTable.setWidget(1, 1, myBodyType);
		datingTable.setWidget(1, 2, myWardrobe);
		datingTable.setWidget(1, 3, fashionMoviews);
		datingTable.setWidget(1, 4, myBestLook);
		datingTable.setWidget(1, 5, yourRomanceEI);
		datingTable.setWidget(1, 6, yourFashionWellbeing);
		
		
		
		myFashionGoalsLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 DatingGoalsView1.getInstance().getDatingGoalsTable());
		      }
		    });
		myFashionProblemsLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		myFashionDiaryLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		myFashionBudgetLink.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		shopping.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		groupShopping.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		myBodyType.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		myWardrobe.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		fashionMoviews.addClickHandler(new ClickHandler() {
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
		myBestLook.addClickHandler(new ClickHandler() {
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
		yourFashionWellbeing.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	 RootPanel.get("flexTable").clear(); 
		    	 RootPanel.get("flexTable").add(
		    			 new HTML("Coming Soon"));
		      }
		    });
		
		
	}
		
		 public static FinanceHeader getInstance() {
		     if(instance == null) {
		        instance = new FinanceHeader();
		     }
		     return instance;
		 }
		
	
	
}
