package com.example.oceansbounty;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set logged_in to false during logout
        boolean logged_in = false;

        // in main menu, if not logged in, ask for log in before proceeding, check the boolean here first

        if(logged_in){
            // Jump to main menu if logged in
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {
            // Load the login layout
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
