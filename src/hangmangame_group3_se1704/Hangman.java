/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame_group3_se1704;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 *
 * @author CE171454 Hua Tien Thanh
 */
public class Hangman {
    
    public static final int WIDTH = 300;
    public static final int HEIGHT = 380;
    public static final int HORIZONTAL_MIDDLE_POINT = WIDTH / 2;
    public static final int TOP_PADDING = 50;
    
    public static final int POLE_HEIGHT = 250;
    public static final int BASE_X = HORIZONTAL_MIDDLE_POINT - 80;
    public static final int BASE_Y = TOP_PADDING + POLE_HEIGHT;
    public static final int EXTENDER_X = HORIZONTAL_MIDDLE_POINT + 40;
    public static final int ROPE_LENGTH = TOP_PADDING + 60;
    
    public static final int HEAD_DIAMETER = 40;
    public static final int HEAD_RADIUS = HEAD_DIAMETER / 2;
    public static final int HEAD_TOP_X = EXTENDER_X - (HEAD_RADIUS);
    public static final int HEAD_TOP_Y = ROPE_LENGTH;
    public static final int HEAD_BOTTOM_X = EXTENDER_X;
    public static final int HEAD_BOTTOM_Y = HEAD_TOP_Y + HEAD_DIAMETER;
    
    public static final int BODY_HEIGHT = 80;
    public static final int BODY_X = HEAD_BOTTOM_X;
    public static final int BODY_TOP_Y = HEAD_BOTTOM_Y;
    public static final int BODY_BOTTOM_Y = BODY_TOP_Y + BODY_HEIGHT;
    
    public static final int NECK_SIZE = 20;
    public static final int ARMS_LENGTH = 30;
    public static final int ARMS_TOP_X = BODY_X;
    public static final int ARMS_TOP_Y = BODY_TOP_Y + NECK_SIZE;
    public static final int LEFT_ARM_X = ARMS_TOP_X - 30;
    public static final int RIGHT_ARM_X = ARMS_TOP_X + 30;
    public static final int ARMS_BOTTOM_Y = ARMS_TOP_Y + ARMS_LENGTH;
    
    public static final int LEGS_LENGTH = ARMS_LENGTH;
    public static final int LEGS_TOP_X = BODY_X;
    public static final int LEGS_TOP_Y = BODY_BOTTOM_Y;
    public static final int LEFT_LEG_X = LEFT_ARM_X;
    public static final int RIGHT_LEG_X = RIGHT_ARM_X;
    public static final int LEGS_BOTTOM_Y = LEGS_TOP_Y + LEGS_LENGTH;
    
    private int state;
    
    public Hangman(String difficulty) {
        switch (difficulty) {
            case "medium":
            case "hard":
            case "asian":
                state = 3;
                break;
            default:
                state = 0;
                break;
        }
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
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(5));
        g.draw(new Line2D.Float(BASE_X - 30, BASE_Y, BASE_X + 30, BASE_Y));
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
                g.draw(new Line2D.Float(BASE_X - 30, BASE_Y, BASE_X + 30, BASE_Y));
                break;
        }
    }
}
