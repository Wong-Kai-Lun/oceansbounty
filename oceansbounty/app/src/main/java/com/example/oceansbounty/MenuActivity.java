package com.example.oceansbounty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_page);

        // UI Components
        Button bookButton = findViewById(R.id.book_button);

        // menuButton not needed since in MainMenu
        ImageButton inboxButton = findViewById(R.id.inbox_button);
        ImageButton profileButton = findViewById(R.id.profile_button);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });

        // Bottom Navigation Buttons
        inboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MenuActivity.this, InboxActivity.class);
                //startActivity(intent);
            }
        });

        inboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                //startActivity(intent);
            }
        });

    }
}