package com.irelaxa.irtk.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;

public class FashionView extends Composite {
	 private static FashionView instance = null;
	 private FlexTable dietViewTable = new FlexTable();
		protected FashionView(){
			initWidget(dietViewTable);
			/*MARKET PLACE*/
			/*SELF-AWARENESS*/
			dietViewTable.setWidget(0, 0, new HTML("<h1>Fashion Self-Awareness</h1>"));
			dietViewTable.setWidget(1, 0, new HTML("<h2 style='color=green'>My Body Type : </h2>"));
			dietViewTable.setWidget(1, 1, new HTML("<h2>Pear Shaped </h2>"));
			
			/*SELF-MANAGEMENT*/
			dietViewTable.setWidget(5, 0, new HTML("<h1>Fashion Self-Management</h1>"));
			dietViewTable.setWidget(6, 0, new HTML("<h2>Fashion Goals Completed : </h2>"));
			dietViewTable.setWidget(6, 1, new HTML("<h2>2 </h2>"));
			
			/*SOCIAL-AWARENESS*/
			dietViewTable.setWidget(0, 3, new HTML("<h1>Fashion Social-Awareness</h1>"));
			dietViewTable.setWidget(1, 3, new HTML("<h2>My Friends Fashion Goals : </h2>"));
			dietViewTable.setWidget(1, 4, new HTML("<h2>Smit and Richard </h2>"));
			
			/*SOCIAL-MANAGEMENT*/
			dietViewTable.setWidget(5, 3, new HTML("<h1>Fashion Social-Management</h1>"));
			dietViewTable.setWidget(6, 3, new HTML("<h2>Fashion Deals : </h2>"));
			dietViewTable.setWidget(6, 4, new HTML("<h2>bebe </h2>"));
			
		}
		 public static FashionView getInstance() {
		     if(instance == null) {
		        instance = new FashionView();
		     }
		     return instance;
		 }
}