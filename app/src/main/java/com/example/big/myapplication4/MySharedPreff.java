package com.example.big.myapplication4;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.sql.SQLData;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by PC on 08-May-15.
 */

public class MySharedPreff extends Activity {
    /*
    The fucking 1 in int type can not be used
    also the true in booleans
    some fucking magic android way the ints are set to 1 by default
     */
    //store the names of each item (String or Int) that has been added(by addString and addInt methods)
    public Context context;
    /*public SharedPreferences getSharedPref(){
        return sharedpreferences;
    }*/

    public void  setContext(Context context){
        this.context=context;

    }
    public MySharedPreff(){

    }
    public MySharedPreff(Context context) {
        this.context = context;
    }

    public void CleanAllPreviousData(){
        SharedPreferences sharedpreferences = context.getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }
    public boolean CheckContains(String Name){
        SharedPreferences sharedpreferences = context.getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.apply();
        return sharedpreferences.contains(Name);

    }

    public void addString(String StrKey,String StrVALUE){
        SharedPreferences sharedpreferences = context.getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString( StrKey , StrVALUE );
        editor.apply();
    }
    public String getString(String StrKey){

        SharedPreferences sharedpreferences = context.getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        return sharedpreferences.getString(StrKey,"");
    }
    public void addInt(String IntsName, int value){
        SharedPreferences sharedpreferences = context.getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(IntsName, value);
        editor.apply();
    }
    public int getInt(String IntsName){
        SharedPreferences sharedpreferences = context.getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);
        if(sharedpreferences.contains(IntsName)){
            return sharedpreferences.getInt(IntsName,0);
        }
        else return -1;
    }
    public void addBool(String BoolsName, boolean value){
        SharedPreferences sharedpreferences = context.getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(BoolsName, value);
        editor.apply();
    }
    public boolean getBool(String BoolsName){
        SharedPreferences sharedpreferences = context.getSharedPreferences("sharedpref", Context.MODE_MULTI_PROCESS);

        return sharedpreferences.getBoolean(BoolsName, false);
    }
}
