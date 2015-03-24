package com.example.test_14_socket_phone_client.message;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import com.example.test_14_socket_phone_client.message.MessageManager.OnSocketListener;
import com.example.test_14_socket_phone_client.message.ReadMessageAsyncTask.OnMessageListener;


public class MessageManager {
	
	private Socket mSocket;

	private WriteMessageAsyncTask mWriteMessageAsyncTask;
	
	private ReadMessageAsyncTask mReadMessageAsyncTask;

	private OnMessageListener mOnMessageListener;

	private OnSocketListener mOnSocketListener;
	
	private static MessageManager sInstance;
	
	public static MessageManager getInstance() {
		if (sInstance == null) {
			sInstance = new MessageManager();
		}
		return sInstance;
	}
	
	private MessageManager() {
		
	}
	
	public void connect() {
		SocketConnectAsyncTask socketConnectAsyncTask = new SocketConnectAsyncTask();
		socketConnectAsyncTask.execute();
	}

	@SuppressLint("NewApi")
	public void startListenServer() {
		mReadMessageAsyncTask = new ReadMessageAsyncTask(mSocket);
		mReadMessageAsyncTask.setOnMessageListener(mOnMessageListener);
		mReadMessageAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");  
	}
	
	@SuppressLint("NewApi")
	public void sendMessage(String message) {
		mWriteMessageAsyncTask = new WriteMessageAsyncTask(mSocket);
		mWriteMessageAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, message);
	}
	
	/**
	 * 处理socket连接的异步线程
	 * @author chenhewen
	 *
	 */
	public class SocketConnectAsyncTask extends AsyncTask<String, String, Socket> {

		@Override
		protected Socket doInBackground(String... params) {
			Socket socket = null;
			try {
				socket = new Socket("192.168.214.63", 5432);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return socket;
		}

		@Override
		protected void onPostExecute(Socket result) {
			mSocket = result;
			if (mOnSocketListener != null) {
				mOnSocketListener.OnSocketConnect(result);
			}
		}
		
		
	}
	
	public interface OnSocketListener {
		public void OnSocketConnect(Socket socket);
	}
	
	public void setOnSocketListener(OnSocketListener l) {
		mOnSocketListener = l;
	}
	
	public void setOnMessageListener(OnMessageListener l) {
		mOnMessageListener = l;
	}

	public void shutdown() {
		if (mSocket != null) {
			try {
				mSocket.shutdownInput();
				mSocket.shutdownOutput();
				if(!mSocket.isClosed())  {
					mSocket.close();
					mSocket = null;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
