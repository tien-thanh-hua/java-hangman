package gamepanels;

import components.GameButton;
import components.GameLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import main.Game;
import static utilz.Constants.GameConstants.*;

public class MainmenuPanel extends JPanel implements ActionListener {
    
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
        
        btnPlay = new GameButton("Play");
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
        
        btnSetting = new GameButton("Settings");
        btnSetting.setBounds(Game.PADDING, Game.PADDING * 5 + Game.LABEL_HEIGHT * 4 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnSetting.setHorizontalAlignment(SwingConstants.CENTER);
        btnSetting.addActionListener(this);
        
        btnQuit = new GameButton("Quit");
        btnQuit.setBounds(Game.PADDING, Game.PADDING * 6 + Game.LABEL_HEIGHT * 5 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnQuit.setHorizontalAlignment(SwingConstants.CENTER);
        btnQuit.addActionListener(this);
        
        this.add(lblTitleTxt);
        this.add(btnPlay);
        this.add(btnGuide);
        this.add(btnAbout);
        this.add(btnSetting);
        this.add(btnQuit);
    }
    
    // Variables declaration
    private Game game;
    private GameLabel lblTitleTxt;
    private GameButton btnPlay;
    private GameButton btnGuide;
    private GameButton btnAbout;
    private GameButton btnSetting;
    private GameButton btnQuit;
    // Variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnPlay) {
            Game.GAME_STATE = DIFFICULTY;
            game.update();
        }
        if (evt.getSource() == btnGuide) {
            Game.GAME_STATE = HOW_TO_PLAY;
            game.update();
        }
        if (evt.getSource() == btnAbout) {
            Game.GAME_STATE = ABOUT_US;
            game.update();
        }
        if (evt.getSource() == btnSetting) {
            Game.GAME_STATE = SETTING;
            game.update();
        }
        if (evt.getSource() == btnQuit) {
            Game.GAME_STATE = QUIT;
            game.update();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {

//        super.paintComponent(g);
//        g.drawImage(importImg("/images/hangman_512.png"), (int) (Game.SCALE * (120 - 64)),(int) (Game.SCALE * 160), (int) (Game.SCALE * 128), (int) (Game.SCALE * 128), null);
    }
    
    private BufferedImage importImg(String location) {
        BufferedImage img;
        img = null;
        InputStream is = getClass().getResourceAsStream(location);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
