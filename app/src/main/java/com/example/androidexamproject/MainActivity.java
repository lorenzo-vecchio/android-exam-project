package com.example.androidexamproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flFragment, Products.class, null)
                .setReorderingAllowed(true).commit();

        BottomNavigationView bottomBar = findViewById(R.id.bottomNavigationView);
        bottomBar.setOnItemSelectedListener(item -> {
            switch (item.getTitle().toString()) {
                case "Products":
                    fragmentManager.beginTransaction().replace(R.id.flFragment, Products.class, null)
                            .setReorderingAllowed(true).commit();
                    break;
                case "Cart":
                    fragmentManager.beginTransaction().replace(R.id.flFragment, Cart.class, null)
                            .setReorderingAllowed(true).commit();
                    break;
            }
            return true;
        });
    }
}