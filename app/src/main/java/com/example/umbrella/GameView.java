package com.example.umbrella;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    public static int maxX = 18; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали

    private boolean firstTime = true;
    public static boolean gameRunning = true;
    private Umbrella umbrella;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    public ArrayList<SmallCloud> smallClouds = new ArrayList<>(); // тут будут харанится облака
    public ArrayList<BigCloud> bigClouds = new ArrayList<>(); // тут будут харанится облака
    private final int SMALL_CLOUD_INTERVAL = 100; // время через которое появляются облака (в итерациях)
    private int currentTime = 0;
    public Random random = new Random();
    private int points = 0;
    public static Cursor record;





    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) ;
        {

            if (event.getX() > getWidth() / 2.0) {
                umbrella.x += umbrella.speed;
            } else {
                umbrella.x -= umbrella.speed;
            }


        }

        return super.onTouchEvent(event);
        }






    public GameView(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewCloud();
            control();
            checkRecord();
        }

    }


    public void checkRecord(){

        DataBase database = new DataBase(getContext());

        SQLiteDatabase bd = database.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBase.KEY_INT, 0);

        bd.insert(DataBase.TABLE_CONTACTS, null, contentValues);
        try {
            record = bd.rawQuery("SELECT REC FROM record ORDER BY REC DESC LIMIT 1", null);
            record.moveToFirst();
        }catch (Exception e){

        }
    }

    private void update() {
        if(!firstTime) {
            umbrella.update();
            for (SmallCloud smallCloud : smallClouds) {
                smallCloud.update();
            } for (BigCloud bigCloud : bigClouds) {
                bigCloud.update();
            }
        }
    }



    @SuppressLint("ResourceAsColor")
    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if (firstTime) { // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width() / maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height() / maxY;

                umbrella = new Umbrella(getContext()); // добавляем корабль
            }


            canvas = surfaceHolder.lockCanvas(); // закрываем canvas

            canvas.drawColor(Color.rgb(157, 195, 215)); // заполняем фон

            if (Custom.grey) {
                canvas.drawColor(Color.rgb(150, 151, 147)); // заполняем фон
            } else if (Custom.black) {
                canvas.drawColor(Color.BLACK); // заполняем фон
            } else if(Custom.blue) {
                canvas.drawColor(Color.rgb(157, 195, 215)); // заполняем фон

            }


            umbrella.draw(paint, canvas); // рисуем корабль

            for (BigCloud bigCloud : bigClouds) { // рисуем облака
                bigCloud.draw(paint, canvas);
            }
            for (SmallCloud smallCloud : smallClouds) { // рисуем облака
                smallCloud.draw(paint, canvas);
            }



            paint.setTextSize(80.0f);
            paint.setColor(Color.WHITE);
            paint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL));

            canvas.drawText(points + "", unitW, 70, paint);

            if (!gameRunning) {
                canvas.drawText("YOU LOSE!", 380, 1000, paint);
                canvas.drawText("Record: " + record.getString(0), 380 , 70, paint);
            }

            try {
                if (gameRunning) {
                    canvas.drawText("Record: " + record.getString(0), 600, 70, paint);
                }

            }catch (Exception e){

            }

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }


    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("Recycle")
    private void checkCollision(){   // перебираем все астероиды и проверяем не касается ли один из них корабля

        DataBase database = new DataBase(getContext());

        SQLiteDatabase bd = database.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        for (SmallCloud smallCloud : smallClouds) {
            if(smallCloud.isCollision(umbrella.x, umbrella.y, umbrella.sizeW-2)){
                // игрок проиграл

                if (Integer.parseInt(record.getString(0)) < points){
                    contentValues.put("rec", points);
                    bd.update(DataBase.TABLE_CONTACTS, contentValues, "rec = ?", new String[] {record.getString(0)});
                }
                gameRunning = false; // останавливаем игру
                draw();



            }
        } for (BigCloud bigCloud : bigClouds) {
            if(bigCloud.isCollision(umbrella.x, umbrella.y, umbrella.sizeW -2)){
                // игрок проиграл
                gameRunning = false; // останавливаем игру
                draw();



            }
        }
    }

    private void checkIfNewCloud() { // каждые 150 и 250 итераций добавляем новое облако

        if (currentTime >= SMALL_CLOUD_INTERVAL) {


            points = smallClouds.size() + bigClouds.size();


            int i = random.nextInt(100);
            if (i < 65) {
                SmallCloud smallCloud = new SmallCloud(getContext());
                smallClouds.add(smallCloud);
            } else {
                BigCloud bigCloud = new BigCloud(getContext());
                bigClouds.add(bigCloud);
            }
            currentTime = 0;
        } else {
            currentTime++;
        }
    }

}