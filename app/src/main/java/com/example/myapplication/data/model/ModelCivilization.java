package com.example.myapplication.data.model;

public class ModelCivilization {
   private  String Image;
   private String Name;

    public ModelCivilization(String image, String name) {
        this.Image = image;
       this. Name = name;
    }

    public ModelCivilization() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
