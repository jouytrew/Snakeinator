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

    private ArrayList<Point> body = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private GridDrawData drawData;
    private Boolean gamePlaying = true;
//    private LocationValidatorIntf locationValidator;
    private SnakeLocationValidatorIntf snakeLocationValidator;
    private Boolean paused = false;

    private int red;
    private int green;
    private int blue;
    private int opac;
    private int growthCounter;

    private Image biohazard;
    private Color WHITE = Color.WHITE;
    private int MAX_OPACITY = 255;
    private int MIN_OPACITY = 50;
    private int score;

    /**
     * grow ---- eat move ---- die draw ----
     *
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
        int opacity = getMAX_OPACITY();
        int opacityStepSize = (getMAX_OPACITY() - getMIN_OPACITY()) / getBody().size();

        for (Point bodySegmentLocation : getBody()) {

            Point topLeft = drawData.getCellSystemCoordinate(bodySegmentLocation);
//            System.out.println("Location = " + bodySegmentLocation);
//            System.out.println("System Loc = " + drawData.getCellSystemCoordinate(bodySegmentLocation));
//            Point topLeft = getDrawData().getCellSystemCoordinate(bodySegmentLocation);
            graphics.setColor(new Color(getRed(), getGreen(), getBlue(), opacity));
            graphics.fillOval(topLeft.x, topLeft.y, drawData.getCellWidth(), drawData.getCellHeight());
            opacity -= opacityStepSize;

        }
    }

    public void drawBiohazard(Graphics graphics, Point point, Dimension dimension) {
        biohazard = ResourceTools.loadImageFromResource("resources/biohazard.png");
        graphics.fillOval(point.x, point.y, dimension.width, dimension.height);
        graphics.drawImage(biohazard, point.x + drawData.getCellWidth() / 16, point.y + drawData.getCellHeight() / 16,
                7 * dimension.width / 8, 7 * dimension.height / 8, null);
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
            if (snakeLocationValidator != null) {
//                body.add(HEAD_POSITION, getLocationValidator().validateLocation(newHead));
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

//    public LocationValidatorIntf getLocationValidator() {
//        return locationValidator;
//    }
//
//    public void setLocationValidator(LocationValidatorIntf locationValidator) {
//        this.locationValidator = locationValidator;
//    }

    public void togglePaused() {
        paused = !paused;
    }

    public void setColorCode(int red, int green, int blue, int opac) {
        this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
        this.setOpac(opac);
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

    public void grow(int length) {
        growthCounter += length;
    }

    /**
     * @return the MAX_OPACITY
     */
    public int getMAX_OPACITY() {
        return MAX_OPACITY;
    }

    /**
     * @param MAX_OPACITY the MAX_OPACITY to set
     */
    public void setMAX_OPACITY(int MAX_OPACITY) {
        this.MAX_OPACITY = MAX_OPACITY;
    }

    /**
     * @return the MIN_OPACITY
     */
    public int getMIN_OPACITY() {
        return MIN_OPACITY;
    }

    /**
     * @param MIN_OPACITY the MIN_OPACITY to set
     */
    public void setMIN_OPACITY(int MIN_OPACITY) {
        this.MIN_OPACITY = MIN_OPACITY;
    }

    /**
     * @return the WHITE
     */
    public Color getWHITE() {
        return WHITE;
    }

    /**
     * @param WHITE the WHITE to set
     */
    public void setWHITE(Color WHITE) {
        this.WHITE = WHITE;
    }

    /**
     * @return the red
     */
    public int getRed() {
        return red;
    }

    /**
     * @param red the red to set
     */
    public void setRed(int red) {
        this.red = red;
    }

    /**
     * @return the green
     */
    public int getGreen() {
        return green;
    }

    /**
     * @param green the green to set
     */
    public void setGreen(int green) {
        this.green = green;
    }

    /**
     * @return the blue
     */
    public int getBlue() {
        return blue;
    }

    /**
     * @param blue the blue to set
     */
    public void setBlue(int blue) {
        this.blue = blue;
    }

    /**
     * @return the opac
     */
    public int getOpac() {
        return opac;
    }

    /**
     * @param opac the opac to set
     */
    public void setOpac(int opac) {
        this.opac = opac;
    }

    /**
     * @return the snakeLocationValidator
     */
    public SnakeLocationValidatorIntf getSnakeLocationValidator() {
        return snakeLocationValidator;
    }

    /**
     * @param snakeLocationValidator the snakeLocationValidator to set
     */
    public void setSnakeLocationValidator(SnakeLocationValidatorIntf snakeLocationValidator) {
        this.snakeLocationValidator = snakeLocationValidator;
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
}
