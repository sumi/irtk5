package com.irelaxa.irtk.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;

public class LoginWidget{
	 private final LoginServiceAsync loginService = GWT.create(LoginService.class);
	 public LoginInfo loginInfo = null;
	 private Anchor signOutLink = new Anchor("Sign Out");
	public void LoginWidget(){
		
	}
	
	 public void loginService() {
			loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			      public void onFailure(Throwable error) {
			      }
			      public void onSuccess(LoginInfo result) {
			        loginInfo = result;
			        if(loginInfo.isLoggedIn()) {
			        	signOutLink.setHref(loginInfo.getLogoutUrl());
			        	
			        } else {
			        	signOutLink.setHref(loginInfo.getLoginUrl());
			        	signOutLink.setText("Sign In");
			        	 
			        }
			      }
			    });
		}
}