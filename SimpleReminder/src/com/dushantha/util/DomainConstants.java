package com.dushantha.util;

public class DomainConstants {

	public static final String FIVE_MINUTES = "5 minutes";
	public static final String TEN_MINUTES = "10 minutes";
	public static final String FIFTEEN_MINUTES = "15 minutes";
	public static final String THIRTY_MINUTES = "30 minutes";
	public static final String FOURTYFIVE_MINUTES = "45 minutes";
	public static final String SIXTY_MINUTES = "60 minutes";

	public enum selectedEventType {
		CALL, SMS, PARTY, MEETING, OTHER
	}

	public enum reminderType {
		VIBRATION, NOTIFICATION_SOUND, SCREEN, SNOOZE
	}

	public enum priorityType {
		LOW, MEDIUM, HIGH
	}

	public static int getRemindMeInValue(String value) {
		if (value != null) {
			if (value.equals(FIVE_MINUTES)) {
				return 5;
			} else if (value.equals(TEN_MINUTES)) {
				return 10;
			} else if (value.equals(FIFTEEN_MINUTES)) {
				return 15;
			} else if (value.equals(THIRTY_MINUTES)) {
				return 30;
			} else if (value.equals(FOURTYFIVE_MINUTES)) {
				return 45;
			} else if (value.equals(SIXTY_MINUTES)) {
				return 60;
			}
		}
		return 0;
	}
}
