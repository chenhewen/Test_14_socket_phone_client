package com.example.test_14_socket_phone_client;

import android.content.Context;
import android.telephony.TelephonyManager;

public class Machine {

	public static Machine sInstance;
	private Context mContext;
	private static TelephonyManager mTelephoneManager;
	
	public static Machine getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new Machine(context);
		}
		return sInstance;
	}

	private Machine(Context context) {
		mContext = context;
		mTelephoneManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	}
	
	public String getAndroidId() {
		return mTelephoneManager.getDeviceId();
	}
}
