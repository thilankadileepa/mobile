package com.dushantha.util;

import java.util.Calendar;


import com.dushantha.presentation.SimpleReminderHomeActiviy.TimeDialogFragmentListener;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.app.DialogFragment;
import android.widget.TimePicker;

public class TimeDialogFragment extends DialogFragment {

	static int _hourOfDay;
	static int _minute;
	static Context mContext;
	static TimeDialogFragmentListener mListener;

	public static TimeDialogFragment getInstance(Context context,
			TimeDialogFragmentListener listener, Calendar now) {
		TimeDialogFragment timeDialogFragment = new TimeDialogFragment();
		
		mContext = context;
		mListener = listener;
		_hourOfDay = now.get(Calendar.HOUR_OF_DAY);
		_minute = now.get(Calendar.MINUTE);
		
		Bundle args = new Bundle();
		args.putString("title", "Set Time");
		timeDialogFragment.setArguments(args);
		return timeDialogFragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		return new TimePickerDialog(mContext,mTimeSetListener,_hourOfDay,_minute,true);
	}

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			_hourOfDay = hourOfDay;
			_minute = minute;
			mListener.updateChangedTime(_hourOfDay, _minute);
		}
	};
	

}
