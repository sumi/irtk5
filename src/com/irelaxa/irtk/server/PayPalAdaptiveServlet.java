package com.irelaxa.irtk.server;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.adaptive.api.requests.fnapi.ParallelPay;
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


@SuppressWarnings("serial")
public class PayPalAdaptiveServlet extends HttpServlet {
        private static final Logger log = Logger.getLogger(PayPalAdaptiveServlet.class.getName());


        private static APICredential credentialObj;

       /* @Override
        public void init(ServletConfig config) throws ServletException {
                // TODO Auto-generated method stub
                super.init(config);

                // Get the value of APIUsername
                String APIUsername = getServletConfig().getInitParameter("sumi00_1279146496_biz_api1.gmail.com"); 
                String APIPassword = getServletConfig().getInitParameter("1279146500"); 
                String APISignature = getServletConfig().getInitParameter("AcxH3fObvAA9M5VKgDn5zRUiVVdlAqPjAgUR1p4WZXI5eReUHLCGFR0h"); 
                String AppID = getServletConfig().getInitParameter("APP-80W284485P519543T"); 
                String AccountEmail = getServletConfig().getInitParameter("sumi00_1279146496_biz@gmail.com");

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
                log.info("Servlet initialized successfully");
        }
*/
       /* public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
                String action = req.getParameter("action");
                String returnParam = req.getParameter("return"); 
                String cancel = req.getParameter("cancel");
                try {
                        
                        log.info("Received Action: " + action );

                        if(cancel != null && cancel.equals("1")) {
                                // user canceled the payment
                        } 

                        if(returnParam != null && returnParam.equals("1")){
                                // user returned from PayPal AuthZ url
                                resp.setContentType("text/html");
                                
                                if(action != null && action.equals("pay")){
                                        resp.getWriter().println("<html><head><title>Payment status</title></head><body>");
                                        resp.getWriter().println("<a href=\"/adaptivesample\">Home</a> <br/>");
                                        String payKey = req.getParameter("payKey");
                                        PaymentDetailsResponse payDetailsResp = 
                                                AdaptiveRequests.processPaymentDetails(resp, payKey, null, null, credentialObj);
                                        resp.getWriter().println( payDetailsResp.getPaymentDetails().getStatus());
                                } else if(action != null && action.equals("preapproval")){
                                        resp.getWriter().println("<html><head><title>Preapproval status</title></head><body>");
                                        resp.getWriter().println("<a href=\"/adaptivesample\">Home</a> <br/>");
                                        String preapprovalKey = req.getParameter("preapprovalKey");
                                        PreapprovalDetailsResponse preapprovalDetailsResp =                                              
                                                AdaptiveRequests.processPreapprovalDetails(resp, preapprovalKey, true, credentialObj);
                                        resp.getWriter().println( preapprovalDetailsResp.getPreapprovalDetails().getStatus());
                                }
                                resp.getWriter().println("</body></html>");
                        } else { 
                                if (action != null && action.equals("pay")) {

                                        getServletConfig().getServletContext().getRequestDispatcher(
                                        "/pay.jsp").forward(req, resp);
                                } else if (action != null && action.equals("preapproval")) {

                                        getServletConfig().getServletContext().getRequestDispatcher(
                                        "/preapproval.jsp").forward(req, resp);
                                } else if (action != null && action.equals("refund")) {

                                        getServletConfig().getServletContext().getRequestDispatcher(
                                        "/refund.jsp").forward(req, resp);
                                } else if (action != null && action.equals("payDetails")) {

                                        getServletConfig().getServletContext().getRequestDispatcher(
                                        "/payDetails.jsp").forward(req, resp);
                                } else if (action != null && action.equals("preapprovalDetails")) {

                                        getServletConfig().getServletContext().getRequestDispatcher(
                                        "/preapprovalDetails.jsp").forward(req, resp);  
                                } else if (action != null && action.equals("cancelPreapproval")) {

                                        getServletConfig().getServletContext().getRequestDispatcher(
                                        "/cancelPreapproval.jsp").forward(req, resp);   
                                } else if (action != null && action.equals("currencyConversion")) {

                                        getServletConfig().getServletContext().getRequestDispatcher(
                                        "/convertCurrency.jsp").forward(req, resp);
                                
                                } else {
                                        getServletConfig().getServletContext().getRequestDispatcher(
                                        "/index.jsp").forward(req, resp);
                                }
                        }

                } catch (ServletException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

        }

        public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {


                String action = req.getParameter("action");

                if (action != null && action.equals("pay")) {
                                                
                        AdaptiveRequests.processPayRequest(req, resp, credentialObj);
                        
                } else if (action != null && action.equals("payDetails")) {
                        resp.setContentType("text/html");
                        resp.getWriter().println("<html><head><title>Preapproval Details</title></head><body>");
                        resp.getWriter().println("<a href=\"/adaptivesample\">Home</a> <br/>");

                        String payKey = req.getParameter("payKey");
                        if(payKey != null){
                                PaymentDetailsResponse payDetailsResp = 
                                        AdaptiveRequests.processPaymentDetails(resp, payKey, null, null, credentialObj);

                                resp.getWriter().println( payDetailsResp.getPaymentDetails().getStatus());

                        }  else {
                                resp.getWriter().println("PayDetails Failed");
                        }

                        resp.getWriter().println("</body></html>");
                } else if (action != null && action.equals("preapproval")) {
                        resp.setContentType("text/html");
                        resp.getWriter().println("<html><head><title>Preapproval Response & Preapproval Details</title></head><body>");
                        resp.getWriter().println("<a href=\"/adaptivesample\">Home</a> <br/>");

                        PreapprovalResponse preapprovalResp = AdaptiveRequests.processPreapprovalRequest(req, resp, credentialObj);
                        resp.getWriter().println( preapprovalResp.toString());

                        // session.setAttribute("payResponseRef", payResp);
                        if(preapprovalResp != null) {
                                if(preapprovalResp.getErrorList() != null && preapprovalResp.getErrorList().size() > 0) {
                                        // error occured

                                } else {

                                        PreapprovalDetailsResponse payDetailsResp = 
                                                AdaptiveRequests.processPreapprovalDetails(resp, preapprovalResp.getPreapprovalKey(), true, credentialObj);

                                        resp.getWriter().println( payDetailsResp.toString());


                                        // generate authurization url
                                        if (preapprovalResp.getResponseEnvelope().getAck() == AckCode.Success) {

                                                resp.getWriter().println("Preapproval Success.");
                                        
                                                resp.getWriter().println("<a href=\""
                                                                + AdaptiveRequests.generatePreApprovalAuthorizeUrl(preapprovalResp.getPreapprovalKey(), ServiceEnvironment.SANDBOX)
                                                                + "\">Click here to authorize</a>");
                                        }


                                }
                        }  else {
                                resp.getWriter().println("Preapproval Failed");
                        }

                        resp.getWriter().println("</body></html>");
                } else if (action != null && action.equals("preapprovalDetails")) {
                        resp.setContentType("text/html");
                        resp.getWriter().println("<html><head><title>Preapproval Details</title></head><body>");
                        resp.getWriter().println("<a href=\"/adaptivesample\">Home</a> <br/>");

                        String preapprovalKey = req.getParameter("preapprovalKey");
                        if(preapprovalKey != null){
                                        PreapprovalDetailsResponse payDetailsResp = 
                                                AdaptiveRequests.processPreapprovalDetails(resp, preapprovalKey, true, credentialObj);

                                        resp.getWriter().println( payDetailsResp.getPreapprovalDetails().getStatus());

                        }  else {
                                resp.getWriter().println("PreapprovalDetails Failed");
                        }

                        resp.getWriter().println("</body></html>");
                } else if (action != null && action.equals("cancelPreapproval")) {
                        resp.setContentType("text/html");
                        resp.getWriter().println("<html><head><title>Cancel Preapproval</title></head><body>");
                        resp.getWriter().println("<a href=\"/adaptivesample\">Home</a> <br/>");

                        String preapprovalKey = req.getParameter("preapprovalKey");
                        if(preapprovalKey != null){
                                CancelPreapprovalResponse cancelPreapprovalResp = 
                                                AdaptiveRequests.processCancelPreapproval(resp, preapprovalKey, credentialObj);

                                        resp.getWriter().println( cancelPreapprovalResp.toString());

                        }  else {
                                resp.getWriter().println("PreapprovalDetails Failed");
                        }

                        resp.getWriter().println("</body></html>");
                } else if (action != null && action.equals("refund")) {
                        resp.setContentType("text/html");
                        resp.getWriter().println("<html><head><title>Refund</title></head><body>");
                        resp.getWriter().println("<a href=\"/adaptivesample\">Home</a> <br/>");

                        RefundResponse refundResp = 
                                        AdaptiveRequests.processRefund(req, resp, credentialObj);

                        resp.getWriter().println( refundResp.toString());


                        resp.getWriter().println("</body></html>");
                } else if (action != null && action.equals("currencyConversion")) {
                        resp.setContentType("text/html");
                        resp.getWriter().println("<html><head><title>Convert Currency</title></head><body>");
                        resp.getWriter().println("<a href=\"/adaptivesample\">Home</a> <br/>");

                        ConvertCurrencyResponse currConvertResp = 
                                        AdaptiveRequests.processCurrencyConversion(req, resp, credentialObj);

                        resp.getWriter().println( currConvertResp.toString());


                        resp.getWriter().println("</body></html>");
                
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

                
        }*/
        //i need to create a client
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
                log.info("Servlet initialized successfully");
        }
        
        
}

