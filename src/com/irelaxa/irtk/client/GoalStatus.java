package com.irelaxa.irtk.client;
import java.io.Serializable;
import java.util.Date;




public class GoalStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long statusParent;
	private Long spendingAmount;
	private Date spendingDate;
	private String milestone;
	private Double completeness;
	private String comment;
	private Date createDate;

	public GoalStatus()
	{
		this.createDate = new Date();
	}
	public GoalStatus(Long spendingAmount, Date spendingDate, String milestone, Double completeness, String comment)
	{
		//this.id = id;
		this.spendingAmount = spendingAmount;
		this.spendingDate = spendingDate;
		this.milestone = milestone;
		this.completeness = completeness;
		this.comment = comment;
		this.createDate = new Date();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatusParent(Long statusParent) {
		this.statusParent = statusParent;
	}

	public Long getStatusParent() {
		return statusParent;
	}

	public Long getSpendingAmount() {
		return spendingAmount;
	}

	public void setSpendingAmount(Long spendingAmount) {
		this.spendingAmount = spendingAmount;
	}

	public Date getSpendingDate() {
		return spendingDate;
	}

	public void setSpendingDate(Date spendingDate) {
		this.spendingDate = spendingDate;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public Double getCompleteness() {
		return completeness;
	}

	public void setCompleteness(Double completeness) {
		this.completeness = completeness;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}