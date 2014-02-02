package com.dushantha.util;

import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.entities.EventEntity;
import com.dushantha.entities.SettingsEntity;
import com.dushantha.entities.SmsCallEntity;

public class EntityConverter {
	
	//Converts dto in to entity
	public static EventEntity getEventEntityFromDTO(EventUpdateDTO eventUpdateDTO){
		EventEntity eventEntity = new EventEntity();
		if(!eventUpdateDTO.equals(null)){
			eventEntity.setSettingsEntity(new SettingsEntity());
			eventEntity.setSmsCallEntity(new SmsCallEntity());
			eventEntity.setEventId(eventUpdateDTO.getEventID());
			eventEntity.setEventName(eventUpdateDTO.getEventName());
			eventEntity.setDateTime(eventUpdateDTO.getDateTime());			
			
			eventEntity.getSmsCallEntity().setEventId(eventUpdateDTO.getEventID());
			eventEntity.getSmsCallEntity().setId(eventUpdateDTO.getSmsCallID());
			eventEntity.getSmsCallEntity().setNumber(eventUpdateDTO.getPhoneNumber());
			eventEntity.getSmsCallEntity().setSms(eventUpdateDTO.getSms());
			
			eventEntity.getSettingsEntity().setEventID(eventUpdateDTO.getEventID());
			eventEntity.getSettingsEntity().setId(eventUpdateDTO.getSettingID());
			eventEntity.getSettingsEntity().setEventType(eventUpdateDTO.getEventType());
			eventEntity.getSettingsEntity().setPriority(eventUpdateDTO.getPriority());
			eventEntity.getSettingsEntity().setReminderType(eventUpdateDTO.getReminderType());
			eventEntity.getSettingsEntity().setRemindMeIn(eventUpdateDTO.getRemindMeIn());			
			
		}
		return eventEntity;
	}

}
