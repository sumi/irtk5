package com.irelaxa.irtk.client;

import java.util.Stack;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class CategoryView extends Composite {
	private Label categoryLabel = new Label("Category");
	private TextBox categoryTB = new TextBox();
	private FlexTable flexTable = new FlexTable();
	private Button save = new Button("Save");
	private Stack dataObject = new Stack();
	
	public CategoryView(){
		flexTable.setWidget(0, 0, categoryLabel);
		flexTable.setWidget(0, 1, categoryTB);
		flexTable.setWidget(1, 1, save);
		save.addClickHandler(new ClickHandler(){
	          public void onClick(ClickEvent event) {
	        	  dataObject.clear();
	        	  dataObject.push(categoryTB.getValue());
	        	  Window.alert(categoryTB.getValue());
	        	  //make a call to addStock();
	        	  TopBarView.getInstance().getTopBarViewListener().getStockService().addStock(dataObject, "category", 
		    			  new AsyncCallback<Void>() {
				      public void onFailure(Throwable error) {
				    	  Window.alert("Failure");
				      }
				      public void onSuccess(Void ignore) {
				       Window.alert("Status Added");
				       //poupulae the popup with the added status.
				       //step 1: there should be a flex table on the popup
				       //step 2: add the values added to the flex table.
				       
				      }
				    });
	          }
	        });
		initWidget(flexTable);
		}
}