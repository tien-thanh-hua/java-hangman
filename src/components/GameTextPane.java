package components;

import javax.swing.JTextPane;
import static utilz.Constants.GameConstants.DEFAULT_FONT;


public class GameTextPane extends JTextPane {
    
    public GameTextPane() {
        this.setFont(DEFAULT_FONT);
        this.setEditable(false);
        this.setHighlighter(null);
    }
}
