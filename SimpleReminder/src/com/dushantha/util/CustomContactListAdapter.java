package com.dushantha.util;

import java.util.List;

import com.dushantha.dto.PhoneContactInfoDTO;
import com.dushantha.util.CustomListArrayAdapter.ViewHolder;
import com.example.simplereminder.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class CustomContactListAdapter extends ArrayAdapter<PhoneContactInfoDTO> {

	private List<PhoneContactInfoDTO> dataList;
	private LayoutInflater layoutInflater;
	private final Context context;

	public CustomContactListAdapter(Context context,
			List<PhoneContactInfoDTO> contactList) {
		super(context, R.layout.reminder_list, contactList);
		this.context = context;
		this.dataList = contactList;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.dataList.size();
	}

	@Override
	public PhoneContactInfoDTO getItem(int position) {
		// TODO Auto-generated method stub
		return this.dataList.get(position);
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
			convertView = layoutInflater.inflate(R.layout.contact_listitem,
					parent, false);
			holder = new ViewHolder();
			holder.contactName = (TextView) convertView
					.findViewById(R.id.contactName);
			holder.contactNumber = (TextView) convertView
					.findViewById(R.id.contactNumber);
			
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.contactName.setText(dataList.get(position).getContactName());
		holder.contactNumber.setText(dataList.get(position).getContactNumber());			
		
		return convertView;
	}

	static class ViewHolder {
		TextView contactName;
		TextView contactNumber;
	}

}
