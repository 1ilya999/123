package com.example.sirons_day1_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameWorldLoader {
        private Context context;
        private  Bitmap tiles;
        private  Bitmap level1Background;
        private Bitmap doorImage;
        private Bitmap level1Floor;
        private Bitmap wallImage;



        public int pxFromDp(int dp) {
                return (int) (dp * context.getResources().getDisplayMetrics().density);
        }

        public GameWorldLoader(Context context){
                this.context = context;
                tiles = BitmapFactory.decodeResource(context.getResources(), R.drawable.egatiles);
                int x = pxFromDp(0);
                int y = pxFromDp(179);
                int s = pxFromDp(16);
                level1Background = Bitmap.createBitmap(tiles, x, y, s, s);

                level1Floor = Bitmap.createBitmap(tiles,
                        pxFromDp( 192),
                        pxFromDp( 48),
                        s,
                        s);
                doorImage = Bitmap.createBitmap(tiles,
                        pxFromDp(65),
                        pxFromDp(304),
                        pxFromDp(32),
                        pxFromDp(48));

                wallImage = Bitmap.createBitmap(tiles,
                        pxFromDp(144),
                        pxFromDp(80),
                        pxFromDp(16),
                        pxFromDp(16));
        }

        /**
         * Метод загрузки тайлов игрока
         * @return массив картинок
         */
        private Bitmap[] loadPlayerTies() {
               Bitmap playerTiles = BitmapFactory.decodeResource(context.getResources(), R.drawable.s_dave_fixed);
                Bitmap[] playerImages = new Bitmap[17];
                for (int i = 0; i < playerImages.length; i++) {
                        playerImages[i] = Bitmap.createBitmap(playerTiles,
                                pxFromDp(32 * i),
                                pxFromDp(32),
                                pxFromDp(32),
                                pxFromDp(32));
                }
                return playerImages;
        }
        private void loadLevel(String[] level, GameWorld world) {
                int y = 0;
                for (String s: level) {
                        int x = 0;
                        for ( int i= 0; i < s.length(); i++) {
                                char ch = s.charAt(i);
                                switch (ch) {
                                        case '=':
                                                world.addObject(new Floor(level1Floor, x, y,
                                                        pxFromDp(16),
                                                        pxFromDp(16)));
                                                break;
                                        case 'D':
                                                world.addObject(new Door(doorImage, x, y - 92,
                                                        pxFromDp(32),
                                                        pxFromDp(48)));
                                                break;
                                        case 'P':
                                                world.addObject(
                                                        new Player(loadPlayerTies(),
                                                                x,
                                                                y-52,
                                                                pxFromDp(32),
                                                                pxFromDp(32)));
                                                break;
                                        case '|':
                                                world.addObject(new Wall(wallImage, x-10, y,
                                                        pxFromDp(16),
                                                        pxFromDp(16)));
                                }
                                x+=32;

                        }
                        y += 32;
                }
        }

        public GameWorld load(String[] level) {
                GameWorld world = new GameWorld(level1Background, level1Floor);
                loadLevel(level, world );
                return world;

        }
}
