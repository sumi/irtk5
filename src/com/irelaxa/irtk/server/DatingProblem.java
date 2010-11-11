package com.irelaxa.irtk.server;

import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class DatingProblem {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long id;
  @Persistent
  private User user;
  @Persistent
  private String problemArea;
  @Persistent
  private String timeWasted;
  @Persistent
  private String cost;
  @Persistent
  private String emotion;
  @Persistent
  private String careerImpact;
@Persistent
  private Date createDate;


 
  public DatingProblem() {
    this.createDate = new Date();
  }

  public DatingProblem(User user, String problemArea, String timeWasted, String cost, String emotion, String careerImpact) {
    this();
    this.user = user;
    this.problemArea = problemArea;
    this.timeWasted = timeWasted;
    this.cost = cost; 
    this.emotion = emotion;
    this.careerImpact = careerImpact; 
  }

  public String getProblemArea() {
	return problemArea;
}

public void setProblemArea(String problemArea) {
	this.problemArea = problemArea;
}

public String getTimeWasted() {
	return timeWasted;
}

public void setTimeWasted(String timeWasted) {
	this.timeWasted = timeWasted;
}

public Long getId() {
    return this.id;
  }

  public User getUser() {
    return this.user;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setUser(User user) {
    this.user = user;
  }

 

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public String getCareerImpact() {
		return careerImpact;
	}

	public void setCareerImpact(String careerImpact) {
		this.careerImpact = careerImpact;
	}
	 
}