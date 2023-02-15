package com.example.android_projects;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class CarInformation extends AppCompatActivity {
    private static final String TAG = "CarInformation";
    private static final long ONE_MEGABYTE = 5 * 1024 * 1024;
    private Button btn_rent;
    private ImageView car_img;
    private TextView title_txt, seats_txt, transmission_txt, motorType_txt, location_txt, price_txt;
    private RecyclerView recyclerView_xml;

    private String carID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_information);

        setupView();
    }

    private void setupView() {
        carID = getIntent().getStringExtra("carID");
        assignReferences();
        btn_rent.setOnClickListener(view -> {
            handleRentButton();
        });
        getDataFromDatabase();
    }


    private void assignReferences() {
        btn_rent = findViewById(R.id.btn_rent);
        title_txt = findViewById(R.id.title_txt);
        seats_txt = findViewById(R.id.seats_txt);
        transmission_txt = findViewById(R.id.transmission_txt);
        motorType_txt = findViewById(R.id.motorTyoe_txt);
        location_txt = findViewById(R.id.location_txt);
        price_txt = findViewById(R.id.price_txt);
        car_img = findViewById(R.id.car_img);
        recyclerView_xml = findViewById(R.id.recyclerView_xml);
    }

    private void handleRentButton() {
        Intent i = new Intent(getApplicationContext(), MakePaymentActivity.class);
        i.putExtra("carId", carID);
        startActivity(i);
    }

    private void getDataFromDatabase() {
        getCarImageFromDatabase();
        getCarData();
        getReviewsData();
    }


    private void getCarImageFromDatabase() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        StorageReference imageRef = storageRef.child("images/cars/" + carID + ".jpg");

        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            car_img.setImageBitmap(bmp);
        });
    }

    private void getCarData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cars").child(carID);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String brand = dataSnapshot.child("brand").getValue(String.class);
                    int modelYear = dataSnapshot.child("modelYear").getValue(Integer.class);
                    int seats = dataSnapshot.child("seats").getValue(Integer.class);
                    String motorType = dataSnapshot.child("motorType").getValue(String.class);
                    String pickup = dataSnapshot.child("location").getValue(String.class);
                    double price = dataSnapshot.child("price").getValue(Double.class);
                    String transmission = dataSnapshot.child("transmission").getValue(String.class);

                    showFetchedData(brand, modelYear, seats, motorType, pickup, price, transmission);
                }
            }

            private void showFetchedData(String brand, int modelYear, int seats, String motorType, String pickup, double price, String transmission) {
                title_txt.setText(new StringBuilder().append(brand).append(" ").append(modelYear).toString());
                seats_txt.setText(new StringBuilder().append(seats).append(" Seats").toString());
                transmission_txt.setText(transmission);
                motorType_txt.setText(motorType);
                location_txt.setText(pickup);
                price_txt.setText(new StringBuilder().append(price).append("$/Day").toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(CarInformation.this, "Failed to read data from the database.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getReviewsData() {
        recyclerView_xml.setHasFixedSize(true);
        recyclerView_xml.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Review> listOfReviews = new ArrayList<>();
        ReviewAdapter reviewAdapter = new ReviewAdapter(this, listOfReviews);
        recyclerView_xml.setAdapter(reviewAdapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference carReviewsRef = database.getReference("cars").child(carID).child("feedbacks");


        carReviewsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot reviewSnapshot) {
                for (DataSnapshot singleReviewSnapshot : reviewSnapshot.getChildren()) {
                    String userId = singleReviewSnapshot.getKey();

                    final Review review = new Review();
                    review.rating = singleReviewSnapshot.child("rating").getValue(Integer.class);

                    review.comment = singleReviewSnapshot.child("comment").getValue(String.class);

                    getUsernameAndPhoto(userId, review);
                }
            }

            private void getUsernameAndPhoto(String userId, Review review) {
                assert userId != null;
                DatabaseReference userReviewsRef = database.getReference("users").child(userId);
                userReviewsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                        String firstName = userSnapshot.child("firstName").getValue(String.class);

                        String lastName = userSnapshot.child("lastName").getValue(String.class);

                        review.userName = firstName + " " + lastName;
                        listOfReviews.add(review);

                        getUserImage();
                    }

                    private void getUserImage() {
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();

                        StorageReference imageRef = storageRef.child("images/users/" + userId + ".png");

                        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
                            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            review.bmpUserImage = bmp;
                            reviewAdapter.notifyDataSetChanged();
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CarInformation.this, "Failed to read data from the database.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CarInformation.this, "Failed to read data from the database.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}