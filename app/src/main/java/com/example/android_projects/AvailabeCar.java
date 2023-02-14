package com.example.android_projects;

import android.graphics.Bitmap;

public class AvailabeCar {
    String id;
    Bitmap btmImg;
    String brand;
    String seats;
    String transmission;
    String price;

    public String getId() {
        return id;
    }

    public Bitmap getBtmImg() {
        return btmImg;
    }

    public String getBrand() {
        return brand;
    }

    public String getSeats() {
        return seats;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "AvailabeCar{" +
                "id='" + id + '\'' +
                ", btmImg=" + btmImg +
                ", brand='" + brand + '\'' +
                ", seats='" + seats + '\'' +
                ", transmission='" + transmission + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
