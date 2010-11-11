package com.irelaxa.irtk.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.widgetideas.client.SliderBar;

public class DatingGoalsView extends Composite {
	private static DatingGoalsView instance = null;
	private final String[][] datingGoals;
	private FlexTable datingGoalsTable = new FlexTable();
	private Button setDateGoal = new Button("Set New Dating Goal");
	PopupPanel goalTrack1 = new PopupPanel();
	VerticalPanel vp = new VerticalPanel();
	Button closePopupButton = new Button("X");
	HorizontalPanel hpStatus = new HorizontalPanel();
	HTML spendingLabel = new HTML("Spending$ :");
	TextBox spending = new TextBox();
	HTML statusDateLabel = new HTML("Spending Date: ");
	DateBox statusDate = new DateBox();
	HTML milestoneLabel = new HTML("MileStone: ");
	ListBox milestone = new ListBox(true);
	HTML perCompletedLabel = new HTML("% Completed: ");
	SliderBar slider = new SliderBar(0.0, 100.0);
	HTML commentLabel = new HTML("Comment :");
	TextBox comment = new TextBox();
	Button formSubmit = new Button("Save");
	    HorizontalPanel hp2 = new HorizontalPanel();
	int row = 0;
	protected DatingGoalsView(){
		slider.setWidth("250px");
		 slider.setStepSize(50.0);
		  slider.setCurrentValue(50.0);
		  slider.setNumTicks(10);
		  slider.setNumLabels(5);	
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
		//create the Submit button 
		hpStatus.add(formSubmit);
		closePopupButton.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  goalTrack1.setVisible(false);
		    	  RootPanel.get("goaltrack").clear();
		      }
		    });
		formSubmit.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    //	  question is calling server side method from here
		    	//  TopBarView.getInstance().getTopBarViewListener().removeStock(new Long(theDatingGoal[5])); 
		   // 	  saveGoalStatus(new GoalStatus(spending.getValue(),
		   // 			  statusDate, slider.getCurrentValue(), milestone.getValue(milestone.getSelectedIndex()), comment.getValue()))
		      
		    	  TopBarView.getInstance().getTopBarViewListener().getStockService().getDatingGoals(new AsyncCallback<String[][]>() {
				      public void onFailure(Throwable error) {
				      }
				      public void onSuccess(String[][] datingGoals) {
				       String[][] datingGoals1 = datingGoals;
				       Window.alert("Dating Goal 1="+ datingGoals1[0][0]);
				      }
				    });
		    	 /*TopBarView.getInstance().getTopBarViewListener().getStockService().addGoalStatus(new GoalStatus(new Long(spending.getValue()),
		    		    			  statusDate.getValue(),  milestone.getValue(milestone.getSelectedIndex()), slider.getCurrentValue(), comment.getValue()), 
		    			  new AsyncCallback<Void>() {
				      public void onFailure(Throwable error) {
				      }
				      public void onSuccess(Void ignore) {
				       
				      }
				    });*/
		      }
		    });
		vp.add(closePopupButton);
		datingGoals = TopBarView.getInstance().getTopBarViewListener().getDatingGoals();
		for (final String[] theDatingGoal : datingGoals) {
			  row = datingGoalsTable.getRowCount();
		      ShareButton fbShare = new ShareButton();
		      Button removeStockButton = new Button("x");
		      Button editStockButton = new Button("Edit");
		      Button interestedStockButton = new Button("Public");
		      Button newTrackGoalButton = new Button("Goal Status");
		      newTrackGoalButton.addClickHandler(new ClickHandler(){
		          public void onClick(ClickEvent event) {
		        	  addGoalToPopup(addGoalToHP(theDatingGoal));
		          }
		        });
		          datingGoalsTable.setWidget(row, 0, fbShare);
				  datingGoalsTable.setText(row, 1, theDatingGoal[0]);
				  datingGoalsTable.setText(row, 2, theDatingGoal[1]);
				  datingGoalsTable.setText(row, 3, theDatingGoal[2]);
				  datingGoalsTable.setText(row, 4, theDatingGoal[3]);
				  datingGoalsTable.setText(row, 5, theDatingGoal[4]);
			      datingGoalsTable.setWidget(row, 6, interestedStockButton);
			      datingGoalsTable.setWidget(row, 7, editStockButton);
			      datingGoalsTable.setWidget(row, 8, newTrackGoalButton);
			      datingGoalsTable.setWidget(row, 9, removeStockButton);
		    }
		datingGoalsTable.setWidget((row+1), 9, setDateGoal);
	}
	 public static DatingGoalsView getInstance() {
	     if(instance == null) {
	        instance = new DatingGoalsView();
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
   public HorizontalPanel addGoalToHP(String[] datingGoal){ 
	   hp2.clear();
		hp2.add(new HTML(datingGoal[0]));
		hp2.add(new HTML(datingGoal[1]));
		hp2.add(new HTML(datingGoal[2]));
		hp2.add(new HTML(datingGoal[3]));
		hp2.add(new HTML(datingGoal[4]));
	   	return hp2;
   }
}