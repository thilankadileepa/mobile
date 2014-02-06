package com.dushantha.dao;

import java.util.List;

import android.content.Context;

import com.dushantha.entities.EventEntity;
import com.dushantha.util.ReturnData;

public interface OperationDAO {

	/**
	 * @param context
	 * @param eventEntity
	 * @return the operation status and the event id
	 */
	public ReturnData<Long> saveEvent(Context context, EventEntity eventEntity);

	public boolean editEvent(Context context, EventEntity eventEntity);

	public boolean deleteEvent(Context context, Integer eventId);

	public List<EventEntity> getAllEvents(final Context context);
	
	public EventEntity getEvent(final Context context, long eventId);
}
