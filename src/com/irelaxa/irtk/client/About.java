package com.irelaxa.irtk.client;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;

public class About extends Canvas {
	 HTMLFlow htmlFlow = new HTMLFlow(); 
	public About(){ 
		getAboutPanel().draw();  
	}
	
	public Canvas getAboutPanel() {
		Canvas canvas = new Canvas();
		   htmlFlow.setWidth(230);  
	        htmlFlow.setStyleName("exampleTextBlock");  
	        String contents = "<hr><span class='exampleDropTitle'>Ajax  </span> " +  
	                "<b>A</b>synchronous <b>J</b>avaScript <b>A</b>nd <b>X</b>ML (AJAX) is a " +  
	                "Web development technique for creating interactive <b>web applications</b>.<hr>";  
	        htmlFlow.setContents(contents);  
	        canvas.addChild(htmlFlow);
	    return canvas;
	  }
}