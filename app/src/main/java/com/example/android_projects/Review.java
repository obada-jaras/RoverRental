package com.example.android_projects;

import android.graphics.Bitmap;

public class Review {
    Bitmap bmpUserImage;
    String userName;
    int rating;
    String comment;

    public Bitmap getBmpUserImage() {
        return bmpUserImage;
    }

    public String getUserName() {
        return userName;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
