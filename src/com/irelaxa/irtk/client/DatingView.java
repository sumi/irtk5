package com.irelaxa.irtk.client;

//import com.google.gwt.event.dom.client.ClickEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.smartgwt.client.widgets.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class DatingView extends Composite {
	 private static DatingView instance = null;
	 private FlexTable datingAlertTable = new FlexTable();
	 private Button datingGoals = new Button("Dating Goals");
	 private Button makeADate = new Button("Make A Date");
	 HLayout layout = new HLayout(); 
	 VLayout vLeftLayout = new VLayout();
	 VLayout vRightLayout = new VLayout();
	 HLayout hStatus = new HLayout();
	 TextArea staus = new TextArea();
	 
	 HLayout topHLayout = new HLayout();
	 VLayout topVLeftLayout = new VLayout();
	 VLayout topVRightLayout = new VLayout();
	 VLayout topVMiddleLayout = new VLayout();
	 Widget datingGoalsFlexTable;
		protected DatingView(){
		//	DOM.removeChild(RootPanel.getBodyElement(), DOM.getElementById("test"));
			vLeftLayout.setShowEdges(true);  
			vLeftLayout.setWidth(150);  
			//vLeftLayout.setMembersMargin(1);  
			//vLeftLayout.setLayoutMargin(10); 
			vLeftLayout.addMember(datingGoals);
			vLeftLayout.addMember(makeADate);
			vLeftLayout.addMember(new Button("Dating Problems"));
			vLeftLayout.addMember(new Button("Dating Diary"));
			vLeftLayout.addMember(new Button("Dating & Spending"));
			
			vLeftLayout.addMember(new Button("Group Date"));
			vLeftLayout.addMember(new Button("Dating Needs"));
			vLeftLayout.addMember(new Button("Dating Boundaries"));
			vLeftLayout.addMember(new Button("Ideal Partner"));
			vLeftLayout.addMember(new Button("Romantic Music"));
			vLeftLayout.addMember(new Button("Romantic Movies"));
			vLeftLayout.addMember(new Button("Romantic Moments"));
			vLeftLayout.addMember(new Button("Romance EI"));
			vLeftLayout.addMember(new Button("Romance Wellbeing"));
			vLeftLayout.addMember(new Button("Romantic Activities"));
			layout.setWidth100();  
		     layout.setHeight100();  
		     layout.setMembersMargin(20);
		     layout.addMember(vLeftLayout);
		     
		     final VLayout mLayout = new VLayout();  
		     mLayout.setShowEdges(true);  
		    // hLayout.setHeight(150);  
		     mLayout.setMembersMargin(5);  
		     mLayout.setLayoutMargin(10);
		     staus.setSize("600px", "30px");
		    // Window.alert(mLayout.getWidthAsString());
		     mLayout.addMember(staus);
		     mLayout.addMember(datingAlertTable);
		     layout.addMember(mLayout);
		     
		     vRightLayout.setShowEdges(true);  
		     vRightLayout.setWidth(150);  
		     vRightLayout.setMembersMargin(1);  
		     vRightLayout.setLayoutMargin(10); 
		     vRightLayout.addMember(new Anchor("Dating Sponsors"));
		     layout.addMember(vRightLayout);
				
		     layout.draw(); 
		     initWidget(layout);
			/*********************************ALERTS*******************************************/
			
			/*MARKET PLACE- DEALS FOR MOVIES, DEALS FOR DINNER, DEALS FOR TRIPS*/
			datingAlertTable.setWidget(0, 0, new HTML("<u><b><p style='font-family:arial;color:green;font-size:15px;'>Deals for your Romance</p></b></u>"));
			datingAlertTable.setWidget(1, 0, new HTML("<p style='font-family:arial;color:green;font-size:10px;'>Dinner for 2 at Tamarine for $6</p>"));
			datingAlertTable.setWidget(1, 1, new Button("Grab It!"));
			datingAlertTable.setWidget(1, 2, new Button("Send to Friend!"));
			datingAlertTable.setWidget(1, 3, new Button("Bargain"));
			/*SELF-AWARENESS*/
		//	datingAlertTable.setWidget(0, 0, new HTML("<u><b><p style='font-family:arial;color:green;font-size:15px;'>Fashion Self-Awareness</p></b></u>"));
			datingAlertTable.setWidget(2, 0, new HTML("<p style='font-family:arial;color:green;font-size:10px;'>Date Invitation from Rick:</p>"));
			datingAlertTable.setWidget(2, 1, new Button("Accept!"));
			datingAlertTable.setWidget(2, 2, new Button("Suggest New Time&Place"));
			datingAlertTable.setWidget(2, 3, new Button("Decline"));
			
			/*SELF-MANAGEMENT*/
		//	datingAlertTable.setWidget(5, 0, new HTML("<u><b><p style='font-family:arial;color:green;font-size:15px;'>Fashion Self-Management</p></b></u>"));
			datingAlertTable.setWidget(3, 0, new HTML("<p style='font-family:arial;color:green;font-size:10px;'>Exceeding the Time spent on Dating by 2hrs:</p>"));
			datingAlertTable.setWidget(3, 1, new Button("Amend"));
			datingAlertTable.setWidget(3, 2, new Button("Update Goal"));
			datingAlertTable.setWidget(3, 3, new Button("Stick"));
			
			/*SOCIAL-AWARENESS*/
		//	datingAlertTable.setWidget(0, 3, new HTML("<u><b><p style='font-family:arial;color:green;font-size:15px;'>Fashion Social-Awareness</p></b></u>"));
			datingAlertTable.setWidget(4, 0, new HTML("<p style='font-family:arial;color:green;font-size:10px;'>Completed The Goal:Spirituality in Dating</p>"));
			datingAlertTable.setWidget(4, 1, new Button("Celebrate"));
			datingAlertTable.setWidget(4, 2, new Button("Share with Friends"));
			datingAlertTable.setWidget(4, 3, new Button("Diary it"));
			
			/*SOCIAL-MANAGEMENT*/
			//datingAlertTable.setWidget(5, 3, new HTML("<u><b><p style='font-family:arial;color:green;font-size:15px;'>Fashion Social-Management</p></b></u>"));
			datingAlertTable.setWidget(5, 0, new HTML("<p style='font-family:arial;color:green;font-size:10px;'>The Dating Problem:Anxiety With Rick is under control</p>"));
			datingAlertTable.setWidget(5, 1, new Button("Celebrate"));
			datingAlertTable.setWidget(5, 2, new Button("Share with Friends"));
			datingAlertTable.setWidget(5, 3, new Button("Diary it"));
			//datingGoalsCanvas.addChild(DatingGoalsView1.getInstance().getDatingGoalsTable());
			
			datingGoalsFlexTable = DatingGoalsView1.getInstance().getDatingGoalsTable();
			//  mLayout.addMember(datingGoalsFlexTable,0);
			//datingGoalsFlexTable.setVisible(false);
			mLayout.setTitle("");
			datingGoals.addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  if(mLayout.getTitle().equalsIgnoreCase("DatingGoalsDiplayed")){
			    		
			    	  }else{
			    		// mLayout.clear();
			    	  mLayout.addMember(datingGoalsFlexTable,0);
			    	  mLayout.setTitle("DatingGoalsDiplayed");
			    	  }
			    	//  datingGoalsFlexTable.setVisible(true);
			    	//  datingGoalsCanvas.show();
			    	//  mLayout.clear();
			    	//  mLayout.removeMember(datingGoalsCanvas);
			    	//  mLayout.addMember(datingGoalsFlexTable,0);
			    	//  datingGoalsCanvas.addChild(DatingGoalsView1.getInstance().getDatingGoalsTable());
			    	//  mLayout.addMember(datingGoalsCanvas);
			    	  /*RootPanel.get("flexTable").clear(); 
			    	 RootPanel.get("flexTable").add(
			    			 DatingGoalsView1.getInstance().getDatingGoalsTable());*/
			      }
			    });
			makeADate.addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  if(mLayout.getTitle().equalsIgnoreCase("Make-A-Date")){
			    		
			    	  }else{
			    		// mLayout.clear();
			    		//  mLayout.hideMember(DateMakeView.getInstance());
			    	  mLayout.addMember(DateMakeView.getInstance(),0);
			    	  mLayout.setTitle("Make-A-Date");
			    	  }
			      }
		    });
			
		}
		 public static DatingView getInstance() {
		     if(instance == null) {
		        instance = new DatingView();
		     }
		     return instance;
		 }
}