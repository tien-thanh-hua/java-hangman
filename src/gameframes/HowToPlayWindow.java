package gameframes;

import components.GameWindow;
import gamepanels.HowToPlayPanel;
import java.awt.Toolkit;

public class HowToPlayWindow extends GameWindow {
    
    private HowToPlayPanel howToPlayPanel;
    
    public HowToPlayWindow(HowToPlayPanel howToPlayPanel) {
        this.howToPlayPanel = howToPlayPanel;
        this.add(this.howToPlayPanel);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/hangman_128.png")));
        this.setTitle("How to Play");
        
        // the code below must be in last and in this order
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
