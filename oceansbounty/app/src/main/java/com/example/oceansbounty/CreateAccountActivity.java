package com.example.oceansbounty;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {

    private RequestData requestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_page);

        // UI Components
        EditText newNameField = findViewById(R.id.input_name);
        EditText newPhoneField = findViewById(R.id.input_phone_num);
        Button createMyAccountButton = findViewById(R.id.create_my_account_button);
        Button backButton = findViewById(R.id.back_button);

        // Create retrofit instance
        requestData = RetrofitClient.getInstance().create(RequestData.class);

        // Create account
        createMyAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = newNameField.getText().toString();
                String inputPhoneNumber = newPhoneField.getText().toString();

                // Check if input fields are empty or not
                if(TextUtils.isEmpty(inputName) || TextUtils.isEmpty(inputPhoneNumber)) {
                    Toast.makeText(CreateAccountActivity.this, "The fields cannot be empty.", Toast.LENGTH_SHORT).show();

                } else {
                    createNewAccount(inputName, inputPhoneNumber);
                    Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Back to login page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createNewAccount(String newName, String newPhoneNum){

        PostUserRequest newAccount = new PostUserRequest(newName, newPhoneNum, "None", "None", 1, "2024-01-01");

        requestData.createAccount(newAccount).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreateAccountActivity.this, "Account creation successful!", Toast.LENGTH_SHORT).show();

                } else {
                    assert response.errorBody() != null;
                    Toast.makeText(CreateAccountActivity.this, "Failed to create account.", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                // code to handle failure
            }
        });

    }

    private void deleteAccount() {

        // Hardcoded to test
        requestData.deleteAccount(2).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreateAccountActivity.this, "Account deletion successful!", Toast.LENGTH_SHORT).show();
                    Log.d("TEST", "Deletion successful. Response Code: " + response.code());
                } else {
                    try {
                        Log.d("TEST", "Failed to delete. Response Code: " + response.code() +
                                ", Error Body: " + response.errorBody().string());
                    } catch (Exception e) {
                        Log.d("TEST", "Error parsing response body: " + e.getMessage());
                    }
                    Toast.makeText(CreateAccountActivity.this, "Failed to delete account.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("TEST", "onFailure: " + t.getMessage());
                Toast.makeText(CreateAccountActivity.this, "Failed to delete account.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}