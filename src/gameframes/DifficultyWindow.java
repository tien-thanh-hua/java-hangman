package gameframes;

import components.GameWindow;
import gamepanels.DifficultyPanel;
import gamepanels.GameplayPanel;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class DifficultyWindow extends GameWindow {
    
    private DifficultyPanel difficultyPanel;
    
    public DifficultyWindow(DifficultyPanel difficultyPanel) {
        
        this.difficultyPanel = difficultyPanel;
        this.add(this.difficultyPanel);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/hangman_128.png")));
        this.setTitle("Choose Difficulty");
        
        // the code below must be in last and in this order
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}
