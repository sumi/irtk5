package com.irelaxa.irtk.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

public class OrganizeEventView extends Composite{
	FlexTable eventTable = new FlexTable();
	private TextBox nameTB = new TextBox();
	private DateBox when = new DateBox();
	private Button addNewEventButton = new Button("Create Event");
	private Button sendInvitationButton = new Button("Send Invitations");
	protected OrganizeEventView(){
		addNewEventButton.setStyleName("gwt-irelaxa-Button");
		sendInvitationButton.setStyleName("gwt-irelaxa-Button");
		initWidget(eventTable);
		/*What are the open deals and their status.*/
		eventTable.setWidget(0, 0, new HTML("<b><p style='font-family:arial;color:green;font-size:15px;'>Create An Event</p></b>"));
		eventTable.setWidget(1, 0, new HTML("<b><p style='font-family:arial;color:green;font-size:10px;'>Name of the Event</p></b>"));
		eventTable.setWidget(1, 1, nameTB);
		eventTable.setWidget(2, 0, new HTML("<b><p style='font-family:arial;color:green;font-size:10px;'>Date:</p></b>"));
		eventTable.setWidget(2, 1, when);
		eventTable.setWidget(3, 1, sendInvitationButton);
		



		eventTable.setWidget(2, 3, addNewEventButton);	
	}
}