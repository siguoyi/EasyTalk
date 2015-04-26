package com.easytalk.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cl.easytalk.R;
import com.easytalk.chat.ChatMessage.Type;

public class Chat extends Activity {

	TextView tv_name;
	EditText et_input;
	Button bt_send;	
	ClientThread clientThread;
	
	
	private ListView listview;
	private List<ChatMessage> mData;
	private ChatMessageAdapter mAdapter;
	
	
	private static final String tag = "Chat";
	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 0x123){
				ChatMessage receiveMsg =  (ChatMessage) msg.obj;
				mData.add(receiveMsg);
				mAdapter.notifyDataSetChanged();
				listview.setSelection(mData.size() - 1);
			} 
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat);
	
		initView();
		
		initData();
		
		clientThread = new ClientThread(handler);
		new Thread(clientThread).start();
		
		bt_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String inputMsg = et_input.getText().toString();
				Log.v(tag, "inputMsg: " + inputMsg);
				if(TextUtils.isEmpty(inputMsg)){
					Toast.makeText(Chat.this, "发送消息不能为空！", Toast.LENGTH_SHORT).show();
				}
				try {
					ChatMessage cm =new ChatMessage();
					cm.setDate(new Date());
					cm.setMsg(inputMsg);
					cm.setType(Type.OUTCOMING);
					cm.setName("paul");
					mData.add(cm);
					mAdapter.notifyDataSetChanged();
					listview.setSelection(mData.size() - 1);
					
					Message msg = new Message();
					msg.what = 0x345;
					msg.obj = inputMsg;
					et_input.setText("");
					clientThread.revHandler.sendMessage(msg);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
							
			}		
		});
	}

	private void initData() {
		mData = new ArrayList<ChatMessage>();
		String data = et_input.getText().toString();
		
		mAdapter = new ChatMessageAdapter(Chat.this, mData);
		listview.setAdapter(mAdapter);
		
		
	}

	private void initView() {
		tv_name = (TextView) findViewById(R.id.tv_name);
		et_input = (EditText) findViewById(R.id.et_input);
		bt_send = (Button) findViewById(R.id.bt_send);
		listview = (ListView) findViewById(R.id.id_listview);
		
	}

}
