package gamepanels;

import components.GameButton;
import components.GameLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import main.Game;
import static utilz.Constants.GameConstants.BACK;
import static utilz.Constants.GameConstants.GAMEPLAY;

public class GameOverPanel extends JPanel implements ActionListener {
    
    public GameOverPanel(Game game) {
        this.game = game;
        this.setLayout(null);
        Dimension dimension = new Dimension(Game.MENU_WIDTH, Game.MENU_HEIGHT);
        this.setPreferredSize(dimension);
        initComponents();
    }

    private void initComponents() {

        lblTitleTxt = new GameLabel("GAME OVER");
        lblTitleTxt.setBounds(Game.PADDING, Game.PADDING + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblTitleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblHighScore = new GameLabel("High Score: " + game.getHighScore());
        lblHighScore.setBounds(Game.PADDING, Game.PADDING * 2 + Game.LABEL_HEIGHT + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblHighScore.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblYourScore = new GameLabel(game.getLblYourScore_dlgGameOverTop5());
        lblYourScore.setBounds(Game.PADDING, Game.PADDING * 3 + Game.LABEL_HEIGHT * 2 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblYourScore.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnBack = new GameButton("Back");
        btnBack.setBounds(Game.PADDING, Game.PADDING * 6 + Game.LABEL_HEIGHT * 5 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnBack.setHorizontalAlignment(SwingConstants.CENTER);
        btnBack.addActionListener(this);
        
        this.add(lblTitleTxt);
        this.add(lblHighScore);
        this.add(lblYourScore);
        this.add(btnBack);
    }
    
    // Variables declaration
    private Game game;
    private GameLabel lblTitleTxt;
    private GameLabel lblHighScore;
    private GameLabel lblYourScore;
    private GameButton btnBack;
    // Variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnBack) {
            Game.GAME_STATE = BACK;
            game.update();
        }
    }
}
