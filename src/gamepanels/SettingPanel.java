package gamepanels;

import components.GameButton;
import components.GameLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import main.Game;
import static utilz.Constants.GameConstants.*;

public class SettingPanel extends JPanel implements ActionListener {
    
    public SettingPanel(Game game) {
        this.game = game;
        game.updateProperties();
        this.setLayout(null);
        Dimension dimension = new Dimension(Game.MENU_WIDTH, Game.MENU_HEIGHT);
        this.setPreferredSize(dimension);
        initComponents();
    }

    private void initComponents() {

        lblTitleTxt = new GameLabel("SETTINGS", TITLE);
        lblTitleTxt.setBounds(Game.PADDING, Game.PADDING, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblTitleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnScale = new GameButton("Scale");
        btnScale.setBounds(Game.PADDING, Game.PADDING * 2 + Game.LABEL_HEIGHT, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnScale.setHorizontalAlignment(SwingConstants.CENTER);
        btnScale.addActionListener(this);
//        
//        btnGuide = new GameButton("How to play");
//        btnGuide.setBounds(Game.PADDING, Game.PADDING * 3 + Game.LABEL_HEIGHT * 2, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
//        btnGuide.setHorizontalAlignment(SwingConstants.CENTER);
//        btnGuide.addActionListener(this);
//        
//        btnAbout = new GameButton("About us");
//        btnAbout.setBounds(Game.PADDING, Game.PADDING * 4 + Game.LABEL_HEIGHT * 3, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
//        btnAbout.setHorizontalAlignment(SwingConstants.CENTER);
//        btnAbout.addActionListener(this);
//        
//        btnTheme = new GameButton("Change theme");
//        btnTheme.setBounds(Game.PADDING, Game.PADDING * 5 + Game.LABEL_HEIGHT * 4, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
//        btnTheme.setHorizontalAlignment(SwingConstants.CENTER);
//        btnTheme.addActionListener(this);
//        
//        btnQuit = new GameButton("Quit");
//        btnQuit.setBounds(Game.PADDING, Game.PADDING * 6 + Game.LABEL_HEIGHT * 5, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
//        btnQuit.setHorizontalAlignment(SwingConstants.CENTER);
//        btnQuit.addActionListener(this);
        
        this.add(lblTitleTxt);
        this.add(btnScale);
    }
    
    // Variables declaration
    private Game game;
    private GameLabel lblTitleTxt;
    private GameButton btnScale;
    // Variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
       if (evt.getSource() == btnScale) {
           changeScale();
       }
    }

    private void changeScale() {
        try {
            game.SCALE = Float.parseFloat(JOptionPane.showInputDialog(this, "Current scale: " + game.SCALE, ""));
            game.GAME_STATE = BACK;
            game.update();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nothing has changed", "Message", 1);
        }
        
    }
}
