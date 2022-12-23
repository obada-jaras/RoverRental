package com.example.android_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private Button back ;
    private TextView rentedView, feedView , paymentView , paymentxt, feedtxt , rentedtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String rtxt = "Your Rented Activity :\n\n-Rent BMW", ftxt = "Your Feedback Activity :\n\n-Very Good System",
                ptxt = "Your Payment Activity :\n\n-Visa Card" ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getReference();


        feedtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedView.setText(ftxt);
                rentedView.setVisibility(View.INVISIBLE);
                feedView.setVisibility(View.VISIBLE);
                paymentView.setVisibility(View.INVISIBLE);
            }
        });

        paymentxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentView.setText(ptxt);
                rentedView.setVisibility(View.INVISIBLE);
                feedView.setVisibility(View.INVISIBLE);
                paymentView.setVisibility(View.VISIBLE);

            }
        });

        rentedtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rentedView.setText(rtxt);
                rentedView.setVisibility(View.VISIBLE);
                feedView.setVisibility(View.INVISIBLE);
                paymentView.setVisibility(View.INVISIBLE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,Profile.class);
                //Android to open menu activity ..
                startActivity(intent);
                finish();
            }
        });


    }

    private void  getReference()
    {
        rentedtxt = findViewById(R.id.rentedtxt);
        paymentxt = findViewById(R.id.paymenttxt);
        feedtxt = findViewById(R.id.feedbacktxt);
        rentedView = findViewById(R.id.rentedView);
        feedView = findViewById(R.id.feedView);
        paymentView = findViewById(R.id.paymentView);
        back = findViewById(R.id.back);
    }
}