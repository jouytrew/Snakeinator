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
    private Point position;
    private Color color;
    private int score;
    private int timeLeft;
    
    public Score(Point position, int score){
        this.position = position;
        this.score = score;
        if (score > 0){
            color = Color.GREEN;
        } else {
            color = Color.RED;
        }
        timeLeft = 20;
    }
    
    public void draw(Graphics graphics){
        graphics.setColor(getColor());
        graphics.setFont(new Font("Impact", Font.BOLD, 12));
        graphics.drawString("" + getScore(), 40 + 25*(getPosition().x), 40 + ((int) timeLeft/2) + 25*getPosition().y);
    }

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
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
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
        timeLeft --;
    }

    
}
