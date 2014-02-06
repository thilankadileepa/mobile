package com.dushantha.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.dushantha.business.Operation;
import com.dushantha.business.OperationIMPL;
import com.dushantha.dto.EventUpdateDTO;
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
		EventFactory.getInstance().getEventProcessor(event.getEventType())
				.process(event);

		return super.onStartCommand(intent, flags, startId);
	}
}
