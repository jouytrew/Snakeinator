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

        /**
         * First Snake
         */
//        snake = new Snake(Direction.RIGHT, this, this);
        snake = new ChainSnake(Direction.RIGHT, this, this);
        snake.setRGB(255, 255, 255);

        ArrayList<Point> body = new ArrayList<>();
        body.add(new Point(2, 2));
        snake.setSnake(body);
        snake.setGrowthCounter(3);

        /**
         * End of First Snake Snake 2 below
         */
//        snake2 = new Snake(Direction.RIGHT, this, this);
        snake2 = new ChainSnake(Direction.RIGHT, this, this);
        snake2.setRGB(0, 255, 0);

        ArrayList<Point> body2 = new ArrayList<>();
        body2.add(new Point(2, 10));
        snake2.setSnake(body2);
        snake2.setGrowthCounter(3);

        gridObjects = new GridObjects();

        addGridObject(GridObjectType.APPLE, 15);
        addGridObject(GridObjectType.POISON_BOTTLE, 2);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="timerTaskHandler">
    @Override
    public void timerTaskHandler() {
        if (!paused) {
            if(moveDelayCounter % moveDelayLimit == 0) {
                if (snake != null) {
                    snake.move();
                }
                if (snake2 != null) {
                    snake2.move();
                }
            }
            moveDelayCounter++;
            for (Score score : scores) {
                score.time();
            }
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="keyPressedHandler">
    @Override
    public void keyPressedHandler(KeyEvent e) {
        /**
         * Second snake needs second set of controls Use WASD keys to control
         * snake Use arrow keys to control snake2 O Key turns the grid off P Key
         * pauses snakes `/~ Key toggles grid coordinate display
         */
        if (e.getKeyCode() == KeyEvent.VK_P) {
            this.togglePaused();
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            toggleDrawGrid();
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            AudioPlayer.play("/resources/grenade.wav");
        }
        if (!paused) {
            if (e.getKeyCode() == KeyEvent.VK_A && (snake.getNoMove() != Direction.LEFT)) {
                snake.setDirection(Direction.LEFT);
            }
            if (e.getKeyCode() == KeyEvent.VK_D && (snake.getNoMove() != Direction.RIGHT)) {
                snake.setDirection(Direction.RIGHT);
            }
            if (e.getKeyCode() == KeyEvent.VK_S && (snake.getNoMove() != Direction.DOWN)) {
                snake.setDirection(Direction.DOWN);
            }
            if (e.getKeyCode() == KeyEvent.VK_W && (snake.getNoMove() != Direction.UP)) {
                snake.setDirection(Direction.UP);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT && (snake2.getNoMove() != Direction.LEFT)) {
                snake2.setDirection(Direction.LEFT);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && (snake2.getNoMove() != Direction.RIGHT)) {
                snake2.setDirection(Direction.RIGHT);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN && (snake2.getNoMove() != Direction.DOWN)) {
                snake2.setDirection(Direction.DOWN);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP && (snake2.getNoMove() != Direction.UP)) {
                snake2.setDirection(Direction.UP);
            }
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
        /**
         * Clear screen of objects test code if (e.getKeyCode() ==
         * KeyEvent.VK_C){ clearGridObjects(); }
         */
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
        //<editor-fold defaultstate="collapsed" desc="antiAlias">
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
//</editor-fold>
        switch (getGameState()) {
            //<editor-fold defaultstate="collapsed" desc="GameState : START">
            case START:

                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="GameState : PLAYING">
            case PLAYING:
                if (drawGrid) {
                    if (grid != null) {
                        grid.paintComponent(graphics);
                    }
                }
                for (Point clearAreaPoint : getSafeClearArea()){
                    Point topLeft = grid.getCellSystemCoordinate(clearAreaPoint);
                    graphics.setColor(new Color(255, 0, 0, 50));
                    graphics.fillRect(topLeft.x, topLeft.y, grid.getCellWidth(), grid.getCellHeight());
                }
                if (snake != null) {
                    snake.draw(graphics);
                    graphics.setFont(new Font("Courier New", Font.PLAIN, 12));
                    graphics.drawString("Snake 1: " + snake.getScore(), 5, 12);
                }
                if (snake2 != null) {
                    snake2.draw(graphics);
                    graphics.setFont(new Font("Courier New", Font.PLAIN, 12));
                    graphics.drawString("Snake 2: " + snake2.getScore(), 5, 24);
                }
                graphics.setFont(new Font("Courier New", Font.PLAIN, 12));
                graphics.setColor(new Color(255,255,255));
                graphics.drawString("Time: " + moveDelayCounter + " seconds", 5, 36);

                if (gridObjects != null) {
                    for (GridObject gridObject : gridObjects.getObjects()) {
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
                ArrayList<Score> scoresToRemove = new ArrayList<>();
                for (Score score : scores) { // TODO: get rid of safe scores methods?
                    score.draw(graphics);
                    if (score.getTimeLeft() <= 0) {
                        scoresToRemove.add(score);
                        // scores.remove(score);
                    }
                }
                for (Score score : scoresToRemove) {
                    scores.remove(score);
                }
                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="GameState : PAUSED">
            case PAUSED:

                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="GameState : GG">
            case GG:

                break;
//</editor-fold>
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
        createClearArea(data.getPoint(), data.getSnake().getDirection());
        /**
         * check if the snake hit a GridObject --Apple --Poison Look at all the
         * locations stored in the gridObject ArrayList for each, compare to
         * head loc.
         */
        for (GridObject object : gridObjects.getObjects()) {
            if (object.getLocation().equals(data.getPoint()) == true) {
                if (object.getType() == GridObjectType.APPLE) {
                    data.getSnake().incrementScore(100);
                    scores.add(new Score(object.getLocation(), 100));
                    object.setLocation(randomDeconflictedGridLocation());
                    data.getSnake().grow(1);
                } else if (object.getType() == GridObjectType.POISON_BOTTLE) {
                    data.getSnake().incrementScore(-200);
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
    private boolean drawGrid = true;
    public boolean paused = true;
    private Snake snake2;
    private GameState gameState = GameState.PLAYING;
    private ArrayList<Point> clearArea = new ArrayList<>();
    private boolean clearSafePoint = true;

    public final int SLOW_SPEED = 7;
    public final int MEDIUM_SPEED = 5;
    public final int HIGH_SPEED = 3;

    int moveDelayLimit = SLOW_SPEED;
    int moveDelayCounter = 0;
    int moveDelayCounter2 = 0;

    private GridObjects gridObjects;
    private ArrayList<Score> scores;
//</editor-fold>

    public Point randomGridLocation() {
        return new Point((int) (Math.random() * grid.getColumns()), (int) (Math.random() * grid.getRows()));
    }

    public Point randomDeconflictedGridLocation() {
        Point location = randomGridLocation();
        return (snake.contains(location) || snake2.contains(location) ||
                gridObjects.getObjectLocations().contains(location) || clearArea.contains(location)) ? randomDeconflictedGridLocation() : location;
    }

    public void addGridObject(GridObjectType objectType, int i) {
        for (int j = 0; j < i; j++) {
            gridObjects.getObjects().add(new GridObject(objectType, randomGridLocation()));
        }
    }

    public void clearGridObjects() {
        gridObjects.clear();
    }

    public void togglePaused() {
        paused = !paused;
    }

    private void createClearArea(Point head, Direction direction) {
        /** 
         *  Creates 5*3 box in the direction the snake is moving that objects cannot spawn in
         */
        if (doClearSafePoint()){
            clearArea.clear();
            setClearSafePoint(false);
        } else {
            setClearSafePoint(true);
        }
//        clearArea.clear();
        if (direction == Direction.UP){
            for (int i = 0; i <=4; i++){
                getClearArea().add(new Point((head.x - 1 + grid.getColumns()) % grid.getColumns(), (head.y - i + grid.getRows()) % grid.getRows()));
                getClearArea().add(new Point((head.x + grid.getColumns()) % grid.getColumns(), (head.y - i + grid.getRows()) % grid.getRows()));
                getClearArea().add(new Point((head.x + 1 + grid.getColumns()) % grid.getColumns(), (head.y - i + grid.getRows()) % grid.getRows()));
            }
        }
        if (direction == Direction.DOWN){
            for (int i = 0; i <= 4; i++){
                getClearArea().add(new Point((head.x - 1 + grid.getColumns()) % grid.getColumns(), (head.y + i + grid.getRows()) % grid.getRows()));
                getClearArea().add(new Point((head.x + grid.getColumns()) % grid.getColumns(), (head.y + i + grid.getRows()) % grid.getRows()));
                getClearArea().add(new Point((head.x + 1 + grid.getColumns()) % grid.getColumns(), (head.y + i + grid.getRows()) % grid.getRows()));
            }
        }
        if (direction == Direction.RIGHT){
            for (int i = 0; i <= 4; i++){
                getClearArea().add(new Point((head.x + i + grid.getColumns()) % grid.getColumns(), (head.y - 1 + grid.getRows()) % grid.getRows()));
                getClearArea().add(new Point((head.x + i + grid.getColumns()) % grid.getColumns(), (head.y + grid.getRows()) % grid.getRows()));
                getClearArea().add(new Point((head.x + i + grid.getColumns()) % grid.getColumns(), (head.y + 1 + grid.getRows()) % grid.getRows()));
            }
        }
        if (direction == Direction.LEFT){
            for (int i = 0; i <= 4; i++){
                getClearArea().add(new Point((head.x - i + grid.getColumns()) % grid.getColumns(), (head.y - 1 + grid.getRows()) % grid.getRows()));
                getClearArea().add(new Point((head.x - i + grid.getColumns()) % grid.getColumns(), (head.y + grid.getRows()) % grid.getRows()));
                getClearArea().add(new Point((head.x - i + grid.getColumns()) % grid.getColumns(), (head.y + 1 + grid.getRows()) % grid.getRows()));
            }
        }

    }

    /**
     * @return the gameState
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * @param gameState the gameState to set
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * @return the clearArea
     */
    public ArrayList<Point> getClearArea() {
        return clearArea;
    }

    /**
     * @param clearArea the clearArea to set
     */
    public void setClearArea(ArrayList<Point> clearArea) {
        this.clearArea = clearArea;
    }
    
    public ArrayList<Point> getSafeClearArea() {
        ArrayList<Point> safeClearArea = new ArrayList<>();
        for (Point clearAreaPoint : clearArea){
            safeClearArea.add(clearAreaPoint);
        }
        return safeClearArea;
    }

    /**
     * @return the clearSafePoint
     */
    public boolean doClearSafePoint() {
        return clearSafePoint;
    }

    /**
     * @param clearSafePoint the clearSafePoint to set
     */
    public void setClearSafePoint(boolean clearSafePoint) {
        this.clearSafePoint = clearSafePoint;
    }

}
