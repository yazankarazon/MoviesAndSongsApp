package com.example.moviesandsongsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText Email, Password;
    private CheckBox rememberMeCheckBox;
    private SharedPreferences prefs;
    public static SharedPreferences.Editor editor;

    private static final String FLAG = "FLAG";
    private static final String PREF_EMAIL = "EMAIL";
    private static final String PREF_PASSWORD = "PASSWORD";
    private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupViews();
        setupSharedPrefs();
        checkPrefs();

    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);

        if(flag){
            Email.setText(prefs.getString(PREF_EMAIL, ""));
            Password.setText(prefs.getString(PREF_PASSWORD, ""));
            rememberMeCheckBox.setChecked(true);
        }
    }

    private void setupViews(){
        Email = findViewById(R.id.UserNameLogin);
        Password = findViewById(R.id.PasswordLogin);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);
    }
    private void setupSharedPrefs() {

        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    public void btnLogin(View view) {
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simulate a login by checking stored credentials
        String storedEmail = prefs.getString(RegisterActivity.EMAIL, "");
        String storedPassword = prefs.getString(RegisterActivity.PASSWORD, "");

        if (email.equals(storedEmail) && password.equals(storedPassword)) {

            if (rememberMeCheckBox.isChecked()) {
                if (!flag) {
                    editor.putString(PREF_EMAIL, email);
                    editor.putString(PREF_PASSWORD, password);
                    editor.putBoolean(FLAG, true);
                    editor.commit();
                }
            } else {
                editor.remove(PREF_EMAIL);
                editor.remove(PREF_PASSWORD);
                editor.putBoolean(FLAG, false);
                editor.commit();
            }


            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid UserName or Password", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnRegisterLogin(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
