package com.example.expiry_tracker;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



import android.os.AsyncTask;


import java.sql.*;

import com.example.expiry_tracker.Model.UserAccount;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private UserAccount userAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextUsername = findViewById(R.id.editText1);
        editTextPassword = findViewById(R.id.editText2);


    }

    public void login(View view) {
        // Retrieve the username and password entered by the user
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();


        // Initialize UserAccount
        userAccount = new UserAccount();

        // Validate the login using the UserAccount instance
        UserAccount loggedInUser = userAccount.validateLogin(username, password);

        Log.d("infoffff", "OK1");
        // Get reference to the login confirmation TextView
        TextView loginConfirmationTextView = findViewById(R.id.loginConfirmationTextView);

        Log.d("infoffff", "OK2");
        // Check if login was successful
        if (loggedInUser != null) {

            Log.d("infoffff", "OK3");
            // Login successful, display confirmation message
            String fullName = loggedInUser.getFullName();
            String email = loggedInUser.getEmail();
            loginConfirmationTextView.setText("Welcome, " + fullName + "! Email: " + email);
            loginConfirmationTextView.setVisibility(View.VISIBLE); // Show the TextView
        } else {

            Log.d("infoffff", "OK4");
            // Login failed, display an error message
            loginConfirmationTextView.setText("Invalid username or password");
            loginConfirmationTextView.setVisibility(View.VISIBLE); // Show the TextView
        }
    }



}