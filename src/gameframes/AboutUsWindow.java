package gameframes;

import gamepanels.AboutUsPanel;
import gamepanels.GameplayPanel;
import gamepanels.MainmenuPanel;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class AboutUsWindow extends JFrame {
    
    private AboutUsPanel aboutUsPanel;
    
    public AboutUsWindow(AboutUsPanel aboutUsPanel) {
        
        this.aboutUsPanel = aboutUsPanel;
        this.add(this.aboutUsPanel);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/hangman_128.png")));
        this.setTitle("About us");
        
        // the code below must be in last and in this order
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}
