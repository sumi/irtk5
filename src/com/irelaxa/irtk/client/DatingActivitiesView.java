package com.irelaxa.irtk.client;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
public class DatingActivitiesView extends Composite {
	private static DatingActivitiesView instance = null;
	private FlexTable datingTable = new FlexTable();
	private Label headingLabel = new Label("Dating Activity Goals");
	private Label activityTypeLabel = new Label("Dating Activity Type");
	private ListBox activityLB = new ListBox(true);
	private Label activityLabel = new Label("Dating Activity");
	private TextArea activityTA = new TextArea();
	private Label activityDateL = new Label("Activity Date");
	private DateBox activityDate = new DateBox();
	private Label activityBudgetL = new Label("Dating Activity");
	private TextBox activityBudget = new TextBox();
	private Label dateL = new Label("Who is Your Date?");
	private TextBox date = new TextBox();
	private Button save = new Button("Save Goal");
	private DatingActivityObject object = new DatingActivityObject();
	private FlexTable datingActivityTable = new FlexTable();
	//displaying would be the datingGoalsView code.
	protected DatingActivitiesView(){
		initWidget(datingTable);
		datingTable.setSize("196px", "122px");
		headingLabel.setStyleName("gwt-Label-Heading");
		datingTable.setWidget(0, 0, headingLabel);
		activityTypeLabel.setStyleName("gwt-Label-Table");
		datingTable.setWidget(1, 0, activityTypeLabel);
		activityLB.addItem("Spiritual Activity");
		activityLB.addItem("Fun Activity");
		activityLB.addItem("Healthy Activity");
		activityLB.addItem("Community Activity");
		activityLB.addItem("Social Activity");
		activityLB.addItem("Intellectual Activity");
		activityLB.setStyleName("gwt-ListBox-Goal");
		activityLB.setVisibleItemCount(3);
		datingTable.setWidget(1, 1, activityLB);
		activityLabel.setStyleName("gwt-Label-Table");
		datingTable.setWidget(2, 0, activityLabel);
		datingTable.setWidget(2, 1, activityTA);
		datingTable.setWidget(3, 0, activityDateL);
		datingTable.setWidget(3, 1, activityDate);
		datingTable.setWidget(4, 0, activityBudgetL);
		datingTable.setWidget(4, 1, activityBudget);
        datingTable.setWidget(5, 0, dateL);
		datingTable.setWidget(5, 1, date);
		save.setText("Save Goal");
		datingTable.setWidget(6, 1, save);
		
		save.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    //1.display after saving
		    //2.send invitation to the date.-this will be sending an email. reading email from gmail.
		    	  object.setActivity(activityTA.getValue());
		    	  object.setActivityDate(activityDate.getValue());
		    	  object.setActivityType(activityLB.getValue(activityLB.getSelectedIndex()));
		    	  object.setActivityWith(date.getValue());
		    	  object.setBudget(new Double(activityBudget.getValue()));
		    	  TopBarView.getInstance().getTopBarViewListener().getStockService().addDatingActivity(object, 
		    			  new AsyncCallback<Void>() {
				      public void onFailure(Throwable error) {
				    	  Window.alert("Failure");
				      }
				      public void onSuccess(Void ignore) {
				       Window.alert("DATING ACTIVITY Added");
				       //1.have the activityTable
				       //2.attach the goal to it.
				       //3.add the button, add dating activity goal 
				       //4.change the dating activity view to read the dating activities from the database and display them in table.
				      }
				    });
		    	  
			}
		});
	}
	 public static DatingActivitiesView getInstance() {
	     if(instance == null) {
	        instance = new DatingActivitiesView();
	     }
	     return instance;
	 }
	
	
}