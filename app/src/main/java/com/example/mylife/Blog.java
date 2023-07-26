package com.example.mylife;

public class Blog {
    private int id;
    private String title;
    private String date;
    private String content;

    public Blog() {}

    public Blog(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public Blog(int id, String title, String date, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    // Getters and setters for all the fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


