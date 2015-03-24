package com.example.test_14_socket_phone_client;
import com.google.gson.annotations.SerializedName;


/**
 * socket 传输字符串内容协议
 * androidId||message
 * @author chenhewen
 *
 */
public class ClientProtocolModle {
	
	private String mFromAndroidId;
	
	private String mToAndroidId;
	
	private String mMessage;
	
	public String getmFromAndroidId() {
		return mFromAndroidId;
	}

	public void setmFromAndroidId(String mFromAndroidId) {
		this.mFromAndroidId = mFromAndroidId;
	}

	public String getmToAndroidId() {
		return mToAndroidId;
	}

	public void setmToAndroidId(String mToAndroidId) {
		this.mToAndroidId = mToAndroidId;
	}

	public String getmMessage() {
		return mMessage;
	}

	public void setmMessage(String mMessage) {
		this.mMessage = mMessage;
	}

	
}
