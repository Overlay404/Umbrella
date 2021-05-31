package com.example.umbrella;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout li = (LinearLayout)findViewById(R.id.layautId);

        if (Custom.grey) {
            li.setBackgroundColor(Color.rgb(150, 151, 147));
        } else if (Custom.black) {
            li.setBackgroundColor(Color.BLACK);
        } else{
            li.setBackgroundColor(Color.rgb(157, 195, 215));
        }

        GameView gameView = new GameView(this); // создаём gameView


        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); // находим gameLayout
        gameLayout.addView(gameView); // и добавляем в него gameView
        Button res;

        res = findViewById(R.id.button);
        Button but_2 = (Button) findViewById(R.id.button2);

        but_2.setVisibility(View.GONE);

        if (GameView.check){
            but_2.setVisibility(View.VISIBLE);

        }

        res.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, Pause.class);
            startActivity(intent);

        });
        but_2.setOnClickListener(v -> {
            GameView.gameRunning = true;

        });



    }

}