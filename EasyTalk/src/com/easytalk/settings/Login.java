package com.easytalk.settings;

import org.json.JSONException;
import org.json.JSONObject;

import com.cl.easytalk.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity{

	private static final String tag ="Login";
	private EditText et_username;
	private EditText et_password;
	private Button bt_login_confirm;
	private Button bt_login_cancel;
	private Handler mHandler;
	
	JSONObject jsonObject = new JSONObject();
	private String Url = "http://10.105.36.51/new/login.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		bt_login_confirm = (Button) findViewById(R.id.bt_login_confirm);
		bt_login_cancel = (Button) findViewById(R.id.bt_login_cancel);
		
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				
				if(msg.what == 0x123){
					Log.v(tag, "handle msg: "+msg.obj.toString());
					switch(msg.obj.toString()){
					
						case "ok":
						{
							Toast.makeText(Login.this, "注册成功！！", Toast.LENGTH_SHORT).show();
							et_username.setText("");
							et_password.setText("");
							Login.this.finish();
							break;
						}
						case "error":
						{
							Toast.makeText(Login.this, "注册失败，请重试。。", Toast.LENGTH_SHORT).show();
							break;
						}
						case "exist":
						{
							Toast.makeText(Login.this, "此用户名已被占用，请重新输入", Toast.LENGTH_SHORT).show();
							break;
						}
					}
//					Log.v(tag, "handle msg: "+msg.obj.toString());
//					if(msg.obj.toString().equals("ok")){
//						Toast.makeText(Login.this, "注册成功！！", Toast.LENGTH_SHORT).show();
//						et_username.setText("");
//						et_password.setText("");
//						Login.this.finish();
//					} else 
//						if(msg.obj.toString().equals("error")){
//							Toast.makeText(Login.this, "注册失败，请重试。。", Toast.LENGTH_SHORT).show();
//					} else 
//						if(msg.obj.toString()=="exist"){
//							Log.v(tag, "此用户名已被占用，请重新输入");
//							Toast.makeText(Login.this, "此用户名已被占用，请重新输入", Toast.LENGTH_SHORT).show();
//					}
				}
			}
			
		};
		
		bt_login_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String username = et_username.getText().toString();
				String password = et_password.getText().toString();
				int state = 0;
				
				try {
					jsonObject.put("username", username);
					jsonObject.put("password", password);
					jsonObject.put("state", state);
					

				} catch (JSONException e) {
					e.printStackTrace();
				}
				Log.v(tag, "json" + jsonObject);
				new Thread(){

					@Override
					public void run() {
						Utils upload = new Utils(Url, jsonObject);
						String result = upload.upData();
						Log.v(tag, "result:" + result);
						
						Message msg = new Message();
						msg.what = 0x123;
						msg.obj = result;
						mHandler.sendMessage(msg);
					}
					
				}.start();	
				
				
			}
		});
		
		bt_login_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				Login.this.finish();
			}
		});		
		
	}
}
