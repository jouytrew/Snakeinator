/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeinator;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Benjamin Wong
 */
public class GridObjects {
    
    {
        objects = new ArrayList<>();
    }
    
    private ArrayList<GridObject> objects;
//    private ArrayList<GridObject> objects = new ArrayList<GridObject>();

    /**
     * @return the objectLocations
     */
    public ArrayList<Point> getObjectLocations() {
        ArrayList<Point> points = new ArrayList<>();
        for (GridObject object : objects) {
            points.add(object.getLocation());
        }
        return points;
    }
    
    /**
     * @return the objects
     */
    public ArrayList<GridObject> getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(ArrayList<GridObject> objects) {
        this.objects = objects;
    }
    
    public void clear(){
        this.objects.clear();
    }
    
}
