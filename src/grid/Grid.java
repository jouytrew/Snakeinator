/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grid;

import environment.PaintableIntf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kevinlawrence
 */
public class Grid implements PaintableIntf {

//  <editor-fold defaultstate="collapsed" desc="Constructors">
    public static final int DEFAULT_COLUMNS = 20;
    public static final int DEFAULT_ROWS = 10;
    public static final int DEFAULT_CELL_WIDTH = 20;
    public static final int DEFAULT_CELL_HEIGHT = 20;
    public static final Point DEFAULT_POSITION = new Point(10, 10);
    public static final Color DEFAULT_COLOR = Color.GRAY;

    {
        columns = DEFAULT_COLUMNS;
        rows = DEFAULT_ROWS;
        
        cellWidth = DEFAULT_CELL_WIDTH;
        cellHeight = DEFAULT_CELL_HEIGHT;
        
        position = DEFAULT_POSITION;
        color = DEFAULT_COLOR;
    
        showCellCoordinates = false;
        cellCoordinateColor = DEFAULT_COLOR;
        cellCoordinateFont = new Font("serif", Font.ITALIC, (DEFAULT_CELL_HEIGHT * 3 / 4));
    }

    public Grid() {
    }

    /**
     * @param columns the number of columns in the grid
     * @param rows the number of rows in the grid
     * @param cellWidth the width of each cell in the grid in pixels
     * @param cellHeight the height of each cell in the grid in pixels
     * @param position the system coordinate location of the top left corner of
     * the grid
     * @param color the color to draw the grid
     */
    public Grid(int columns, int rows, int cellWidth, int cellHeight, Point position, Color color) {
        this.columns = columns;
        this.rows = rows;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.position = position;
        this.color = color;
    }
//  </editor-fold>

//  <editor-fold defaultstate="collapsed" desc="PaintableIntf Interface">
    @Override
    public void paintComponent(Graphics graphics) {
        paint(graphics, position, columns, rows, cellWidth, cellHeight, color);

        if (showCellCoordinates) {
            drawCoordinates(graphics);
        }
    }
//  </editor-fold>

//       <editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * By Kevin Lawrence
     *
     * @param graphics the Graphics surface upon which to draw
     * @param position the system coordinate location of the top left corner of
     * the grid
     * @param columns the number of columns in the grid
     * @param rows the number of rows in the grid
     * @param cellWidth the width of each cell in the grid in pixels
     * @param cellHeight the height of each cell in the grid in pixels
     * @param color the color to draw the grid
     *
     * Draws a grid based on input parameters.
     */
    //TODO - refactor all paint references to draw
    public static void paint(Graphics graphics, Point position, int columns, int rows, int cellWidth, int cellHeight, Color color) {
        graphics.setColor(color);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                graphics.drawRect(position.x + (column * cellWidth),
                        position.y + (row * cellHeight),
                        cellWidth, cellHeight);
            }
        }
    }

    /**
     * Draws cell coordinates (row and column indeces) in the left column and
     * top row of the grid; usually used for design, testing and debug scenarios
     *
     * @param graphics the graphics canvas on which to draw
     */
    public void drawCoordinates(Graphics graphics) {
        graphics.setColor(cellCoordinateColor);
        graphics.setFont(cellCoordinateFont);

        for (int row = 0; row < rows; row++) {
            graphics.drawString(String.valueOf(row), position.x - cellWidth, position.y + cellCoordinateFont.getSize() + (row * cellHeight));
        }

        for (int column = 0; column < columns; column++) {
            graphics.drawString(String.valueOf(column), position.x + (column * cellWidth), position.y - (cellHeight / 2));
        }
    }

    /**
     * @return a random Point(x, y) within the row and column of the grid.
     */
    public Point getRandomGridLocation() {
        return new Point((int) (Math.random() * getColumns()), (int) (Math.random() * getRows()));
    }

    /**
     * @param occupiedLocations the list of locations that are considered to be
     * occupied within the grid, and are therefore not available for the method
     * return value.
     *
     * @return a random Point(x, y) within the row and column of the grid; this
     * method will make a best effort at providing a location that is
     * unoccupied, i.e. that does not intersect with the list of occupied
     * locations provided. If the grid is completely full (the set of potential
     * locations within the grid is a subset of the occupied locations) the
     * method will return a location with x = -1 and y = -1;
     */
    public Point getRandomUnoccupiedGridLocation(ArrayList<Point> occupiedLocations) {
        //generate a new random point in the grid
        Point point = getRandomGridLocation();

        int x = point.x;
        int y = point.y;

        //check the point, if the position is occupied, move it 
        //across the grid until you find an open point.
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                point.setLocation((x + row) % getColumns(), (y + column) % getRows());

                if (!occupiedLocations.contains(point)) {
                    return point;
                }
            }
        }
        return new Point(-1, -1);
    }

    /**
     * @param column the column index of the cell
     * @param row the row index of the cell
     * @return a Point(x, y) coordinate of the top left position of the cell
     * Note that this does not validate if the row and column input parameters
     * are valid (i.e. contained within the limits of the size of the grid).
     */
    public Point getCellSystemCoordinate(int column, int row) {
        int x = position.x + (column * cellWidth);
        int y = position.y + (row * cellHeight);

        return new Point(x, y);
    }

    /**
     * @param point cell location coordinate
     * @return a Point(x, y) graphics system coordinate of the top left position
     * of the cell. Note that this does not validate if the row and column input
     * parameters are valid (i.e. contained within the limits of the size of the
     * grid).
     */
    public Point getCellSystemCoordinate(Point point) {
        return getCellSystemCoordinate(point.x, point.y);
    }

    /**
     * By Kevin Lawrence
     *
     * @param systemCoordinateX the x coordinate, based on the graphics system
     * @param systemCoordinateY the y coordinate, based on the graphics system
     *
     * @return a Point(x, y) which is the decoded [x, y] coordinate of the top
     * left position of the cell that contains the input system coordinates Note
     * that this does not validate if the row and column input parameters are
     * valid (i.e. contained within the limits of the size of the grid).
     */
    public Point getCellLocationFromSystemCoordinate(int systemCoordinateX, int systemCoordinateY) {
        int cellCoordinateX = (systemCoordinateX - getPosition().x) / getCellWidth();
        int cellCoordinateY = (systemCoordinateY - getPosition().y) / getCellHeight();

        return new Point(cellCoordinateX, cellCoordinateY);
    }

    /**
     *
     * @param systemCoordinate the point to be assess, based on the graphics
     * system
     *
     * @return a Point(x, y) which is the decoded [x, y] coordinate of the top
     * left position of the cell that contains the input system coordinates Note
     * that this does not validate if the row and column input parameters are
     * valid (i.e. contained within the limits of the size of the grid).
     */
    public Point getCellLocationFromSystemCoordinate(Point systemCoordinate) {
        return getCellLocationFromSystemCoordinate(systemCoordinate.x, systemCoordinate.y);
    }
//   </editor-fold>

//  <editor-fold defaultstate="collapsed" desc="Properties">
    private int columns;
    private int rows;
    private int cellWidth;
    private int cellHeight;
    private Point position;
    private Color color;
    
    private boolean showCellCoordinates;
    private Color cellCoordinateColor;
    private Font cellCoordinateFont;
    
    /**
     * @return Dimension having width = cell width and height = cell height
     */
    public Dimension getGridSize() {
        return new Dimension(cellWidth * columns, cellHeight * rows);
    }

    /**
     * @return Dimension having width = cell width and height = cell height
     */
    public Point getCellSizePoint() {
        return new Point(cellWidth, cellHeight);
    }

    /**
     * @return Dimension having width = cell width and height = cell height
     */
    public Dimension getCellSize() {
        return new Dimension(cellWidth, cellHeight);
    }

    /**
     * @return the columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * @return the cellWidth
     */
    public int getCellWidth() {
        return cellWidth;
    }

    /**
     * @param cellWidth the cellWidth to set
     */
    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    /**
     * @return the cellHeight
     */
    public int getCellHeight() {
        return cellHeight;
    }

    /**
     * @param cellHeight the cellHeight to set
     */
    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
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
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Toggle (reverse) the value of showCellCoordinates
     */
    public void toggleShowCellCoordinates() {
        showCellCoordinates = !showCellCoordinates;
    }

    /**
     * @param showCellCoordinates the showCellCoordinates to set
     */
    public void setShowCellCoordinates(boolean showCellCoordinates) {
        this.showCellCoordinates = showCellCoordinates;
    }

    /**
     * @return the showCellCoordinates
     */
    public boolean isShowCellCoordinates() {
        return showCellCoordinates;
    }

    /**
     * @return the cellCoordinateColor
     */
    public Color getCellCoordinateColor() {
        return cellCoordinateColor;
    }

    /**
     * @param cellCoordinateColor the cellCoordinateColor to set
     */
    public void setCellCoordinateColor(Color cellCoordinateColor) {
        this.cellCoordinateColor = cellCoordinateColor;
    }

    /**
     * @return the cellCoordinateFont
     */
    public Font getCellCoordinateFont() {
        return cellCoordinateFont;
    }

    /**
     * @param cellCoordinateFont the cellCoordinateFont to set
     */
    public void setCellCoordinateFont(Font cellCoordinateFont) {
        this.cellCoordinateFont = cellCoordinateFont;
    }
//  </editor-fold>
    
}
