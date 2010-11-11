package com.irelaxa.irtk.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockObject  implements Serializable{

  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private Long key;



  private String symbol;

  private String goalType;
  
  private String budget;

  private String spending = "0";

  Boolean  isPublic = false; 

  private Date startDate;

  private Date endDate;

  private Date createDate;


private List<GoalStatus> goalStatuses = new ArrayList<GoalStatus>();

  public StockObject() {
    this.createDate = new Date();
  }

  public StockObject(String symbol, 
		  String goalType, 
		  String budget , 
		  Date startDate, 
		  Date endDate) {
    this();
    
    this.symbol = symbol;
    this.goalType = goalType;
    this.budget = budget;
   // this.spending = spending;
    this.startDate = startDate;
    this.endDate = endDate;
    
  }

  public Long getKey() {
    return this.key;
  }
  public void setKey(Long id) {
	    this.key = id;
	  }

  

  public String getSymbol() {
    return this.symbol;
  }
  
  public String getGoalType() {
	    return this.goalType;
	  }
  public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getSpending() {
		return spending;
	}
	public void setSpending(String spending) {
		this.spending = spending;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	public Boolean getIsPublic() {
		return isPublic;
	}

	

  public Date getCreateDate() {
    return this.createDate;
  }

 

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
  
  public void setGoalType(String goalType) {
	    this.goalType = goalType;
	  }

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public Date getStartDate() {
	return startDate;
}

public void setEndDate(Date endDate) {
	this.endDate = endDate;
}

public Date getEndDate() {
	return endDate;
}

public void setAlLGoalStatus(List<GoalStatus> goalStatus) {
	this.goalStatuses = goalStatuses;
}

public List<GoalStatus> getAllGoalStatus() {
	return goalStatuses;
}
public void addGoalStatus(GoalStatus goalStatus){
	this.goalStatuses.add(goalStatus);
}
}