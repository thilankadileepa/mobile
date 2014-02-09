package com.dushantha.presentation;

import android.app.Activity;

import com.dushantha.util.DomainConstants;

public class NotificationActivityFactory {
	
	public static Activity getNotificationActivity(String eventType) {
		if (eventType.equals(DomainConstants.selectedEventType.CALL)) {
			return new AutoCallActivity();
		}

		return new AutoCallActivity();
	}
}
