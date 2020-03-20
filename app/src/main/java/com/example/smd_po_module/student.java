package com.example.smd_po_module;

import java.util.List;

public class student {
    String name;
    String email;

    student(){

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    String password;
    String phoneNo;
    String gender;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public String getSkills() {
        return skills;
    }

    public String getDegree() {
        return degree;
    }

    public String getGpa() {
        return gpa;
    }

    public int getBatch() {
        return batch;
    }

    public String getExperience() {
        return experience;
    }

    String skills;
    String degree;
    String gpa;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    String id;

    public student(String id,String name, String email, String password, String phoneNo, String gender, String skills, String degree, String gpa, int batch, String experience) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.skills = skills;
        this.degree = degree;
        this.gpa = gpa;
        this.batch = batch;
        this.experience = experience;
    }

    int batch;
    String experience;
}
