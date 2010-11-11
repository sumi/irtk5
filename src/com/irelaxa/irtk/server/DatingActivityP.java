package com.irelaxa.irtk.server;
import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.irelaxa.irtk.client.DatingActivityObject;
import com.irelaxa.irtk.client.StockObject;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class DatingActivityP implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	  @Persistent
	private String activityWith;
	  @Persistent
	private Date activityDate;
	  @Persistent
	private String activity;
	  @Persistent
	private Double budget;
	  @Persistent
	private String activityType;
	  @Persistent
	private Double hrsSpent;
	  @Persistent
	private Date createDate;
	
	  private DatingActivityObject object = new DatingActivityObject();
	public DatingActivityP()
	{
	}
	public DatingActivityP(DatingActivityObject object)
	{
		//this.id = id;
		this.activityWith = object.getActivityWith();
		this.activityDate = object.getActivityDate();
		this.activity = object.getActivity();
		this.budget = object.getBudget();
		this.activityType = object.getActivityType();
		this.hrsSpent = object.getHrsSpent();
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
	public DatingActivityObject toDatingActivityObject(){
		object.setId(this.id);
		object.setActivity(this.activity);
		object.setActivityDate(this.activityDate);
		object.setActivityType(this.activityType);
		object.setActivityWith(this.activityWith);
		object.setBudget(this.budget);
		object.setCreateDate(this.createDate);
		object.setHrsSpent(this.hrsSpent);
		return object;
	}
	
}