package com.java.Model;


import java.util.Date;

public class Activity {
    private int id;
    private String username;
    private String date;
    private String activity;
    private boolean completed;


    public Activity( String username, String date, String activity)
    {
        id = -1;
        this.username = username;
        this.date = date;
        this.activity = activity;
        completed = false;
    }

    public Activity( int id, String username, String date, String activity, boolean completed)
    {
        this.id = id;
        this.username = username;
        this.date = date;
        this.activity = activity;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
