package com.example.big.myapplication4;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.lang.reflect.Field;
import java.sql.Time;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by Big on 5/5/2015.
 */

public class TimePickerClass {

public int FromDate_Month;
    public void setFromDate_Month(int fromDate_Month) {
        FromDate_Month = fromDate_Month;
    }

    public int getFromDate_Month() {
        return FromDate_Month;
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener ,DatePickerDialog.OnDateSetListener {



        public class whocalled{
            public int getwhocalledme(){

                Activity activity = getActivity();
                Context context = activity.getApplicationContext();
/*                SharedPreferences sharedpreferences = c.getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
                String who = sharedpreferences.getString("whyucalled", "no");
                switch (who){*/
                MySharedPreff my = new MySharedPreff(context);
                String f = my.getString("whyucalled");
                switch (f){
                    case "showtime": return 1;
                    case "showdate": return 2;
                    case "showtimeUntil": return 3;
                    case "showdateUntil": return 4;
                    default : return -1;
                }
            }
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            Activity activity = getActivity();
            Context context = activity.getApplicationContext();
            MySharedPreff mySharedPreff = new MySharedPreff(context);
            whocalled w = new whocalled();
            int who = w.getwhocalledme();
            switch (who){
                case 1://create the calendar dialogs in relation to whos called
                    return hideminuteCalendar(new TimePickerDialog(getActivity(), this, hour, minute, true));
                case 2:
                    return hideyearCalendar(new DatePickerDialog(getActivity(), this, year, month, day));
                case 3:
                    return  hideminuteCalendar(new TimePickerDialog(getActivity(), this, hour, minute, true));
                case 4:
                    return hideyearCalendar( new DatePickerDialog(getActivity(), this, year, month, day));
                default: return hideminuteCalendar(new TimePickerDialog(getActivity(), this, hour, minute, true));
            }



            // Create a new instance of TimePickerDialog and return it
            /*TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
            return timePickerDialog;*/
            //return hideyearCalendar( new DatePickerDialog(getActivity(), this, year, month, day));
            //return hideminuteCalendar(new TimePickerDialog(getActivity(), this, hour, minute, true));
        }








/////////////////////////////////////////////////////
        private Dialog hideyearCalendar(DatePickerDialog datePickerDialog) {
            try {
                Field[] datePickerDialogFields = datePickerDialog.getClass().getDeclaredFields();
                for (Field datePickerDialogField : datePickerDialogFields) {
                    if (datePickerDialogField.getName().equals("mDatePicker")) {
                        datePickerDialogField.setAccessible(true);
                        DatePicker datePicker = (DatePicker) datePickerDialogField.get(datePickerDialog);
                        Field datePickerFields[] = datePickerDialogField.getType().getDeclaredFields();
                        for (Field datePickerField : datePickerFields) {
                            if ("mYearPicker".equals(datePickerField.getName()) | "mYearSpinner".equals(datePickerField.getName())) {
                                datePickerField.setAccessible(true);
                                Object dayPicker = new Object();
                                dayPicker = datePickerField.get(datePicker);
                                ((View) dayPicker).setVisibility(View.GONE);
                            }
                        }
                    }

                }
                datePickerDialog.setTitle("Date Available:");
                //datePickerDialog.onDateChanged(null,0,0,0);
                //datePickerDialog.getDatePicker().setCalendarViewShown(false);
                return datePickerDialog;
            } catch (Exception e) {
                Log.e("TAG", "EROOR" + e);
                return datePickerDialog;

            }
        }
//////////////////////////////////////////////////////////////
private Dialog hideminuteCalendar( TimePickerDialog timePickerDialog) {
    return timePickerDialog;
    //To remove minutes, delete return, and uncomment the following:
/*    try { Field[] timePickerDialogFields = timePickerDialog.getClass().getDeclaredFields();
        for (Field timePickerDialogField : timePickerDialogFields) {
            if (timePickerDialogField.getName().equals("mTimePicker")) {
                timePickerDialogField.setAccessible(true);
                TimePicker timePicker = (TimePicker) timePickerDialogField.get(timePickerDialog);
                Field timePickerFields[] = timePickerDialogField.getType().getDeclaredFields();
                for (Field timePickerField : timePickerFields) {
                    if ("mMinutePicker".equals(timePickerField.getName()) | "mMinuteSpinner".equals(timePickerField.getName())) {
                        timePickerField.setAccessible(true);
                        Object minutePicker = new Object();
                        minutePicker = timePickerField.get(timePicker);
                        ((View) minutePicker).setVisibility(View.GONE);


                    }
                }
            }

        }
        timePickerDialog.setTitle("Hour Available From:");

        //timePickerDialog.getTimePicker().setCalendarViewShown(false);
        return timePickerDialog;
    } catch (Exception e) {
        Log.e("TAG", "EROOR" + e);
        return timePickerDialog;

    }*/
}
///////////////////////////////////////////////////////



        public void onDateSet(DatePicker view, int year, int month, int day){
            Activity a = getActivity();
            Context context = a.getApplicationContext();

            MySharedPreff mySharedPreff = new MySharedPreff(context);
            month+=1;

            TextView DateFrom = (TextView)a.findViewById(R.id.FromDate);
            TextView DateTo=(TextView)a.findViewById(R.id.ToDate);

            Button DateFromBtn = (Button)a.findViewById(R.id.BtnFromDate);

            String monthStr = String.format("%02d",month);
            String dayStr = String.format("%02d",day);

            whocalled w = new whocalled();
            switch (w.getwhocalledme()){
                case 2:
                    DateFrom.setText(dayStr + "/" + monthStr + "/" + year);
                    DateFromBtn.setText(dayStr + "/" + monthStr + "/" + year);
                    mySharedPreff.addInt("FromDateMonth", month);
                    mySharedPreff.addInt("FromDateYear",year);
                    mySharedPreff.addInt("FromDateDay", day);
                    //signal FromDate is set
                    mySharedPreff.addInt("FromDateSET",1);
                    break;
                case 4:
                    mySharedPreff.addInt("ToDateMonth",month);
                    mySharedPreff.addInt("ToDateYear",year);
                    mySharedPreff.addInt("ToDateDay", day);
                    //signal ToDate is set
                    mySharedPreff.addInt("ToDateSET",1);
                    DateTo.setText(dayStr + "/" + monthStr + "/" + year);
                    break;
                default:
                    break;
            }
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            Activity a = getActivity();
            Context context = a.getApplicationContext();
            MySharedPreff mySharedPreff = new MySharedPreff(context);
            TextView TimeFrom = (TextView)a.findViewById(R.id.FromTime);
            TextView TimeUntil = (TextView)a.findViewById(R.id.ToTime);
            String hourOfDayStr = String.format("%02d", hourOfDay);
            String minuteStr = String.format("%02d", minute);
            whocalled w = new whocalled();
            switch (w.getwhocalledme()){
                case 1:
                    TimeFrom.setText(hourOfDayStr + " : " + minuteStr);
                    mySharedPreff.addInt("TimeFromHour", hourOfDay);
                    mySharedPreff.addInt("TimeFromMinute", minute);
                    //signal TimeFrom is set
                    mySharedPreff.addInt("TimeFromSET",1);
                    break;
                case 3: ///called by BtnFromTime
                    TimeUntil.setText(hourOfDayStr + " : "+minuteStr);
                    mySharedPreff.addInt("TimeToHour", hourOfDay);
                    mySharedPreff.addInt("TimeToMinute", minute);
                    //signal TimeTo is set
                    mySharedPreff.addInt("TimeToSET",1);

                    break;
                default:
                    break;
            }
        }

    }
}
