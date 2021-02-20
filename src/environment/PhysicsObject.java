/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author kevinlawrence
 */
public class PhysicsObject implements MovableIntf, RotatableIntf, IntersectableIntf {

//  <editor-fold defaultstate="collapsed" desc="Constructors">    
    public PhysicsObject(Point position, Velocity velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public PhysicsObject(Point position, Velocity velocity, Point size) {
        this.position = position;
        this.velocity = velocity;
        this.width = size.x;
        this.height = size.y;
    }

    public PhysicsObject(Point position, Velocity velocity, int angularVelocity, int angle) {
        this.position = position;
        this.velocity = velocity;
        this.angularVelocity = angularVelocity;
        this.angle = angle;
    }
//  </editor-fold>

//   <editor-fold defaultstate="collapsed" desc="MovableIntf">
    private Velocity velocity = new Velocity(0, 0);
    private Point position = new Point(0, 0);
    
    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

    @Override
    public Velocity getVelocity() {
        return this.velocity;
    }

    @Override
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(int x, int y) {
        this.velocity.x = x;
        this.velocity.y = y;
    }

    @Override
    public void move() {
        this.position.x += this.velocity.x;
        this.position.y += this.velocity.y;
    }

    @Override
    public void stop() {
        this.velocity.x = 0;
        this.velocity.y = 0;
    }

    @Override
    public void accelerate(int x) {
        this.velocity.x += x;
    }
    
    public void accelerate(int x, int y) {
        this.velocity.x += x;
        this.velocity.y += y;
    }
    
    @Override
    public void accelerate(Velocity vector) {
        this.velocity.x += vector.x;
        this.velocity.y += vector.y;
    }
//  </editor-fold>

//   <editor-fold defaultstate="collapsed" desc="RotatableIntf">
    private int angularVelocity = 0;
    private int angle = EAST;

    /**
     * @return the angle
     */
    @Override
    public int getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    @Override
    public void setAngle(int angle) {
        this.angle = angle;
    }

    /**
     * @param angularVelocity the angularVelocity to set
     */
    @Override
    public void setAngularVelocity(int angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    /**
     * @return the angularVelocity
     */
    @Override
    public int getAngularVelocity() {
        return this.angularVelocity;
    }

    @Override
    public void rotate() {
        setAngle((this.angle += this.angularVelocity) % RotatableIntf.MAX_ANGLE);
    }

//  </editor-fold>

//   <editor-fold defaultstate="collapsed" desc="IntersectableIntf">
    private int height = 3;
    private int width = 3;

    @Override
    public Rectangle getObjectBoundary() {
        return new Rectangle(position.x, position.y, width, height);
    }

    /**
     * @return boolean: true if the IntersectableIntf object intersects (overlaps)
     * this object, false otherwise 
     * @param intersectable the object (that implements IntersectableIntf) that 
     * will be tested for overlap (intersection) with this object.
     */
    @Override
    public boolean intersects(IntersectableIntf intersectable) {
        return getObjectBoundary().intersects(intersectable.getObjectBoundary());
    }

    /**
     * @param intersections the list of IntersectableIntf objects that the current 
     * object intersects with (presumably, this will be created by an external
     * method that will call the "intersects" method on the relevant objects.
     * The intent of this method is to allow child classes to optionally 
     * implement their own class that implements IntersectionHandlerIntf, and use 
     * the "intersect" method on this class to define their own "collision 
     * physics", i.e. their own reaction to intersecting to other objects in
     * the environment.
     */
    @Override
    public void intersects(ArrayList<IntersectableIntf> intersections) {
        if (this.getIntersectionHandler() != null)
            for (IntersectableIntf intersectable : intersections)
                this.getIntersectionHandler().intersect(intersectable);
    }
//  </editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">    
    private IntersectionHandlerIntf intersectionHandler;

    /**
     * @return the intersectionHandler
     */
    public IntersectionHandlerIntf getIntersectionHandler() {
        return intersectionHandler;
    }
    
    /**
     * @param intersectionHandler the intersectionHandler to set
     */
    public void setIntersectionHandler(IntersectionHandlerIntf intersectionHandler) {
        this.intersectionHandler = intersectionHandler;
    }
    
    /**
     * @return the Point that is the geometric center
     */
    public Point getCenterOfMass(){
        return Physics.getCenterOfMass(this.getObjectBoundary());
    }

    /**
     * @return the zero-size rectangle that is the geometric center
     */
    public Rectangle getCenterOfMassSpatial(){
        Point com =  getCenterOfMass();
        return new Rectangle(com.x, com.y, 0, 0);
    }
    
    /**
     * @return boolean true if this object contains (surrounds) the provided point
     * @param point the point to be assessed for containment
     */
    public boolean contains(Point point){
        return this.getObjectBoundary().contains(point);
    }
//</editor-fold>
    
}
