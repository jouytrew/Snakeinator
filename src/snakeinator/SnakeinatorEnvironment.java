/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeinator;

import environment.Environment;
import environment.LocationValidatorIntf;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
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

    public final int SLOW_SPEED = 7;
    public final int MEDIUM_SPEED = 5;
    public final int HIGH_SPEED = 3;

    int moveDelayLimit = MEDIUM_SPEED;
    int moveDelayCounter = 0;
    int moveDelayCounter2 = 0;

    public SnakeinatorEnvironment() {
    }

//<editor-fold defaultstate="collapsed" desc="initializeEnvironment">
    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/background.jpg").getScaledInstance(1366, 768, Image.SCALE_SMOOTH));
        grid = new Grid(40, 25, 20, 20, new Point(50, 50), Color.RED);
        /**
         * First Snake
         */
        snake = new Snake();
        snake.setColorCode(255, 0, 0);
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
        snake2 = new Snake();
        snake2.setColorCode(0, 255, 0);
        snake2.setDirection(Direction.RIGHT);
        snake2.setDrawData(this);
        snake2.setLocationValidator(this);

        ArrayList<Point> body2 = new ArrayList<>();
        body2.add(new Point(2, 10));
        body2.add(new Point(3, 10));
        body2.add(new Point(4, 10));
        body2.add(new Point(5, 10));
        snake2.setBody(body2);
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
        }
        if (snake2 != null) {
            snake2.draw(graphics);
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
        }
        if (point.x > grid.getColumns() - 1) {
            point.x = 0;
        }
        if (point.y < 0) {
            point.y = grid.getRows() - 1;
        }
        if (point.y > grid.getRows()) {
            point.y = 0;
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

}
