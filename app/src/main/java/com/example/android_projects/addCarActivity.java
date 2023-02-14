package com.example.android_projects;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

public class addCarActivity extends AppCompatActivity {
    Spinner brandSpin ;
    Spinner yearSpin ;
    Spinner fuelSpin ;
    Spinner tranSpin;
    EditText carsseat;
    EditText price;
    ImageView carimag ;
    Button uploadimg ;
    String uuid ;
    Button AddCar ;
    Uri imgUri ;
    EditText location ;


    private DatabaseReference mDatabase;
    private FirebaseDatabase rootNode;
    private StorageReference storageReference ;




    //// From Shared Prefrence
    static String user_id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        // retrieve the user ID from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        user_id = sharedPreferences.getString("userId", "");

        setUpView();

        //uploaaad image for the car from the users gallery
       uploadimg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               chooseImg();

           }
       });

       // add car to rent
        AddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkdata()) {
                    new AlertDialog.Builder(addCarActivity.this)
                            .setTitle("Add Car For Rent")
                            .setMessage("Are you sure you want to add this car for rent?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    FireBaseCarUpload();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(R.drawable.logo)
                            .show();
                }
            }
        });
    }

    private void setUpView() {
        brandSpin = findViewById(R.id.carBrandrSpin);
        yearSpin = findViewById(R.id.carYearspin);
        fuelSpin = findViewById(R.id.carFuel);
        tranSpin = findViewById(R.id.carTransmition);
        price = findViewById(R.id.CarPrice);
        carsseat = findViewById(R.id.seatsText);
        carimag =findViewById(R.id.galleryImage);
        uploadimg = findViewById(R.id.upload);
        AddCar = findViewById(R.id.AddCar);
        location =findViewById(R.id.locationtxt);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        String [] brands ={"BMW","Chevrolet", "Honda","Ford","Volkswagen"};
        String [] fuel ={"Electric","Diesel", "Gasoline / petrol"};
        String[] transmissionString ={"Manual","Automatic"};

        int[] range = IntStream.rangeClosed(2010,2022).toArray();
        String[] yearArray = Arrays.stream(range)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        ArrayAdapter<String> brand = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,brands);
        brandSpin.setAdapter(brand);

        ArrayAdapter<String> objYearArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,yearArray);
        yearSpin.setAdapter(objYearArr);

        ArrayAdapter<String> objFuelArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,fuel);
        fuelSpin.setAdapter(objFuelArr);

        ArrayAdapter<String> tran = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,transmissionString);
        tranSpin.setAdapter(tran);

    }

    private void FireBaseCarUpload() {
        rootNode = FirebaseDatabase.getInstance();
        mDatabase =rootNode.getReference("cars");

        String brand = (String) brandSpin.getSelectedItem();
        String fuel = (String) fuelSpin.getSelectedItem();
        String trans = (String) tranSpin.getSelectedItem();
        int year = Integer.parseInt((String) yearSpin.getSelectedItem());
        int seats = Integer.parseInt(carsseat.getText().toString());
        String locations = location.getText().toString();

        double pricecar = Double.parseDouble(price.getText().toString());

        // Create a HashMap to store the car properties
        Map<String, Object> car = new HashMap<>();
        car.put("carOwner", user_id);
        car.put("brand", brand);
        car.put("modelYear", year);
        car.put("motorType", fuel);
        car.put("price", pricecar);
        car.put("seats", seats);
        car.put("transmission", trans);
        car.put("location", locations);
        LocationHelper locationHelper = new LocationHelper(this);
        String gps = locationHelper.getLocation();;

        car.put("lat_long", gps);

        if (gps != null) {
            Log.d("Location!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:" , gps);
            car.put("lat_long", gps);
        } else {
            car.put("lat_long", "null");

        }

        //upload to firebase
        uuid = UUID.randomUUID().toString();
        mDatabase.child(uuid).setValue(car);
        uploadFirebaseimg();

        //operation upload done
        Toast.makeText(addCarActivity.this, "Car added successfully!", Toast.LENGTH_SHORT).show();
        price.setText("");
        carsseat.setText("");
        carimag.setImageDrawable(null);
        location.setText("");

        //add it to offers
        mDatabase =rootNode.getReference("activities").child(user_id).child("offers");
        String key = mDatabase.push().getKey();

        String msg = "you offered  "+ brand +" for rent with a pick up location as  "+ locations +" with the price of " + String.valueOf(pricecar) +"  $ per day ";
        mDatabase.child(key).setValue(msg);

    }

    private void uploadFirebaseimg() {

        String fileName = uuid ;
        storageReference=FirebaseStorage.getInstance().getReference("images/"+ "cars/" +fileName+"jpg");
        storageReference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {



            }
        });


    }

    private void chooseImg() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1 && resultCode==RESULT_OK && data!= null && data.getData() != null){
            imgUri = data.getData();
            carimag.setImageURI(imgUri);
        }

    }


    private boolean checkdata() {
        boolean valid = true;

        if (price.getText().toString().isEmpty()) {
            price.setError("Please fill this field");
            valid = false;

        }
        if(carsseat.getText().toString().isEmpty()){
            carsseat.setError("Please fill this field");
            valid = false;
        }
        if(location.getText().toString().isEmpty()){
            location.setError("Please fill this field");
            valid = false;
        }


        Drawable drawable = carimag.getDrawable();
        if (drawable == null) {
            valid = false;
            View view = findViewById(R.id.layout1);
            Snackbar snackbar = Snackbar.make( view, "please upload the cars picture",
                    Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(getResources().getColor(R.color.error));
            snackbar.show();

        }


        return valid;

    }
}