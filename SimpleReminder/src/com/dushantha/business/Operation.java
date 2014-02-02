package com.dushantha.business;

import java.util.List;

import android.content.Context;

import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.presentation.SimpleReminderHomeActiviy;

public interface Operation {
	public boolean saveEvent(final Context context, EventUpdateDTO eventUpdateDto);
	public boolean editEvent(EventUpdateDTO eventUpdateDTO);
	public boolean deleteEvent(Integer eventId);
	public List<EventUpdateDTO> getAllEvents(final Context context);
}
