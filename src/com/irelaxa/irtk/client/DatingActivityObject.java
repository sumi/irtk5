package com.irelaxa.irtk.client;
import java.io.Serializable;
import java.util.Date;




public class DatingActivityObject implements Serializable{
	private Long id;
	private String activityWith;
	private Date activityDate;
	private String activity;
	private Double budget;
	private String activityType;
	private Double hrsSpent;
	private Date createDate;
	
	public DatingActivityObject()
	{
	}
	public DatingActivityObject(String activityWith, 
			Date activityDate, 
			String activity, 
			Double budget, 
			String activityType,
			Double hrsSpent)
	{
		//this.id = id;
		this.activityWith = activityWith;
		this.activityDate = activityDate;
		this.activity = activity;
		this.budget = budget;
		this.activityType = activityType;
		this.hrsSpent = hrsSpent;
	}

	
	public String getActivityWith() {
		return activityWith;
	}
	public void setActivityWith(String activityWith) {
		this.activityWith = activityWith;
	}
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public Double getHrsSpent() {
		return hrsSpent;
	}
	public void setHrsSpent(Double hrsSpent) {
		this.hrsSpent = hrsSpent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}