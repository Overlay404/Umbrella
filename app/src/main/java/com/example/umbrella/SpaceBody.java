package com.example.umbrella;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class SpaceBody {
    public float x; // координаты
    public float y;
    public float sizeW; // размер
    public float sizeH; // размер
    public float speed; // скорость
    public int bitmapId; // id картинки
    public Bitmap bitmap; // картинка

    void init(Context context) { // сжимаем картинку до нужных размеров
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int)(sizeW * GameView.unitW), (int)(sizeH * GameView.unitH), false);
        cBitmap.recycle();
    }

    void update(){ // тут будут вычисляться новые координаты
    }

    void draw(Paint paint, Canvas canvas){ // рисуем картинку
        canvas.drawBitmap(bitmap, x* GameView.unitW, y* GameView.unitH, paint);
    }
}
