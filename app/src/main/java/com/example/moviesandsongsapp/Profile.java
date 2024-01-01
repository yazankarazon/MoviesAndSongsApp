package com.example.moviesandsongsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.content.SharedPreferences;

public class Profile extends AppCompatActivity {

    private TextView txtFullName, txtEmail, txtPassword;
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        setupViews();
        printInfo();
    }

    private void setupViews(){
        txtFullName = findViewById(R.id.txtFullName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
    }

    private void printInfo(){
        String storedFirstName = prefs.getString(RegisterActivity.FIRSTNAME, "");
        String storedLastName = prefs.getString(RegisterActivity.LASTNAME, "");
        String storedEmail = prefs.getString(RegisterActivity.EMAIL, "");
        String storedPassword = prefs.getString(RegisterActivity.PASSWORD, "");

        txtFullName.setText("Full Name : " + storedFirstName + " " + storedLastName);
        txtEmail.setText("User Name To Login : "+ storedEmail );
        txtPassword.setText("Your Password : " + storedPassword);
    }
}