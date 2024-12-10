package com.example.oceansbounty;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the login layout
        setContentView(R.layout.login_page);
    }
}

// make a java class full of UI components UIComponents.java
// buttons and text fields can be manipulated better, like change how it looks on click