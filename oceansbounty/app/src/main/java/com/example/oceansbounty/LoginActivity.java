package com.example.oceansbounty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        EditText phoneNumField = findViewById(R.id.input_phone_num);
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number = phoneNumField.getText().toString();

                if (validateCredentials(phone_number)) {

                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // CURRENT TASK - CREATE ACCOUNT PAGE AND FUNCTION (ADD NEW DATA TO JSON)

    // Create a json file containing mock data
    // Code a function that goes through the json file to search for matching credentials
    private boolean validateCredentials(String phone_number) {
        // Mock data: Replace with a real database or API call in the future
        String validPhoneNum = "074544877562";

        return phone_number.equals(validPhoneNum);
    }
}
