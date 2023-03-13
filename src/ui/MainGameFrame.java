/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.Player;
import hangmangame_group3_se1704.GameInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

/**
 *
 * @author ibuyc
 */
public class MainGameFrame extends javax.swing.JFrame {
    private HangmanCanvas hCanvas = null;
    private GameInstance game = null;

    public void setDifficulty(String difficulty) {
        game.setDifficulty(difficulty);
        game.setDifficultyFactor(difficulty);
        updateTxtDifficulty();
    }

    public void initFrame() {
        showChooseDifficultyDialog();
        
        pnlDrawHangman.setLayout(null);
        pnlDrawHangman.removeAll();
        pnlDrawHangman.revalidate();
        pnlDrawHangman.repaint();

        hCanvas = new HangmanCanvas(game.getHangMan());
        pnlDrawHangman.setLayout(new BorderLayout());
        pnlDrawHangman.add(hCanvas, BorderLayout.CENTER);

        // init Word Display panel
        initTxtSecretWord();
        updateLevelText();
        btnNextLevel.setEnabled(false);
    }
    
    public void resetFrame() {
        
        pnlDrawHangman.revalidate();
        pnlDrawHangman.repaint();
        
        // reset buttons
        btnNewGame.setEnabled(true);
        btnNextLevel.setEnabled(false);
        resetLetterButtons();
        
        // reset Player Information panel
        updateTxtDifficulty();
        updateScoreText();
        
        // init Word Display panel
        initTxtSecretWord();
        updateLevelText();
    }
    
    public void showChooseDifficultyDialog() {
        dlgChooseDifficulty.setLocationRelativeTo(null);
        dlgChooseDifficulty.setVisible(true);
    }
    
    public void disableLetterButtons() {
        for (Enumeration<AbstractButton> buttons = btgLetterButtons.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.setEnabled(false);
        }
    }
    
    public void resetLetterButtons() {
        for (Enumeration<AbstractButton> buttons = btgLetterButtons.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.setEnabled(true);
        }
    }

    public void updateTxtDifficulty() {
        String properDifficulty = "";
        if (!game.getDifficulty().isEmpty()) {
            properDifficulty = Character.toUpperCase(game.getDifficulty().charAt(0))
                    + game.getDifficulty().substring(1);
        }
        lblDifficulty.setText(properDifficulty);
    }

    public void initTxtSecretWord() {
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.5); // sets character spacing
        txtSecretWord.setFont(txtSecretWord.getFont().deriveFont(attributes));
        txtSecretWord.setForeground(Color.black);
        txtSecretWord.setText(game.getQuestion().getUserString());
    }
    
    public void updateLevelText() {
        game.increaseLevel();
        lblLevel.setText(game.getLevel() + "");
    }

    public void updateSecretText() {
        game.updateUserString();
        if (game.isLevelCompleted()) { // if the level is completed, text turns green
            txtSecretWord.setForeground(new Color(0, 122, 0));
            txtSecretWord.setText(game.getQuestion().getUserString());
        } else {
            txtSecretWord.setForeground(Color.black);
            txtSecretWord.setText(game.getQuestion().getUserString());
        }
    }

    public void updateScoreText() {
        lblPlayerScore.setText(game.getScore() + "");
    }

    public void newScoreDialog() {
        disableLetterButtons();
        String playerName;
        playerName = JOptionPane.showInputDialog(this,
                "You suck! But at least you got a high score...",
                "You lost!", JOptionPane.PLAIN_MESSAGE);
        // if playerName already exists
        // add score to existing player instead

    }

    public void btnLetterActionPerformed(char c) {
        game.setPlayerChoice(c);
        if (game.isCorrect()) {
            updateSecretText();
            game.increaseScore();
            updateScoreText();
        } else {
            this.hCanvas.getHangMan().increaseState();
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
    
    public void btnNewGameActionPerformed() {
        btnNewGame.setEnabled(false);
        showChooseDifficultyDialog();
        game.reset();
        resetFrame();
    }
    
    public void btnDifficultyActionPerformed(String difficulty) {
        setDifficulty(difficulty);
        dlgChooseDifficulty.setVisible(false);
        this.setVisible(true);
        pnlDrawHangman.repaint();
    }
    
    public void levelComplete() {
        btnNextLevel.setEnabled(true);
        disableLetterButtons();
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
        
        if (isInTop5) {
            String top5Names = "", top5Scores = "";
            for (int i = 1; i <= top5Players.size(); i++) {
                top5Names += i + ". " + top5Players.get(i-1).getName() + "\n";
                top5Scores += top5Players.get(i-1).getScore() + "\n";
            }
            top5Names = top5Names.substring(0, top5Names.length() - 1);
            top5Scores = top5Scores.substring(0, top5Scores.length() - 1);
            
            lblYourScore_dlgGameOverTop5.setText("Your Score: " + yourScore);
            txtPlayerNames.setText(top5Names);
            txtPlayerScores.setText(top5Scores);
            dlgGameOverTop5.setLocationRelativeTo(null);
            dlgGameOverTop5.setVisible(true);
        } else {
            Player bestPlayer = game.getTop5Players().get(0);
            int highScore = bestPlayer.getScore();
            lblHighScore.setText("High Score: " + highScore);
            lblYourScore_dlgGameOver.setText("Your Score: " + yourScore);
            dlgGameOver.setLocationRelativeTo(null);
            dlgGameOver.setVisible(true);
        }
    }

    /**
     * Creates new form HangmanGame
     */
    public MainGameFrame() {
        initComponents();
        this.setLocationRelativeTo(null); // put this into the center of screen
        game = new GameInstance();
        initFrame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        btgLetterButtons = new javax.swing.ButtonGroup();
        dlgGameOver = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        lblGameOver_dlgGameOver = new javax.swing.JLabel();
        lblHighScore = new javax.swing.JLabel();
        lblYourScore_dlgGameOver = new javax.swing.JLabel();
        btnNewGame_dlgGameOver = new javax.swing.JButton();
        dlgGameOverTop5 = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        lblGameOver_dlgGameOverTop5 = new javax.swing.JLabel();
        lblYourScore_dlgGameOverTop5 = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        lblTop5 = new javax.swing.JLabel();
        txtPlayerNames = new javax.swing.JTextArea();
        txtPlayerScores = new javax.swing.JTextArea();
        btnSavePlayerName = new javax.swing.JButton();
        txtNameInput = new javax.swing.JTextField();
        dlgChooseDifficulty = new javax.swing.JDialog();
        pnlBackground = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnEasy = new javax.swing.JButton();
        btnNormal = new javax.swing.JButton();
        btnHard = new javax.swing.JButton();
        btnAsian = new javax.swing.JButton();
        pnlPlayArea = new javax.swing.JPanel();
        btnA = new javax.swing.JButton();
        btnB = new javax.swing.JButton();
        btnD = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnG = new javax.swing.JButton();
        btnH = new javax.swing.JButton();
        btnF = new javax.swing.JButton();
        btnE = new javax.swing.JButton();
        btnO = new javax.swing.JButton();
        btnN = new javax.swing.JButton();
        btnM = new javax.swing.JButton();
        btnP = new javax.swing.JButton();
        btnI = new javax.swing.JButton();
        btnJ = new javax.swing.JButton();
        btnK = new javax.swing.JButton();
        btnL = new javax.swing.JButton();
        btnW = new javax.swing.JButton();
        btnV = new javax.swing.JButton();
        btnU = new javax.swing.JButton();
        btnX = new javax.swing.JButton();
        btnQ = new javax.swing.JButton();
        btnR = new javax.swing.JButton();
        btnS = new javax.swing.JButton();
        btnT = new javax.swing.JButton();
        btnY = new javax.swing.JButton();
        btnZ = new javax.swing.JButton();
        btnNextLevel = new javax.swing.JButton();
        btnNewGame = new javax.swing.JButton();
        lblLevelTxt = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        txtSecretWord = new javax.swing.JLabel();
        btnHighScore = new javax.swing.JButton();
        pnlPlayerInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDifficulty = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblPlayerScore = new javax.swing.JLabel();
        lblTopicTxt = new javax.swing.JLabel();
        lblTopic = new javax.swing.JLabel();
        pnlDrawHangman = new javax.swing.JPanel();

        dlgGameOver.setAlwaysOnTop(true);
        dlgGameOver.setBackground(new java.awt.Color(0, 102, 102));
        dlgGameOver.setModal(true);
        dlgGameOver.setResizable(false);
        dlgGameOver.setSize(new java.awt.Dimension(650, 400));
        dlgGameOver.setType(java.awt.Window.Type.POPUP);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, dlgGameOver, org.jdesktop.beansbinding.ELProperty.create("true"), dlgGameOver, org.jdesktop.beansbinding.BeanProperty.create("undecorated"));
        bindingGroup.addBinding(binding);

        dlgGameOverTop5.setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(246, 236, 217));

        lblGameOver_dlgGameOver.setBackground(new java.awt.Color(255, 255, 255));
        lblGameOver_dlgGameOver.setFont(new java.awt.Font("Segoe UI", 1, 72)); // NOI18N
        lblGameOver_dlgGameOver.setForeground(new java.awt.Color(153, 0, 0));
        lblGameOver_dlgGameOver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameOver_dlgGameOver.setText("GAME OVER");
        lblGameOver_dlgGameOver.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblGameOver_dlgGameOver.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblHighScore.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lblHighScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHighScore.setText("High Score: 9001");
        lblHighScore.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblYourScore_dlgGameOver.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lblYourScore_dlgGameOver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYourScore_dlgGameOver.setText("Your Score: 420");
        lblYourScore_dlgGameOver.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        btnNewGame_dlgGameOver.setBackground(new java.awt.Color(0, 153, 153));
        btnNewGame_dlgGameOver.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnNewGame_dlgGameOver.setForeground(new java.awt.Color(102, 0, 0));
        btnNewGame_dlgGameOver.setText("New Game");
        btnNewGame_dlgGameOver.setContentAreaFilled(false);
        btnNewGame_dlgGameOver.setRequestFocusEnabled(false);
        btnNewGame_dlgGameOver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGame_dlgGameOverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewGame_dlgGameOver, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGameOver_dlgGameOver, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addComponent(lblHighScore, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblYourScore_dlgGameOver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGameOver_dlgGameOver, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHighScore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYourScore_dlgGameOver)
                .addGap(24, 24, 24)
                .addComponent(btnNewGame_dlgGameOver)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dlgGameOverLayout = new javax.swing.GroupLayout(dlgGameOver.getContentPane());
        dlgGameOver.getContentPane().setLayout(dlgGameOverLayout);
        dlgGameOverLayout.setHorizontalGroup(
            dlgGameOverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgGameOverLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        dlgGameOverLayout.setVerticalGroup(
            dlgGameOverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgGameOverLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dlgGameOverTop5.setAlwaysOnTop(true);
        dlgGameOverTop5.setBackground(new java.awt.Color(0, 102, 102));
        dlgGameOverTop5.setModal(true);
        dlgGameOverTop5.setResizable(false);
        dlgGameOverTop5.setSize(new java.awt.Dimension(673, 468));
        dlgGameOverTop5.setType(java.awt.Window.Type.POPUP);
        dlgGameOverTop5.setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(246, 236, 217));
        jPanel4.setFocusable(false);

        lblGameOver_dlgGameOverTop5.setBackground(new java.awt.Color(255, 255, 255));
        lblGameOver_dlgGameOverTop5.setFont(new java.awt.Font("Segoe UI", 1, 72)); // NOI18N
        lblGameOver_dlgGameOverTop5.setForeground(new java.awt.Color(153, 0, 0));
        lblGameOver_dlgGameOverTop5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameOver_dlgGameOverTop5.setText("GAME OVER...");
        lblGameOver_dlgGameOverTop5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblGameOver_dlgGameOverTop5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblYourScore_dlgGameOverTop5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblYourScore_dlgGameOverTop5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYourScore_dlgGameOverTop5.setText("Your Score: 420");
        lblYourScore_dlgGameOverTop5.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(0, 102, 102));
        lblSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitle.setText("but you've got a really high score!");

        lblTop5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTop5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop5.setText("Top 5 Scores");
        lblTop5.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtPlayerNames.setEditable(false);
        txtPlayerNames.setBackground(new java.awt.Color(244, 235, 217));
        txtPlayerNames.setColumns(5);
        txtPlayerNames.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtPlayerNames.setRows(5);
        txtPlayerNames.setText("1. Player1\n2. Player4\n3. Player2\n4. Player3\n5. Player5");
        txtPlayerNames.setAutoscrolls(false);
        txtPlayerNames.setBorder(null);
        txtPlayerNames.setFocusable(false);
        txtPlayerNames.setRequestFocusEnabled(false);

        txtPlayerScores.setEditable(false);
        txtPlayerScores.setBackground(new java.awt.Color(244, 235, 217));
        txtPlayerScores.setColumns(2);
        txtPlayerScores.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtPlayerScores.setRows(5);
        txtPlayerScores.setText("9001\n5600\n5000\n4000\n2300");
        txtPlayerScores.setAutoscrolls(false);
        txtPlayerScores.setBorder(null);
        txtPlayerScores.setFocusable(false);
        txtPlayerScores.setRequestFocusEnabled(false);

        btnSavePlayerName.setBackground(new java.awt.Color(0, 153, 153));
        btnSavePlayerName.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnSavePlayerName.setForeground(new java.awt.Color(102, 0, 0));
        btnSavePlayerName.setText("Save");
        btnSavePlayerName.setContentAreaFilled(false);
        btnSavePlayerName.setOpaque(false);
        btnSavePlayerName.setRequestFocusEnabled(false);
        btnSavePlayerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePlayerNameActionPerformed(evt);
            }
        });

        txtNameInput.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtNameInput.setText("Player0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtPlayerNames)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPlayerScores, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTop5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNameInput)
                            .addComponent(lblYourScore_dlgGameOverTop5, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                    .addComponent(lblGameOver_dlgGameOverTop5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSubtitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSavePlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGameOver_dlgGameOverTop5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTop5)
                    .addComponent(lblYourScore_dlgGameOverTop5))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPlayerScores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSavePlayerName)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dlgGameOverTop5Layout = new javax.swing.GroupLayout(dlgGameOverTop5.getContentPane());
        dlgGameOverTop5.getContentPane().setLayout(dlgGameOverTop5Layout);
        dlgGameOverTop5Layout.setHorizontalGroup(
            dlgGameOverTop5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dlgGameOverTop5Layout.setVerticalGroup(
            dlgGameOverTop5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        dlgChooseDifficulty.setAlwaysOnTop(true);
        dlgChooseDifficulty.setSize(new java.awt.Dimension(770, 482));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, dlgChooseDifficulty, org.jdesktop.beansbinding.ELProperty.create("true"), dlgChooseDifficulty, org.jdesktop.beansbinding.BeanProperty.create("undecorated"));
        bindingGroup.addBinding(binding);

        pnlBackground.setBackground(new java.awt.Color(209, 237, 230));

        jLabel4.setFont(new java.awt.Font("Manrope SemiBold", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Choose Your Difficulty");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnEasy.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnEasy.setText("Easy");
        btnEasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEasyActionPerformed(evt);
            }
        });

        btnNormal.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnNormal.setText("Normal");
        btnNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNormalActionPerformed(evt);
            }
        });

        btnHard.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnHard.setText("Hard");
        btnHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHardActionPerformed(evt);
            }
        });

        btnAsian.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnAsian.setText("Asian");
        btnAsian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsianActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(btnEasy, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHard, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAsian, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEasy, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(btnNormal, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(btnHard, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(btnAsian, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout dlgChooseDifficultyLayout = new javax.swing.GroupLayout(dlgChooseDifficulty.getContentPane());
        dlgChooseDifficulty.getContentPane().setLayout(dlgChooseDifficultyLayout);
        dlgChooseDifficultyLayout.setHorizontalGroup(
            dlgChooseDifficultyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlgChooseDifficultyLayout.setVerticalGroup(
            dlgChooseDifficultyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlPlayArea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Word Display", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        btnA.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnA.setText("A");
        btgLetterButtons.add(btnA);
        btnA.setMaximumSize(new java.awt.Dimension(55, 40));
        btnA.setMinimumSize(new java.awt.Dimension(55, 40));
        btnA.setPreferredSize(new java.awt.Dimension(55, 40));
        btnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAActionPerformed(evt);
            }
        });

        btnB.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnB.setText("B");
        btgLetterButtons.add(btnB);
        btnB.setMaximumSize(new java.awt.Dimension(55, 40));
        btnB.setMinimumSize(new java.awt.Dimension(55, 40));
        btnB.setPreferredSize(new java.awt.Dimension(55, 40));
        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBActionPerformed(evt);
            }
        });

        btnD.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnD.setText("D");
        btgLetterButtons.add(btnD);
        btnD.setMaximumSize(new java.awt.Dimension(55, 40));
        btnD.setMinimumSize(new java.awt.Dimension(55, 40));
        btnD.setPreferredSize(new java.awt.Dimension(55, 40));
        btnD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDActionPerformed(evt);
            }
        });

        btnC.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnC.setText("C");
        btgLetterButtons.add(btnC);
        btnC.setMaximumSize(new java.awt.Dimension(55, 40));
        btnC.setMinimumSize(new java.awt.Dimension(55, 40));
        btnC.setPreferredSize(new java.awt.Dimension(55, 40));
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        btnG.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnG.setText("G");
        btgLetterButtons.add(btnG);
        btnG.setMaximumSize(new java.awt.Dimension(55, 40));
        btnG.setMinimumSize(new java.awt.Dimension(55, 40));
        btnG.setPreferredSize(new java.awt.Dimension(55, 40));
        btnG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGActionPerformed(evt);
            }
        });

        btnH.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnH.setText("H");
        btgLetterButtons.add(btnH);
        btnH.setMaximumSize(new java.awt.Dimension(55, 40));
        btnH.setMinimumSize(new java.awt.Dimension(55, 40));
        btnH.setPreferredSize(new java.awt.Dimension(55, 40));
        btnH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHActionPerformed(evt);
            }
        });

        btnF.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnF.setText("F");
        btgLetterButtons.add(btnF);
        btnF.setMaximumSize(new java.awt.Dimension(55, 40));
        btnF.setMinimumSize(new java.awt.Dimension(55, 40));
        btnF.setPreferredSize(new java.awt.Dimension(55, 40));
        btnF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFActionPerformed(evt);
            }
        });

        btnE.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnE.setText("E");
        btgLetterButtons.add(btnE);
        btnE.setMaximumSize(new java.awt.Dimension(55, 40));
        btnE.setMinimumSize(new java.awt.Dimension(55, 40));
        btnE.setPreferredSize(new java.awt.Dimension(55, 40));
        btnE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEActionPerformed(evt);
            }
        });

        btnO.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnO.setText("O");
        btgLetterButtons.add(btnO);
        btnO.setMaximumSize(new java.awt.Dimension(55, 40));
        btnO.setMinimumSize(new java.awt.Dimension(55, 40));
        btnO.setPreferredSize(new java.awt.Dimension(55, 40));
        btnO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOActionPerformed(evt);
            }
        });

        btnN.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnN.setText("N");
        btgLetterButtons.add(btnN);
        btnN.setMaximumSize(new java.awt.Dimension(55, 40));
        btnN.setMinimumSize(new java.awt.Dimension(55, 40));
        btnN.setPreferredSize(new java.awt.Dimension(55, 40));
        btnN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNActionPerformed(evt);
            }
        });

        btnM.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnM.setText("M");
        btgLetterButtons.add(btnM);
        btnM.setMaximumSize(new java.awt.Dimension(55, 40));
        btnM.setMinimumSize(new java.awt.Dimension(55, 40));
        btnM.setPreferredSize(new java.awt.Dimension(55, 40));
        btnM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMActionPerformed(evt);
            }
        });

        btnP.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnP.setText("P");
        btgLetterButtons.add(btnP);
        btnP.setMaximumSize(new java.awt.Dimension(55, 40));
        btnP.setMinimumSize(new java.awt.Dimension(55, 40));
        btnP.setPreferredSize(new java.awt.Dimension(55, 40));
        btnP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPActionPerformed(evt);
            }
        });

        btnI.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnI.setText("I");
        btgLetterButtons.add(btnI);
        btnI.setMaximumSize(new java.awt.Dimension(55, 40));
        btnI.setMinimumSize(new java.awt.Dimension(55, 40));
        btnI.setPreferredSize(new java.awt.Dimension(55, 40));
        btnI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIActionPerformed(evt);
            }
        });

        btnJ.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnJ.setText("J");
        btgLetterButtons.add(btnJ);
        btnJ.setMaximumSize(new java.awt.Dimension(55, 40));
        btnJ.setMinimumSize(new java.awt.Dimension(55, 40));
        btnJ.setPreferredSize(new java.awt.Dimension(55, 40));
        btnJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJActionPerformed(evt);
            }
        });

        btnK.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnK.setText("K");
        btgLetterButtons.add(btnK);
        btnK.setMaximumSize(new java.awt.Dimension(55, 40));
        btnK.setMinimumSize(new java.awt.Dimension(55, 40));
        btnK.setPreferredSize(new java.awt.Dimension(55, 40));
        btnK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKActionPerformed(evt);
            }
        });

        btnL.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnL.setText("L");
        btgLetterButtons.add(btnL);
        btnL.setMaximumSize(new java.awt.Dimension(55, 40));
        btnL.setMinimumSize(new java.awt.Dimension(55, 40));
        btnL.setPreferredSize(new java.awt.Dimension(55, 40));
        btnL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLActionPerformed(evt);
            }
        });

        btnW.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnW.setText("W");
        btgLetterButtons.add(btnW);
        btnW.setMaximumSize(new java.awt.Dimension(55, 40));
        btnW.setMinimumSize(new java.awt.Dimension(55, 40));
        btnW.setPreferredSize(new java.awt.Dimension(55, 40));
        btnW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWActionPerformed(evt);
            }
        });

        btnV.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnV.setText("V");
        btgLetterButtons.add(btnV);
        btnV.setMaximumSize(new java.awt.Dimension(55, 40));
        btnV.setMinimumSize(new java.awt.Dimension(55, 40));
        btnV.setPreferredSize(new java.awt.Dimension(55, 40));
        btnV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVActionPerformed(evt);
            }
        });

        btnU.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnU.setText("U");
        btgLetterButtons.add(btnU);
        btnU.setMaximumSize(new java.awt.Dimension(55, 40));
        btnU.setMinimumSize(new java.awt.Dimension(55, 40));
        btnU.setPreferredSize(new java.awt.Dimension(55, 40));
        btnU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUActionPerformed(evt);
            }
        });

        btnX.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnX.setText("X");
        btgLetterButtons.add(btnX);
        btnX.setMaximumSize(new java.awt.Dimension(55, 40));
        btnX.setMinimumSize(new java.awt.Dimension(55, 40));
        btnX.setPreferredSize(new java.awt.Dimension(55, 40));
        btnX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXActionPerformed(evt);
            }
        });

        btnQ.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnQ.setText("Q");
        btgLetterButtons.add(btnQ);
        btnQ.setMaximumSize(new java.awt.Dimension(55, 40));
        btnQ.setMinimumSize(new java.awt.Dimension(55, 40));
        btnQ.setPreferredSize(new java.awt.Dimension(55, 40));
        btnQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQActionPerformed(evt);
            }
        });

        btnR.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnR.setText("R");
        btgLetterButtons.add(btnR);
        btnR.setMaximumSize(new java.awt.Dimension(55, 40));
        btnR.setMinimumSize(new java.awt.Dimension(55, 40));
        btnR.setPreferredSize(new java.awt.Dimension(55, 40));
        btnR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRActionPerformed(evt);
            }
        });

        btnS.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnS.setText("S");
        btgLetterButtons.add(btnS);
        btnS.setMaximumSize(new java.awt.Dimension(55, 40));
        btnS.setMinimumSize(new java.awt.Dimension(55, 40));
        btnS.setPreferredSize(new java.awt.Dimension(55, 40));
        btnS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSActionPerformed(evt);
            }
        });

        btnT.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnT.setText("T");
        btgLetterButtons.add(btnT);
        btnT.setMaximumSize(new java.awt.Dimension(55, 40));
        btnT.setMinimumSize(new java.awt.Dimension(55, 40));
        btnT.setPreferredSize(new java.awt.Dimension(55, 40));
        btnT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTActionPerformed(evt);
            }
        });

        btnY.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnY.setText("Y");
        btgLetterButtons.add(btnY);
        btnY.setMaximumSize(new java.awt.Dimension(55, 40));
        btnY.setMinimumSize(new java.awt.Dimension(55, 40));
        btnY.setPreferredSize(new java.awt.Dimension(55, 40));
        btnY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYActionPerformed(evt);
            }
        });

        btnZ.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnZ.setText("Z");
        btgLetterButtons.add(btnZ);
        btnZ.setMaximumSize(new java.awt.Dimension(55, 40));
        btnZ.setMinimumSize(new java.awt.Dimension(55, 40));
        btnZ.setPreferredSize(new java.awt.Dimension(55, 40));
        btnZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZActionPerformed(evt);
            }
        });

        btnNextLevel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnNextLevel.setText("Next Level");
        btnNextLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextLevelActionPerformed(evt);
            }
        });

        btnNewGame.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnNewGame.setText("New Game");
        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });

        lblLevelTxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblLevelTxt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLevelTxt.setText("Level");
        lblLevelTxt.setFocusable(false);

        lblLevel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblLevel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblLevel.setText("0");

        txtSecretWord.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        txtSecretWord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSecretWord.setText("TEXT____SECRET");

        btnHighScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnHighScore.setText("High Scores");
        btnHighScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHighScoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPlayAreaLayout = new javax.swing.GroupLayout(pnlPlayArea);
        pnlPlayArea.setLayout(pnlPlayAreaLayout);
        pnlPlayAreaLayout.setHorizontalGroup(
            pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPlayAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSecretWord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPlayAreaLayout.createSequentialGroup()
                        .addComponent(btnNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(btnHighScore, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(btnNextLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPlayAreaLayout.createSequentialGroup()
                        .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPlayAreaLayout.createSequentialGroup()
                                .addComponent(btnY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPlayAreaLayout.createSequentialGroup()
                                .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlPlayAreaLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlPlayAreaLayout.createSequentialGroup()
                                                .addComponent(btnV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnW, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlPlayAreaLayout.createSequentialGroup()
                                                .addComponent(btnN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(pnlPlayAreaLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblLevelTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlPlayAreaLayout.setVerticalGroup(
            pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPlayAreaLayout.createSequentialGroup()
                .addComponent(txtSecretWord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLevelTxt)
                    .addComponent(lblLevel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNextLevel)
                    .addComponent(btnNewGame)
                    .addComponent(btnHighScore))
                .addGap(18, 18, 18)
                .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnF, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPlayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlPlayerInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Game Information", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Difficulty");

        lblDifficulty.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblDifficulty.setText("Easy");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Score");

        lblPlayerScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblPlayerScore.setText("0");

        lblTopicTxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTopicTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTopicTxt.setText("Topic");
        lblTopicTxt.setFocusable(false);

        lblTopic.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTopic.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTopic.setText("Random");

        javax.swing.GroupLayout pnlPlayerInfoLayout = new javax.swing.GroupLayout(pnlPlayerInfo);
        pnlPlayerInfo.setLayout(pnlPlayerInfoLayout);
        pnlPlayerInfoLayout.setHorizontalGroup(
            pnlPlayerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlayerInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPlayerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTopicTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlPlayerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTopic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPlayerScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDifficulty, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPlayerInfoLayout.setVerticalGroup(
            pnlPlayerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPlayerInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPlayerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTopicTxt)
                    .addComponent(lblTopic))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPlayerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDifficulty))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPlayerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblPlayerScore))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlDrawHangman.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hangman Painting", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        pnlDrawHangman.setPreferredSize(new java.awt.Dimension(300, 380));

        javax.swing.GroupLayout pnlDrawHangmanLayout = new javax.swing.GroupLayout(pnlDrawHangman);
        pnlDrawHangman.setLayout(pnlDrawHangmanLayout);
        pnlDrawHangmanLayout.setHorizontalGroup(
            pnlDrawHangmanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );
        pnlDrawHangmanLayout.setVerticalGroup(
            pnlDrawHangmanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPlayerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDrawHangman, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(pnlPlayArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPlayArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDrawHangman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPlayerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('A');
        btnA.setEnabled(false);
    }//GEN-LAST:event_btnAActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('B');
        btnB.setEnabled(false);
    }//GEN-LAST:event_btnBActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('C');
        btnC.setEnabled(false);
    }//GEN-LAST:event_btnCActionPerformed

    private void btnDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('D');
        btnD.setEnabled(false);
    }//GEN-LAST:event_btnDActionPerformed

    private void btnEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('E');
        btnE.setEnabled(false);
    }//GEN-LAST:event_btnEActionPerformed

    private void btnFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('F');
        btnF.setEnabled(false);
    }//GEN-LAST:event_btnFActionPerformed

    private void btnGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('G');
        btnG.setEnabled(false);
    }//GEN-LAST:event_btnGActionPerformed

    private void btnHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('H');
        btnH.setEnabled(false);
    }//GEN-LAST:event_btnHActionPerformed

    private void btnIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('I');
        btnI.setEnabled(false);
    }//GEN-LAST:event_btnIActionPerformed

    private void btnJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('J');
        btnJ.setEnabled(false);
    }//GEN-LAST:event_btnJActionPerformed

    private void btnKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('K');
        btnK.setEnabled(false);
    }//GEN-LAST:event_btnKActionPerformed

    private void btnLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('L');
        btnL.setEnabled(false);
    }//GEN-LAST:event_btnLActionPerformed

    private void btnMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('M');
        btnM.setEnabled(false);
    }//GEN-LAST:event_btnMActionPerformed

    private void btnNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('N');
        btnN.setEnabled(false);
    }//GEN-LAST:event_btnNActionPerformed

    private void btnOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('O');
        btnO.setEnabled(false);
    }//GEN-LAST:event_btnOActionPerformed

    private void btnPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('P');
        btnP.setEnabled(false);
    }//GEN-LAST:event_btnPActionPerformed

    private void btnQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('Q');
        btnQ.setEnabled(false);
    }//GEN-LAST:event_btnQActionPerformed

    private void btnRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('R');
        btnR.setEnabled(false);
    }//GEN-LAST:event_btnRActionPerformed

    private void btnSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('S');
        btnS.setEnabled(false);
    }//GEN-LAST:event_btnSActionPerformed

    private void btnTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('T');
        btnT.setEnabled(false);
    }//GEN-LAST:event_btnTActionPerformed

    private void btnUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('U');
        btnU.setEnabled(false);
    }//GEN-LAST:event_btnUActionPerformed

    private void btnVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('V');
        btnV.setEnabled(false);
    }//GEN-LAST:event_btnVActionPerformed

    private void btnWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('W');
        btnW.setEnabled(false);
    }//GEN-LAST:event_btnWActionPerformed

    private void btnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('X');
        btnX.setEnabled(false);
    }//GEN-LAST:event_btnXActionPerformed

    private void btnYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('Y');
        btnY.setEnabled(false);
    }//GEN-LAST:event_btnYActionPerformed

    private void btnZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZActionPerformed
        // TODO add your handling code here:
        btnLetterActionPerformed('Z');
        btnZ.setEnabled(false);
    }//GEN-LAST:event_btnZActionPerformed

    private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGameActionPerformed
        // TODO add your handling code here:
        btnNewGameActionPerformed();
    }//GEN-LAST:event_btnNewGameActionPerformed

    private void btnNextLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextLevelActionPerformed
        // TODO add your handling code here:
        btnNextLevel.setEnabled(false);
        game.nextLevel();
        resetFrame();
    }//GEN-LAST:event_btnNextLevelActionPerformed

    private void btnSavePlayerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePlayerNameActionPerformed
        // TODO add your handling code here:
        String name = txtNameInput.getText();
        game.savePlayer(name);
        btnNewGameActionPerformed();
        dlgGameOverTop5.setVisible(false);
    }//GEN-LAST:event_btnSavePlayerNameActionPerformed

    private void btnEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEasyActionPerformed
        // TODO add your handling code here:
        btnDifficultyActionPerformed("easy");
    }//GEN-LAST:event_btnEasyActionPerformed

    private void btnNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNormalActionPerformed
        // TODO add your handling code here:
        btnDifficultyActionPerformed("normal");
    }//GEN-LAST:event_btnNormalActionPerformed

    private void btnHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHardActionPerformed
        // TODO add your handling code here:
        btnDifficultyActionPerformed("hard");
    }//GEN-LAST:event_btnHardActionPerformed

    private void btnAsianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsianActionPerformed
        // TODO add your handling code here:
        btnDifficultyActionPerformed("asian");
    }//GEN-LAST:event_btnAsianActionPerformed

    private void btnNewGame_dlgGameOverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGame_dlgGameOverActionPerformed
        // TODO add your handling code here:
        
        btnNewGameActionPerformed();
        dlgGameOver.setVisible(false);
    }//GEN-LAST:event_btnNewGame_dlgGameOverActionPerformed

    private void btnHighScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHighScoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHighScoreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgLetterButtons;
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnAsian;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnD;
    private javax.swing.JButton btnE;
    private javax.swing.JButton btnEasy;
    private javax.swing.JButton btnF;
    private javax.swing.JButton btnG;
    private javax.swing.JButton btnH;
    private javax.swing.JButton btnHard;
    private javax.swing.JButton btnHighScore;
    private javax.swing.JButton btnI;
    private javax.swing.JButton btnJ;
    private javax.swing.JButton btnK;
    private javax.swing.JButton btnL;
    private javax.swing.JButton btnM;
    private javax.swing.JButton btnN;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnNewGame_dlgGameOver;
    private javax.swing.JButton btnNextLevel;
    private javax.swing.JButton btnNormal;
    private javax.swing.JButton btnO;
    private javax.swing.JButton btnP;
    private javax.swing.JButton btnQ;
    private javax.swing.JButton btnR;
    private javax.swing.JButton btnS;
    private javax.swing.JButton btnSavePlayerName;
    private javax.swing.JButton btnT;
    private javax.swing.JButton btnU;
    private javax.swing.JButton btnV;
    private javax.swing.JButton btnW;
    private javax.swing.JButton btnX;
    private javax.swing.JButton btnY;
    private javax.swing.JButton btnZ;
    private javax.swing.JDialog dlgChooseDifficulty;
    private javax.swing.JDialog dlgGameOver;
    private javax.swing.JDialog dlgGameOverTop5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblDifficulty;
    private javax.swing.JLabel lblGameOver_dlgGameOver;
    private javax.swing.JLabel lblGameOver_dlgGameOverTop5;
    private javax.swing.JLabel lblHighScore;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblLevelTxt;
    private javax.swing.JLabel lblPlayerScore;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTop5;
    private javax.swing.JLabel lblTopic;
    private javax.swing.JLabel lblTopicTxt;
    private javax.swing.JLabel lblYourScore_dlgGameOver;
    private javax.swing.JLabel lblYourScore_dlgGameOverTop5;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlDrawHangman;
    private javax.swing.JPanel pnlPlayArea;
    private javax.swing.JPanel pnlPlayerInfo;
    private javax.swing.JTextField txtNameInput;
    private javax.swing.JTextArea txtPlayerNames;
    private javax.swing.JTextArea txtPlayerScores;
    private javax.swing.JLabel txtSecretWord;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
