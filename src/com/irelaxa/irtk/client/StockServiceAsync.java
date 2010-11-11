package com.irelaxa.irtk.client;

import java.util.ArrayList;
import java.util.Stack;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StockServiceAsync {
  public void getBlobStoreUrl(AsyncCallback<String> async);
  public void sendMail(ArrayList mailObject, AsyncCallback<Void> async);
  public void addStock(Stack dataObject, String objectName, AsyncCallback<Void> async);
  public void addSponsor(SponsorObject sponsorObject, AsyncCallback<Void> async);
  public void addDatingActivity(DatingActivityObject object, AsyncCallback<Void> async);
  public void addStockWithGoalStatus(Long stockId, GoalStatus goalStatus, AsyncCallback<Void> async);
  public void addStockWithActionSteps(Long stockId, ArrayList<ActionPlanObject> actionPlanSteps, AsyncCallback<Void> async);
  public void removeStock(String symbol, AsyncCallback<Void> async);
  public void getAllGoals(AsyncCallback<String[][]> async);
  public void getAllDatingActivityGoals(AsyncCallback<ArrayList<DatingActivityObject>> async);
  public void getDatingGoals(AsyncCallback<String[][]> async);
  public void getDatingGoals1(AsyncCallback<ArrayList<StockObject>> async);
  public void getProducts(AsyncCallback<String[][]> async);
  public void getDatingProblems(AsyncCallback<String[][]> async);
  public void getAllInterns(AsyncCallback<String[][]> async);
  public void getAllInternships(AsyncCallback<String[][]> async);
  public void getAllMentorships(AsyncCallback<String[][]> async);
  public void getMyCareerGoals(AsyncCallback<String[][]> async);
  public void getMyInternshipsBizGoals(AsyncCallback<String[][]> async);
  public void getMyMentorshipsBizGoals(AsyncCallback<String[][]> async);
  public void getAllCategories(AsyncCallback<ArrayList<CategoryObject>> asyncCallback);
  
}