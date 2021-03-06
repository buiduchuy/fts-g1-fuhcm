package vn.edu.fpt.fts.drawer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

public class MyDatePickerDialog extends DatePickerDialog {

	private CharSequence title;

	public MyDatePickerDialog(Context context, OnDateSetListener callBack,
			int year, int monthOfYear, int dayOfMonth, CharSequence title) {
		super(context, callBack, year, monthOfYear, dayOfMonth);
		this.title = title;
		setTitle(title);
	}

	@Override
	public void onDateChanged(DatePicker view, int year, int month, int day) {
		super.onDateChanged(view, year, month, day);
		setTitle(title);
	}
}
