package com.example.test_14_socket_phone_client.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.example.test_14_socket_phone_client.ClientApplication;
import com.example.test_14_socket_phone_client.Machine;

import android.os.AsyncTask;
import android.util.Log;

public class WriteMessageAsyncTask extends AsyncTask<String, String, String> {

	public static final String DIVIDER = ":";

	private OnResultListener mOnResultListener;
	private static Socket mSocket = null;
	static OutputStream out = null;
	static DataOutputStream dout = null;
	static InputStream in = null;
	static DataInputStream din = null;

	public WriteMessageAsyncTask(Socket socket) {
		mSocket = socket;
	}
	
	@Override
	protected String doInBackground(String... params) {

		try {
			if (out == null) {
				out = mSocket.getOutputStream();
			}
			if (dout == null) {
				dout = new DataOutputStream(out);
			}

			dout.writeUTF(/*Machine.getInstance(ClientApplication.getAppContext()).getAndroidId()
					+ DIVIDER + "1" + DIVIDER + */params[0]);
			dout.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		
		return "success";
	}

	@Override
	protected void onProgressUpdate(String... values) {
		//mOnResultListener.onResult(values[0]);
	}

	@Override
	protected void onPostExecute(String result) {

	}

	public interface OnResultListener {
		public void onResult(String result);
	}

	public void setOnResultListener(OnResultListener listener) {
		mOnResultListener = listener;
	}
}