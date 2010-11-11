package com.irelaxa.irtk.client;
import java.util.ArrayList;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.search.client.ExpandMode;
import com.google.gwt.search.client.ImageSearch;
import com.google.gwt.search.client.Result;
import com.google.gwt.search.client.ResultClass;
import com.google.gwt.search.client.ResultSetSize;
import com.google.gwt.search.client.SearchControl;
import com.google.gwt.search.client.SearchControlOptions;
import com.google.gwt.search.client.SearchResultsHandler;
import com.google.gwt.search.client.WebResult;
import com.google.gwt.search.client.WebSearch;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
/*
 * send invitation to ..let me do with facebook.
 * google contacts api
 * google address book api
 */
public class DateMakeView extends Composite {
	private static DateMakeView instance = null;
	private FlexTable datingTable = new FlexTable();
	 VerticalPanel vp = new VerticalPanel();
	
	private Button save = new Button("Make a Date");
	int currentRow;
	  FlexTable resultsTable;
	//displaying would be the datingGoalsView code.
	protected DateMakeView(){
		initWidget(vp);
		//datingTable.setSize("196px", "122px");
		vp.add(datingTable);
		

		save.setStyleName("gwt-date-Button");
		save.setText("Make a Date");
		datingTable.setWidget(1, 1, save);
		save.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  
		  
		    //select the date and time
		    //send the invitation- first let me focus on sending email.
		    //i am going to place the send mail method in stock service impl class.-DONE
		    	  
		    	  
		    	  TopBarView.getInstance().getTopBarViewListener().getStockService().sendMail(new ArrayList(), 
		    			  new AsyncCallback<Void>() {
				      public void onFailure(Throwable error) {
				    	  Window.alert("Failure");
				      }
				      public void onSuccess(Void ignore) {
				       Window.alert("Email Sent");
				       //setp 1: send the user email id in the from
				       //setp 2: integrate with google address book api to send date invitaion
				       //setp 3: select the business-what api do i use - google ajax search api
				       //step 4: save the date invitation in database.
				       //step 5: there should be a accept invitation button
				       //step 6: suggest a different place and different time button
				       //step 7: decline the date button
				       
				       
				       
				      }
				    });
		    	  
		      	}
		      });
	}
	 public static DateMakeView getInstance() {
	     if(instance == null) {
	        instance = new DateMakeView();
	     }
	     return instance;
	 }
	 public FlexTable getDatingTable(){
		 return datingTable;
	 }
	
}