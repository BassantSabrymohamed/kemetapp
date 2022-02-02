package com.example.myapplication.data.model;

public class ModelDriver {
    private String CarModel;
    private String Name;
    private String image;

    public ModelDriver(String carModel, String name, String image) {
       this. CarModel = carModel;
        this.Name = name;
        this.image = image;
    }

    public ModelDriver() {
    }


    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String carModel) {
         this.CarModel = carModel;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
       this. Name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
