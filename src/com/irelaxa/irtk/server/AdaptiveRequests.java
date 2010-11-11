package com.irelaxa.irtk.server;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.adaptive.api.requests.CancelPreapprovalRequest;
import com.paypal.adaptive.api.requests.ConvertCurrencyRequest;
import com.paypal.adaptive.api.requests.PayRequest;
import com.paypal.adaptive.api.requests.PaymentDetailsRequest;
import com.paypal.adaptive.api.requests.PreapprovalDetailsRequest;
import com.paypal.adaptive.api.requests.PreapprovalRequest;
import com.paypal.adaptive.api.requests.RefundRequest;
import com.paypal.adaptive.api.responses.CancelPreapprovalResponse;
import com.paypal.adaptive.api.responses.ConvertCurrencyResponse;
import com.paypal.adaptive.api.responses.PayResponse;
import com.paypal.adaptive.api.responses.PaymentDetailsResponse;
import com.paypal.adaptive.api.responses.PreapprovalDetailsResponse;
import com.paypal.adaptive.api.responses.PreapprovalResponse;
import com.paypal.adaptive.api.responses.RefundResponse;
import com.paypal.adaptive.core.APICredential;
import com.paypal.adaptive.core.AckCode;
import com.paypal.adaptive.core.ActionType;
import com.paypal.adaptive.core.ClientDetails;
import com.paypal.adaptive.core.CurrencyCodes;
import com.paypal.adaptive.core.CurrencyType;
import com.paypal.adaptive.core.EndPointUrl;
import com.paypal.adaptive.core.PaymentDetails;
import com.paypal.adaptive.core.PreapprovalDetails;
import com.paypal.adaptive.core.Receiver;
import com.paypal.adaptive.core.ServiceEnvironment;
import com.paypal.adaptive.exceptions.AuthorizationRequiredException;
import com.paypal.adaptive.exceptions.InvalidAPICredentialsException;
import com.paypal.adaptive.exceptions.InvalidResponseDataException;
import com.paypal.adaptive.exceptions.MissingAPICredentialsException;
import com.paypal.adaptive.exceptions.MissingParameterException;
import com.paypal.adaptive.exceptions.PayPalErrorException;
import com.paypal.adaptive.exceptions.PaymentExecException;
import com.paypal.adaptive.exceptions.PaymentInCompleteException;
import com.paypal.adaptive.exceptions.RequestFailureException;

/**
 * @author palavilli
 * 
 */
public class AdaptiveRequests {

        public static void processPayRequest(HttpServletRequest req,
                        HttpServletResponse resp, APICredential credentialObj) throws IOException {

                resp.setContentType("text/html");
                resp.getWriter().println("<html><head><title>Pay Response & Payment Details</title></head><body>");
                resp.getWriter().println("<a href=\"/adaptivesample\">Home</a> <br/>");
                
                try {

                        StringBuilder url = new StringBuilder();
                        url.append(req.getRequestURL());
                        String returnURL = url.toString() + "?return=1&action=pay&payKey=${payKey}";
                        String cancelURL = url.toString() + "?action=pay&cancel=1";
                        String ipnURL = url.toString() + "?action=ipn";
                        String[] receiverEmailItems = req
                        .getParameterValues("receiveremail");
                        String[] amountItems = req.getParameterValues("amount");
                        String[] primaryItems = req.getParameterValues("primary");
                        
                        PaymentDetails paymentDetails = new PaymentDetails(ActionType.PAY);
                        
                        PayRequest payRequest = new PayRequest("en_US",
                                        ServiceEnvironment.SANDBOX);
                        
                        for (int i = 0; i < receiverEmailItems.length; i++) {
                                String recreceiverEmail = receiverEmailItems[i];
                                if (recreceiverEmail != null
                                                && recreceiverEmail.length() != 0) {
                                        Receiver rec1 = new Receiver();
                                        rec1.setAmount((new Double(amountItems[i])
                                        .doubleValue()));
                                        rec1.setEmail(receiverEmailItems[i]);
                                        rec1.setPrimary(Boolean.parseBoolean(primaryItems[i]));
                                        paymentDetails.addToReceiverList(rec1);
                                }
                        }

                        ClientDetails cl = new ClientDetails();
                        cl.setIpAddress(req.getRemoteAddr());
                        cl.setApplicationId("Praveen's GAE Sample");
                        paymentDetails.setCancelUrl(cancelURL);
                        paymentDetails.setReturnUrl(returnURL);
                        paymentDetails.setIpnNotificationUrl(ipnURL);
                        paymentDetails.setSenderEmail((String) req.getParameter("email"));
                        paymentDetails.setCurrencyCode((String) req
                                        .getParameter("currencyCode"));
                        payRequest.setClientDetails(cl);

                        String pkey = req.getParameter("preapprovalKey");
                        if (pkey != null & pkey.length() >= 20) {
                                paymentDetails.setPreapprovalKey(pkey);
                        }
                        
                        payRequest.setPaymentDetails(paymentDetails);
                        
                        resp.getWriter().println( payRequest.toString());
                        
                        PayResponse payResp = payRequest.execute(credentialObj);
                        resp.getWriter().println( payResp.toString());

                        // session.setAttribute("payResponseRef", payResp);
                        if(payResp != null) {
                                if(payResp.getPayErrorList() != null && payResp.getPayErrorList().size() > 0) {
                                        // error occured

                                } else {

                                        PaymentDetailsResponse payDetailsResp = 
                                                AdaptiveRequests.processPaymentDetails(resp, payResp.getPayKey(), null, null, credentialObj);

                                        resp.getWriter().println( payDetailsResp.toString());


                                        // generate authurization url
                                        if (payResp.isPaymentCOMPLETED()) {
                                                resp.getWriter().println("Payment COMPLETED.");
                                        } else {
                                                resp.getWriter().println("<a href=\""
                                                                + AdaptiveRequests.generateAuthorizeUrl(payResp.getPayKey(), ServiceEnvironment.SANDBOX)
                                                                + "\">Click here to authorize</a>");
                                        }

                                }
                        } else {
                                resp.getWriter().println("Payment Failed");
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
                } catch (PaymentExecException e) {

                        resp.getWriter().println("Failed Payment Request w/ PaymentExecStatus: " + e.getPaymentExecStatus().toString());
                        resp.getWriter().println("ErrorData provided:");

                        resp.getWriter().println(e.getPayErrorList().toString());
                }catch (PaymentInCompleteException e){
                        resp.getWriter().println("Incomplete Payment w/ PaymentExecStatus: " + e.getPaymentExecStatus().toString());
                        resp.getWriter().println("ErrorData provided:");

                        resp.getWriter().println(e.getPayErrorList().toString());                       
                } catch (AuthorizationRequiredException e) {
                        // redirect the user to PayPal for Authorization
                        resp.sendRedirect(e.getAuthorizationUrl(ServiceEnvironment.SANDBOX));
                }

                resp.getWriter().println("</body></html>");
        }
        
        public static PaymentDetailsResponse processPaymentDetails(HttpServletResponse resp, 
                        String payKey, String transactionId, String trackingId,
                        APICredential credentialObj) throws IOException {

                try {


                        PaymentDetailsRequest paymentDetailsRequest = new PaymentDetailsRequest("en_US",
                                        ServiceEnvironment.SANDBOX);

                        if(payKey != null)
                                paymentDetailsRequest.setPayKey(payKey);
                        if(transactionId != null)
                                paymentDetailsRequest.setTransactionId(transactionId);
                        if(trackingId != null)
                                paymentDetailsRequest.setTrackingId(trackingId);
                        
                        resp.getWriter().println( paymentDetailsRequest.toString());
                        
                        PaymentDetailsResponse response = paymentDetailsRequest.execute(credentialObj);
                        
                        return response;
                        
                } catch (IOException e) {
                        return null;
                } catch (Exception e) {
                        return null;
                }

        }
        
        public static String generateAuthorizeUrl(String paykey, ServiceEnvironment env) 
        throws UnsupportedEncodingException{
                StringBuilder outStr = new StringBuilder();
                outStr.append(EndPointUrl.getAuthorizationUrl(env));
                outStr.append("?cmd=_ap-payment&paykey=");
                outStr.append(java.net.URLEncoder.encode(paykey, "UTF-8"));
                return outStr.toString();
        }
        
        public static PreapprovalResponse processPreapprovalRequest(HttpServletRequest req,
                        HttpServletResponse resp, APICredential credentialObj) throws IOException {

                try {

                        StringBuilder url = new StringBuilder();
                        url.append(req.getRequestURL());
                        String returnURL = url.toString()
                        + "?return=1&action=preapproval&preapprovalKey=${preapprovalKey}";
                        String cancelURL = url.toString() + "?action=preapproval&cancel=1";
                        String ipnURL = url.toString() + "?action=ipn";
                        PreapprovalDetails preapprovalDetails = new PreapprovalDetails();
                        
                        PreapprovalRequest preapprovalRequest = new PreapprovalRequest("en_US",
                                        ServiceEnvironment.SANDBOX);
                        
                        ClientDetails cl = new ClientDetails();
                        cl.setIpAddress(req.getRemoteAddr());
                        cl.setApplicationId("PraveenPreApprovalSample");
                        preapprovalRequest.setClientDetails(cl);
                        
                        preapprovalDetails.setCancelUrl(cancelURL);
                        preapprovalDetails.setReturnUrl(returnURL);
                        preapprovalDetails.setIpnNotificationUrl(ipnURL);
                        preapprovalDetails.setSenderEmail((String) req.getParameter("senderEmail"));
                        preapprovalDetails.setCurrencyCode(CurrencyCodes.valueOf(req.getParameter("currencyCode")));    
                        preapprovalDetails.setEndingDate((String)req.getParameter("endingDate"));
                        
                        if(req.getParameter("maxAmountPerPayment") != null && req.getParameter("maxAmountPerPayment").length() > 0)
                                preapprovalDetails.setMaxAmountPerPayment(Double.parseDouble(req.getParameter("maxAmountPerPayment")));
                        preapprovalDetails.setStartingDate((String)req.getParameter("startingDate"));
                        if(req.getParameter("maxNumberOfPayments") != null && req.getParameter("maxNumberOfPayments").length() > 0)
                                preapprovalDetails.setMaxNumberOfPayments(Integer.parseInt(req.getParameter("maxNumberOfPayments")));
                        if(req.getParameter("maxTotalAmountOfAllPayments") != null && req.getParameter("maxTotalAmountOfAllPayments").length() > 0)
                                preapprovalDetails.setMaxTotalAmountOfAllPayments(Double.parseDouble(req.getParameter("maxTotalAmountOfAllPayments"))); 
                        if(req.getParameter("maxNumberOfPaymentsPerPeriod") != null && req.getParameter("maxNumberOfPaymentsPerPeriod").length() > 0)
                                preapprovalDetails.setMaxNumberOfPaymentsPerPeriod(Integer.parseInt(req.getParameter("maxNumberOfPaymentsPerPeriod")));
                        
                        // set preapproval details
                        preapprovalRequest.setPreapprovalDetails(preapprovalDetails);
                        
                        resp.getWriter().println( preapprovalRequest.toString());
                        
                        PreapprovalResponse payResp = preapprovalRequest.execute(credentialObj);
                        return payResp;

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
                } catch (PaymentExecException e) {

                        resp.getWriter().println("Failed Payment Request w/ PaymentExecStatus: " + e.getPaymentExecStatus().toString());
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
                return null;

        }
        
        public static PreapprovalDetailsResponse processPreapprovalDetails(HttpServletResponse resp, 
                        String preapprovalKey, boolean getBillingAddress,
                        APICredential credentialObj) throws IOException {

                try {


                        PreapprovalDetailsRequest preapprovalDetailsRequest = new PreapprovalDetailsRequest("en_US",
                                        ServiceEnvironment.SANDBOX);

                        if(preapprovalKey != null)
                                preapprovalDetailsRequest.setPreapprovalKey(preapprovalKey);
                        
                        preapprovalDetailsRequest.setGetBillingAddress(getBillingAddress);
                        
                        
                        resp.getWriter().println( preapprovalDetailsRequest.toString());
                        
                        PreapprovalDetailsResponse response = preapprovalDetailsRequest.execute(credentialObj);
                        
                        return response;
                        
                } catch (IOException e) {
                        return null;
                } catch (Exception e) {
                        return null;
                }

        }
        
        public static String generatePreApprovalAuthorizeUrl(String preapprovalKey, ServiceEnvironment env) 
        throws UnsupportedEncodingException{
                StringBuilder outStr = new StringBuilder();
                outStr.append(EndPointUrl.getAuthorizationUrl(env));
                outStr.append("?cmd=_ap-preapproval&preapprovalkey=");
                outStr.append(java.net.URLEncoder.encode(preapprovalKey, "UTF-8"));
                return outStr.toString();
        }
        
        public static CancelPreapprovalResponse processCancelPreapproval(HttpServletResponse resp, 
                        String preapprovalKey, APICredential credentialObj) throws IOException {

                try {


                        CancelPreapprovalRequest cancelPreapprovalRequest = new CancelPreapprovalRequest("en_US",
                                        ServiceEnvironment.SANDBOX);

                        if(preapprovalKey != null)
                                cancelPreapprovalRequest.setPreapprovalKey(preapprovalKey);
                        
                        
                        
                        resp.getWriter().println( cancelPreapprovalRequest.toString());
                        
                        CancelPreapprovalResponse response = cancelPreapprovalRequest.execute(credentialObj);
                                                
                        return response;
                        
                } catch (IOException e) {
                        return null;
                } catch (Exception e) {
                        return null;
                }
        }
        public static RefundResponse processRefund(HttpServletRequest req,
                        HttpServletResponse resp, APICredential credentialObj) throws IOException {

                try {

                        RefundRequest refundReq = new RefundRequest("en_US", ServiceEnvironment.SANDBOX);

                        ClientDetails cl = new ClientDetails();
                        cl.setIpAddress(req.getRemoteAddr());
                        cl.setApplicationId("Praveen's GAE Sample");
                        refundReq.setPayKey(req.getParameter("payKey"));
                        refundReq.setCurrencyCode(CurrencyCodes.valueOf(req.getParameter("currencyCode")));
                        refundReq.setTrackingId(req.getParameter("trackingId"));
                        refundReq.setTransactionId(req.getParameter("transactionId"));
                        String[] receiverEmailItems = req
                        .getParameterValues("receiveremail");
                        String[] amountItems = req.getParameterValues("amount");
                        for (int i = 0; i < receiverEmailItems.length; i++) {
                                String recreceiverEmail = receiverEmailItems[i];
                                if (recreceiverEmail != null
                                                && recreceiverEmail.length() != 0) {
                                        Receiver rec1 = new Receiver();
                                        rec1.setAmount((new Double(amountItems[i])
                                        .doubleValue()));
                                        rec1.setEmail(receiverEmailItems[i]);
                                        refundReq.addToReceiverList(rec1);
                                }
                        }
                        
                        resp.getWriter().println( refundReq.toString());
                        
                        RefundResponse response = refundReq.execute(credentialObj);
                        
                        return response;
                        
                } catch (IOException e) {
                        resp.getWriter().println("Refund Failed w/ IOException");
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
                } 
                return null;
        }
        
        public static ConvertCurrencyResponse processCurrencyConversion(HttpServletRequest req,
                        HttpServletResponse resp, APICredential credentialObj) throws IOException {

                try {

                        ConvertCurrencyRequest currReq = new ConvertCurrencyRequest("en_US", ServiceEnvironment.SANDBOX);
                        String[] baseamountItems = req.getParameterValues("baseamount");
                        String[] fromcodeItems = req.getParameterValues("fromcode");
                        for (int i = 0; i < baseamountItems.length; i++) {
                                String baseamount = baseamountItems[i];
                                if (baseamount != null && baseamount.length() != 0) {
                                        CurrencyType currType = new CurrencyType(CurrencyCodes.valueOf(fromcodeItems[i]), 
                                                        Double.parseDouble(baseamount));
                                        currReq.addToBaseAmountList(currType);
                                }
                        }
                        String[] tocodeItems = req.getParameterValues("tocode");
                        for (int i = 0; i < tocodeItems.length; i++) {
                                String tocode = tocodeItems[i];
                                if (tocode != null && tocode.length() != 0) {
                                        currReq.addToConvertToCurrencyList(CurrencyCodes.valueOf(tocode));
                                }
                        }
                        
                        resp.getWriter().println( currReq.toString());
                        
                        ConvertCurrencyResponse response = currReq.execute(credentialObj);
                        
                        return response;
                        
                } catch (IOException e) {
                        resp.getWriter().println("Convert Currency Failed w/ IOException");
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
                }
                return null;
        }
        
}

