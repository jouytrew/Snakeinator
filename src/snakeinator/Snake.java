/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeinator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Benjamin Wong
 */
public class Snake {

//<editor-fold defaultstate="collapsed" desc="Fields">
    private ArrayList<Point> snake = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private GridDrawData drawData;
    private boolean gamePlaying = true;
    private SnakeLocationValidatorIntf snakeLocationValidator;
    private Direction noMove = null;

    private int red;
    private int green;
    private int blue;
    private int opac;
    private int growthCounter;

    private Color WHITE = Color.WHITE;
    private double MAX_OPACITY = 255;
    private double MIN_OPACITY = 30;
    private int score;

    static public final int HEAD_POSITION = 0;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Graphics Draw">
    public void draw(Graphics graphics) {
        double opacity = getMAX_OPACITY();
        double opacityStepSize = (getMAX_OPACITY() - getMIN_OPACITY()) / getSnake().size();

        for (Point bodySegmentLocation : getSafeSnake()) {
            Point topLeft = drawData.getCellSystemCoordinate(bodySegmentLocation);
            graphics.setColor(new Color(getRed(), getGreen(), getBlue(), (int) opacity));
            graphics.fillRect(topLeft.x, topLeft.y, drawData.getCellWidth(), drawData.getCellHeight());
            opacity -= opacityStepSize;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Movements">
    public void move() {
//make the snake move
        Point newHead = (Point) getHead().clone();
        if (direction == Direction.DOWN) {
            newHead.y++;
            setNoMove(Direction.UP);
        }
        if (direction == Direction.RIGHT) {
            newHead.x++;
            setNoMove(Direction.LEFT);
        }
        if (direction == Direction.UP) {
            newHead.y--;
            setNoMove(Direction.DOWN);
        }
        if (direction == Direction.LEFT) {
            newHead.x--;
            setNoMove(Direction.RIGHT);
        }
        if (snakeLocationValidator != null) {
            snake.add(HEAD_POSITION, getSnakeLocationValidator().validateLocation(new SnakeAndPoint(this, newHead)).getPoint());
        }
        if (growthCounter <= 0) {
            snake.remove(snake.size() - 1);
        } else {
            growthCounter--;

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
    public void checkSelfHit() {
        int i = 0;
        for (Point bodyPart : getBody()) {
            i++;
            if (getHead().equals(bodyPart)) {
                //remove everthing from position i onwards...?!?!?!
                for (int j = snake.size() - 1; j >= i; j--) {
                    snake.remove(j);
                    setScore(getScore() - 25);
                }
                break;
            }
        }
    }

    public Snake(Direction direction, GridDrawData drawData, SnakeLocationValidatorIntf snakeLocationValidator) {
        this.direction = direction;
        this.drawData = drawData;
        this.snakeLocationValidator = snakeLocationValidator;
    }

    public void setColorCode(int red, int green, int blue) {
        this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
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

    public double getMAX_OPACITY() {
        return MAX_OPACITY;
    }

    public void setMAX_OPACITY(int MAX_OPACITY) {
        this.MAX_OPACITY = MAX_OPACITY;
    }

    public double getMIN_OPACITY() {
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

    public ArrayList<Point> getSnake() {
        return snake;
    }

    private ArrayList<Point> getSafeSnake() {
        ArrayList<Point> safeBody = new ArrayList<>();
        for (Point location : getSnake()) {
            safeBody.add(location);
        }
        return safeBody;
    }

    public ArrayList<Point> getBody() {
        ArrayList<Point> body = new ArrayList<>();
        for (int i = 1; i < this.snake.size(); i++) {
            body.add(this.snake.get(i));
        }
        return body;
    }

    public void setSnake(ArrayList<Point> snake) {
        this.snake = snake;
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
        return snake.get(HEAD_POSITION);
    }

    public boolean contains(Point location) {
        for (Point bodyPart : snake) {
            if (location.equals(bodyPart)) {
                return true;
            }
        }
        return false;
    }
//</editor-fold>

    /**
     * @return the noMove
     */
    public Direction getNoMove() {
        return noMove;
    }

    /**
     * @param noMove the noMove to set
     */
    public void setNoMove(Direction noMove) {
        this.noMove = noMove;
    }

}
