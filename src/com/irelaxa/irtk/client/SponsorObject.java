package com.irelaxa.irtk.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SponsorObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String businessName;
	private String businessAddress;
	private String businessCity;
	private String businessState;
	private String businessZip;
	private String businessDes;
	private List<Long> categories = new ArrayList<Long>();
	private List<String> sponsorshipZips = new ArrayList<String>();
	private int sponsorshipPackage;
	public void addCategoryKey(Long categoryKey) {
		categories.add(categoryKey);
	}
	public void removeCategory(Long key) {
		categories.remove(key);
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
	public List<Long> getCategories() {
		return categories;
	}
	public void setCategories(List<Long> categories) {
		this.categories = categories;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}