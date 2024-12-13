package com.example.oceansbounty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.login_page);
        // Define correct username and password
        final String correctUsername = "admin";
        final String correctPassword = "12345";
        // Get references to UI elements
        EditText usernameInput = findViewById(R.id.input_email);
        EditText passwordInput = findViewById(R.id.input_password);
        Button loginButton = findViewById(R.id.login_button);

        // Set click listener on the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                // Validate the login credentials
                if (username.equals(correctUsername) && password.equals(correctPassword)) {
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

// make a java class full of UI components UIComponents.java
// buttons and text fields can be manipulated better, like change how it looks on click