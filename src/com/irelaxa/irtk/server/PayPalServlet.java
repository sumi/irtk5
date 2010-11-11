package com.irelaxa.irtk.server;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.paypal.adaptive.api.requests.fnapi.ParallelPay;
import com.paypal.adaptive.api.responses.PayResponse;
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
public class PayPalServlet extends HttpServlet {
        private static final Logger log = Logger.getLogger(PayPalServlet.class.getName());


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
                log.info("Servlet initialized successfully");
        }

        public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
                try {
                        String id = req.getParameter("id");
                        String title = req.getParameter("title");
                        String order = req.getParameter("order");
                        String returnParam = req.getParameter("return"); 
                        String cancel = req.getParameter("cancel");

                        if(cancel != null && cancel.equals("1")) {
                                // user canceled the payment
                                getServletConfig().getServletContext().getRequestDispatcher("/paymentcancel.jsp").forward(req, resp);

                        } else if(returnParam != null && returnParam.equals("1")){
                                getServletConfig().getServletContext().getRequestDispatcher("/paymentsuccess.jsp").forward(req, resp);

                        } else if(order != null && order.length() > 0){
                                // process order

                                try {


                                        StringBuilder url = new StringBuilder();
                                        url.append(req.getRequestURL());
                                        String returnURL = url.toString() + "?return=1&payKey=${payKey}&id="+ id + "&title=" + title;
                                        String cancelURL = url.toString() + "?cancel=1&id="+ id + "&title=" + title;
                                        //String ipnURL = url.toString() + "?action=ipn";

                                        ParallelPay parallelPay = new ParallelPay(2);
                                        parallelPay.setCancelUrl(cancelURL);
                                        parallelPay.setReturnUrl(returnURL);
                                        parallelPay.setCredentialObj(credentialObj);
                                        parallelPay.setUserIp(req.getRemoteAddr());
                                        parallelPay.setApplicationName("Sample app on GAE");
                                        parallelPay.setCurrencyCode(CurrencyCodes.USD);
                                        parallelPay.setEnv(ServiceEnvironment.SANDBOX);
                                        //parallelPay.setIpnURL(ipnURL);
                                        parallelPay.setLanguage("en_US");
                                        parallelPay.setMemo(title);
                                        
                                        // set the receivers
                                        Receiver primaryReceiver = new Receiver();
                                        primaryReceiver.setAmount(3.0);
                                        primaryReceiver.setEmail("pp+sel_1261964325_biz@yahoo.com");
                                        primaryReceiver.setPaymentType(PaymentType.GOODS);
                                        parallelPay.addToReceivers(primaryReceiver);

                                        // set the second receivers
                                        Receiver rec1 = new Receiver();
                                        rec1.setAmount(5.0);
                                        rec1.setEmail("ppalav_1260515409_biz@yahoo.com");
                                        rec1.setPaymentType(PaymentType.GOODS);
                                        parallelPay.addToReceivers(rec1);
                                        
                                        PayResponse payResponse = parallelPay.makeRequest();
                                        log.info("Payment success - payKey:" + payResponse.getPayKey());

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
                                } catch(ReceiversCountMismatchException e){
                                        // missing receiver - log  error
                                        e.printStackTrace();
                                        resp.getWriter().println("Receiver count did not match - expected: " 
                                                        + e.getExpectedNumberOfReceivers() 
                                                        + " - actual:" + e.getActualNumberOfReceivers());                       
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

                                } catch (NotEnoughReceivers e) {
                                        // not enough receivers - min requirements for Parallel pay not met
                                        e.printStackTrace();
                                        resp.getWriter().println("Min number of receivers not met - Min Required:"
                                                        + e.getMinimumRequired() + " - actual set:" + e.getActualNumber());
                                } catch (AuthorizationRequiredException e) {
                                        // redirect the user to PayPal for Authorization
                                        resp.sendRedirect(e.getAuthorizationUrl(ServiceEnvironment.SANDBOX));
                                }



                        } /*else if(id == null || id.length() <= 0) {

                                PicasawebService service = new PicasawebService("Picasa test");
                                PicasawebClient picasaClient = new PicasawebClient(service);
                                List<AlbumEntry> albums = picasaClient.getAlbums("ppx.devnet@gmail.com");
                                for(AlbumEntry album: albums){
                                        resp.getWriter().println(album.getTitle().getPlainText());
                                        List<PhotoEntry> photos = picasaClient.getPhotos(album);
                                        req.setAttribute("photos", photos);
                                }

                                getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);


                        } */else {
                                getServletConfig().getServletContext().getRequestDispatcher("/order.jsp").forward(req, resp);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}

