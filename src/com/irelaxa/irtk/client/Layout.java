package com.irelaxa.irtk.client;
import java.util.LinkedHashMap;


import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.OperatorId;
import com.smartgwt.client.widgets.Canvas;
//import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.ItemChangedEvent;
import com.smartgwt.client.widgets.form.events.ItemChangedHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SliderItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.CellStyleHandler;
import com.smartgwt.client.widgets.viewer.DetailFormatter;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public class Layout extends Canvas{
	
	VLayout mainLayout = new VLayout(); 
	HLayout moneyAndTimeLayout = new HLayout(); 
	VStack moneyStack = new VStack(20);
	final TileGrid businessGrid = new TileGrid();
	//final DynamicForm businessFilterForm = new DynamicForm(); 
	// final DynamicForm businessSortForm = new DynamicForm();  
	 final CheckboxItem ascendingItem = new CheckboxItem("chkSortDir");
	public Layout(){
		mainLayout.setWidth100();  
        mainLayout.setHeight100();  
        mainLayout.setMembersMargin(20);
        
        moneyAndTimeLayout.setWidth100();  
        moneyAndTimeLayout.setHeight100();  
        moneyAndTimeLayout.setMembersMargin(20);
        
       
        
        moneyStack.setWidth100();
        
        businessGrid.setTileWidth(150);  
        businessGrid.setTileHeight(205);  
        businessGrid.setHeight(400);  
        businessGrid.setCanDrag(true);  
        businessGrid.setCanAcceptDrop(true);  
        businessGrid.setShowAllRecords(true);  
     //   businessGrid.setDataSource(AnimalXmlDS.getInstance());  
        businessGrid.setAutoFetchData(true);  
        businessGrid.setAnimateTileChange(true); 
        
        DetailViewerField pictureField = new DetailViewerField("picture");  
        DetailViewerField commonNameField = new DetailViewerField("commonName");  
        commonNameField.setCellStyle("commonName");  
  
        DetailViewerField lifeSpanField = new DetailViewerField("lifeSpan");  
        lifeSpanField.setCellStyle("lifeSpan");  
        lifeSpanField.setDetailFormatter(new DetailFormatter() {  
            public String format(Object value, Record record, DetailViewerField field) {  
                return "Lifespan: " + value;  
            }  
        });  
        
        DetailViewerField statusField = new DetailViewerField("status");  
        statusField.setCellStyleHandler(new CellStyleHandler() {  
            public String execute(Object value, DetailViewerField field, Record record) {  
                if("Endangered".equals(value)) {  
                    return "endangered";  
                } else if ("Threatened".equals(value)) {  
                    return "threatened";  
                } else if ("Not Endangered".equals(value)) {  
                    return "notEndangered";  
                } else {  
                    return null;  
                }  
            }  
        });  
        businessGrid.setFields(pictureField, commonNameField, lifeSpanField, statusField); 
        
        moneyAndTimeLayout.addMember(businessGrid); 
        
      /*  businessFilterForm.setIsGroup(true);  
        businessFilterForm.setGroupTitle("Search");  
        businessFilterForm.setNumCols(6);  
        businessFilterForm.setDataSource(AnimalXmlDS.getInstance());  
        businessFilterForm.setAutoFocus(false);  */
        
        TextItem commonNameItem = new TextItem("commonName");  
        SliderItem lifeSpanItem = new SliderItem("lifeSpan");  
        lifeSpanItem.setTitle("Max Life Span");  
        lifeSpanItem.setMinValue(1);  
        lifeSpanItem.setMaxValue(60);  
        lifeSpanItem.setDefaultValue(60);  
        lifeSpanItem.setHeight(50);  
        lifeSpanItem.setOperator(OperatorId.LESS_THAN);  

        SelectItem statusItem = new SelectItem("status");  
        statusItem.setOperator(OperatorId.EQUALS);  
        statusItem.setAllowEmptyValue(true);  

       /* businessFilterForm.setFields(commonNameItem, lifeSpanItem, statusItem); 
        
        businessFilterForm.addItemChangedHandler(new ItemChangedHandler() {  
            public void onItemChanged(ItemChangedEvent event) {  
            	businessGrid.fetchData(businessFilterForm.getValuesAsCriteria());  
            }  
        });  */
        moneyStack.addMember(businessGrid); 
       // moneyStack.addMember(businessFilterForm); 
        
       
       /* businessSortForm.setIsGroup(true);  
        businessSortForm.setGroupTitle("Sort");  
        businessSortForm.setAutoFocus(false);  
        businessSortForm.setNumCols(6);  */
        
        SelectItem sortItem = new SelectItem();  
        sortItem.setName("sortBy");  
        sortItem.setTitle("Sort By");  
  
        LinkedHashMap valueMap = new LinkedHashMap();  
        valueMap.put("commonName", "Animal");  
        valueMap.put("lifeSpan", "Life Span");  
        valueMap.put("status", "Endangered Status");
        
        sortItem.setValueMap(valueMap);  
        
        
        ascendingItem.setTitle("Ascending");  
        
      /*  businessSortForm.setFields(sortItem, ascendingItem);  
  
        businessSortForm.addItemChangedHandler(new ItemChangedHandler() {  
            public void onItemChanged(ItemChangedEvent event) {  
                String sortVal = businessSortForm.getValueAsString("sortBy");  
                Boolean sortDir = (Boolean) ascendingItem.getValue();  
                if(sortDir == null) sortDir = false;  
                if(sortVal != null) {  
                	businessGrid.sortByProperty(sortVal, sortDir);  
                }  
            }  
        });  */
       // moneyStack.addMember(businessSortForm); 
        
        HLayout hLayout = new HLayout(10);  
        hLayout.setHeight(22);  
  
      //  IButton filterButton = new IButton("Filter");  
        /*filterButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                businessGrid.fetchData(businessFilterForm.getValuesAsCriteria());  
            }  
        });  */
      /*  filterButton.setAutoFit(true);  
  
        IButton clearButton = new IButton("Clear");  
        clearButton.setAutoFit(true);  
        clearButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                businessGrid.fetchData();  
                businessFilterForm.clearValues();  
                businessSortForm.clearValues();  
            }  
        });  
        hLayout.addMember(filterButton);  
        hLayout.addMember(clearButton); */
        
        moneyStack.addMember(hLayout); 
        
        moneyAndTimeLayout.addMember(moneyStack);
        mainLayout.addMember(moneyAndTimeLayout);  
        mainLayout.draw();  
	}
	
	VLayout getMainLayout(){
		return mainLayout;
	}
}