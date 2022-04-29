package com.example.sirons_day1_game;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class GameObject {
    protected Bitmap[] image;
    private int x; //может использоваться только в этом классе
    private int y;
    protected int imageIndex = 0; //Может использваться классом и наследником в котором он обьявлён
    protected Rect hitbox;
    //класс хранит в себе функции методы переменные
    //метод функция которая принадлежит к классу
    public GameObject(Bitmap[] image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.hitbox = new Rect(x, y, x + width, y + height);
    }
    public GameObject(Bitmap image, int x, int y, int width, int height) {
        this(new Bitmap[] {image}, x, y, width, height);
    }
    public int getX(){ return x; }

    public  int getY( ){
        return y;
    }

    public void move (int dx, int dy) {
        this.x +=dx;
        this.y +=dy;
        this.hitbox.offset(dx, dy);

    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.hitbox.offsetTo(x, y);


    }
    public Rect getHitbox() {
        return  this.hitbox;
    }

    public void idle() {
        // DO NOtHING.
    }
    public Bitmap getImage() {
        return this.image[imageIndex % image.length];
    }



}
