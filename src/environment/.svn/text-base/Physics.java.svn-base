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
    
    public static Point getCenterOfMass(Rectangle rectangle){
        return new Point(rectangle.x + (rectangle.width / 2), rectangle.y + (rectangle.height / 2));
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
        return (relativeLocation >= 0)? Direction.EAST : Direction.WEST;
    }

    public static Direction getNorthSouth(int relativeLocation){
        return (relativeLocation >= 0)? Direction.SOUTH : Direction.NORTH;
    }

    
}
