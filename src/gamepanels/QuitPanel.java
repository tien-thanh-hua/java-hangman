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

public class QuitPanel extends GamePanel implements ActionListener {
    
    public QuitPanel(Game game) {
        this.game = game;
        game.updateProperties();
        this.setLayout(null);
        Dimension dimension = new Dimension(Game.MENU_WIDTH, Game.MENU_HEIGHT);
        this.setPreferredSize(dimension);
        initComponents();
    }

    private void initComponents() {

        lblTitleTxt = new GameLabel("DO YOU REALLY WANT TO QUIT?", TITLE);
        lblTitleTxt.setBounds(Game.PADDING, Game.PADDING + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblTitleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblMessageTxt = new GameLabel("H A N G M A N WILL MISS YOU");
        lblMessageTxt.setBounds(Game.PADDING, Game.PADDING * 2 + Game.LABEL_HEIGHT + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblMessageTxt.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnBack = new GameButton("NOOOOO");
        btnBack.setBounds(Game.PADDING, Game.PADDING * 3 + Game.LABEL_HEIGHT * 2 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnBack.setHorizontalAlignment(SwingConstants.CENTER);
        btnBack.addActionListener(this);
        
        btnQuit = new GameButton("I SAY QUIT!");
        btnQuit.setBounds(Game.PADDING, Game.PADDING * 4 + Game.LABEL_HEIGHT * 3 + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnQuit.setHorizontalAlignment(SwingConstants.CENTER);
        btnQuit.addActionListener(this);
        
        this.add(lblTitleTxt);
        this.add(lblMessageTxt);
        this.add(btnBack);
        this.add(btnQuit);
    }
    
    // Variables declaration
    private Game game;
    private GameLabel lblTitleTxt;
    private GameLabel lblMessageTxt;
    private GameButton btnBack;
    private GameButton btnQuit;
    // Variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnBack) {
            game.getQuitWindow().dispose();
            game.getMainmenuWindow().setVisible(true);
        }
        if (evt.getSource() == btnQuit) {
            Game.GAME_STATE = CLOSE;
            game.update();
        }
    }
}
