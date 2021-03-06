package com.nurdcoder.customerappdemo.other;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by ZOARDER AL MUKTADIR on 11/21/2016.
 */

public class DateTimeUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "hh:mm a";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd, hh:mm a";
    public static final String DURATION_FORMAT = "y M d H m";
    private static final String[] DURATION_FORMAT_TOKENS = {" Year", " Month", " Day", " Hour", " Minute", " Second", " Millisecond"};

    public static String getDateTimeFromMilliSeconds(long milliSeconds, String dateTimeFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat);
        formatter.setLenient(false);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static long getMilliSecondsFromDateTime(String dateTime, String dateTimeFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat);
        formatter.setLenient(false);

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(formatter.parse(dateTime));
//            calendar.set(Calendar.SECOND, 59);
//            calendar.set(Calendar.MILLISECOND, 999);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }

    public static void setDateFromDatePicker(final Activity activity, int year, int month, int day, final String dateFormat, final TextView textView) {

        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonthOfYear, int selectedDayOfMonth) {
                        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(selectedYear, selectedMonthOfYear, selectedDayOfMonth);
                        Date date = new Date(calendar.getTimeInMillis());
                        textView.setText(formatter.format(date));
                    }
                }, year, month, day);
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                CommonUtils.hideSoftInputMode(activity, textView);
            }
        });
        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                CommonUtils.hideSoftInputMode(activity, textView);
            }
        });
        datePickerDialog.setTitle("Select Your Birth Date");
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        datePickerDialog.show();
    }

    public static void setTimeFromTimePicker(final Activity activity, int hour, int minute, final int second, final String timeFormat, final TextView textView) {
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                calendar.set(Calendar.MINUTE, selectedMinute);
                calendar.set(Calendar.SECOND, second);
                Time time = new Time(calendar.getTimeInMillis());
                textView.setText(formatter.format(time));
            }
        }, hour, minute, false);//Yes 24 hour time
        timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                CommonUtils.hideSoftInputMode(activity, textView);
            }
        });
        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                CommonUtils.hideSoftInputMode(activity, textView);
            }
        });
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public static String getDurationFromMilliSecondse(long milliSeconds, String durationFormat) {
        String durationString = DurationFormatUtils.formatDuration(milliSeconds, durationFormat);
        StringTokenizer stringTokenizer = new StringTokenizer(durationString);

        durationString = "";
        for (int i = 0; stringTokenizer.hasMoreElements(); i++) {
            int n = Integer.parseInt(stringTokenizer.nextToken());
            if (n > 0) {
                if (!durationString.isEmpty()) {
                    durationString += ", ";
                }
                durationString = durationString + n + DURATION_FORMAT_TOKENS[i];
                if (n > 1) {
                    durationString += "s";
                }
            }
        }
        return durationString;
    }

    public static boolean isValidDateTimeFormat(String dateTime, String dateTimeFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat);
        formatter.setLenient(false);

        Calendar calendar = Calendar.getInstance();
        Boolean mark = false;
        try {
            calendar.setTime(formatter.parse(dateTime));
            mark = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mark;
    }
}
