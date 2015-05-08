package com.example.big.myapplication4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


import android.app.Activity;
import android.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends Activity {
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button setBtn = (Button)findViewById(R.id.button);

        if (savedInstanceState!=null){
            //has some data inside. place them in the appropriate fields
        }
        //final Calendar c = Calendar.getInstance();
         //int min = c.get(Calendar.YEAR);
        //String g = min+"";
        //TextView ShowDate = (TextView)findViewById(R.id.textView);
        //ShowDate.setText(g);



        sharedpreferences = getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("whyucalled","");
        editor.apply(); //anti gia commit()

    }
    //called by the pick date putton
    public void showTimePickerDialog(View v) {
        sharedpreferences = getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("whyucalled","showtime");
        editor.apply(); //anti gia commit()
        DialogFragment newFragment = new TimePickerClass.TimePickerFragment();

        newFragment.show(getFragmentManager(), "timePicker");

    }
    public void showDatePickerDialog(View v) {
        sharedpreferences = getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("whyucalled","showdate");
        editor.apply(); //anti gia commit()
        DialogFragment newFragment = new TimePickerClass.TimePickerFragment();

        newFragment.show(getFragmentManager(), "timePicker");
    }
    public void showTimePickerDialogUntil(View v) {
        sharedpreferences = getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("whyucalled","showtimeUntil");
        editor.apply(); //anti gia commit()
        DialogFragment newFragment = new TimePickerClass.TimePickerFragment();

        newFragment.show(getFragmentManager(), "timePicker");

    }
    public void showDatePickerDialogUtil(View v) {
        sharedpreferences = getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("whyucalled","showdateUntil");
        editor.apply(); //anti gia commit()
        DialogFragment newFragment = new TimePickerClass.TimePickerFragment();

        newFragment.show(getFragmentManager(), "timePicker");
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
}
