package com.dushantha.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dushantha.entities.EventEntity;
import com.dushantha.util.DomainConstants;
import com.dushantha.util.ReturnData;

public class OperationDAOIMPL implements OperationDAO {

	private DatabaseHandler databaseHandler;

	@Override
	public ReturnData<Long> saveEvent(Context context, EventEntity eventEntity) {
		boolean isSaved = false;
		long eventID = DomainConstants.ERROR;
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
		} finally {
			db.endTransaction();
			db.close();
		}

		return new ReturnData<Long>(isSaved, eventID);
	}

	@Override
	public boolean editEvent(Context context, EventEntity eventEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReturnData<Boolean> deleteEvent(Context context, Integer eventId) {
		// TODO Auto-generated method stub
		boolean isDeleted = false;
		databaseHandler = new DatabaseHandler(context);
		SQLiteDatabase db = databaseHandler.getWritableDatabase();
		try {
			db.beginTransaction();
			StringBuilder queryOne = new StringBuilder();
			queryOne.append("DELETE FROM setting \n");
			queryOne.append("WHERE event_id = '" + eventId + "';");

			StringBuilder queryTwo = new StringBuilder();
			queryTwo.append("DELETE FROM sms_call \n");
			queryTwo.append("WHERE event_id = '" + eventId + "';");

			StringBuilder queryThree = new StringBuilder();
			queryThree.append("DELETE FROM event \n");
			queryThree.append("WHERE _id = '" + eventId + "';");

			db.execSQL(queryOne.toString());
			db.execSQL(queryTwo.toString());
			db.execSQL(queryThree.toString());

			db.setTransactionSuccessful();
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
			isDeleted = false;
		} finally {
			db.endTransaction();
			db.close();
		}

		return new ReturnData<Boolean>(isDeleted, isDeleted);
	}

	@Override
	public List<EventEntity> getAllEvents(final Context context) {
		return getEvents(context, DomainConstants.ERROR);
	}

	@Override
	public EventEntity getEvent(Context context, long eventId) {
		List<EventEntity> eventList = getEvents(context, eventId);

		// there will be only one event, take that
		if (eventList != null && eventList.size() == 1) {
			return eventList.get(0);
		}

		return null;
	}

	/**
	 * @param context
	 * @param eventId
	 * @return the events
	 */
	private List<EventEntity> getEvents(Context context, long eventId) {

		List<EventEntity> eventList = new ArrayList<EventEntity>();
		databaseHandler = new DatabaseHandler(context);
		SQLiteDatabase db = databaseHandler.getReadableDatabase();
		StringBuilder query = new StringBuilder("");

		query.append("SELECT e._id AS EventID, \n");// 0
		query.append(" e.event_name AS EventName, \n");// 1
		query.append(" e.dateTime AS DateTime, \n");// 2
		query.append(" s.event_type AS EventType, \n");// 3
		query.append(" s.reminder_type AS ReminderType, \n");// 4
		query.append(" s.priority AS Priority, \n");// 5
		query.append(" s.remind_me_in AS RemindMeIn, \n");// 6
		query.append(" sc.number AS Number, \n");// 7
		query.append(" sc.sms AS Sms \n");// 8
		query.append("FROM event e JOIN setting s \n");
		query.append("ON e._id=s.event_Id LEFT JOIN sms_call sc \n");
		query.append("ON sc.event_Id = e._id");

		String[] whereArgs = null;
		if (eventId != DomainConstants.ERROR) {
			query.append("AND e._id = ?");
			whereArgs = new String[] { String.valueOf(eventId) };
		}

		Cursor cursor = db.rawQuery(query.toString(), whereArgs);

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
					if (cursor.getString(7) != null) {
						eventEntity.getSmsCallEntity().setEventId(
								cursor.getInt(0));
						eventEntity.getSmsCallEntity().setNumber(
								cursor.getString(7));
						eventEntity.getSmsCallEntity().setSms(
								cursor.getString(8));
					}
					eventList.add(eventEntity);
				}
			} while (cursor.moveToNext());
		}

		return eventList;
	}
}
