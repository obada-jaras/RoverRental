package com.example.android_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    ImageView img_logo;
    TextView tf_note;
    private Animation top;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setupView();
    }

    private void setupView() {
        img_logo = findViewById(R.id.img_logo);
        tf_note = findViewById(R.id.tf_note);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        img_logo.setAnimation(top);


        final String animateText = tf_note.getText().toString();
        tf_note.setText("");

        new CountDownTimer(animateText.length() * 100, 100) {

            @Override
            public void onTick(long l) {
                tf_note.setText(tf_note.getText().toString() + animateText.charAt(count));
                count++;
            }

            @Override
            public void onFinish() {

            }
        }.start();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, login.class);
                startActivity(intent);
                finish();
            }
        }, 6000);
    }
}