package gamepanels;

import components.GamePanel;
import components.GameButton;
import components.GameLabel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import main.Game;
import static utilz.Constants.GameConstants.*;

public class DifficultyPanel extends GamePanel implements ActionListener {
    
    public DifficultyPanel(Game game) {
        this.game = game;
        game.updateProperties();
        this.setLayout(null);
        Dimension dimension = new Dimension(Game.MENU_WIDTH, Game.MENU_HEIGHT);
        this.setPreferredSize(dimension);
        initComponents();
    }

    private void initComponents() {

        lblTitleTxt = new GameLabel("CHOOSE YOUR DIFFICULTY", TITLE);
        lblTitleTxt.setBounds(Game.PADDING, Game.PADDING + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblTitleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnEasy = new GameButton("Easy");
        btnEasy.setBounds(Game.PADDING, Game.PADDING * 2 + Game.LABEL_HEIGHT + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnEasy.setHorizontalAlignment(SwingConstants.CENTER);
        btnEasy.addActionListener(this);
        
        btnNormal = new GameButton("Normal");
        btnNormal.setBounds(Game.PADDING, Game.PADDING * 3 + Game.LABEL_HEIGHT * 2 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnNormal.setHorizontalAlignment(SwingConstants.CENTER);
        btnNormal.addActionListener(this);
        
        btnHard = new GameButton("Hard");
        btnHard.setBounds(Game.PADDING, Game.PADDING * 4 + Game.LABEL_HEIGHT * 3 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnHard.setHorizontalAlignment(SwingConstants.CENTER);
        btnHard.addActionListener(this);
        
        btnAsian = new GameButton("Asian");
        btnAsian.setBounds(Game.PADDING, Game.PADDING * 5 + Game.LABEL_HEIGHT * 4 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnAsian.setHorizontalAlignment(SwingConstants.CENTER);
        btnAsian.addActionListener(this);
        
        btnBack = new GameButton("Back");
        btnBack.setBounds(Game.PADDING, Game.PADDING * 6 + Game.LABEL_HEIGHT * 5 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnBack.setHorizontalAlignment(SwingConstants.CENTER);
        btnBack.addActionListener(this);
        
        this.add(lblTitleTxt);
        this.add(btnEasy);
        this.add(btnNormal);
        this.add(btnHard);
        this.add(btnAsian);
        this.add(btnBack);
    }
    
    // Variables declaration
    private Game game;
    private GameLabel lblTitleTxt;
    private GameButton btnEasy;
    private GameButton btnNormal;
    private GameButton btnHard;
    private GameButton btnAsian;
    private GameButton btnBack;
    // Variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnEasy) {
            btnDifficultyActionPerformed("easy");
        }
        if (evt.getSource() == btnNormal) {
            btnDifficultyActionPerformed("normal");
        }
        if (evt.getSource() == btnHard) {
            btnDifficultyActionPerformed("hard");
        }
        if (evt.getSource() == btnAsian) {
            btnDifficultyActionPerformed("asian");
        }
        if (evt.getSource() == btnBack) {
            Game.GAME_STATE = BACK;
            game.update();
        }
    }
    
    public void btnDifficultyActionPerformed(String difficulty) {
        game.setDifficulty(difficulty);
        game.setDifficultyFactor(difficulty);
        game.GAME_STATE = GAMEPLAY;
        game.update();
//        pnlDrawHangman.repaint();
    }
}
