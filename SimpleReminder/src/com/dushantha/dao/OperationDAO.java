package com.dushantha.dao;

import java.util.List;

import android.content.Context;

import com.dushantha.entities.EventEntity;

public interface OperationDAO {
	public boolean saveEvent(Context context,EventEntity eventEntity);
	public boolean editEvent(Context context,EventEntity eventEntity);
	public boolean deleteEvent(Context context,Integer eventId);
	public List<EventEntity> getAllEvents(final Context context);
}
