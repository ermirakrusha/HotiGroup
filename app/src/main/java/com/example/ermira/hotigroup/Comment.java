package com.example.ermira.hotigroup;


public class Comment {
    int id;
    String name;
    String text;

    public Comment(){}

    public Comment(String text) {
        this.text = text;
    }
    public Comment(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public Comment(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
