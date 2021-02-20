/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package environment;

import java.awt.Point;

/**
 * The MovableIntf interface defines an object that has a position,
 * @author kevinlawrence
 */
public interface MovableIntf {

    /**
     * @return the position of the MovableIntf object
     */
    public Point getPosition();

    /**
     * @param position the position to set
     */
    public void setPosition(Point position);

    /**
     * @param x the x component of the position to set
     * @param y the y component of the position to set
     */
    public void setPosition(int x, int y);

    /**
     * @return the velocity of the MovableIntf object
     */
    public Velocity getVelocity();

    /**
     * @param velocity the velocity to set
     */
    public void setVelocity(Velocity velocity);

    /**
     * @return void
 cause the MovableIntf object to move
     */
    public void move();

    /**
     * @return void
 
 Causes the MovableIntf object to stop; While the implementation of this
 function is left to the relevant object, unless otherwise stated, it 
 may be assumed that this method sets the velocity to (0, 0), and that,
 subject to other mutations of the objects velocity, subsequent calls
 to the move method will have no effect on position.
     */
    public void stop();
    
    /**
     * @return void
 
 Causes the MovableIntf object to accelerate, i.e. increase the velocity  
 along the "x" (horizontal) dimension by the amount of the input parameter
 x.While the implementation of this method is left to the relevant object, 
 unless otherwise stated, it may be assumed that this method performs a 
 scalar addition to the x sub-component of the current velocity vector.
     */
    public void accelerate(int x);
    
    /**
     * @return void
     * @param vector the Velocity(x, y) vector that will be added to the current
 velocity.
 
 Causes the MovableIntf object accelerate in both the x (horizontal) and y 
 (vertical) dimensions by the respective scalar amounts. While the 
 implementation of this method is left to the relevant object, unless 
 otherwise stated, it may be assumed that this method performs a scalar
 addition to the x and y sub-component of the current velocity vector.
     */
    public void accelerate(Velocity vector);

}
