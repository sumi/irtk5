package com.irelaxa.irtk.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class AddCanvasToRootPanel {
	public AddCanvasToRootPanel(){
		getViewPanel().draw();
	}
	public Canvas getViewPanel() {
	    Canvas canvas = new Canvas();

	    final Window window = new Window();
	    window.setTitle("Window with footer");
	    window.setWidth(200);
	    window.setHeight(200);
	    window.setCanDragResize(true);
	    window.setShowFooter(true);

	    Label label = new Label();
	    label.setContents("Click Me");
	    label.setAlign(Alignment.CENTER);
	    label.setPadding(5);
	    label.setHeight100();
	    label.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	            window.setStatus("Click at: " + event.getX() + ", " + event.getY());
	        }
	    });
	    window.addItem(label);
	    canvas.addChild(window);

	    return canvas;
	  }
}