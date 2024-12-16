package com.example.oceansbounty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    private RequestData requestData;
    private String selectedSeatingArea;
    private String selectedMeal;
    private int tableSize;
    private String selectedDate;
    private boolean haveBooking;

    // Declare UI components as class-level variables
    private TextView seatingArea;
    private TextView meal;
    private TextView date;
    private TextView seats;
    private ConstraintLayout existingBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_page);

        // Initialize UI Components
        seatingArea = findViewById(R.id.input_seating_area);
        meal = findViewById(R.id.input_meal);
        date = findViewById(R.id.input_date);
        seats = findViewById(R.id.input_seats);
        existingBooking = findViewById(R.id.existing_booking);

        Button bookButton = findViewById(R.id.book_button);
        ImageButton inboxButton = findViewById(R.id.inbox_button);
        ImageButton profileButton = findViewById(R.id.profile_button);

        // Create Retrofit instance
        requestData = RetrofitClient.getInstance().create(RequestData.class);

        selectedSeatingArea = null;
        selectedMeal = null;
        tableSize = 0;
        selectedDate = null;
        haveBooking = false;

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String loggedInPhoneNum = sharedPreferences.getString("phone_number", "");
        searchExistingBooking(loggedInPhoneNum);

        bookButton.setOnClickListener(view -> {
            if (!haveBooking) {
                Intent intent = new Intent(MenuActivity.this, BookingActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MenuActivity.this, "You already have an existing booking.", Toast.LENGTH_SHORT).show();
            }
        });

        // Bottom Navigation Buttons
        inboxButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, InboxActivity.class);
            startActivity(intent);
        });

        profileButton.setOnClickListener(view -> {
            // Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
            // startActivity(intent);
        });
    }

    private void searchExistingBooking(String phoneNumber) {
        requestData.getAllData().enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Reservation> reservations = response.body();

                    for (Reservation reservation : reservations) {
                        if (Objects.equals(reservation.getCustomerPhoneNumber(), phoneNumber) && !Objects.equals(reservation.getSeatingArea(), "None")) {
                            haveBooking = true;
                            selectedSeatingArea = reservation.getSeatingArea();
                            selectedMeal = reservation.getMeal();
                            tableSize = reservation.getTableSize();
                            selectedDate = reservation.getDate();

                            // Update UI Components
                            seatingArea.setText(selectedSeatingArea);
                            meal.setText(selectedMeal);
                            date.setText(selectedDate);
                            seats.setText(String.valueOf(tableSize));
                            existingBooking.setVisibility(View.VISIBLE);
                            break;
                        }
                    }

                    if (!haveBooking) {
                        existingBooking.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(MenuActivity.this, "Error retrieving data from server.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Toast.makeText(MenuActivity.this, "Failed to connect to server.", Toast.LENGTH_SHORT).show();
                Log.d("TEST", "failed at on failure: " + t.getMessage());
            }
        });
    }
}
