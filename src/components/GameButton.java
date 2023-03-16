package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import main.Game;
import static utilz.Constants.GameConstants.DEFAULT_FONT;

public class GameButton extends JButton implements MouseListener {
    
    public GameButton(String label) {
        this.setText(label);
        this.setForeground(Color.BLACK);
        this.setBackground(Color.WHITE);
        this.setFont(DEFAULT_FONT);
        this.setFocusPainted(false);
        Border border = BorderFactory.createEtchedBorder(); 
        this.setBorder(border);
    }
    
    public GameButton(String label, int type) {
        if (type == 0) {
            this.setText(label);
            this.setForeground(Color.BLACK);
            this.setBackground(Color.WHITE);
            this.setFont(new Font("Consolas", Font.PLAIN, Game.FONT_SIZE));
            this.setFocusPainted(false);
            Border border = BorderFactory.createEtchedBorder();
            this.setBorder(border);
        }
        if (type == 1) {
            this.setText(label);
            if (Game.THEME == 1) {
            }
            this.setForeground(Color.BLACK);
            this.setBackground(Color.WHITE);
            this.setFont(new Font("Consolas", Font.PLAIN, Game.FONT_SIZE * 2));
            this.setFocusPainted(false);
            Border border = BorderFactory.createEtchedBorder();
            this.setBorder(border);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
