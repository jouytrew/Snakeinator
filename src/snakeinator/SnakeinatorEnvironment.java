/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeinator;

import audio.AudioPlayer;
import environment.Environment;
import environment.GraphicsPalette;
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
class SnakeinatorEnvironment extends Environment implements GridDrawData, SnakeLocationValidatorIntf {

    public SnakeinatorEnvironment() {
    }

//<editor-fold defaultstate="collapsed" desc="initializeEnvironment">
    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/background.jpg").getScaledInstance(1366, 768, Image.SCALE_SMOOTH));
        grid = new Grid(40, 25, 25, 25, new Point(50, 50), Color.RED);
        scores = new ArrayList<>();
        safeScores = new ArrayList<>();
        safeScores = getSafeScore();

        /**
         * First Snake
         */
        snake = new Snake(Direction.RIGHT, this, this);
        snake.setColorCode(255, 255, 255);

        ArrayList<Point> body = new ArrayList<>();
        body.add(new Point(2, 2));
        snake.setSnake(body);

        snake.setGrowthCounter(3);

        /**
         * End of First Snake Snake 2 below
         */
        snake2 = new Snake(Direction.RIGHT, this, this);
        snake2.setColorCode(0, 255, 0);

        ArrayList<Point> body2 = new ArrayList<>();
        body2.add(new Point(2, 10));
        snake2.setSnake(body2);

        snake2.setGrowthCounter(3);

        gridObjects = new ArrayList<>();
        gridObjects.add(new GridObject(GridObjectType.APPLE, randomGridLocation()));
        gridObjects.add(new GridObject(GridObjectType.APPLE, randomGridLocation()));
        gridObjects.add(new GridObject(GridObjectType.POISON_BOTTLE, randomGridLocation()));
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
        if (snake2 != null && (moveDelayCounter2 >= moveDelayLimit)) {
            snake2.move();
            moveDelayCounter2 = 0;
        } else {
            moveDelayCounter2++;
        }
        for (Score score : getSafeScore()) {
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
            snake2.togglePaused();
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
        if (e.getKeyCode() == KeyEvent.VK_LEFT && (snake2.getDirection() != Direction.RIGHT)) {
            snake2.setDirection(Direction.LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && (snake2.getDirection() != Direction.LEFT)) {
            snake2.setDirection(Direction.RIGHT);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && (snake2.getDirection() != Direction.UP)) {
            snake2.setDirection(Direction.DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && (snake2.getDirection() != Direction.DOWN)) {
            snake2.setDirection(Direction.UP);
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
            snake2.setGrowthCounter(1);
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
            graphics.setColor(new Color(snake.getRed(), snake.getGreen(), snake.getBlue()));
            graphics.drawString("Snake 1: " + snake.getScore(), 5, 12);
        }
        if (snake2 != null) {
            snake2.draw(graphics);
            graphics.setFont(new Font("Courier New", Font.PLAIN, 12));
            graphics.setColor(new Color(snake2.getRed(), snake2.getGreen(), snake2.getBlue()));
            graphics.drawString("Snake 2: " + snake2.getScore(), 5, 24);
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
            }
        }
        for (Score score : getSafeScore()) {
            score.draw(graphics);
        }
        for (Score score : getSafeScore()) {
            if (score.getTimeLeft() <= 0) {
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

//<editor-fold defaultstate="collapsed" desc="SnakeLocationValidatorIntf">
    @Override
    public SnakeAndPoint validateLocation(SnakeAndPoint data) {
        data.getSnake().checkSelfHit();
        
        if (data.getPoint().x < 0) {
            data.getPoint().x = grid.getColumns() - 1;
        }
        if (data.getPoint().x > grid.getColumns() - 1) {
            data.getPoint().x = 0;
        }
        if (data.getPoint().y < 0) {
            data.getPoint().y = grid.getRows() - 1;
        }
        if (data.getPoint().y > grid.getRows() - 1) {
            data.getPoint().y = 0;
        }
        /**
         * check if the snake hit a GridObject --Apple --Poison Look at all the
         * locations stored in the gridObject ArrayList for each, compare to
         * head loc.
         */
        for (GridObject object : gridObjects) {
            if (object.getLocation().equals(data.getPoint()) == true) {
                System.out.println("HIT = " + object.getType());
                if (object.getType() == GridObjectType.APPLE) {
                    data.getSnake().setScore(data.getSnake().getScore() + 100);
                    scores.add(new Score(object.getLocation(), 100));
//                    gridObjects.remove(object);
                    object.setLocation(randomDeconflictedGridLocation());
                    data.getSnake().grow(1);
                }
                if (object.getType() == GridObjectType.POISON_BOTTLE) {
                    data.getSnake().setScore(data.getSnake().getScore() - 200);
                    scores.add(new Score(object.getLocation(), -200));
                    object.setLocation(randomDeconflictedGridLocation());
                    AudioPlayer.play("/resources/grenade.wav");
                }
            }
        }
        return data;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Fields">
    private Grid grid;
    private Snake snake;
    private Boolean drawGrid = true;
    private Snake snake2;

    public final int SLOW_SPEED = 7;
    public final int MEDIUM_SPEED = 5;
    public final int HIGH_SPEED = 3;

    int moveDelayLimit = SLOW_SPEED;
    int moveDelayCounter = 0;
    int moveDelayCounter2 = 0;

    private ArrayList<GridObject> gridObjects;
    private ArrayList<Score> scores;
    private ArrayList<Score> safeScores;
//</editor-fold>

    public Point randomGridLocation() {
        return new Point((int) (Math.random() * grid.getColumns()), (int) (Math.random() * grid.getRows()));
    }

    public Point randomDeconflictedGridLocation() {
        Point location = randomGridLocation();
        return (snake.contains(location) || snake2.contains(location) || checkLocation(location)) ? randomDeconflictedGridLocation() : location;
    }
    
    public boolean checkLocation(Point location) {
        for (GridObject gridObject : gridObjects) {
            if (location.equals(gridObject.getLocation())) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Score> getSafeScore() {
        ArrayList<Score> safeScore = new ArrayList<>();
        for (Score score : scores){
            safeScore.add(score);
        }
        return safeScore;
    }

}
