package com.gwtfb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * @author ola
 * 
 */
public class GwtFB implements EntryPoint, ValueChangeHandler<String>  {

    
   

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

	    History.addValueChangeHandler ( this );



		//

		}


	 public void onValueChange(ValueChangeEvent<String> event) {

	    }

}