package gamepanels;

import components.GamePanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawHangmanPanel extends GamePanel {
    
    public DrawHangmanPanel() {
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
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
