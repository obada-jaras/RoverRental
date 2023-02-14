package com.example.android_projects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class AboutUs extends AppCompatActivity {


    private ImageView imageView1,imageView2,imageView3;
    private Button btn_contactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getReferance();
//        Handler handler = new Handler();
//
//        Handler handler1 = new Handler();
//        handler1.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Make object type intent to go to menu activity ..
//                Intent intent = new Intent(MainActivity.this,ContactUs.class);
//                //Android to open menu activity ..
//                startActivity(intent);
//                finish();
//            }
//        },5000);
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Make object type intent to go to menu activity ..
//                Intent intent = new Intent(MainActivity.this,Profile.class);
//                //Android to open menu activity ..
//                startActivity(intent);
//                finish();
//            }
//        },10000);
//
//
            btn_contactUs = findViewById(R.id.btn_contactUs);

            btn_contactUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), ContactUs.class);
                    startActivity(i);
                }
            });
    }




    private void getReferance()
    {


    }
}