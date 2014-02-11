package com.dushantha.presentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.dushantha.dto.PhoneContactInfoDTO;
import com.dushantha.util.CommonOperation;
import com.dushantha.util.CustomContactListAdapter;
import com.example.simplereminder.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


public class SimpleReminderContactListViewActivity extends ListActivity {
	private List<PhoneContactInfoDTO> dataList;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_listview);		
		Set<PhoneContactInfoDTO> contactSet = CommonOperation
				.getAllPhoneContacts(this);
		List<PhoneContactInfoDTO> contactList = new ArrayList<PhoneContactInfoDTO>();
		for (PhoneContactInfoDTO cont : contactSet) {
			contactList.add(cont);
		}
		dataList=contactList;
		Collections.sort(contactList);
		setListAdapter(new CustomContactListAdapter(this, contactList));
		
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		PhoneContactInfoDTO selected = dataList.get(position);	
		
		Intent data = new Intent(Intent.ACTION_SEND);
		data.putExtra("contactNumber",selected.getContactNumber());
		setResult(1, data);
		this.finish();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
		super.finish();
	}
	
	

}
