package components;

import java.awt.Font;
import javax.swing.JTextArea;
import main.Game;

public class GameTextArea extends JTextArea {
    
    public GameTextArea(String text) {
        this.setText(text);
        this.setFont(new Font("Consolas", Font.PLAIN, Game.FONT_SIZE));
    }
}
