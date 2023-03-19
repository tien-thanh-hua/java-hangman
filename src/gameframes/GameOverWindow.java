package gameframes;

import components.GameWindow;
import gamepanels.GameOverPanel;
import java.awt.Toolkit;


public class GameOverWindow extends GameWindow {
    
    
    public GameOverWindow(GameOverPanel gameOverPanel) {
        
        this.add(gameOverPanel);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/hangman_128.png")));
        this.setTitle("Game Over");
        this.setAlwaysOnTop(true);
        
        // the code below must be in last and in this order
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}
