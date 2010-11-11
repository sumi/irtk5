package com.irelaxa.irtk.client;

import java.util.Date;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceSequenceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.calendar.Calendar; 
import com.smartgwt.client.widgets.calendar.CalendarEvent;  
import com.smartgwt.client.widgets.calendar.events.DayBodyClickEvent;
import com.smartgwt.client.widgets.calendar.events.DayBodyClickHandler;


public class CompactCalender extends Canvas {
	Calendar calendar;
	public CompactCalender(){
		 DataSource eventDS = new DataSource();  
	        DataSourceSequenceField eventIdField = new DataSourceSequenceField("eventId");  
	        eventIdField.setPrimaryKey(true);  
	  
	        DataSourceTextField nameField = new DataSourceTextField("name");  
	        DataSourceTextField descField = new DataSourceTextField("description");  
	        DataSourceDateField startDateField = new DataSourceDateField("startDate");  
	        DataSourceDateField endDateField = new DataSourceDateField("endDate");  
	  
	        eventDS.setFields(eventIdField, nameField, descField, startDateField, endDateField);  
	        eventDS.setClientOnly(true);  
	        eventDS.setTestData(CalendarData.getRecords());  
	  
	        calendar = new Calendar() {  
	            @Override   
	            protected String getDayBodyHTML(Date date, CalendarEvent[] events, Calendar calendar, int rowNum, int colNum) {  
	                String returnStr = date.getDate() + "";  
	                if(events != null && events.length > 0) {  
	                    returnStr += imgHTML("icons/16/approved.png", 16, 16, "image", "style='margin-top:6px'", null);  
	                }  
	                return returnStr;  
	            }  
	        };  
	  
	        calendar.setWidth(500);  
	        calendar.setHeight(220);  
	        calendar.setShowDayView(false);  
	        calendar.setShowWeekView(false);  
	        calendar.setShowOtherDays(false);  
	        calendar.setShowDayHeaders(false);  
	        calendar.setShowDatePickerButton(false);  
	        calendar.setShowAddEventButton(false);  
	        calendar.setDisableWeekends(false);          
	        calendar.setShowDateChooser(false);  
	        calendar.setCanCreateEvents(true);  
	  
	        calendar.setDataSource(eventDS);  
	        calendar.setAutoFetchData(true);  
	        calendar.addDayBodyClickHandler(new DayBodyClickHandler() {  
	            public void onDayBodyClick(DayBodyClickEvent event) {  
	                String nameStr = "";  
	                CalendarEvent[] events = event.getEvents();  
	                if(events.length == 0) {  
	                    nameStr = "No events";  
	                } else {  
	                    for (CalendarEvent calEvent : events) {  
	                        nameStr += calEvent.getName() + "<br/>";  
	                    }  
	                }  
	                SC.say(nameStr);  
	            }  
	        });  
	  
	        calendar.draw();  
	}
	
	Calendar getCalendar(){
		return calendar;
	}
}