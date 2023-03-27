/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ui.MainMenu;

/**
 * The default class only used to play the game.
 * @author CE171454 Hua Tien Thanh
 */
public class HangmanGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainMenu menu = new MainMenu();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
    
}
