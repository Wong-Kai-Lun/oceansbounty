package com.example.oceansbounty;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TableDetailsActivity extends AppCompatActivity {

    private String selectedScenery;
    private String selectedArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_details_page);

        // UI Components
        Button gardenButton = findViewById(R.id.garden_button);
        Button seasideButton = findViewById(R.id.seaside_button);
        Button insideButton = findViewById(R.id.inside_button);
        Button outsideButton = findViewById(R.id.outside_button);

        EditText numberOfSeats = findViewById(R.id.number_of_seats);

        Button breakfastButton = findViewById(R.id.breakfast_button);
        Button lunchButton = findViewById(R.id.lunch_button);
        Button dinnerButton = findViewById(R.id.dinner_button);

        selectedScenery = null;
        selectedArea = null;


        gardenButton.setOnClickListener(view -> { scenerySelect(gardenButton, seasideButton); });
        seasideButton.setOnClickListener(view -> { scenerySelect(seasideButton, gardenButton); });
        // create areaSelect

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

    // create areaSelect method
}
