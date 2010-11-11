package com.irelaxa.irtk.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;

public class DietView extends Composite {
	 private static DietView instance = null;
	 private FlexTable dietViewTable = new FlexTable();
		protected DietView(){
			initWidget(dietViewTable);
			/*MARKET PLACE*/
			/*SELF-AWARENESS*/
			dietViewTable.setWidget(0, 0, new HTML("<h1>Diet Self-Awareness</h1>"));
			dietViewTable.setWidget(1, 0, new HTML("<h2 style='color=green'>Diet Goals Completed : </h2>"));
			dietViewTable.setWidget(1, 1, new HTML("<h2>2 </h2>"));
			
			/*SELF-MANAGEMENT*/
			dietViewTable.setWidget(5, 0, new HTML("<h1>Diet Self-Management</h1>"));
			dietViewTable.setWidget(6, 0, new HTML("<h2>My Diet Diary : </h2>"));
			dietViewTable.setWidget(6, 1, new HTML("<h2>Ate Nuts instead of chips </h2>"));
			
			/*SOCIAL-AWARENESS*/
			dietViewTable.setWidget(0, 3, new HTML("<h1>Diet Social-Awareness</h1>"));
			dietViewTable.setWidget(1, 3, new HTML("<h2>My Friends Diet Goals : </h2>"));
			dietViewTable.setWidget(1, 4, new HTML("<h2>Smit and Richard </h2>"));
			
			/*SOCIAL-MANAGEMENT*/
			dietViewTable.setWidget(5, 3, new HTML("<h1>Diet Social-Management</h1>"));
			dietViewTable.setWidget(6, 3, new HTML("<h2>Diet Deals : </h2>"));
			dietViewTable.setWidget(6, 4, new HTML("<h2>Whole Foods </h2>"));
			
		}
		 public static DietView getInstance() {
		     if(instance == null) {
		        instance = new DietView();
		     }
		     return instance;
		 }
}