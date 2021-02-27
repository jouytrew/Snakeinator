/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeinator;

import static environment.Physics.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author Benjamin Wong
 */
public class ChainSnake extends Snake {
    
    public ChainSnake(Direction direction, GridDrawData drawData, SnakeLocationValidatorIntf snakeLocationValidator) {
        super(direction, drawData, snakeLocationValidator);
    }
    
    @Override
    public void draw(Graphics graphics) {
        ArrayList<Point> safeBody = this.getSafeSnake();
        Point cellSysLoc, cellCoM, previousCellSysLoc, previousCellCoM;
        int cellWidth = getDrawData().getCellWidth();
        int cellHeight = getDrawData().getCellHeight();
        //intialize the "previous" to the head of the snake        
        previousCellSysLoc = getDrawData().getCellSystemCoordinate(getHead());
        previousCellCoM = getCenterOfMass(previousCellSysLoc.x, previousCellSysLoc.y, cellWidth, cellHeight);

        for (int i = Snake.HEAD_POSITION + 1; i < safeBody.size(); i++) {
            cellSysLoc = getDrawData().getCellSystemCoordinate(safeBody.get(i));
            cellCoM = getCenterOfMass(cellSysLoc.x, cellSysLoc.y, cellWidth, cellHeight);

            // For all nodes (after the HEAD (the  first) draw a connector from 
            // the center of the current cell to the center of previous cell
            graphics.setColor(new Color(getRed(), getGreen(), getBlue(), 100));
            fillBar(graphics, cellCoM, previousCellCoM, cellWidth / 2);

            previousCellCoM = cellCoM;
        }
        for (int i = 0; i < safeBody.size(); i++) {
            cellSysLoc = getDrawData().getCellSystemCoordinate(safeBody.get(i));

            graphics.setColor(getColor());
            graphics.fillOval(cellSysLoc.x + (cellWidth / 4), cellSysLoc.y + (cellHeight / 4), cellWidth / 2, cellHeight / 2);
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="fillBar">
    /**
     * TODO: please write docs for this
     * @param graphics
     * @param cellCoM
     * @param previousCellCoM
     * @param size
     */
    private void fillBar(Graphics graphics, Point cellCoM, Point previousCellCoM, int size) {
        graphics.setColor(getColor());
        if (abs(cellCoM.x - previousCellCoM.x) == getDrawData().getCellWidth()){
            int[] xBar = new int[4];
            xBar[0] = cellCoM.x;
            xBar[1] = xBar[0];
            xBar[2] = previousCellCoM.x;
            xBar[3] = xBar[2];
            int[] yBar = new int[4];
            yBar[0] = cellCoM.y - size/2;
            yBar[1] = cellCoM.y + size/2;
            yBar[2] = yBar[1];
            yBar[3] = yBar[0];
            graphics.fillPolygon(xBar, yBar, 4);
        } else if ((cellCoM.x - previousCellCoM.x) > getDrawData().getCellWidth()) {
            int[] xBar1 = new int[4];
            xBar1[0] = cellCoM.x;
            xBar1[1] = cellCoM.x + size;
            xBar1[2] = xBar1[1];
            xBar1[3] = xBar1[0];
            int[] yBar1 = new int[4];
            yBar1[0] = cellCoM.y + size/2;
            yBar1[1] = yBar1[0];
            yBar1[2] = cellCoM.y - size/2;
            yBar1[3] = yBar1[2];
            graphics.fillPolygon(xBar1, yBar1, 4);
            int[] xBar2 = new int[4];
            xBar2[0] = previousCellCoM.x;
            xBar2[1] = previousCellCoM.x - size;
            xBar2[2] = xBar2[1];
            xBar2[3] = xBar2[0];
            int[] yBar2 = new int[4];
            yBar2[0] = previousCellCoM.y + size/2;
            yBar2[1] = yBar2[0];
            yBar2[2] = previousCellCoM.y - size/2;
            yBar2[3] = yBar2[2];
            graphics.fillPolygon(xBar2, yBar2, 4);
        } else if ((cellCoM.x - previousCellCoM.x) < 0) {
            int[] xBar1 = new int[4];
            xBar1[0] = cellCoM.x;
            xBar1[1] = cellCoM.x - size;
            xBar1[2] = xBar1[1];
            xBar1[3] = xBar1[0];
            int[] yBar1 = new int[4];
            yBar1[0] = cellCoM.y + size/2;
            yBar1[1] = yBar1[0];
            yBar1[2] = cellCoM.y - size/2;
            yBar1[3] = yBar1[2];
            graphics.fillPolygon(xBar1, yBar1, 4);
            int[] xBar2 = new int[4];
            xBar2[0] = previousCellCoM.x;
            xBar2[1] = previousCellCoM.x + size;
            xBar2[2] = xBar2[1];
            xBar2[3] = xBar2[0];
            int[] yBar2 = new int[4];
            yBar2[0] = previousCellCoM.y + size/2;
            yBar2[1] = yBar2[0];
            yBar2[2] = previousCellCoM.y - size/2;
            yBar2[3] = yBar2[2];
            graphics.fillPolygon(xBar2, yBar2, 4);
        }
        if (abs(cellCoM.y - previousCellCoM.y) == getDrawData().getCellHeight()){
            int[] xBar = new int[4];
            xBar[0] = cellCoM.x - size/2;
            xBar[1] = cellCoM.x + size/2;
            xBar[2] = xBar[1];
            xBar[3] = xBar[0];
            int[] yBar = new int[4];
            yBar[0] = cellCoM.y;
            yBar[1] = yBar[0];
            yBar[2] = previousCellCoM.y;
            yBar[3] = yBar[2];
            graphics.fillPolygon(xBar, yBar, 4);
        } else if ((cellCoM.y - previousCellCoM.y) > getDrawData().getCellHeight()) {
            int[] xBar1 = new int[4];
            xBar1[0] = cellCoM.x + size/2;
            xBar1[1] = xBar1[0];
            xBar1[2] = cellCoM.x - size/2;
            xBar1[3] = xBar1[2];
            int[] yBar1 = new int[4];
            yBar1[0] = cellCoM.y;
            yBar1[1] = cellCoM.y + size;
            yBar1[2] = yBar1[1];
            yBar1[3] = yBar1[0];
            graphics.fillPolygon(xBar1, yBar1, 4);
            int[] xBar2 = new int[4];
            xBar2[0] = previousCellCoM.x - size/2;
            xBar2[1] = xBar2[0];
            xBar2[2] = previousCellCoM.x + size/2;
            xBar2[3] = xBar2[2];
            int[] yBar2 = new int[4];
            yBar2[0] = previousCellCoM.y;
            yBar2[1] = previousCellCoM.y - size;
            yBar2[2] = yBar2[1];
            yBar2[3] = yBar2[0];
            graphics.fillPolygon(xBar2, yBar2, 4);
        } else if ((cellCoM.y - previousCellCoM.y) < 0) {
            int[] xBar1 = new int[4];
            xBar1[0] = cellCoM.x + size/2;
            xBar1[1] = xBar1[0];
            xBar1[2] = cellCoM.x - size/2;
            xBar1[3] = xBar1[2];
            int[] yBar1 = new int[4];
            yBar1[0] = cellCoM.y;
            yBar1[1] = cellCoM.y - size;
            yBar1[2] = yBar1[1];
            yBar1[3] = yBar1[0];
            graphics.fillPolygon(xBar1, yBar1, 4);
            int[] xBar2 = new int[4];
            xBar2[0] = previousCellCoM.x - size/2;
            xBar2[1] = xBar2[0];
            xBar2[2] = previousCellCoM.x + size/2;
            xBar2[3] = xBar2[2];
            int[] yBar2 = new int[4];
            yBar2[0] = previousCellCoM.y;
            yBar2[1] = previousCellCoM.y + size;
            yBar2[2] = yBar2[1];
            yBar2[3] = yBar2[0];
            graphics.fillPolygon(xBar2, yBar2, 4);
        }
    }
//</editor-fold>
    
    private ArrayList<Point> getSafeSnake() {
        ArrayList<Point> safeBody = new ArrayList<>();
        for (Point location : getSnake()) {
            safeBody.add(location);
        }
        return safeBody;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Direction direction = Direction.RIGHT;
    private Boolean gamePlaying = true;
    private SnakeLocationValidatorIntf snakeLocationValidator;
    private Boolean paused = false;

    private Color color;
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    static public final int HEAD_POSITION = 0;
//</editor-fold>
    
    @Override
    public void setRGB(int red, int green, int blue) {
        setColor(new Color(red, green, blue));
        this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
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
    
}
