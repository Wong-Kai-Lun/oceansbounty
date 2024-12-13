package com.example.oceansbounty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the login layout
        setContentView(R.layout.activity_main);

        Button button_1 = findViewById(R.id.button_1);

        if (button_1 != null) {
            button_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View it) {
                    changeLayout("login_page");
                }
            });
        }
    }

    void changeLayout(String layoutName) {
        int layoutId = getResources().getIdentifier(layoutName, "layout", getPackageName());
        if (layoutId != 0) { // Check if the resource exists
            setContentView(layoutId);
        } else {
            // Handle the case where the layout name is invalid
            Toast.makeText(this, "Layout not found", Toast.LENGTH_SHORT).show();
        }
    }
}
