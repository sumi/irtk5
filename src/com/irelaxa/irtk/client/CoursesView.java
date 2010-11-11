package com.irelaxa.irtk.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class CoursesView extends Composite{
	private static CoursesView instance = null;
	private FlexTable coursesTable = new FlexTable();
	private TextBox courseTB = new TextBox();
	private ListBox grade = new ListBox();
	private Button save = new Button("Save");
	protected CoursesView(){
		initWidget(coursesTable);
		coursesTable.setWidget(0, 0, new Label("Set Your Course Goal"));
		coursesTable.setWidget(1, 0, new Label("Course Name"));
		coursesTable.setWidget(1, 1, courseTB);
		grade.addItem("Grade A");
		grade.addItem("Grade A -");
		grade.addItem("Grade B");
		grade.addItem("Grade B -");
		grade.addItem("Grade C");
		grade.addItem("Grade C -");
		coursesTable.setWidget(2, 0, new Label("Course Goal"));
		coursesTable.setWidget(2, 1, grade);
		save.setStyleName("gwt-irelaxa-Button");
		coursesTable.setWidget(3, 1, save);
		
	}
	
	public static CoursesView getInstance() {
	     if(instance == null) {
	        instance = new CoursesView();
	     }
	     return instance;
	 }
}