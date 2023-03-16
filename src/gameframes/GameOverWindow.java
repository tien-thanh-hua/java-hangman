package gameframes;

import gamepanels.GameOverPanel;
import gamepanels.GameplayPanel;
import gamepanels.SettingPanel;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class GameOverWindow extends JFrame {
    
    private GameOverPanel gameOverPanel;
    
    public GameOverWindow(GameOverPanel gameOverPanel) {
        
        this.gameOverPanel = gameOverPanel;
        this.add(this.gameOverPanel);
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
