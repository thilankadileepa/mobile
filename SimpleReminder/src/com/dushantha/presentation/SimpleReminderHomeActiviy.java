package com.dushantha.presentation;

import java.util.Calendar;

import org.joda.time.DateTime;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.dushantha.business.Operation;
import com.dushantha.business.OperationIMPL;
import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.util.DateDialogFragment;
import com.dushantha.util.DomainConstants;
import com.dushantha.util.DomainConstants.selectedEventType;
import com.dushantha.util.ReturnData;
import com.dushantha.util.TimeDialogFragment;
import com.example.simplereminder.R;

public class SimpleReminderHomeActiviy extends Activity {

	protected static final String TAG = SimpleReminderHomeActiviy.class
			.getSimpleName();
	DateDialogFragment dateFrag;
	TimeDialogFragment timeFrag;
	EditText selectDate;
	EditText selectTime;
	Calendar now;
	Spinner spinner;
	Button btnSave;
	EventUpdateDTO eventUpdateDTO;
	Operation operationBusiness;
	private EditText reminderName;
	private Spinner eventType;
	private Spinner remindMeIn;
	private EditText sms;
	private EditText number;
	private Spinner reminderType;
	private RatingBar ratingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.make_reminder);

		btnSave = (Button) findViewById(R.id.btnSave);
		selectDate = (EditText) findViewById(R.id.lableDate);
		selectTime = (EditText) findViewById(R.id.lableTime);
		reminderName = (EditText) findViewById(R.id.name);
		eventType = (Spinner) findViewById(R.id.eventTypeSelect);
		remindMeIn = (Spinner) findViewById(R.id.remindMeInSelect);
		sms = (EditText) findViewById(R.id.enterMessage);
		number = (EditText) findViewById(R.id.enterNumber);
		reminderType = (Spinner) findViewById(R.id.reminderTypeSelect);
		ratingBar = (RatingBar) findViewById(R.id.priorityBar);

		number.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent k = new Intent(SimpleReminderHomeActiviy.this,
						SimpleReminderContactListViewActivity.class);
				SimpleReminderHomeActiviy.this.startActivityForResult(k,RESULT_OK);
				

			}
		});
		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				operationBusiness = new OperationIMPL();
				ReturnData<Long> returnData = operationBusiness.saveEvent(
						SimpleReminderHomeActiviy.this, buildEvent());
				if (returnData.isSucsess()) {
					startReminderListActivity();
				}

			}
		});

		now = Calendar.getInstance();
		selectDate.setText(String.valueOf(now.get(Calendar.MONTH) + 1) + "-"
				+ String.valueOf(now.get(Calendar.DAY_OF_MONTH)) + "-"
				+ String.valueOf(now.get(Calendar.YEAR)));

		selectTime.setText(String.valueOf(now.get(Calendar.HOUR_OF_DAY)) + "-"
				+ String.valueOf(now.get(Calendar.MINUTE)));

		selectDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialogDate();
			}
		});

		selectTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialogTime();
			}
		});
		eventType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				String selected = parentView.getItemAtPosition(position)
						.toString();
				Log.d(TAG, "Selected event type : " + selected);
				selectedEventType selectedType = selectedEventType
						.valueOf(selected.toUpperCase());
				displayCallSmsControlls(selectedType);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				// arg0.getItemAtPosition(0);

			}
		});

		remindMeIn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				String selected = parentView.getItemAtPosition(position)
						.toString();
				Log.d(TAG, "Selected event type : " + selected);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		reminderType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				String selected = parentView.getItemAtPosition(position)
						.toString();
				Log.d(TAG, "Selected event type : " + selected);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.simple_reminder, menu);
		MenuItem menuItem = menu.findItem(R.id.addNew);
		menuItem.setVisible(false);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.reminderList:
			startReminderListActivity();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void startReminderListActivity() {
		Intent k = new Intent(SimpleReminderHomeActiviy.this,
				SimpleReminderListViewActivity.class);
		startActivity(k);
	}

	public void showDialogDate() {
		FragmentTransaction ft = getFragmentManager().beginTransaction(); // Get
																			// the
																			// fragment
		dateFrag = DateDialogFragment.newInstance(this,
				new DateDialogFragmentListener() {
					public void updateChangedDate(int year, int month, int day) {
						selectDate.setText(String.valueOf(month + 1) + "/"
								+ String.valueOf(day) + "/"
								+ String.valueOf(year));
						now.set(year, month, day);
					}
				}, now);

		dateFrag.show(ft, "DateDialogFragment");

	}

	public void showDialogTime() {
		FragmentTransaction ft = getFragmentManager().beginTransaction();

		timeFrag = TimeDialogFragment.getInstance(this,
				new TimeDialogFragmentListener() {
					public void updateChangedTime(int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						selectTime.setText(String.valueOf(hourOfDay) + ":"
								+ String.valueOf(minute));
						now.set(Calendar.HOUR_OF_DAY, hourOfDay);
						now.set(Calendar.MINUTE, minute);

					}
				}, now);

		timeFrag.show(ft, "TimeDialogFragment");
	}

	public interface DateDialogFragmentListener {

		public void updateChangedDate(int year, int month, int day);
	}

	public interface TimeDialogFragmentListener {
		public void updateChangedTime(int hourOfDay, int minute);
	}

	private void displayCallSmsControlls(selectedEventType type) {

		TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber);
		EditText enterNumber = (EditText) findViewById(R.id.enterNumber);

		TextView message = (TextView) findViewById(R.id.message);
		EditText enterMessage = (EditText) findViewById(R.id.enterMessage);

		if (type.equals(selectedEventType.CALL)) {
			phoneNumber.setVisibility(View.VISIBLE);
			enterNumber.setVisibility(View.VISIBLE);
			message.setVisibility(View.GONE);
			enterMessage.setVisibility(View.GONE);
		} else if (type.equals(selectedEventType.SMS)) {
			phoneNumber.setVisibility(View.VISIBLE);
			enterNumber.setVisibility(View.VISIBLE);
			message.setVisibility(View.VISIBLE);
			enterMessage.setVisibility(View.VISIBLE);
		} else {
			phoneNumber.setVisibility(View.GONE);
			enterNumber.setVisibility(View.GONE);
			message.setVisibility(View.GONE);
			enterMessage.setVisibility(View.GONE);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		 if (resultCode == RESULT_OK) {
		      if (data != null) {
		         Bundle b = data.getExtras(); 
		         String str = b.getString("contactName");
		         number.setText(str);
		      }
		 }
	}

	private EventUpdateDTO buildEvent() {
		eventUpdateDTO = new EventUpdateDTO();
		reminderName = (EditText) findViewById(R.id.name);
		eventUpdateDTO.setEventName(reminderName.getText().toString());
		eventType = (Spinner) findViewById(R.id.eventTypeSelect);
		eventUpdateDTO.setEventType(eventType.getSelectedItem().toString());
		String[] dateString = selectDate.getText().toString().split("/");
		int month = Integer.parseInt(dateString[0]);
		int date = Integer.parseInt(dateString[1]);
		int year = Integer.parseInt(dateString[2]);

		String[] timeString = selectTime.getText().toString().split(":");
		int hour = Integer.parseInt(timeString[0]);
		int minute = Integer.parseInt(timeString[1]);

		eventUpdateDTO
				.setDateTime(new DateTime(year, month, date, hour, minute));

		Spinner remindMeIn = (Spinner) findViewById(R.id.remindMeInSelect);
		String remindMeInType = remindMeIn.getSelectedItem().toString();

		eventUpdateDTO.setRemindMeIn(DomainConstants
				.getRemindMeInValue(remindMeInType));

		sms = (EditText) findViewById(R.id.enterMessage);
		eventUpdateDTO.setSms(sms.getText().toString());

		number = (EditText) findViewById(R.id.enterNumber);
		eventUpdateDTO.setPhoneNumber(number.getText().toString());

		reminderType = (Spinner) findViewById(R.id.reminderTypeSelect);
		eventUpdateDTO.setReminderType(reminderType.getSelectedItem()
				.toString());

		ratingBar = (RatingBar) findViewById(R.id.priorityBar);
		eventUpdateDTO.setPriority(ratingBar.getRating());
		return eventUpdateDTO;
	}

}