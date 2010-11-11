package com.irelaxa.irtk.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtfb.sdk.FBXfbml;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
//import com.smartgwt.client.widgets.layout.HLayout;
/*
 * I want to set the logo in the file and not in the hml file.
 */
public class TopBarView extends Composite {
	private static TopBarView instance = null;
	private static TopBarViewListener topBarViewListener;
	 private final LoginServiceAsync loginService = GWT.create(LoginService.class);
	 public LoginInfo loginInfo = null;
	 private MenuBar menu = new MenuBar();//horizonal menu bar
	private VLayout picPanel = new VLayout();
	private VLayout centerPanel = new VLayout();
	private VLayout signinPanel = new VLayout();
	private HLayout timemoney = new HLayout();
	private HLayout layout2 = new HLayout();
	private TextArea middleTextArea = new TextArea();
	 private Anchor signOutLink = new Anchor("Sign Out");
	
	/*Sub-Menu Personal Goals */
    MenuBar personalGoalsMenu = new MenuBar(true);//vertical menu bar
    MenuBar professionalGoalsMenu = new MenuBar(true);//vertical menu bar
    MenuBar travelGoalsMenu = new MenuBar(true);//vertical menu bar
    MenuBar funGoalsMenu = new MenuBar(true);//vertical menu bar
    MenuBar eventGoalsMenu = new MenuBar(true);//vertical menu bar
    MenuBar studentGoalsMenu = new MenuBar(true);//vertical menu bar
    MenuBar clubGoalsMenu = new MenuBar(true);//vertical menu bar
    MenuBar workPlaceGoalsMenu = new MenuBar(true);//vertical menu bar
    MenuBar businessGoalsMenu = new MenuBar(true);//vertical menu bar
    MenuBar dealsGoalsMenu = new MenuBar(true);//vertical menu bar
    MenuBar accountMenu = new MenuBar(true);//vertical menu bar
    private DatingView1 datingView;
    public Layout layoutTest;
    Command cmdDating = new Command() {
    	
        public void execute() {
        	
        	/*RootPanel.get("logout").clear();
        	datingView = new DatingView();
        	layout2.setWidth100();  
		   //  layout2.setHeight100();  
        	 layout2.setHeight(200);  
		     layout2.setMembersMargin(20);
        	//picPanel.setShowEdges(true);  
        	picPanel.setWidth(150); 
        	picPanel.setHeight(200); 
        	//signinPanel.setShowEdges(true);  
        	signinPanel.setWidth(150);
        	signinPanel.setHeight(200);
        	picPanel.addMember(new Img("logo.png"));
        	picPanel.addMember(new Img("ja.jpg"));
        	//gmailSignOut.addMember(signOutLink);
        	signinPanel.addMember(signOutLink);
        	signinPanel.addMember(fblogin);
        	fbXfbml.parse();
        	
        	//signinPanel.removeMember(gmailSignOut);
        	RootPanel.get("logoid").clear();
        	layout2.addMember(picPanel);
        	centerPanel.setMembersMargin(5);  
        	centerPanel.setLayoutMargin(10);
        	centerPanel.setHeight(200);
        	centerPanel.addMember(middleTextArea);
        	layout2.addMember(centerPanel);
        	layout2.addMember(signinPanel);
        //	RootPanel.get("logoid").add(layout2);
        	
        	HorizontalPanel hp = new HorizontalPanel();
        	VerticalPanel vp1 = new VerticalPanel();
        	vp1.setWidth("300px");
        	VerticalPanel vp2 = new VerticalPanel();
        	vp2.setWidth("300px");
        	VerticalPanel vp3 = new VerticalPanel();
        	vp3.setWidth("300px");
        	Image logo = new Image();
        	logo.setUrl("http://www.irelaxa.com/images/logo.png");
        	vp1.add(logo);
        	 
        	//vp1.add(new Image("ja.jpg"));
        	TextArea ta = new TextArea();
        	ta.setPixelSize(600, 40);
        	vp2.add(ta);
        	vp3.add(signOutLink);
        	hp.add(vp1);
        	hp.add(vp2);
        	hp.add(vp3);*/
        	
       	    RootPanel.get("platformheader").clear();
        	RootPanel.get("flexTable").clear();
 			RootPanel.get("goalList").clear();
 			RootPanel.get("logoid").clear();
        	//RootPanel.get("logoid").add(new DatingNetworkWidget().getWidget());
 			//RootPanel.get("logoid").add(new VisualizationWidget().getInstance());
			RootPanel.get("flexTable").add(datingView.getInstance());//why this didn't show up when i didn't use getInstance()?
			//layoutTest.draw();
        }
      };
      Command cmdDiet = new Command() {
          public void execute() {
          	
  			RootPanel.get("flexTable").clear();
  			RootPanel.get("goalList").clear();
  			RootPanel.get("flexTable").add(dietView.getInstance());
  		    RootPanel.get("platformheader").clear();
  		    RootPanel.get("platformheader").add(
  						new DietHeader());
          }
        };
        Command cmdFitness = new Command() {
            public void execute() {
            	
    			RootPanel.get("flexTable").clear();
    			RootPanel.get("goalList").clear();
    			RootPanel.get("flexTable").add(fitnessView.getInstance());
    		    RootPanel.get("platformheader").clear();
    		    RootPanel.get("platformheader").add(
    						new DietHeader());
            }
          };
        Command cmdFashion = new Command() {
            public void execute() {
            	
    			RootPanel.get("flexTable").clear();
    			RootPanel.get("goalList").clear();
    			RootPanel.get("flexTable").add(fashionView.getInstance());
    		    RootPanel.get("platformheader").clear();
    		    RootPanel.get("platformheader").add(
    						new FashionHeader());
            }
          };
          Command cmdFinances = new Command() {
              public void execute() {
              	
      			RootPanel.get("flexTable").clear();
      			RootPanel.get("goalList").clear();
      			RootPanel.get("flexTable").add(financesView.getInstance());
      		    RootPanel.get("platformheader").clear();
      		  //  RootPanel.get("platformheader").add(
      			//			new FinanceHeader());
              }
            };
            Command cmdFriendships = new Command() {
                public void execute() {
                	
        			RootPanel.get("flexTable").clear();
        			RootPanel.get("goalList").clear();
        			RootPanel.get("flexTable").add(friendshipsView);
        		    RootPanel.get("platformheader").clear();
        		    RootPanel.get("platformheader").add(
        						new FinanceHeader());
                }
              };
            Command cmdCourses = new Command() {
                public void execute() {
                	
        			RootPanel.get("flexTable").clear();
        			RootPanel.get("goalList").clear();
        			RootPanel.get("flexTable").add(new CoursesView());
        		    RootPanel.get("platformheader").clear();
        		    RootPanel.get("platformheader").add(
        						new CoursesHeader());
                }
              };
              Command cmdGiveADeal = new Command() {
                  public void execute() {
                  	
          			RootPanel.get("flexTable").clear();
          			RootPanel.get("goalList").clear();
          			RootPanel.get("platformheader").clear();
          			RootPanel.get("logoid").clear();
          			RootPanel.get("flexTable").add(new GiveADealView());
          		  //  RootPanel.get("platformheader").add(
          		//				new CoursesHeader());
                  }
                };
                Command cmdOrganizeEvent = new Command() {
                    public void execute() {
                    	
            			RootPanel.get("flexTable").clear();
            			RootPanel.get("goalList").clear();
            			RootPanel.get("flexTable").add(new OrganizeEventView());
            		    RootPanel.get("platformheader").clear();
            		    RootPanel.get("platformheader").add(
            						new OrganizeEventHeader());
                    }
                  };
                  Command cmdCategory = new Command() {
                      public void execute() {
                      	
              			RootPanel.get("flexTable").clear();
              			RootPanel.get("goalList").clear();
              			RootPanel.get("flexTable").add(new CategoryView());
              		    RootPanel.get("platformheader").clear();
              		    
                      }
                    };
                    Command cmdMentorships = new Command() {
                        public void execute() {
                        	RootPanel.get("flexTable").clear();
                			RootPanel.get("flexTable").clear();
                			RootPanel.get("goalList").clear();
                			RootPanel.get("flexTable").add(new CategoryView());
                		    RootPanel.get("platformheader").clear();
                		    
                        }
                      };
      Command cmd = new Command() {
          public void execute() {
            Window.alert("Lauching soon!");
        	//  new About();
          }
        };
        Command cmdLogOut = new Command() {
            public void execute() {
              Window.alert("You are Logged Out!");
              
            }
          };
	private DietView1 dietView;// = new DietView1();
	private FitnessView1 fitnessView;// = new FitnessView1();
	private FashionView1 fashionView;// = new FashionView1();
	private FinancesView1 financesView;// = new FinancesView1();
	private FriendshipsView1 friendshipsView;// = new FriendshipsView1();
	private FinancialGoalsView financialGoalsView;// = new FinancialGoalsView();
	private FriendshipGoalsView friendshipGoalsView;// = new FriendshipGoalsView();
	private FamilyGoalsView familyGoalsView;// = new FamilyGoalsView();
	private ProfessionalGoalsView professionalView = new ProfessionalGoalsView();
	private SalaryGoalsView salaryGoalsView = new SalaryGoalsView();
	private CareerManagerView careerManagerView = new CareerManagerView();
	private StrengthsManagerView strengthsManagerView = new StrengthsManagerView();
	private VendorFormView vendorFormView = new VendorFormView(topBarViewListener);
	private WorkLifeBalanceView wlbView = new WorkLifeBalanceView();
	private CreativityToolKitView ctkView = new CreativityToolKitView();
	private MentorView mentorView = new MentorView();
	private InternshipBizView internshipBizView = new InternshipBizView();
	private OrganizeEventsView organizeEventsView = new OrganizeEventsView();
	private FamilyEventsView familyEventsView = new FamilyEventsView();
	private EventsView eventsView = new EventsView();
	private TravelView travelView = new TravelView();
	private InternGoalMatchingEvent internGoalMatchingEvent;
    protected TopBarView(TopBarViewListener topBarViewListener){
    	internGoalMatchingEvent = new InternGoalMatchingEvent();
		this.topBarViewListener = topBarViewListener;
		//topHL.addMember(menu);
		//topHL.addMember(topBarViewListener.getSignIn());
		//topHL.draw();
		initWidget(menu);	
		personalGoalsMenu.addItem("Dating", cmdDating);
		personalGoalsMenu.addItem("Diet", cmdDiet);
		personalGoalsMenu.addItem("Fitness", cmdFitness);
		personalGoalsMenu.addItem("Fashion", cmdFashion);
		personalGoalsMenu.addItem("Finances", cmdFinances);
		personalGoalsMenu.addItem("Friendships", cmdFriendships);
		personalGoalsMenu.addItem("Family", cmd);
		menu.addItem("Personal", personalGoalsMenu);
		
		professionalGoalsMenu.addItem("Salary", cmd);
		professionalGoalsMenu.addItem("Career", cmd);
		professionalGoalsMenu.addItem("Skills", cmd);
		professionalGoalsMenu.addItem("Networking", cmd);
		professionalGoalsMenu.addItem("Strengths", cmd);
		professionalGoalsMenu.addItem("Weaknesses", cmd);
		menu.addItem("Professional", professionalGoalsMenu);
		travelGoalsMenu.addItem("Vacation", cmd);
		travelGoalsMenu.addItem("Flights", cmd);
		travelGoalsMenu.addItem("Hotels", cmd);
		travelGoalsMenu.addItem("Cruises", cmd);
		menu.addItem("Travel", travelGoalsMenu);
		funGoalsMenu.addItem("Sports", cmd);
		funGoalsMenu.addItem("Music", cmd);
		funGoalsMenu.addItem("Books", cmd);
		funGoalsMenu.addItem("Movies", cmd);
		funGoalsMenu.addItem("TV Shows", cmd);
		funGoalsMenu.addItem("Creativity Center", cmd);
		funGoalsMenu.addItem("Zen Center", cmd);
		menu.addItem("Fun", funGoalsMenu);
		eventGoalsMenu.addItem("Organize Events", cmdOrganizeEvent);
		eventGoalsMenu.addItem("Career Events", cmd);
		eventGoalsMenu.addItem("Social Events", cmd);
		eventGoalsMenu.addItem("Family Events", cmd);
		eventGoalsMenu.addItem("Community Events", cmd);
		eventGoalsMenu.addItem("Spiritual Events", cmd);
		menu.addItem("Events", eventGoalsMenu);
		studentGoalsMenu.addItem("Courses", cmdCourses);
		studentGoalsMenu.addItem("Internships", cmd);
		studentGoalsMenu.addItem("Groups", cmd);
		studentGoalsMenu.addItem("Mentorships", cmdMentorships);
		studentGoalsMenu.addItem("Teaching", cmd);
		studentGoalsMenu.addItem("Dream Jobs", cmd);
		studentGoalsMenu.addItem("Stress Management", cmd);
		menu.addItem("Student Solutions", studentGoalsMenu);
		clubGoalsMenu.addItem("Clubs", cmd);
		clubGoalsMenu.addItem("Members", cmd);
		clubGoalsMenu.addItem("Events", cmd);
		clubGoalsMenu.addItem("Sponsorships", cmd);
		menu.addItem("Groups", clubGoalsMenu);
		workPlaceGoalsMenu.addItem("Mentoring", cmd);
		workPlaceGoalsMenu.addItem("Motivation", cmd);
		workPlaceGoalsMenu.addItem("Creativity ToolKit", cmd);
		workPlaceGoalsMenu.addItem("Innovation ToolKit", cmd);
		workPlaceGoalsMenu.addItem("Team Building", cmd);
		workPlaceGoalsMenu.addItem("Work Life Balance", cmd);
		menu.addItem("WorkPlace Solutions", workPlaceGoalsMenu);
		//businesses signup to reward.those
		//rewards incentivise to make a buy.
		//they won't be able to give rewards?reward a user that
		//becomes a fan. reward a person who tweets, reward a person who make a  wall post.a business decides the tweet
		//the business decides the status post.businesses pay for this.this is the rewards part, different from sponsor goals
		dealsGoalsMenu.addItem("Sponsor Goals", cmdGiveADeal);
		dealsGoalsMenu.addItem("Social Rewards", cmdGiveADeal);
		dealsGoalsMenu.addItem("Social CRM", cmd);
		dealsGoalsMenu.addItem("Affiliate Program", cmd);
		//dealsGoalsMenu.addItem("Loyalty Lab", cmd);
		
		//dealsGoalsMenu.addItem("Partnerships", cmd);
		menu.addItem("Give Deals", dealsGoalsMenu);
		loginService();
		menu.addItem("Account", accountMenu);
		//accountMenu.addItem("Add Category", cmdCategory);
	}
	
    public void loginService() {
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
		      public void onFailure(Throwable error) {
		      }
		      public void onSuccess(LoginInfo result) {
		        loginInfo = result;
		        if(loginInfo.isLoggedIn()) {
		        	signOutLink.setHref(loginInfo.getLogoutUrl());
		        	//RootPanel.get("logout").add(signOutLink);
		        //	Window.alert(loginInfo.getLogoutUrl());
		        	accountMenu.addItem("<a href=''>Log Out</a>", true, cmdLogOut);
		        	if(loginInfo.getEmailAddress().equalsIgnoreCase("sumi007@gmail.com")){
		        		accountMenu.addItem("Add Category", cmdCategory);
		        	}
		        	
		        	
		        } else {
		        	signOutLink.setHref(loginInfo.getLoginUrl());
		        	signOutLink.setText("Sign In");
		        	 
		        }
		      }
		    });
	}
    
private void onGoalWordsSelect(Widget w){
	topBarViewListener.onGoalWordsSelect(w);
}

private void onPersonalGoalsSelect(){
	topBarViewListener.onPersonalGoalsSelect();
}

private void onAllGoalsSelect(){
	topBarViewListener.onAllGoalsSelect();
}
private void onShowAllMyProductsSelect(){
	topBarViewListener.onShowAllMyProductsSelect();
}
private void onCareerManagerViewSelect(Widget w){
	topBarViewListener.onCareerManagerViewSelect(w);
}

public void addSearchResultsView(VendorFormView view ){
	//mainView.add( view );
	}

 public MentorView getMentorView(){
	 return mentorView;
 }
 
 public VendorFormView getVendorFormView(){
	 return vendorFormView;
 }
 public DatingView1 getDatingView(){
	 return datingView;
 }
 public CareerManagerView getCareerManagerView(){
	 return careerManagerView;
 }
 public InternshipBizView getInternshipBizView(){
	 return internshipBizView;
 }
 public InternGoalMatchingEvent getInternGoalMatchingEvent(){
	 return internGoalMatchingEvent;
 }
 
 public static TopBarView getInstance() {
     if(instance == null) {
        instance = new TopBarView(topBarViewListener);
     }
     return instance;
 }
 public  TopBarViewListener getTopBarViewListener(){
	 
	 return topBarViewListener;
 
 }
 
  
}