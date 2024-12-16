package com.example.oceansbounty;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {

    private String selectedScenery;
    private String selectedArea;
    private String selectedSeatingArea;
    private String selectedMeal;
    private String selectedDate;
    private int tableSize;
    private RequestData requestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_page);

        // UI Components
        requestData = RetrofitClient.getInstance().create(RequestData.class);

        Button gardenButton = findViewById(R.id.garden_button);
        Button seasideButton = findViewById(R.id.seaside_button);
        Button insideButton = findViewById(R.id.inside_button);
        Button outsideButton = findViewById(R.id.outside_button);

        EditText numberOfSeatsField = findViewById(R.id.number_of_seats);

        Button breakfastButton = findViewById(R.id.breakfast_button);
        Button lunchButton = findViewById(R.id.lunch_button);
        Button dinnerButton = findViewById(R.id.dinner_button);

        Button inputDate = findViewById(R.id.input_date);

        Button backButton = findViewById(R.id.back_button);
        Button nextButton = findViewById(R.id.next_button);

        selectedScenery = null;
        selectedArea = null;
        selectedSeatingArea = null;
        selectedMeal = null;
        selectedDate = null;
        tableSize = 0;

        // Button call methods
        gardenButton.setOnClickListener(view -> { scenerySelect(gardenButton, seasideButton); });
        seasideButton.setOnClickListener(view -> { scenerySelect(seasideButton, gardenButton); });

        insideButton.setOnClickListener(view -> { areaSelect(insideButton, outsideButton); });
        outsideButton.setOnClickListener(view -> { areaSelect(outsideButton, insideButton); });

        breakfastButton.setOnClickListener(view -> { mealSelect(breakfastButton, lunchButton, dinnerButton); });
        lunchButton.setOnClickListener(view -> { mealSelect(lunchButton, breakfastButton, dinnerButton); });
        dinnerButton.setOnClickListener(view -> { mealSelect(dinnerButton, breakfastButton, lunchButton); });

        // User chooses a date
        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedScenery == null || selectedArea == null) {
                    Toast.makeText(BookingActivity.this, "Please select seating area first.", Toast.LENGTH_SHORT).show();
                } else {
                    // Get today's date
                    Calendar today = Calendar.getInstance();

                    // A week in advance
                    today.add(Calendar.DAY_OF_MONTH, 7);

                    // Create a DatePickerDialog
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            BookingActivity.this,
                            (view1, year, month, day) -> {
                                month += 1;
                                selectedDate = year + "/" + month + "/" + day;
                                inputDate.setText(selectedDate);
                            },
                            today.get(Calendar.YEAR),
                            today.get(Calendar.MONTH),
                            today.get(Calendar.DAY_OF_MONTH)
                    );

                    // Restrict past dates
                    datePickerDialog.getDatePicker().setMinDate(today.getTimeInMillis());

                    // Show the DatePickerDialog
                    datePickerDialog.show();
                }
            }
        });

        // Go back to menu
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        // Proceed to confirmation page
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputNumberOfSeats = numberOfSeatsField.getText().toString();

                if(selectedScenery == null || selectedArea == null || selectedMeal == null || selectedDate == null || TextUtils.isEmpty(inputNumberOfSeats)) {
                    Toast.makeText(BookingActivity.this, "One of the fields are not selected or filled.", Toast.LENGTH_SHORT).show();

                } else {
                    View bookingConfirmationPage = getLayoutInflater().inflate(R.layout.booking_confirmation_page, null);
                    setContentView(bookingConfirmationPage);

                    // booking_confirmation_page's UI Components
                    TextView seatingArea = bookingConfirmationPage.findViewById(R.id.input_seating_area);
                    TextView meal = bookingConfirmationPage.findViewById(R.id.input_meal);
                    TextView date = bookingConfirmationPage.findViewById(R.id.input_date);
                    TextView seats = bookingConfirmationPage.findViewById(R.id.input_seats);
                    Button backButton = bookingConfirmationPage.findViewById(R.id.back_button);
                    Button confirmButton = bookingConfirmationPage.findViewById(R.id.confirm_button);

                    // Set Text of UI in Confirmation Page
                    selectedSeatingArea = selectedScenery + " " + selectedArea;
                    seatingArea.setText(selectedSeatingArea);
                    meal.setText(selectedMeal);
                    date.setText(selectedDate);
                    seats.setText(inputNumberOfSeats);

                    // Parse String to Integer
                    tableSize = Integer.parseInt(inputNumberOfSeats);

                    // On User Confirmation
                    confirmButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                            String loggedInPhoneNum = sharedPreferences.getString("phone_number", "");
                            searchByPhone(loggedInPhoneNum);
                        }
                    });

                    // When backButton is pressed (in Confirmation Page)
                    backButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(BookingActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    private void scenerySelect(Button clickedButton, Button otherButton) {

        if(clickedButton.getId() == R.id.garden_button) {
            selectedScenery = "Garden";
            Log.d("TEST", selectedScenery);
        } else if (clickedButton.getId() == R.id.seaside_button) {
            selectedScenery = "Seaside";
            Log.d("TEST", selectedScenery);
        }

        clickedButton.setBackgroundColor(Color.rgb(128,128,128));
        clickedButton.setTextColor(Color.WHITE);

        otherButton.setBackgroundColor(Color.rgb(204,204,204));
        otherButton.setTextColor(Color.BLACK);
    }

    private void areaSelect(Button clickedButton, Button otherButton) {

        if(clickedButton.getId() == R.id.inside_button) {
            selectedArea = "Inside";
            Log.d("TEST", selectedArea);
        } else if (clickedButton.getId() == R.id.outside_button) {
            selectedArea = "Outside";
            Log.d("TEST", selectedArea);
        }

        clickedButton.setBackgroundColor(Color.rgb(128,128,128));
        clickedButton.setTextColor(Color.WHITE);

        otherButton.setBackgroundColor(Color.rgb(204,204,204));
        otherButton.setTextColor(Color.BLACK);
    }

    private void mealSelect(Button clickedButton, Button otherButton1, Button otherButton2) {

        if(clickedButton.getId() == R.id.breakfast_button) {
            selectedMeal = "Breakfast";
            Log.d("TEST", selectedMeal);
        } else if (clickedButton.getId() == R.id.lunch_button) {
            selectedMeal = "Lunch";
            Log.d("TEST", selectedMeal);
        } else if (clickedButton.getId() == R.id.dinner_button) {
            selectedMeal = "Dinner";
            Log.d("TEST", selectedMeal);
        }

        clickedButton.setBackgroundColor(Color.rgb(128,128,128));
        clickedButton.setTextColor(Color.WHITE);
        otherButton1.setBackgroundColor(Color.rgb(255,255,255));
        otherButton1.setTextColor(Color.BLACK);
        otherButton2.setBackgroundColor(Color.rgb(255,255,255));
        otherButton2.setTextColor(Color.BLACK);
    }

    private void searchByPhone(String phoneNumber) {
        requestData.getAllData().enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<Reservation> reservations = response.body();
                    boolean searchSuccessful = false;
                    int matchingCustomerId = 0;

                    for (Reservation reservation : reservations) {
                        if (Objects.equals(reservation.getCustomerPhoneNumber(), phoneNumber)) {
                            searchSuccessful = true;
                            matchingCustomerId = reservation.getId();
                            break;
                        }
                    }

                    if (searchSuccessful && matchingCustomerId != 0) {
                        Toast.makeText(BookingActivity.this, "Search Successful!", Toast.LENGTH_SHORT).show();
                        Log.d("USER_ID", String.valueOf(matchingCustomerId));
                        patchBooking(matchingCustomerId);
                    } else {
                        Toast.makeText(BookingActivity.this, "Search Failed, Please try again.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(BookingActivity.this, "Error retrieving data from server.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Failed to connect to server.", Toast.LENGTH_SHORT).show();
                Log.d("TEST", "failed at on failure: " + t.getMessage());
            }
        });
    }

    private void patchBooking(int matchingId){
        PatchBookingRequest newBooking = new PatchBookingRequest(selectedMeal, selectedSeatingArea, tableSize, selectedDate);

        requestData.editBooking(matchingId, newBooking).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if(response.isSuccessful()){
                    Toast.makeText(BookingActivity.this, "Booking Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookingActivity.this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("TEST", String.valueOf(response.code()));
                    Log.d("TEST", matchingId + selectedSeatingArea + tableSize + selectedDate);
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Booking Failed, Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
