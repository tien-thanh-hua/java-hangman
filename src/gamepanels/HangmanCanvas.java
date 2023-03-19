/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepanels;

import components.GamePanel;
import entities.Hangman;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author CE171454 Hua Tien Thanh
 */
public class HangmanCanvas extends GamePanel {
    private Graphics2D g = null;
    Hangman hangMan = null;
    
    public HangmanCanvas(Hangman hangman) {
        this.hangMan = hangman;
    }

    public Hangman getHangMan() {
        return hangMan;
    }

    public void setHangMan(Hangman hangMan) {
        this.hangMan = hangMan;
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.g = (Graphics2D) graphics;
        hangMan.draw(g);
    }
}
