package com.irelaxa.irtk.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GiveADealView extends Composite{
	private VerticalPanel pWidget;
    private FormPanel form;
    private VerticalPanel formElements;
    private FileUpload picUpload;
    private Button button;
   // Image imagePP = new Image();
    
    //PushButton button = new PushButton(imagePP);
   
    private VerticalPanel pResponse;
    private ListBox subscriptionOptions = new ListBox();
	StockServiceAsync stockService = GWT.create(StockService.class);
	public GiveADealView(){
		
		/**
         * constructor - init widget
         */
		loadCategories();
		//imagePP.setUrl("https://www.paypal.com/en_US/i/btn/x-click-but6.gif");
                initWidget(getTheWidget());
                // get the form url for the blobstore
             //   getFormUrl();
                subscriptionOptions.addItem("Sponsor 10 Goals for $25");
                subscriptionOptions.addItem("Sponsor 25 Goals for $50");
                subscriptionOptions.addItem("Sponsor 50 Goals for $75");
                button.setEnabled(true); 
               
                
        }
	
	 private void getFormUrl() {
         stockService.getBlobStoreUrl(new AsyncCallback<String>() {
                 public void onSuccess(String url) {
                         form.setAction(url);
                         button.setEnabled(true); 
                      //   System.out.println("retrieved url for blob store: " + url);
                 }
                 public void onFailure(Throwable caught) {
                       //  Window.alert(caught.toString());
                 }
         });
	 }
	 
	 private VerticalPanel getTheWidget() {
         if (pWidget == null) {
                 pWidget = new VerticalPanel();
                 pWidget.add(getFormPanel());
                 pWidget.add(getPResponse());
         }
         return pWidget;
 }
	 private FormPanel getFormPanel() {
         if (form == null) {
                 form = new FormPanel();
                 //form.setAction("/upload");
                 form.setAction(GWT.getModuleBaseURL() + "stock");
                // form.setAction("stock");
                // form.setEncoding(FormPanel.ENCODING_MULTIPART);
                 form.setMethod(FormPanel.METHOD_GET);
                 form.setWidget(getFormElements());
                 //form.add(getHidden());
                 
                 // add submit handler
     form.addSubmitHandler(new SubmitHandler() {
                         public void onSubmit(SubmitEvent event) {
                                /* if (fileUpload.getFilename().length() == 0) {
          // Window.alert("Did you select a file?");
           event.cancel();
         }*/
                         }
                 });
     
     // add submit complete handler
     form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
                         public void onSubmitComplete(SubmitCompleteEvent event) {
                                 button.setEnabled(false);
                                 String results = event.getResults();
                                 pResponse.add(new HTML(results));
                                 getFormUrl();
                                 Window.alert(results);
                         }
                 });
     
         }
         return form;
 }
	 private VerticalPanel getPResponse() {
         if (pResponse == null) {
                 pResponse = new VerticalPanel();
         }
         return pResponse;
 }
	 private VerticalPanel getFormElements() {
         if (formElements == null) {
                 formElements = new VerticalPanel();
                 formElements.setSize("100%", "100%");
                 FlexTable formTable = new FlexTable();
                 
                
                 formTable.setWidget(0, 0, new Label("Business Name: "));
                 TextBox idTB = new TextBox();
                 //idTB.setName("id"); - this sets the servlet parameter
                 idTB.setName("businessName");
                 formTable.setWidget(0, 1, idTB);
                 
                 formTable.setWidget(1, 0, new Label("Business Address: "));
                 TextBox addressTA = new TextBox();
                 addressTA.setName("businessAddress");
                 formTable.setWidget(1, 1, addressTA);
                 formTable.setWidget(2, 0, new Label("City: "));
                 TextBox cityTA = new TextBox();
                 cityTA.setName("businessCity");
                 formTable.setWidget(2, 1, cityTA);
                 formTable.setWidget(3, 0, new Label("State: "));
                 TextBox stateTA = new TextBox();
                 stateTA.setName("businessState");
                 formTable.setWidget(3, 1, stateTA);
                 formTable.setWidget(4, 0, new Label("Zip: "));
                 TextBox zipTA = new TextBox();
                 stateTA.setName("businessZip");
                 formTable.setWidget(4, 1, stateTA);
                 formTable.setWidget(5, 0, new Label("Upload Logo: "));
                 formTable.setWidget(5, 1, picUpload);
                 formTable.setWidget(6, 0, new Label("Business Description: "));
                 TextArea descTA = new TextArea();
                 descTA.setName("businessDes");
                 formTable.setWidget(6, 1, descTA);
                 formTable.setWidget(7, 0, new Label("Type of Goals You want to Sponsor: "));
                 CheckBox checkBoxDiet = new CheckBox("Diet");
                 checkBoxDiet.setName("dietCB");
                 CheckBox checkBoxDating = new CheckBox("Romance");
                 checkBoxDating.setName("romanceCB");
                 CheckBox checkBoxFitness = new CheckBox("Fitness");
                 checkBoxFitness.setName("fitnessCB");
                 HorizontalPanel hp1 = new HorizontalPanel();
                 hp1.add(checkBoxDiet); 
                 hp1.add(checkBoxDating);
                 hp1.add(checkBoxFitness); 
                 CheckBox checkBoxFashion = new CheckBox("Fashion");
                 checkBoxFashion.setName("fashionCB");
                 CheckBox checkBoxFinances = new CheckBox("Finances");
                 checkBoxFinances.setName("financesCB");
                 CheckBox checkBoxFriendships = new CheckBox("Friendships");
                 checkBoxFriendships.setName("friendshipsCB");
                 HorizontalPanel hp2 = new HorizontalPanel();
                 hp2.add(checkBoxFashion);
                 hp2.add(checkBoxFinances);
                 hp2.add(checkBoxFriendships);
                 CheckBox checkBoxFamily = new CheckBox("Family");
                 checkBoxFamily.setName("familyCB");
                 CheckBox checkBoxSalary = new CheckBox("Salary");
                 checkBoxSalary.setName("salaryCB");
                 CheckBox checkBoxCareer = new CheckBox("Career");
                 checkBoxCareer.setName("careerCB");
                 HorizontalPanel hp3 = new HorizontalPanel();
                 hp3.add(checkBoxFamily);
                 hp3.add(checkBoxSalary);
                 hp3.add(checkBoxCareer);
                 CheckBox checkBoxSkills = new CheckBox("Skills");
                 checkBoxSkills.setName("skillsCB");
                 CheckBox checkBoxNetworking = new CheckBox("Networking");
                 checkBoxNetworking.setName("networkingCB");
                 CheckBox checkBoxStrengths = new CheckBox("Strengths");
                 checkBoxStrengths.setName("strengthsCB");
                 HorizontalPanel hp4 = new HorizontalPanel();
                 hp4.add(checkBoxSkills);
                 hp4.add(checkBoxNetworking);
                 hp4.add(checkBoxStrengths);
                 CheckBox checkBoxWeaknesses = new CheckBox("Weaknesses");
                 checkBoxWeaknesses.setName("weaknessCB");
                 CheckBox checkBoxVacation = new CheckBox("Vacation");
                 checkBoxVacation.setName("vacationCB");
                 CheckBox checkBoxFlights = new CheckBox("Flights");
                 checkBoxFlights.setName("fightsCB");
                 HorizontalPanel hp5 = new HorizontalPanel();
                 hp5.add(checkBoxWeaknesses);
                 hp5.add(checkBoxVacation);
                 CheckBox checkBoxHotels = new CheckBox("Hotels");
                 checkBoxHotels.setName("hotelsCB");
                 CheckBox checkBoxCruise = new CheckBox("Cruise");
                 checkBoxCruise.setName("cruiseCB");
                 CheckBox checkBoxSports = new CheckBox("Sports");
                 checkBoxSports.setName("sportsCB");
                 HorizontalPanel hp6 = new HorizontalPanel();
                 hp6.add(checkBoxFlights);
                 hp6.add(checkBoxHotels);
                 hp6.add(checkBoxCruise);
                 hp6.add(checkBoxSports);
                 CheckBox checkBoxMusic = new CheckBox("Music");
                 checkBoxMusic.setName("musicCB");
                 CheckBox checkBoxBooks = new CheckBox("Books");
                 checkBoxBooks.setName("booksCB");
                 CheckBox checkBoxMovies = new CheckBox("Movies");
                 checkBoxMovies.setName("moviesCB");
                 
                 HorizontalPanel hp7 = new HorizontalPanel();
                 hp7.add(checkBoxMusic);
                 hp7.add(checkBoxBooks);
                 hp7.add(checkBoxMovies);
                 CheckBox checkBoxTVShows = new CheckBox("TV Shows");
                 checkBoxTVShows.setName("tvshowsCB");
                 CheckBox checkBoxCreativityCenter = new CheckBox("Creativity Center");
                 checkBoxCreativityCenter.setName("creativityCB");
                 HorizontalPanel hp8 = new HorizontalPanel();
                 hp8.add(checkBoxTVShows);
                 hp8.add(checkBoxCreativityCenter);
                 
                 
                 CheckBox checkBoxZenCenter = new CheckBox("Zen Center");
                 checkBoxZenCenter.setName("zenCB");
                 CheckBox careerEvents = new CheckBox("Career Events");
                 careerEvents.setName("careerEventsCB");
                 HorizontalPanel hp9 = new HorizontalPanel();
                 hp9.add(checkBoxZenCenter);
                 hp9.add(careerEvents);
                 
                 CheckBox socialEvents = new CheckBox("Social Events");
                 socialEvents.setName("socialEventsCB");
                 CheckBox familyEvents = new CheckBox("Family Events");
                 familyEvents.setName("familyEventsCB");
                 HorizontalPanel hp10 = new HorizontalPanel();
                 hp10.add(socialEvents);
                 hp10.add(familyEvents);
                 
                 CheckBox communityEvents = new CheckBox("Community Events");
                 communityEvents.setName("communityEventsCB");
                 CheckBox spiritualEvents = new CheckBox("Spiritual Events");
                 spiritualEvents.setName("spiritualEventsCB");
                 HorizontalPanel hp11 = new HorizontalPanel();
                 hp11.add(communityEvents);
                 hp11.add(spiritualEvents);
                 
                 CheckBox studentCourses = new CheckBox("Student Courses");
                 studentCourses.setName("studentCoursesCB");
                 CheckBox studentProjects = new CheckBox("Student Projects");
                 studentProjects.setName("studentProjectsCB");
                 HorizontalPanel hp12 = new HorizontalPanel();
                 hp12.add(studentCourses);
                 hp12.add(studentProjects);
                 
                 
                 CheckBox studentGroups = new CheckBox("Student Groups");
                 studentGroups.setName("studentGroupsCB");
                 CheckBox studentMentorships = new CheckBox("Student Mentorships");
                 studentMentorships.setName("studentMentorshipsCB");
                 HorizontalPanel hp13 = new HorizontalPanel();
                 hp13.add(studentGroups);
                 hp13.add(studentMentorships);
                 
                 CheckBox studentDreamJobs = new CheckBox("Student Dream Jobs");
                 studentDreamJobs.setName("studentDreamJobsCB");
                 CheckBox studentStressManagement = new CheckBox("Student Stress Management");
                 studentStressManagement.setName("studentStressManagementCB");
                 HorizontalPanel hp14 = new HorizontalPanel();
                 hp14.add(studentDreamJobs);
                 hp14.add(studentStressManagement);
                 formTable.setWidget(7, 1, hp1);
                 formTable.setWidget(7, 2, hp2);
                 formTable.setWidget(8, 1, hp3);
                 formTable.setWidget(8, 2, hp4);
                 formTable.setWidget(9, 1, hp5);
                 formTable.setWidget(9, 2, hp6);
                 formTable.setWidget(10, 1, hp7);
                 formTable.setWidget(10, 2, hp8);
                 formTable.setWidget(11, 1, hp9);
                 formTable.setWidget(11, 2, hp10);
                 formTable.setWidget(12, 1, hp11);
                 formTable.setWidget(12, 2, hp12);
                 formTable.setWidget(13, 1, hp13);
                 formTable.setWidget(13, 2, hp14);
                // formTable.setWidget(1, 1, titleTA);
                 formTable.setWidget(14, 0, new Label("Sponsorship Packages : "));
                 TextBox dealPriceTB = new TextBox();
                 formTable.setWidget(14, 1, subscriptionOptions);
                 formTable.setWidget(15, 1, getButton());
                /* formTable.setWidget(3, 0, new Label("Deal Start Date: "));
                 DateBox dealSDate = new DateBox();
                 formTable.setWidget(3, 1, dealSDate);
                 formTable.setWidget(4, 0, new Label("Deal End Date: "));
                 DateBox dealEDate = new DateBox();
                 formTable.setWidget(4, 1, dealEDate);*/
                 formElements.add(formTable);
                // formElements.add(getFileUpload());
                 PaypalPanel paypal = new PaypalPanel();
          		formElements.add(paypal);
                 /*HTMLPanel wrap;wrap = new HTMLPanel("<div id='SpinnerDiv'></div>"
         	            + "<div id='ImageMenuDiv'></div><div id='PaypalDiv'></div>");
         		
         		 paypal.update(new Object());//would actually pass your cart in here...
         	        wrap.add(paypal,"PaypalDiv");

         	        RootPanel.get().add(wrap);*/
          	/*	
          		setName("title");
          		setName("order");
          		setName("return");
          		setName("cancel");*/
          		
         }
         return formElements;
 }
	/* private FileUpload getFileUpload() {
         if (fileUpload == null) {
                 fileUpload = new FileUpload();
                 fileUpload.setName("myFile");
         }
         return fileUpload;
 }*/
 
 private Button getButton() {
         if (button == null) {
                // button = new PushButton(imagePP);
                 button = new Button("Pay");
                 button.addClickHandler(new ClickHandler() {
                         public void onClick(ClickEvent event) {
                        	 
                                 form.submit();
                         }
                 });
               //  button.setEnabled(false);
         }
         return button;
 }
	/* private PushButton getButton() {
         if (button == null) {
                 button = new PushButton(imagePP);
                 //button = new Button("Pay");
                 button.addClickListener(new ClickListener() {
                         public void onClick(Widget sender) {
                        	 
                                 form.submit();
                         }
                 });
                 button.setEnabled(false);
         }
         return button;
 }*/
 //ArrayList<CategoryObject> allCategories;
 private void loadCategories() {
	    stockService.getAllCategories(new AsyncCallback<ArrayList<CategoryObject>>() {
	      public void onFailure(Throwable error) {
	    	  Window.alert(error.toString());
	      }
	      String categorykey;
	      public void onSuccess(ArrayList<CategoryObject> allCategories) {
	    	 // allCategories = allCategories;
	    	  for (CategoryObject category : allCategories) {
	    		  categorykey += category.getCategory() + ":"+category.getKey().toString() +";";
		      }
	    	  Window.alert(categorykey);
	      }
	    });
	  }
}