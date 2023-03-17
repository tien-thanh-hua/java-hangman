/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import main.Game;
import static utilz.Constants.GameConstants.*;

/**
 *
 * @author CE171454 Hua Tien Thanh
 */
public class Hangman {
    
    public static int WIDTH = (int) (DEFAULT_HANGMAN_PANEL_WIDTH * Game.SCALE);
    public static int HEIGHT = (int) (DEFAULT_HANGMAN_PANEL_HEIGHT * Game.SCALE);
    public static int HORIZONTAL_MIDDLE_POINT = WIDTH / 2;
    public static int TOP_PADDING = (int) (Game.SCALE * 50);
    
    public static int POLE_HEIGHT = (int) (Game.SCALE * 125);
    public static int BASE_X = HORIZONTAL_MIDDLE_POINT - (int) (Game.SCALE * 40);
    public static int BASE_Y = TOP_PADDING + POLE_HEIGHT;
    public static int EXTENDER_X = HORIZONTAL_MIDDLE_POINT + (int) (Game.SCALE * 20);
    public static int ROPE_LENGTH = TOP_PADDING + (int) (Game.SCALE * 30);
    
    public static int HEAD_DIAMETER = (int) (Game.SCALE * 20);
    public static int HEAD_RADIUS = HEAD_DIAMETER / 2;
    public static int HEAD_TOP_X = EXTENDER_X - (HEAD_RADIUS);
    public static int HEAD_TOP_Y = ROPE_LENGTH;
    public static int HEAD_BOTTOM_X = EXTENDER_X;
    public static int HEAD_BOTTOM_Y = HEAD_TOP_Y + HEAD_DIAMETER;
    
    public static int BODY_HEIGHT = (int) (Game.SCALE * 40);
    public static int BODY_X = HEAD_BOTTOM_X;
    public static int BODY_TOP_Y = HEAD_BOTTOM_Y;
    public static int BODY_BOTTOM_Y = BODY_TOP_Y + BODY_HEIGHT;
    
    public static int NECK_SIZE = (int) (Game.SCALE * 10);
    public static int ARMS_LENGTH = (int) (Game.SCALE * 15);
    public static int ARMS_TOP_X = BODY_X;
    public static int ARMS_TOP_Y = BODY_TOP_Y + NECK_SIZE;
    public static int LEFT_ARM_X = ARMS_TOP_X - (int) (Game.SCALE * 15);
    public static int RIGHT_ARM_X = ARMS_TOP_X + (int) (Game.SCALE * 15);
    public static int ARMS_BOTTOM_Y = ARMS_TOP_Y + ARMS_LENGTH;
    
    public static int LEGS_LENGTH = ARMS_LENGTH;
    public static int LEGS_TOP_X = BODY_X;
    public static int LEGS_TOP_Y = BODY_BOTTOM_Y;
    public static int LEFT_LEG_X = LEFT_ARM_X;
    public static int RIGHT_LEG_X = RIGHT_ARM_X;
    public static int LEGS_BOTTOM_Y = LEGS_TOP_Y + LEGS_LENGTH;
    
    private int state;
    
    public Hangman() {
        
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    public void increaseState() {
        setState(this.getState() + 1);
    }
    
    public void draw(Graphics2D g) {
        
        WIDTH = (int) (DEFAULT_HANGMAN_PANEL_WIDTH * Game.SCALE);
        HEIGHT = (int) (DEFAULT_HANGMAN_PANEL_HEIGHT * Game.SCALE);
        HORIZONTAL_MIDDLE_POINT = WIDTH / 2;
        TOP_PADDING = (int) (Game.SCALE * 50);

        POLE_HEIGHT = (int) (Game.SCALE * 125);
        BASE_X = HORIZONTAL_MIDDLE_POINT - (int) (Game.SCALE * 40);
        BASE_Y = TOP_PADDING + POLE_HEIGHT;
        EXTENDER_X = HORIZONTAL_MIDDLE_POINT + (int) (Game.SCALE * 20);
        ROPE_LENGTH = TOP_PADDING + (int) (Game.SCALE * 30);

        HEAD_DIAMETER = (int) (Game.SCALE * 20);
        HEAD_RADIUS = HEAD_DIAMETER / 2;
        HEAD_TOP_X = EXTENDER_X - (HEAD_RADIUS);
        HEAD_TOP_Y = ROPE_LENGTH;
        HEAD_BOTTOM_X = EXTENDER_X;
        HEAD_BOTTOM_Y = HEAD_TOP_Y + HEAD_DIAMETER;

        BODY_HEIGHT = (int) (Game.SCALE * 40);
        BODY_X = HEAD_BOTTOM_X;
        BODY_TOP_Y = HEAD_BOTTOM_Y;
        BODY_BOTTOM_Y = BODY_TOP_Y + BODY_HEIGHT;

        NECK_SIZE = (int) (Game.SCALE * 10);
        ARMS_LENGTH = (int) (Game.SCALE * 15);
        ARMS_TOP_X = BODY_X;
        ARMS_TOP_Y = BODY_TOP_Y + NECK_SIZE;
        LEFT_ARM_X = ARMS_TOP_X - (int) (Game.SCALE * 15);
        RIGHT_ARM_X = ARMS_TOP_X + (int) (Game.SCALE * 15);
        ARMS_BOTTOM_Y = ARMS_TOP_Y + ARMS_LENGTH;

        LEGS_LENGTH = ARMS_LENGTH;
        LEGS_TOP_X = BODY_X;
        LEGS_TOP_Y = BODY_BOTTOM_Y;
        LEFT_LEG_X = LEFT_ARM_X;
        RIGHT_LEG_X = RIGHT_ARM_X;
        LEGS_BOTTOM_Y = LEGS_TOP_Y + LEGS_LENGTH;
        
        g.setColor(Color.black);
        g.setStroke(new BasicStroke((int) (Game.SCALE * 2.5f)));
        g.draw(new Line2D.Float(BASE_X - (int) (Game.SCALE * 15), BASE_Y, BASE_X + (int) (Game.SCALE * 15), BASE_Y));
        switch (state) {
            case 9:
                g.setColor(Color.red); // game over: entire hangman turns red
                g.draw(new Line2D.Float(LEGS_TOP_X, LEGS_TOP_Y, RIGHT_LEG_X, LEGS_BOTTOM_Y));
            case 8:
                g.draw(new Line2D.Float(LEGS_TOP_X, LEGS_TOP_Y, LEFT_LEG_X, LEGS_BOTTOM_Y));
            case 7:
                g.draw(new Line2D.Float(ARMS_TOP_X, ARMS_TOP_Y, RIGHT_ARM_X, ARMS_BOTTOM_Y));
            case 6:
                g.draw(new Line2D.Float(ARMS_TOP_X, ARMS_TOP_Y, LEFT_ARM_X, ARMS_BOTTOM_Y));
            case 5:
                g.draw(new Line2D.Float(BODY_X, BODY_TOP_Y, BODY_X, BODY_BOTTOM_Y));
            case 4:
                g.draw(new Ellipse2D.Float(HEAD_TOP_X, HEAD_TOP_Y, HEAD_DIAMETER, HEAD_DIAMETER));
            case 3:
                g.draw(new Line2D.Float(EXTENDER_X, TOP_PADDING, EXTENDER_X, ROPE_LENGTH));
            case 2:
                g.draw(new Line2D.Float(BASE_X, TOP_PADDING, EXTENDER_X, TOP_PADDING));
            case 1:
                g.draw(new Line2D.Float(BASE_X, TOP_PADDING, BASE_X, BASE_Y));
            case 0:
                g.draw(new Line2D.Float(BASE_X - (int) (Game.SCALE * 15), BASE_Y, BASE_X + (int) (Game.SCALE * 15), BASE_Y));
                break;
        }
    }
}
