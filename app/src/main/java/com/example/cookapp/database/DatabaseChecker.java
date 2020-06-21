package com.example.cookapp.database;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.cookapp.MainActivity;
import com.example.cookapp.mainfragments.ListFragment;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DatabaseChecker extends AsyncTask<Void, Void, Void> {

    private int version = DatabaseHelper.DATABASE_VERSION;
    public DatabaseHelper myDB = new DatabaseHelper(null);

    @Override
    public Void doInBackground(Void... voids) {
        try {
            if(DatabaseHelper.DATABASE_VERSION!=version){
                myDB.asyncUpdate();
            }
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
