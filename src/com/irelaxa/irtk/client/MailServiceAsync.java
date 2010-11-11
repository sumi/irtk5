package com.irelaxa.irtk.client;

import java.util.Stack;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MailServiceAsync {
	 public void sendMail(JSONObject mailObject, AsyncCallback<Void> async);
	 
}