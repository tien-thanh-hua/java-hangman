package gameframes;

import components.GameWindow;
import gamepanels.MainmenuPanel;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.Game;
import static utilz.Constants.GameConstants.MAINMENU;


public class MainmenuWindow extends GameWindow implements WindowListener {
    
    private MainmenuPanel mainmenuPanel;
    
    public MainmenuWindow(MainmenuPanel mainmenuPanel) {
        
        this.mainmenuPanel = mainmenuPanel;
        this.add(this.mainmenuPanel);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/hangman_128.png")));
        this.setTitle("Hangman!");
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
        if (Game.GAME_STATE == MAINMENU) {
            int result = JOptionPane.showConfirmDialog(this,
                "Do you want to Quit ?", "Quit Confirmation : ",
                JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Goodbye and see you again", "Thanks for playing", 1);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else {
                this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
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