package com.irelaxa.irtk.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.gwtfb.client.Callback;
import com.gwtfb.sdk.FBCore;
import com.irelaxa.irtk.shared.FieldVerifier;

public class GoalWidget extends Composite{
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);
    private final StockServiceAsync stockService = GWT.create(StockService.class);
    private FBCore fbCore = GWT.create(FBCore.class);
    public LoginInfo loginInfo = null;
	private ArrayList<String> stocks = new ArrayList<String>();
	final String[] goalTypes = {"Personal", "Professional", "Travel", "Fun", "Events","Student Solutions", "Club Solutions",
    		"WorkPlace Solutions", "Sponsor"};
	final String[] personal = {"Dating", "Diet", "Fitness", "Fashion", "Finances", "Friendships", "Family"};
    final String[] professional = {"Salary", "Career", "Skills", "Networking", "Strengths", "Weaknesses"};
    final String[] travel ={"Vacation", "Flights", "Hotels", "Cruises"};
    final String[] fun ={"Sports", "Music", "Books", "Movies", "TV Shows", "Creativity Center", "Zen Center"};
    final String[] events = {"Organize Events", "Career Events", "Social Events", "Family Events", "Community Events", "Spiritual Events"};
    final String[] students = {"Courses", "Projects", "Profesors", "Activities", "Groups", "Mentorships", "Teach", "Dream Jobs", "Job Offers", "Stress Management"};
    final String[] clubs = {"Clubs", "Members", "Events", "Sponsorships"};
    final String[] workplace = {"Mentoring", "Motivation", "Creativity ToolKit", "Innovation ToolKit", "Team Building", "Work Life Balance"};
    final String[] sponsor = {"Give Deals", "Sponsor Goals", "CRM", "Loyalty Lab", "Cross-Promote", "Partnerships"};
    
	private ListBox goalTypeDropBox = new ListBox(false);
	private ListBox subGoalTypeDropBox = new ListBox(true);
	public final FlexTable goalTable = new FlexTable();
	private ListBox listBox1 = new ListBox();
	final ListBox budgetDropBox = new ListBox(false);
	DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
    DateBox sdateBox = new DateBox();
    DateBox edateBox = new DateBox();
    private Button sendButton = new Button("Save & Match My Goal");
    public Stack goalObject = new Stack();
    private FlexTable stocksFlexTable = new FlexTable();
    private Anchor signOutLink = new Anchor("Sign Out");
    private Anchor signInLink = new Anchor("Sign In");
	
	public GoalWidget(){
		addGoalType();
		showGoalDates();
		loginService();
		loadGoalTableHeading();
		loadStocks();
		edateBox.addValueChangeHandler(new ValueChangeHandler<Date>(){
			public void onValueChange(ValueChangeEvent<Date> event){
				if(sdateBox.getValue().after(edateBox.getValue())){
				 Window.alert("Goal End Date is before Goal Start Date");
				}
			}
		});
		
		sendButton.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        	
		    	  goalObject.clear();
		    	  goalObject.push(edateBox.getValue());
		    	  goalObject.push(sdateBox.getValue());
		    	  goalObject.push(budgetDropBox.getValue(budgetDropBox.getSelectedIndex()));
		    	  goalObject.push(listBox1.getValue(listBox1.getSelectedIndex()));
		    	  goalObject.push(goalTypeDropBox.getValue(goalTypeDropBox.getSelectedIndex()));
		    	  afterGoalSave();
		    	  addStock(goalObject, "goal");
		    	 // getMatchingProduct();   
		      }
		    });	
	}
	 public void addStock(Stack dataObject, String objectName) {
		  final Stack dataObjectLocal = dataObject;
		  final String finalobjectName = objectName;
		        if(loginInfo.isLoggedIn()) {
		        	signOutLink.setHref(loginInfo.getLogoutUrl());
		        	stockService.addStock(dataObjectLocal, objectName, new AsyncCallback<Void>() {
		        		String[] justAddedGoal = new String[10];
		   			  public void onFailure(Throwable error) {
		   		      }
		   		      public void onSuccess(Void ignore) {
		   		    	  if(finalobjectName.equals("goal")){
		   		    	justAddedGoal[0] = goalTypeDropBox.getValue(goalTypeDropBox.getSelectedIndex());
				    	  justAddedGoal[1] = listBox1.getValue(listBox1.getSelectedIndex());
				    	  justAddedGoal[2] = budgetDropBox.getValue(budgetDropBox.getSelectedIndex());
				    	  justAddedGoal[3] = FieldVerifier.getMonth(sdateBox.getValue().getMonth())+" "+Integer.toString(sdateBox.getValue().getDate())+" " +Integer.toString((sdateBox.getValue().getYear()+1900));
				    	  justAddedGoal[4] = FieldVerifier.getMonth(edateBox.getValue().getMonth())+" "+Integer.toString(edateBox.getValue().getDate())+" " +Integer.toString((edateBox.getValue().getYear()+1900));
				       displayStock(justAddedGoal);
				       
		   		    	  }
		   		      }
		   		    });
		        } else {
		          loadLogin();
		        }
		  }
	public void addGoalType(){
		
	    for (int i = 0; i < goalTypes.length; i++) {
	        goalTypeDropBox.addItem(goalTypes[i]);
	      }
	    goalTypeDropBox.ensureDebugId("cwListBox-goalTypeDropBox");
	    subGoalTypeDropBox.ensureDebugId("cwListBox-subGoalTypeDropBox");
	    subGoalTypeDropBox.setWidth("11em");
	    subGoalTypeDropBox.setVisibleItemCount(3);
	    goalTable.setWidget(2, 0, new HTML("Select Goal Type"));
	    goalTable.setWidget(2, 1, goalTypeDropBox);
	    RootPanel.get("flexTable").add(goalTable);
	 // Add a handler to handle drop box events
	    goalTypeDropBox.addChangeHandler(new ChangeHandler() {
	    //	box.addChangeListener(new ChangeListener() {
	      public void onChange(ChangeEvent event) {
	        showSubGoalType(subGoalTypeDropBox, goalTypeDropBox.getSelectedIndex());
	        subGoalTypeDropBox.ensureDebugId("cwListBox-subGoalTypeDropBox");
	      }
	    });
	}
	private void showSubGoalType(ListBox listBox, final int goalType) {
		  listBox1 = listBox;
			listBox1.clear();
		  String[] listData = null;
		  
		  switch (goalType) {
		    case 0:
		      listData = personal;
		      
		      break;
		    case 1:
		      listData = professional;
		      break;
		    case 2:
		      listData = travel;
		      break;
		    case 3:
		        listData = fun;
		        break;
		    case 4:
		        listData = events;
		        break;
		    case 5:
		          listData = students;
		          break;
		    case 6:
		          listData = clubs;
		          break;
		          
		    case 7:
		          listData = workplace;
		          break;
		    case 8:
		          listData = sponsor;
		          break;
		    
		      
		  }
		  for (int i = 0; i < listData.length; i++) {
			  listBox1.addItem(listData[i]);
		  }
		// goalPanel.clear();
		// goalPanel.add(goalLable);
		// goalPanel.add(listBox1, goalTypeDropBox.getAbsoluteLeft(), goalLable.getAbsoluteTop());
		 goalTable.setWidget(3, 0, new HTML("Select Goal"));
		 goalTable.setWidget(3, 1, listBox1);
		 
		
		listBox.addChangeHandler(new ChangeHandler() {
		  public void onChange(ChangeEvent event) {
		    showBudget(budgetDropBox, goalType, subGoalTypeDropBox.getSelectedIndex());
		    budgetDropBox.ensureDebugId("cwListBox-budgetDropBox");
		  }
		});
		}
	private void showBudget(ListBox budgetListBox, int goalType, int subGoalType) {
			budgetListBox.clear();
			//goalTypeValue = goalTypes[goalType];
			  String[] listData = null;
			  
			  String[] dating = {"<$50", "<150", "<250"};
			  String[] diet = {"<$100", "<250", "<500"};
			  String[] fitness = {"<$50", "<150", "<500"};
			  String[] fashion = {"<$50", "<150", "<500"};
			  String[] finances = {"<$50", "<150", "<500"};
			  String[] friendships = {"<$50", "<150", "<500"};
			  String[] family = {"<$50", "<150", "<500"};
			  
			  String[] salary = {"<$40,000", "<$80,000", "<120,000", "<250,000"};
			  String[] career = {"<102", "<252", "<552"};
			  String[] skills = {"<103", "<253", "<553"};
			  String[] networking = {"<521", "<715", "<1031"};
			  String[] strengths = {"<522", "<716", "<1032"};
			  String[] weaknesses = {"<523", "<717", "<1033"};
			  
			  
			  String[] vacation = {"<501", "<101", "<151"};
			  String[] flights = {"<502", "<102", "<152"};
			  String[] hotels = {"<503", "<103", "<153"};
			  String[] cruises = {"<50000", "<100000", "<150000"};
			  
			  
			  String[] sports = {"<50001", "<100001", "<150001"};
			  String[] music = {"<50002", "<100002", "<150002"};
			  String[] books = {"<500", "<1000", "<1500"};
			  String[] movies = {"<501", "<1001", "<1501"};
			  String[] TVShows = {"<502", "<1002", "<1502"};
			  String[] creativityCenter = {"<501", "<1001", "<1501"};
			  String[] zenCenter = {"<502", "<1002", "<1502"};
			  
			  String[] organizeEvents = {"<50001", "<100001", "<150001"};
			  String[] careerEvents = {"<50002", "<100002", "<150002"};
			  String[] socialEvents = {"<500", "<1000", "<1500"};
			  String[] familyEvents = {"<501", "<1001", "<1501"};
			  String[] communityEvents = {"<502", "<1002", "<1502"};
			  String[] spiritualEvents = {"<501", "<1001", "<1501"};
			  
			  String[] courses = {"<500", "<1000", "<1500"};
			  String[] projects = {"<501", "<1001", "<1501"};
			  String[] professors = {"<502", "<1002", "<1502"};
			  String[] activities = {"<501", "<1001", "<1501"};
			  String[] groups = {"<50001", "<100001", "<150001"};
			  String[] mentorships = {"<50002", "<100002", "<150002"};
			  String[] teach = {"<500", "<1000", "<1500"};
			  String[] dreamJobs = {"<501", "<1001", "<1501"};
			  String[] jobOffers = {"<502", "<1002", "<1502"};
			  String[] stressManagement = {"<501", "<1001", "<1501"};
			  
			  String[] clubs = {"<500", "<1000", "<1500"};
			  String[] members = {"<501", "<1001", "<1501"};
			  String[] events = {"<502", "<1002", "<1502"};
			  String[] sponsorships = {"<501", "<1001", "<1501"};
			  
			  String[] mentoring = {"<500", "<1000", "<1500"};
			  String[] motivation = {"<501", "<1001", "<1501"};
			  String[] creativityToolKit = {"<502", "<1002", "<1502"};
			  String[] innovationToolKit = {"<501", "<1001", "<1501"};
			  String[] teamBuilding = {"<500", "<1000", "<1500"};
			  String[] workLifeBalance = {"<501", "<1001", "<1501"};
			  
			  String[] giveDeal = {"<500", "<1000", "<1500"};
			  String[] sponsorGoals = {"<501", "<1001", "<1501"};
			  String[] crm = {"<502", "<1002", "<1502"};
			  String[] loyaltyLab = {"<501", "<1001", "<1501"};
			  String[] crossPromote = {"<500", "<1000", "<1500"};
			  String[] partnerships = {"<501", "<1001", "<1501"};
			  
			  
			  switch (goalType) {
			    case 0:
			    	//goalValue = nutrition[goal];
			    	switch(subGoalType){
			    	case 0:	
			      listData = dating;
			      break;
			    	case 1:
			      listData = diet;
			      break;
			    	case 2:
			  	  listData = fitness;
			  	  break;
			    	case 3:	
				  listData = fashion;
				  break;
					case 4:
				  listData = finances;
				  break;
					case 5:
				  listData = friendships;
				  break;
					case 6:
				  listData = family;
				  break;
			    	}
			      break;
			    case 1:
			    	//goalValue = fitness[goal];
			    	switch(subGoalType){
			    	case 0:	
			      listData = salary;
			      break;
			    	case 1:
			      listData = career;
			      break;
			    	case 2:
			  	  listData = skills;
			  	  break;
			    	case 3:	
				  listData = networking;
				  break;
				    case 4:
				  listData = strengths;
				  break;
					case 5:
				  listData = weaknesses;
				  break;
			    	}
			      break;
			    
			    case 2:
			    	//goalValue = fashion[goal];
			    	switch(subGoalType){
			    	case 0:	
			      listData = vacation;
			      break;
			    	case 1:
			      listData = flights;
			      break;
			    	case 2:
			  	  listData = hotels;
			  	  break;
			    	case 3:
				  listData = cruises;
				  break;
			    	}
			        break;
			    case 3:
			    //	goalValue = career[goal];
			    	switch(subGoalType){
			    	case 0:	
			      listData = sports;
			      break;
			    	case 1:
			      listData = music;
			      break;
			    	case 2:
			  	  listData = books;
			  	  break;
			    	case 3:	
				 listData = movies;
			     break;
				   case 4:
				listData = TVShows;
				break;
				   case 5:
				listData = creativityCenter;
				break;
				   case 6:
				listData = zenCenter;
				break;
			    	}
				      break;
				case 4:
					//goalValue = admission[goal];
					switch(subGoalType){
			    	case 0:	
			      listData = organizeEvents;
			      break;
			    	case 1:
			      listData = careerEvents;
			      break;
			    	case 2:
			  	  listData = socialEvents;
			  	  break;
			    	case 3:	
				  listData = familyEvents;
				  break;
					case 4:
				  listData = communityEvents;
				  break;
					case 5:
				  listData = spiritualEvents;
				  break;
			    	}
				      break;
				case 5:
					//goalValue = admission[goal];
					switch(subGoalType){
			    	case 0:	
			      listData = courses;
			      break;
			    	case 1:
			      listData = projects;
			      break;
			    	case 2:
			  	  listData = professors;
			  	  break;
			    	case 3:	
				  listData = activities;
				  break;
					case 4:
				  listData = groups;
				  break;
					case 5:
				  listData = mentorships;
				  break;
					case 6:
				  listData = teach;
			  	  break;
			    	case 7:	
				  listData = dreamJobs;
				  break;
					case 8:
				  listData = jobOffers;
				  break;
					case 9:
				  listData = stressManagement;
				  break;
			    	}
				      break;
				case 6:
					//goalValue = admission[goal];
					switch(subGoalType){
			    	case 0:	
			      listData = clubs;
			      break;
			    	case 1:
			      listData = members;
			      break;
			    	case 2:
			  	  listData = events;
			  	  break;
			    	case 3:	
				  listData = sponsorships;
				  break;
			    	}
				      break;
				case 7:
					//goalValue = admission[goal];
					switch(subGoalType){
			    	case 0:	
			      listData = mentoring;
			      break;
			    	case 1:
			      listData = motivation;
			      break;
			    	case 2:
			  	  listData = creativityToolKit;
			  	  break;
			    	case 3:	
				  listData = innovationToolKit;
				  break;
					case 4:
				  listData = teamBuilding;
				  break;
					case 5:
				  listData = workLifeBalance;
				  break;
			    	}
				      break;
				case 8:
					//goalValue = admission[goal];
					switch(subGoalType){
			    	case 0:	
			      listData = giveDeal;
			      break;
			    	case 1:
			      listData = sponsorGoals;
			      break;
			    	case 2:
			  	  listData = crm;
			  	  break;
			    	case 3:	
				  listData = loyaltyLab;
				  break;
					case 4:
				  listData = crossPromote;
				  break;
					case 5:
				  listData = partnerships;
				  break;
			    	}
				      break;
			  }
			  
			  for (int i = 0; i < listData.length; i++) {
				  budgetListBox.addItem(listData[i]);
			  }
			  //budgetPanel.clear();
			  //budgetPanel.add(bugetLable);
			  //budgetPanel.add(budgetListBox, goalTypeDropBox.getAbsoluteLeft(), bugetLable.getAbsoluteTop());
			  goalTable.setWidget(4, 0, new HTML("Select Budget"));
			  goalTable.setWidget(4, 1, budgetListBox);
			  RootPanel.get("flexTable").add(goalTable);  
		   }
	private void showGoalDates(){
			sdateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		    goalTable.setWidget(5, 0, new HTML("Goal Start Date"));
			goalTable.setWidget(5, 1, sdateBox);
		    edateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		    goalTable.setWidget(6, 0, new HTML("Goal End Date"));
			goalTable.setWidget(6, 1, edateBox);
			goalTable.setWidget(7, 1,sendButton);
			//RootPanel.get("flexTable").add(goalTable);
		}
	public void afterGoalSave(){
			onPersonalGoalsSelect();
		}
	public void onPersonalGoalsSelect(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(stocksFlexTable);	
		
			}
	private void loadGoalTableHeading() {
			// Create table for stock data.
			    stocksFlexTable.setHTML(0, 1, "<h3>Goal Type</h3>");
			    stocksFlexTable.setHTML(0, 2, "<h3>Goal</h3>");
			    stocksFlexTable.setHTML(0, 3, "<h3>Budget</h3>");
			    stocksFlexTable.setHTML(0, 4, "<h3>Start Date</h3>");
			    stocksFlexTable.setHTML(0, 5, "<h3>End Date</h3>");
			    //stocksFlexTable.setHTML(0, 7, "<h3>Remove Goal</h3>");
			    stocksFlexTable.setHTML(0, 9, "<h3>Time Left</h3>");
			    //stocksFlexTable.setHTML(0, 9, "<h3>Buget Remaining</h3>");
		}
	private void loadStocks() {
		    stockService.getAllGoals(new AsyncCallback<String[][]>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(String[][] goals) {
		        displayStocks(goals);
		      }
		    });
		  }
	private void displayStocks(String[][] goals) {
		    for (String[] theGoal : goals) {
		      displayStock(theGoal);
		    }
		  }
	private void displayStock(final String[] theGoal) {
		  int row = stocksFlexTable.getRowCount();
	      stocksFlexTable.setText(row, 1, theGoal[1]);
	      stocksFlexTable.setText(row, 2, theGoal[0]);
	      stocksFlexTable.setText(row, 3, theGoal[2]);
	      stocksFlexTable.setText(row, 4, theGoal[3]);
	      stocksFlexTable.setText(row, 5, theGoal[4]);
	      Button removeStockButton = new Button("x");
	      Button editStockButton = new Button("Edit");
	      Button interestedStockButton = new Button("Public");
	      stocksFlexTable.setWidget(row, 6, interestedStockButton);
	      stocksFlexTable.setWidget(row, 7, editStockButton);
	      stocksFlexTable.setText(row, 9, theGoal[5]+ " Days");
	      ShareButton fbShare = new ShareButton();
	      fbShare.addClickHandler( new ClickHandler () { 
	          public void onClick(ClickEvent event) { 
	              fbShare ("GOAL TYPE: "+ theGoal[1]+"\n"+ "GOAL: "+theGoal[0]+"\n"+"BUDGET: "+theGoal[2]+"\n"+"START DATE: "+theGoal[3]+"\n"+"END DATE: "+theGoal[4]);
	          } 
	      }); 

	    stocksFlexTable.setWidget(row, 0, fbShare);
	      removeStockButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	removeStock(theGoal[1]);
	        }
	      });
	  }
	private void removeStock(final String symbol) {
	            stockService.removeStock(symbol, new AsyncCallback<Void>() {
	              public void onFailure(Throwable error) {
	              }
	              public void onSuccess(Void ignore) {
	                undisplayStock(symbol);
	              }
	            });    
	          }
	private void undisplayStock(String symbol) {
         int removedIndex = stocks.indexOf(symbol);
         stocks.remove(removedIndex);
         stocksFlexTable.removeRow(removedIndex + 1);
       }    	    	  
	public void fbShare(String myGoal){
		 JSONObject data = new JSONObject (); 
        data.put( "method", new JSONString ( "stream.publish" ) ); 
        data.put( "message", new JSONString ( "Share your Personal Goals with your Friends and Realize them Faster") ); 
         
        JSONObject attachment = new JSONObject (); 
        attachment.put( "name", new JSONString ( "iRelaxa.com" ) ); 
        attachment.put("caption", new JSONString ( "iRelaxa the Goal Match Engine" ) ); 
        attachment.put( "description", new JSONString (myGoal) );  
        attachment.put("href",  new JSONString ( "http://www.irelaxa.com" ) ); 
        
         /* iRelaxa Logo */ 
        JSONObject logoLink = new JSONObject (); 
        logoLink.put ( "type", new JSONString ( "image" ) );  
        logoLink.put ( "src", new JSONString ( "http://www.irelaxa.com/images/logo.png" ) ); 
        logoLink.put ( "href", new JSONString ( "http://www.irelaxa.com" ) ); 
 
        JSONArray logoLinks = new JSONArray (); 
        logoLinks.set(0, logoLink); 
        attachment.put( "media", logoLinks); 
        
        data.put( "attachment", attachment ); 
 
        JSONObject actionLink = new JSONObject (); 
        actionLink.put ( "text", new JSONString ( "track my goal" ) ); 
        actionLink.put ( "href", new JSONString ( "http://www.irelaxa.com" ) ); 
        
        JSONArray actionLinks = new JSONArray (); 
        actionLinks.set(0, actionLink); 
        data.put( "action_links", actionLinks); 
        data.put( "user_message_prompt", new JSONString ( "Share your thoughts abt this goal" ) ); 
        fbCore.ui(data.getJavaScriptObject(), new Callback () ); 
        // fbCore.api ( "/me/friends", new FacebookCallback ( "/me/friends", Ui.INPUT, null ) )
      
	}
	public void loginService() {
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(LoginInfo result) {
		        loginInfo = result;
		        if(loginInfo.isLoggedIn()) {
		        	signOutLink.setHref(loginInfo.getLogoutUrl());
		        	RootPanel.get("logout").clear();
		        	RootPanel.get("logout").add(signOutLink);
		        } else {
		          loadLogin();
		        }
		      }
		    });
	}
	private void loadLogin() {
		    signInLink.setHref(loginInfo.getLoginUrl());
		    RootPanel.get("logout").clear();
		    RootPanel.get("logout").add(signInLink);
		  }
/*
 * http://developers.facebook.com/docs/reference/api/
 */
/*OBJECTS
 * Users: https://graph.facebook.com/btaylor (Bret Taylor)
Pages: https://graph.facebook.com/cocacola (Coca-Cola page)
Events: https://graph.facebook.com/251906384206 (Facebook Developer Garage Austin)
Groups: https://graph.facebook.com/2204501798 (Emacs users group)
Applications: https://graph.facebook.com/2439131959 (the Graffiti app)
Status messages: https://graph.facebook.com/367501354973 (A status message from Bret)
Photos: https://graph.facebook.com/98423808305 (A photo from the Coca-Cola page)
Photo albums: https://graph.facebook.com/99394368305 (Coca-Cola's wall photos)
Profile pictures: http://graph.facebook.com/sumithraj/picture (your profile picture)
Videos: https://graph.facebook.com/614004947048 (A Facebook tech talk on Tornado)
Notes: https://graph.facebook.com/122788341354 (Note announcing Facebook for iPhone 3.0)
Check-ins: https://graph.facebook.com/414866888308 (Check-in at a pizzeria)
 */
/*CONNECTIONS
 *  can examine the connections between objects using the URL structure https://graph.facebook.com/ID/CONNECTION_TYPE. The connections supported for people and pages include:

Friends: https://graph.facebook.com/me/friends
News feed: https://graph.facebook.com/me/home
Profile feed (Wall): https://graph.facebook.com/me/feed
Likes: https://graph.facebook.com/me/likes
Movies: https://graph.facebook.com/me/movies
Books: https://graph.facebook.com/me/books
Notes: https://graph.facebook.com/me/notes
Photo Tags: https://graph.facebook.com/me/photos
Photo Albums: https://graph.facebook.com/me/albums
Video Tags: https://graph.facebook.com/me/videos
Video Uploads: https://graph.facebook.com/me/videos/uploaded
Events: https://graph.facebook.com/me/events
Groups: https://graph.facebook.com/me/groups
Check-ins: https://graph.facebook.com/me/checkins
 */
}