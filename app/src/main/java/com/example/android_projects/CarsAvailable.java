package com.example.android_projects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CarsAvailable extends AppCompatActivity {
    ConstraintLayout card_car1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_available);

        setupView();
    }

    private void setupView() {
        card_car1 = findViewById(R.id.card_car1);

        card_car1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CarInformation.class);
                startActivity(i);
            }
        });
    }
}