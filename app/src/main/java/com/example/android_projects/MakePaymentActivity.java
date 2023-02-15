package com.example.android_projects;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MakePaymentActivity extends AppCompatActivity {
    private static final long ONE_MEGABYTE = 1024 * 1024 * 5;
    static String user_id;
    TextView price_day;
    TextView totalprice;
    TextView AlldaysPrice;
    double Fees = 0.65;
    TextView location;
    TextView dates;
    TextView finalFee;
    ImageView carImg;
    Double FinalPrice;
    String CarBrand;
    //from SHARED PREF
    String carId;
    String pickUp;
    String Dropoff;
    private DatabaseReference mDatabase;
    private FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);
        //initialization :
        final MaterialCardView visa = findViewById(R.id.visaPaymen);
        final MaterialCardView onSite = findViewById(R.id.payOnSite);
        AlldaysPrice = findViewById(R.id.PriceAllDaysText);
        totalprice = findViewById(R.id.totalPrice);
        price_day = findViewById(R.id.PriceDayText);
        location = findViewById(R.id.locationText);
        dates = findViewById(R.id.datesText);
        finalFee = findViewById(R.id.FeesText);
        carImg = findViewById(R.id.imageview2);
        rootNode = FirebaseDatabase.getInstance();


        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("userId", "");

        SharedPreferences sharedPreferencesDates = getSharedPreferences("dates", Context.MODE_PRIVATE);
        pickUp = sharedPreferencesDates.getString("fromDate", "");
        Dropoff = sharedPreferencesDates.getString("toDate", "");


        carId = getIntent().getStringExtra("carId");


        readCarData(carId);


        //ACTIONS
        onSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSite.setChecked(true);
                visa.setChecked(false);

            }
        });
        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visa.setChecked(true);
                onSite.setChecked(false);
            }
        });


        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visa.isChecked() == true) {
                    Intent intent = new Intent(MakePaymentActivity.this, creditCardActivity.class);
                    startActivityForResult(intent, 1);
                } else if (onSite.isChecked() == true) {
                    Toast.makeText(MakePaymentActivity.this, "Car is now preserved for you :)", Toast.LENGTH_LONG).show();
                    FirBaseAddBookings("onSite");
                    FirBaseAddTrans("OnSite");
                    FirBaseAddRentals();
                    Intent intent = new Intent(MakePaymentActivity.this, HomePage.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MakePaymentActivity.this, "PLEASE CHOSE A PAYMENT METHOD", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getExtras().getInt("complete");
                if (result == 1) {
                    FirBaseAddBookings("visa");
                    FirBaseAddTrans("visa");
                    FirBaseAddRentals();
                    Intent intent = new Intent(MakePaymentActivity.this, UserActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(MakePaymentActivity.this, "RENT CAR IS CANCELLED", Toast.LENGTH_LONG);
                }


            }
        }
    }

    private void FirBaseAddBookings(String paymentMethod) {
        mDatabase = rootNode.getReference("cars").child(carId);
        String key = mDatabase.push().getKey();
        // Create a HashMap to store the car properties
        Map<String, Object> car = new HashMap<>();
        car.put("userID", user_id);
        car.put("fromDate", pickUp);
        car.put("toDate", Dropoff);
        car.put("PaymentMethod", paymentMethod);
        car.put("totalPrice", FinalPrice);
        mDatabase.child("bookings").child(key).setValue(car);

    }

    private void FirBaseAddRentals() {
        mDatabase = rootNode.getReference("activities").child(user_id).child("rentals");
        String key = mDatabase.push().getKey();

        //for rentals in activites
        String msg = "you rented " + CarBrand + " from " + pickUp + " to " + Dropoff + " with the final price off  " + FinalPrice;
        mDatabase.child(key).setValue(msg);
    }


    private void FirBaseAddTrans(String method) {
        //0 is user id
        mDatabase = rootNode.getReference(user_id).child("activities").child("transactions");
        String key = mDatabase.push().getKey();

        if (method.equalsIgnoreCase("OnSite")) {
            //for rentals in activites
            String msg = "you agreed on paying  " + FinalPrice + " Cash On Site ";
            mDatabase.child(key).setValue(msg);

        } else if (method.equalsIgnoreCase("visa")) {
            //for rentals in activites
            String msg = "you payed through visa  " + FinalPrice;
            mDatabase.child(key).setValue(msg);
        }
    }


    private void readCarData(String id) {
        mDatabase = rootNode.getReference("cars");
        mDatabase.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        price_day.append(String.valueOf(dataSnapshot.child("price").getValue()));
                        location.setText(String.valueOf(dataSnapshot.child("location").getValue()));
                        long num = getRentDays(pickUp, Dropoff);
                        Long priceNoFee = num * Long.parseLong(String.valueOf(dataSnapshot.child("price").getValue()));
                        AlldaysPrice.append(String.valueOf(priceNoFee));
                        FinalPrice = priceNoFee * 0.65;
                        totalprice.append(String.valueOf(FinalPrice));
                        dates.setText(pickUp);
                        dates.append(" - ");
                        dates.append(Dropoff);
                        CarBrand = String.valueOf(dataSnapshot.child("brand").getValue());

                        ReadImage(id);

                    }

                } else {
                    Toast.makeText(MakePaymentActivity.this, "fail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void ReadImage(String carID) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        StorageReference imageRef = storageRef.child("images/cars/" + carID + ".jpg");

        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            carImg.setImageBitmap(bmp);
        });

    }

//    long getRentDays(String pickUp , String dropOff){
//        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
//        Date date1 = null;
//        try {
//            date1 = formatter.parse(pickUp);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date date2 = null;
//        try {
//            date2 = formatter.parse(dropOff);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long diff = date2.getTime() - date1.getTime();
//        long diffDays = diff / (24 * 60 * 60 * 1000);
//
//    return diffDays;
//    }

    public long getRentDays(String pickUp, String dropOff) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        try {
            Date pickupDate = sdf.parse(pickUp);
            Date dropoffDate = sdf.parse(dropOff);
            long diff = dropoffDate.getTime() - pickupDate.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }


}