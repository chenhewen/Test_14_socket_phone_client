package com.example.test_14_socket_phone_client;

import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test_14_socket_phone_client.message.MessageManager;
import com.example.test_14_socket_phone_client.message.MessageManager.OnSocketListener;
import com.example.test_14_socket_phone_client.message.ReadMessageAsyncTask.OnMessageListener;

public class MainActivity extends Activity implements OnClickListener, OnMessageListener, OnSocketListener {

	public TextView mStreamText;
	EditText mInputText;
	Button mSubmitButton;
	private ListView mOnLineListView;
	
	private MessageManager mMessageManager;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findView();
		init();
	}
	
	private void findView() {
		mStreamText = (TextView) findViewById(R.id.text);
		mInputText = (EditText) findViewById(R.id.input_text);
		mSubmitButton = (Button) findViewById(R.id.submint_button);
		mOnLineListView = (ListView) findViewById(R.id.online_listview);
		
		mSubmitButton.setOnClickListener(this);
	}
	
	private void init() {
		mMessageManager = MessageManager.getInstance();
		mMessageManager.setOnSocketListener(this);
		mMessageManager.setOnMessageListener(this);
		mMessageManager.connect();
	}

	@Override
	public void onClick(View v) {
		mMessageManager.sendMessage(mInputText.getText().toString());
	}

	@Override
	public void OnMessage(String result) {
		mStreamText.setText(result + "\n");
	}

	@Override
	public void OnSocketConnect(Socket socket) {
		mMessageManager.startListenServer();
	}
	
	
}
