package gameframes;

import gamepanels.GameplayPanel;
import gamepanels.SettingPanel;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class SettingWindow extends JFrame {
    
    private SettingPanel settingPanel;
    
    public SettingWindow(SettingPanel settingPanel) {
        
        this.settingPanel = settingPanel;
        this.add(this.settingPanel);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/hangman_128.png")));
        this.setTitle("Settings");
        
        // the code below must be in last and in this order
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}
