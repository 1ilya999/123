package com.example.sirons_day1_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class GameView extends View implements View.OnTouchListener {

   GameObject player;
   GameWorld world;
   Timer timer;
   private long counter = 0;
   final int PLAYER_STEP_SIZE = 10;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int height = this.getHeight();
        int width = this.getWidth();
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        if(y < (height / 3)) {
            world.movePlayer(0, -PLAYER_STEP_SIZE);
        } else if (y > (height - (height/3))) {
            world.movePlayer(0, PLAYER_STEP_SIZE);
        }

        if (x < (width/3)) {
            world.movePlayer(-PLAYER_STEP_SIZE, 0);
        } else if (x > (width - (width/ 3))) {
            world.movePlayer(PLAYER_STEP_SIZE ,0);
        }
        return true;
    }


    class UpdateGameViewTask extends TimerTask {
      private GameView gameView;

      public UpdateGameViewTask(GameView gameView) {
          this.gameView = gameView;
      }

      public void run() {
          counter++;
          if( counter % 100 == 0) {
              player.idle();
          }
          gameView.invalidate();
      }
  }


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        GameWorldLoader loader = new GameWorldLoader(context);
        String level[] = {
                "|================================|",
                "|                                |", //80
                "|                                |",
                "|                        ==      |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|       ==                       |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|        D            D          |",
                "|    ==========================  |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                        D       |",
                "|=============    ===============|",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "| P                  D           |",
                "|============================    |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|                                |",
                "|================================|",



        };
        world = loader.load(level);
        player = world.getPlayer();
        timer = new Timer();
        UpdateGameViewTask task = new UpdateGameViewTask(this);
        final int FPS = 40;
        timer.scheduleAtFixedRate(task, 0, 1000/FPS);
        this.setOnTouchListener(this);
    }

    public void onDraw(Canvas canvas) {

       world.draw(canvas);

    }
}


