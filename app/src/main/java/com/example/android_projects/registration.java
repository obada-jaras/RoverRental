package com.example.android_projects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class registration extends AppCompatActivity {
    EditText firstname_txt;
    EditText lastname_txt;
    EditText email_txt;
    EditText number_txt;
    EditText password;
    String dob_txt;

    Spinner gender_spn;
    Button registration_btn;
    Button login_page_btn;
    boolean isAllChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setupViews();
    }

    private void setupViews() {
        firstname_txt = findViewById(R.id.firstname_txt);
        lastname_txt = findViewById(R.id.lastname_txt);
        email_txt = findViewById(R.id.email_txt);
        password = findViewById(R.id.password);
        gender_spn = findViewById(R.id.gender_spn);
        number_txt = findViewById(R.id.number_txt);
        dob_txt = "1/1/1900";

        registration_btn = findViewById(R.id.registration_btn);
        login_page_btn = findViewById(R.id.login_page_btn);

        String[] genderChoice = {"Select Your Gender", "Female", "Male"};

        ArrayAdapter<String> gender = new ArrayAdapter<>(this, R.layout.spinner_item, genderChoice);
        gender_spn.setAdapter(gender);


        registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllChecked = checkInformation();

                if (isAllChecked) {
                    writeToDatabase();
                }

            }
        });

        login_page_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registration.this, login.class);
                startActivity(intent);
            }
        });
    }


    boolean isEmail(EditText text) {
        CharSequence email_txt = text.getText().toString();
        return (!TextUtils.isEmpty(email_txt) && Patterns.EMAIL_ADDRESS.matcher(email_txt).matches());
    }


    private boolean checkInformation() {

        if (firstname_txt.getText().toString().isEmpty()) {
            firstname_txt.setError("First Name is Required");
            return false;
        }

        if (lastname_txt.getText().toString().isEmpty()) {
            lastname_txt.setError("Last Name is Required");
            return false;
        }

        if (email_txt.getText().toString().isEmpty()) {
            email_txt.setError("Email is Required");
            return false;
        } else if (!isEmail(email_txt)) {
            email_txt.setError("Please Enter a Valid Username!");
            return false;
        }

        if (number_txt.getText().toString().isEmpty()) {
            number_txt.setError("Phone Number is Required");
            return false;
        }

        if (password.getText().toString().isEmpty() || password.length() < 6) {
            password.setError("Please Enter a Password With at Least 6 Characters");
            return false;
        }

        return true;
    }


    private void writeToDatabase() {
        String uuid = UUID.randomUUID().toString();
        Map<String, Object> user = new HashMap<>();
        user.put("firstName", firstname_txt.getText().toString());
        user.put("lastName", lastname_txt.getText().toString());
        user.put("dob", dob_txt);
        user.put("gender", gender_spn.getSelectedItem().toString());
        user.put("email", email_txt.getText().toString());
        user.put("password", password.getText().toString());
        user.put("phoneNumber", number_txt.getText().toString());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.child(uuid).setValue(user);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userID", uuid);
        editor.apply();

        Intent intent = new Intent(this, login.class);
        startActivity(intent);
        finish();
    }
}

