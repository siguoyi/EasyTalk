package com.easytalk.cl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cl.easytalk.R;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ContactsFragment extends Fragment {

	private final String tag = "ContactsFragment";
	
	private ListView contacts_details;
	private int[] imageId = {R.drawable.icon, R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4,
			R.drawable.icon5, R.drawable.icon6, R.drawable.icon7}; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contactsLayout = inflater.inflate(R.layout.contacts_layout,
				container, false);
		return contactsLayout;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		Log.v(tag, "view "+view);
		contacts_details = (ListView) view.findViewById(R.id.contacts_list);
		Log.v(tag, "message_details "+ contacts_details);
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
		contacts_details.setAdapter(adapter);
	}
}
