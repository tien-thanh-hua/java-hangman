package gamepanels;

import components.GameButton;
import components.GameLabel;
import components.GameTextField;
import components.GameTextPane;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import main.Game;
import static utilz.Constants.GameConstants.BACK;
import static utilz.Constants.GameConstants.GAMEPLAY;

public class HighScorePanel extends JPanel implements ActionListener {
    
    public HighScorePanel(Game game) {
        this.game = game;
        this.setLayout(null);
        Dimension dimension = new Dimension(Game.MENU_WIDTH, Game.MENU_HEIGHT);
        this.setPreferredSize(dimension);
        initComponents();
    }

    private void initComponents() {

        lblGameOver_dlgGameOverTop5 = new GameLabel("HIGHT SCORE");
        lblGameOver_dlgGameOverTop5.setBounds(Game.PADDING, Game.PADDING + (int) (Game.SCALE * 61), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblGameOver_dlgGameOverTop5.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblTop5 = new GameLabel("Top 5 Scores");
        lblTop5.setBounds(Game.PADDING, Game.PADDING * 2 + (int) (Game.SCALE * 61) + Game.LABEL_HEIGHT, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblTop5.setHorizontalAlignment(SwingConstants.CENTER);
        
        txtPlayerNames_dlgGameOverTop5 = new GameTextPane();
        txtPlayerNames_dlgGameOverTop5.setText(game.getTxtPlayerNames_dlgGameOverTop5());
        txtPlayerNames_dlgGameOverTop5.setBounds((int) (Game.SCALE * 27), Game.PADDING * 3 + (int) (Game.SCALE * 61) + Game.LABEL_HEIGHT * 2, (int) (Game.SCALE * 90), Game.LABEL_HEIGHT * 5);
        setAlignmentRight(txtPlayerNames_dlgGameOverTop5);
        changeLineSpacing(txtPlayerNames_dlgGameOverTop5, Game.SCALE * 0.3f, true);
        
        txtPlayerScores_dlgGameOverTop5 = new GameTextPane();
        txtPlayerScores_dlgGameOverTop5.setText(game.getTop5Scores());
        txtPlayerScores_dlgGameOverTop5.setBounds((int) (Game.SCALE * 123), Game.PADDING * 3 + (int) (Game.SCALE * 61) + Game.LABEL_HEIGHT * 2,(int) (Game.SCALE * 90), Game.LABEL_HEIGHT * 5);
        changeLineSpacing(txtPlayerScores_dlgGameOverTop5, Game.SCALE * 0.3f, true);

        btnBack = new GameButton("Back");
        btnBack.setHorizontalAlignment(SwingConstants.CENTER);
        btnBack.setBounds(Game.PADDING, Game.PADDING * 4 + (int) (Game.SCALE * 61) + Game.LABEL_HEIGHT * 7, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnBack.addActionListener(this);
        
        this.add(lblGameOver_dlgGameOverTop5);
        this.add(lblTop5);
        this.add(txtPlayerNames_dlgGameOverTop5);
        this.add(txtPlayerScores_dlgGameOverTop5);
        this.add(btnBack);
    }
    
    // Variables declaration
    private Game game;
    private GameLabel lblGameOver_dlgGameOverTop5;
    private GameLabel lblTop5;
    private GameTextPane txtPlayerScores_dlgGameOverTop5;
    private GameTextPane txtPlayerNames_dlgGameOverTop5;
    private GameButton btnBack;
    // Variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnBack) {
            game.GAME_STATE = BACK;
            game.update();
        }
    }

    private void setAlignmentRight(GameTextPane txtPane) {
        StyledDocument doc = txtPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }
    
    private void changeLineSpacing(GameTextPane pane, float factor, boolean replace) {
        pane.selectAll();
        MutableAttributeSet set = new SimpleAttributeSet(pane.getParagraphAttributes());
        StyleConstants.setLineSpacing(set, factor);
        pane.setParagraphAttributes(set, replace);
    }
}
