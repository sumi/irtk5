package com.irelaxa.irtk.client;

import java.util.ArrayList;
import java.util.Stack;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("stock")
public interface StockService extends RemoteService {
  public String getBlobStoreUrl();
  public void sendMail(ArrayList mailObject);
  public void addStock(Stack dataObject, String objectName) throws NotLoggedInException;
  public void addSponsor(SponsorObject sponsorObject) throws NotLoggedInException;
  public void addDatingActivity(DatingActivityObject object) throws NotLoggedInException;
  public void addStockWithGoalStatus(Long stockId, GoalStatus goalStatus) throws NotLoggedInException;
  public void addStockWithActionSteps(Long stockId, ArrayList<ActionPlanObject> actionPlanSteps) throws NotLoggedInException;
  public void removeStock(String symbol) throws NotLoggedInException;
  public String[][] getAllGoals() throws NotLoggedInException;
  public ArrayList<DatingActivityObject> getAllDatingActivityGoals() throws NotLoggedInException;
  public String[][] getDatingGoals() throws NotLoggedInException;
  public ArrayList<StockObject> getDatingGoals1() throws NotLoggedInException;
  public String[][] getProducts() throws NotLoggedInException;
  public String[][] getDatingProblems() throws NotLoggedInException;
  public String[][] getAllInterns() throws NotLoggedInException;
  public String[][] getAllInternships() throws NotLoggedInException;
  public String[][] getAllMentorships() throws NotLoggedInException;
  public String[][] getMyCareerGoals() throws NotLoggedInException;
  public String[][] getMyInternshipsBizGoals() throws NotLoggedInException;
  public String[][] getMyMentorshipsBizGoals() throws NotLoggedInException;
  public ArrayList<CategoryObject> getAllCategories() throws NotLoggedInException;
}