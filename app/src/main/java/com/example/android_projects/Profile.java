package com.example.android_projects;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    private TextView updateInfo;
    private Button userActivity ;
    private TextView name, lname ,email , contact , gender ,dob;
    private DatabaseReference rootDatabaseref;
    static String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getReference();


        SharedPreferences sharedPreferences = getSharedPreferences("userId", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("userId", "");

        userActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,UserActivity.class);
                intent.putExtra("userId", user_id);
                startActivity(intent);


            }
        });
        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,UpdateProfile.class);
                intent.putExtra("userId", user_id);
                startActivity(intent);
            }
        });

        rootDatabaseref = FirebaseDatabase.getInstance().getReference();
        rootDatabaseref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String data1= dataSnapshot.child("users").child(user_id).child("firstName").getValue().toString().trim();
                        String data2= dataSnapshot.child("users").child(user_id).child("email").getValue().toString().trim();
                        String data3= dataSnapshot.child("users").child(user_id).child("phoneNumber").getValue().toString().trim();
                        String data4= dataSnapshot.child("users").child(user_id).child("gender").getValue().toString().trim();
                        String data5= dataSnapshot.child("users").child(user_id).child("lastName").getValue().toString().trim();
                        String data6= dataSnapshot.child("users").child(user_id).child("dob").getValue().toString().trim();

                        name.setText(data1);
                        email.setText(data2);
                        contact.setText(data3);
                        gender.setText(data4);
                        lname.setText(data5);
                        dob.setText(data6);
                    }
                }
            }
        });
//
//        SharedPreferences shp = getSharedPreferences("key",MODE_PRIVATE);
//        String value1 = shp.getString("value1", " ");
//        String value2 = shp.getString("value2", " ");
//        String value3 = shp.getString("value3", " ");
//        String value4 = shp.getString("value4", " ");
//        String value5 = shp.getString("value5", " ");
//        String value6 = shp.getString("value6", " ");
//
//        name.setText(value1);
//        email.setText(value2);
//        contact.setText(value3);
//        gender.setText(value4);
//        lname.setText(value5);
//        dob.setText(value6);


    }

    private void  getReference()
    {
        updateInfo = findViewById(R.id.updateInfo);
        userActivity = findViewById(R.id.userActivity);
        name = findViewById(R.id.fnametxt);
        email = findViewById(R.id.emailtxt);
        contact = findViewById(R.id.phonetxt);
        gender = findViewById(R.id.gendertxt);
        lname = findViewById(R.id.lnametxt);
        dob = findViewById(R.id.dobtxt);




    }
}