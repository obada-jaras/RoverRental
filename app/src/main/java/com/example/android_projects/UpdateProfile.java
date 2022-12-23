package com.example.android_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateProfile extends AppCompatActivity {

    private  EditText name,email,contact, city ;
    private Button save ;
    private TextView back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getReferance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateProfile.this,Profile.class);
                //Android to open menu activity ..
                startActivity(intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1 = name.getText().toString().trim();
                String value2 = email.getText().toString().trim();
                String value3 = contact.getText().toString().trim();
                String value4 = city.getText().toString().trim();

                SharedPreferences shp = getSharedPreferences("key",MODE_PRIVATE);
                SharedPreferences.Editor ed = shp.edit();
                ed.putString("value1",value1);
                ed.putString("value2",value2);
                ed.putString("value3",value3);
                ed.putString("value4",value4);
                ed.apply();
//
//                Intent intent = new Intent(UpdateProfile.this , Profile.class);
//                startActivity(intent);

            }
        });


//        Update up = new Update();
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                name.setText(up.getName());
//                email.setText(up.getEmail());
//                contact.setText(up.getContact());
//                city.setText(up.getCity());
//            }
//        });


    }


    private void getReferance()
    {
        name = findViewById(R.id.nameEdit);
        email = findViewById(R.id.emailEdit);
        contact = findViewById(R.id.contactEdit);
        city = findViewById(R.id.cityEdit);
        save = findViewById(R.id.save);
        back = findViewById(R.id.textView7);
    }
}