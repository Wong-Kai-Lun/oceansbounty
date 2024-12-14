package com.example.oceansbounty;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccountActivity extends AppCompatActivity {

    // CURRENT TASK - CREATE ACCOUNT PAGE AND FUNCTION (ADD NEW DATA TO JSON)
    // use java.util.concurrent for the requirement of worker thread

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_page);
        Log.d("TEST","failed after setcontent");

        EditText newNameField = findViewById(R.id.input_name);
        EditText newPhoneField = findViewById(R.id.input_phone_num);
        Button createMyAccountButton = findViewById(R.id.create_my_account_button);

        TextView textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestData requestData = retrofit.create(RequestData.class);

        requestData.getData(1).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                Log.d("TEST","failed at on response");
                assert response.body() != null;
                textView.setText(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Log.d("TEST","failed at on failure");
                textView.setText(t.getMessage());
            }
        });


        createMyAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = newNameField.getText().toString();
                String newPhone = newPhoneField.getText().toString();

                if(TextUtils.isEmpty(newName) || TextUtils.isEmpty(newPhone)) {
                    Toast.makeText(CreateAccountActivity.this, "The fields cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    // Add new customer
                }
            }
        });
    }
}