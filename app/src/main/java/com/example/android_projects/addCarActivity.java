package com.example.android_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.stream.IntStream;

public class addCarActivity extends AppCompatActivity {
    Spinner brandSpin ;
    Spinner yearSpin ;
    Spinner fuelSpin ;
    Spinner tranSpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        brandSpin = findViewById(R.id.carBrandrSpin);
        yearSpin = findViewById(R.id.carYearspin);
        fuelSpin = findViewById(R.id.carFuel);
        tranSpin = findViewById(R.id.carTransmition);

        Button AddCar = findViewById(R.id.AddCar);


        String [] brands ={"BMW","Chevrolet", "Honda","Ford","Volkswagen"};
        String [] fuel ={"Electric","Diesel", "Gasoline / petrol"};
        String[] transmissionString ={"Manual","Automatic"};

        int[] range = IntStream.rangeClosed(2010,2022).toArray();
        String[] yearArray = Arrays.stream(range)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        //
        ArrayAdapter<String> brand = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,brands);
        brandSpin.setAdapter(brand);

        ArrayAdapter<String> objYearArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,yearArray);
        yearSpin.setAdapter(objYearArr);

        ArrayAdapter<String> objFuelArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,fuel);
        fuelSpin.setAdapter(objFuelArr);

        ArrayAdapter<String> tran = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,transmissionString);
        tranSpin.setAdapter(tran);

    }
}