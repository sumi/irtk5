package com.irelaxa.irtk.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.datepicker.client.DateBox;

public class MotivatingHeader extends Composite {
	private FlexTable datingTable = new FlexTable();
	FlexCellFormatter cellFormatter = datingTable.getFlexCellFormatter();
	private Button dateProblemSaveButton = new Button("Save");
    	
    private TextBox dateName = new TextBox();
    private TextBox howWasDate = new TextBox();
    private TextBox whatDidDo = new TextBox();
    private TextBox spending = new TextBox();
    private TextBox nextdate = new TextBox();
    private TextBox emotion = new TextBox();
    private TextBox careerImpact = new TextBox();
    private Anchor myDatingGoalsLink = new Anchor("Dating Goals");
    private Anchor myDatingProblemsLink = new Anchor("Dating Problems");
    private Anchor myDatingDiaryLink = new Anchor("Dating Diary");
    private Anchor myDatingBudgetLink = new Anchor("Dating & Money");
    private Anchor myMakeADateLink = new Anchor("Make a Date");
    private Anchor myGroupDateLink = new Anchor("Group Date");
  
  
	MotivatingHeader(){
		initWidget(datingTable);
		datingTable.setWidth("48em");
		datingTable.setCellSpacing(5);
		datingTable.setCellPadding(3);
		/*cellFormatter.setHorizontalAlignment(0, 1,
		        HasHorizontalAlignment.ALIGN_LEFT);
		datingTable.getFlexCellFormatter().setColSpan(1, 0, 2);*/
		datingTable.setWidget(0, 0, myDatingGoalsLink);
		datingTable.setWidget(0, 2, myDatingProblemsLink);
		datingTable.setWidget(0, 3, myDatingDiaryLink);
		datingTable.setWidget(0, 4, myDatingBudgetLink);
		datingTable.setWidget(0, 5, myMakeADateLink);
		datingTable.setWidget(0, 6, myGroupDateLink);
		
	}
	
}
