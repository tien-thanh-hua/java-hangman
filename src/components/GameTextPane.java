package components;

import java.awt.Font;
import javax.swing.JTextPane;
import main.Game;

public class GameTextPane extends JTextPane {
    
    public GameTextPane() {
        this.setFont(new Font("Consolas", Font.PLAIN, Game.FONT_SIZE));
        this.setEditable(false);
        this.setHighlighter(null);
    }
}
