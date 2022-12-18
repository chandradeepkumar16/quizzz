package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView =  findViewById(R.id.bottomNavigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new CommunityFragment()).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener  =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            selectedFragment= new HomeFragment();
                            break;
                        case R.id.community:
                            selectedFragment=new CommunityFragment();
                            break;

                        case R.id.profile:
                            selectedFragment=new ProfileFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,
                            selectedFragment).commit();

                    return true;
                }
            };



}