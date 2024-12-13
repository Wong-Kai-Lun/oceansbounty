package com.example.oceansbounty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_page);
    }
}

        //EditText usernameField = findViewById(R.id.input_email);
        //EditText passwordField = findViewById(R.id.input_password);
        //Button loginButton = findViewById(R.id.login_button);

        /* loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lol", "test");
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                if (validateCredentials(username, password)) {

                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.main_menu_page);
                    Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Create a json file containing mock data
    // Code a function that goes through the json file to search for matching credentials
    private boolean validateCredentials(String username, String password) {
        // Mock data: Replace with a real database or API call in the future
        String validUsername = "admin@gmail.com";
        String validPassword = "1234";

        return username.equals(validUsername) && password.equals(validPassword);
    }
}
*/