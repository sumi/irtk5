package com.irelaxa.irtk.client;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;

public class VisualizationWidget extends Composite{
	VerticalPanel panel = new VerticalPanel();
	private static VisualizationWidget instance = null;
	public VisualizationWidget(){
		Runnable onLoadCallback = new Runnable(){
			public void run(){
				
				
				//create a pie chart visualization.
				PieChart pie = new PieChart(createTable(), createOptions());
				pie.addSelectHandler(createSelectHandler(pie));
				panel.add(pie);
				initWidget(panel);
			}
		};
		//Load the visualizatin api. passing the onLoadCallback to be called when loading is done.
		VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
	}
	
	private Options createOptions(){
		Options options = Options.create();
		options.setWidth(400);
		options.setHeight(240);
		options.set3D(true);
		options.setTitle("Dating Activities");
		return options;
	}
	
	private SelectHandler createSelectHandler(final PieChart chart){
		return new SelectHandler(){
			public void onSelect(SelectEvent event){
			String message = "";
			JsArray<Selection> selections = chart.getSelections();
			for(int i = 0; i < selections.length(); i++) {
				//add a new line for each selection
				message += i == 0 ? "" : "\n";
				Selection selection = selections.get(i);
				
				if(selection.isCell()){
					//isCell returns true if a cell has been selected.
					int row = selection.getRow();
					int column = selection.getColumn();
					message += "cell" + row + ":" + column + "selected";
				} else if (selection.isRow()){
					int row = selection.getRow();
					message += "row " + row + " selected";
				} else {
					message += "Make a Selection";
				}
			}
			Window.alert(message);
			}
		};
	}
	
	private AbstractDataTable createTable(){
		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING, "Dating Activity");
		data.addColumn(ColumnType.NUMBER, "Dating Time");
		data.addRows(2);
		data.setValue(0, 0, "Dinner");
		data.setValue(0, 1, 5);
		data.setValue(1, 0, "Movie");
		data.setValue(1, 1, "8");
		return data;
	}
	
	public  VisualizationWidget getInstance() {
		 if(instance == null) {
		        instance = new VisualizationWidget();
		     }
		     return instance;
	 }
}