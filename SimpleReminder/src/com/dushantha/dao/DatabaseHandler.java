package com.dushantha.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final String LOG = "DatabaseHelper";
	private static final int DATABASE_VERSION = 4;
	private static final String DATABASE_NAME = "SimpleReminder";
	
    private static final String CREATE_TABLE_EVENT = "CREATE TABLE event ( " +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "event_name TEXT, "+
            "dateTime DATETIME )";    
    
    private static final String CREATE_TABLE_SMS_CALL = "CREATE TABLE sms_call ( " +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "event_id INTEGER, "+
            "number TEXT DEFAULT NULL, "+
            "sms TEXT DEFAULT NULL, "+
            "FOREIGN KEY(event_id) REFERENCES event(_id))";
    
    private static final String CREATE_TABLE_SETTING = "CREATE TABLE setting ( " +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "event_id INTEGER, "+
            "event_type TEXT DEFAULT NULL, "+
            "reminder_type TEXT DEFAULT NULL, "+
            "priority DOUBLE DEFAULT 0.0, "+
            "remind_me_in INTEGER DEFAULT 0, "+
            "FOREIGN KEY(event_id) REFERENCES event(_id))";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		  db.execSQL(CREATE_TABLE_EVENT);
	      db.execSQL(CREATE_TABLE_SMS_CALL);
	      db.execSQL(CREATE_TABLE_SETTING);
		
	}
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		if(!db.isReadOnly()){
		db.execSQL("PRAGMA foreign_keys = ON;");
		}
		super.onOpen(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		 db.execSQL("DROP TABLE IF EXISTS " + "setting");
	     db.execSQL("DROP TABLE IF EXISTS " + "sms_call");
	     db.execSQL("DROP TABLE IF EXISTS " + "event");
	     
	     onCreate(db);
		
	}

}
