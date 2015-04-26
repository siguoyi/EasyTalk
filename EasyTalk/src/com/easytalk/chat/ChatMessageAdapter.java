package com.easytalk.chat;

import java.text.SimpleDateFormat;
import java.util.List;

import com.cl.easytalk.R;
import com.easytalk.chat.ChatMessage.Type;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatMessageAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<ChatMessage> mData;
	
	public ChatMessageAdapter(Context context, List<ChatMessage> mData) {
		inflater = LayoutInflater.from(context);
		this.mData = mData;
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	
	
	@Override
	public int getItemViewType(int position) {
		ChatMessage chatMessage = mData.get(position);
		if(chatMessage.getType() == Type.INCOMING){
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
				ChatMessage chatMessage = mData.get(position);
				ViewHolder viewHolder = null;
				if(convertView == null){
					if(getItemViewType(position)==0){
						convertView = inflater.inflate(R.layout.item_from_message, parent,false);
						viewHolder = new ViewHolder();
						viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_from_message_date);
						viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_from_message);
					}
					else{
						convertView = inflater.inflate(R.layout.item_to_message, parent,false);
						viewHolder = new ViewHolder();
						viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_to_message_date);
						viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_to_message);
					}
					convertView.setTag(viewHolder);
				}else{
					viewHolder = (ViewHolder) convertView.getTag();
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				viewHolder.mDate.setText(df.format(chatMessage.getDate()));
				viewHolder.mMsg.setText(chatMessage.getMsg());
				return convertView;
	}

	
	private final class ViewHolder {
		TextView mDate;
		TextView mMsg;
	}

}
