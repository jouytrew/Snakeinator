/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeinator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Benjamin Wong
 */
public class Score {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
    timeLeft = DEFAULT_TIME;
}
    
    public Score(Point position, int score) {
        this.position = position;
        this.score = score;
    }
//</editor-fold>
    
    public void draw(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.setFont(new Font("Impact", Font.BOLD, 12));
        graphics.drawString(String.valueOf(getScore()), 40 + 25 * (getPosition().x), 40 + ((int) timeLeft / 2) + 25 * getPosition().y);
    }  

//<editor-fold defaultstate="collapsed" desc="Properties">
    public static final int DEFAULT_TIME = 20;
    
    private Point position;
    private int score;
    private int timeLeft;
    
    /**
     * @return the position
     */
    public Point getPosition() {
        return position;
    }
    
    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }
    
    /**
     * @return the color
     */
    public Color getColor() {
        return (score > 0) ? Color.GREEN : Color.RED;
    }
    
    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * @return the timeLeft
     */
    public int getTimeLeft() {
        return timeLeft;
    }
    
    /**
     * @param timeLeft the timeLeft to set
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
    
    void time() {
        timeLeft--;
    }
//</editor-fold>
}
