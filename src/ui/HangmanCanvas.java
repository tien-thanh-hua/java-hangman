/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.Hangman;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Hangman drawing canvas used for MainGameFrame.
 * @author CE171454 Hua Tien Thanh
 */
public class HangmanCanvas extends JPanel{
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
    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.g = (Graphics2D) graphics;
        hangMan.draw(g);
    }
}
