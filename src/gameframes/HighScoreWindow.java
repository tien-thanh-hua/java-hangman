package gameframes;

import components.GameWindow;
import gamepanels.HighScorePanel;
import java.awt.Toolkit;


public class HighScoreWindow extends GameWindow {
    
    private HighScorePanel highScorePanel;
    
    public HighScoreWindow(HighScorePanel highScorePanel) {
        
        this.highScorePanel = highScorePanel;
        this.add(this.highScorePanel);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/hangman_128.png")));
        this.setTitle("High Scores");
        
        // the code below must be in last and in this order
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}
