package com.irelaxa.irtk.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.irelaxa.irtk.client.CategoryObject;
import com.irelaxa.irtk.client.SponsorObject;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class Sponsor {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	@Element(dependent = "true")
    private List<Long> categories = new ArrayList<Long>();
	@Persistent
	private String businessName;
	@Persistent
	private String businessAddress;
	@Persistent
	private String businessCity;
	@Persistent
	private String businessState;
	@Persistent
	private String businessZip;
	@Persistent
	private String businessDes;
	@Persistent
	private List<String> sponsorshipZips = new ArrayList<String>();
	@Persistent
	private int sponsorshipPackage;
	@Persistent
	private Boolean paid;
	@Persistent
	private User user;
	public Sponsor(SponsorObject sponsorObject){
		
		this.businessAddress = sponsorObject.getBusinessAddress();
		this.businessCity = sponsorObject.getBusinessCity();
		this.businessDes = sponsorObject.getBusinessDes();
		this.businessName = sponsorObject.getBusinessName();
		this.businessState = sponsorObject.getBusinessState();
		this.businessZip = sponsorObject.getBusinessZip();
		for(Long categoryObjectKeyLong : sponsorObject.getCategories()){
		this.categories.add(categoryObjectKeyLong);
		}
		
		
	}
public Sponsor(){
		
	}
	public void addCategory(Category category) {
		categories.add(category.getKey());
	}
	public void addCategoryKey(Long categoryKey) {
		categories.add(categoryKey);
	}
	public void removeCategory(Category category) {
		categories.remove(category.getKey());
	}
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public List<Long> getCategories() {
		return categories;
	}
	public void setCategories(List<Long> categories) {
		this.categories = categories;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getBusinessCity() {
		return businessCity;
	}
	public void setBusinessCity(String businessCity) {
		this.businessCity = businessCity;
	}
	public String getBusinessState() {
		return businessState;
	}
	public void setBusinessState(String businessState) {
		this.businessState = businessState;
	}
	public String getBusinessZip() {
		return businessZip;
	}
	public void setBusinessZip(String businessZip) {
		this.businessZip = businessZip;
	}
	public String getBusinessDes() {
		return businessDes;
	}
	public void setBusinessDes(String businessDes) {
		this.businessDes = businessDes;
	}
	public List<String> getSponsorshipZips() {
		return sponsorshipZips;
	}
	public void setSponsorshipZips(List<String> sponsorshipZips) {
		this.sponsorshipZips = sponsorshipZips;
	}
	public int getSponsorshipPackage() {
		return sponsorshipPackage;
	}
	public void setSponsorshipPackage(int sponsorshipPackage) {
		this.sponsorshipPackage = sponsorshipPackage;
	}
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	
	public User getUser() {
	    return this.user;
	  }
	public void setUser(User user) {
	    this.user = user;
	  }
	
}