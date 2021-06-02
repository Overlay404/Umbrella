package com.example.umbrella;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Umbrella extends SpaceBody {

    public Umbrella(Context context) {
        bitmapId = R.drawable.umbrella;
        if (Custom.person2) {
            bitmapId = R.drawable.umbrella_2; // определяем начальные параметры
        }else if(Custom.person){
                bitmapId = R.drawable.umbrella;
        }
        sizeW = 3;
        sizeH = 3;
        x = 7;
        y = GameView.maxY - sizeW - 5;
        speed = (float) 0.4;

        init(context); // инициализируем корабль
    }

    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки

    }
}
