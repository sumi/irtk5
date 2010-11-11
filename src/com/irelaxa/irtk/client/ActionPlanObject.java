package com.irelaxa.irtk.client;
import java.io.Serializable;
import java.util.Date;




public class ActionPlanObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long actionPlanParent;
	private int stepNumber;
	private String milestone;
	private Date createDate;

	public ActionPlanObject()
	{
		this.createDate = new Date();
	}
	public ActionPlanObject(Long id, 
			Long actionPlanParent,
			int stepNumber,
			String milestone)
	{
		this.id = id;
		this.actionPlanParent = actionPlanParent;
		this.stepNumber = stepNumber;
		this.milestone = milestone;
		this.createDate = new Date();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getActionPlanParent() {
		return actionPlanParent;
	}
	public void setActionPlanParent(Long actionPlanParent) {
		this.actionPlanParent = actionPlanParent;
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
	
	
	
}