package com.example.smd_po_module;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity

public class retrofit implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String status_code;
    private String version;
    private String app_meta_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String id) {
        this.status_code = id;
    }

    public String getVersion() {
        return version;
    }

    public void setApp_meta_name(String title) {
        this.app_meta_name = title;
    }

    public String getApp_meta_name() {
        return app_meta_name;
    }

    public void setVersion(String description) {
        this.version = description;
    }
}
