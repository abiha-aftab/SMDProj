package com.example.smd_po_module;

/**
 * Created by Belal on 10/2/2017.
 */

public class Hero {
    public String getStatus_code() {
        return status_code;
    }

    public String getVersion() {
        return version;
    }

    public String getApp_meta_name() {
        return app_meta_name;
    }

    private String status_code;
    private String version;
    private String app_meta_name;

    public Hero(String status_code, String version, String app_meta_name) {
        this.status_code = status_code;
        this.version = version;
        this.app_meta_name = app_meta_name;
    }
}
