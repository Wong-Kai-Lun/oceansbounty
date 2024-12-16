package com.example.oceansbounty;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    private String selectedScenery;
    private String selectedArea;
    private String selectedMeal;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_page);

        // UI Components
        Button gardenButton = findViewById(R.id.garden_button);
        Button seasideButton = findViewById(R.id.seaside_button);
        Button insideButton = findViewById(R.id.inside_button);
        Button outsideButton = findViewById(R.id.outside_button);

        EditText numberOfSeats = findViewById(R.id.number_of_seats);

        Button breakfastButton = findViewById(R.id.breakfast_button);
        Button lunchButton = findViewById(R.id.lunch_button);
        Button dinnerButton = findViewById(R.id.dinner_button);

        Button inputDate = findViewById(R.id.input_date);

        Button backButton = findViewById(R.id.back_button);
        Button nextButton = findViewById(R.id.next_button);

        selectedScenery = null;
        selectedArea = null;
        selectedMeal = null;
        selectedDate = null;

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
                datePickerDialog.getDatePicker().setMinDate(today.getTimeInMillis() + 7);

                // Show the DatePickerDialog
                datePickerDialog.show();
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
}
