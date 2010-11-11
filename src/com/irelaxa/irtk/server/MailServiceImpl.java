package com.irelaxa.irtk.server;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.irelaxa.irtk.client.MailService;

public class MailServiceImpl extends RemoteServiceServlet implements

MailService {
	public void sendMail(JSONObject mailObject){
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
}