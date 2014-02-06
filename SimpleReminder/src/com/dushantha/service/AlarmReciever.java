package com.dushantha.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * when an event is added to the AlarmManager, when the event occurs
 * the onReceive method will fire.
 * 
 * @author Thilanka
 *
 */ 
public class AlarmReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context, NotificationService.class);
		
		// copy the parameters
		service.putExtras(intent.getExtras());
		
		context.startService(service);
	}

}
