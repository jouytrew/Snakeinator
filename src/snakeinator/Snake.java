/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeinator;

import environment.LocationValidatorIntf;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Benjamin Wong
 */
public class Snake {

    private ArrayList<Point> body = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private GridDrawData drawData;
    private Boolean gamePlaying = true;
    private LocationValidatorIntf locationValidator;
    private Boolean paused = false;

    private int red;
    private int green;
    private int blue;
    private int growthCounter;

    /**
     * grow ---- 
     * eat 
     * move ---- 
     * die 
     * draw ----
     * @return 
     */
    
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
    
    public void draw(Graphics graphics) {
        for (Point bodySegmentLocation : getBody()) {
            System.out.println("Location = " + bodySegmentLocation);
            System.out.println("System Loc = " + drawData.getCellSystemCoordinate(bodySegmentLocation));

            Point topLeft = drawData.getCellSystemCoordinate(bodySegmentLocation);

            graphics.setColor(new Color(red, green, blue));
            graphics.fillOval(topLeft.x, topLeft.y, drawData.getCellWidth(), drawData.getCellHeight());
        }
    }

    public final int HEAD_POSITION = 0;

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
            if (locationValidator != null) {
                body.add(HEAD_POSITION, getLocationValidator().validateLocation(newHead));
            }
            if (growthCounter <= 0){
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

    public LocationValidatorIntf getLocationValidator() {
        return locationValidator;
    }

    public void setLocationValidator(LocationValidatorIntf locationValidator) {
        this.locationValidator = locationValidator;
    }

    public void togglePaused() {
        paused = !paused;
    }

    public void setColorCode(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }    
    /**
     * @return the growthCounter
     */
    public int getGrowthCounter() {
        return growthCounter;
    }

    /**
     * @param growthCounter the growthCounter to set
     */
    public void setGrowthCounter(int growthCounter) {
        this.growthCounter = growthCounter;
    }
    
    public void grow(int length){
        growthCounter += length;
    }

}
