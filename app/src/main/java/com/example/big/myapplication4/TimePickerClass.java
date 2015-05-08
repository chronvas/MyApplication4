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
 * Created by Big on 5/5/2015. OnTimeChanged must go
 */

public class TimePickerClass {

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener ,DatePickerDialog.OnDateSetListener {


        public class whocalled{
            public int getwhocalledme(){
                Activity  a = getActivity();
                Context c = a.getApplicationContext();
                SharedPreferences sharedpreferences = c.getApplicationContext().getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
                String who = sharedpreferences.getString("whyucalled", "no");
                int result=-1;
                String ShowTime="showtime";
                String ShowDate="showdate";
                String ShowTimeUntil="showtimeUntil";
                String ShowDateUntil="showdateUntil";
                if (who==null) return -1;
                if( who.equals(ShowTime) )  {
                    return 1;
                }
                else if(who.equals(ShowDate)) {
                    return 2;
                }
                else if (who.equals(ShowTimeUntil)) {
                    return 3;
                }
                else if (who.equals(ShowDateUntil)){
                    return 4;
                }
                return result;

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

            Activity a = getActivity();



            whocalled w = new whocalled();
            int who = w.getwhocalledme();
            if (who==-1) {
                Toast.makeText(getActivity(), "-1", Toast.LENGTH_LONG).show();
                return hideminuteCalendar(new TimePickerDialog(getActivity(), this, hour, minute, true));
            }
            else if (who==1){
                return hideminuteCalendar(new TimePickerDialog(getActivity(), this, hour, minute, true));
            }
            else if (who==2){
                return hideyearCalendar( new DatePickerDialog(getActivity(), this, year, month, day));
            }
            else if (who==3){
                return  hideminuteCalendar(new TimePickerDialog(getActivity(), this, hour, minute, true));
            }
            else if (who==4){
                return hideyearCalendar( new DatePickerDialog(getActivity(), this, year, month, day));
            }
            else {
                return hideminuteCalendar(new TimePickerDialog(getActivity(), this, hour, minute, true));
            }
            /*switch (who){
                case  -1 :
                    return hideminuteCalendar(new TimePickerDialog(getActivity(), this, hour, minute, true));
                    Toast.makeText(getActivity(), "NOOO",Toast.LENGTH_SHORT).show();
                    break;
                case 1: //TIME
                    Toast.makeText(getActivity(), "time",Toast.LENGTH_SHORT).show();
                    break;
                case 2: //DATE
                    Toast.makeText(getActivity(), "date",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getActivity(), "DEF",Toast.LENGTH_SHORT).show();
                    break;
            }*/



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









        public void onDateSet(DatePicker view, int year, int month, int what){
            Activity a = getActivity();
            Toast.makeText(a, year + " onDateSet " + month, Toast.LENGTH_SHORT).show();

            month+=1;
            TextView DateFrom = (TextView)a.findViewById(R.id.FromDate);
            TextView DateTo=(TextView)a.findViewById(R.id.ToDate);
            whocalled w = new whocalled();
            switch (w.getwhocalledme()){
                case 2:
                    DateFrom.setText(what + "/"+month + "/" +year);
                    break;
                case 4:
                    DateTo.setText(what + "/"+month + "/" +year);
                    break;
                default:
                    break;
            }
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            //Toast.makeText(getActivity(), " onTimeSet, hour= " + hourOfDay, Toast.LENGTH_SHORT).show();
            Activity a = getActivity();
            TextView TimeFrom = (TextView)a.findViewById(R.id.FromTime);
            TextView TimeUntil = (TextView)a.findViewById(R.id.ToTime);
            //TimeFrom.setText(hourOfDay + " : "+minute);

            whocalled w = new whocalled();
            switch (w.getwhocalledme()){
                case 1:
                    TimeFrom.setText(hourOfDay + " : "+minute);
                    break;
                case 3: ///called by BtnFromTime
                    TimeUntil.setText(hourOfDay + " : "+minute);
                    break;
                default:
                    break;
            }
            Toast.makeText(getActivity(), " adsasdonTimeSet, hour= " + hourOfDay, Toast.LENGTH_SHORT).show();
        }

    }
}
