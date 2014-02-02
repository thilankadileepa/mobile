package com.dushantha.dto;

import org.joda.time.DateTime;

import com.dushantha.entities.EventEntity;



public class EventUpdateDTO {

	private Integer eventID;
	private Integer settingID;
	private Integer smsCallID;
	private String eventName;
	private String eventType;
	private String phoneNumber;
	private String sms;
	private DateTime dateTime;
	private Integer remindMeIn;
	private Float priority;
	private String reminderType;
	
	public EventUpdateDTO(){
		
	}

	public EventUpdateDTO(EventEntity entity) {
		this.eventID = entity.getEventId();
		this.smsCallID = entity.getSmsCallEntity().getId();
		this.settingID = entity.getSettingsEntity().getId();
		this.eventName = entity.getEventName();
		this.dateTime = entity.getDateTime();
		this.eventType = entity.getSettingsEntity().getEventType();
		this.phoneNumber = entity.getSmsCallEntity().getNumber();
		this.sms = entity.getSmsCallEntity().getSms();
		
		this.remindMeIn = entity.getSettingsEntity().getRemindMeIn();
		this.reminderType = entity.getSettingsEntity().getReminderType();
		this.priority = entity.getSettingsEntity().getPriority();

	}

	public Integer getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	public Integer getSettingID() {
		return settingID;
	}

	public void setSettingID(Integer settingID) {
		this.settingID = settingID;
	}

	public Integer getSmsCallID() {
		return smsCallID;
	}

	public void setSmsCallID(Integer smsCallID) {
		this.smsCallID = smsCallID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getRemindMeIn() {
		return remindMeIn;
	}

	public void setRemindMeIn(Integer remindMeIn) {
		this.remindMeIn = remindMeIn;
	}

	public Float getPriority() {
		return priority;
	}

	public void setPriority(Float priority) {
		this.priority = priority;
	}

	public String getReminderType() {
		return reminderType;
	}

	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}

	@Override
	public String toString() {
		return "EventUpdateDTO [eventID=" + eventID + ", eventName="
				+ eventName + ", eventType=" + eventType + "]";
	}

}
