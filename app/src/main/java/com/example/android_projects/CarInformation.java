package com.example.android_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CarInformation extends AppCompatActivity {
    private Button btn_rent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_information);

        setupView();
    }

    private void setupView() {
        btn_rent = findViewById(R.id.btn_rent);

        btn_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MakePaymentActivity.class);
                startActivity(i);
            }
        });
    }
}