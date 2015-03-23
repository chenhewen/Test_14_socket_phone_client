package com.example.test_14_socket_phone_client.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.os.AsyncTask;

public class ReadMessageAsyncTask extends AsyncTask<String, String, String>{
	
	public static final String DIVIDER = ":";

	private OnMessageListener mOnMessageListener;
	private static Socket mSocket = null;
	static OutputStream out = null;
	static DataOutputStream dout = null;
	static InputStream in = null;
	static DataInputStream din = null;
	
	public ReadMessageAsyncTask(Socket socket) {
		mSocket = socket;
	}
	
	@Override
	protected String doInBackground(String... params) {
		
		String stream = "";
		
		while (true) {

			try {
				if (in == null) {
					in = mSocket.getInputStream();
				}
				if (din == null) {
					din = new DataInputStream(in);
				}
				stream = din.readUTF();
				
				publishProgress(stream);
			} catch (Exception e) {
				e.printStackTrace();
			} finally { 
				
			}
		}
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		if (mOnMessageListener != null) {
			mOnMessageListener.OnMessage(values[0]);
		}
	}

	@Override
	protected void onPostExecute(String result) {

	}

	public interface OnMessageListener {
		public void OnMessage(String result);
	}

	public void setOnMessageListener(OnMessageListener listener) {
		mOnMessageListener = listener;
	}

}
