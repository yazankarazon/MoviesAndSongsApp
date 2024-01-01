package com.example.moviesandsongsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class RegisterActivity extends AppCompatActivity {

    private EditText FirstName, LastName, Email, Password;
    public static final String FIRSTNAME="FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupViews();
        setupSharedPrefs();
        getDataLifeCycle();
    }



    private void setupViews(){
        FirstName = findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }



    public void btnRegister(View view) {
        String firstname = FirstName.getText().toString();
        String lastname = LastName.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        saveUserDataInSharedPref(firstname, lastname, email, password);


        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void btnCancel(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }



    private void saveUserDataInSharedPref(String firstname, String lastname, String email, String password) {
        editor.putString("FIRSTNAME", firstname);
        editor.putString("LASTNAME", lastname);
        editor.putString("EMAIL", email);
        editor.putString("PASSWORD", password);
        editor.commit();
    }


    @Override
    protected void onPause() {
        super.onPause();

        editor.putString("LIFECYCLE_FIRSTNAME", FirstName.getText().toString());
        editor.putString("LIFECYCLE_LASTNAME", LastName.getText().toString());
        editor.putString("LIFECYCLE_EMAIL", Email.getText().toString());
        editor.putString("LIFECYCLE_PASSWORD", Password.getText().toString());
        editor.commit();
    }

    private void getDataLifeCycle() {
        String lc_firstname = prefs.getString("LIFECYCLE_FIRSTNAME", "");
        String lc_lastname = prefs.getString("LIFECYCLE_LASTNAME", "");
        String lc_email = prefs.getString("LIFECYCLE_EMAIL", "");
        String lc_password = prefs.getString("LIFECYCLE_PASSWORD", "");

        FirstName.setText(lc_firstname);
        LastName.setText(lc_lastname);
        Email.setText(lc_email);
        Password.setText(lc_password);
    }








}