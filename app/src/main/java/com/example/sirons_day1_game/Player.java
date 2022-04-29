package com.example.sirons_day1_game;

import android.graphics.Bitmap;

public class Player extends GameObject{ //наседование
    enum State { //перечисление
        IDLE_LEFT,
        IDLE_RIGHT,
        RUNNING_LEFT,
        RUNNING_RIGHT
    }
    private State state = State.RUNNING_RIGHT;


    public Player(Bitmap[] image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
        imageIndex = 1;
    }

    public void idle() {
        switch (state){
            case RUNNING_RIGHT:
                imageIndex = 0;
                state = State.IDLE_RIGHT;
                break;
            case RUNNING_LEFT:
                imageIndex = 7;
                state = State.IDLE_LEFT;
                break;
        }
    }

   public void move(int dx, int dy) {
       super.move(dx, dy);
       imageIndex++;
       switch (state) {
           case RUNNING_RIGHT:
               if (imageIndex == 4) {
                   imageIndex = 1;
               }
               if (dx< 0) {
                   state = State.RUNNING_LEFT;
                   imageIndex = 8;
               }
               break;
           case RUNNING_LEFT:
               if(imageIndex == 12) {
                   imageIndex = 8;
               }
               if (dx > 0) {
                   state = State.RUNNING_RIGHT;
                   imageIndex = 1;
               }
               break;
           case IDLE_RIGHT:
           case IDLE_LEFT:
               if(dx < 0) {
                   imageIndex = 8;
                   state = State.RUNNING_LEFT;
               } else  if (dx > 0) {
                   imageIndex = 1;
                   state= State.RUNNING_RIGHT;
               }
               break;

       }
   }

}
