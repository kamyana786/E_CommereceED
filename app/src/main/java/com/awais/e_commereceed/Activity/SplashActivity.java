package com.awais.e_commereceed.Activity;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.awais.e_commereceed.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.imageView);

        // Create a scale animation
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 0.5f, 1.0f);
        scaleAnimator.setDuration(1000); // Set the duration in milliseconds

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 0.5f, 1.0f);
        scaleYAnimator.setDuration(1000);

        // Start the animation
        scaleAnimator.start();
        scaleYAnimator.start();
        new Handler(getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        },4000);
    }
}
