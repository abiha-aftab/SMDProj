package com.example.smd_po_module;

public class PlacementOfficer {
    String email;
    String password;
    String qualification;
    String tools;
    String username;
    String pid;
    int experience;

    public PlacementOfficer()
    {

    }
    public PlacementOfficer(String email, String password, String qualification, String tools, String username, String pid, int experience) {
        this.email = email;
        this.password = password;
        this.qualification = qualification;
        this.tools = tools;
        this.username = username;
        this.pid = pid;
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getQualification() {
        return qualification;
    }

    public String getTools() {
        return tools;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPid() {
        return pid;
    }

    public int getExperience() {
        return experience;
    }
}
