
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Rectangle;

/**
 *
 * @author kevinlawrence
 */
public class Region {
//    public static final int NO_LIMIT = -1;
    Rectangle boundary = new Rectangle(0, 0, 0, 0);
    Vector2D gravitation = new Vector2D(0, 0);
    Vector2D etherFlow = new Vector2D(0, 0);
    
    public boolean contains(PhysicsObject object){
        return boundary.intersects(object.getCenterOfMassSpatial());
    }
}
