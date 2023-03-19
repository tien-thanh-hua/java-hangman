package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import main.Game;

public class GameTextField extends JTextField implements FocusListener {
    
    public GameTextField(String text) {
        this.setText(text);
        this.setFont(new Font("Consolas", Font.PLAIN, Game.FONT_SIZE));
        this.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.setForeground(Color.BLACK);
        this.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
}
