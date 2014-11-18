/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeinator;

import java.awt.Point;

/**
 *
 * @author Benjamin Wong
 */
public interface GridDrawData {
    public int getCellHeight();
    public int getCellWidth();
    public int getColumns();
    public int getRows();
    public Point getRandomGridLocation();
    
    public Point getCellSystemCoordinate(Point cellCoordinate);
}
