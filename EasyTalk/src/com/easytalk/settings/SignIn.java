package com.easytalk.settings;

import org.json.JSONException;
import org.json.JSONObject;

import com.cl.easytalk.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends Activity{

	private static final String tag = "SignIn";
	
	private EditText et_username_sign;
	private EditText et_password_sign;
	private Button bt_sign_confirm;
	private Button bt_sign_cancel;
	
	JSONObject jsonObject = new JSONObject();
	private String Url = "http://10.105.36.51/new/sign.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin_layout);
		
		et_username_sign = (EditText) findViewById(R.id.et_sign_username);
		et_password_sign = (EditText) findViewById(R.id.et_sign_password);
		bt_sign_confirm  = (Button) findViewById(R.id.bt_sign_confirm);
		bt_sign_cancel = (Button) findViewById(R.id.bt_sign_cancel);
		
		bt_sign_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username = et_username_sign.getText().toString();
				String password = et_password_sign.getText().toString();
				
				try {
					jsonObject.put("username", username);
					jsonObject.put("password", password);
					et_username_sign.setText("");
					et_password_sign.setText("");

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
					}
					
				}.start();	
				
			}
		});
		
		bt_sign_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SignIn.this.finish();
			}
		});
	}
	
}
