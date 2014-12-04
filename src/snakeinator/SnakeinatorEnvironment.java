/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeinator;

import audio.AudioPlayer;
import environment.Environment;
import environment.GraphicsPalette;
import environment.LocationValidatorIntf;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Benjamin Wong
 */
class SnakeinatorEnvironment extends Environment implements GridDrawData, LocationValidatorIntf {

    private Grid grid;
    private Snake snake;
    private Boolean drawGrid = true;
    private Snake snake2;
    private int score = 0;
    private int score2 = 0;

    public final int SLOW_SPEED = 7;
    public final int MEDIUM_SPEED = 5;
    public final int HIGH_SPEED = 3;

    int moveDelayLimit = SLOW_SPEED;
    int moveDelayCounter = 0;
    int moveDelayCounter2 = 0;

    private ArrayList<GridObject> gridObjects;
    private ArrayList<Score> scores;

    public SnakeinatorEnvironment() {
    }

    public Point randomPoint() {
        Point p = new Point((int) (Math.random() * grid.getColumns()), (int) (Math.random() * grid.getRows()));
//        for (Point body : snake.getBody()) {
//            for (Point body2 : snake.getBody()) {
//                if (p == body || p == body2){
//                    p = new Point((int) (Math.random() * grid.getColumns()), (int) (Math.random() * grid.getRows()));
//                }
//            }
//        }
        return p;
    }

    
    
//<editor-fold defaultstate="collapsed" desc="initializeEnvironment">
    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/background.jpg").getScaledInstance(1366, 768, Image.SCALE_SMOOTH));
        grid = new Grid(40, 25, 25, 25, new Point(50, 50), Color.RED);
        scores = new ArrayList<Score>();
        gridObjects = new ArrayList<>();
        gridObjects.add(new GridObject(GridObjectType.APPLE, randomPoint()));
        gridObjects.add(new GridObject(GridObjectType.APPLE, randomPoint()));
        gridObjects.add(new GridObject(GridObjectType.POISON_BOTTLE, randomPoint()));
        /**
         * First Snake
         */
        snake = new Snake();
        snake.setColorCode(255, 0, 0, 255);
        snake.setDirection(Direction.RIGHT);
        snake.setDrawData(this);
        snake.setLocationValidator(this);

        ArrayList<Point> body = new ArrayList<>();

        body.add(new Point(3, 1));
        body.add(new Point(3, 2));
        body.add(new Point(2, 2));
        body.add(new Point(2, 3));
        snake.setBody(body);
        /**
         * End of First Snake Snake 2 below
         */
        setSnake2(new Snake());
        getSnake2().setColorCode(0, 255, 0, 255);
        getSnake2().setDirection(Direction.RIGHT);
        getSnake2().setDrawData(this);
        getSnake2().setLocationValidator(this);

        ArrayList<Point> body2 = new ArrayList<>();
        body2.add(new Point(2, 10));
        body2.add(new Point(3, 10));
        body2.add(new Point(4, 10));
        body2.add(new Point(5, 10));
        getSnake2().setBody(body2);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="timerTaskHandler">
    @Override
    public void timerTaskHandler() {
        if (snake != null && (moveDelayCounter2 >= moveDelayLimit)) {
            snake.move();
            moveDelayCounter = 0;
        } else {
            moveDelayCounter++;
        }
        if (getSnake2() != null && (moveDelayCounter2 >= moveDelayLimit)) {
            getSnake2().move();
            moveDelayCounter2 = 0;
        } else {
            moveDelayCounter2++;
        }
        for (Score score : scores) {
            score.time();
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="keyPressedHandler">
    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_QUOTE) {
            grid.setShowCellCoordinates(!grid.getShowCellCoordinates());
        }
        /**
         * Second snake needs second set of controls Use WASD keys to control
         * snake Use arrow keys to control snake2 O Key turns the grid off P Key
         * pauses snakes `/~ Key toggles grid coordinate display
         */
        if (e.getKeyCode() == KeyEvent.VK_P) {
            snake.togglePaused();
            getSnake2().togglePaused();
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            toggleDrawGrid();
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            AudioPlayer.play("/resources/grenade.wav");
        }
        if (e.getKeyCode() == KeyEvent.VK_A && (snake.getDirection() != Direction.RIGHT)) {
            snake.setDirection(Direction.LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_D && (snake.getDirection() != Direction.LEFT)) {
            snake.setDirection(Direction.RIGHT);
        }
        if (e.getKeyCode() == KeyEvent.VK_S && (snake.getDirection() != Direction.UP)) {
            snake.setDirection(Direction.DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_W && (snake.getDirection() != Direction.DOWN)) {
            snake.setDirection(Direction.UP);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && (getSnake2().getDirection() != Direction.RIGHT)) {
            getSnake2().setDirection(Direction.LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && (getSnake2().getDirection() != Direction.LEFT)) {
            getSnake2().setDirection(Direction.RIGHT);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && (getSnake2().getDirection() != Direction.UP)) {
            getSnake2().setDirection(Direction.DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && (getSnake2().getDirection() != Direction.DOWN)) {
            getSnake2().setDirection(Direction.UP);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="keyReleasedHandler">
    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_G) {
            snake.setGrowthCounter(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_H) {
            getSnake2().setGrowthCounter(1);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="environmentMouseClicked">
    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="PaintEnvironment">
    @Override
    public void paintEnvironment(Graphics graphics) {
        /**
         * Graphics 2D used to anti-alias images/shapes
         */
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (drawGrid) {
            if (grid != null) {
                grid.paintComponent(graphics);
            }
        }
        if (snake != null) {
            snake.draw(graphics);
            graphics.setFont(new Font("Courier New", Font.PLAIN, 12));
            graphics.drawString("Snake 1: " + getScore(), 5, 12);
        }
        if (getSnake2() != null) {
            getSnake2().draw(graphics);
            graphics.setFont(new Font("Courier New", Font.PLAIN, 12));
            graphics.drawString("Snake 2: " + score2, 5, 24);
        }
        if (gridObjects != null) {
            for (GridObject gridObject : gridObjects) {
                if (gridObject.getType() == GridObjectType.APPLE) {
                    GraphicsPalette.drawApple(graphics, grid.getCellSystemCoordinate(gridObject.getLocation()),
                            grid.getCellSize(), Color.RED);
                }
                if (gridObject.getType() == GridObjectType.POISON_BOTTLE) {
                    GraphicsPalette.drawPoisonBottle(graphics, grid.getCellSystemCoordinate(gridObject.getLocation()),
                            grid.getCellSize(), Color.yellow);
                }
                if (gridObject.getType() == GridObjectType.BIOHAZARD) {
                    snake.drawBiohazard(graphics, grid.getCellSystemCoordinate(gridObject.getLocation()),
                            grid.getCellSize());
                }
            }
        }
        for (Score score : scores) {
            score.draw(graphics);
        }
        for (Score score : scores) {
            if (score.getTimeLeft() <= 0){
                scores.remove(score);
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="GridDrawData Interface">
    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }

    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }

    @Override
    public Point getCellSystemCoordinate(Point cellCoordinate) {
        return grid.getCellSystemCoordinate(cellCoordinate);
    }

    @Override
    public int getColumns() {
        return grid.getColumns();
    }

    @Override
    public int getRows() {
        return grid.getRows();
    }

    @Override
    public Point getRandomGridLocation() {
        return grid.getRandomGridLocation();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="LocationValidatorIntf">
    @Override
    public Point validateLocation(Point point) {
        if (point.x < 0) {
            point.x = grid.getColumns() - 1;
        } if (point.x > grid.getColumns() - 1) {
            point.x = 0;
        } if (point.y < 0) {
            point.y = grid.getRows() - 1;
        } if (point.y > grid.getRows() - 1) {
            point.y = 0;
        }
        /**
         * check if the snake hit a GridObject
         *  --Apple
         *  --Poison
         * Look at all the locations stored in the gridObject ArrayList for each,
         * compare to head loc.
         */
        for (GridObject object : gridObjects){
            if (object.getLocation().equals(point) == true) {
                System.out.println("HIT = " + object.getType());
                if (object.getType() == GridObjectType.APPLE) {
                    score += 100;
                    scores.add(new Score(object.getLocation(), 100));                    
                    object.setLocation(randomPoint());
                    snake.setGrowthCounter(1);
                }
                if (object.getType() == GridObjectType.POISON_BOTTLE) {
                    score -= 200;
                    scores.add(new Score(object.getLocation(), -200));
                    object.setLocation(randomPoint());
                    
                }
            }
        }
        
        
        return point;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="On Screen GridMethods">
    public Boolean getDrawGrid() {
        return drawGrid;
    }

    public void setDrawGrid(Boolean gridDrawn) {
        this.drawGrid = gridDrawn;
    }

    public void toggleDrawGrid() {
        drawGrid = !drawGrid;
    }
//</editor-fold>

    /**
     * @return the snake2
     */
    public Snake getSnake2() {
        return snake2;
    }

    /**
     * @param snake2 the snake2 to set
     */
    public void setSnake2(Snake snake2) {
        this.snake2 = snake2;
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
