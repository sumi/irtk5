package com.irelaxa.irtk.server;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;
import com.irelaxa.irtk.client.GoalStatus;
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class GoalStatusP  {
	  @PrimaryKey
	  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	  private Key key;
	  @Persistent
	  private Long spendingAmount;
	  @Persistent
	  private Date spendingDate;
	  @Persistent
	  private String milestone;
	  @Persistent
	  private Double completeness;
	  @Persistent
	  private String comment;
	  @Persistent
	  private Date createDate;
	 
	//  @Persistent(defaultFetchGroup = "true")
	  @Persistent
	  private Stock stock;
	  
	  private GoalStatus goalStatus = new GoalStatus();

	public GoalStatusP()
	{
	}

	public GoalStatusP(GoalStatus goalStatusObject){
		this.spendingAmount = goalStatusObject.getSpendingAmount();
		this.spendingDate = goalStatusObject.getSpendingDate();
		this.milestone = goalStatusObject.getMilestone();
		this.completeness = goalStatusObject.getCompleteness();
		this.comment = goalStatusObject.getComment();
		this.createDate = goalStatusObject.getCreateDate();
	}
	

	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
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

	public void setStock(Stock goal) {
		this.stock = goal;
	}

	public Stock getStock() {
		return stock;
	}
	
	public GoalStatus toGoalStatusObject(){
		goalStatus.setId(this.key.getId());
		goalStatus.setComment(this.comment);
		goalStatus.setCompleteness(this.completeness);
		//stockObject.setUser(this.user);
		goalStatus.setCreateDate(this.createDate);
		goalStatus.setMilestone(this.milestone);
		goalStatus.setSpendingAmount(this.spendingAmount);
		goalStatus.setStatusParent(this.stock.getKey().getId());
		return goalStatus;
	}
	
}