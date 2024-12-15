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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        createMyAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = newNameField.getText().toString();
                String newPhone = newPhoneField.getText().toString();

                if(TextUtils.isEmpty(newName) || TextUtils.isEmpty(newPhone)) {
                    Toast.makeText(CreateAccountActivity.this, "The fields cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    // get the largest customer id, + 1 and create account
                    // learn POST, UPDATE and DELETE with retrofit
                    RequestData requestData = RetrofitClient.getInstance().create(RequestData.class);

                    // repurpose for cycling through and get the largest id - ask chat if need help
                    requestData.getAllData().enqueue(new Callback<List<Reservation>>() {
                        @Override
                        public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                            assert response.body() != null;

                            List<Reservation> reservations = response.body();
                            int largestId = -1;

                            for (Reservation reservation : reservations) {
                               if(reservation.getId() > largestId){
                                   largestId = reservation.getId();
                               }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Reservation>> call, Throwable t) {
                            Log.d("TEST","failed at on failure");
                        }
                    });
                }
            }
        });
    }
}