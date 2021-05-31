package com.example.umbrella;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button start, cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        start = findViewById(R.id.play);
        cus = findViewById(R.id.custom);


        start.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, MainMenu.class);
            startActivity(intent);
        });

        cus.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Custom.class);
            startActivity(intent);
        });


    }



}
