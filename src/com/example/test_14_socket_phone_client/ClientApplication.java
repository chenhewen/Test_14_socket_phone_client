package com.example.test_14_socket_phone_client;

import android.app.Application;
import android.content.Context;

public class ClientApplication extends Application {
	
	private static Context sAppContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Machine.getInstance(this);
	}
	
	public ClientApplication() {
		sAppContext = this;
	}
	
	public static Context getAppContext() {
		return sAppContext;
	}
}
