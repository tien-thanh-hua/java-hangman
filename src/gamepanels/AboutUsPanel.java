package gamepanels;

import components.GamePanel;
import components.GameButton;
import components.GameLabel;
import components.GameTextArea;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import main.Game;
import static utilz.Constants.GameConstants.*;

public class AboutUsPanel extends GamePanel implements ActionListener {
    
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
            game.getAboutUsWindow().dispose();
            game.getMainmenuWindow().setVisible(true);
        }
    }
}
