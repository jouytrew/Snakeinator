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

    //<editor-fold desc="Constructor">
    public Snake(Point startLoc, Direction direction, Color color, GridDrawData drawData, SnakeLocationValidatorIntf snakeLocationValidator) {
        this.direction = direction;
        this.drawData = drawData;
        this.snakeLocationValidator = snakeLocationValidator;
        this.color = color;

        snake.add(new Point(startLoc));
        setGrowthCounter(3);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private ArrayList<Point> snake = new ArrayList<>();
    private Direction direction;
    private GridDrawData drawData;
    private boolean gamePlaying = true;
    private SnakeLocationValidatorIntf snakeLocationValidator;
    private Direction noMove = null;

    protected Color color;
    private int growthCounter;

    private int score;

    static public final int HEAD_POSITION = 0;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Graphics Draw">

    /**
     * Draw function called in the environment
     * @param graphics Java graphics used to draw.
     */
    public void draw(Graphics graphics) {

        for (Point bodySegmentLocation : getSafeSnake()) {
            Point topLeft = drawData.getCellSystemCoordinate(bodySegmentLocation);
            graphics.setColor(color);
            graphics.fillRect(topLeft.x, topLeft.y, drawData.getCellWidth(), drawData.getCellHeight());
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Movements">

    /**
     * Moves the snake! The snake is moved by adding a new point in the direction the snake is travelling.
     * If the snake needs to grow (growthCounter > 0), such as when it eats an apple, then the tail is not deleted.
     * Otherwise, the tail is removed. This way we can move the snake by only adding and subtracting one point,
     * and not moving all the individual body parts.
     */
    public void move() {
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

    //<editor-fold defaultstate="collapsed" desc="Setters/Getters">
    public Boolean getGamePlaying() { return gamePlaying; }

    public void setGamePlaying(Boolean gamePlaying) {
        this.gamePlaying = gamePlaying;
    }

    public void setRGB(int red, int green, int blue) { color = new Color(red, green, blue); }

    public int getGrowthCounter() {
        return growthCounter;
    }

    public void setGrowthCounter(int growthCounter) {
        this.growthCounter = growthCounter;
    }

    public void setSnakeLocationValidator(SnakeLocationValidatorIntf snakeLocationValidator) { this.snakeLocationValidator = snakeLocationValidator; }

    public Color getColor() {
        return color;
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

    /**
     * @return the direction that the snake cannot move to
     */
    public Direction getNoMove() {
        return noMove;
    }

    private void setNoMove(Direction direction) {
        noMove = direction;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * This method currently returns the snake without the head
     * @return the body without the head
     */
    public ArrayList<Point> getBody() {
        ArrayList<Point> body = new ArrayList<>();
        for (int i = 1; i < snake.size(); i++) {
            body.add(snake.get(i));
        }
        return body;
    }

    public void checkSelfHit() {
        int i = 0;
        for (Point bodyPart : getBody()) {
            i++;
            if (getHead().equals(bodyPart)) {
                //remove everything from position i onwards...?!?!?!
                for (int j = snake.size() - 1; j >= i; j--) {
                    snake.remove(j);
                    setScore(getScore() - 25);
                }
                break;
            }
        }
    }

    public SnakeLocationValidatorIntf getSnakeLocationValidator() {
        return snakeLocationValidator;
    }

    /**
     * Increment the snake's score by 'increment'
     * @param increment value to increment the Snake's internal score by
     */
    public void incrementScore(int increment) { score += increment; }

    /**
     * grow the snake by 'length'
     * @param length the number of extra segments the snake needs to grow by
     */
    public void grow(int length) {
        growthCounter += length;
    }

    private ArrayList<Point> getSafeSnake() {
        ArrayList<Point> safeBody = new ArrayList<>();
        safeBody.addAll(getSnake());
        return safeBody;
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

}
