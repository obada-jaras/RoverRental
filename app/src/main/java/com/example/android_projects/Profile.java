package com.example.android_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private TextView updateInfo;
    private Button userActivity ;
    private TextView name, email , contact , city ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getReference();
        userActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,UserActivity.class);
                startActivity(intent);
            }
        });
        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,UpdateProfile.class);
                startActivity(intent);
            }
        });

        SharedPreferences shp = getSharedPreferences("key",MODE_PRIVATE);
        String value1 = shp.getString("value1", " ");
        String value2 = shp.getString("value2", " ");
        String value3 = shp.getString("value3", " ");
        String value4 = shp.getString("value4", " ");

        name.setText(value1);
        email.setText(value2);
        contact.setText(value3);
        city.setText(value4);


    }

    private void  getReference()
    {
        updateInfo = findViewById(R.id.updateInfo);
        userActivity = findViewById(R.id.userActivity);
        name = findViewById(R.id.nametxt);
        email = findViewById(R.id.emailtxt);
        contact = findViewById(R.id.phonetxt);
        city = findViewById(R.id.citytxt);



    }
}