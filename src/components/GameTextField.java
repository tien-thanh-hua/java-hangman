package components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import static utilz.Constants.GameConstants.DEFAULT_FONT;

public class GameTextField extends JTextField implements FocusListener {
    
    public GameTextField(String text) {
        this.setText(text);
        this.setFont(DEFAULT_FONT);
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
