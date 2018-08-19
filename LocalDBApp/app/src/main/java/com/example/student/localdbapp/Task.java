package com.example.student.localdbapp;

public class Task {
    private  String title;
    private  long id;
    private long timestamp;

    public Task(String title, long id, long timestamp) {
        this.title = title;
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
