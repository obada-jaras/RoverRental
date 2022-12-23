package com.example.android_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class HomePage extends AppCompatActivity {
    private EditText edt_pickupLocation;
    private TextView tf_pickupDate, tf_dropoffDate, tf_warning;
    private Button btn_search;

    private LinearLayout menu_addCar;
    private LinearLayout menu_feedback;
    private LinearLayout menu_home;
    private LinearLayout menu_aboutus;
    private LinearLayout menu_account;


    private LinearLayout card_car1;
    private LinearLayout card_car2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
    }


    private void setupView() {
        edt_pickupLocation = findViewById(R.id.edt_pickupLocation);
        tf_pickupDate = findViewById(R.id.tf_pickupDate);
        tf_dropoffDate = findViewById(R.id.tf_dropoffDate);
        btn_search = findViewById(R.id.btn_search);
        tf_warning = findViewById(R.id.tf_warning);

        menu_addCar = findViewById(R.id.menu_addCar);
        menu_feedback = findViewById(R.id.menu_feedback);
        menu_home = findViewById(R.id.menu_home);
        menu_aboutus = findViewById(R.id.menu_aboutus);
        menu_account = findViewById(R.id.menu_account);

        card_car1 = findViewById(R.id.card_car1);
        card_car2 = findViewById(R.id.card_car2);


        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tf_pickupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(HomePage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        tf_pickupDate.setText(date);
                    }
                },year, month,day);
                dialog.show();

            }
        });

        tf_dropoffDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(HomePage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        tf_dropoffDate.setText(date);
                    }
                },year, month,day);
                dialog.show();

            }
        });


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_pickupLocation.getText().toString().isEmpty()) {
                    tf_warning.setText(R.string.pickupLocationError);
                }

                else if (tf_pickupDate.getText().toString().isEmpty()) {
                    tf_warning.setText(R.string.pickupDateError);
                }

                else if (tf_dropoffDate.getText().toString().isEmpty()) {
                    tf_warning.setText(R.string.dropoffDateError);
                }

                else {
                    String pickupLocation = edt_pickupLocation.getText().toString();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

                    LocalDate pickupDate = LocalDate.parse(tf_pickupDate.getText().toString(), formatter);
                    LocalDate dropoffDate = LocalDate.parse(tf_dropoffDate.getText().toString(), formatter);

                    if (pickupDate.isBefore(LocalDate.now())) {
                        tf_warning.setText(R.string.futureDatesError);
                    }

                    else if (pickupDate.isAfter(dropoffDate)) {
                        tf_warning.setText(R.string.dropDateAfterPickupError);
                    }

                    else {
                        Intent i = new Intent(getApplicationContext(), CarsAvailable.class);
                        startActivity(i);
                    }
                }
            }
        });


        card_car1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CarInformation.class);
                startActivity(i);
            }
        });

        card_car2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CarInformation.class);
                startActivity(i);
            }
        });


        menu_addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), addCarActivity.class);
                startActivity(i);
            }
        });

        menu_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), feedback.class);
                startActivity(i);
            }
        });


        menu_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(i);
            }
        });

        menu_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);
            }
        });
    }
}