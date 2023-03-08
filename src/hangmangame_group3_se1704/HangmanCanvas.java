/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame_group3_se1704;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author CE171454 Hua Tien Thanh
 */
public class HangmanCanvas extends JPanel{
    Graphics2D g;
    Hangman hman = new Hangman("easy");
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.g = (Graphics2D) graphics;
        hman.draw(g);
    }
}
