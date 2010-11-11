package com.irelaxa.irtk.client;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("mailService")
public interface MailService extends RemoteService {
	public void sendMail(JSONObject mailObject);
}