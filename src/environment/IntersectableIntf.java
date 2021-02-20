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
public interface IntersectableIntf {

    /**
     * @return the rectangle that defines the boundaries of the object
     */
    public Rectangle getObjectBoundary();

    /**
     * @param intersectable the IntersectableIntf object to be assessed for 
     * intersection
     * @return true if the object intersects with the IntersectableIntf object 
     * provided, false otherwise.
     */
    public boolean intersects(IntersectableIntf intersectable);

    /**
     * @param intersections ArrayList<Intersectable> the collection of 
     * IntersectableIntf objects that are currently intersecting the object; the
     * intent is to allow the object to implement a "handler" that will manage 
     * collision physics.
     */
    public void intersects(ArrayList<IntersectableIntf> intersections);
}
