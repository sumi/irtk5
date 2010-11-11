package com.irelaxa.irtk.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.irelaxa.irtk.client.CategoryObject;
import com.irelaxa.irtk.client.StockObject;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class Category {
	@PrimaryKey
	  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	  private Long key;
	  @Persistent
	  private String category;
	  private CategoryObject categoryObject = new CategoryObject();
	  
	  public Category(String category){
		  this.category = category;
	  }
	  public Category(Long key){
		  this.key = key;
	  }
	public void setKey(Long key) {
		this.key = key;
	}
	public Long getKey() {
		return key;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
	
	public CategoryObject toCategoryObject(){
		if(categoryObject == null){
			categoryObject = new CategoryObject();
		}
		categoryObject.setKey(this.key);
		categoryObject.setCategory(this.category);
		return categoryObject;
	}
	  
}