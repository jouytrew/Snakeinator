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
public class GridObject {
    
    public GridObject(GridObjectType type, Point location){
        this.type = type;
        this.location = location;
    }
    
    public GridObject(GridObjectType type, int x, int y){
        this.type = type;
        this.location = new Point(x, y);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    private GridObjectType type;
    private Point location;

    public GridObjectType getType() {
        return type;
    }
    
    public void setType(GridObjectType type) {
        this.type = type;
    }
    
    public Point getLocation() {
        return location;
    }
    
    public void setLocation(Point location) {
        this.location = location;
    }
//</editor-fold>
}
