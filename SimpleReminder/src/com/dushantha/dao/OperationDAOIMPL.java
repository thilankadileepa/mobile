package com.dushantha.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dushantha.entities.EventEntity;

public class OperationDAOIMPL implements OperationDAO {

	private DatabaseHandler databaseHandler;

	@Override
	public boolean saveEvent(Context context, EventEntity eventEntity) {
		boolean isSaved = false;
		long eventID;
		databaseHandler = new DatabaseHandler(context);
		SQLiteDatabase db = databaseHandler.getWritableDatabase();
		try {
			db.beginTransaction();
			ContentValues values;
			values = new ContentValues();
			values.put("event_name", eventEntity.getEventName());
			values.put("dateTime", eventEntity.getDateTime().toString());
			eventID = db.insert("event", null, values);

			values.clear();

			String number = eventEntity.getSmsCallEntity().getNumber();
			if (number != null && !number.trim().equals("")) {
				values.put("event_id", eventID);
				values.put("number", number);
				values.put("sms", eventEntity.getSmsCallEntity().getSms());
				db.insert("sms_call", null, values);

				values.clear();
			}

			values.put("event_id", eventID);
			values.put("event_type", eventEntity.getSettingsEntity()
					.getEventType());
			values.put("reminder_type", eventEntity.getSettingsEntity()
					.getReminderType());
			values.put("priority", eventEntity.getSettingsEntity()
					.getPriority());
			values.put("remind_me_in", eventEntity.getSettingsEntity()
					.getRemindMeIn());
			db.insert("setting", null, values);
			db.setTransactionSuccessful();
			isSaved = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSaved = false;
		}
		db.endTransaction();
		db.close();
		return isSaved;
	}

	@Override
	public boolean editEvent(Context context, EventEntity eventEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEvent(Context context, Integer eventId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EventEntity> getAllEvents(final Context context) {
		// TODO Auto-generated method stub
		List<EventEntity> eventList = new ArrayList<EventEntity>();
		databaseHandler = new DatabaseHandler(context);
		SQLiteDatabase db = databaseHandler.getReadableDatabase();
		StringBuilder query = new StringBuilder("");
		query.append("SELECT event._id AS EventID, \n");// 0
		query.append(" event.event_name AS EventName, \n");// 1
		query.append(" event.dateTime AS DateTime, \n");// 2
		query.append(" setting.event_type AS EventType, \n");// 3
		query.append(" setting.reminder_type AS ReminderType, \n");// 4
		query.append(" setting.priority AS Priority, \n");// 5
		query.append(" setting.remind_me_in AS RemindMeIn, \n");// 6
		query.append(" sms_call.number AS Number, \n");// 7
		query.append(" sms_call.sms AS Sms \n");// 8
		query.append("FROM event,setting,sms_call \n");
		query.append("WHERE setting.event_id = event._id \n");

		Cursor cursor = db.rawQuery(query.toString(), null);

		if (cursor != null) {
			cursor.moveToFirst();
			do {
				if (cursor.getCount() > 0) {
					EventEntity eventEntity = new EventEntity();
					eventEntity.setEventId(cursor.getInt(0));
					eventEntity.setEventName(cursor.getString(1));
					eventEntity
							.setDateTime(DateTime.parse(cursor.getString(2)));

					eventEntity.getSettingsEntity()
							.setEventID(cursor.getInt(0));
					eventEntity.getSettingsEntity().setEventType(
							cursor.getString(3));
					eventEntity.getSettingsEntity().setReminderType(
							cursor.getString(4));
					eventEntity.getSettingsEntity().setPriority(
							(float) cursor.getDouble(5));
					eventEntity.getSettingsEntity().setRemindMeIn(
							cursor.getInt(6));

					eventEntity.getSmsCallEntity().setEventId(cursor.getInt(0));
					eventEntity.getSmsCallEntity().setNumber(
							cursor.getString(7));
					eventEntity.getSmsCallEntity().setSms(cursor.getString(8));
					eventList.add(eventEntity);
				}
			} while (cursor.moveToNext());
		}

		return eventList;
	}

}
