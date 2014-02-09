package com.dushantha.service;

import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.util.DomainConstants;

public class NotificationFactory {

	NotificationService notificationService = null;

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

		protected void vibrate() {
			notificationService.vibrate();
		}

		protected void notifyUser(EventUpdateDTO event) {
			notificationService.notifyUser(event);
		}

		protected void sound() {
			notificationService.playSound();
		}
	}

	public class VibrateNotificationProcessor extends NotificationProcessor {

		@Override
		public void process(EventUpdateDTO event) {
			vibrate();
			notifyUser(event);
		}

	}

	public class SoundNotificationProcessor extends NotificationProcessor {

		@Override
		public void process(EventUpdateDTO event) {
			sound();
			notifyUser(event);
		}

	}

	public class ScreenNotificationProcessor extends NotificationProcessor {

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
