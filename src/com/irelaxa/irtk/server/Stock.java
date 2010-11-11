package com.irelaxa.irtk.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.irelaxa.irtk.client.StockObject;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class Stock {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;
  @Persistent
  private User user;
  @Persistent
  private String symbol;
  @Persistent
  private String goalType;
  @Persistent
  private String budget;
  @Persistent
  private String spending = "0";
  @Persistent
  Boolean  isPublic = false; 
  @Persistent
  private Date startDate;
  @Persistent
  private Date endDate;
@Persistent
  private Date createDate;
@Persistent(mappedBy = "stock")
@Element(dependent = "true")
private List<GoalStatusP> goalStatuses;
@Persistent(mappedBy = "goal")
@Element(dependent = "true")
private List<ActionPlanStepP> actionPlanSteps;

private StockObject stockObject = new StockObject();
  public Stock() {
    this.createDate = new Date();
    this.goalStatuses = new ArrayList<GoalStatusP>();
    this.actionPlanSteps = new ArrayList<ActionPlanStepP>();
  }

  public Stock(User user, 
		  String symbol, 
		  String goalType, 
		  String budget , 
		  Date startDate, 
		  Date endDate) {
		  //ArrayList<GoalStatusP> goalStatusPs) {
    this();
    this.user = user;
    this.symbol = symbol;
    this.goalType = goalType;
    this.budget = budget;
   // this.spending = spending;
    this.startDate = startDate;
    this.endDate = endDate;
   // this.goalStatuses = goalStatusPs;
    
  }

  public Key getKey() {
    return this.key;
  }
  public void setKey(Key id) {
		this.key = id;
	}

  public User getUser() {
    return this.user;
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

  public void setUser(User user) {
    this.user = user;
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

public void setAlLGoalStatus(List<GoalStatusP> goalStatuses) {
	this.goalStatuses = goalStatuses;
}

public List<GoalStatusP> getAllGoalStatus() {
	return goalStatuses;
}


public List<ActionPlanStepP> getActionPlanSteps() {
	return actionPlanSteps;
}

public void setActionPlanSteps(List<ActionPlanStepP> actionPlanSteps) {
	this.actionPlanSteps = actionPlanSteps;
}

public void addGoalStatus(GoalStatusP goalStatus){
	this.goalStatuses.add(goalStatus);
}
public void addActionPlanStep(ActionPlanStepP actionPlanStep){
	this.actionPlanSteps.add(actionPlanStep);
}

public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public List<GoalStatusP> getGoalStatuses() {
	return goalStatuses;
}

public void setGoalStatuses(List<GoalStatusP> goalStatuses) {
	this.goalStatuses = goalStatuses;
}

public StockObject toStockObject(){
	stockObject.setKey(this.key.getId());
	stockObject.setGoalType(this.goalType);
	stockObject.setSymbol(this.symbol);
	//stockObject.setUser(this.user);
	stockObject.setBudget(this.budget);
	stockObject.setStartDate(this.startDate);
	stockObject.setEndDate(this.endDate);
	for (GoalStatusP goalStatusP : goalStatuses) {
		stockObject.addGoalStatus(goalStatusP.toGoalStatusObject());
    }
	return stockObject;
}
}