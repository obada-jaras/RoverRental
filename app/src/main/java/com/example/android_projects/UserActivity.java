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

import java.util.HashMap;

public class UserActivity extends AppCompatActivity {

    static String user_id;
    private Button back;
    private TextView rentedView, feedView, paymentView, paymentxt, feedtxt, rentedtxt, showtxt, offerstxt, offersView;
    private DatabaseReference rootDatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String rtxt = "Your Rented Activity :", ftxt = "Your Feedback Activity :\n\n",
                ptxt = "Your Payment Activity :\n\n", oftxt = "Your Offers Activities";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getReference();

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("userId", "");


        feedtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootDatabaseref = FirebaseDatabase.getInstance().getReference();
                rootDatabaseref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                DataSnapshot dataSnapshot = task.getResult();
                                String data = getValuesAsString((HashMap<String, String>) dataSnapshot.child(user_id).child("activities").child("feedbacks").getValue());
                                showtxt.setText(ftxt);
                                feedView.setText(data);
                            }
                        }
                    }
                });
//                rootDatabaseref.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.exists())
//                        {
//
//                            String data = snapshot.child("feedbacks").getKey().toString();
//                            feedView.setText(data);
//                        }
//
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
                // feedView.setText(ftxt);
                rentedView.setVisibility(View.INVISIBLE);
                feedView.setVisibility(View.VISIBLE);
                paymentView.setVisibility(View.INVISIBLE);
                offersView.setVisibility(View.INVISIBLE);

            }
        });

        paymentxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootDatabaseref = FirebaseDatabase.getInstance().getReference();
                rootDatabaseref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                DataSnapshot dataSnapshot = task.getResult();
                                String data = getValuesAsString((HashMap<String, String>) dataSnapshot.child(user_id).child("activities").child("transactions").getValue());
                                showtxt.setText(ptxt);
                                paymentView.setText(data);
                            }
                        }
                    }
                });

                rentedView.setVisibility(View.INVISIBLE);
                feedView.setVisibility(View.INVISIBLE);
                paymentView.setVisibility(View.VISIBLE);
                offersView.setVisibility(View.INVISIBLE);

            }
        });

        rentedtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootDatabaseref = FirebaseDatabase.getInstance().getReference();
                rootDatabaseref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                DataSnapshot dataSnapshot = task.getResult();
                                String data = getValuesAsString((HashMap<String, String>) dataSnapshot.child(user_id).child("activities").child("rentals").getValue());
                                showtxt.setText(rtxt);
                                rentedView.setText(data);
                            }
                        }
                    }
                });
                rentedView.setVisibility(View.VISIBLE);
                feedView.setVisibility(View.INVISIBLE);
                paymentView.setVisibility(View.INVISIBLE);
                offersView.setVisibility(View.INVISIBLE);


            }
        });

        offerstxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootDatabaseref = FirebaseDatabase.getInstance().getReference();
                rootDatabaseref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                DataSnapshot dataSnapshot = task.getResult();
                                String data = getValuesAsString((HashMap<String, String>) dataSnapshot.child(user_id).child("activities").child("offers").getValue());
                                showtxt.setText(oftxt);
                                offersView.setText(data);
                            }
                        }
                    }
                });
                rentedView.setVisibility(View.INVISIBLE);
                feedView.setVisibility(View.INVISIBLE);
                paymentView.setVisibility(View.INVISIBLE);
                offersView.setVisibility(View.VISIBLE);


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, Profile.class);
                //Android to open menu activity ..
                startActivity(intent);
                finish();
            }
        });


    }

    private void getReference() {
        rentedtxt = findViewById(R.id.rentedtxt);
        paymentxt = findViewById(R.id.paymenttxt);
        feedtxt = findViewById(R.id.feedbacktxt);
        rentedView = findViewById(R.id.rentedView);
        feedView = findViewById(R.id.feedView);
        paymentView = findViewById(R.id.paymentView);
        back = findViewById(R.id.back);
        offersView = findViewById(R.id.offersView);
        showtxt = findViewById(R.id.showtxt);
        offerstxt = findViewById(R.id.offerstxt);

    }


    public String getValuesAsString(HashMap<String, String> map) {
        if (map == null)
            return "";

        StringBuilder sb = new StringBuilder();
        for (String value : map.values()) {
            sb.append(value).append("\n-----------\n");
        }
        return sb.toString();
    }

}