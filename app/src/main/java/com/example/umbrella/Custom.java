package com.example.umbrella;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Custom extends AppCompatActivity {

    Button blue_b, black_b, grey_b, back, person_1, person_2;
    public static boolean blue = false;
    public static boolean black = false;
    public static boolean grey = false;
    public static boolean person = false;
    public static boolean person2 = false;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);

        blue_b = findViewById(R.id.blue);
        black_b = findViewById(R.id.black);
        grey_b = findViewById(R.id.grey);
        back = findViewById(R.id.back);
        person_1 = findViewById(R.id.person_1);
        person_2 = findViewById(R.id.person_2);





            blue_b.setOnClickListener(v ->
            {
                blue = true;
                black = false;
                grey = false;

            });
            black_b.setOnClickListener(v ->
            {
                blue = false;
                black = true;
                grey = false;
            });
            grey_b.setOnClickListener(v ->
            {
                blue = false;
                black = false;
                grey = true;
            });
            back.setOnClickListener(v ->
            {
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            });
            person_1.setOnClickListener(v -> {
                person = true;
                person2 = false;
            });
            person_2.setOnClickListener(v -> {
                person = false;
                person2 = true;
            });

        }
}

