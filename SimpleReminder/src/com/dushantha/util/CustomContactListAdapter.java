package com.dushantha.util;

import java.util.ArrayList;
import java.util.List;
import com.dushantha.dto.PhoneContactInfoDTO;
import com.example.simplereminder.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;


@SuppressLint("DefaultLocale")
public class CustomContactListAdapter extends ArrayAdapter<PhoneContactInfoDTO> {

	private List<PhoneContactInfoDTO> dataList;
	private LayoutInflater layoutInflater;
	private final Context context;
	private Filter filter;

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

	@Override
	public Filter getFilter() {
		if (filter == null) {
			filter = new Filter() {

				@Override
				protected void publishResults(CharSequence constraint,
						FilterResults results) {
					// TODO Auto-generated method stub

				}

				@SuppressLint("DefaultLocale")
				@Override
				protected FilterResults performFiltering(CharSequence constraint) {
					// TODO Auto-generated method stub
					FilterResults results = new FilterResults();
					// We implement here the filter logic
					if (constraint == null || constraint.length() == 0) {
						// No filter implemented we return all the list
						results.values = dataList;
						results.count = dataList.size();
					} else {
						// We perform filtering operation
						List<PhoneContactInfoDTO> nPlanetList = new ArrayList<PhoneContactInfoDTO>();

						for (PhoneContactInfoDTO p : dataList) {
							if (p.getContactName()
									.toUpperCase()
									.startsWith(
											constraint.toString().toUpperCase()))
								nPlanetList.add(p);
						}

						results.values = nPlanetList;
						results.count = nPlanetList.size();

					}
					return results;
				}
			};
		}
		return filter;
	}

}
