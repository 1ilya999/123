package com.example.sirons_day1_game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class GameWorld {
    //список игровых обьектов
    private ArrayList<GameObject> objects = new ArrayList<>(); //создается новый экземпляр класса
    private Bitmap background;
    private Player player;
    private Bitmap floor;

    //Конструктор класса
    public GameWorld( Bitmap background, Bitmap floor) {
        this.background = background;
        this.floor = floor;

    }

    //Метед добавления игровоого обьекта в мир
    public void addObject(GameObject object) {

        objects.add(object);
        if(object instanceof Player) {
            objects.add(0, object); // был на позиции в начале массива, после отрисовки мира
            this.player = (Player) object;
        } else {
            objects.add(object);
        }
    }
    public  GameObject getPlayer(){
        return  this.player;
    }

    /**
     * Метод проверки столкновений.
     * @param object -- Объект, который мы хотим проверить.
     * @return Объект, с которым найдено столкновение.
     */

    public ArrayList<GameObject >getHit(GameObject object) {
        Rect objectHiBox = object.getHitbox();
        ArrayList<GameObject> result = new ArrayList<>();
        for (GameObject obj: objects) {
            Rect hitbox = obj.getHitbox();
            if((obj != object) && Rect.intersects(hitbox, objectHiBox)) {
              result.add(obj) ;
            }
        }
        return result;
    }

    public void movePlayer(int dx, int dy ) {
        int oldX = player.getX();
        int oldY = player.getY();
        player.move(dx, dy);
       ArrayList <GameObject> hits = getHit(player);
        for(GameObject obj:hits) {
            if(obj instanceof Floor | obj instanceof Wall) {
                player.setPosition(oldX, oldY);
            }
        }
    }


    //Метод отрисовки игрового мира в холсте
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        int tileSize = background.getHeight();
        for (int y = 0; y < canvasHeight; y += tileSize) {

                for (int x = 0; x < canvasWidth; x += tileSize) {
                    canvas.drawBitmap(background, x, y, p);
                }
            }
        Paint hitboxPaint = new Paint();
        hitboxPaint.setColor(Color.RED);//Цвет
        hitboxPaint.setStrokeWidth(3); //Толщина линии
        hitboxPaint.setStyle(Paint.Style.STROKE);// стиль отрисовки (толко обводка, без заливки)

        for (int i = 1; i<objects.size();i++) {
            GameObject obj = objects.get(i);
            canvas.drawBitmap(obj.getImage(), obj.getX(), obj.getY(), p);
            canvas.drawRect(obj.getHitbox(), hitboxPaint);
        }
        canvas.drawBitmap(player.getImage(), player.getX(), player.getY(), p);
        canvas.drawRect(player.getHitbox(), hitboxPaint );
    }
}

