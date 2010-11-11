package com.irelaxa.irtk.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.irelaxa.irtk.client.ActionPlanObject;
import com.irelaxa.irtk.client.CategoryObject;
import com.irelaxa.irtk.client.DatingActivityObject;
import com.irelaxa.irtk.client.GoalStatus;
import com.irelaxa.irtk.client.NotLoggedInException;
import com.irelaxa.irtk.client.SponsorObject;
import com.irelaxa.irtk.client.StockObject;
import com.irelaxa.irtk.client.StockService;
import com.irelaxa.irtk.shared.FieldVerifier;
import com.paypal.adaptive.api.requests.fnapi.ParallelPay;
import com.paypal.adaptive.api.requests.fnapi.SimplePay;
import com.paypal.adaptive.api.responses.CancelPreapprovalResponse;
import com.paypal.adaptive.api.responses.ConvertCurrencyResponse;
import com.paypal.adaptive.api.responses.PayResponse;
import com.paypal.adaptive.api.responses.PaymentDetailsResponse;
import com.paypal.adaptive.api.responses.PreapprovalDetailsResponse;
import com.paypal.adaptive.api.responses.PreapprovalResponse;
import com.paypal.adaptive.api.responses.RefundResponse;
import com.paypal.adaptive.core.APICredential;
import com.paypal.adaptive.core.AckCode;
import com.paypal.adaptive.core.CurrencyCodes;
import com.paypal.adaptive.core.PaymentType;
import com.paypal.adaptive.core.Receiver;
import com.paypal.adaptive.core.ServiceEnvironment;
import com.paypal.adaptive.exceptions.AuthorizationRequiredException;
import com.paypal.adaptive.exceptions.InvalidAPICredentialsException;
import com.paypal.adaptive.exceptions.InvalidResponseDataException;
import com.paypal.adaptive.exceptions.MissingAPICredentialsException;
import com.paypal.adaptive.exceptions.MissingParameterException;
import com.paypal.adaptive.exceptions.NotEnoughReceivers;
import com.paypal.adaptive.exceptions.PayPalErrorException;
import com.paypal.adaptive.exceptions.PaymentExecException;
import com.paypal.adaptive.exceptions.PaymentInCompleteException;
import com.paypal.adaptive.exceptions.ReceiversCountMismatchException;
import com.paypal.adaptive.exceptions.RequestAlreadyMadeException;
import com.paypal.adaptive.exceptions.RequestFailureException;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class StockServiceImpl extends RemoteServiceServlet implements
StockService {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final Logger LOG = Logger.getLogger(StockServiceImpl.class.getName());
  private static final PersistenceManagerFactory PMF =
      JDOHelper.getPersistenceManagerFactory("transactions-optional");
  private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); 
  public void addStockWithGoalStatus(Long stockId, GoalStatus goalStatus) throws NotLoggedInException{
	  checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    Key key = KeyFactory.createKey(GoalStatusP.class.getSimpleName(), stockId);
	    try {
	    	
	    	tx.begin();

	    	 Stock stock = (Stock) pm.getObjectById(Stock.class, stockId);
	    	 GoalStatusP goalStatusP = new GoalStatusP(goalStatus);
	    	 goalStatusP.setKey(key);
	    	 //goalStatusP.setStock(stock);
	    	// stock.addGoalStatus(goalStatusP);
	    	 stock.getGoalStatuses().add(goalStatusP);
	    	pm.makePersistent(stock);
	    	tx.commit();

  } finally {
	  if (tx.isActive()) {
          tx.rollback();
      }

    pm.close();
  }
	    
  }
  
  public void addStockWithActionSteps(Long stockId, ArrayList<ActionPlanObject> actionPlanSteps) throws NotLoggedInException{
	  checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    
	    try {
	    	tx.begin();
	    	 Stock stock = (Stock) pm.getObjectById(Stock.class, stockId);
	    	 for(final ActionPlanObject theActionPlanStep : actionPlanSteps){
	    	 ActionPlanStepP actionPlanStepP = new ActionPlanStepP(theActionPlanStep);
	    	 Key key = KeyFactory.createKey(ActionPlanStepP.class.getSimpleName(), stockId);
	    	 actionPlanStepP.setKey(key);
	    	 stock.getActionPlanSteps().add(actionPlanStepP);
	    	 }
	    	pm.makePersistent(stock);
	    	tx.commit();

  } finally {
	  if (tx.isActive()) {
          tx.rollback();
      }

    pm.close();
  }
	    
  }
  public void addDatingActivity(DatingActivityObject object) throws NotLoggedInException{
	  checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    try {
	    	 DatingActivityP objectToBeSaved = new DatingActivityP(object);
	    	pm.makePersistent(objectToBeSaved);
  } finally {
    pm.close();
  }
  }

  public ArrayList<DatingActivityObject> getAllDatingActivityGoals() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    ArrayList<DatingActivityObject> datingActivitiesObjects;
	    try {
	      Query q = pm.newQuery(DatingActivityP.class, "user == u");
	      q.declareParameters("com.google.appengine.api.users.User u");
	      q.setOrdering("createDate");
	      List<DatingActivityP> datingActivities = (List<DatingActivityP>) q.execute(getUser());
	      datingActivitiesObjects = new ArrayList<DatingActivityObject>(datingActivities.size());
	    //  LOG.log(Level.WARNING, "the getDatingGoals Query "+ q);
	      for (DatingActivityP datingActivity : datingActivities) {
	    	  datingActivitiesObjects.add(datingActivity.toDatingActivityObject());
	      }
	    } finally {
	      pm.close();
	    }
	    return  datingActivitiesObjects;
	  }
  
  public void addStock(Stack dataObject, String objectName) throws NotLoggedInException {
    checkLoggedIn();
    PersistenceManager pm = getPersistenceManager();
    //LOG.log(Level.WARNING, "career manager objectName "+objectName);
    try {
    	/*if(objectName.equals("vendor")){
    		 LOG.log(Level.WARNING, "inside server add stock the object name is "+objectName);
    		pm.makePersistent(new Product(getUser(), (String)dataObject.pop(), (String)dataObject.pop(), (String)dataObject.pop()));
    	}*/
    	if(objectName.matches("goal")){
    		 pm.makePersistent(new Stock(getUser(), (String)dataObject.pop(), (String)dataObject.pop(), (String)dataObject.pop(), (Date)dataObject.pop(), (Date)dataObject.pop()));
    	}
    	if(objectName.matches("product")){
    	//	LOG.log(Level.WARNING, "product objectName "+objectName);
    	      pm.makePersistent(new Product(getUser(), (String)dataObject.pop(), (String)dataObject.pop(), (String)dataObject.pop()));
    	    	}
    	if(objectName.matches("datingproblem")){
    	//	LOG.log(Level.WARNING, "datingproblem objectName "+objectName);
    	      pm.makePersistent(new DatingProblem(getUser(), (String)dataObject.pop(), (String)dataObject.pop(), (String)dataObject.pop(), (String)dataObject.pop(), (String)dataObject.pop()));
    	    	}
    	if(objectName.matches("careermanager")){
    		
  	      pm.makePersistent(new Intern(getUser(), (String)dataObject.pop(), (String)dataObject.pop(), (String)dataObject.pop(), 
  	    		(String)dataObject.pop(), 
  	    		(Date)dataObject.pop(), (Date)dataObject.pop(), (String)dataObject.pop(), 
  	    		(String)dataObject.pop(), (String)dataObject.pop(), (String)dataObject.pop()));
  	    	}
    	if(objectName.matches("internbiz")){
  	      pm.makePersistent(new InternBiz(getUser(), (String)dataObject.pop(), 
  	    		  (String)dataObject.pop(), (String)dataObject.pop(), (String)dataObject.pop(), 
  	    		  (Date)dataObject.pop(), (Date)dataObject.pop(), (String)dataObject.pop(), 
  	    		  (String)dataObject.pop()));
  	    	}
    	if(objectName.matches("mentorbiz")){
  	      pm.makePersistent(new MentorBiz(getUser(), (String)dataObject.pop(), (String)dataObject.pop(), 
  	    		  (String)dataObject.pop(), (String)dataObject.pop(), (Date)dataObject.pop(), 
  	    		  (Date)dataObject.pop(), (String)dataObject.pop(), (String)dataObject.pop()));
  	    	}
    	if(objectName.matches("category")){
    	      pm.makePersistent(new Category((String)dataObject.pop()));
    	    	}
    	
    } finally {
      pm.close();
    }
  }
  
  public void addSponsor(SponsorObject sponsorObject) throws NotLoggedInException{
	  checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    try {
	    	Sponsor sponsor = new Sponsor(sponsorObject);
	    	sponsor.addCategoryKey(new Long(177001));
	    	sponsor.setUser(getUser());
	    	sponsor.setPaid(false);
	    	pm.makePersistent(sponsor);
	    }finally {
	        pm.close();
	    }
	  
  }
 

  public void removeStock(String symbol) throws NotLoggedInException {
    checkLoggedIn();
    PersistenceManager pm = getPersistenceManager();
    
    try {
      long deleteCount = 0;
      Query q = pm.newQuery(Stock.class, "user == u");
      q.declareParameters("com.google.appengine.api.users.User u");
      List<Stock> stocks = (List<Stock>) q.execute(getUser());
      for (Stock stock : stocks) {
    	  if(stock != null) {
        if (symbol.equals(stock.getSymbol())) {
          deleteCount++;
          pm.deletePersistent(stock);
        }
      }
      }
      if (deleteCount != 1) {
    //    LOG.log(Level.WARNING, "removeStock deleted "+deleteCount+" Stocks");
      }
    } finally {
      pm.close();
    }
  }
  
  

  public String[][] getDatingGoals() throws NotLoggedInException {
    checkLoggedIn();
    PersistenceManager pm = getPersistenceManager();
    String[][] goals;
    try {
      Query q = pm.newQuery(Stock.class, "user == u && symbol == 'Dating'");
      q.declareParameters("com.google.appengine.api.users.User u");
      //q.setFilter("goalType == 'Dating'");
      q.setOrdering("createDate");
      List<Stock> stocks = (List<Stock>) q.execute(getUser());
      goals = new String[stocks.size()][6];
      //LOG.log(Level.WARNING, "the getDatingGoals Query "+ q);
      int i =0;
      for (Stock stock : stocks) {
    	  goals[i][0] = stock.getGoalType();
    	  goals[i][1] = stock.getSymbol();
    	  goals[i][2] = stock.getBudget();
    	  goals[i][3] = stock.getStartDate().toString();
    	  goals[i][4] = stock.getEndDate().toString();
    	  goals[i][5] = stock.getKey().toString();
    	  
    	  i++;
      }
    } finally {
      pm.close();
    }
    return  goals;
  }
  public ArrayList<StockObject> getDatingGoals1() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    ArrayList<StockObject> goals;
	    try {
	     // Query q = pm.newQuery(Stock.class, "user == u && symbol == 'Dating'");
	      Query q = pm.newQuery(Stock.class, "user == u && goalType == 'Dating'");
	      q.declareParameters("com.google.appengine.api.users.User u");
	      //q.setFilter("goalType == 'Dating'");
	      q.setOrdering("createDate");
	      List<Stock> stocks = (List<Stock>) q.execute(getUser());
	      //goals = new String[stocks.size()][6];
	      goals = new ArrayList<StockObject>(stocks.size());
	     // LOG.log(Level.WARNING, "the getDatingGoals Query "+ q);
	      for (Stock stock : stocks) {
	    	  goals.add(stock.toStockObject());
	      }
	    } finally {
	      pm.close();
	    }
	    return  goals;
	  }
  public ArrayList<CategoryObject> getAllCategories() throws NotLoggedInException {
	  checkLoggedIn();
	  PersistenceManager pm = getPersistenceManager();
	  ArrayList<CategoryObject> categoryObjects;
	  try {
		  Query q = pm.newQuery(Category.class);
		  List<Category> categories = (List<Category>) q.execute();
		  categoryObjects = new ArrayList<CategoryObject>(categories.size());
		  for (Category category : categories) {
			  categoryObjects.add(category.toCategoryObject());
	      }
	  } finally {
	      pm.close();
	    }
	    return  categoryObjects;
  }
  public String[][] getAllGoals() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    String[][] goals;
	    Date today = new Date();
	    try {
	      Query q = pm.newQuery(Stock.class, "user == u");
	      q.declareParameters("com.google.appengine.api.users.User u");
	      q.setOrdering("createDate");
	      List<Stock> stocks = (List<Stock>) q.execute(getUser());
	      goals = new String[stocks.size()][7];
	      int i =0;
	      for (Stock stock : stocks) {
	    	  goals[i][0] = stock.getGoalType();
	    	  goals[i][1] = stock.getSymbol();
	    	  goals[i][2] = stock.getBudget();
	    	 // goals[i][3] = stock.getStartDate().toString();
	    	  goals[i][3] = FieldVerifier.getMonth(stock.getStartDate().getMonth())+" "+Integer.toString(stock.getStartDate().getDate())+" " +Integer.toString((stock.getStartDate().getYear()+1900));
	    	  goals[i][4] = FieldVerifier.getMonth(stock.getEndDate().getMonth())+" "+Integer.toString(stock.getEndDate().getDate())+" " +Integer.toString((stock.getEndDate().getYear()+1900));
	    	  goals[i][5] = Integer.toString((int)(stock.getEndDate().getTime()-today.getTime())/86400000);
	    	//  LOG.log(Level.WARNING, "Remaining Days "+goals[i][5]);
	    	  i++;
	      }
	    } finally {
	      pm.close();
	    }
	    return  goals;
	  }
  
  public String[][] getProducts() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    String[][] products;
	    try {
	      Query q = pm.newQuery(Product.class, "user == u");
	      q.declareParameters("com.google.appengine.api.users.User u");
	      q.setOrdering("createDate");
	      List<Product> dsproducts = (List<Product>) q.execute(getUser());
	      products = new String[dsproducts.size()][6];
	      int i =0;
	      for (Product product : dsproducts) {
	    	  products[i][0] = product.getBizName();
	    	  products[i][1] = product.getProductType();
	    	  products[i][2] = product.getPrice();
	    	  i++;
	      }
	    } finally {
	      pm.close();
	    }
	    return  products;
	  }
  
  public String[][] getDatingProblems() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    String[][] datingproblems;
	    try {
	      Query q = pm.newQuery(DatingProblem.class, "user == u");
	      q.declareParameters("com.google.appengine.api.users.User u");
	      q.setOrdering("createDate");
	      List<DatingProblem> dsdatingproblems = (List<DatingProblem>) q.execute(getUser());
	      datingproblems = new String[dsdatingproblems.size()][6];
	      int i =0;
	      for (DatingProblem datingproblem : dsdatingproblems) {
	    	  datingproblems[i][0] = datingproblem.getProblemArea();
	    	  datingproblems[i][1] = datingproblem.getTimeWasted();
	    	  datingproblems[i][2] = datingproblem.getCost();
	    	  datingproblems[i][1] = datingproblem.getEmotion();
	    	  datingproblems[i][2] = datingproblem.getCareerImpact();
	    	  i++;
	      }
	    } finally {
	      pm.close();
	    }
	    return  datingproblems;
	  }
  
  public String[][] getAllInternships() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    String[][] internships;
	    try {
	      Query q = pm.newQuery(InternBiz.class);
	      q.setOrdering("createDate");
	      List<InternBiz> dsinternships = (List<InternBiz>) q.execute();
	     // LOG.log(Level.WARNING, "Get all internships Query "+ q);
	      internships = new String[dsinternships.size()][8];
	      int i =0;
	      for (InternBiz internship : dsinternships) {
	    	  internships[i][0] = internship.getSkills();
	    	  internships[i][1] = internship.getTime();
	    	  internships[i][2] = internship.getproduct();
	    	  internships[i][3] = internship.getpay();
	    	  internships[i][4] = internship.getStartDate().toString();
	    	  internships[i][5] = internship.getEndDate().toString();
	    	  internships[i][6] = internship.getBizName();
	    	  internships[i][7] = internship.getContactName();
	    	  i++;
	      }
	    } finally {
	      pm.close();
	    }
	    return  internships;
	  }
  
  public String[][] getAllInterns() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    String[][] interns;
	    try {
	      Query q = pm.newQuery(Intern.class);
	      q.setOrdering("createDate");
	      List<Intern> dsinterns = (List<Intern>) q.execute();
	     // LOG.log(Level.WARNING, "Get all internships "+ q);
	      int i =0;
	      interns = new String[dsinterns.size()][10];
	      for (Intern intern : dsinterns) {
	    	  interns[i][0] = intern.getlongTermGoal();
	    	  interns[i][1] = intern.getshortTermGoal();
	    	  interns[i][2] = intern.getavailableTimes();
	    	  interns[i][3] = intern.getStipend();
	    	  interns[i][4] = intern.getStartDate().toString();
	    	  interns[i][5] = intern.getEndDate().toString();
	    	  interns[i][6] = intern.getFirstName();
	    	  interns[i][7] = intern.getLastName();
	    	  interns[i][8] = intern.getUniversity();
	    	  interns[i][9] = intern.getDegree();
	    	  i++;
	      }
	    } finally {
	      pm.close();
	    }
	    return  interns;
	  }
  public String[][] getMyCareerGoals() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    String[][] interns;
	    try {
	      Query q = pm.newQuery(Intern.class, "user == u");
	      q.declareParameters("com.google.appengine.api.users.User u");
	      q.setOrdering("createDate");
	      List<Intern> dsinterns = (List<Intern>) q.execute(getUser());
	      interns = new String[dsinterns.size()][10];
	   //   LOG.log(Level.WARNING, "Get all internships "+ q);
	      int i =0;
	      for (Intern intern : dsinterns) {
	    	  interns[i][0] = intern.getlongTermGoal();
	    	  interns[i][1] = intern.getshortTermGoal();
	    	  interns[i][2] = intern.getavailableTimes();
	    	  interns[i][3] = intern.getStipend();
	    	  interns[i][4] = intern.getStartDate().toString();
	    	  interns[i][5] = intern.getEndDate().toString();
	    	  interns[i][6] = intern.getFirstName();
	    	  interns[i][7] = intern.getLastName();
	    	  interns[i][8] = intern.getUniversity();
	    	  interns[i][9] = intern.getDegree();
	    	  i++;
	      }
	    } finally {
	      pm.close();
	    }
	    return  interns;
	  }
  public String[][] getMyInternshipsBizGoals() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    String[][] internsships;
	    try {
	      Query q = pm.newQuery(InternBiz.class, "user == u");
	      q.declareParameters("com.google.appengine.api.users.User u");
	      q.setOrdering("createDate");
	      List<InternBiz> dsinternsBiz = (List<InternBiz>) q.execute(getUser());
	      internsships = new String[dsinternsBiz.size()][10];
	   //   LOG.log(Level.WARNING, "Get My internshipsBiz "+ q);
	      int i =0;
	      for (InternBiz internBiz : dsinternsBiz) {
	    	  internsships[i][0] = internBiz.getSkills();
	    	  internsships[i][1] = internBiz.getTime();
	    	  internsships[i][2] = internBiz.getproduct();
	    	  internsships[i][3] = internBiz.getpay();
	    	  internsships[i][4] = internBiz.getStartDate().toString();
	    	  internsships[i][5] = internBiz.getEndDate().toString();
	    	  internsships[i][6] = internBiz.getBizName();
	    	  internsships[i][7] = internBiz.getContactName();
	    	 
	    	  i++;
	      }
	    } finally {
	      pm.close();
	    }
	    return  internsships;
	  }  
  public String[][] getMyMentorshipsBizGoals() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    String[][] internsships;
	    try {
	      Query q = pm.newQuery(MentorBiz.class, "user == u");
	      q.declareParameters("com.google.appengine.api.users.User u");
	      q.setOrdering("createDate");
	      List<MentorBiz> dsinternsBiz = (List<MentorBiz>) q.execute(getUser());
	      internsships = new String[dsinternsBiz.size()][10];
	 //     LOG.log(Level.WARNING, "Get My internshipsBiz "+ q);
	      int i =0;
	      for (MentorBiz internBiz : dsinternsBiz) {
	    	  internsships[i][0] = internBiz.getSkills();
	    	  internsships[i][1] = internBiz.getTime();
	    	  internsships[i][2] = internBiz.getMenteeDes();
	    	  internsships[i][3] = internBiz.getMentorDes();
	    	  internsships[i][4] = internBiz.getStartDate().toString();
	    	  internsships[i][5] = internBiz.getEndDate().toString();
	    	  internsships[i][6] = internBiz.getBizName();
	    	  internsships[i][7] = internBiz.getContactName();
	    	 
	    	  i++;
	      }
	    } finally {
	      pm.close();
	    }
	    return  internsships;
	  }  
public String[][] getAllMentorships() throws NotLoggedInException {
	    checkLoggedIn();
	    PersistenceManager pm = getPersistenceManager();
	    String[][] mentorships;
	    try {
	      Query q = pm.newQuery(MentorBiz.class);
	      q.setOrdering("createDate");
	      List<MentorBiz> dsmentorships = (List<MentorBiz>) q.execute();
	      mentorships = new String[dsmentorships.size()][8];
	 //     LOG.log(Level.WARNING, "Get all mentorships "+ q);
	      int i =0;
	      for (MentorBiz mentor : dsmentorships) {
	    	  mentorships[i][0] = mentor.getSkills();
	    	  mentorships[i][1] = mentor.getTime();
	    	  mentorships[i][2] = mentor.getMenteeDes();
	    	  mentorships[i][3] = mentor.getMentorDes();
	    	  mentorships[i][4] = mentor.getStartDate().toString();
	    	  mentorships[i][5] = mentor.getEndDate().toString();
	    	  mentorships[i][6] = mentor.getBizName();
	    	  mentorships[i][7] = mentor.getContactName();
	    	  i++;
	      }
	    } finally {
	      pm.close();
	    }
	    return  mentorships;
	  }

public void sendMail(ArrayList mailObject){
	Properties props = new Properties();
	  Session session = Session.getDefaultInstance(props, null);
	  String msgBody = "Lets go on a Date invitation from iRelaxa.com";
	 try {
		 Message msg = new MimeMessage(session);
		 msg.setFrom(new InternetAddress("sumi007@gmail.com", "iRelaxa.com Admin"));
		 msg.addRecipient(Message.RecipientType.TO, 
				 new InternetAddress("sumi007@gmail.com", "Hello Sumi"));
		 msg.setSubject("lets take over the world");
		 msg.setText(msgBody);
		 Transport.send(msg);
		  
	  } catch (AddressException e){
		  
		  
	  } catch (MessagingException e){
		  
} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

  private void checkLoggedIn() throws NotLoggedInException {
    if (getUser() == null) {
      throw new NotLoggedInException("Not logged in.");
    }
  }

  private User getUser() {
    UserService userService = UserServiceFactory.getUserService();
    return userService.getCurrentUser();
  }

  private PersistenceManager getPersistenceManager() {
    return PMF.getPersistenceManager();
	  
  }

@Override
public String getBlobStoreUrl() {
	// TODO Auto-generated method stub
	// String url = blobstoreService.createUploadUrl("/upload");
    // return url;
     return null;
}

private static APICredential credentialObj;



@Override
public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);

        // Get the value of APIUsername
        String APIUsername = getServletConfig().getInitParameter("PPAPIUsername"); 
        String APIPassword = getServletConfig().getInitParameter("PPAPIPassword"); 
        String APISignature = getServletConfig().getInitParameter("PPAPISignature"); 
        String AppID = getServletConfig().getInitParameter("PPAppID"); 
        String AccountEmail = getServletConfig().getInitParameter("PPAccountEmail");

        if(APIUsername == null || APIUsername.length() <= 0
                        || APIPassword == null || APIPassword.length() <=0 
                        || APISignature == null || APISignature.length() <= 0
                        || AppID == null || AppID.length() <=0 ) {
                // requires API Credentials not set - throw exception
                throw new ServletException("APICredential(s) missing");
        }

        credentialObj = new APICredential();
        credentialObj.setAPIUsername(APIUsername);
        credentialObj.setAPIPassword(APIPassword);
        credentialObj.setSignature(APISignature);
        credentialObj.setAppId(AppID);
        credentialObj.setAccountEmail(AccountEmail);
   //     LOG.info("Servlet initialized successfully");
}

public void doGet(HttpServletRequest req, HttpServletResponse resp)
throws IOException {
        try {
                /*String id = req.getParameter("id");
                String title = req.getParameter("title");*/
                String order = req.getParameter("order");
                String returnParam = req.getParameter("return"); 
                String cancel = req.getParameter("cancel");
                SponsorObject sponsorObject = new SponsorObject();
                sponsorObject.setBusinessName(req.getParameter("businessName"));
                sponsorObject.setBusinessAddress(req.getParameter("businessAddress"));
                sponsorObject.setBusinessCity(req.getParameter("businessCity"));
                sponsorObject.setBusinessState(req.getParameter("businessState"));
                sponsorObject.setBusinessZip(req.getParameter("businessZip"));
                sponsorObject.setBusinessDes(req.getParameter("businessDes"));
                /*setting up the categories
                 *Step 1: hardcode the key values of categories
                 *Friendships 147002
                 *Diet 148001
                 *Romance 149001 */
                if(req.getParameter("dietCB").equalsIgnoreCase("on")){
                	sponsorObject.addCategoryKey(new Long(148001));
                }
                addSponsor(sponsorObject);
                System.out.println("Business Name from front form:"+req.getParameter("dietCB"));
/*
                if(cancel != null && cancel.equals("1")) {
                        // user canceled the payment
                        getServletConfig().getServletContext().getRequestDispatcher("/paymentcancel.jsp").forward(req, resp);

                } else if(returnParam != null && returnParam.equals("1")){
                        getServletConfig().getServletContext().getRequestDispatcher("/paymentsuccess.jsp").forward(req, resp);

                } else if(order != null && order.length() > 0){
                        // process order
*/
                        try {

                                StringBuilder url = new StringBuilder();
                                url.append(req.getRequestURL());
                                /*String returnURL = url.toString() + "?return=1&payKey=${payKey}&id="+ id + "&title=" + title;
                                String cancelURL = url.toString() + "?cancel=1&id="+ id + "&title=" + title;*/
                                //String ipnURL = url.toString() + "?action=ipn";

                                SimplePay parallelPay = new SimplePay();
                                /*parallelPay.setCancelUrl(cancelURL);
                                parallelPay.setReturnUrl(returnURL);*/
                               // String newUrl = url.toString().substring(0, 21);
                                String newUrl = url.toString().substring(0, (url.toString().length()-10));
                                //newUrl.substring(0, 20); irtk/stock length 
                              //  getServletConfig().getServletContext().getRequestDispatcher("/paymentcancel.jsp").forward(req, resp);
                              //  getServletConfig().getServletContext().getRequestDispatcher("/paymentcancel.jsp").include(req, resp);
                                parallelPay.setCancelUrl(newUrl+"paymentcancel.jsp");
                              //  System.out.println("URL:::::::::"+newUrl);
                                parallelPay.setReturnUrl(newUrl+"paymentsuccess.jsp");
                                parallelPay.setCredentialObj(credentialObj);
                                parallelPay.setUserIp(req.getRemoteAddr());
                                parallelPay.setApplicationName("iRelaxa:Monetize your Actions");
                                parallelPay.setCurrencyCode(CurrencyCodes.USD);
                                parallelPay.setEnv(ServiceEnvironment.SANDBOX);
                                //parallelPay.setIpnURL(ipnURL);
                                parallelPay.setLanguage("en_US");
                                parallelPay.setMemo("Monetize Your Actions");
                                
                                // set the receivers
                                Receiver primaryReceiver = new Receiver();
                                primaryReceiver.setAmount(300.0);
                                primaryReceiver.setEmail("sumi00_1279146496_biz@gmail.com");
                               // primaryReceiver.setPaymentType(PaymentType.GOODS);
                               // primaryReceiver.setPaymentType("DIGITALGOODS");
                                parallelPay.setReceiver(primaryReceiver);
                               // parallelPay.addToReceivers(primaryReceiver);

                                 
                                PayResponse payResponse = parallelPay.makeRequest();
                                LOG.log(Level.WARNING, "Payment success - payKey:" + payResponse.getPayKey());
                                if(payResponse.isPaymentINCOMPLETE()){
                                	System.out.println("Payment is completed :"+payResponse.getPayKey());
                                }

                        } catch (IOException e) {
                                resp.getWriter().println("Payment Failed w/ IOException");
                        } catch (MissingAPICredentialsException e) {
                                // No API Credential Object provided - log error
                                e.printStackTrace();
                                resp.getWriter().println("No APICredential object provided");
                        } catch (InvalidAPICredentialsException e) {
                                // invalid API Credentials provided - application error - log error
                                e.printStackTrace();
                                resp.getWriter().println("Invalid API Credentials " + e.getMissingCredentials());
                        } catch (MissingParameterException e) {
                                // missing parameter - log  error
                                e.printStackTrace();
                                resp.getWriter().println("Missing Parameter error: " + e.getParameterName());
                        } catch (RequestFailureException e) {
                                // HTTP Error - some connection issues ?
                                e.printStackTrace();
                                resp.getWriter().println("Request HTTP Error: " + e.getHTTP_RESPONSE_CODE());
                        } catch (InvalidResponseDataException e) {
                                // PayPal service error 
                                // log error
                                e.printStackTrace();
                                resp.getWriter().println("Invalid Response Data from PayPal: \"" + e.getResponseData() + "\"");
                        } catch (PayPalErrorException e) {
                                // Request failed due to a Service/Application error
                                e.printStackTrace();
                                if(e.getResponseEnvelope().getAck() == AckCode.Failure){
                                        // log the error
                                        resp.getWriter().println("Received Failure from PayPal (ack)");
                                        resp.getWriter().println("ErrorData provided:");
                                        resp.getWriter().println(e.getPayErrorList().toString());
                                        if(e.getPaymentExecStatus() != null){
                                                resp.getWriter().println("PaymentExecStatus: " + e.getPaymentExecStatus());
                                        }
                                } else if(e.getResponseEnvelope().getAck() == AckCode.FailureWithWarning){
                                        // there is a warning - log it!
                                        resp.getWriter().println("Received Failure with Warning from PayPal (ack)");
                                        resp.getWriter().println("ErrorData provided:");
                                        resp.getWriter().println(e.getPayErrorList().toString());
                                }
                        } catch (RequestAlreadyMadeException e) {
                                // shouldn't occur - log the error
                                e.printStackTrace();
                                resp.getWriter().println("Request to send a request that has already been sent!");
                        } catch (PaymentExecException e) {

                                resp.getWriter().println("Failed Payment Request w/ PaymentExecStatus: " + e.getPaymentExecStatus().toString());
                                resp.getWriter().println("ErrorData provided:");

                                resp.getWriter().println(e.getPayErrorList().toString());
                        }catch (PaymentInCompleteException e){
                                resp.getWriter().println("Incomplete Payment w/ PaymentExecStatus: " + e.getPaymentExecStatus().toString());
                                resp.getWriter().println("ErrorData provided:");

                                resp.getWriter().println(e.getPayErrorList().toString());                       
                        } catch (NumberFormatException e) {
                                // invalid number passed
                                e.printStackTrace();
                                resp.getWriter().println("Invalid number of receivers sent");

                        } catch (AuthorizationRequiredException e) {
                                // redirect the user to PayPal for Authorization
                                resp.sendRedirect(e.getAuthorizationUrl(ServiceEnvironment.SANDBOX));
                        }



             /*   } else if(id == null || id.length() <= 0) {

                        PicasawebService service = new PicasawebService("Picasa test");
                        PicasawebClient picasaClient = new PicasawebClient(service);
                        List<AlbumEntry> albums = picasaClient.getAlbums("ppx.devnet@gmail.com");
                        for(AlbumEntry album: albums){
                                resp.getWriter().println(album.getTitle().getPlainText());
                                List<PhotoEntry> photos = picasaClient.getPhotos(album);
                                req.setAttribute("photos", photos);
                        }

                        getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);


                } else {
                        getServletConfig().getServletContext().getRequestDispatcher("/order.jsp").forward(req, resp);
                }*/
            //    getServletConfig().getServletContext().getRequestDispatcher("/paymentcancel.jsp").forward(req, resp);
        } catch (Exception e) {
                e.printStackTrace();
        }
        
}
/*public void service(HttpServletRequest req, HttpServletResponse resp)
throws IOException {


        String action = req.getParameter("action");

        if (action != null && action.equals("payDetails")) {
                resp.setContentType("text/html");
                resp.getWriter().println("<html><head><title>Preapproval Details</title></head><body>");
                resp.getWriter().println("<a href=\"/adaptivesamplefnapi\">Home</a> <br/>");

                String payKey = req.getParameter("payKey");
                if(payKey != null){
                        PaymentDetailsResponse payDetailsResp = 
                                AdaptiveRequests.processPaymentDetails(resp, payKey, null, null, credentialObj);

                        resp.getWriter().println( payDetailsResp.getPaymentDetails().getStatus());

                }  else {
                        resp.getWriter().println("PayDetails Failed");
                }

                resp.getWriter().println("</body></html>");
        
        } else if (action != null && action.equals("preapprovalDetails")) {
                resp.setContentType("text/html");
                resp.getWriter().println("<html><head><title>Preapproval Details</title></head><body>");
                resp.getWriter().println("<a href=\"/adaptivesamplefnapi\">Home</a> <br/>");

                String preapprovalKey = req.getParameter("preapprovalKey");
                if(preapprovalKey != null){
                                PreapprovalDetailsResponse payDetailsResp = 
                                        AdaptiveRequests.processPreapprovalDetails(resp, preapprovalKey, true, credentialObj);

                                resp.getWriter().println( payDetailsResp.getPreapprovalDetails().getStatus());

                }  else {
                        resp.getWriter().println("PreapprovalDetails Failed");
                }

                resp.getWriter().println("</body></html>");
        
        } else if (action != null && action.equals("simplePay")) {
                AdaptiveRequests.processSimplePayRequest(req, resp, credentialObj);
        } else if (action != null && action.equals("implicitSimplePay")) {
                AdaptiveRequests.processImplicitSimplePayRequest(req, resp, credentialObj);
        } else if (action != null && action.equals("parallelPay")) {
                AdaptiveRequests.processParallelPayRequest(req, resp, credentialObj);
        } else if (action != null && action.equals("implicitParallelPay")) {
                AdaptiveRequests.processImplicitParallelPayRequest(req, resp, credentialObj);
        } else if (action != null && action.equals("chainedPay")) {
                AdaptiveRequests.processChainedPayRequest(req, resp, credentialObj);
        } else if (action != null && action.equals("implicitChainedPay")) {
                AdaptiveRequests.processImplicitChainedPayRequest(req, resp, credentialObj);
        } else if (action != null && action.equals("createSimplePreapproval")) {
                AdaptiveRequests.processCreateSimplePreapprovalRequest(req, resp, credentialObj);
        } else if (action != null && action.equals("createPreapprovalForPeriodicPayments")) {
                AdaptiveRequests.processCreatePreapprovalForPeriodicPaymentsRequest(req, resp, credentialObj);
        } else if (action != null && action.equals("preapprovedSimplePay")) {
                AdaptiveRequests.processPreapprovedSimplePay(req, resp, credentialObj);
        } else if (action != null && action.equals("preapprovedParallelPay")) {
                AdaptiveRequests.processPreapprovedParallelPay(req, resp, credentialObj);
        } else if (action != null && action.equals("preapprovedChainedPay")) {
                AdaptiveRequests.processPreapprovedChainedPay(req, resp, credentialObj);
        } else if (action != null && action.equals("refundCompletePayment")) {
                AdaptiveRequests.processRefundCompletePayment(req, resp, credentialObj);
        } else if (action != null && action.equals("refundTransaction")) {
                AdaptiveRequests.processRefundTransaction(req, resp, credentialObj);
        } else if (action != null && action.equals("refundPartialPayment")) {
                AdaptiveRequests.processRefundPartialPayment(req, resp, credentialObj);
        } else if (action != null && action.equals("convertCurrency")) {
                AdaptiveRequests.processConvertCurrency(req, resp, credentialObj);
        } else {
                try {
                        getServletConfig().getServletContext().getRequestDispatcher(
                                        "/index.jsp").forward(req, resp);

                } catch (ServletException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }

        
}
*/
}