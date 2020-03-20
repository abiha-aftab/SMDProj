package com.example.smd_po_module;

public class admin {
    String aid;
    String username;
    String password;

    public admin(String aid, String username, String password) {
        this.aid = aid;
        this.username = username;
        this.password = password;
    }
    admin(){

    }

    public String getAid() {
        return aid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
