package com.example.myapplication.data.model;

import android.widget.Button;

public class ModelTourGuide {
    private String image;
    private String language;
    private String name;


    public ModelTourGuide(String image, String language, String name) {
        this.image = image;
        this.language = language;
        this.name = name;
    }

    public ModelTourGuide() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}