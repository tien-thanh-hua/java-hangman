package gameframes;

import components.GameWindow;
import gamepanels.GameplayPanel;
import gamepanels.QuitPanel;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class QuitWindow extends GameWindow {
    
    private QuitPanel quitPanel;
    
    public QuitWindow(QuitPanel quitPanel) {
        
        this.quitPanel = quitPanel;
        this.add(this.quitPanel);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/hangman_128.png")));
        this.setTitle("Quit");
        
        // the code below must be in last and in this order
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}
