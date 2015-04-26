package com.easytalk.cl;


import com.cl.easytalk.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final static String tag = "MainActivity";
	/**
	 * 用于展示消息的Fragment
	 */
	private MessageFragment messageFragment;

	/**
	 * 用于展示联系人的Fragment
	 */
	private ContactsFragment contactsFragment;

	/**
	 * 用于展示动态的Fragment
	 */
	private NewsFragment newsFragment;

	/**
	 * 用于展示设置的Fragment
	 */
	private SettingFragment settingFragment;

	/**
	 * 消息界面布局
	 */
	private View messageLayout;

	/**
	 * 联系人界面布局
	 */
	private View contactsLayout;

	/**
	 * 动态界面布局
	 */
	private View newsLayout;

	/**
	 * 设置界面布局
	 */
	private View settingLayout;
	
	/**
	 * 在Tab布局上显示消息图标的控件
	 */
	private ImageView messageImage;

	/**
	 * 在Tab布局上显示联系人图标的控件
	 */
	private ImageView contactsImage;

	/**
	 * 在Tab布局上显示动态图标的控件
	 */
	private ImageView newsImage;

	/**
	 * 在Tab布局上显示设置图标的控件
	 */
	private ImageView settingImage;

	/**
	 * 在Tab布局上显示消息标题的控件
	 */
	private TextView messageText;

	/**
	 * 在Tab布局上显示联系人标题的控件
	 */
	private TextView contactsText;

	/**
	 * 在Tab布局上显示动态标题的控件
	 */
	private TextView newsText;

	/**
	 * 在Tab布局上显示设置标题的控件
	 */
	private TextView settingText;

	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;
	
	private Builder exitBuilder;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		initViews();
		fragmentManager = getFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);
		
	}

private void initViews() {
		
		messageLayout = findViewById(R.id.message_layout);
		contactsLayout = findViewById(R.id.contacts_layout);
		newsLayout = findViewById(R.id.news_layout);
		settingLayout = findViewById(R.id.setting_layout);
		
		messageImage = (ImageView) findViewById(R.id.message_image);
		contactsImage = (ImageView) findViewById(R.id.contacts_image);
		newsImage = (ImageView) findViewById(R.id.news_image);
		settingImage = (ImageView) findViewById(R.id.setting_image);
		
		messageText = (TextView) findViewById(R.id.message_text);
		contactsText = (TextView) findViewById(R.id.contacts_text);
		newsText = (TextView) findViewById(R.id.news_text);
		settingText = (TextView) findViewById(R.id.setting_text);
	
		messageLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setTabSelection(0);	
				Log.v(tag, "切换到第一个界面");
			}
		});
		
		contactsLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setTabSelection(1);		
				Log.v(tag, "切换到第二个界面");
			}
		});
		
		newsLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setTabSelection(2);
				Log.v(tag, "切换到第三个界面");
			}
		});
		
		settingLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setTabSelection(3);
				Log.v(tag, "切换到第四个界面");
			}
		});
	}
	
	private void setTabSelection(int index) {

		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
		case 0:
			// 当点击了消息tab时，改变控件的图片和文字颜色
			messageImage.setImageResource(R.drawable.message_selected);
			messageText.setTextColor(Color.WHITE);
			Log.v(tag, "检查Fragment");
			if (messageFragment == null) {
				// 如果MessageFragment为空，则创建一个并添加到界面上
				messageFragment = new MessageFragment();
				transaction.add(R.id.content, messageFragment);
//				transaction.show(messageFragment);
			} else {
				// 如果MessageFragment不为空，则直接将它显示出来
				transaction.show(messageFragment);
			}
			break;
		case 1:
			// 当点击了联系人tab时，改变控件的图片和文字颜色
			contactsImage.setImageResource(R.drawable.contacts_selected);
			contactsText.setTextColor(Color.WHITE);
			if (contactsFragment == null) {
				// 如果ContactsFragment为空，则创建一个并添加到界面上
				contactsFragment = new ContactsFragment();
				transaction.add(R.id.content, contactsFragment);
			} else {
				// 如果ContactsFragment不为空，则直接将它显示出来
				transaction.show(contactsFragment);
			}
			break;
		case 2:
			// 当点击了动态tab时，改变控件的图片和文字颜色
			newsImage.setImageResource(R.drawable.news_selected);
			newsText.setTextColor(Color.WHITE);
			if (newsFragment == null) {
				// 如果NewsFragment为空，则创建一个并添加到界面上
				newsFragment = new NewsFragment();
				transaction.add(R.id.content, newsFragment);
			} else {
				// 如果NewsFragment不为空，则直接将它显示出来
				transaction.show(newsFragment);
			}
			break;
		case 3:
		default:
			// 当点击了设置tab时，改变控件的图片和文字颜色
			settingImage.setImageResource(R.drawable.setting_selected);
			settingText.setTextColor(Color.WHITE);
			if (settingFragment == null) {
				// 如果SettingFragment为空，则创建一个并添加到界面上
				settingFragment = new SettingFragment();
				transaction.add(R.id.content, settingFragment);
			} else {
				// 如果SettingFragment不为空，则直接将它显示出来
				transaction.show(settingFragment);
			}
			break;
		}
		transaction.commit();
		
	}

	private void clearSelection() {
		messageImage.setImageResource(R.drawable.message_unselected);
		messageText.setTextColor(Color.parseColor("#82858b"));
		contactsImage.setImageResource(R.drawable.contacts_unselected);
		contactsText.setTextColor(Color.parseColor("#82858b"));
		newsImage.setImageResource(R.drawable.news_unselected);
		newsText.setTextColor(Color.parseColor("#82858b"));
		settingImage.setImageResource(R.drawable.setting_unselected);
		settingText.setTextColor(Color.parseColor("#82858b"));
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (messageFragment != null) {
			transaction.hide(messageFragment);
		}
		if (contactsFragment != null) {
			transaction.hide(contactsFragment);
		}
		if (newsFragment != null) {
			transaction.hide(newsFragment);
		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		exitBuilder = new Builder(this);
		exitBuilder.setIcon(R.drawable.ic_launcher1);
		exitBuilder.setTitle("是否退出");
		exitBuilder.setMessage("确定退出吗?");
		exitBuilder.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						MainActivity.this.finish();
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
		return false;
	}
}
