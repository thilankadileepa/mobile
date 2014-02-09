package com.dushantha.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dushantha.dto.PhoneContactInfoDTO;
import com.dushantha.util.CommonOperation;
import com.dushantha.util.CustomContactListAdapter;
import com.example.simplereminder.R;

import android.app.ListActivity;
import android.os.Bundle;

public class SimpleReminderContactListViewActivity extends ListActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder_list);
		Set<PhoneContactInfoDTO> contactSet = CommonOperation.getAllPhoneContacts(this);
		List<PhoneContactInfoDTO> contactList = new ArrayList<PhoneContactInfoDTO>();
		for(PhoneContactInfoDTO cont : contactSet){
			contactList.add(cont);
		}
		setListAdapter(new CustomContactListAdapter(this, contactList));
	}
    
	
}
