package com.dushantha.service;

import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.util.DomainConstants;

public class NotificationFactory {

	private static NotificationFactory notificationFactory = new NotificationFactory();

	public static NotificationFactory getInstance() {
		return notificationFactory;
	}

	public NotificationProcessor getNotificationProcessor(
			String notificationType) {

		if (notificationType.equals(DomainConstants.reminderType.VIBRATION)) {
			return new VibrateNotificationProcessor();
		} else if (notificationType.equals(DomainConstants.reminderType.SCREEN)) {
			return new ScreenNotificationProcessor();
		} else {
			return new SoundNotificationProcessor();
		}

	}

	public abstract class NotificationProcessor {
		public abstract void process(EventUpdateDTO event);
	}

	public class VibrateNotificationProcessor extends NotificationProcessor {

		@Override
		public void process(EventUpdateDTO event) {

		}

	}

	public class SoundNotificationProcessor extends NotificationProcessor {

		@Override
		public void process(EventUpdateDTO event) {

		}

	}

	public class ScreenNotificationProcessor extends NotificationProcessor {

		@Override
		public void process(EventUpdateDTO event) {

		}

	}
}
