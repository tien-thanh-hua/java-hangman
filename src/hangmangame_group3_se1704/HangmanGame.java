/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame_group3_se1704;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ibuyc
 */
public class HangmanGame extends javax.swing.JFrame {
    
    private HangmanCanvas hCanvas = null;
    private int score;
    private int level;
    private String currentDifficulty;
    
    private Hangman hangMan;
    
    private HashMap<String, Integer> difficultyFactors;
    
    private char playerChoice;
    
    private Question question;
    
    private enum QuestionTopics {
        City, 
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public char getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice(char playerChoice) {
        this.playerChoice = playerChoice;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(String currentDifficulty) {
        this.currentDifficulty = currentDifficulty;
    }

    public HashMap<String, Integer> getDifficultyFactors() {
        return difficultyFactors;
    }

    public void setDifficultyFactors(String difficulty) {
        HashMap<String, Integer> newDifficultyFactors = new HashMap<>();
        switch (difficulty) {
            case "easy":
                newDifficultyFactors.put(difficulty, 1);
                break;
            case "medium":
                newDifficultyFactors.put(difficulty, 2);
                break;
            case "hard":
                newDifficultyFactors.put(difficulty, 4);
                break;
            case "asian":
                newDifficultyFactors.put(difficulty, 16);
                break;
        }
        
        this.difficultyFactors = newDifficultyFactors;
    }
    

    public void initGame() {
        pnlDrawHangman.setLayout(null);
        pnlDrawHangman.removeAll();
        pnlDrawHangman.revalidate();
        pnlDrawHangman.repaint();
        
        hCanvas = new HangmanCanvas();
        pnlDrawHangman.setLayout(new BorderLayout());
        pnlDrawHangman.add(hCanvas, BorderLayout.CENTER);
        score = 0;
        
        hangMan = hCanvas.getHangMan();
        
        // temporary data for testing
        this.question = new Question("ONTARIO");
        this.setCurrentDifficulty("medium");
        this.setDifficultyFactors(currentDifficulty);
        String properDifficulty = Character.toUpperCase(currentDifficulty.charAt(0)) + currentDifficulty.substring(1);
        
        StyledDocument doc = txtSecretWord.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        txtSecretWord.setText(question.getUserString());
        lblDifficulty.setText(properDifficulty);
        /*
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
        */
    }
    
    public void increaseScore() {
        this.setScore(this.getScore() + 100*difficultyFactors.get(currentDifficulty));
        updateScoreText();
    }
    
    public void updateScoreText() {
        lblPlayerScore.setText(this.getScore()+"");
    }
    
    public boolean isLevelCompleted() {
        return this.question.isCompleted();
    }

    public boolean isGameOver() {
        return this.hangMan.getState() == 9;
    }

    public boolean isCorrect() {
        return (question.getSecretString().indexOf(playerChoice)) != -1;
    }
    
    public boolean isWrong() {
        return !isCorrect();
    }
    
    public void updateHiddenText() {
        question.updateString(playerChoice);
        txtSecretWord.setText(question.getUserString());
        StyledDocument doc = txtSecretWord.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }
    
    public void gameOver() {
        
    }
    
    public void letterActionPerformed(ActionEvent e) {
        if (isCorrect()) {
            increaseScore();
            updateHiddenText();
        } else {
            this.hCanvas.getHangMan().increaseState();
            pnlDrawHangman.repaint();
        }
        
        if (isLevelCompleted()) {
            // proceeds to next level
        } else if (isGameOver()) {
            // stops current game, display score
        }
    }

    /**
     * Creates new form HangmanGame
     */
    public HangmanGame() {
        initComponents();
        this.setLocationRelativeTo(null); // put this into the center of screen
        initGame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSecretWord = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblDifficulty = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblPlayerScore = new javax.swing.JLabel();
        pnlDrawHangman = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Word Display"));

        btnA.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnA.setText("A");
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
        btnZ.setMaximumSize(new java.awt.Dimension(55, 40));
        btnZ.setMinimumSize(new java.awt.Dimension(55, 40));
        btnZ.setPreferredSize(new java.awt.Dimension(55, 40));
        btnZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZActionPerformed(evt);
            }
        });

        txtSecretWord.setEditable(false);
        txtSecretWord.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        txtSecretWord.setFocusable(false);
        jScrollPane1.setViewportView(txtSecretWord);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnW, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnF, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Game Information"));

        jLabel12.setText("Level:");

        lblLevel.setText("0");

        jLabel1.setText("Difficulty: ");

        lblDifficulty.setText("Easy");

        jLabel2.setText("Player Score");

        lblPlayerScore.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDifficulty, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPlayerScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblLevel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDifficulty))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblPlayerScore))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDrawHangman.setBorder(javax.swing.BorderFactory.createTitledBorder("Hangman Painting"));
        pnlDrawHangman.setPreferredSize(new java.awt.Dimension(300, 380));

        javax.swing.GroupLayout pnlDrawHangmanLayout = new javax.swing.GroupLayout(pnlDrawHangman);
        pnlDrawHangman.setLayout(pnlDrawHangmanLayout);
        pnlDrawHangmanLayout.setHorizontalGroup(
            pnlDrawHangmanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlDrawHangmanLayout.setVerticalGroup(
            pnlDrawHangmanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu1.setText("New Game");

        jMenuItem1.setText("Easy");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Medium");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Hard");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Asian");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Score");

        jMenuItem5.setText("Personal High Score");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("High Score");
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        jMenuItem7.setText("Game Manual");
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDrawHangman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDrawHangman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed
        // TODO add your handling code here:
        playerChoice = 'A';
        letterActionPerformed(evt);
        btnA.setEnabled(false);
    }//GEN-LAST:event_btnAActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        // TODO add your handling code here:
        playerChoice = 'B';
        letterActionPerformed(evt);
        btnB.setEnabled(false);
    }//GEN-LAST:event_btnBActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        // TODO add your handling code here:
        playerChoice = 'C';
        letterActionPerformed(evt);
        btnC.setEnabled(false);
    }//GEN-LAST:event_btnCActionPerformed

    private void btnDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDActionPerformed
        // TODO add your handling code here:
        playerChoice = 'D';
        letterActionPerformed(evt);
        btnD.setEnabled(false);
    }//GEN-LAST:event_btnDActionPerformed

    private void btnEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEActionPerformed
        // TODO add your handling code here:
        playerChoice = 'E';
        letterActionPerformed(evt);
        btnE.setEnabled(false);
    }//GEN-LAST:event_btnEActionPerformed

    private void btnFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFActionPerformed
        // TODO add your handling code here:
        playerChoice = 'F';
        letterActionPerformed(evt);
        btnF.setEnabled(false);
    }//GEN-LAST:event_btnFActionPerformed

    private void btnGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGActionPerformed
        // TODO add your handling code here:
        playerChoice = 'G';
        letterActionPerformed(evt);
        btnG.setEnabled(false);
    }//GEN-LAST:event_btnGActionPerformed

    private void btnHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHActionPerformed
        // TODO add your handling code here:
        playerChoice = 'H';
        letterActionPerformed(evt);
        btnH.setEnabled(false);
    }//GEN-LAST:event_btnHActionPerformed

    private void btnIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIActionPerformed
        // TODO add your handling code here:
        playerChoice = 'I';
        letterActionPerformed(evt);
        btnI.setEnabled(false);
    }//GEN-LAST:event_btnIActionPerformed

    private void btnJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJActionPerformed
        // TODO add your handling code here:
        playerChoice = 'J';
        letterActionPerformed(evt);
        btnJ.setEnabled(false);
    }//GEN-LAST:event_btnJActionPerformed

    private void btnKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKActionPerformed
        // TODO add your handling code here:
        playerChoice = 'K';
        letterActionPerformed(evt);
        btnK.setEnabled(false);
    }//GEN-LAST:event_btnKActionPerformed

    private void btnLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLActionPerformed
        // TODO add your handling code here:
        playerChoice = 'L';
        letterActionPerformed(evt);
        btnL.setEnabled(false);
    }//GEN-LAST:event_btnLActionPerformed

    private void btnMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMActionPerformed
        // TODO add your handling code here:
        playerChoice = 'M';
        letterActionPerformed(evt);
        btnM.setEnabled(false);
    }//GEN-LAST:event_btnMActionPerformed

    private void btnNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNActionPerformed
        // TODO add your handling code here:
        playerChoice = 'N';
        letterActionPerformed(evt);
        btnN.setEnabled(false);
    }//GEN-LAST:event_btnNActionPerformed

    private void btnOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOActionPerformed
        // TODO add your handling code here:
        playerChoice = 'O';
        letterActionPerformed(evt);
        btnO.setEnabled(false);
    }//GEN-LAST:event_btnOActionPerformed

    private void btnPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPActionPerformed
        // TODO add your handling code here:
        playerChoice = 'P';
        letterActionPerformed(evt);
        btnP.setEnabled(false);
    }//GEN-LAST:event_btnPActionPerformed

    private void btnQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQActionPerformed
        // TODO add your handling code here:
        playerChoice = 'Q';
        letterActionPerformed(evt);
        btnQ.setEnabled(false);
    }//GEN-LAST:event_btnQActionPerformed

    private void btnRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRActionPerformed
        // TODO add your handling code here:
        playerChoice = 'R';
        letterActionPerformed(evt);
        btnR.setEnabled(false);
    }//GEN-LAST:event_btnRActionPerformed

    private void btnSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSActionPerformed
        // TODO add your handling code here:
        playerChoice = 'S';
        letterActionPerformed(evt);
        btnS.setEnabled(false);
    }//GEN-LAST:event_btnSActionPerformed

    private void btnTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTActionPerformed
        // TODO add your handling code here:
        playerChoice = 'T';
        letterActionPerformed(evt);
        btnT.setEnabled(false);
    }//GEN-LAST:event_btnTActionPerformed

    private void btnUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUActionPerformed
        // TODO add your handling code here:
        playerChoice = 'U';
        letterActionPerformed(evt);
        btnU.setEnabled(false);
    }//GEN-LAST:event_btnUActionPerformed

    private void btnVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVActionPerformed
        // TODO add your handling code here:
        playerChoice = 'V';
        letterActionPerformed(evt);
        btnV.setEnabled(false);
    }//GEN-LAST:event_btnVActionPerformed

    private void btnWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWActionPerformed
        // TODO add your handling code here:
        playerChoice = 'W';
        letterActionPerformed(evt);
        btnW.setEnabled(false);
    }//GEN-LAST:event_btnWActionPerformed

    private void btnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXActionPerformed
        // TODO add your handling code here:
        playerChoice = 'X';
        letterActionPerformed(evt);
        btnX.setEnabled(false);
    }//GEN-LAST:event_btnXActionPerformed

    private void btnYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYActionPerformed
        // TODO add your handling code here:
        playerChoice = 'Y';
        letterActionPerformed(evt);
        btnY.setEnabled(false);
    }//GEN-LAST:event_btnYActionPerformed

    private void btnZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZActionPerformed
        // TODO add your handling code here:
        playerChoice = 'Z';
        letterActionPerformed(evt);
        btnZ.setEnabled(false);
    }//GEN-LAST:event_btnZActionPerformed

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
            java.util.logging.Logger.getLogger(HangmanGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HangmanGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HangmanGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HangmanGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HangmanGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnD;
    private javax.swing.JButton btnE;
    private javax.swing.JButton btnF;
    private javax.swing.JButton btnG;
    private javax.swing.JButton btnH;
    private javax.swing.JButton btnI;
    private javax.swing.JButton btnJ;
    private javax.swing.JButton btnK;
    private javax.swing.JButton btnL;
    private javax.swing.JButton btnM;
    private javax.swing.JButton btnN;
    private javax.swing.JButton btnO;
    private javax.swing.JButton btnP;
    private javax.swing.JButton btnQ;
    private javax.swing.JButton btnR;
    private javax.swing.JButton btnS;
    private javax.swing.JButton btnT;
    private javax.swing.JButton btnU;
    private javax.swing.JButton btnV;
    private javax.swing.JButton btnW;
    private javax.swing.JButton btnX;
    private javax.swing.JButton btnY;
    private javax.swing.JButton btnZ;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDifficulty;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblPlayerScore;
    private javax.swing.JPanel pnlDrawHangman;
    private javax.swing.JTextPane txtSecretWord;
    // End of variables declaration//GEN-END:variables
}
