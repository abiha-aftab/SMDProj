package com.example.smd_po_module.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.smd_po_module.dao.DaoAccess;
import com.example.smd_po_module.retrofit;


@Database(entities = {retrofit.class}, version = 1, exportSchema = false)
public abstract class DatabaseClass extends RoomDatabase {

    public abstract DaoAccess daoAccess();

}