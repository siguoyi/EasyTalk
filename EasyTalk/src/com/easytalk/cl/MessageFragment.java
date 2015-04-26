package com.easytalk.cl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cl.easytalk.R;
import com.easytalk.chat.Chat;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MessageFragment extends Fragment {

	private final String tag = "MessageFragment";
	
	private int[] imageId = {R.drawable.icon, R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4,
			R.drawable.icon5, R.drawable.icon6, R.drawable.icon7}; 
	
	private ListView message_details;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.message_layout, 
				container, false);
		return messageLayout;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		Log.v(tag, "view "+view);
		message_details = (ListView) view.findViewById(R.id.message_list);
		Log.v(tag, "message_details "+message_details);
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		
		for(int i = 0; i < 7; i++){
			HashMap<String, Object> map =new HashMap<>();
			map.put("time", "time" + i);
			map.put("user", "user" + i);
			map.put("content", "content" + i);
			map.put("image", imageId[i]);
			data.add(map);
		}
		
		String[] from = {"time", "user", "content", "image"};
		int[] to = {R.id.message_time, R.id.message_user_text, R.id.message_details_text, R.id.message_pic};
		
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, R.layout.message_details, from, to);
		message_details.setAdapter(adapter);
		
		message_details.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
//				HashMap<String, Object> map = (HashMap<String, Object>) message_details.getItemAtPosition(arg2);
				Intent intent = new Intent(getActivity(), Chat.class);
				startActivity(intent);
			}
		});
	}
}
