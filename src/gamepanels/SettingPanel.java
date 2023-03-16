package gamepanels;

import components.GameButton;
import components.GameLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import main.Game;
import static utilz.Constants.GameConstants.GAMEPLAY;

public class SettingPanel extends JPanel implements ActionListener {
    
    public SettingPanel(Game game) {
        this.game = game;
        this.setLayout(null);
        Dimension dimension = new Dimension(Game.MENU_WIDTH, Game.MENU_HEIGHT);
        this.setPreferredSize(dimension);
        initComponents();
    }

    private void initComponents() {

        lblTitleTxt = new GameLabel("H A N G M A N");
        lblTitleTxt.setBounds(Game.PADDING, Game.PADDING, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblTitleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnPlay = new GameButton("Play");
        btnPlay.setBounds(Game.PADDING, Game.PADDING * 2 + Game.LABEL_HEIGHT, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnPlay.setHorizontalAlignment(SwingConstants.CENTER);
        btnPlay.addActionListener(this);
        
        btnGuide = new GameButton("How to play");
        btnGuide.setBounds(Game.PADDING, Game.PADDING * 3 + Game.LABEL_HEIGHT * 2, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnGuide.setHorizontalAlignment(SwingConstants.CENTER);
        btnGuide.addActionListener(this);
        
        btnAbout = new GameButton("About us");
        btnAbout.setBounds(Game.PADDING, Game.PADDING * 4 + Game.LABEL_HEIGHT * 3, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnAbout.setHorizontalAlignment(SwingConstants.CENTER);
        btnAbout.addActionListener(this);
        
        btnTheme = new GameButton("Change theme");
        btnTheme.setBounds(Game.PADDING, Game.PADDING * 5 + Game.LABEL_HEIGHT * 4, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnTheme.setHorizontalAlignment(SwingConstants.CENTER);
        btnTheme.addActionListener(this);
        
        btnQuit = new GameButton("Quit");
        btnQuit.setBounds(Game.PADDING, Game.PADDING * 6 + Game.LABEL_HEIGHT * 5, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnQuit.setHorizontalAlignment(SwingConstants.CENTER);
        btnQuit.addActionListener(this);
        
        this.add(lblTitleTxt);
        this.add(btnPlay);
        this.add(btnGuide);
        this.add(btnAbout);
        this.add(btnTheme);
        this.add(btnQuit);
    }
    
    // Variables declaration
    private Game game;
    private GameLabel lblTitleTxt;
    private GameButton btnPlay;
    private GameButton btnGuide;
    private GameButton btnAbout;
    private GameButton btnTheme;
    private GameButton btnQuit;
    // Variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnPlay) {
            Game.GAME_STATE = GAMEPLAY;
            game.update();
        }
        if (evt.getSource() == btnGuide) {
            
        }
        if (evt.getSource() == btnAbout) {
            
        }
        if (evt.getSource() == btnTheme) {
            
        }
        if (evt.getSource() == btnQuit) {
            
        }
    }
}
