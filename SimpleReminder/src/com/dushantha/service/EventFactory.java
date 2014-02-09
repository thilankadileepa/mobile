package com.dushantha.service;

import android.telephony.SmsManager;

import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.util.DomainConstants;

public class EventFactory {

	NotificationService notificationService = null;
	private static EventFactory eventFactory = new EventFactory();

	public static EventFactory getInstance() {
		return eventFactory;
	}

	public EventProcessor getEventProcessor(String eventType) {

		if (eventType.equals(DomainConstants.selectedEventType.CALL)) {
			return new CallEventProcessor();
		} else if (eventType.equals(DomainConstants.selectedEventType.SMS)) {
			return new SMSEventProcessor();
		} else {
			return new DefaultEventProcessor();
		}

	}

	public abstract class EventProcessor {
		public abstract void process(EventUpdateDTO event);

		public void notifyUser(EventUpdateDTO event) {
			NotificationFactory.getInstance().setNotificationService(
					notificationService);
			NotificationFactory.getInstance()
					.getNotificationProcessor(event.getReminderType())
					.process(event);
		}
	}

	public class CallEventProcessor extends EventProcessor {

		@Override
		public void process(EventUpdateDTO event) {

			notifyUser(event);
		}

	}

	public class SMSEventProcessor extends EventProcessor {

		@Override
		public void process(EventUpdateDTO event) {

			// send the sms
			String phoneNumberReciver = event.getPhoneNumber();
			String message = event.getSms();
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(phoneNumberReciver, null, message, null, null);

			notifyUser(event);
		}

	}

	public class DefaultEventProcessor extends EventProcessor {

		@Override
		public void process(EventUpdateDTO event) {

			notifyUser(event);
		}

	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

}
