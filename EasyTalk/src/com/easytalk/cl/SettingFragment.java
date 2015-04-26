package com.easytalk.cl;

import com.cl.easytalk.R;
import com.easytalk.settings.Login;
import com.easytalk.settings.SignIn;
import com.easytalk.settings.Update;

import android.app.Fragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;


public class SettingFragment extends Fragment{

	private static final String tag ="SettingFragment";
	
	private Button bt_login;
	private Button bt_signIn;
	private Button bt_update;
	private Button bt_about;
	private Button bt_exit;
	
	private Builder exitBuilder;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View settingsLayout = inflater.inflate(R.layout.settings_layout,
				container, false);
		return settingsLayout;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		bt_login = (Button) view.findViewById(R.id.bt_login);
		bt_signIn = (Button) view.findViewById(R.id.bt_signIn);
		bt_update = (Button) view.findViewById(R.id.bt_update);
		bt_about = (Button) view.findViewById(R.id.bt_about);
		bt_exit = (Button) view.findViewById(R.id.bt_exit);
		
		bt_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_login = new Intent(getActivity(), Login.class);
				startActivity(intent_login);
			}
		});
		
		bt_signIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_signIn = new Intent(getActivity(), SignIn.class);
				startActivity(intent_signIn);
			}
		});
		
		bt_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_update = new Intent(getActivity(), Update.class);
				startActivity(intent_update);
			}
		});
		
		bt_about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		            	LayoutInflater inflater = LayoutInflater.from(getActivity());
		                // 引入窗口配置文件
		                final View view = inflater.inflate(R.layout.aboutpanel, null);
		                @SuppressWarnings("deprecation")
		        		final PopupWindow pop = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,true);
		                // 设置点击窗口外边窗口消失
		                pop.setTouchable(true);
		                pop.setOutsideTouchable(true);
		                pop.showAsDropDown(view);
		                view.setOnClickListener(new View.OnClickListener() {
		                    public void onClick(View v) {
		                        // TODO Auto-generated method stub
		                        pop.dismiss();
		                    }
		                });
		            }
							
		});
		
		bt_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				exitBuilder = new Builder(getActivity());
				exitBuilder.setIcon(R.drawable.ic_launcher1);
				exitBuilder.setTitle("是否退出");
				exitBuilder.setMessage("确定退出吗?");
				exitBuilder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								System.exit(0);
							}
						});
				exitBuilder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
							}
						});
				exitBuilder.show();
				
			}
		});
	}
}
