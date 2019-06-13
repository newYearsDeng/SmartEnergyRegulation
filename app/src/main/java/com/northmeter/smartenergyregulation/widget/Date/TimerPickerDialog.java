package com.northmeter.smartenergyregulation.widget.Date;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

/**
 */
public class TimerPickerDialog extends DatePickerDialog {
    private boolean isShowDay;
    private boolean isShowYear;
    private boolean isShowMonth;

    public TimerPickerDialog(Context context, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth, boolean isShowDay
            , boolean isShowMonth, boolean isShowYear) {
        super(context, AlertDialog.THEME_HOLO_LIGHT, listener, year, monthOfYear, dayOfMonth);
        this.isShowDay=isShowDay;
        this.isShowMonth=isShowMonth;
        this.isShowYear=isShowYear;

        if(!isShowDay){
            ((ViewGroup) ((ViewGroup) this.getDatePicker().getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
            this.setTitle(year + "年" + (monthOfYear + 1) + "月");

        }
        if(!isShowMonth){
            ((ViewGroup) ((ViewGroup) this.getDatePicker().getChildAt(0)).getChildAt(0)).getChildAt(1).setVisibility(View.GONE);
            this.setTitle(year + "年");
        }

        if(!isShowYear){
            ((ViewGroup) ((ViewGroup) this.getDatePicker().getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
            this.setTitle( (monthOfYear + 1)+ "月" + dayOfMonth+"日");
        }

    }
    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
        if(!isShowDay){
            this.setTitle(year + "年" + (month+1) + "月");
        }
        if(!isShowMonth){
            this.setTitle(year + "年");
        }
        if(!isShowYear){
            this.setTitle( (month+1) + "月"+day+"日");
        }
    }
}
