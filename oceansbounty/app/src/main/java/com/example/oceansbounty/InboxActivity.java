package com.example.oceansbounty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class InboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbox_page);

        // UI Components
        SwitchCompat allowNotification = findViewById(R.id.allow_notification);
        ImageButton menuButton = findViewById(R.id.menu_button);
        ImageButton profileButton = findViewById(R.id.profile_button);


        // Bottom Navigation Buttons
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InboxActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(InboxActivity.this, ProfileActivity.class);
                //startActivity(intent);
            }
        });

    }
}