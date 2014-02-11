package com.dushantha.util;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.dushantha.dto.PhoneContactInfoDTO;

public abstract class CommonOperation {

	public static Set<PhoneContactInfoDTO> getAllPhoneContacts(
			Context context) {
		Log.d("START", "Getting all Contacts");
		Set<PhoneContactInfoDTO> arrContacts = new HashSet<PhoneContactInfoDTO>();
		PhoneContactInfoDTO phoneContactInfo = null;
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		try {
			Cursor cursor = context
					.getContentResolver()
					.query(uri,
							new String[] {
									ContactsContract.CommonDataKinds.Phone.IN_VISIBLE_GROUP,
									ContactsContract.CommonDataKinds.Phone.NUMBER,
									ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
									ContactsContract.CommonDataKinds.Phone._ID },
							null,
							null,
							ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
									+ " ASC");
			cursor.moveToFirst();
			while (cursor.isAfterLast() == false) {
				String contactNumber = cursor
						.getString(cursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				String contactName = cursor
						.getString(cursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				int phoneContactID = cursor
						.getInt(cursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));

				phoneContactInfo = new PhoneContactInfoDTO();
				phoneContactInfo.setPhoneContactID(phoneContactID);
				phoneContactInfo.setContactName(contactName);
				phoneContactInfo.setContactNumber(contactNumber);
				if (phoneContactInfo != null) {
					arrContacts.add(phoneContactInfo);
				}
				phoneContactInfo = null;
				cursor.moveToNext();
			}
			cursor.close();
			cursor = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("END", "Got all Contacts");		
		return arrContacts;
	}

}
