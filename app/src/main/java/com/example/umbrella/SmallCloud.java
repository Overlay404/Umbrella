package com.example.umbrella;

import android.content.Context;

import java.util.Random;

public class SmallCloud extends SpaceBody {

    public SmallCloud(Context context) {
        Random random = new Random();

        bitmapId = R.drawable.smallcloud;
        y=0;
        // радиус
        int radius = 2;
        x = random.nextInt(GameView.maxX) - radius;
        sizeW = radius *2;
        sizeH = radius;
        // минимальная скорость
        float minSpeed = (float) 0.1;
        // максимальная скорость
        float maxSpeed = (float) 0.4;
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }

    @Override
    public void update() {
        y += speed;
    }

    public boolean isCollision(float cloudX, float cloudY, float cloudSize) {
        return !(((x+sizeW) < cloudX)||(x > (cloudX+cloudSize))||((y+sizeH) < cloudY)||(y > (cloudY+cloudSize)));
    }
}
