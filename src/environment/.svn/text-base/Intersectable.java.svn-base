/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author kevinlawrence
 */
public interface Intersectable {

    /**
     * @return the rectangle that defines the boundaries of the object
     */
    public Rectangle getObjectBoundary();

    /**
     * @return an assessment if the object intersects with the Intersectable
     * object provided.
     */
    public boolean intersects(Intersectable intersectable);

    /**
     * @param ArrayList<Intersectable> the collection of Intersectable objects
     * that are currently intersecting the object; the intent is to allow the 
     * object to implement a "handler" that will manage collision physics.
     */
    public void intersects(ArrayList<Intersectable> intersections);
}
