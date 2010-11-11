package com.irelaxa.irtk.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ActionPlanForm extends Composite{
	/*step 1 
	 * add next step
	 * */
	private FlexTable flexTable = new FlexTable();
	private Label step1 = new Label("Step 1");
	private TextArea step1TA = new TextArea();
	private Button addStepButton = new Button("Add Next Step");
	private Button saveButton = new Button("Save");
	private VerticalPanel vp = new VerticalPanel();
	int row;
	Long stockId;
	private ArrayList<ActionPlanObject> actionPlanSteps = new ArrayList<ActionPlanObject>();
	ActionPlanForm(final Long stockId){
		
		initWidget(vp);
		this.stockId = stockId;
		saveButton.setStyleName("gwt-irelaxa-Button");
		addStepButton.setStyleName("gwt-irelaxa-Button");
		flexTable.setText(0, 0, "Goal Achievement Action Plan");
		flexTable.setWidget(1, 0, step1);
		flexTable.setWidget(1, 1, step1TA);
		flexTable.setWidget(2, 1, saveButton);
		flexTable.setWidget(2, 2, addStepButton);
		addStepButton.addClickHandler(new ClickHandler(){
	          public void onClick(ClickEvent event) {
	        	  //add one more row to the flex table
	        	  row = flexTable.getRowCount()-1;
	        	  Label nextStepLabel = new Label("Step "+Integer.toString(row));
	        	  TextArea nextStepTA = new TextArea();
	        	  flexTable.setWidget(row, 0, nextStepLabel);
	      		  flexTable.setWidget(row, 1, nextStepTA);
	      		 flexTable.setWidget(row+1, 1, saveButton);
	      		 flexTable.setWidget(row+1, 2, addStepButton);
	        	  
	      		 ActionPlanObject userValuesObject = new ActionPlanObject();
	        	  userValuesObject.setStepNumber(row-1);
	        	  userValuesObject.setMilestone(nextStepTA.getValue());
	        	  actionPlanSteps.add(userValuesObject);
	      		
	          }
	        });
		saveButton.addClickHandler(new ClickHandler(){
	          public void onClick(ClickEvent event) {
	        	  //STEP 1: Construct actionPlanSteps from input values - I need to figure out to get the stockId from the datinggaolsview
	        	  //STEP 2:populate the action plan table
	        	  //STEP 3: milestones in drop down box
	        	  //STEP 4:tooltip of actual milestone
	        	  ActionPlanObject userValuesObject = new ActionPlanObject();
	        	  userValuesObject.setStepNumber(1);
	        	  userValuesObject.setMilestone(step1TA.getValue());
	        	  actionPlanSteps.add(userValuesObject);
	          	  TopBarView.getInstance().getTopBarViewListener().getStockService().addStockWithActionSteps(stockId, actionPlanSteps,
	          			  new AsyncCallback<Void>() {
	        		      public void onFailure(Throwable error) {
	        		    	  Window.alert("Not Saved");
	        		      }
	        		      public void onSuccess(Void ignore) {
	        		       //after adding the action steps..what needs to happen? Display needs to happen.
	        		    	  Window.alert("Saved");
	        		      }
	        		      });
	        	  
	          }
	        });
		vp.add(flexTable);
		
	}
	
	
}