package com.example.android_projects;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class MakePaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);
        //initialization :
        final MaterialCardView visa = findViewById(R.id.visaPaymen);
        final MaterialCardView onSite = findViewById(R.id.payOnSite);
        TextView text= findViewById(R.id.PriceAllDaysText);

        //ACTIONS
        onSite.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                onSite.setChecked (true);
                visa.setChecked(false);
            }
        });
        visa.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                visa.setChecked (true);
                onSite.setChecked(false);
            }
        });

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(visa.isChecked()==true){
                    Intent intent = new Intent(MakePaymentActivity.this , creditCardActivity.class);
                    MakePaymentActivity.this.startActivity(intent);
                }
                else if (onSite.isChecked()==true){
                    Intent intent = new Intent(MakePaymentActivity.this , HomePage.class);
                    MakePaymentActivity.this.startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(MakePaymentActivity.this, "PLEASE CHOSE A PAYMENT METHOD", Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}