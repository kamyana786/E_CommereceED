package com.awais.e_commereceed.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.awais.e_commereceed.R;

public class LoginActivity extends BaseActivity {
    private TextView createNewAccount; // Declare as a class variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the TextView and set the click listener
        createNewAccount = findViewById(R.id.createNewAccount); // Initialize properly

        createNewAccount.setOnClickListener(new View.OnClickListener() { // Set click listener
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)); // Navigate to RegisterActivity
            }
        });
    }
}
