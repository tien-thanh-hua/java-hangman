package gameframes;

import components.GameWindow;
import gamepanels.AboutUsPanel;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import main.Game;
import static utilz.Constants.GameConstants.BACK;


public class AboutUsWindow extends GameWindow implements WindowListener {
    
    private AboutUsPanel aboutUsPanel;
    
    public AboutUsWindow(AboutUsPanel aboutUsPanel) {
        
        this.aboutUsPanel = aboutUsPanel;
        this.add(this.aboutUsPanel);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/hangman_128.png")));
        this.setTitle("About us");
        this.addWindowListener(this);
        
        // the code below must be in last and in this order
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.dispose();
        Game.GAME_STATE = BACK;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        Game.GAME_STATE = BACK;
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
