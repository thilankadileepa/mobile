package com.dushantha.presentation;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

import com.dushantha.business.Operation;
import com.dushantha.business.OperationIMPL;
import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.util.CustomListArrayAdapter;
import com.dushantha.util.ReturnData;
import com.example.simplereminder.R;

/**
 * @author Dushantha
 * 
 */
public class SimpleReminderListViewActivity extends ListActivity {

	private Operation operation;
	private ListView listView;
	private List<EventUpdateDTO> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder_list);
		loadEventList();
	}

	private void loadEventList() {
		operation = new OperationIMPL();
		List<EventUpdateDTO> listData = new ArrayList<EventUpdateDTO>();
		listData = operation.getAllEvents(this);
		dataList = listData;
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

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		EventUpdateDTO eventUpdateDTO = dataList.get(position);
		Intent data = new Intent(SimpleReminderListViewActivity.this, SimpleReminderHomeActiviy.class);
		data.putExtra("event", (Parcelable)eventUpdateDTO);
		startActivity(data);	
		
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}
	
	

}
