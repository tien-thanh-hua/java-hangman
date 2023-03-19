package gamepanels;

import components.GamePanel;
import components.GameButton;
import components.GameLabel;
import components.GameTextField;
import components.GameTextPane;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import main.Game;
import static utilz.Constants.GameConstants.*;

public class GameOverTop5Panel extends GamePanel implements ActionListener {
    
    public GameOverTop5Panel(Game game) {
        this.game = game;
        game.updateProperties();
        this.setLayout(null);
        Dimension dimension = new Dimension(Game.MENU_WIDTH, Game.MENU_HEIGHT);
        this.setPreferredSize(dimension);
        initComponents();
    }
    
    private void initComponents() {

        if (game.getWordIndex() >= game.getWords().size()) {
            lblGameOver_dlgGameOverTop5 = new GameLabel("CONGRATULATION", TITLE);
        } else {
            lblGameOver_dlgGameOverTop5 = new GameLabel("GAME OVER...", TITLE);
        }
        
        lblGameOver_dlgGameOverTop5.setBounds(Game.PADDING, Game.PADDING, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblGameOver_dlgGameOverTop5.setHorizontalAlignment(SwingConstants.CENTER);
        
        if (game.getWordIndex() >= game.getWords().size()) {
            lblSubtitle = new GameLabel("You have reached the highest level");
        } else {
            lblSubtitle = new GameLabel("but you've got a really high score!");
        }
        
        lblSubtitle.setBounds(Game.PADDING, Game.PADDING * 2 + Game.LABEL_HEIGHT, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblYourScore_dlgGameOverTop5 = new GameLabel(game.getLblYourScore_dlgGameOverTop5());
        lblYourScore_dlgGameOverTop5.setBounds(Game.PADDING, Game.PADDING * 3 + Game.LABEL_HEIGHT * 2, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblYourScore_dlgGameOverTop5.setHorizontalAlignment(SwingConstants.CENTER);
        
        txtNameInput = new GameTextField("");
        txtNameInput.setBounds(Game.PADDING, Game.PADDING * 4 + Game.LABEL_HEIGHT * 3, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        txtNameInput.setHorizontalAlignment(SwingConstants.CENTER);
        txtNameInput.addActionListener(this);
        
        btnSavePlayerName = new GameButton("Save Player Name");
        btnSavePlayerName.setBounds(Game.PADDING, Game.PADDING * 5 + Game.LABEL_HEIGHT * 4, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnSavePlayerName.setHorizontalAlignment(SwingConstants.CENTER);
        btnSavePlayerName.addActionListener(this);
        
        lblTop5 = new GameLabel("Top 5 Scores");
        lblTop5.setBounds(Game.PADDING, Game.PADDING * 6 + Game.LABEL_HEIGHT * 5, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblTop5.setHorizontalAlignment(SwingConstants.CENTER);
        
        txtPlayerNames_dlgGameOverTop5 = new GameTextPane();
        txtPlayerNames_dlgGameOverTop5.setText(game.getTxtPlayerNames_dlgGameOverTop5());
        txtPlayerNames_dlgGameOverTop5.setBounds((int) (Game.SCALE * 27), Game.PADDING * 7 + Game.LABEL_HEIGHT * 6, (int) (Game.SCALE * 90), Game.LABEL_HEIGHT * 5);
        setAlignmentRight(txtPlayerNames_dlgGameOverTop5);
        changeLineSpacing(txtPlayerNames_dlgGameOverTop5, Game.SCALE * 0.3f, true);
        
        txtPlayerScores_dlgGameOverTop5 = new GameTextPane();
        txtPlayerScores_dlgGameOverTop5.setText(game.getTop5Scores());
        txtPlayerScores_dlgGameOverTop5.setBounds((int) (Game.SCALE * 123), Game.PADDING * 7 + Game.LABEL_HEIGHT * 6,(int) (Game.SCALE * 90), Game.LABEL_HEIGHT * 5);
        changeLineSpacing(txtPlayerScores_dlgGameOverTop5, Game.SCALE * 0.3f, true);

        this.add(lblGameOver_dlgGameOverTop5);
        this.add(lblSubtitle);
        this.add(lblYourScore_dlgGameOverTop5);
        this.add(txtNameInput);
        this.add(btnSavePlayerName);
        this.add(lblTop5);
        this.add(txtPlayerNames_dlgGameOverTop5);
        this.add(txtPlayerScores_dlgGameOverTop5);
    }
    
    // Variables declaration
    private Game game;
    private GameLabel lblGameOver_dlgGameOverTop5;
    private GameLabel lblYourScore_dlgGameOverTop5;
    private GameLabel lblSubtitle;
    private GameLabel lblTop5;
    private GameButton btnSavePlayerName;
    private GameTextField txtNameInput;
    private GameTextPane txtPlayerScores_dlgGameOverTop5;
    private GameTextPane txtPlayerNames_dlgGameOverTop5;
    // Variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnSavePlayerName) {
            String name = txtNameInput.getText();
            if (name.length() <= 15 && !name.contains(" ") && !name.equals(null) && !name.equals("")) {
                game.savePlayer(name);
                game.updateScoreFile();
                JOptionPane.showMessageDialog(this,"Your Score have been saved!", "Successful",1);
                game.GAME_STATE = BACK;
                game.update();
            } else {
                JOptionPane.showMessageDialog(this, "Name must be have no space and less than 16 characters", "Invalid name", 1);
            }
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
