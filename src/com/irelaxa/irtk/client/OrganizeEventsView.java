package com.irelaxa.irtk.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class OrganizeEventsView extends Composite {
	private FlexTable organizeEventsFormTable = new FlexTable();
	FlexCellFormatter cellFormatter = organizeEventsFormTable.getFlexCellFormatter();
	OrganizeEventsView(){
		initWidget(organizeEventsFormTable);
		organizeEventsFormTable.setWidth("32em");
		organizeEventsFormTable.setCellSpacing(5);
		organizeEventsFormTable.setCellPadding(3);
		cellFormatter.setHorizontalAlignment(0, 1,
		        HasHorizontalAlignment.ALIGN_LEFT);
		organizeEventsFormTable.getFlexCellFormatter().setColSpan(1, 0, 2);
		organizeEventsFormTable.setWidget(2, 0, new HTML("Oraganize Events"));
	}
}