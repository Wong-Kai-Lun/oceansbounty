package com.example.oceansbounty;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean logged_in = false;
        // set logged_in to false during logout

        if(logged_in){
            // Jump to main menu if logged in
            setContentView(R.layout.main_menu_page);
        } else {
            // Load the login layout
            setContentView(R.layout.login_page);
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
