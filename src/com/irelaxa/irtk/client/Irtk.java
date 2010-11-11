package com.irelaxa.irtk.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

//import widgets.client.net.davesplanet.PaypalPanel;

import com.irelaxa.irtk.client.TopBarView;
import com.irelaxa.irtk.client.TopBarViewListener;
//import com.irelaxa.irtk.server.Goal;
import com.irelaxa.irtk.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
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
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.datepicker.client.DateBox;

import com.gwtfb.client.Callback;
//import com.gwtfb.client.FrontpageViewController;
//import com.gwtfb.client.HomeSideBarPanel;
import com.gwtfb.sdk.FBCore;
import com.gwtfb.sdk.FBEvent;
import com.gwtfb.sdk.FBXfbml;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Irtk extends TopBarViewListener implements EntryPoint, ValueChangeHandler<String> {
	 /* Api key defined in facebook.
     */
    public static String API_KEY = "500f12576f276dd7b9d6531676773905";
//  public static String API_KEY = "074415396e1d9a2fa2df06932c16e238";//irelaxa1 -surabhi
    //  public static String API_KEY = "34f794398e38cbfdd6a6272074b1e40f";//irelaxa2-sneha goal
//  public static String API_KEY = "8415c864e0ebcebb46cd22da4809a000";//irelaxa3-miki
//  public static String API_KEY = "44f53402d12ca00c5d9c58303130946d";//irelaxa4-anamika
 // public static String API_KEY = "2b916d69577e3568c7fe1735694836c5";//irelaxa5-akash
  //  public static String API_KEY = "aa118b5409819a2e292d93eb40acacc6";//irelaxa6-ravi ojha
    //  public static String API_KEY = "ef26976334e433773f04c5bab5587d54";//irelaxa7-manish
    //  public static String API_KEY = "f5fe9c205c1047f8506960409a3859ed";//irelaxa8-rohit
    //  public static String API_KEY = "4e8828167d986f33642fab120159292a";//irelaxa9-vibhu
//  public static String API_KEY = "a3294ca1b2ad4df64af1d63c72058a51";//irelaxa10-asha
//  public static String API_KEY = "39fea4503f276bdea1ac03d1eae33e50";//irelaxa11-arijit
//  public static String API_KEY = "3643b0d6f8a8023bb64d4e43849e449d";//irelaxa12-mukul
   Long removedId = null;
    private String[][] datingGoals1;
   private FBCore fbCore = GWT.create(FBCore.class);
   private FBEvent fbEvent = GWT.create(FBEvent.class);
   private FBXfbml fbXfbml = GWT.create(FBXfbml.class);
   private boolean status = true;
   private boolean xfbml = true;
   private boolean cookie = true;
   private HTML goalmatch = new HTML("<h3 valign='top' halign='right'><font color='#008000'>Goal Match Engine</font></h1>");
   
	    public Stack goalObject = new Stack();
	    public final HorizontalPanel topHoriPanel = new HorizontalPanel();
	    private Label bugetLable = new Label("Budget");
		private Label goalLable = new Label("Select Your Goal");
		//private AbsolutePanel budgetPanel = new AbsolutePanel();
	//	private AbsolutePanel goalPanel = new AbsolutePanel();
		private ListBox subGoalTypeDropBox = new ListBox(true);
		private ListBox goalTypeDropBox = new ListBox(false);
		final ListBox budgetDropBox = new ListBox(false);
		// Create a DateBox
	    DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
	    DateBox sdateBox = new DateBox();
	    DateBox edateBox = new DateBox();
		public final FlexTable goalTable = new FlexTable();
		private FlexTable stocksFlexTable = new FlexTable();
		private FlexTable productsFlexTable = new FlexTable();
		private FlexTable datingProblemFlexTable = new FlexTable();
		private FlexTable internsFlexTable = new FlexTable();
		private FlexTable internshipsFlexTable = new FlexTable();
		private FlexTable myInternshipsFlexTable = new FlexTable();
		private FlexTable myMentorshipsFlexTable = new FlexTable();
		private FlexTable myCareerManagerFlexTable = new FlexTable();
		private FlexTable mentorshipsFlexTable = new FlexTable();
		private DatingDiaryView datingDiaryView = new DatingDiaryView();
		FlexCellFormatter cellFormatter = goalTable.getFlexCellFormatter();
		private Button sendButton = new Button("Save & Match My Goal");
		private ListBox listBox1 = new ListBox();
		private ArrayList<String> stocks = new ArrayList<String>();
		public LoginInfo loginInfo = null;
		private Label loginLabel = new Label("Please sign in to your Google Account to access the iRelaxa application.");
		  private Anchor signInLink = new Anchor("Sign In");
		  private Anchor signOutLink = new Anchor("Sign Out");
		/*  private Anchor internEvent = new Anchor("Intern Goal Match Event");*/
		  private final StockServiceAsync stockService = GWT.create(StockService.class);
		  private final LoginServiceAsync loginService = GWT.create(LoginService.class);
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
	    
	    
	    final String[] nutrition = {"Weight Loss", "Vegan", "Low Carb", "High Protien", "Less Fat", "Low Sugar", "Less Red Meat"};
	    final String[] fitness = {"Toning", "Lower Body", "Upper Body", "Core", "Flat Abs", "Arms", "Deltoids", "Altoids", "Yoga", "Pilates"};
	    final String[] dating = {"Have Fun", "Committed Relationship", "Marriage"};
	    final String[] fashion = {"Business Casual", "Causal", "Sexy"};
	    final String[] career = {"Doctor", "Engineer", "Lawyer", "Musicain", "Actress", "Writer", "Nurse", "Teacher", "Sales&Marketing", "Computer Science"};
	    final String[] admission = {"Graduate School", "UnderGraduate", "PhD"};
	   // final String[] travel = {"next 6 months", "next 2 years", "next 5 years"};
	   // final String[] events = {"Family Events", "Career Events", "Sports Events", "Team Building"};
	  //  final String[] fun = {"Movies", "Picnics", "Clubbing", "TV Shows", "Hiking", "Cycling", "Flying", "Cooking","Dancing", "Singing", "Shopping", "Reading"};
	    final String[] creativity = {"Imagination", "Free Ideas", "Creative Reading", "Creative Writing", "Painting", "Song Writing", "Acting"};
	    
	  //  Panel panel = RootPanel.get("chart");
	  //  SimpleViz PIE = new SimpleViz(panel);
	   
	   
	      TopBarView topBar;
	  /**
	   * Entry point method.
	   */
	  public void onModuleLoad() {
		  History.addValueChangeHandler(this);
		  addGoalType();
			showGoalDates();
		  fbCore.init(API_KEY, status, cookie, xfbml);
			fbLogin();
			// Obtain the credentials from your configs 
			/*APICredential credentialObj = new APICredential();
			credentialObj.setAPIUsername(getServletConfig()
				    .getInitParameter("PPAPIUsername"));*/
		loadGoalTableHeading();
		/* Display stocks...*/
		loginService();
		topBar = new TopBarView(this);
		//what if i set the menu account here?
		RootPanel.get("topView").add(topBar);
	//	RootPanel.get("logoid").add(new VisualizationWidget().getInstance());
		
		loadStocks();
		loadProductTableHeading();
		loadDatingProblemTableHeading();
		loadProducts();
		loadDatingGoals();
		loadInternTableHeading();
		loadAllInterns();
		loadInternshipTableHeading();
		loadAllInternships();
		loadMentorshipTableHeading();
		loadAllMentorships();
		loadMyInternGoals();
		loadMyInternshipsBiz();
		loadMyMentorships();
			goalTable.addStyleName("cw-FlexTable");
			goalTable.setWidth("32em");
			goalTable.setCellSpacing(5);
		    goalTable.setCellPadding(3);
		 // Add some text
		    cellFormatter.setHorizontalAlignment(0, 1,
		        HasHorizontalAlignment.ALIGN_LEFT);
		   
		 // ...and set it's column span so that it takes up the whole row.
		    goalTable.getFlexCellFormatter().setColSpan(1, 0, 2);
		     //add goal Type Drop Box widget
			 edateBox.addValueChangeHandler(new ValueChangeHandler<Date>(){
				public void onValueChange(ValueChangeEvent<Date> event){
					if(sdateBox.getValue().after(edateBox.getValue())){
					 Window.alert("Goal End Date is before Goal Start Date");
					}
				}
			});
			// We can add style names to widgets
			sendButton.addStyleName("sendButton");
			sendButton.addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			        	//addStock(symbol, goalType, budgetDropBox.getValue(budgetDropBox.getSelectedIndex()),sdateBox.getValue(),edateBox.getValue() );
			    	  //date validation
			   
			    	  
			    	  goalObject.clear();
			    	  goalObject.push(edateBox.getValue());
			    	  goalObject.push(sdateBox.getValue());
			    	  goalObject.push(budgetDropBox.getValue(budgetDropBox.getSelectedIndex()));
			    	  goalObject.push(listBox1.getValue(listBox1.getSelectedIndex()));
			    	  goalObject.push(goalTypeDropBox.getValue(goalTypeDropBox.getSelectedIndex()));
			    	  
			    	  /*goalPOJO.setBudget(budgetDropBox.getValue(budgetDropBox.getSelectedIndex()));
			    	  goalPOJO.setGoalType(goalTypeDropBox.getValue(goalTypeDropBox.getSelectedIndex()));
			    	  goalPOJO.setSymbol(listBox1.getValue(listBox1.getSelectedIndex()));
			    	  goalPOJO.setStartDate(sdateBox.getValue());
			    	  goalPOJO.setEndDate(edateBox.getValue());*/
			    	  afterGoalSave();
			    	  addStock(goalObject, "goal");
			    	 // getMatchingProduct();   
			      }
			    });	
			topBar.getVendorFormView().getregisterProductButton().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			        	//addStock(symbol, goalType, budgetDropBox.getValue(budgetDropBox.getSelectedIndex()),sdateBox.getValue(),edateBox.getValue() );
			    	  goalObject.clear();
			    	 // Window.alert("The vendor add button is clicked");
			    	  goalObject.push(topBar.getVendorFormView().getPrice());
			    	  goalObject.push(topBar.getVendorFormView().getProduct());
			    	  goalObject.push(topBar.getVendorFormView().getBusinessName());
			    	  addStock(goalObject, "product");  
			    	  onShowAllMyProductsSelect();
			      }
			    }
			);
			
			
			
			topBar.getCareerManagerView().getSubmitCareerManagerButton().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  goalObject.clear();
			    	  goalObject.push(topBar.getCareerManagerView().getDegreePursuing());
			    	  goalObject.push(topBar.getCareerManagerView().getUniversityName());
			    	  goalObject.push(topBar.getCareerManagerView().getLastName());
			    	  goalObject.push(topBar.getCareerManagerView().getFirstName());
			    	  goalObject.push(topBar.getCareerManagerView().getEdateBox().getValue());
			    	  goalObject.push(topBar.getCareerManagerView().getSdateBox().getValue());
			    	  goalObject.push(topBar.getCareerManagerView().getPrice());
			    	  goalObject.push(topBar.getCareerManagerView().getTime());
			    	  goalObject.push(topBar.getCareerManagerView().getShortTermArea());
			    	  goalObject.push(topBar.getCareerManagerView().getLongTermArea());
			    	  addStock(goalObject, "careermanager");
			    	  loadMyCareerManagerTableHeading();
			    	  String longTerm = topBar.getCareerManagerView().getLongTermArea();
			    	  String shortTerm = topBar.getCareerManagerView().getShortTermArea();
			    	  String time = topBar.getCareerManagerView().getTime();
			    	  String price = topBar.getCareerManagerView().getPrice();
			    	  String sDate = topBar.getCareerManagerView().getSdateBox().getValue().toString();
			    	  String eDate = topBar.getCareerManagerView().getEdateBox().getValue().toString();
			    	  String fName = topBar.getCareerManagerView().getFirstName();
			    	  String lName = topBar.getCareerManagerView().getLastName();
			    	  String univ = topBar.getCareerManagerView().getUniversityName();
			    	  String degree = topBar.getCareerManagerView().getDegreePursuing();
			    	  myCareerManagerFlexTable.setText(1, 0, longTerm);
			    	  myCareerManagerFlexTable.setText(1, 1, shortTerm);
			    	  myCareerManagerFlexTable.setText(1, 2, time);
			    	  myCareerManagerFlexTable.setText(1, 3, price);
			    	  myCareerManagerFlexTable.setText(1, 4, sDate);
			    	  myCareerManagerFlexTable.setText(1, 5, eDate);
			    	  myCareerManagerFlexTable.setText(1, 6, fName);
			    	  myCareerManagerFlexTable.setText(1, 7, lName);
			    	  myCareerManagerFlexTable.setText(1, 8, univ);
			    	  myCareerManagerFlexTable.setText(1, 9, degree); 
			    	  Button removeStockButton = new Button("x");
				      Button editStockButton = new Button("Edit");
				      Button interestedStockButton = new Button("Interested");
				      myCareerManagerFlexTable.setWidget(1, 10, interestedStockButton);
				      myCareerManagerFlexTable.setWidget(1, 11, editStockButton);
				      myCareerManagerFlexTable.setWidget(1, 12, removeStockButton);
			    	  onSaveMyCareerManager();
			    	  topBar.getCareerManagerView().setShortTermAreaNull();
			    	  topBar.getCareerManagerView().setLongTermAreaNull();
			    	  topBar.getCareerManagerView().setTimeNull();
			    	  topBar.getCareerManagerView().setPriceNull();
			    	  topBar.getCareerManagerView().setSdateBoxNull();
			    	  topBar.getCareerManagerView().setEdateBoxNull();
			    	  topBar.getCareerManagerView().setFirstNameNull();
			    	  topBar.getCareerManagerView().setLastNameNull();
			    	  topBar.getCareerManagerView().setUniversityNameNull();
			    	  topBar.getCareerManagerView().setDegreePursuingNull();
			      }
			    });
			topBar.getInternshipBizView().getSubmitInternshipBizButton().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  goalObject.clear();
			    	  goalObject.push(topBar.getInternshipBizView().getContactName());
			    	  goalObject.push(topBar.getInternshipBizView().getBusinessName());
			    	  goalObject.push(topBar.getInternshipBizView().getEdateBox().getValue());
			    	  goalObject.push(topBar.getInternshipBizView().getSdateBox().getValue());
			    	  goalObject.push(topBar.getInternshipBizView().getPay());
			    	  goalObject.push(topBar.getInternshipBizView().getProject());
			    	  goalObject.push(topBar.getInternshipBizView().getTime());
			    	  goalObject.push(topBar.getInternshipBizView().getSkills());
			    	  addStock(goalObject, "internbiz");
			    	  loadMyInternshipTableHeading();
			    	  String longTerm = topBar.getInternshipBizView().getSkills();
			    	  String shortTerm = topBar.getInternshipBizView().getTime();
			    	  String time = topBar.getInternshipBizView().getProject();
			    	  String price = topBar.getInternshipBizView().getPay();
			    	  String sDate = topBar.getInternshipBizView().getSdateBox().getValue().toString();
			    	  String eDate = topBar.getInternshipBizView().getEdateBox().getValue().toString();
			    	  String fName = topBar.getInternshipBizView().getBusinessName();
			    	  String lName = topBar.getInternshipBizView().getContactName();
			    	  myInternshipsFlexTable.setText(1, 0, longTerm);
			    	  myInternshipsFlexTable.setText(1, 1, shortTerm);
			    	  myInternshipsFlexTable.setText(1, 2, time);
			    	  myInternshipsFlexTable.setText(1, 3, price);
			    	  myInternshipsFlexTable.setText(1, 4, sDate);
			    	  myInternshipsFlexTable.setText(1, 5, eDate);
			    	  myInternshipsFlexTable.setText(1, 6, fName);
			    	  myInternshipsFlexTable.setText(1, 7, lName);
			    	  Button removeStockButton = new Button("x");
				      Button editStockButton = new Button("Edit");
				      Button interestedStockButton = new Button("Interested");
				      myInternshipsFlexTable.setWidget(1, 8, interestedStockButton);
				      myInternshipsFlexTable.setWidget(1, 9, editStockButton);
				      myInternshipsFlexTable.setWidget(1, 10, removeStockButton);
			    	  onSaveMyInternships();
			    	  topBar.getInternshipBizView().setSkillsNull();
			    	  topBar.getInternshipBizView().setTimeNull();
			    	  topBar.getInternshipBizView().setProjectNull();
			    	  topBar.getInternshipBizView().setPayNull();
			    	  topBar.getInternshipBizView().setSdateBoxNull();
			    	  topBar.getInternshipBizView().setEdateBoxNull();
			    	  topBar.getInternshipBizView().setBusinessNameNull();
			    	  topBar.getInternshipBizView().setContactNameNull();
			      }
			    });
			topBar.getMentorView().getMentorSaveButton().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  goalObject.clear();
			    	  goalObject.push(topBar.getMentorView().getContactName());
			    	  goalObject.push(topBar.getMentorView().getBusinessName());
			    	  goalObject.push(topBar.getMentorView().getEdateBox().getValue());
			    	  goalObject.push(topBar.getMentorView().getSdateBox().getValue());
			    	  goalObject.push(topBar.getMentorView().getMentorDescription());
			    	  goalObject.push(topBar.getMentorView().getMenteeDescription());
			    	  goalObject.push(topBar.getMentorView().getTime());
			    	  goalObject.push(topBar.getMentorView().getSkills());
			    	  addStock(goalObject, "mentorbiz");
			    	  loadMyMentorshipTableHeading();
			    	  String longTerm = topBar.getMentorView().getSkills();
			    	  String shortTerm = topBar.getMentorView().getTime();
			    	  String time = topBar.getMentorView().getMenteeDescription();
			    	  String price = topBar.getMentorView().getMentorDescription();
			    	  String sDate = topBar.getMentorView().getSdateBox().getValue().toString();
			    	  String eDate = topBar.getMentorView().getEdateBox().getValue().toString();
			    	  String fName = topBar.getMentorView().getBusinessName();
			    	  String lName = topBar.getMentorView().getContactName();
			    	  myMentorshipsFlexTable.setText(1, 0, longTerm);
			    	  myMentorshipsFlexTable.setText(1, 1, shortTerm);
			    	  myMentorshipsFlexTable.setText(1, 2, time);
			    	  myMentorshipsFlexTable.setText(1, 3, price);
			    	  myMentorshipsFlexTable.setText(1, 4, sDate);
			    	  myMentorshipsFlexTable.setText(1, 5, eDate);
			    	  myMentorshipsFlexTable.setText(1, 6, fName);
			    	  myMentorshipsFlexTable.setText(1, 7, lName);
			    	  Button removeStockButton = new Button("x");
				      Button editStockButton = new Button("Edit");
				      Button interestedStockButton = new Button("Interested");
				      myMentorshipsFlexTable.setWidget(1, 8, interestedStockButton);
				      myMentorshipsFlexTable.setWidget(1, 9, editStockButton);
				      myMentorshipsFlexTable.setWidget(1, 10, removeStockButton);
			    	  onSaveMyMentorships();
			    	  topBar.getMentorView().setSkillsNull();
			    	  topBar.getMentorView().setTimeNull();
			    	  topBar.getMentorView().setMenteeDescriptionNull();
			    	  topBar.getMentorView().setMentorDescriptionNull();
			    	  topBar.getMentorView().setSdateBoxNull();
			    	  topBar.getMentorView().setEdateBoxNull();
			    	  topBar.getMentorView().setBusinessNameNull();
			    	  topBar.getMentorView().setContactNameNull();
			    	
			      }
			    });
			topBar.getCareerManagerView().getInternshipsLink().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  onShowAllInternships();
			      }
			    });
			topBar.getCareerManagerView().getMentorshipsLink().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  onShowAllMentorships();
			      }
			    });
			topBar.getInternshipBizView().getInternsLink().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  onShowAllInterns();
			      }
			    });
			topBar.getMentorView().getInternsLink().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  onShowAllInterns();
			      }
			    });
			topBar.getCareerManagerView().getMyCareerGoalsLink().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  onSaveMyCareerManager();
			      }
			    });
			topBar.getInternshipBizView().getMyInternshipBizLink().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  onSaveMyInternships();
			      }
			    });
			topBar.getMentorView().getMyMentorshipsLink().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  onSaveMyMentorships();
			      }
			    });
			
			/*internEvent.addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  loadInternEvent();
			      }
			    });*/
			 /*fbLogOut.addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) 
			      {
			    	 // fbCore.getLoginStatus(fbLogoutCallback);
			    	 fbCore.logout(fbLogoutCallback);
			    	  
			      }
			 });*/
			topBar.getInternGoalMatchingEvent().getSubmitCompanyButton().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  onGoalWordsSelect(topBar.getInternshipBizView());
			      }
			    });
			topBar.getInternGoalMatchingEvent().getSubmitInternButton().addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  onGoalWordsSelect(topBar.getCareerManagerView());
			      }
			    });
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
		        	//renderWhenLogged();
		        } else {
		          loadLogin();
		        }
		      }
		    });
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
	private void loadProductTableHeading() {
		// Create table for stock data.
		    productsFlexTable.setHTML(0, 0, "<h3>Business Name</h3>");
		    productsFlexTable.setHTML(0, 1, "<h3>Product Type</h3>");
		    productsFlexTable.setHTML(0, 2, "<h3>Price</h3>");
		    productsFlexTable.setHTML(0, 5, "<h3>Remove Product</h3>'");
	}
	private void loadDatingProblemTableHeading() {
		// Create table for stock data.
		//datingProblemFlexTable.setWidget(0, 0, topBar.getDatingView().getMyDatingGoalLink());
		//datingProblemFlexTable.setWidget(0, 1, topBar.getDatingView().getMyDatingProblemsLink());
		    datingProblemFlexTable.setHTML(1, 0, "<h3>Problem Area</h3>");
		    datingProblemFlexTable.setHTML(1, 1, "<h3>TimeWasted</h3>");
		    datingProblemFlexTable.setHTML(1, 2, "<h3>Money Wasted</h3>");
		    datingProblemFlexTable.setHTML(1, 3, "<h3>Emotion</h3>'");
		    datingProblemFlexTable.setHTML(1, 4, "<h3>Career Impact</h3>'");
	}
	private void loadInternTableHeading() {
		// Create table for stock data.
		internsFlexTable.setHTML(0, 0, "<h3>Long Term Goals</h3>");
		internsFlexTable.setHTML(0, 1, "<h3>Short Term Goals</h3>'");
		internsFlexTable.setHTML(0, 2, "<h3>Hours of work</h3>");
		internsFlexTable.setHTML(0, 3, "<h3>Stipend</h3>");
		    internsFlexTable.setHTML(0, 4, "<h3>Start Date</h3>");
			  internsFlexTable.setHTML(0, 5, "<h3>End Date</h3>");
			  internsFlexTable.setHTML(0, 6, "<h3>First Name</h3>");
			  internsFlexTable.setHTML(0, 7, "<h3>Last Name</h3>");
			  internsFlexTable.setHTML(0, 8, "<h3>University</h3>");
			  internsFlexTable.setHTML(0, 9, "<h3>Degree</h3>");
	}
	private void loadMyCareerManagerTableHeading() {
		// Create table for stock data.
		myCareerManagerFlexTable.setHTML(0, 0, "<h3>Long Term Goals</h3>");
		myCareerManagerFlexTable.setHTML(0, 1, "<h3>Short Term Goals</h3>");
		myCareerManagerFlexTable.setHTML(0, 2, "<h3>Hours of work</h3>");
		myCareerManagerFlexTable.setHTML(0, 3, "<h3>Stipend</h3>");
		myCareerManagerFlexTable.setHTML(0, 4, "<h3>Start Date</h3>");
		myCareerManagerFlexTable.setHTML(0, 5, "<h3>End Date</h3>");
		myCareerManagerFlexTable.setHTML(0, 6, "<h3>First Name</h3>");
		myCareerManagerFlexTable.setHTML(0, 7, "<h3>Last Name</h3>");
		myCareerManagerFlexTable.setHTML(0, 8, "<h3>University</h3>");
		myCareerManagerFlexTable.setHTML(0, 9, "<h3>Degree</h3>");
	}
	private void loadInternshipTableHeading() {
		// Create table for stock data.
		internshipsFlexTable.setHTML(0, 0, "<h3>Skills</h3>");
		internshipsFlexTable.setHTML(0, 1, "<h3>Hours</h3>");
		internshipsFlexTable.setHTML(0, 2, "<h3>Work Description</h3>");
		internshipsFlexTable.setHTML(0, 3, "<h3>Stipend</h3>");
		internshipsFlexTable.setHTML(0, 4, "<h3>Start Date</h3>");
		internshipsFlexTable.setHTML(0, 5, "<h3>End Date</h3>");
		internshipsFlexTable.setHTML(0, 6, "<h3>Company Name</h3>");
		internshipsFlexTable.setHTML(0, 7, "<h3>Contact</h3>");
	}
	private void loadMyInternshipTableHeading() {
		// Create table for stock data.
		myInternshipsFlexTable.setHTML(0, 0, "<h3>Skills</h3>");
		myInternshipsFlexTable.setHTML(0, 1, "<h3>Hours</h3>");
		myInternshipsFlexTable.setHTML(0, 2, "<h3>Work Description</h3>");
		myInternshipsFlexTable.setHTML(0, 3, "<h3>Stipend</h3>");
		myInternshipsFlexTable.setHTML(0, 4, "<h3>Start Date</h3>");
		myInternshipsFlexTable.setHTML(0, 5, "<h3>End Date</h3>");
		myInternshipsFlexTable.setHTML(0, 6, "<h3>Company Name</h3>");
		myInternshipsFlexTable.setHTML(0, 7, "<h3>Contact</h3>");
	}
	private void loadMentorshipTableHeading() {
		// Create table for stock data.
		mentorshipsFlexTable.setHTML(0, 0, "<h3>Skills</h3>");
		mentorshipsFlexTable.setHTML(0, 1, "<h3>Hours</h3>");
		mentorshipsFlexTable.setHTML(0, 2, "<h3>Mentee Description</h3>");
		mentorshipsFlexTable.setHTML(0, 3, "<h3>Mentor Description</h3>");
		mentorshipsFlexTable.setHTML(0, 4, "<h3>Start Date</h3>");
		mentorshipsFlexTable.setHTML(0, 5, "<h3>End Date</h3>");
		mentorshipsFlexTable.setHTML(0, 6, "<h3>Company Name</h3>");
		mentorshipsFlexTable.setHTML(0, 7, "<h3>Contact</h3>");
	}
	private void loadMyMentorshipTableHeading() {
		// Create table for stock data.
		myMentorshipsFlexTable.setHTML(0, 0, "<h3>Skills</h3>");
		myMentorshipsFlexTable.setHTML(0, 1, "<h3>Hours</h3>");
		myMentorshipsFlexTable.setHTML(0, 2, "<h3>Mentee Description</h3>");
		myMentorshipsFlexTable.setHTML(0, 3, "<h3>Mentor Description</h3>");
		myMentorshipsFlexTable.setHTML(0, 4, "<h3>Start Date</h3>");
		myMentorshipsFlexTable.setHTML(0, 5, "<h3>End Date</h3>");
		myMentorshipsFlexTable.setHTML(0, 6, "<h3>Company Name</h3>");
		myMentorshipsFlexTable.setHTML(0, 7, "<h3>Contact</h3>");
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
	  private void loadProducts() {
		    stockService.getProducts(new AsyncCallback<String[][]>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(String[][] products) {
		        displayProducts(products);
		      }
		    });
		  }
	  private void loadDatingProblems() {
		    stockService.getDatingProblems(new AsyncCallback<String[][]>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(String[][] datingProblems) {
		        displayDatingProblems(datingProblems);
		      }
		    });
		  }
	  private void loadAllInterns() {
		    stockService.getAllInterns(new AsyncCallback<String[][]>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(String[][] interns) {
		    	 
		        displayInterns(interns);
		      }
		    });
		  }
	  private void loadAllInternships() {
		    stockService.getAllInternships(new AsyncCallback<String[][]>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(String[][] internships) {
		        displayInternships(internships);
		      }
		    });
		  }
	  private void loadAllMentorships() {
		    stockService.getAllMentorships(new AsyncCallback<String[][]>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(String[][] mentorships) {
		        displayMentorships(mentorships);
		      }
		    });
		  }
	  private void loadMyInternGoals() {
		    stockService.getMyCareerGoals(new AsyncCallback<String[][]>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(String[][] mentorships) {
		        displayMyInternGoals(mentorships);
		      }
		    });
		  }
	  private void loadMyInternshipsBiz() {
		    stockService.getMyInternshipsBizGoals(new AsyncCallback<String[][]>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(String[][] mentorships) {
		        displayMyInternshipsBiz(mentorships);
		      }
		    });
		  }
	  private void loadMyMentorships() {
		    stockService.getMyMentorshipsBizGoals(new AsyncCallback<String[][]>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(String[][] mentorships) {
		        displayMyMentorships(mentorships);
		      }
		    });
		  }
	  private void loadDatingGoals() {
		    stockService.getDatingGoals(new AsyncCallback<String[][]>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(String[][] datingGoals) {
		        datingGoals1 = datingGoals;
		      }
		    });
		  }
	  
	  private void displayProducts(String[][] products) {
		    for (String[] theProduct : products) {
		      displayProduct(theProduct);
		    }
		  }
	  private void displayDatingProblems(String[][] datingProblems) {
		    for (String[] theDatingProblem : datingProblems) {
		      displayDatingProblem(theDatingProblem);
		    }
		  }
	  private void displayInterns(String[][] interns) {
		    for (String[] theIntern : interns) {
		      displayIntern(theIntern);
		    }
		  }
	  private void displayInternships(String[][] internships) {
		    for (String[] theInternship : internships) {
		      displayInternship(theInternship);
		    }
		  }
	  private void displayMentorships(String[][] mentorships) {
		    for (String[] theMentorship : mentorships) {
		      displayMentorship(theMentorship);
		    }
		  }
	  private void displayMyInternGoals(String[][] mentorships) {
		    for (String[] theMentorship : mentorships) {
		      displayMyInternGoals(theMentorship);
		    }
		  }
	  private void displayMyInternshipsBiz(String[][] mentorships) {
		    for (String[] theMentorship : mentorships) {
		      displayMyInternshipsBiz(theMentorship);
		    }
		  }
	  private void displayMyMentorships(String[][] mentorships) {
		    for (String[] theMentorship : mentorships) {
		      displayMyMentorships(theMentorship);
		    }
		  }
	  
	  private void displayProduct(final String[] theProduct) {
		  int row = productsFlexTable.getRowCount();
		  productsFlexTable.setText(row, 0, theProduct[0]);
		  productsFlexTable.setText(row, 1, theProduct[1]);
		  productsFlexTable.setText(row, 2, theProduct[2]);
		  Button removeStockButton = new Button("x");
	      Button editStockButton = new Button("Edit");
	      Button interestedStockButton = new Button("Interested");
	      productsFlexTable.setWidget(row, 3, interestedStockButton);
	      productsFlexTable.setWidget(row, 4, editStockButton);
	      productsFlexTable.setWidget(row, 5, removeStockButton);
	  }
	  
	  private void displayDatingProblem(final String[] theDatingProblem) {
		  int row = datingProblemFlexTable.getRowCount();
		  datingProblemFlexTable.setText(row, 0, theDatingProblem[0]);
		  datingProblemFlexTable.setText(row, 1, theDatingProblem[1]);
		  datingProblemFlexTable.setText(row, 2, theDatingProblem[2]);
		  datingProblemFlexTable.setText(row, 3, theDatingProblem[3]);
		  datingProblemFlexTable.setText(row, 4, theDatingProblem[4]);
		  Button removeStockButton = new Button("x");
	      Button editStockButton = new Button("Edit");
	      Button interestedStockButton = new Button("Interested");
	      datingProblemFlexTable.setWidget(row, 5, interestedStockButton);
	      datingProblemFlexTable.setWidget(row, 6, editStockButton);
	      datingProblemFlexTable.setWidget(row, 7, removeStockButton);
	  }
	  
	  private void displayIntern(final String[] theIntern) {
		 
		  int row = internsFlexTable.getRowCount();
		  internsFlexTable.setText(row, 0, theIntern[0]);
		  internsFlexTable.setText(row, 1, theIntern[1]);
		  internsFlexTable.setText(row, 2, theIntern[2]);
		  internsFlexTable.setText(row, 3, theIntern[3]);
		  internsFlexTable.setText(row, 4, theIntern[4]);
		  internsFlexTable.setText(row, 5, theIntern[5]);
		  internsFlexTable.setText(row, 6, theIntern[6]);
		  internsFlexTable.setText(row, 7, theIntern[7]);
		  internsFlexTable.setText(row, 8, theIntern[8]);
		  internsFlexTable.setText(row, 9, theIntern[9]);
		  Button removeStockButton = new Button("x");
	      Button editStockButton = new Button("Edit");
	      Button interestedStockButton = new Button("Interested");
	      internsFlexTable.setWidget(row, 10, interestedStockButton);
	      internsFlexTable.setWidget(row, 11, editStockButton);
	      internsFlexTable.setWidget(row, 12, removeStockButton);
		  
	  }
	  private void displayInternship(final String[] theInternship) {
		  int row = internshipsFlexTable.getRowCount();
		  internshipsFlexTable.setText(row, 0, theInternship[0]);
		  internshipsFlexTable.setText(row, 1, theInternship[1]);
		  internshipsFlexTable.setText(row, 2, theInternship[2]);
		  internshipsFlexTable.setText(row, 3, theInternship[3]);
		  internshipsFlexTable.setText(row, 4, theInternship[4]);
		  internshipsFlexTable.setText(row, 5, theInternship[5]);
		  internshipsFlexTable.setText(row, 6, theInternship[6]);
		  internshipsFlexTable.setText(row, 7, theInternship[7]);
		  Button removeStockButton = new Button("x");
	      Button editStockButton = new Button("Edit");
	      Button interestedStockButton = new Button("Interested");
	      internshipsFlexTable.setWidget(row, 8, interestedStockButton);
	      internshipsFlexTable.setWidget(row, 9, editStockButton);
	      internshipsFlexTable.setWidget(row, 10, removeStockButton);
	
	  }
	  private void displayMentorship(final String[] theMentorship) {
		  int row = mentorshipsFlexTable.getRowCount();
		  mentorshipsFlexTable.setText(row, 0, theMentorship[0]);
		  mentorshipsFlexTable.setText(row, 1, theMentorship[1]);
		  mentorshipsFlexTable.setText(row, 2, theMentorship[2]);
		  mentorshipsFlexTable.setText(row, 3, theMentorship[3]);
		  mentorshipsFlexTable.setText(row, 4, theMentorship[4]);
		  mentorshipsFlexTable.setText(row, 5, theMentorship[5]);
		  mentorshipsFlexTable.setText(row, 6, theMentorship[6]);
		  mentorshipsFlexTable.setText(row, 7, theMentorship[7]);
		  Button removeStockButton = new Button("x");
	      Button editStockButton = new Button("Edit");
	      Button interestedStockButton = new Button("Interested");
	      mentorshipsFlexTable.setWidget(row, 8, interestedStockButton);
	      mentorshipsFlexTable.setWidget(row, 9, editStockButton);
	      mentorshipsFlexTable.setWidget(row, 10, removeStockButton);
		  
	  }
	  private void displayMyInternGoals(final String[] theMentorship) {
		  int row = myCareerManagerFlexTable.getRowCount();
		  myCareerManagerFlexTable.setText(row, 0, theMentorship[0]);
		  myCareerManagerFlexTable.setText(row, 1, theMentorship[1]);
		  myCareerManagerFlexTable.setText(row, 2, theMentorship[2]);
		  myCareerManagerFlexTable.setText(row, 3, theMentorship[3]);
		  myCareerManagerFlexTable.setText(row, 4, theMentorship[4]);
		  myCareerManagerFlexTable.setText(row, 5, theMentorship[5]);
		  myCareerManagerFlexTable.setText(row, 6, theMentorship[6]);
		  myCareerManagerFlexTable.setText(row, 7, theMentorship[7]);
		  myCareerManagerFlexTable.setText(row, 8, theMentorship[6]);
		  myCareerManagerFlexTable.setText(row, 9, theMentorship[7]);
		  Button removeStockButton = new Button("x");
	      Button editStockButton = new Button("Edit");
	      myCareerManagerFlexTable.setWidget(row, 10, editStockButton);
	      myCareerManagerFlexTable.setWidget(row, 11, removeStockButton);
		  
	  }
	  private void displayMyInternshipsBiz(final String[] theMentorship) {
		  int row = myInternshipsFlexTable.getRowCount();
		  myInternshipsFlexTable.setText(row, 0, theMentorship[0]);
		  myInternshipsFlexTable.setText(row, 1, theMentorship[1]);
		  myInternshipsFlexTable.setText(row, 2, theMentorship[2]);
		  myInternshipsFlexTable.setText(row, 3, theMentorship[3]);
		  myInternshipsFlexTable.setText(row, 4, theMentorship[4]);
		  myInternshipsFlexTable.setText(row, 5, theMentorship[5]);
		  myInternshipsFlexTable.setText(row, 6, theMentorship[6]);
		  myInternshipsFlexTable.setText(row, 7, theMentorship[7]);
		  Button removeStockButton = new Button("x");
	      Button editStockButton = new Button("Edit");
	      myInternshipsFlexTable.setWidget(row, 8, editStockButton);
	      myInternshipsFlexTable.setWidget(row, 9, removeStockButton);
	  }
	  private void displayMyMentorships(final String[] theMentorship) {
		  int row = myMentorshipsFlexTable.getRowCount();
		  myMentorshipsFlexTable.setText(row, 0, theMentorship[0]);
		  myMentorshipsFlexTable.setText(row, 1, theMentorship[1]);
		  myMentorshipsFlexTable.setText(row, 2, theMentorship[2]);
		  myMentorshipsFlexTable.setText(row, 3, theMentorship[3]);
		  myMentorshipsFlexTable.setText(row, 4, theMentorship[4]);
		  myMentorshipsFlexTable.setText(row, 5, theMentorship[5]);
		  myMentorshipsFlexTable.setText(row, 6, theMentorship[6]);
		  myMentorshipsFlexTable.setText(row, 7, theMentorship[7]);
		  Button removeStockButton = new Button("x");
	      Button editStockButton = new Button("Edit");
	      myMentorshipsFlexTable.setWidget(row, 8, editStockButton);
	      myMentorshipsFlexTable.setWidget(row, 9, removeStockButton);
	  }
		  private void displayStocks(String[][] goals) {
		    for (String[] theGoal : goals) {
		      displayStock(theGoal);
		    }
		  }

		  public void addStock(Stack dataObject, String objectName) {
			  //Window.alert("inside addstock in itk");
			  final Stack dataObjectLocal = dataObject;
			  final String finalobjectName = objectName;
			        if(loginInfo.isLoggedIn()) {
			        	signOutLink.setHref(loginInfo.getLogoutUrl());
			        	// Window.alert("size of stack " +Integer.toString(dataObject.capacity()));
			        	 
			        	stockService.addStock(dataObjectLocal, objectName, new AsyncCallback<Void>() {
			        		String[] justAddedGoal = new String[10];
			   			  public void onFailure(Throwable error) {
			   			//	Window.alert("inside add stock  onfailure 2");
			   		      }
			   		      public void onSuccess(Void ignore) {
			   		    //	Window.alert("inside add stock onsuccess 2");
			   		    	  if(finalobjectName.equals("goal")){
			   		    	justAddedGoal[0] = goalTypeDropBox.getValue(goalTypeDropBox.getSelectedIndex());
					    	  justAddedGoal[1] = listBox1.getValue(listBox1.getSelectedIndex());
					    	  justAddedGoal[2] = budgetDropBox.getValue(budgetDropBox.getSelectedIndex());
					    	  justAddedGoal[3] = FieldVerifier.getMonth(sdateBox.getValue().getMonth())+" "+Integer.toString(sdateBox.getValue().getDate())+" " +Integer.toString((sdateBox.getValue().getYear()+1900));
					    	  justAddedGoal[4] = FieldVerifier.getMonth(edateBox.getValue().getMonth())+" "+Integer.toString(edateBox.getValue().getDate())+" " +Integer.toString((edateBox.getValue().getYear()+1900));
					       displayStock(justAddedGoal);
					       
			   		    	  }
			   		    	if(finalobjectName.equals("product")){
			   		    		Window.alert("the business name is" +topBar.getVendorFormView().getBusinessName());
				   		    	justAddedGoal[0] = topBar.getVendorFormView().getBusinessName();
						    	  justAddedGoal[1] = topBar.getVendorFormView().getProduct();
						    	  justAddedGoal[2] = topBar.getVendorFormView().getPrice();
						       displayProduct(justAddedGoal);
				   		    	  }
			   		    	if(finalobjectName.equals("datingproblem")){
			   		    		//Window.alert("the dating problem is" +topBar.getVendorFormView().getBusinessName());
				   		    	/*justAddedGoal[0] = topBar.getDatingView().getDatingProblem();
						    	  justAddedGoal[1] = topBar.getDatingView().getTimeWasted();
						    	  justAddedGoal[2] = topBar.getDatingView().getMoneyWasted();
						    	  justAddedGoal[3] = topBar.getDatingView().getEmotion();
						    	  justAddedGoal[4] = topBar.getDatingView().getCareerImpact();
						    	  displayDatingProblem(justAddedGoal);*/
				   		    	  }
			   		      }
			   		    });
			        } else {
			          loadLogin();
			        }
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
         //logoLink.put ( "src", new JSONString ( "http://www.irelaxa.com/images/leaf1.jpg" ) ); 
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
  
         /* 
          * Execute facebook method 
          */ 
       /*  RootPanel.get("fblogout").clear();
         RootPanel.get("fblogout").add(fbLogOut);*/
         fbCore.ui(data.getJavaScriptObject(), new Callback () ); 
        //fbCore.ui(data.getJavaScriptObject(), loginStatusCallback ); 
       // fbCore.ui(data.getJavaScriptObject(), logoutCallback ); 
         
        // fbCore.getLoginStatus(loginStatusCallback);
 
	}
	  private void displayStock(final String[] theGoal) {
	  int row = stocksFlexTable.getRowCount();
    //  stocks.add(symbol);
      stocksFlexTable.setText(row, 1, theGoal[1]);
      stocksFlexTable.setText(row, 2, theGoal[0]);
      stocksFlexTable.setText(row, 3, theGoal[2]);
      stocksFlexTable.setText(row, 4, theGoal[3]);
      stocksFlexTable.setText(row, 5, theGoal[4]);
    /*  afterGoalSave();*/
   // Add a button to remove this stock from the table.-not
      Button removeStockButton = new Button("x");
      Button editStockButton = new Button("Edit");
      Button interestedStockButton = new Button("Public");
      //Label spending = new Label("");
      stocksFlexTable.setWidget(row, 6, interestedStockButton);
      stocksFlexTable.setWidget(row, 7, editStockButton);
      stocksFlexTable.setText(row, 9, theGoal[5]+ " Days");
      ShareButton fbShare = new ShareButton();
      fbShare.addClickHandler( new ClickHandler () { 
          public void onClick(ClickEvent event) { 
        //	  renderWhenNotLoggedIn();
              fbShare ("GOAL TYPE: "+ theGoal[1]+"\n"+ "GOAL: "+theGoal[0]+"\n"+"BUDGET: "+theGoal[2]+"\n"+"START DATE: "+theGoal[3]+"\n"+"END DATE: "+theGoal[4]);
        //      renderWhenLogged();
          } 
      }); 

    stocksFlexTable.setWidget(row, 0, fbShare);
     // Window.alert("Remaining days "+theGoal[5]);
      removeStockButton.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
        	removeStock(theGoal[1]);
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
      });
    
      if((theGoal[1].length()!= 0)){
      stocksFlexTable.setWidget(row, 7, removeStockButton);
      }
	  }
	  private void loadLogin() {
		    // Assemble login panel.
		  
		    signInLink.setHref(loginInfo.getLoginUrl());
		    
		   // RootPanel.get("login").add(loginLabel);
		    RootPanel.get("logout").clear();
		    RootPanel.get("logout").add(signInLink);
		    
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
		public void onGoalWordsSelect(Widget w){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("goalList").clear();
			RootPanel.get("flexTable").add(w);
		    if(w.getClass().getName().toString().equals(
		    		"com.irelaxa.irtk.client.DatingView")
		    		){
		    	RootPanel.get("platformheader").clear();
				RootPanel.get("platformheader").add(
						new DatingHeader());
		    }
			}
		public void onCareerManagerViewSelect(Widget w){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("goalList").clear();
			RootPanel.get("flexTable").add(w);		
			}
		public void onPersonalGoalsSelect(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(stocksFlexTable);	
		
			}
		public void onAllGoalsSelect(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(goalTable);		
			}
		public void onWorkLifeBalanceSelect(Widget w){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("goalList").clear();
			RootPanel.get("flexTable").add(w);		
			}
		public void onShowAllMyProductsSelect(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(productsFlexTable);	
		}
		public void onShowAllMyDatingProblemsSelect(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(datingProblemFlexTable);	
		}
		
		public void showDatingDiaryForm(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(datingDiaryView);
		 }
		public void onShowAllInternships(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(internshipsFlexTable);	
		}
		public void onShowAllMentorships(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(mentorshipsFlexTable);	
		}
		public void onShowAllInterns(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(internsFlexTable);	
		}
		public void loadInternEvent(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(topBar.getInternGoalMatchingEvent());
				
		}
		public void afterGoalSave(){
			onPersonalGoalsSelect();
		}
		public void onSaveMyCareerManager(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(myCareerManagerFlexTable);	
		}
		public void onSaveMyInternships(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(myInternshipsFlexTable);	
		}
		public void onSaveMyMentorships(){
			
			RootPanel.get("flexTable").clear();
			RootPanel.get("flexTable").add(myMentorshipsFlexTable);	
		}
		
		
		
		
		public void onValueChange(ValueChangeEvent<String> event){
			
		}
		
		
		
		public void fbLogin(){
				RootPanel.get("fblogout").clear();
				RootPanel.get("fblogout").add(new HTML ( "<fb:login-button autologoutlink='true' perms='publish_stream,read_stream' /> " ) );
				RootPanel.get("fbprofpic").add(new HTML ( "<fb:profile-pic uid='650516592' facebook-logo='false' linked='true' width='75' height='100'></fb:profile-pic> " ) );
		            fbXfbml.parse();
		}
		
		
		public String[][] getDatingGoals(){
			return datingGoals1;
		}

		public StockServiceAsync getStockService() {
			return stockService;
		}
		
		public FBXfbml getFbXfbml(){
			return fbXfbml;
		}
		
		public LoginInfo getLoginInfo(){
			
			return loginInfo;
		}
		
		
}
