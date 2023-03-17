package gameframes;

import gamepanels.GameOverPanel;
import gamepanels.GameOverTop5Panel;
import gamepanels.GameplayPanel;
import gamepanels.SettingPanel;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class GameOverTop5Window extends JFrame {
    
    private GameOverTop5Panel gameOverTop5Panel;
    
    public GameOverTop5Window(GameOverTop5Panel gameOverTop5Panel) {
        
        this.gameOverTop5Panel = gameOverTop5Panel;
        this.add(this.gameOverTop5Panel);
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
