package com.dushantha.presentation;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dushantha.business.Operation;
import com.dushantha.business.OperationIMPL;
import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.util.CustomListArrayAdapter;
import com.example.simplereminder.R;

public class SimpleReminderListViewActivity extends ListActivity {

	private Operation operation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder_list);
		operation = new OperationIMPL();
		// ListView listView = (ListView)findViewById(R.id.eventListView);
		List<EventUpdateDTO> listData = new ArrayList<EventUpdateDTO>();
		listData = operation.getAllEvents(this);
		// listView.setAdapter(new CustomListArrayAdapter(this, listData));
		setListAdapter(new CustomListArrayAdapter(this, listData));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.simple_reminder, menu);
		MenuItem menuItem = menu.findItem(R.id.reminderList);
		menuItem.setVisible(false);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.addNew:
			startMakeReminderActivity();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void startMakeReminderActivity() {
		Intent k = new Intent(SimpleReminderListViewActivity.this,
				SimpleReminderHomeActiviy.class);
		startActivity(k);
	}

}
