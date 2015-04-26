package com.easytalk.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;

import com.easytalk.chat.ChatMessage.Type;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class ClientThread implements Runnable {

	private Socket s;
	private Handler handler;
	public Handler revHandler;
	
	BufferedReader br = null;
	OutputStream os = null;
	
	public ClientThread(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void run() {
		try {
			s = new Socket("10.105.36.51", 30000);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			os = s.getOutputStream();
			
			new Thread(){

				@Override
				public void run() {
					String content = null;
					try {
						while((content = br.readLine()) != null){
							ChatMessage cm =new ChatMessage();
							cm.setDate(new Date());
							cm.setMsg(content);
							cm.setType(Type.INCOMING);
							cm.setName("paul");
							
							Message msg = new Message();
							msg.what = 0x123;
							msg.obj = cm;
							handler.sendMessage(msg);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}.start();
			
			Looper.prepare();
			
			revHandler = new Handler(){

				@Override
				public void handleMessage(Message msg) {
					if(msg.what == 0x345){
						try {
							os.write((msg.obj.toString() + "\r\n").getBytes("utf-8"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			};
			Looper.loop();
		} catch (SocketTimeoutException e) {
			System.out.println("ÍøÂçÁ¬½Ó³¬Ê±£¡");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
