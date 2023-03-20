/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author CE170028 Truong Duy Minh
 */
public class MainMenu extends javax.swing.JFrame 
        implements WindowListener{

    private final Font OPTION_PANE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    
    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        this.addWindowListener(this);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/hangman_128.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgHowToPlay = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        lblHowToPlay = new javax.swing.JLabel();
        btnClose_dlgHowToPlay = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHowToPlay = new javax.swing.JTextPane();
        dlgAboutUs = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        lblAboutUs = new javax.swing.JLabel();
        btnClose_dlgAboutUs = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMemberName = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMemberRollNo = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnStartGame = new javax.swing.JButton();
        btnHowToPlay = new javax.swing.JButton();
        btnAboutUs = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();

        dlgHowToPlay.setTitle("How To Play");
        dlgHowToPlay.setModal(true);
        dlgHowToPlay.setUndecorated(true);
        dlgHowToPlay.setResizable(false);
        dlgHowToPlay.setSize(new java.awt.Dimension(650, 450));

        jPanel1.setBackground(new java.awt.Color(239, 247, 226));

        lblHowToPlay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblHowToPlay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHowToPlay.setText("How To Play");
        lblHowToPlay.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        btnClose_dlgHowToPlay.setBackground(new java.awt.Color(0, 153, 153));
        btnClose_dlgHowToPlay.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnClose_dlgHowToPlay.setForeground(new java.awt.Color(0, 102, 102));
        btnClose_dlgHowToPlay.setText("Close");
        btnClose_dlgHowToPlay.setContentAreaFilled(false);
        btnClose_dlgHowToPlay.setFocusPainted(false);
        btnClose_dlgHowToPlay.setRequestFocusEnabled(false);
        btnClose_dlgHowToPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose_dlgHowToPlayActionPerformed(evt);
            }
        });

        txtHowToPlay.setEditable(false);
        txtHowToPlay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtHowToPlay.setAutoscrolls(false);
        txtHowToPlay.setFocusable(false);
        jScrollPane1.setViewportView(txtHowToPlay);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(lblHowToPlay, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose_dlgHowToPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHowToPlay)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnClose_dlgHowToPlay)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout dlgHowToPlayLayout = new javax.swing.GroupLayout(dlgHowToPlay.getContentPane());
        dlgHowToPlay.getContentPane().setLayout(dlgHowToPlayLayout);
        dlgHowToPlayLayout.setHorizontalGroup(
            dlgHowToPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlgHowToPlayLayout.setVerticalGroup(
            dlgHowToPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dlgAboutUs.setTitle("About Us");
        dlgAboutUs.setModal(true);
        dlgAboutUs.setUndecorated(true);
        dlgAboutUs.setResizable(false);
        dlgAboutUs.setSize(new java.awt.Dimension(650, 596));

        jPanel2.setBackground(new java.awt.Color(253, 235, 252));
        jPanel2.setLayout(null);

        lblAboutUs.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblAboutUs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAboutUs.setText("About Us");
        lblAboutUs.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(lblAboutUs);
        lblAboutUs.setBounds(36, 12, 578, 48);

        btnClose_dlgAboutUs.setBackground(new java.awt.Color(153, 0, 0));
        btnClose_dlgAboutUs.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnClose_dlgAboutUs.setForeground(new java.awt.Color(153, 0, 0));
        btnClose_dlgAboutUs.setText("Close");
        btnClose_dlgAboutUs.setContentAreaFilled(false);
        btnClose_dlgAboutUs.setFocusPainted(false);
        btnClose_dlgAboutUs.setRequestFocusEnabled(false);
        btnClose_dlgAboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose_dlgAboutUsActionPerformed(evt);
            }
        });
        jPanel2.add(btnClose_dlgAboutUs);
        btnClose_dlgAboutUs.setBounds(230, 530, 185, 42);

        txtMemberName.setEditable(false);
        txtMemberName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtMemberName.setFocusable(false);
        jScrollPane2.setViewportView(txtMemberName);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(36, 299, 388, 210);

        txtMemberRollNo.setEditable(false);
        txtMemberRollNo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtMemberRollNo.setFocusable(false);
        jScrollPane3.setViewportView(txtMemberRollNo);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(442, 299, 172, 210);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("<html> This project was made by group 3, class SE1704, <br>FPT University<br> Semester: SP23<br> Subject: CSD201 (Data Structures and Algorithms)<br> Mentor: Vo Hong Khanh </html>");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(36, 78, 578, 160);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Member Name");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(36, 256, 388, 25);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Roll No.");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(442, 256, 172, 25);

        javax.swing.GroupLayout dlgAboutUsLayout = new javax.swing.GroupLayout(dlgAboutUs.getContentPane());
        dlgAboutUs.getContentPane().setLayout(dlgAboutUsLayout);
        dlgAboutUsLayout.setHorizontalGroup(
            dlgAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlgAboutUsLayout.setVerticalGroup(
            dlgAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hangman v1.0.1");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setText("HANGMAN PLUS");

        btnStartGame.setBackground(new java.awt.Color(223, 249, 243));
        btnStartGame.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnStartGame.setText("Start Game");
        btnStartGame.setFocusPainted(false);
        btnStartGame.setFocusable(false);
        btnStartGame.setPreferredSize(new java.awt.Dimension(329, 42));
        btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });

        btnHowToPlay.setBackground(new java.awt.Color(228, 255, 228));
        btnHowToPlay.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnHowToPlay.setText("How to Play");
        btnHowToPlay.setFocusPainted(false);
        btnHowToPlay.setFocusable(false);
        btnHowToPlay.setPreferredSize(new java.awt.Dimension(329, 42));
        btnHowToPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHowToPlayActionPerformed(evt);
            }
        });

        btnAboutUs.setBackground(new java.awt.Color(255, 234, 255));
        btnAboutUs.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnAboutUs.setText("About Us");
        btnAboutUs.setFocusPainted(false);
        btnAboutUs.setFocusable(false);
        btnAboutUs.setPreferredSize(new java.awt.Dimension(329, 42));
        btnAboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutUsActionPerformed(evt);
            }
        });

        btnQuit.setBackground(new java.awt.Color(255, 204, 204));
        btnQuit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnQuit.setText("Quit");
        btnQuit.setFocusPainted(false);
        btnQuit.setFocusable(false);
        btnQuit.setPreferredSize(new java.awt.Dimension(329, 42));
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHowToPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAboutUs, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHowToPlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAboutUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        // TODO add your handling code here:
        MainGameFrame game = new MainGameFrame();
        game.setLocationRelativeTo(null);
        game.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnStartGameActionPerformed

    private void btnHowToPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHowToPlayActionPerformed
        // TODO add your handling code here:
        String gameRules = "Welcome! If you have never played Hangman, or if you "
                + "need a reminder, here are the rules of the game:\n\n"
                + "1. Choose your difficulty: There are 4 options for you, "
                + "choose wisely! Especially that last one...\n\n"
                + "2. You will start from level 1. There will be a \"hidden word\" "
                + "containing only underscores which must be fully revealed.\n\n"
                + "3. To reveal the entire word, you must choose the letters "
                + "that make up the hidden word.\n\n"
                + "4. If you choose a correct letter, that letter will be revealed "
                + "on the hidden word and your score will be increased.\n\n"
                + "5. Choose a wrong option, and the Hangman painting will have "
                + "one extra stroke drawn. The game ends when the painting is "
                + "completed.\n\n"
                + "6. There is no final level, your job is to complete as many "
                + "word as possible and get the highest score.\n\n"
                + "Good luck!";
        txtHowToPlay.setText(gameRules);
        txtHowToPlay.setCaretPosition(0);
        dlgHowToPlay.setLocationRelativeTo(null);
        dlgHowToPlay.setVisible(true);
    }//GEN-LAST:event_btnHowToPlayActionPerformed

    private void btnClose_dlgHowToPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose_dlgHowToPlayActionPerformed
        // TODO add your handling code here:
        dlgHowToPlay.setVisible(false);
    }//GEN-LAST:event_btnClose_dlgHowToPlayActionPerformed

    private void btnClose_dlgAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose_dlgAboutUsActionPerformed
        // TODO add your handling code here:
        dlgAboutUs.setVisible(false);
    }//GEN-LAST:event_btnClose_dlgAboutUsActionPerformed

    private void btnAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutUsActionPerformed
        // TODO add your handling code here:
        String memberNames = "1. Truong Duy Minh\n"
                + "2. Nguyen Huu Duy\n"
                + "3. Hua Tien Thanh\n"
                + "4. Phan Khanh Linh\n"
                + "5. Vo Phuoc Tho\n"
                + "6. Le Tan Quoc";
        String memberRollNo = "CE170029\n"
                + "CE170563\n"
                + "CE171454\n"
                + "CE171528\n"
                + "CE161117\n"
                + "CE161868";
        txtMemberName.setText(memberNames);
        txtMemberRollNo.setText(memberRollNo);
        dlgAboutUs.setLocationRelativeTo(null);
        dlgAboutUs.setVisible(true);
    }//GEN-LAST:event_btnAboutUsActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        // TODO add your handling code here:
        this.windowClosing(null);
    }//GEN-LAST:event_btnQuitActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAboutUs;
    private javax.swing.JButton btnClose_dlgAboutUs;
    private javax.swing.JButton btnClose_dlgHowToPlay;
    private javax.swing.JButton btnHowToPlay;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JDialog dlgAboutUs;
    private javax.swing.JDialog dlgHowToPlay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAboutUs;
    private javax.swing.JLabel lblHowToPlay;
    private javax.swing.JTextPane txtHowToPlay;
    private javax.swing.JTextPane txtMemberName;
    private javax.swing.JTextPane txtMemberRollNo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        JLabel question = new JLabel("Do you want to quit?");
        JLabel goodbye = new JLabel("Goodbye and see you again!");
        question.setFont(OPTION_PANE_FONT);
        goodbye.setFont(OPTION_PANE_FONT);
        int result = JOptionPane.showConfirmDialog(this,
            question, "Quit Game?",
            JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                    goodbye, "Thanks for playing!",1);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.exit(0);
        } else {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}