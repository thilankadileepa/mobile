package com.dushantha.util;

import java.util.List;

import com.dushantha.dto.EventUpdateDTO;
import com.example.simplereminder.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class CustomListArrayAdapter extends ArrayAdapter<EventUpdateDTO> {

	private List<EventUpdateDTO> listData;

	private LayoutInflater layoutInflater;
	
	private final Context context;

	public CustomListArrayAdapter(Context context, List<EventUpdateDTO> listData) {
		super(context, R.layout.reminder_list, listData);
		this.listData = listData;
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public EventUpdateDTO getItem(int position) {
		// TODO Auto-generated method stub
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.listitem_layout,
					parent, false);
			holder = new ViewHolder();
			holder.reminderNameView = (TextView) convertView
					.findViewById(R.id.eventItemName);
			holder.reminderDateTimeView = (TextView) convertView
					.findViewById(R.id.eventItemDate);
			holder.reminderTypeView = (TextView) convertView
					.findViewById(R.id.eventItemType);
			holder.reminderPhoneNumberView = (TextView) convertView
					.findViewById(R.id.eventItemNumber);
			holder.reminderPriorityBar = (RatingBar) convertView
					.findViewById(R.id.eventItemRating);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.reminderNameView.setText(listData.get(position).getEventName());
		holder.reminderDateTimeView.setText(listData.get(position)
				.getDateTime().toString());
		holder.reminderTypeView.setText(listData.get(position)
				.getEventType());
		holder.reminderPhoneNumberView.setText(listData.get(position)
				.getPhoneNumber());
		holder.reminderPriorityBar.setRating(listData.get(position)
				.getPriority());
		holder.reminderPriorityBar.setEnabled(false);
		holder.reminderPriorityBar.setFocusable(false);
		
		return convertView;
	}

	static class ViewHolder {
		TextView reminderNameView;
		TextView reminderDateTimeView;
		TextView reminderTypeView;
		TextView reminderPhoneNumberView;
		RatingBar reminderPriorityBar;
	}

}
