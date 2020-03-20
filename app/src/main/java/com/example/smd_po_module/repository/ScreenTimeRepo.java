package com.example.smd_po_module.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.example.smd_po_module.database.DatabaseClass;
import com.example.smd_po_module.retrofit;


public class ScreenTimeRepo {

    private String DB_NAME = "app_core_db";
    private DatabaseClass screentimeDatabase;

    public ScreenTimeRepo(Context context) {
        getInstance(context);
    }

    private DatabaseClass getInstance(Context context){
        if (screentimeDatabase!=null){
            return screentimeDatabase;
        }else{
            screentimeDatabase = Room.databaseBuilder(context, DatabaseClass.class, DB_NAME).build();
        }
        return screentimeDatabase;
    }

    public void insertTask(String status, String title, String description) {
        retrofit screentime = new retrofit();
        screentime.setStatus_code(status);
        screentime.setVersion(title);
        screentime.setApp_meta_name(description);
        insertTask(screentime);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertTask(final retrofit screentime) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.e("screentimeRepo", "Inserting!!!! from Background Thread: " + Thread.currentThread().getId());
                screentimeDatabase.daoAccess().insertTask(screentime);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getTask(String id) {
        new AsyncTask<Void, Void, retrofit>() {
            @Override
            protected retrofit doInBackground(Void... voids) {
                Log.e("Retrofit", "getting!!!! from Background Thread: " + Thread.currentThread().getId());
                retrofit r= screentimeDatabase.daoAccess().getTask(id);
                System.out.println("Fetching data ");
                System.out.println("Status code "+r.getStatus_code());
                System.out.println("Version "+r.getVersion());
                return r;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void updateTask(final retrofit screentime) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                screentimeDatabase.daoAccess().updateTask(screentime);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteTask(final retrofit screentime) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                screentimeDatabase.daoAccess().deleteTask(screentime);
                return null;
            }
        }.execute();
    }



}
