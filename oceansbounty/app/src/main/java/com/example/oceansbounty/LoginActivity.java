package com.example.oceansbounty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private RequestData requestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        // Find UI Components
        EditText phoneNumField = findViewById(R.id.input_phone_num);
        Button loginButton = findViewById(R.id.login_button);
        Button createAccButton = findViewById(R.id.create_account_button);

        // Create retrofit instance
        requestData = RetrofitClient.getInstance().create(RequestData.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPhoneNumber = phoneNumField.getText().toString();

                // Check if input fields are empty or not
                if(TextUtils.isEmpty(inputPhoneNumber)) {
                    Toast.makeText(LoginActivity.this, "The field cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    checkLoginData(inputPhoneNumber);
                }
            }
        });

        createAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLoginData(String phoneNumber) {
        requestData.getAllData().enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<Reservation> reservations = response.body();
                    boolean loginSuccessful = false;

                    for (Reservation reservation : reservations) {
                        if (Objects.equals(reservation.getCustomerPhoneNumber(), phoneNumber)) {
                            loginSuccessful = true;

                            // Save current user's info in Shared Preferences
                            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("phone_number", reservation.getCustomerPhoneNumber());
                            editor.apply();
                            break;
                        }
                    }

                    if (loginSuccessful) {
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed, Please Retry.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Error retrieving data from server.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed to connect to server.", Toast.LENGTH_SHORT).show();
                Log.d("TEST", "failed at on failure: " + t.getMessage());
            }
        });
    }
}
