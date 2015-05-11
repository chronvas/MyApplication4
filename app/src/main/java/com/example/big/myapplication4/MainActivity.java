package com.example.big.myapplication4;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
//gia to locale
//Locale currentLocale = getResources().getConfiguration().locale;
public class MainActivity extends Activity {
   /* MySharedPreff mySharedPreff;
    SharedPreferences sharedpreferences;
    FromLocalToDB fromLocalToDB;*/




    public static MySharedPreff mySharedPreff = new MySharedPreff();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button setBtn = (Button)findViewById(R.id.button);
        mySharedPreff.setContext(getApplicationContext());
        if (savedInstanceState!=null){
            //has some data inside. place them in the appropriate fields
        }else{
            Log.e("savedInstanceState", "was NULL, SharedPref.. data cleaned by Main");
            mySharedPreff.CleanAllPreviousData();
        }
        //final Calendar c = Calendar.getInstance();
         //int min = c.get(Calendar.YEAR);
        //String g = min+"";
        //TextView ShowDate = (TextView)findViewById(R.id.textView);
        //ShowDate.setText(g);



/*
        sharedpreferences = getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("whyucalled", "");
        editor.apply(); //anti gia commit()*/
        MySharedPreff mySharedPreff = new MySharedPreff(getApplicationContext());

        mySharedPreff.addString("whyucalled","");



    }
    //called by the pick date putton
    public void showTimePickerDialog(View v) {
        //PROIGOUMENO
/*        sharedpreferences = getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("whyucalled","showtime");
        editor.apply(); //anti gia commit()*/
        // H ALLIOS
        //new MySharedPreff(getApplicationContext()).addString("whyucalled", "showtime");
        mySharedPreff.addString("whyucalled", "showtime");
        DialogFragment newFragment = new TimePickerClass.TimePickerFragment();

        newFragment.show(getFragmentManager(), "timePicker");

    }
    public void showDatePickerDialog(View v) {
       /* sharedpreferences = getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("whyucalled","showdate");
        editor.apply(); */
        mySharedPreff.addString("whyucalled","showdate");
        DialogFragment newFragment = new TimePickerClass.TimePickerFragment();

        newFragment.show(getFragmentManager(), "timePicker");
    }
    public void showTimePickerDialogUntil(View v) {
        /*sharedpreferences = getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("whyucalled","showtimeUntil");
        editor.apply(); */
        mySharedPreff.addString("whyucalled","showtimeUntil");
        DialogFragment newFragment = new TimePickerClass.TimePickerFragment();

        newFragment.show(getFragmentManager(), "timePicker");

    }
    public void showDatePickerDialogUtil(View v) {
        /*sharedpreferences = getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("whyucalled","showdateUntil");
        editor.apply();  */
        mySharedPreff.addString("whyucalled","showdateUntil");
        DialogFragment newFragment = new TimePickerClass.TimePickerFragment();

        newFragment.show(getFragmentManager(), "timePicker");
    }
    public void SetTheData(View v){
        Locale currentLocale = getResources().getConfiguration().locale;
        /*SharedPreferences sharedpreferences = new MySharedPreff.getSharedPref();*/
        FromLocalToDB fromLocalToDB =  new FromLocalToDB(mySharedPreff);
        //fromLocalToDB.CompareDatesFromTo(currentLocale);
        //FromLocalToDB fromLocalToDB = new FromLocalToDB(mySharedPreff);
        //fromLocalToDB.getFromSharedPref(mySharedPreff);
        //go.getFromSharedPref(mySharedPreff);
        //Call
        fromLocalToDB.ShowToastsforDataMissing(getApplicationContext());
        fromLocalToDB.CompareDatesFromTo(currentLocale);







    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        super.onSaveInstanceState(savedInstanceState);
    }

}
