package com.irelaxa.irtk.client;

import java.util.ArrayList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
//import com.smartgwt.client.widgets.
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.widgetideas.client.SliderBar;

public class DatingGoalsView1 extends Composite {
	private static DatingGoalsView1 instance = null;
	private  ArrayList<StockObject> datingGoals;
	private FlexTable datingGoalsTable = new FlexTable();
	private Button setDateGoal = new Button("Set New Dating Goal");
	PopupPanel goalTrack1 = new PopupPanel();
	PopupPanel sponsoror = new PopupPanel();
	VerticalPanel vp = new VerticalPanel();
	Button closePopupButton = new Button("X");
	HorizontalPanel hpStatus = new HorizontalPanel();
	HTML spendingLabel = new HTML("Spending$ :");
	TextBox spending = new TextBox();
	HTML statusDateLabel = new HTML("Spending Date: ");
	DateBox statusDate = new DateBox();
	HTML milestoneLabel = new HTML("MileStone: ");
	ListBox milestone = new ListBox(true);
	//step 1: when a goal is created a template for action plan should presented.
	//the goal action plan template will be step 1, step2, step 3 and add step button.
	//these steps will become the milestones.
	//setp 1: add the action plan button-action plan button goes before status button
	HTML perCompletedLabel = new HTML("% Completed: ");
	SliderBar slider = new SliderBar(0.0, 100.0);
	HTML commentLabel = new HTML("Comment :");
	TextBox comment = new TextBox();
	Button formSubmit = new Button("Save");
	    HorizontalPanel hp2 = new HorizontalPanel();
	int row = 0;
	GoalStatus goalStatus;
	
	protected DatingGoalsView1(){
		slider.setWidth("100px");
		 slider.setStepSize(1.0);
		  slider.setCurrentValue(50.0);
		  slider.setNumTicks(10);
		  slider.setNumLabels(5);
		  slider.sinkEvents( Event.MOUSEEVENTS);
		  slider.sinkEvents( Event.KEYEVENTS);
		  slider.sinkEvents( Event.ONMOUSEWHEEL);
		  slider.sinkEvents( Event.FOCUSEVENTS);
		  
		  
		  milestone.addItem("MileStone 1");
		  milestone.addItem("MileStone 2");
		  milestone.addItem("MileStone 3");
		  milestone.addItem("MileStone 4");
		hpStatus.add(spendingLabel);
		hpStatus.add(spending);
		hpStatus.add(statusDateLabel);
		hpStatus.add(statusDate);
		hpStatus.add(perCompletedLabel);
		hpStatus.add(slider);
		hpStatus.add(milestoneLabel);
		hpStatus.add(milestone);
		hpStatus.add(commentLabel);
		hpStatus.add(comment);
		hpStatus.add(formSubmit);
		closePopupButton.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  goalTrack1.setVisible(false);
		    	  RootPanel.get("goaltrack").clear();
		      }
		    });
		
		setDateGoal.setStyleName("gwt-irelaxa-Button");
		formSubmit.setStyleName("gwt-irelaxa-Button");
		formSubmit.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	   goalStatus = new GoalStatus();
		      goalStatus.setSpendingAmount(new Long(spending.getValue()));
		      goalStatus.setSpendingDate(statusDate.getValue());
		      goalStatus.setComment(comment.getValue());
		   
		        TopBarView.getInstance().getTopBarViewListener().getStockService().addStockWithGoalStatus(stockId, goalStatus, 
		    			  new AsyncCallback<Void>() {
				      public void onFailure(Throwable error) {
				    	  Window.alert("Failure");
				      }
				      public void onSuccess(Void ignore) {
				       Window.alert("Status Added");
				       //poupulae the popup with the added status.
				       //step 1: there should be a flex table on the popup
				       //step 2: add the values added to the flex table.
				       
				      }
				    });
		      }
		    });
		vp.add(closePopupButton);

  	  TopBarView.getInstance().getTopBarViewListener().getStockService().getDatingGoals1(new AsyncCallback<ArrayList<StockObject>>() {
		      public void onFailure(Throwable error) {
		      }
		      @SuppressWarnings("deprecation")
			public void onSuccess(ArrayList<StockObject> datingGoals1) {
		       datingGoals = datingGoals1;
	   
		for (final StockObject theDatingGoal : datingGoals) {
			  row = datingGoalsTable.getRowCount();
		      ShareButton fbShare = new ShareButton();
		      Button removeStockButton = new Button("x");
		      //i want to work on delete button.
		      //step 1: when delete is clicked that record in the table gets deleted 
		      //step 2: also it gets deleted in the database.
		      
		      removeStockButton.setStyleName("gwt-irelaxa-Button");
		      final Button editStockButton = new Button("Edit");
		      editStockButton.setStyleName("gwt-irelaxa-Button");
		     /* Button interestedStockButton = new Button("Public");
		      interestedStockButton.setStyleName("gwt-irelaxa-Button");*/
		      Button actionPlanButton = new Button("Plan");
		      actionPlanButton.setStyleName("gwt-irelaxa-Button");
		      Button newTrackGoalButton = new Button("Status");
		      newTrackGoalButton.setStyleName("gwt-irelaxa-Button");
		      Button visualizeGoalButton = new Button("Visualize");
		      visualizeGoalButton.setStyleName("gwt-irelaxa-Button");
		      Button godMomDadButton = new Button("Goal GodMom/Dad");
		      godMomDadButton.setStyleName("gwt-irelaxa-Button");
		      Button shareWithButton = new Button("Share With");
		      shareWithButton.setStyleName("gwt-irelaxa-Button");
		      final Button businessButton = new Button("Sponsorships");
		      businessButton.setStyleName("gwt-irelaxa-Button");
		      Button rewardGoalButton = new Button("Celebrate");
		      rewardGoalButton.setStyleName("gwt-irelaxa-Button");
		      final Label startDateString = new Label();
			  final Label endDateString = new Label();
				startDateString.setStyleName("gwt-irelaxa-Button");
				endDateString.setStyleName("gwt-irelaxa-Button");
		      editStockButton.addClickHandler(new ClickHandler(){
		          public void onClick(ClickEvent event) {
		        	/*put this in popup panel*/
		      		editStockButton.setText("Save");
		      		 datingGoalsTable.setWidget(row-1, 1, new TextBox());
					 datingGoalsTable.setWidget(row-1, 2, new TextBox());
					 datingGoalsTable.setWidget(row-1, 3, new TextBox());
					 datingGoalsTable.setWidget(row-1, 4, new DateBox());
					 datingGoalsTable.setWidget(row-1, 5, new DateBox());
		          }
		        });
		      //pubish_stream FB.Connect.showPermissionDialog("publish_stream");
		      /*$result = $facebook->api(
		    		    '/me/feed/',
		    		    'post',
		    		    array('access_token' => $this->access_token, 'message' => 'Playing around with FB Graph..')
		    		);*/
		      //<form promptpermission="publish_stream" method="post" action="(url)"> ... </form>
		      shareWithButton.addClickHandler(new ClickHandler(){
		          public void onClick(ClickEvent event) {
		        	 //facebook selct-friend form
		        		RootPanel.get("flexTable").clear();
						RootPanel.get("flexTable").add(new HTML ( "<fb:serverFbml style='width: 755px;'>"  
    + "<script type='text/fbml'>"
       + "<fb:fbml>"
         +   "<fb:request-form "
            +    "action='http://irelaxa.appspot.com/' "
              +  "method='POST' "
               + "invite='true' "
               + "type='XFBML' "
               + "content='This is a test invitation from XFBML irelaxa app"
            //   + "<fb:req-choice url='see fb:req-choice docs for details.'"
             //  +     "label='Ignore the Facebook test app!' />"
             +"'>"
              +  "<fb:multi-friend-selector "
               +     "showborder='false' "
                +    "actiontext='Invite your friends to use irelaxa.' />"
                +"</fb:request-form>"
        + "</fb:fbml>"
    +"</script>"
+"</fb:serverFbml>" ) );
						TopBarView.getInstance().getTopBarViewListener().getFbXfbml().parse();
		          }
		        });
		      
		      businessButton.addClickHandler(new ClickHandler(){
		          public void onClick(ClickEvent event) {
		        Window.alert("sponsorhips button is clicked");
		         //STEP 1: SHOW LIST OF AVAILABLE SPONSORSHIPS IN THAT CATEGORY
		      		//POPUP
		      		//IN THE GOALS, WE BREAK DOWN THE THINGS NEEDED.
		      		//SO THIS SHOWS THE SPONSORERS FOR THOSE ITEMS, IN THE AREA.
		      		//IT WILL HAVE INVITE SPONSORSHIP-THAT WILL SEND EMAIL TO THE BUSINESS-BUSINESS CAN ACCEPT OR REJECT.
		        	  hp2.clear();
		        	  FlexTable sponsorTable = new FlexTable();
		        	  sponsorTable.setText(0,0,"Business");
		        	  sponsorTable.setText(0,1,"Start Date");
		        	  sponsorTable.setText(0,2,"End Date");
		        	  sponsorTable.setText(0,3,"Items");
		        	  //pouplate the businesses from the database-now the question is how do they 
		        	  //get into the database?it is under sponsor goals
		        	  sponsorTable.setWidget(0,4,new Button("Invite"));
		        	  hp2.add(sponsorTable);
		        	  vp.clear();
		           	  vp.add(closePopupButton);
		           	  vp.add(hp2);
		           	  //vp.add(hpStatus);
		           	  goalTrack1.setWidget(vp);
			           	goalTrack1.setPopupPosition(businessButton.getAbsoluteLeft(), (businessButton.getAbsoluteTop()-20));
			           	vp.setVisible(true);
		           	  goalTrack1.setVisible(true);
		           	 // RootPanel.get("goaltrack").clear();
		           	//  RootPanel.get("goaltrack").add(goalTrack1);

		          }
		        });
		      actionPlanButton.addClickHandler(new ClickHandler(){
		          public void onClick(ClickEvent event) {
		        	  RootPanel.get("flexTable").clear();
		      		RootPanel.get("flexTable").add(new ActionPlanForm(theDatingGoal.getKey()));
		          }
		        });
		      newTrackGoalButton.addClickHandler(new ClickHandler(){
		          public void onClick(ClickEvent event) {
		        	  Window.alert("clicked staus button");
		        	  addGoalToPopup(addGoalToHP(theDatingGoal, startDateString, endDateString));
		          }
		        });
		      visualizeGoalButton.addClickListener(new ClickListener(){
		          public void onClick(Widget sender) {
		        	/*what is visualization?*/
		        	  //this is media file
		        	  //either a picture
		        	  //either a video
		        	  //either a song
		        	  //either a audio file
		        	  HTML contents = new HTML("Click anywhere outside this popup to make it disappear.");
		        	    contents.setWidth("128px");
		        	    
		        	    PopupPanel p = new PopupPanel(true);
		                int left = sender.getAbsoluteLeft() + 10;
		                int top = sender.getAbsoluteTop() + 10;
		                p.setPopupPosition(left, top);
		                p.setWidget(contents);
		                p.show();
		      		
		          }
		        });
		      removeStockButton.addClickHandler(new ClickHandler() {
		          public void onClick(ClickEvent event) {
		            int removedIndex = datingGoals.indexOf(theDatingGoal);
		            datingGoals.remove(removedIndex);
		            datingGoalsTable.removeRow(removedIndex + 1);
		          }
		        });
		          datingGoalsTable.setWidget(row, 0, fbShare);
				  datingGoalsTable.setText(row, 1, theDatingGoal.getGoalType());
				//  datingGoalsTable.setText(row, 1, theDatingGoal.getAllGoalStatus().get(0).getComment());
				  datingGoalsTable.setText(row, 2, theDatingGoal.getSymbol());
				  datingGoalsTable.setText(row, 3, theDatingGoal.getBudget());
				  
				  startDateString.setText(theDatingGoal.getStartDate().toString().substring(0, 10)
						  +theDatingGoal.getStartDate().toString().substring(27, 32));
				  
				  datingGoalsTable.setWidget(row, 4, startDateString);
				 /* datingGoalsTable.setText(row, 4, Integer.toString( theDatingGoal.getStartDate().getMonth())
							+Integer.toString( theDatingGoal.getStartDate().getDay())
					        +Integer.toString( theDatingGoal.getStartDate().getMonth()));*/
				//  datingGoalsTable.setText(row, 4, Integer.toString( theDatingGoal.getStartDate().getMonth()));
				//  datingGoalsTable.setText(row, 5, theDatingGoal.getEndDate().toString());
				  datingGoalsTable.setWidget(row, 5, endDateString);
				  endDateString.setText(theDatingGoal.getEndDate().toString().substring(0, 10)
						  +theDatingGoal.getEndDate().toString().substring(27, 32));
			     // datingGoalsTable.setWidget(row, 6, interestedStockButton);
			      datingGoalsTable.setWidget(row, 6, editStockButton);
			      datingGoalsTable.setWidget(row, 7, godMomDadButton);
			      datingGoalsTable.setWidget(row, 8, shareWithButton);
			      datingGoalsTable.setWidget(row, 9, businessButton);
			      datingGoalsTable.setWidget(row, 10, actionPlanButton);
			      datingGoalsTable.setWidget(row, 11, newTrackGoalButton);
			      datingGoalsTable.setWidget(row, 12, visualizeGoalButton);
			      datingGoalsTable.setWidget(row, 13, rewardGoalButton);
			      datingGoalsTable.setWidget(row, 14, removeStockButton);
			     
			      datingGoalsTable.setText(row, 15, Integer.toString(theDatingGoal.getAllGoalStatus().size()));
		    }
		datingGoalsTable.setWidget((row+1), 16, setDateGoal);
		      }
  	});
	}
	 public static DatingGoalsView1 getInstance() {
	     if(instance == null) {
	        instance = new DatingGoalsView1();
	     }
	     return instance;
	 }
	 public FlexTable getDatingGoalsTable(){
		 return datingGoalsTable;
	 }
	 public void addGoalToPopup(Widget w){
		 vp.clear();
   	  vp.add(closePopupButton);
   	  vp.add(w);
   	  vp.add(hpStatus);
   	  goalTrack1.setWidget(vp);
   	  goalTrack1.setVisible(true);
   	  RootPanel.get("goaltrack").clear();
   	  RootPanel.get("goaltrack").add(goalTrack1);
	 }	 
	 Long stockId;
   public HorizontalPanel addGoalToHP(StockObject datingGoal, Label startDateString, Label endDateString){ 
	   hp2.clear();
	   stockId = datingGoal.getKey();
	   //hp2.add(new HTML(datingGoal.getId().toString()));
		hp2.add(new HTML(datingGoal.getGoalType()));
		hp2.add(new HTML(datingGoal.getSymbol()));
		hp2.add(new HTML(datingGoal.getBudget()));
		//hp2.add(new HTML(Integer.toString(datingGoal.getStartDate().getMonth())
		//		+Integer.toString(datingGoal.getStartDate().getDay())
		//        +Integer.toString(datingGoal.getStartDate().getMonth())));
		hp2.add(startDateString);
		hp2.add(endDateString);
		//hp2.add(new HTML(datingGoal.getEndDate().toString()));
	   	return hp2;
   }
}