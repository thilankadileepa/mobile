package com.dushantha.service;

import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.util.DomainConstants;

public class EventFactory {

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

			notifyUser(event);
		}

	}

	public class DefaultEventProcessor extends EventProcessor {

		@Override
		public void process(EventUpdateDTO event) {

			notifyUser(event);
		}

	}

}
