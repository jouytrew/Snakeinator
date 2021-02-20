/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author kevinlawrence
 */
public class Physics {
    
    /**
     *
     * @param x the x (horizontal) position of the top left corner
     * @param y the y (vertical) position of the top left corner
     * @param width the width of the object
     * @param height the height of the object
     * @return a Point that is the center ("center of mass") for the object 
     */
    public static Point getCenterOfMass(int x, int y, int width, int height){
        return new Point(x + (width / 2), y + (height / 2));
    }

    /**
     * @param rectangle
     * @return a Point that is the center ("center of mass") for the rectangle 
     */
    public static Point getCenterOfMass(Rectangle rectangle){
        return getCenterOfMass(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public static Point getRelativeLocationVector(Point point, Point reference){
        return new Point(reference.x - point.x, reference.y - point.y);
    }

    public static Direction[] getRelativeDirection(Point point, Point reference){
        return getRelativeDirection(getRelativeLocationVector(point, reference));
    }
    
    public static Direction[] getRelativeDirection(Point relativeLocationVector){
        Direction[] direction = {null, null};
        
        if (Math.abs(relativeLocationVector.x) - Math.abs(relativeLocationVector.y) >= 0){
            direction[0] = getEastWest(relativeLocationVector.x);
            direction[1] = getNorthSouth(relativeLocationVector.y);
        } else {
            direction[0] = getNorthSouth(relativeLocationVector.y);
            direction[1] = getEastWest(relativeLocationVector.x);
        }
        
        return direction;
    }
    
    public static Direction getEastWest(int relativeLocation){
        return (relativeLocation >= 0)? Direction.RIGHT : Direction.LEFT;
    }

    public static Direction getNorthSouth(int relativeLocation){
        return (relativeLocation >= 0)? Direction.DOWN : Direction.UP;
    }

    
}
