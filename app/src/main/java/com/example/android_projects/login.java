package com.example.android_projects;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class login extends AppCompatActivity {

    EditText username_txt;
    EditText pw_txt;
    Button login_btn;
    Button register_btn;
    boolean isAllChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupViews();
    }

    private void setupViews() {
        username_txt = findViewById(R.id.username_txt);
        pw_txt = findViewById(R.id.pw_txt);
        register_btn = findViewById(R.id.register_btn);
        login_btn = findViewById(R.id.login_btn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllChecked = checkUsername();

                if (isAllChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference userRef = database.getReference("users");

                    userRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String userId = dataSnapshot.getKey();

                                String email = dataSnapshot.child("email").getValue(String.class);
                                String password = dataSnapshot.child("password").getValue(String.class);

                                if (username_txt.getText().toString().equals(email)
                                        && pw_txt.getText().toString().equals(password)) {
                                    Intent intent = new Intent(login.this, HomePage.class);
                                    intent.putExtra("userId", userId);

                                    SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("userId", userId);
                                    editor.apply();

                                    startActivity(intent);
                                    finish();
                                }
                            }
//                            Toast.makeText(login.this, "Account Doesn't Exist", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(login.this, "Failed to Get Data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, registration.class);
                startActivity(intent);
                finish();
            }
        });

    }

    boolean isEmail(EditText text) {
        CharSequence username_txt = text.getText().toString();
        return (!TextUtils.isEmpty(username_txt) && Patterns.EMAIL_ADDRESS.matcher(username_txt).matches());
    }


    private boolean checkUsername() {

        if (username_txt.getText().toString().isEmpty()) {
            username_txt.setError("Username is Required");
            return false;
        } else if (isEmail(username_txt) == false) {
            username_txt.setError("Please Enter a Valid Username!");
            return false;
        }

        if (pw_txt.getText().toString().isEmpty() || pw_txt.length() < 6) {
            pw_txt.setError("Password is Required");
            return false;
        }

        return true;
    }


}





