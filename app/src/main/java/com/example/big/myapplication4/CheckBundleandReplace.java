package com.example.big.myapplication4;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by PC on 13-May-15.
 */
public class CheckBundleandReplace {
    public void Replace(MySharedPreff mySharedPreff){
        List<String> ListwhtiWhatExists = CheckWhatExists();
    }

    private ArrayList<String> CheckWhatExists() {
        ArrayList<String> myList = new ArrayList<String>();
        Log.e("adsaa","adsada");
        myList.add("asdda");
        String asda = myList.get(1);
        Log.e(asda,"LIST"+asda);
        return null;
    }
}
