package gamepanels;

import components.GameButton;
import components.GameLabel;
import entities.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import main.Game;
import ui.HangmanCanvas;
import static utilz.Constants.GameConstants.BACK;
import static utilz.Constants.GameConstants.GAME_OVER;
import static utilz.Constants.GameConstants.GAME_OVER_TOP5;
import static utilz.Constants.GameConstants.HIGH_SCORE;
import static utilz.Constants.GameConstants.TEXT_SECRET;

public class GameplayPanel extends JPanel implements ActionListener {
    
    public GameplayPanel(Game game) {
        this.game = game;
        this.setLayout(null);
        Dimension dimension = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setPreferredSize(dimension);
        initComponents();
        game.reset();
        resetFrame();
    }

    private void initComponents() {
        initDrawHangman();
        initPlayerInfo();
        initPlayArea();
        
        this.add(pnlDrawHangman);
        this.add(pnlPlayerInfo);
        this.add(pnlPlayArea);
    }

    private void initDrawHangman() {
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Hangman Painting");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleFont(new Font("Consolas", Font.PLAIN , Game.FONT_SIZE));
        
        pnlDrawHangman = new JPanel();
        pnlDrawHangman.setBounds(Game.PADDING, Game.PADDING, Game.HANGMAN_PANEL_WIDTH, Game.HANGMAN_PANEL_HEIGHT);
        pnlDrawHangman.setBorder(titledBorder);
        
        pnlDrawHangman.setLayout(null);
        pnlDrawHangman.removeAll();
        pnlDrawHangman.revalidate();
        pnlDrawHangman.repaint();

        hCanvas = new HangmanCanvas(game.getHangMan());
        pnlDrawHangman.setLayout(new BorderLayout());
        pnlDrawHangman.add(hCanvas, BorderLayout.CENTER);
    }

    private void initPlayerInfo() {
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Game Information");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleFont(new Font("Consolas", Font.PLAIN , Game.FONT_SIZE));
        
        pnlPlayerInfo = new JPanel();
        pnlPlayerInfo.setBounds(Game.PADDING, Game.PADDING * 2 + Game.HANGMAN_PANEL_HEIGHT, Game.INFO_PANEL_WIDTH, Game.INFO_PANEL_HEIGHT);
        pnlPlayerInfo.setBorder(titledBorder);
        pnlPlayerInfo.setLayout(null);
        
        lblTopicTxt = new GameLabel("Topic");
        lblTopicTxt.setBounds(Game.PADDING, Game.PADDING * 2, Game.NORMAL_LABEL_WIDTH, Game.LABEL_HEIGHT);
        
        lblTopic = new GameLabel("Random");
        lblTopic.setBounds(Game.PADDING * 2 + Game.NORMAL_LABEL_WIDTH, Game.PADDING * 2, Game.NORMAL_LABEL_WIDTH, Game.LABEL_HEIGHT);
        
        lblDiffTxt = new GameLabel("Difficulty");
        lblDiffTxt.setBounds(Game.PADDING, Game.PADDING * 3 + Game.LABEL_HEIGHT, Game.NORMAL_LABEL_WIDTH, Game.LABEL_HEIGHT);
        
        lblDifficulty = new GameLabel("Easy");
        lblDifficulty.setBounds(Game.PADDING * 2 + Game.NORMAL_LABEL_WIDTH, Game.PADDING * 3 + Game.LABEL_HEIGHT, Game.NORMAL_LABEL_WIDTH, Game.LABEL_HEIGHT);
        
        lblScoreTxt = new GameLabel("Score");
        lblScoreTxt.setBounds(Game.PADDING, Game.PADDING * 4 + Game.LABEL_HEIGHT * 2, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        
        lblPlayerScore = new GameLabel("0");
        lblPlayerScore.setBounds(Game.PADDING * 2 + Game.NORMAL_LABEL_WIDTH, Game.PADDING * 4 + Game.LABEL_HEIGHT * 2, Game.NORMAL_LABEL_WIDTH, Game.LABEL_HEIGHT);
        
        btnNextLevel = new GameButton("Next Level");
        btnNextLevel.setBounds(Game.HANGMAN_PANEL_WIDTH - Game.PADDING - Game.NORMAL_LABEL_WIDTH, Game.PADDING * 2, Game.NORMAL_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnNextLevel.addActionListener(this);
        
        btnHighScore = new GameButton("High Score");
        btnHighScore.setBounds(Game.HANGMAN_PANEL_WIDTH - Game.PADDING - Game.NORMAL_LABEL_WIDTH, Game.PADDING * 3 + Game.LABEL_HEIGHT, Game.NORMAL_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnHighScore.addActionListener(this);
        
        btnNewGame = new GameButton("Restart");
        btnNewGame.setBounds(Game.HANGMAN_PANEL_WIDTH - Game.PADDING - Game.NORMAL_LABEL_WIDTH, Game.PADDING * 4 + Game.LABEL_HEIGHT * 2, Game.NORMAL_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnNewGame.addActionListener(this);
        
        pnlPlayerInfo.add(lblTopicTxt);
        pnlPlayerInfo.add(lblDiffTxt);
        pnlPlayerInfo.add(lblScoreTxt);
        
        pnlPlayerInfo.add(lblTopic);
        pnlPlayerInfo.add(lblDifficulty);
        pnlPlayerInfo.add(lblPlayerScore);
        
        pnlPlayerInfo.add(btnNewGame);
        pnlPlayerInfo.add(btnHighScore);
        pnlPlayerInfo.add(btnNextLevel);
        
        updateLblTopic();
        btnNextLevel.setEnabled(false);
    }

    private void initPlayArea() {
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Word Display");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleFont(new Font("Consolas", Font.PLAIN , Game.FONT_SIZE));
        pnlPlayArea = new JPanel();
        pnlPlayArea.setBorder(titledBorder);
        pnlPlayArea.setBounds(Game.PADDING * 2 + Game.HANGMAN_PANEL_WIDTH, Game.PADDING, Game.GAME_WIDTH - Game.PADDING * 3 - Game.HANGMAN_PANEL_WIDTH, Game.GAME_HEIGHT - Game.PADDING * 2);

        txtSecretWord = new GameLabel("TEXT_SERECT", TEXT_SECRET);
        txtSecretWord.setHorizontalAlignment(SwingConstants.CENTER);
        txtSecretWord.setVerticalAlignment(SwingConstants.CENTER);
        txtSecretWord.setBounds(Game.PADDING, Game.PADDING, Game.GAME_WIDTH - Game.PADDING * 3 - Game.HANGMAN_PANEL_WIDTH, (int) (Game.SCALE * 160));
        initTxtSecretWord();
        
        lblLevel = new GameLabel("Level 1");
        lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
        lblLevel.setBounds(Game.PADDING, Game.PADDING * 2 + (int) (Game.SCALE * 160), Game.GAME_WIDTH - Game.PADDING * 3 - Game.HANGMAN_PANEL_WIDTH, Game.LABEL_HEIGHT);
        
        btnA = new GameButton("A", 1);
        btnB = new GameButton("B", 1);
        btnC = new GameButton("C", 1);
        btnD = new GameButton("D", 1);
        btnE = new GameButton("E", 1);
        btnF = new GameButton("F", 1);
        btnG = new GameButton("G", 1);
        btnH = new GameButton("H", 1);
        btnI = new GameButton("I", 1);
        btnJ = new GameButton("J", 1);
        btnK = new GameButton("K", 1);
        btnL = new GameButton("L", 1);
        btnM = new GameButton("M", 1);
        btnN = new GameButton("N", 1);
        btnO = new GameButton("O", 1);
        btnP = new GameButton("P", 1);
        btnQ = new GameButton("Q", 1);
        btnR = new GameButton("R", 1);
        btnS = new GameButton("S", 1);
        btnT = new GameButton("T", 1);
        btnU = new GameButton("U", 1);
        btnV = new GameButton("V", 1);
        btnW = new GameButton("W", 1);
        btnX = new GameButton("X", 1);
        btnY = new GameButton("Y", 1);
        btnZ = new GameButton("Z", 1);
        
        btnA.addActionListener(this);
        btnB.addActionListener(this);
        btnC.addActionListener(this);
        btnD.addActionListener(this);
        btnE.addActionListener(this);
        btnF.addActionListener(this);
        btnG.addActionListener(this);
        btnH.addActionListener(this);
        btnI.addActionListener(this);
        btnJ.addActionListener(this);
        btnK.addActionListener(this);
        btnL.addActionListener(this);
        btnM.addActionListener(this);
        btnN.addActionListener(this);
        btnO.addActionListener(this);
        btnP.addActionListener(this);
        btnQ.addActionListener(this);
        btnR.addActionListener(this);
        btnS.addActionListener(this);
        btnT.addActionListener(this);
        btnU.addActionListener(this);
        btnV.addActionListener(this);
        btnW.addActionListener(this);
        btnX.addActionListener(this);
        btnY.addActionListener(this);
        btnZ.addActionListener(this);

        btnA.setBounds(Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0,(int) (Game.SCALE * 182) + Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnB.setBounds(Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1,(int) (Game.SCALE * 182) +  Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnC.setBounds(Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2,(int) (Game.SCALE * 182) +  Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnD.setBounds(Game.PADDING * 5 + Game.CHAR_BUTTON_SIZE * 3,(int) (Game.SCALE * 182) +  Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnE.setBounds(Game.PADDING * 6 + Game.CHAR_BUTTON_SIZE * 4,(int) (Game.SCALE * 182) +  Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnF.setBounds(Game.PADDING * 7 + Game.CHAR_BUTTON_SIZE * 5,(int) (Game.SCALE * 182) +  Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnG.setBounds(Game.PADDING * 8 + Game.CHAR_BUTTON_SIZE * 6,(int) (Game.SCALE * 182) +  Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnH.setBounds(Game.PADDING * 9 + Game.CHAR_BUTTON_SIZE * 7,(int) (Game.SCALE * 182) +  Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);

        btnI.setBounds(Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0,(int) (Game.SCALE * 182) +  Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnJ.setBounds(Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1,(int) (Game.SCALE * 182) +  Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnK.setBounds(Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2,(int) (Game.SCALE * 182) +  Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnL.setBounds(Game.PADDING * 5 + Game.CHAR_BUTTON_SIZE * 3,(int) (Game.SCALE * 182) +  Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnM.setBounds(Game.PADDING * 6 + Game.CHAR_BUTTON_SIZE * 4,(int) (Game.SCALE * 182) +  Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnN.setBounds(Game.PADDING * 7 + Game.CHAR_BUTTON_SIZE * 5,(int) (Game.SCALE * 182) +  Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnO.setBounds(Game.PADDING * 8 + Game.CHAR_BUTTON_SIZE * 6,(int) (Game.SCALE * 182) +  Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnP.setBounds(Game.PADDING * 9 + Game.CHAR_BUTTON_SIZE * 7,(int) (Game.SCALE * 182) +  Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);

        btnQ.setBounds(Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0,(int) (Game.SCALE * 182) +  Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnR.setBounds(Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1,(int) (Game.SCALE * 182) +  Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnS.setBounds(Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2,(int) (Game.SCALE * 182) +  Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnT.setBounds(Game.PADDING * 5 + Game.CHAR_BUTTON_SIZE * 3,(int) (Game.SCALE * 182) +  Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnU.setBounds(Game.PADDING * 6 + Game.CHAR_BUTTON_SIZE * 4,(int) (Game.SCALE * 182) +  Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnV.setBounds(Game.PADDING * 7 + Game.CHAR_BUTTON_SIZE * 5,(int) (Game.SCALE * 182) +  Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnW.setBounds(Game.PADDING * 8 + Game.CHAR_BUTTON_SIZE * 6,(int) (Game.SCALE * 182) +  Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnX.setBounds(Game.PADDING * 9 + Game.CHAR_BUTTON_SIZE * 7,(int) (Game.SCALE * 182) +  Game.PADDING * 4 + Game.CHAR_BUTTON_SIZE * 2, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);

        btnY.setBounds(Game.PADDING * 2 + Game.CHAR_BUTTON_SIZE * 0,(int) (Game.SCALE * 182) + Game.PADDING * 5 + Game.CHAR_BUTTON_SIZE * 3, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);
        btnZ.setBounds(Game.PADDING * 3 + Game.CHAR_BUTTON_SIZE * 1,(int) (Game.SCALE * 182) + Game.PADDING * 5 + Game.CHAR_BUTTON_SIZE * 3, Game.CHAR_BUTTON_SIZE, Game.CHAR_BUTTON_SIZE);

        pnlPlayArea.setLayout(null);
        
        pnlPlayArea.add(txtSecretWord);
        pnlPlayArea.add(lblLevel);
        
        pnlPlayArea.add(btnA);
        pnlPlayArea.add(btnB);
        pnlPlayArea.add(btnC);
        pnlPlayArea.add(btnD);
        pnlPlayArea.add(btnE);
        pnlPlayArea.add(btnF);
        pnlPlayArea.add(btnG);
        pnlPlayArea.add(btnH);

        pnlPlayArea.add(btnI);
        pnlPlayArea.add(btnJ);
        pnlPlayArea.add(btnK);
        pnlPlayArea.add(btnL);
        pnlPlayArea.add(btnM);
        pnlPlayArea.add(btnN);
        pnlPlayArea.add(btnO);
        pnlPlayArea.add(btnP);

        pnlPlayArea.add(btnQ);
        pnlPlayArea.add(btnR);
        pnlPlayArea.add(btnS);
        pnlPlayArea.add(btnT);
        pnlPlayArea.add(btnU);
        pnlPlayArea.add(btnV);
        pnlPlayArea.add(btnW);
        pnlPlayArea.add(btnX);

        pnlPlayArea.add(btnY);
        pnlPlayArea.add(btnZ);
        
        updateLblLevel();
    }
    
    // Variables declaration
    private Game game = null;
    private HangmanCanvas hCanvas = null;
    private GameLabel lblTopicTxt, lblDiffTxt, lblScoreTxt, lblTopic, lblDifficulty, lblPlayerScore;
    private JPanel pnlDrawHangman, pnlPlayerInfo, pnlPlayArea;
    private GameLabel txtSecretWord;
    private GameLabel lblLevel;
    private GameButton btnNewGame, btnHighScore, btnNextLevel;
    private GameButton btnA;
    private GameButton btnB;
    private GameButton btnC;
    private GameButton btnD;
    private GameButton btnE;
    private GameButton btnF;
    private GameButton btnG;
    private GameButton btnH;
    private GameButton btnI;
    private GameButton btnJ;
    private GameButton btnK;
    private GameButton btnL;
    private GameButton btnM;
    private GameButton btnN;
    private GameButton btnO;
    private GameButton btnP;
    private GameButton btnQ;
    private GameButton btnR;
    private GameButton btnS;
    private GameButton btnT;
    private GameButton btnU;
    private GameButton btnV;
    private GameButton btnW;
    private GameButton btnX;
    private GameButton btnY;
    private GameButton btnZ;
    
    private ArrayList<Player> top5Players;
    private Player player;
    private char playerChoice;
    // End of variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnA) {
            btnLetterActionPerformed('A');
            btnA.setEnabled(false);
        }
        if (evt.getSource() == btnB) {
            btnLetterActionPerformed('B');
            btnB.setEnabled(false);
        }
        if (evt.getSource() == btnC) {
            btnLetterActionPerformed('C');
            btnC.setEnabled(false);
        }
        if (evt.getSource() == btnD) {
            btnLetterActionPerformed('D');
            btnD.setEnabled(false);
        }
        if (evt.getSource() == btnE) {
            btnLetterActionPerformed('E');
            btnE.setEnabled(false);
        }
        if (evt.getSource() == btnF) {
            btnLetterActionPerformed('F');
            btnF.setEnabled(false);
        }
        if (evt.getSource() == btnG) {
            btnLetterActionPerformed('G');
            btnG.setEnabled(false);
        }
        if (evt.getSource() == btnH) {
            btnLetterActionPerformed('H');
            btnH.setEnabled(false);
        }
        if (evt.getSource() == btnI) {
            btnLetterActionPerformed('I');
            btnI.setEnabled(false);
        }
        if (evt.getSource() == btnJ) {
            btnLetterActionPerformed('J');
            btnJ.setEnabled(false);
        }
        if (evt.getSource() == btnK) {
            btnLetterActionPerformed('K');
            btnK.setEnabled(false);
        }
        if (evt.getSource() == btnL) {
            btnLetterActionPerformed('L');
            btnL.setEnabled(false);
        }
        if (evt.getSource() == btnM) {
            btnLetterActionPerformed('M');
            btnM.setEnabled(false);
        }
        if (evt.getSource() == btnN) {
            btnLetterActionPerformed('N');
            btnN.setEnabled(false);
        }
        if (evt.getSource() == btnO) {
            btnLetterActionPerformed('O');
            btnO.setEnabled(false);
        }
        if (evt.getSource() == btnP) {
            btnLetterActionPerformed('P');
            btnP.setEnabled(false);
        }
        if (evt.getSource() == btnQ) {
            btnLetterActionPerformed('Q');
            btnQ.setEnabled(false);
        }
        if (evt.getSource() == btnR) {
            btnLetterActionPerformed('R');
            btnR.setEnabled(false);
        }
        if (evt.getSource() == btnS) {
            btnLetterActionPerformed('S');
            btnS.setEnabled(false);
        }
        if (evt.getSource() == btnT) {
            btnLetterActionPerformed('T');
            btnT.setEnabled(false);
        }
        if (evt.getSource() == btnU) {
            btnLetterActionPerformed('U');
            btnU.setEnabled(false);
        }
        if (evt.getSource() == btnV) {
            btnLetterActionPerformed('V');
            btnV.setEnabled(false);
        }
        if (evt.getSource() == btnW) {
            btnLetterActionPerformed('W');
            btnW.setEnabled(false);
        }
        if (evt.getSource() == btnX) {
            btnLetterActionPerformed('X');
            btnX.setEnabled(false);
        }
        if (evt.getSource() == btnY) {
            btnLetterActionPerformed('Y');
            btnY.setEnabled(false);
        }
        if (evt.getSource() == btnZ) {
            btnLetterActionPerformed('Z');
            btnZ.setEnabled(false);
        }
        if (evt.getSource() == btnNewGame) {
            if (JOptionPane.showConfirmDialog(this, "Do you want to start a new game?",
                "New Game?", JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION) {
//            btnNewGameActionPerformed();
            game.GAME_STATE = BACK;
            game.update();
        }
        }
        if (evt.getSource() == btnHighScore) {
            showHighScore();
        }
        if (evt.getSource() == btnNextLevel) {
            btnNextLevel.setEnabled(false);
            game.nextLevel();
            resetFrame();
        }
    }

    public Game getGame() {
        return game;
    }

    public void resetFrame() {
        pnlDrawHangman.revalidate();
        pnlDrawHangman.repaint();

        // reset buttons
        btnNewGame.setEnabled(true);
        btnNextLevel.setEnabled(false);
        resetLetterButtons();

        // reset Player Information panel
        updateLblTopic();
        updateLblDifficulty();
        updateLblScore();

        // init Word Display panel
        initTxtSecretWord();
        updateLblLevel();
    }
    
//    public void showChooseDifficultyDialog() {
//        dlgChooseDifficulty.setLocationRelativeTo(null);
//        dlgChooseDifficulty.setVisible(true);
//    }

    public void disableLetterButtons() {
//        for (Enumeration<AbstractButton> buttons = btgLetterButtons.getElements(); buttons.hasMoreElements();) {
//            AbstractButton button = buttons.nextElement();
//            button.setEnabled(false);
//        }
        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
        btnD.setEnabled(false);
        btnE.setEnabled(false);
        btnF.setEnabled(false);
        btnG.setEnabled(false);
        btnH.setEnabled(false);
        btnI.setEnabled(false);
        btnJ.setEnabled(false);
        btnK.setEnabled(false);
        btnL.setEnabled(false);
        btnM.setEnabled(false);
        btnN.setEnabled(false);
        btnO.setEnabled(false);
        btnP.setEnabled(false);
        btnQ.setEnabled(false);
        btnR.setEnabled(false);
        btnS.setEnabled(false);
        btnT.setEnabled(false);
        btnU.setEnabled(false);
        btnV.setEnabled(false);
        btnW.setEnabled(false);
        btnX.setEnabled(false);
        btnY.setEnabled(false);
        btnZ.setEnabled(false);

    }

    public void resetLetterButtons() {
//        for (Enumeration<AbstractButton> buttons = btgLetterButtons.getElements(); buttons.hasMoreElements();) {
//            AbstractButton button = buttons.nextElement();
//            button.setEnabled(true);
//        }
        btnA.setEnabled(true);
        btnB.setEnabled(true);
        btnC.setEnabled(true);
        btnD.setEnabled(true);
        btnE.setEnabled(true);
        btnF.setEnabled(true);
        btnG.setEnabled(true);
        btnH.setEnabled(true);
        btnI.setEnabled(true);
        btnJ.setEnabled(true);
        btnK.setEnabled(true);
        btnL.setEnabled(true);
        btnM.setEnabled(true);
        btnN.setEnabled(true);
        btnO.setEnabled(true);
        btnP.setEnabled(true);
        btnQ.setEnabled(true);
        btnR.setEnabled(true);
        btnS.setEnabled(true);
        btnT.setEnabled(true);
        btnU.setEnabled(true);
        btnV.setEnabled(true);
        btnW.setEnabled(true);
        btnX.setEnabled(true);
        btnY.setEnabled(true);
        btnZ.setEnabled(true);
    }

    public void initTxtSecretWord() {
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.35); // sets character spacing
//        txtSecretWord.setFont(txtSecretWord.getFont().deriveFont(attributes));
//        txtSecretWord.setForeground(Color.black);
        txtSecretWord.setText(game.getQuestion().getUserString());
    }

    public void updateLblDifficulty() {
        String properDifficulty = "";
        if (!game.getDifficulty().isEmpty()) {
            properDifficulty = Character.toUpperCase(game.getDifficulty().charAt(0))
                + game.getDifficulty().substring(1);
        }
        lblDifficulty.setText(properDifficulty);
    }

    public void updateLblTopic() {
        String properTopic = "";
        if (!(game.getQuestion() == null)) {
            properTopic = Character.toUpperCase(game.getQuestion().getTopic().charAt(0))
                + game.getQuestion().getTopic().substring(1);
        }
        lblTopic.setText(properTopic);
    }

    public void updateLblLevel() {
        txtSecretWord.setForeground(Color.BLACK); // turn the secret word back to black again right away
        game.increaseLevel();
        lblLevel.setText("Level " + game.getLevel());
    }

    public void updateLblSecret() {
        game.updateUserString();
        if (game.isLevelCompleted()) { // if the level is completed, text turns green
            txtSecretWord.setForeground(new Color(0, 122, 0));
            txtSecretWord.setText(game.getQuestion().getUserString());
        } else {
            txtSecretWord.setForeground(Color.black);
            txtSecretWord.setText(game.getQuestion().getUserString());
        }
    }

    public void updateLblScore() {
        lblPlayerScore.setText(game.getScore() + "");
    }

    public void btnLetterActionPerformed(char c) {
        game.setPlayerChoice(c);
        if (game.isCorrect()) {
            updateLblSecret();
            game.increaseScore();
            updateLblScore();
        } else {
            // Since "Asian" difficulty only has 2 lives, the first incorrent
            // choice will push the Hangman to state = 8 (one more incorrect
            // choice = game over).

            // Therefore, the next wrong choice will only +1 state (like it
            // normally does).
            if (game.getDifficulty().equals("asian")
                && hCanvas.getHangMan().getState() != 8) {
                for (int i = 0; i < 5; i++) {
                    this.hCanvas.getHangMan().increaseState();
                }
            } else {
                this.hCanvas.getHangMan().increaseState();
            }
            pnlDrawHangman.repaint();
        }

        if (game.isLevelCompleted()) {
            // proceeds to next level
            levelComplete();
        } else if (game.isGameOver()) {
            // stops current game, display score
            showGameOver();
            game.gameOver();
        }
    }

//    public void btnNewGameActionPerformed() {
//        btnNewGame.setEnabled(false);
//        showChooseDifficultyDialog();
//        game.reset();
//        resetFrame();
//    }

//    public void btnDifficultyActionPerformed(String difficulty) {
//        setDifficulty(difficulty);
//        dlgChooseDifficulty.setVisible(false);
//        this.setVisible(true);
//        pnlDrawHangman.repaint();
//    }

    public void levelComplete() {
        btnNextLevel.setEnabled(true);
        disableLetterButtons();
    }

    public void setAlignmentRight(JTextPane txtPane) {
        StyledDocument doc = txtPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    public void showGameOver() {
        disableLetterButtons();
        ArrayList<Player> top5Players = game.getTop5Players();
        int yourScore = game.getScore();
        boolean isInTop5 = false;
        ArrayList<Player> tempPlayers = game.getTop5Players();
        for (Player p : top5Players) {
            if (yourScore > p.getScore()) {
                isInTop5 = true;
                break;
            }
        }
//
        if (isInTop5) { // opens top 5 game over dialog
            String top5Names = "", top5Scores = "";
            for (int i = 1; i <= top5Players.size(); i++) {
                top5Names += i + ". " + top5Players.get(i - 1).getName() + "\n";
                top5Scores += top5Players.get(i - 1).getScore() + "\n";
            }
            top5Names = top5Names.substring(0, top5Names.length() - 1);
            top5Scores = top5Scores.substring(0, top5Scores.length() - 1);

            game.setLblYourScore_dlgGameOverTop5("Your Score: " + yourScore);
            game.setTxtPlayerNames_dlgGameOverTop5(top5Names);
            game.setTop5Names(top5Names);
            game.setTop5Scores(top5Scores);

            game.GAME_STATE = GAME_OVER_TOP5;
            game.update();
//            dlgGameOverTop5.setLocationRelativeTo(null);
//            dlgGameOverTop5.setVisible(true);
        } else { // opens normal game over dialog
            game.setLblYourScore_dlgGameOverTop5("Your Score: " + yourScore);
            game.GAME_STATE = GAME_OVER;
            game.update();
//            Player bestPlayer = game.getTop5Players().get(0);
//            int highScore = bestPlayer.getScore();
//            lblHighScore.setText("High Score: " + highScore);
//            lblYourScore_dlgGameOver.setText("Your Score: " + yourScore);
//            dlgGameOver.setLocationRelativeTo(null);
//            dlgGameOver.setVisible(true);
        }
    }

    public void showHighScore() {
        String top5Names = "", top5Scores = "";
        ArrayList<Player> top5Players = game.getTop5Players();
        for (int i = 1; i <= top5Players.size(); i++) {
            top5Names += i + ". " + top5Players.get(i - 1).getName() + "\n";
            top5Scores += top5Players.get(i - 1).getScore() + "\n";
        }
        top5Names = top5Names.substring(0, top5Names.length() - 1);
        top5Scores = top5Scores.substring(0, top5Scores.length() - 1);

        game.setTxtPlayerNames_dlgGameOverTop5(top5Names);
        game.setTop5Names(top5Names);
        game.setTop5Scores(top5Scores);

        game.GAME_STATE = HIGH_SCORE;
        game.update();
    }
    

    public void printTop5(JTextPane txtPane) {

    }
}
