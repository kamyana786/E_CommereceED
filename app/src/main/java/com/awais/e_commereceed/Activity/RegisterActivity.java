package com.awais.e_commereceed.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.awais.e_commereceed.R;

public class RegisterActivity extends BaseActivity {
    private TextView alredyHaveaccount; // Declare as a class variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize the TextView and set the click listener
        alredyHaveaccount = findViewById(R.id.alreadyHaveaccount); // Correct initialization

        alredyHaveaccount.setOnClickListener(new View.OnClickListener() { // Set click listener
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class)); // Navigate to LoginActivity
            }
        });
    }
}
