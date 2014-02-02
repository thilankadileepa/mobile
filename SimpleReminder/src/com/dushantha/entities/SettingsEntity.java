package com.dushantha.entities;

public class SettingsEntity {

	private Integer Id;
	private Integer eventID;
	private String eventType;
	private String reminderType;
	private Float priority;
	private Integer remindMeIn;

	public SettingsEntity() {

	}
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getReminderType() {
		return reminderType;
	}

	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}

	public Float getPriority() {
		return priority;
	}

	public void setPriority(Float priority) {
		this.priority = priority;
	}

	public Integer getRemindMeIn() {
		return remindMeIn;
	}

	public void setRemindMeIn(Integer remindMeIn) {
		this.remindMeIn = remindMeIn;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventID == null) ? 0 : eventID.hashCode());
		result = prime * result
				+ ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result
				+ ((remindMeIn == null) ? 0 : remindMeIn.hashCode());
		result = prime * result
				+ ((reminderType == null) ? 0 : reminderType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SettingsEntity other = (SettingsEntity) obj;
		if (eventID == null) {
			if (other.eventID != null)
				return false;
		} else if (!eventID.equals(other.eventID))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (remindMeIn == null) {
			if (other.remindMeIn != null)
				return false;
		} else if (!remindMeIn.equals(other.remindMeIn))
			return false;
		if (reminderType == null) {
			if (other.reminderType != null)
				return false;
		} else if (!reminderType.equals(other.reminderType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SettingsEntity [settingID=" + Id + ", eventID="
				+ eventID + ", eventType=" + eventType + ", reminderType="
				+ reminderType + ", priority=" + priority + ", remindMeIn="
				+ remindMeIn + "]";
	}

}
