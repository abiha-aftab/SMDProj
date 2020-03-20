package com.example.smd_po_module.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.smd_po_module.retrofit;

import java.util.List;


@Dao
public interface DaoAccess {

    @Insert
    Long insertTask(retrofit retro);

    @Query("SELECT * FROM retrofit")
    List<retrofit> fetchAllTasks();


    @Query("SELECT * FROM retrofit WHERE status_code =:taskId")
    retrofit getTask(String taskId);

    @Update
    void updateTask(retrofit retro);


    @Delete
    void deleteTask(retrofit retro);
}