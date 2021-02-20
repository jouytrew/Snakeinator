/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Point;

/**
 *
 * @author kevin.lawrence
 */
public class Utility {
    
    public static int random(int upperBound){
        return random(0, upperBound);
    }
    
    public static int random(int lowerBound, int upperBound){
        return (int) ((Math.random() * (upperBound - lowerBound)) + lowerBound);
    }
    
    public static Point random(Point upperBounds){
        return new Point(random(upperBounds.x), random(upperBounds.y));
    }
    
    public static Point random(Point lowerBounds, Point upperBounds){
        return new Point(random(lowerBounds.x, upperBounds.x), random(lowerBounds.y, upperBounds.y));
    }
    
}
