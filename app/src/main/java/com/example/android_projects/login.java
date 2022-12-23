package com.example.android_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {

    private EditText username_txt;
    private EditText pw_txt;
    private Button login_btn;
    private Button register_btn;
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

                if(isAllChecked) {
                    Intent intent = new Intent(login.this, HomePage.class);
                    startActivity(intent);
                    finish();
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

    boolean isEmail (EditText text){
        CharSequence username_txt = text.getText().toString();
        return (!TextUtils.isEmpty(username_txt) && Patterns.EMAIL_ADDRESS.matcher(username_txt).matches());
    }


    private boolean checkUsername () {

        if (username_txt.getText().toString().isEmpty()) {
            username_txt.setError("Username is Required");
            return false;
        }
        else if (isEmail(username_txt) == false){
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




