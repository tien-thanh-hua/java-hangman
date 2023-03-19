package gamepanels;

import components.GamePanel;
import components.GameButton;
import components.GameLabel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import main.Game;
import static utilz.Constants.GameConstants.*;

public class MainmenuPanel extends GamePanel implements ActionListener {
    
    public MainmenuPanel(Game game) {
        this.game = game;
        game.updateProperties();
        this.setLayout(null);
        Dimension dimension = new Dimension(Game.MENU_WIDTH, Game.MENU_HEIGHT);
        this.setPreferredSize(dimension);
        initComponents();
    }

    private void initComponents() {

        lblTitleTxt = new GameLabel("H A N G M A N", TITLE);
        lblTitleTxt.setBounds(Game.PADDING, Game.PADDING + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblTitleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnPlay = new GameButton("New Game");
        btnPlay.setBounds(Game.PADDING, Game.PADDING * 2 + Game.LABEL_HEIGHT + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnPlay.setHorizontalAlignment(SwingConstants.CENTER);
        btnPlay.addActionListener(this);
        
        btnGuide = new GameButton("How to play");
        btnGuide.setBounds(Game.PADDING, Game.PADDING * 3 + Game.LABEL_HEIGHT * 2 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnGuide.setHorizontalAlignment(SwingConstants.CENTER);
        btnGuide.addActionListener(this);
        
        btnAbout = new GameButton("About us");
        btnAbout.setBounds(Game.PADDING, Game.PADDING * 4 + Game.LABEL_HEIGHT * 3 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnAbout.setHorizontalAlignment(SwingConstants.CENTER);
        btnAbout.addActionListener(this);

        btnQuit = new GameButton("Quit");
        btnQuit.setBounds(Game.PADDING, Game.PADDING * 5 + Game.LABEL_HEIGHT * 4 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnQuit.setHorizontalAlignment(SwingConstants.CENTER);
        btnQuit.addActionListener(this);
        
        this.add(lblTitleTxt);
        this.add(btnPlay);
        this.add(btnGuide);
        this.add(btnAbout);
        this.add(btnQuit);
        
        if (Game.GAME_STATE == MENU) {
            btnBack = new GameButton("Back");
            btnBack.setBounds(Game.PADDING, Game.PADDING * 6 + Game.LABEL_HEIGHT * 5 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
            btnBack.addActionListener(this);
            this.add(btnBack);
        }
    }
    
    // Variables declaration
    private Game game;
    private GameLabel lblTitleTxt;
    private GameButton btnPlay;
    private GameButton btnGuide;
    private GameButton btnAbout;
    private GameButton btnQuit;
    private GameButton btnBack;
    // Variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnPlay) {
            if (Game.GAME_STATE == MENU) {
                if (JOptionPane.showConfirmDialog(this, "Current game would not be save",
                    "Do you want to start a new game?", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {
                    game.GAME_STATE = DIFFICULTY;
                    game.update();
                }
            } else {
                Game.GAME_STATE = DIFFICULTY;
                game.update();
            }
        }
        if (evt.getSource() == btnGuide) {
            Game.GAME_STATE = HOW_TO_PLAY;
            game.update();
        }
        if (evt.getSource() == btnAbout) {
            Game.GAME_STATE = ABOUT_US;
            game.update();
        }
        if (evt.getSource() == btnQuit) {
            Game.GAME_STATE = QUIT;
            game.update();
        }
        if (evt.getSource() == btnBack) {
            game.getMainmenuWindow().dispose();
        }
    }
}
