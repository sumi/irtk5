package com.irelaxa.irtk.server;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.irelaxa.irtk.client.ActionPlanObject;
import com.irelaxa.irtk.client.GoalStatus;
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class ActionPlanStepP  {
	  @PrimaryKey
	  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	  private Key key;
	  @Persistent
	  private int stepNumber;
	  @Persistent
	  private String milestone;
	  @Persistent
	  private Date createDate;
	  @Persistent(defaultFetchGroup = "true")
	  private Stock goal;
	  private ActionPlanObject actionPlanObject = new ActionPlanObject();
	
	public ActionPlanStepP()
	{
	}

	public ActionPlanStepP(ActionPlanObject actionPlanObject){
		this.stepNumber = actionPlanObject.getStepNumber();
		this.milestone = actionPlanObject.getMilestone();
		this.createDate = new Date();
	}
	

	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}

	
	public int getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Stock getGoal() {
		return goal;
	}

	public void setGoal(Stock goal) {
		this.goal = goal;
	}

	public ActionPlanObject toActionPlanObject(){
		actionPlanObject.setId(this.key.getId());
		actionPlanObject.setCreateDate(this.createDate);
		actionPlanObject.setStepNumber(this.stepNumber);
		actionPlanObject.setMilestone(this.milestone);
		actionPlanObject.setActionPlanParent(this.goal.getKey().getId());
		return actionPlanObject;
	}
	
}