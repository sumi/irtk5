package com.irelaxa.irtk.client;

import java.io.Serializable;

public class CategoryObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long key;
	private String category;
	public CategoryObject(){
		
	}
	public CategoryObject(Long key, 
			  String category) {
	    this.key = key;
	    this.category = category;
	    
	  }
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}