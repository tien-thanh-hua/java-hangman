package gamepanels;

import components.GameButton;
import components.GameLabel;
import components.GameTextArea;
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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import main.Game;
import static utilz.Constants.GameConstants.*;

public class AboutUsPanel extends JPanel implements ActionListener {
    
    public AboutUsPanel(Game game) {
        this.game = game;
        game.updateProperties();
        this.setLayout(null);
        Dimension dimension = new Dimension(Game.MENU_WIDTH, Game.MENU_HEIGHT);
        this.setPreferredSize(dimension);
        initComponents();
    }

    private void initComponents() {

        lblTitleTxt = new GameLabel("ABOUT US", TITLE);
        lblTitleTxt.setBounds(Game.PADDING, Game.PADDING, Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        lblTitleTxt.setHorizontalAlignment(SwingConstants.CENTER);

        textArea = new GameTextArea(
            "ABOUT US\n" + 
            "Need some text here");
        scroll = new JScrollPane(textArea);
        textArea.setEditable(false);
        scroll.setBounds(Game.PADDING, Game.PADDING * 2 + Game.LABEL_HEIGHT, Game.LONG_LABEL_WIDTH, Game.MENU_HEIGHT - Game.PADDING * 4 - Game.LABEL_HEIGHT * 2);

        btnBack = new GameButton("Back");
        btnBack.setBounds(Game.PADDING, Game.PADDING * 3 + Game.LABEL_HEIGHT + (Game.MENU_HEIGHT - Game.PADDING * 4 - Game.LABEL_HEIGHT * 2), Game.LONG_LABEL_WIDTH, Game.LABEL_HEIGHT);
        btnBack.addActionListener(this);

        this.add(lblTitleTxt);
        this.add(scroll);
        this.add(btnBack);

    }

    // Variables declaration
    private Game game;
    private GameLabel lblTitleTxt;
    private GameButton btnBack;
    private GameTextArea textArea;
    private JScrollPane scroll;
    // Variables declaration

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnBack) {
            Game.GAME_STATE = BACK;
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
