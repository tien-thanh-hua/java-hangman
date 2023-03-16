package gameframes;

import gamepanels.GameplayPanel;
import gamepanels.HighScorePanel;
import gamepanels.SettingPanel;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class HighScoreWindow extends JFrame {
    
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
