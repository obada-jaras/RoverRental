package com.example.android_projects;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CarsAvailable extends AppCompatActivity {
    RecyclerView recycler_layout;

    String pickupLocation;
    String pickupDate;
    String dropOffDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_available);

        setupView();
    }


    private void setupView() {
        recycler_layout = findViewById(R.id.recycler_layout);
        setDateFromSharedIntent();
        getAvailableCarsFromDb();
    }

    private void setDateFromSharedIntent() {
        pickupLocation = getIntent().getStringExtra("pickupLocation");
        pickupDate = getIntent().getStringExtra("fromDate");
        dropOffDate = getIntent().getStringExtra("toDate");
    }



    private void getAvailableCarsFromDb() {
        recycler_layout.setHasFixedSize(true);
        recycler_layout.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<AvailabeCar> listOfCars = new ArrayList<>();
        AvailableCardAdapter availableCardAdapter = new AvailableCardAdapter(this, listOfCars);
        recycler_layout.setAdapter(availableCardAdapter);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("cars");
        Query query = databaseReference.orderByChild("location").equalTo(pickupLocation);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot carSnapshot : dataSnapshot.getChildren()) {
                    boolean isCarAvailable = true;

                    if (carSnapshot.hasChild("bookings")) {
                        for (DataSnapshot bookingSnapshot : carSnapshot.child("bookings").getChildren()) {
                            String reservedPickupDate = bookingSnapshot.child("fromDate").getValue(String.class);
                            String reservedDropoffDate = bookingSnapshot.child("toDate").getValue(String.class);

                            if (!isAvailable(pickupDate, dropOffDate, reservedPickupDate, reservedDropoffDate)) {
                                isCarAvailable = false;
                                break;
                            }
                        }
                    }

                    if (isCarAvailable) {
                        String carId = carSnapshot.getKey();
                        String brand = carSnapshot.child("brand").getValue(String.class);
                        String year = carSnapshot.child("modelYear").getValue(Integer.class) + "";
                        String transmission = carSnapshot.child("transmission").getValue(String.class);
                        String price = carSnapshot.child("price").getValue(Integer.class) + "$/Day";
                        String seats = carSnapshot.child("seats").getValue(Integer.class) + "";

                        final AvailabeCar carCard = new AvailabeCar();
                        carCard.id = carId;
                        carCard.brand = brand + " " + year;
                        carCard.transmission = transmission;
                        carCard.price = price;
                        carCard.seats = seats;

                        getCarImage(carId, carCard);
                    }
                }
            }

            private void getCarImage(String carID, AvailabeCar carCard) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();

                StorageReference imageRef = storageRef.child("images/cars/" + carID + ".jpg");

                imageRef.getBytes(5 * 1024 * 1024).addOnSuccessListener(bytes -> {
                    carCard.btmImg = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    listOfCars.add(carCard);
                    availableCardAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });

    }



    public boolean isAvailable(String wantToPickupDate, String wantToDropOffDate, String reservedPickupDate, String reservedDropoffDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        try {
            Date wantToPickup = dateFormat.parse(wantToPickupDate);
            Date wantToDropoff = dateFormat.parse(wantToDropOffDate);
            Date reservedPickup = dateFormat.parse(reservedPickupDate);
            Date reservedDropoff = dateFormat.parse(reservedDropoffDate);

            if (wantToPickup.before(reservedPickup)) {
                if (wantToDropoff.before(reservedPickup)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (wantToPickup.before(reservedDropoff)) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}