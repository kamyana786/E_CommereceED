package com.awais.e_commereceed.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.awais.e_commereceed.Adapter.CartAdapter;
import com.awais.e_commereceed.Helper.ManagmentCart;
import com.awais.e_commereceed.R;
import com.awais.e_commereceed.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    public static final String EXTRA_FROM_CART = "com.awais.e_commereceed.EXTRA_FROM_CART";
    ActivityCartBinding binding;
    private double tax;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart = new ManagmentCart(this);

        calculatorCart();
        setVariable();
        initCartList();
        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        LinearLayout explorarBtn = findViewById(R.id.explorarBtn);
        LinearLayout wishBtn = findViewById(R.id.wishBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        explorarBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });

        cartBtn.setOnClickListener(v ->
                Toast.makeText(CartActivity.this, "You are already in Cart", Toast.LENGTH_SHORT).show());
    }

    private void initCartList() {
        if (managmentCart.getListCart().isEmpty()) {
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }
        binding.cartView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(), this, this::calculatorCart));
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
        binding.btnCheckout.setOnClickListener(v -> {
            // Launch fragment_viewer activity
            Intent intent = new Intent(CartActivity.this, fragment_viewer.class);
            intent.putExtra(EXTRA_FROM_CART, true);
            startActivity(intent);
        });
    }

    private void calculatorCart() {
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round((managmentCart.getTotalFee() * percentTax * 100.0)) / 100.0;
        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemtotal = Math.round(managmentCart.getTotalFee() * 100) / 100;
        binding.totalFeeTxt.setText("Rs" + itemtotal);
        binding.taxTxt.setText("Rs" + tax);
        binding.deliveryTxt.setText("Rs" + delivery);
        binding.totalTxt.setText("Rs" + total);
    }
}
