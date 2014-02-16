package com.dushantha.business;

import java.util.List;

import android.content.Context;

import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.util.ReturnData;

public interface Operation {
	public ReturnData<Long> saveEvent(final Context context,
			EventUpdateDTO eventUpdateDto);

	public boolean editEvent(EventUpdateDTO eventUpdateDTO);

	public ReturnData<Boolean> deleteEvent(final Context context,Integer eventId);

	public List<EventUpdateDTO> getAllEvents(final Context context);
	
	public EventUpdateDTO getEvent(final Context context, long eventId);
	
}
