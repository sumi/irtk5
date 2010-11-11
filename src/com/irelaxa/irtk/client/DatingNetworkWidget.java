package com.irelaxa.irtk.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DatingNetworkWidget {
	private HorizontalPanel hp = new HorizontalPanel();
	private VerticalPanel vp1 = new VerticalPanel();
	private VerticalPanel vp2 = new VerticalPanel();
	private VerticalPanel vp3 = new VerticalPanel();
	private Image logo = new Image();
	private TextArea ta = new TextArea();
	private Anchor signOutLink = new Anchor("Sign Out");
	public DatingNetworkWidget(){
	
    	vp1.setWidth("300px");
    	logo.setUrl("http://www.irelaxa.com/images/logo.png");
    	vp1.add(logo);
    	
    	vp2.setWidth("300px");
    	ta.setPixelSize(600, 40);
    	vp2.add(new VisualizationWidget().getInstance());
    	
    	vp3.setWidth("300px");
    	vp3.add(signOutLink);
    	
    	
    	hp.add(vp1);
    	hp.add(vp2);
    	hp.add(vp3);
	}
	 public Widget getWidget() {
	    return hp;
	  }
	 
	

}