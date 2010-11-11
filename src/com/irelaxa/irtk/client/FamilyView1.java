package com.irelaxa.irtk.client;

//import com.google.gwt.event.dom.client.ClickEvent;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.events.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VLayout;

public class FamilyView1 extends Composite {
	 private static FamilyView1 instance = null;
	
		protected FamilyView1(){
			Canvas tabPane1 = new Canvas();  
	        tabPane1.setWidth100();  
	        tabPane1.setHeight100();  
	        tabPane1.addChild(getGwtTab());
			//////////////////////////////////
			 HLayout layout = new HLayout();  
		        layout.setMembersMargin(10);  
		  
		        final HStack starsLayout = new HStack();  
		        starsLayout.setTop(50);  
		        starsLayout.setMembersMargin(10);  
		        starsLayout.setLayoutMargin(10);  
		        starsLayout.setShowEdges(true);  
		        starsLayout.setAnimateMembers(true);  
		  
		        final Img blueImg = createImage("kiss-icon.png");  
		      //  final Img greenImg = createImage("love-icon.png");  
		        final Canvas greenImg = tabPane1; 
		        final ListGrid datingGoals = new DatingGoalsView2().getListGrid(); 
		   //     final VLayout mainLayoutTest = new Layout().getMainLayout();
		        final Canvas calendarCanvas = new Canvas();
		   //     final Calendar datingCalendar = new DatingCalendar().getCalendar();
		        final Calendar compactCalendar = new CompactCalender().getCalendar();
		       // calendarCanvas.addChild(datingCalendar);
		        calendarCanvas.addChild(compactCalendar);
		       // final Calendar datingCalendar = new DatingCalendar().getCalendar();
		        final Img yellowImg = createImage("message-icon.png");
		        final Img moneyPlant1 = createImage("money-plant-drops.png");
		        final Img moneyPlant2 = createImage("money-plant-watering.png");
		  
		        //starsLayout.addMember(blueImg);  
		        starsLayout.addMember(greenImg); 
		        starsLayout.addMember(datingGoals);
		        starsLayout.addMember(calendarCanvas);
		     //   starsLayout.addMember(mainLayoutTest);
		       // starsLayout.addMember(datingCalendar);
		       // starsLayout.addMember(yellowImg); 
		       // starsLayout.addMember(moneyPlant1); 
		      //  starsLayout.addMember(moneyPlant2); 
		  
		        starsLayout.hideMember(datingGoals); 
		        starsLayout.hideMember(greenImg); 
		        IButton showButton = new IButton();  
		        showButton.setTitle("Spouse Goals");  
		        showButton.setIconOrientation("right");  
		        showButton.setIcon("broken-heart-icon.png");  
		       // showButton.setLeft(40);  
		  
		        IButton hideButton = new IButton();  
		        hideButton.setTitle("Kids Goals");  
		        hideButton.setIcon("flying-heart-icon.png");  
		        hideButton.setIconOrientation("right"); 
		        
		        IButton giftButton = new IButton();  
		        giftButton.setTitle("Sibling Goals");  
		        giftButton.setIcon("flying-heart-icon.png");  
		        giftButton.setIconOrientation("right"); 
		        
		        IButton calenderButton = new IButton();  
		        calenderButton.setTitle("Parents Goals");  
		        calenderButton.setIcon("broken-heart-icon.png");  
		        calenderButton.setIconOrientation("right"); 
		        
		        IButton adviseButton = new IButton();  
		        adviseButton.setTitle("Family Activities");  
		        adviseButton.setIcon("broken-heart-icon.png");  
		        adviseButton.setIconOrientation("right"); 
		        
		        IButton activitiesButton = new IButton();  
		        activitiesButton.setTitle("Needing Attention");  
		        activitiesButton.setIcon("broken-heart-icon.png");  
		        activitiesButton.setIconOrientation("right"); 
		        
		       
		        
		        hideButton.addClickHandler(new ClickHandler() {  
		            public void onClick(ClickEvent event) {  
		                starsLayout.hideMember(datingGoals);
		                starsLayout.hideMember(calendarCanvas); 
		                starsLayout.showMember(greenImg);
		               // starsLayout.showMember(compactCalendar); 
		            }  
		        });  
		  
		        showButton.addClickHandler(new ClickHandler() {  
		            public void onClick(ClickEvent event) { 
		            	starsLayout.hideMember(greenImg);  
		            	starsLayout.hideMember(calendarCanvas);
		                starsLayout.showMember(datingGoals); 
		             //   starsLayout.showMember(compactCalendar);  
		            }  
		        });  
		        calenderButton.addClickHandler(new ClickHandler() {  
		            public void onClick(ClickEvent event) { 
		            	starsLayout.hideMember(greenImg);  
		            	starsLayout.hideMember(datingGoals);
		                starsLayout.showMember(calendarCanvas); 
		             //   starsLayout.showMember(compactCalendar); 
		            }  
		        }); 
		  
		        VLayout vLayout = new VLayout();  
		        vLayout.setMembersMargin(10);  
		        vLayout.addMember(hideButton); 
		        vLayout.addMember(giftButton); 
		        vLayout.addMember(showButton);  
		        vLayout.addMember(adviseButton); 
		        vLayout.addMember(calenderButton); 
		        vLayout.addMember(activitiesButton); 
		       
		        layout.addMember(vLayout);  
		       // layout.addChild(starsLayout);  
		        layout.addMember(starsLayout);  
		   
		   //  layout.draw(); 
		     initWidget(layout);
			
			
		}
		 public static FamilyView1 getInstance() {
		     if(instance == null) {
		        instance = new FamilyView1();
		     }
		     return instance;
		 }
		 
		 private Img createImage(String src) {  
		        Img img = new Img(src);  
		        img.setAppImgDir("images/");  
		        img.setWidth(48);  
		        img.setHeight(48);  
		        img.setLayoutAlign(Alignment.CENTER);  
		        return img;  
		    }  
		 private Widget getGwtTab(){
			 return DateMakeView.getInstance(); 
		 }
}