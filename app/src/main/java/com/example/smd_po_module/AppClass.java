package com.example.smd_po_module;

import android.app.Application;

import com.facebook.stetho.Stetho;


public class AppClass extends Application {
    public void onCreate() {
        System.out.println("Hiiii");
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
