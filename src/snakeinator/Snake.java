/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeinator;

import environment.LocationValidatorIntf;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Benjamin Wong
 */
public class Snake {

//<editor-fold defaultstate="collapsed" desc="Fields">
    private ArrayList<Point> body = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private GridDrawData drawData;
    private Boolean gamePlaying = true;
    private SnakeLocationValidatorIntf snakeLocationValidator;
    private Boolean paused = false;
    
    private int red;
    private int green;
    private int blue;
    private int opac;
    private int growthCounter;
    
    private Color WHITE = Color.WHITE;
    private int MAX_OPACITY = 255;
    private int MIN_OPACITY = 50;
    private int score;
    
    public final int HEAD_POSITION = 0;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Graphics Draw">
    public void draw(Graphics graphics) {
        int opacity = getMAX_OPACITY();
        int opacityStepSize = (getMAX_OPACITY() - getMIN_OPACITY()) / getBody().size();

        for (Point bodySegmentLocation : getBody()) {
            Point topLeft = drawData.getCellSystemCoordinate(bodySegmentLocation);
            graphics.setColor(new Color(getRed(), getGreen(), getBlue(), opacity));
            graphics.fillOval(topLeft.x, topLeft.y, drawData.getCellWidth(), drawData.getCellHeight());
            opacity -= opacityStepSize;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Movements">
    public void move() {
//make the snake move
        if (!paused) {
            Point newHead = (Point) getHead().clone();
            if (direction == Direction.DOWN) {
                newHead.y++;
            }
            if (direction == Direction.RIGHT) {
                newHead.x++;
            }
            if (direction == Direction.UP) {
                newHead.y--;
            }
            if (direction == Direction.LEFT) {
                newHead.x--;
            }
            if (snakeLocationValidator != null) {
                body.add(HEAD_POSITION, getSnakeLocationValidator().validateLocation(new SnakeAndPoint(this, newHead)).getPoint());
            }
            if (growthCounter <= 0) {
                body.remove(body.size() - 1);
            } else {
                growthCounter--;
            }
        }
    }
    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc="GamePlaying">
    public Boolean getGamePlaying() {
        return gamePlaying;
    }

    public void setGamePlaying(Boolean gamePlaying) {
        this.gamePlaying = gamePlaying;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Methods">
    public void togglePaused() {
        paused = !paused;
    }
    
    public void setColorCode(int red, int green, int blue, int opac) {
        this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
        this.setOpac(opac);
    }
    
    public int getGrowthCounter() {
        return growthCounter;
    }
    
    public void setGrowthCounter(int growthCounter) {
        this.growthCounter = growthCounter;
    }
    
    public void grow(int length) {
        growthCounter += length;
    }
    
    public int getMAX_OPACITY() {
        return MAX_OPACITY;
    }
    
    public void setMAX_OPACITY(int MAX_OPACITY) {
        this.MAX_OPACITY = MAX_OPACITY;
    }
    
    public int getMIN_OPACITY() {
        return MIN_OPACITY;
    }
    
    public void setMIN_OPACITY(int MIN_OPACITY) {
        this.MIN_OPACITY = MIN_OPACITY;
    }
    
    public Color getWHITE() {
        return WHITE;
    }
    
    public void setWHITE(Color WHITE) {
        this.WHITE = WHITE;
    }
    
    public int getRed() {
        return red;
    }
    
    public void setRed(int red) {
        this.red = red;
    }
    
    public int getGreen() {
        return green;
    }
    
    public void setGreen(int green) {
        this.green = green;
    }
    
    public int getBlue() {
        return blue;
    }
    
    public void setBlue(int blue) {
        this.blue = blue;
    }
    
    public int getOpac() {
        return opac;
    }
    
    public void setOpac(int opac) {
        this.opac = opac;
    }
    
    public SnakeLocationValidatorIntf getSnakeLocationValidator() {
        return snakeLocationValidator;
    }
    
    public void setSnakeLocationValidator(SnakeLocationValidatorIntf snakeLocationValidator) {
        this.snakeLocationValidator = snakeLocationValidator;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public ArrayList<Point> getBody() {
        return body;
    }
    
    public void setBody(ArrayList<Point> body) {
        this.body = body;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public GridDrawData getDrawData() {
        return drawData;
    }
    
    public void setDrawData(GridDrawData drawData) {
        this.drawData = drawData;
    }
    
    public Point getHead() {
        return body.get(HEAD_POSITION);
    }
//</editor-fold>

}
