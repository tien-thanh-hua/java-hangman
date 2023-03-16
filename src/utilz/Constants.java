package utilz;

import java.awt.Font;
import main.Game;

public class Constants {
    
    public static class GameConstants {
        
        public static final int DEFAULT_GAME_WIDTH = 640;
        public static final int DEFAULT_GAME_HEIGHT = 360;
        public static final int DEFAULT_MENU_WIDTH = 240;
        public static final int DEFAULT_MENU_HEIGHT = 260;
        public static final int DEFAULT_PADDING = 6;
        public static final int DEFAULT_NORMAL_LABEL_WIDTH = 60;
        public static final int DEFAULT_LONG_LABEL_WIDTH = 228;
        public static final int DEFAULT_LABEL_HEIGHT = 16;
        public static final int DEFAULT_CHAR_BUTTON_SIZE = 32;
        public static final int DEFAULT_FONT_SIZE = 8;
        public static final int DEFAULT_HANGMAN_PANEL_WIDTH = 300;
        public static final int DEFAULT_HANGMAN_PANEL_HEGIHT = 264;
        public static final int DEFAULT_INFO_PANEL_WIDTH = 300;
        public static final int DEFAULT_INFO_PANEL_HEIGHT = 78;
        
        public static final int DEFAULT_THEME = 0;
        public static final int DARK_THEME = 1;
        public static final int HORROR_THEME = 2;
        
        public static final int MAINMENU = 0;
        public static final int GAMEPLAY = 1;
        public static final int HOW_TO_PLAY = 2;
        public static final int ABOUT_US = 3;
        public static final int SETTING = 4;
        public static final int QUIT = 5;
        public static final int BACK = 6;
        public static final int CLOSE = 7;
        public static final int DIFFICULTY = 8;
        public static final int GAME_OVER = 9;
        public static final int HIGH_SCORE = 10;
        public static final int GAME_OVER_TOP5 = 11;
        
        public static final int TEXT_SECRET = 0;
        
        public static final Font DEFAULT_FONT = new Font("Consolas", Font.PLAIN , Game.FONT_SIZE);
    }
}

