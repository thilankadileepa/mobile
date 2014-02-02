package com.dushantha.entities;

import org.joda.time.DateTime;

public class EventEntity {

	private Integer eventId;
	private String eventName;
	private DateTime dateTime;	
	private SettingsEntity settingsEntity;
	private SmsCallEntity smsCallEntity;

	public EventEntity() {

	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public SettingsEntity getSettingsEntity() {
		if(settingsEntity == null){
			settingsEntity = new SettingsEntity();
		}
		return settingsEntity;
	}

	public void setSettingsEntity(SettingsEntity settingsEntity) {
		this.settingsEntity = settingsEntity;
	}

	public SmsCallEntity getSmsCallEntity() {
		if(smsCallEntity == null){
			smsCallEntity = new SmsCallEntity();
		}
		return smsCallEntity;
	}

	public void setSmsCallEntity(SmsCallEntity smsCallEntity) {
		this.smsCallEntity = smsCallEntity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result
				+ ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result
				+ ((settingsEntity == null) ? 0 : settingsEntity.hashCode());
		result = prime * result
				+ ((smsCallEntity == null) ? 0 : smsCallEntity.hashCode());
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
		EventEntity other = (EventEntity) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (settingsEntity == null) {
			if (other.settingsEntity != null)
				return false;
		} else if (!settingsEntity.equals(other.settingsEntity))
			return false;
		if (smsCallEntity == null) {
			if (other.smsCallEntity != null)
				return false;
		} else if (!smsCallEntity.equals(other.smsCallEntity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventEntity [eventName=" + eventName + ", dateTime=" + dateTime
				+ "]";
	}
	
	
}
