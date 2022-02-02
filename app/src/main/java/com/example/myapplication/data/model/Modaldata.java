package com.example.myapplication.data.model;

public class Modaldata {
    private String Image;
    private  String Name;

    public Modaldata(String image, String name) {
        this.Image = image;
        this.Name = name;
    }

    public Modaldata() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
       this.Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
       this. Name = name;
    }
}
