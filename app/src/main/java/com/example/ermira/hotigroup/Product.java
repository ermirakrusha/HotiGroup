package com.example.ermira.hotigroup;


import java.io.Serializable;

public class Product implements Serializable{
    private int id;
    private String title, thumbnailUrl,description;

    public Product(){}

    public Product(int id, String thumbnailUrl, String title, String description) {
        this.description = description;
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
    }

    public Product(String thumbnailUrl, String title, String description) {
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return id+"";
    }
}
