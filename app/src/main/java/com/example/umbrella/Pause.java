package com.example.umbrella;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Pause extends AppCompatActivity {

    Button res, menu, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pause);

        back = findViewById(R.id.back1);
        res = findViewById(R.id.res);
        menu = findViewById(R.id.men);



        menu.setOnClickListener(v ->
        {
            Intent intent = new Intent(Pause.this, MainActivity.class);
            startActivity(intent);
            GameView.gameRunning = true;
        });
        res.setOnClickListener(v ->
        {
            Intent intent = new Intent(Pause.this, MainMenu.class);
            startActivity(intent);
            GameView.gameRunning = true;
        });
        back.setOnClickListener(v -> {

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            if (!GameView.gameRunning){
                Intent in = new Intent(Pause.this, MainMenu.class);
                startActivity(in);
                GameView.gameRunning = true;
            }
        });


    }
}
