/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author kevin.lawrence
 */
public class InteriorRegionGrid extends Grid {

//<editor-fold defaultstate="collapsed" desc="Draw Methods">
    /**
     *
     * @param graphics
     */
    public void draw(Graphics graphics) {
        Point currentCellLocation = new Point();

        for (int row = 0; row < this.getRows(); row++) {
            for (int column = 0; column < this.getColumns(); column++) {
                currentCellLocation.setLocation(row, column);

                graphics.setColor(isInteriorCellLocation(currentCellLocation) ? interiorColor : boundaryColor);
                graphics.drawRect(getCellSystemCoordinate(currentCellLocation).x, getCellSystemCoordinate(currentCellLocation).y, getCellWidth(), getCellHeight());
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Cell Location Methods">
    /**
     * Determine if the cell is located outside the grids boundaries; this
     * method is particularly useful if the cell location is computed based on a
     * system coordinate, such as from the system coordinate from a mouse click.
     *
     * @param cellLocation the location of the cell within the context of the
     * grid coordinates (the x value is the column, and the y value is the row
     * @return true if the cell is in the outside "boundary" area of the grid
     */
    public boolean isOutsideGrid(Point cellLocation) {
        return ((cellLocation.x < 0)
                || (cellLocation.x >= this.getColumns())
                || (cellLocation.y < 0)
                || (cellLocation.y >= this.getRows()));
    }

    /**
     * Determine if the cell is in the in the "interior" portion of the grid
     *
     * @param cellLocation the location of the cell within the context of the
     * grid coordinates (the x value is the column, and the y value is the row
     * @return true if the cell is in the inside "boundary" area of the grid
     */
    public boolean isInteriorCellLocation(Point cellLocation) {
        return ((cellLocation.x >= leftOffset)
                && (cellLocation.x <= this.getColumns() - rightOffset)
                && (cellLocation.y >= topOffset)
                && (cellLocation.y <= this.getRows() - bottomOffset));
    }

    /**
     * Determine if the cell is in the in the "boundary" portion of the grid,
     * i.e. inside the boundaries of the grid, but not in the interior region.
     *
     * @param cellLocation the location of the cell within the context of the
     * grid coordinates (the x value is the column, and the y value is the row
     * @return true if the cell is in the inside "boundary" area of the grid
     */
    public boolean isBoundaryCellLocation(Point cellLocation) {
        return !(isInteriorCellLocation(cellLocation) || isOutsideGrid(cellLocation));
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Static Final Data">
    public static final int DEFAULT_TOP_OFFSET = 3;
    public static final int DEFAULT_LEFT_OFFSET = 3;
    public static final int DEFAULT_RIGHT_OFFSET = 3;
    public static final int DEFAULT_BOTTOM_OFFSET = 3;

    public static final Color DEFAULT_INTERIOR_COLOR = Color.WHITE;
    public static final Color DEFAULT_BOUNDARY_COLOR = Color.BLACK;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        setTopOffset(DEFAULT_TOP_OFFSET);
        setLeftOffset(DEFAULT_LEFT_OFFSET);
        setRightOffset(DEFAULT_RIGHT_OFFSET);
        setBottomOffset(DEFAULT_BOTTOM_OFFSET);

        setInteriorColor(DEFAULT_INTERIOR_COLOR);
        setBoundaryColor(DEFAULT_BOUNDARY_COLOR);
    }

    /**
     *
     */
    public InteriorRegionGrid() { }

    /**
     *
     * @param topOffset the number of columns that the interior grid will be
     * offset from the top of the grid
     * @param leftOffset the number of columns that the interior grid will be
     * offset from the left side of the grid
     * @param rightOffset the number of columns that the interior grid will be
     * offset from the right side of the grid
     * @param bottomOffset the number of columns that the interior grid will be
     * offset from the bottom of the grid
     * @param interiorColor the color of the cells in the interior grid
     * @param boundaryColor the color of the cells in "boundary" region outside
     * the interior grid
     */
    public InteriorRegionGrid(int topOffset, int leftOffset, int rightOffset, int bottomOffset, Color interiorColor, Color boundaryColor) {
        this.topOffset = topOffset;
        this.leftOffset = leftOffset;
        this.rightOffset = rightOffset;
        this.bottomOffset = bottomOffset;

        this.interiorColor = interiorColor;
        this.boundaryColor = boundaryColor;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private int topOffset;
    private int leftOffset;
    private int rightOffset;
    private int bottomOffset;

    private Color interiorColor;
    private Color boundaryColor;

    /**
     * @return the topOffset
     */
    public int getTopOffset() {
        return topOffset;
    }

    /**
     * @param topOffset the topOffset to set
     */
    public void setTopOffset(int topOffset) {
        this.topOffset = topOffset;
    }

    /**
     * @return the leftOffset
     */
    public int getLeftOffset() {
        return leftOffset;
    }

    /**
     * @param leftOffset the leftOffset to set
     */
    public void setLeftOffset(int leftOffset) {
        this.leftOffset = leftOffset;
    }

    /**
     * @return the rightOffset
     */
    public int getRightOffset() {
        return rightOffset;
    }

    /**
     * @param rightOffset the rightOffset to set
     */
    public void setRightOffset(int rightOffset) {
        this.rightOffset = rightOffset;
    }

    /**
     * @return the bottomOffset
     */
    public int getBottomOffset() {
        return bottomOffset;
    }

    /**
     * @param bottomOffset the bottomOffset to set
     */
    public void setBottomOffset(int bottomOffset) {
        this.bottomOffset = bottomOffset;
    }

    /**
     * @return the interiorColor
     */
    public Color getInteriorColor() {
        return interiorColor;
    }

    /**
     * @param interiorColor the interiorColor to set
     */
    public void setInteriorColor(Color interiorColor) {
        this.interiorColor = interiorColor;
    }

    /**
     * @return the boundaryColor
     */
    public Color getBoundaryColor() {
        return boundaryColor;
    }

    /**
     * @param boundaryColor the boundaryColor to set
     */
    public void setBoundaryColor(Color boundaryColor) {
        this.boundaryColor = boundaryColor;
    }
//</editor-fold>
}
