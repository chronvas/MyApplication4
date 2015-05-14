package com.example.big.myapplication4;

import android.app.Activity;
import android.content.Context;
import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TreeMap;

/**
 * Created by PC on 09-May-15.
 */
public class FromLocalToDB {
    private   int FromDateYear,FromDateMonth,FromDateDay;
    private   int ToDateYear,ToDateMonth,ToDateDay;
    private   int TimeFromHour,TimeFromMinute;
    private   int TimeToHour,TimeToMinute;
    private   int FromDateSET,ToDateSET,TimeFromSET,TimeToSET,testbool;
    private   int ALLSET;
    private   String why;
    private   int tempFromDate,tempToDate;
    public MySharedPreff mySharedPreff;

/*    public FromLocalToDB(MySharedPreff mySharedPreff) {

        this.mySharedPreff = mySharedPreff;

    }*/

    public  FromLocalToDB(MySharedPreff mySharedPreff){
        this.mySharedPreff = mySharedPreff;
        setInternalValues();
    }


    private void setInternalValues() {
        why = mySharedPreff.getString("whyucalled");
        FromDateYear = mySharedPreff.getInt("FromDateYear");
        FromDateMonth = mySharedPreff.getInt("FromDateMonth");
        FromDateDay = mySharedPreff.getInt("FromDateDay");
        ToDateYear = mySharedPreff.getInt("ToDateYear");
        ToDateMonth = mySharedPreff.getInt("ToDateMonth");
        ToDateDay = mySharedPreff.getInt("ToDateDay");


        TimeFromHour = mySharedPreff.getInt("TimeFromHour");
        TimeFromMinute = mySharedPreff.getInt("TimeFromMinute");
        TimeToHour = mySharedPreff.getInt("TimeToHour");
        TimeToMinute = mySharedPreff.getInt("TimeToMinute");

        //-1 for is not set, 1 is for set
        //fucking booleans are broken
        FromDateSET = mySharedPreff.getInt("FromDateSET");
        ToDateSET = mySharedPreff.getInt("ToDateSET");
        TimeFromSET = mySharedPreff.getInt("TimeFromSET");
        TimeToSET = mySharedPreff.getInt("TimeToSET");

        if (FromDateSET==1&&ToDateSET==1&&TimeFromSET==1&&TimeToSET==1) {
            this.ALLSET = 1;
            Log.e("ALL SET =========", "1111111111111111111" + FromDateSET);
        }
    }
    public Boolean FieldsAllSet(){
        return this.ALLSET == 1;
    }
    public void LogAllOut(){
        Log.e("LOGGG","------------about to begin");
        Log.e("FromDateYear",FromDateYear+"");
        Log.e("FromDateMonth",FromDateMonth+"");
        Log.e("FromDateDay",FromDateDay+"");
        Log.e("ToDateYear",ToDateYear+"");
        Log.e("ToDateMonth",ToDateMonth+"");
        Log.e("ToDateDay", ToDateDay + "");

        Log.e("TimeFromHour", TimeFromHour + "");
        Log.e("TimeFromMinute", TimeFromMinute + "");
        Log.e("TimeToHour", TimeToHour + "");
        Log.e("TimeToMinute", TimeToMinute + "");

        Log.e("FromDateSET", FromDateSET + "");
        Log.e("ToDateSET", ToDateSET + "");
        Log.e("TimeFromSET", TimeFromSET + "");
        Log.e("TimeToSET", TimeToSET + "");
        Log.e("ALLSET",ALLSET+"");
        Log.e("LOGGG", "------------END");
    }
    public boolean CompareDatesFromTo(Locale currentLocale, Context applicationContext){


        if (!FieldsAllSet()){ //If the fields are not set, return showing a toast
            Toast.makeText(applicationContext,"Συμπληρώστε όλα τα πεδία",Toast.LENGTH_LONG).show();
            return false;
        }
        else {              //else contunue to comparison
            Log.e("Fields Set","True. Moving to comparison");

            String FromDateDayString = String.format("%02d", FromDateDay);
            String FromDateMonthString = String.format("%02d", FromDateMonth);
            String ToDateDayString = String.format("%02d", ToDateDay);
            String ToDateMonthString = String.format("%02d", ToDateMonth);
            String from = FromDateYear + "-" + FromDateMonthString + "-" + FromDateDayString + " " + TimeFromHour + ":" + TimeFromMinute;
            String to = ToDateYear + "-" + ToDateMonthString + "-" + ToDateDayString + " " + TimeToHour + ":" + TimeToMinute;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", currentLocale);
            Date strDateFrom = null;
            Date strDateTo = null;
            Boolean ComparisonOK;
            //get dates to String
            try {
                strDateFrom = sdf.parse(from);
                strDateTo = sdf.parse(to);
            } catch (java.text.ParseException e){
               e.printStackTrace();
            }
            //Compare dates
            ComparisonOK = false;
            try {
                ComparisonOK = strDateTo.after(strDateFrom);
            } catch (Exception e) {
               e.printStackTrace();
            }
            if (ComparisonOK) {
                Log.e("DateComparison", "True");
            } else {
                Log.e("DateComparison", "False");
            }

            //LogAllOut();
            if (!ComparisonOK) {
                Toast.makeText(applicationContext, "Λανθασμένες ημερομηνίες", Toast.LENGTH_LONG).show();
                return false;
            }else{
                Toast.makeText(applicationContext, "Σωστός ο παιχτης", Toast.LENGTH_LONG).show();
                return true;

            }
        }
    }



   /* public void CommitInDB(MySharedPreff mySharedPreff, DBDatahandling dbDatahandling){
        ////get
        getFromSharedPref(mySharedPreff);
        String x = mySharedPreff.getString(tempFromDate);

        // check From - To > 0 before commit to DB




        ////commit to DB
        //i dont know what is that
        *//*if (why!=null) {
            try {
                dbDatahandling.InsertNew("asd", 2, 3, 4, 5);
            }catch (Exception e){ Log.e("FromLocalToDb","1",e);}
        }*//*

    }*/
}
