package com.example.big.myapplication4;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.content.Context;
import android.app.Activity;
import android.app.DialogFragment;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

/**
 * Created by PC on 08-May-15.
 */
public class DBDatahandling extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String DATESANDTIMES_TABLE_NAME = "datesandtimes";
    public static final String DATESANDTIMES_COLUMN_ID = "id";
    public static final String DATESANDTIMES_COLUMN_NAME="name";
    public static final String DATESANDTIMES_COLUMN_DATEFROM="DateFrom";
    public static final String DATESANDTIMES_COLUMN_DATETO="DateTo";
    public static final String DATESANDTIMES_COLUMN_TIMEFROM="TimeFrom";
    public static final String DATESANDTIMES_COLUMN_TIMETO="TimeTo";

    public DBDatahandling(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table datesandtimes" +
                        "(id integer primary key,name text, DateFrom integer, DateTo integer, TimeFrom integer, TimeTo integer"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS datesandtimes");
        onCreate(db);
    }

    public boolean InsertNew(String name, int dateFrom, int dateTo, int timeFrom, int timeTo){
        //User specific field, for the user to be able to see only his stuff, not all the table
        SQLiteDatabase sq= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //note: id is auto incremental
        values.put("name",name);
        values.put("DateFrom",dateFrom);
        values.put("DateTo",dateTo);
        values.put("TimeFrom", timeFrom);
        try {
            sq.insert("datesandtimes", null, values);
        }catch (Exception e){
            Log.e("DBErr","DbErr",e);return false;}
        return true;
    }
    public Cursor getDataById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }
    public int countNumberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DATESANDTIMES_TABLE_NAME);
        return numRows;
    }
    public ArrayList getAllNames() {
        ArrayList array_list = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from"+DATESANDTIMES_TABLE_NAME, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(DATESANDTIMES_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public Integer deleteContactById (Integer id)    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DATESANDTIMES_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
}










