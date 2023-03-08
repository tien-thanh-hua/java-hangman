/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame_group3_se1704;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author CE171454 Hua Tien Thanh
 */
public class Hangman {
    
    public static final int DRAWING_WIDTH = 300;
    public static final int DRAWING_HEIGHT = 380;
    public static final int DRAWING_MIDDLE_POINT = DRAWING_WIDTH / 2;
    public static final int DRAWING_TOP_PADDING = 60;
    
    private boolean hasBase = false;
    private boolean hasPole = false;
    private boolean hasExtender = false;
    private boolean hasRope = false;
    
    private boolean hasHead = false;
    private boolean hasBody = false;
    private boolean hasLeftArm = false;
    private boolean hasRightArm = false;
    private boolean hasLeftLeg = false;
    private boolean hasRightLeg = false;
    
    public Hangman(String difficulty) {
        switch (difficulty) {
            case "medium":
            case "hard":
            case "asian":
                this.setBase(true);
                this.setPole(true);
                this.setExtender(true);
                this.setRope(true);
                break;
        }
    }

    public boolean hasBase() {
        return hasBase;
    }

    public void setBase(boolean hasBase) {
        this.hasBase = hasBase;
    }

    public boolean hasPole() {
        return hasPole;
    }

    public void setPole(boolean hasPole) {
        this.hasPole = hasPole;
    }

    public boolean hasExtender() {
        return hasExtender;
    }

    public void setExtender(boolean hasExtender) {
        this.hasExtender = hasExtender;
    }

    public boolean hasRope() {
        return hasRope;
    }

    public void setRope(boolean hasRope) {
        this.hasRope = hasRope;
    }

    public boolean hasHead() {
        return hasHead;
    }

    public void setHead(boolean hasHead) {
        this.hasHead = hasHead;
    }

    public boolean hasBody() {
        return hasBody;
    }

    public void setBody(boolean hasBody) {
        this.hasBody = hasBody;
    }

    public boolean hasLeftArm() {
        return hasLeftArm;
    }

    public void setLeftArm(boolean hasLeftArm) {
        this.hasLeftArm = hasLeftArm;
    }

    public boolean hasRightArm() {
        return hasRightArm;
    }

    public void setRightArm(boolean hasRightArm) {
        this.hasRightArm = hasRightArm;
    }

    public boolean hasLeftLeg() {
        return hasLeftLeg;
    }

    public void setLeftLeg(boolean hasLeftLeg) {
        this.hasLeftLeg = hasLeftLeg;
    }

    public boolean hasRightLeg() {
        return hasRightLeg;
    }

    public void setRightLeg(boolean hasRightLeg) {
        this.hasRightLeg = hasRightLeg;
    }
    
    public void draw(Graphics2D g) {
        
        g.setColor(Color.black);
        g.drawLine(DRAWING_MIDDLE_POINT, DRAWING_TOP_PADDING, DRAWING_MIDDLE_POINT, DRAWING_TOP_PADDING + 200);
        
    }
}
