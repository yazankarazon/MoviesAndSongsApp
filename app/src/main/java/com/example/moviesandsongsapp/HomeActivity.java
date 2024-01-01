package com.example.moviesandsongsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Movies(View view) {
        Intent intent = new Intent(HomeActivity.this, MoviesActivity.class);
        startActivity(intent);
    }

    public void Songs(View view) {
        Intent intent = new Intent(HomeActivity.this, SongsActivity.class);
        startActivity(intent);
    }

    public void btnProfile(View view) {
        Intent intent = new Intent(HomeActivity.this, Profile.class);
        startActivity(intent);
    }

    public void Logout(View view) {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}