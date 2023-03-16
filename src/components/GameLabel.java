package components;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import main.Game;
import static utilz.Constants.GameConstants.TEXT_SECRET;

public class GameLabel extends JLabel {
    
    public GameLabel(String label) {
        this.setText(label);
        this.setFont(new Font("Consolas", Font.PLAIN, Game.FONT_SIZE));
    }
    
    public GameLabel(String label, int type) {
        switch (type) {
            case TEXT_SECRET:
                Map<TextAttribute, Object> attributes = new HashMap<>();
                attributes.put(TextAttribute.TRACKING, 0.15 * Game.SCALE);
                this.setText(label);
                this.setFont(new Font("Consolas", Font.BOLD, Game.FONT_SIZE * 3));
                this.setFont(this.getFont().deriveFont(attributes));
                break;
        }
    }
}
