package com.example.android_projects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProfile extends AppCompatActivity {

    static String user_id;
    private EditText name, email, contact, genderEdit, lastnametxt, dob;
    private Button save;
    private TextView back;
    private DatabaseReference rootDatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getReferance();


        user_id = getIntent().getStringExtra("userId");


        rootDatabaseref = FirebaseDatabase.getInstance().getReference();
        rootDatabaseref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String data1 = dataSnapshot.child("users").child(user_id).child("firstName").getValue().toString().trim();
                        String data2 = dataSnapshot.child("users").child(user_id).child("email").getValue().toString().trim();
                        String data3 = dataSnapshot.child("users").child(user_id).child("phoneNumber").getValue().toString().trim();
                        String data4 = dataSnapshot.child("users").child(user_id).child("gender").getValue().toString().trim();
                        String data5 = dataSnapshot.child("users").child(user_id).child("lastName").getValue().toString().trim();
                        String data6 = dataSnapshot.child("users").child(user_id).child("dob").getValue().toString().trim();

                        name.setText(data1);
                        email.setText(data2);
                        contact.setText(data3);
                        genderEdit.setText(data4);
                        lastnametxt.setText(data5);
                        dob.setText(data6);
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateProfile.this, Profile.class);
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
                String value4 = genderEdit.getText().toString().trim();
                String value5 = lastnametxt.getText().toString().trim();
                String value6 = dob.getText().toString().trim();

                rootDatabaseref = FirebaseDatabase.getInstance().getReference();
                rootDatabaseref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        rootDatabaseref.child("users").child(user_id).child("firstName").setValue(value1);
                        rootDatabaseref.child("users").child(user_id).child("email").setValue(value2);
                        rootDatabaseref.child("users").child(user_id).child("phoneNumber").setValue(value3);
                        rootDatabaseref.child("users").child(user_id).child("gender").setValue(value4);
                        rootDatabaseref.child("users").child(user_id).child("lastName").setValue(value5);
                        rootDatabaseref.child("users").child(user_id).child("dob").setValue(value6);


                    }
                });
//                SharedPreferences shp = getSharedPreferences("key",MODE_PRIVATE);
//                SharedPreferences.Editor ed = shp.edit();
//                ed.putString("value1",value1);
//                ed.putString("value2",value2);
//                ed.putString("value3",value3);
//                ed.putString("value4",value4);
//                ed.putString("value5",value5);
//                ed.putString("value6",value6);
//                ed.apply();

            }
        });
    }

    private void getReferance() {
        name = findViewById(R.id.nameEdit);
        email = findViewById(R.id.emailEdit);
        contact = findViewById(R.id.contactEdit);
        genderEdit = findViewById(R.id.genderEdit);
        save = findViewById(R.id.save);
        back = findViewById(R.id.textView7);
        lastnametxt = findViewById(R.id.lastnametxt);
        dob = findViewById(R.id.dobtext);
    }
}