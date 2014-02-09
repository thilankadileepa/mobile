package com.dushantha.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;

import com.dushantha.business.Operation;
import com.dushantha.business.OperationIMPL;
import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.presentation.AutoCallActivity;
import com.dushantha.presentation.NotificationActivityFactory;
import com.dushantha.util.DomainConstants;

/**
 * notifications to be done as a background service
 * 
 * @author Thilanka
 * 
 */
public class NotificationService extends Service {

	private NotificationManager notificationManager;

	private Operation operationBusiness;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Bundle bundle = intent.getExtras();
		long eventId = bundle.getLong(DomainConstants.EVENT_ID);

		operationBusiness = new OperationIMPL();
		EventUpdateDTO event = operationBusiness.getEvent(this, eventId);
		event.setEventID((int) eventId);

		EventFactory.getInstance().setNotificationService(this);
		EventFactory.getInstance().getEventProcessor(event.getEventType())
				.process(event);

		return super.onStartCommand(intent, flags, startId);
	}

	@SuppressLint("NewApi")
	public void notifyUser(EventUpdateDTO event) {
		notificationManager = (NotificationManager) this
				.getApplicationContext().getSystemService(
						this.getApplicationContext().NOTIFICATION_SERVICE);

		// TODO have a new activity
		Intent mainIntent = new Intent(this, NotificationActivityFactory
				.getNotificationActivity(event.getEventType()).getClass());

		PendingIntent pIntent = PendingIntent.getActivity(this, 0, mainIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		Notification.Builder builder = new Notification.Builder(this);

		builder.setContentIntent(pIntent)
				.setAutoCancel(true)
				.setSmallIcon(
						com.example.simplereminder.R.drawable.ic_action_refresh)
				.setTicker("Reminder").setContentTitle("Reminder")
				.setContentText(event.getEventName());

		notificationManager.notify(0, builder.build());

	}

	public void vibrate() {
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(2000);
	}

	public void playSound() {
		Uri notification = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),
				notification);
		r.play();
	}
}
