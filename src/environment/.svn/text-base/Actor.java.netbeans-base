/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package environment;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author kevinlawrence
 */
public abstract class Actor extends PhysicsObject {
    private String name = "";
    private Image image;
    private MouseAdapter mouseAdapter;

    /**
     * Constructor, returns an instance of the Actor class
     * @param image the image to set
     * @param position the position to set
     * @param velocity the image to set
     */
    public Actor(Image image, Point position, Velocity velocity){
        super(position, velocity);
        this.image = image;
    }

    /**
     * Constructor, returns an instance of the Actor class
     * @param position the position to set
     * @param velocity the image to set
     */
    public Actor(Point position, Velocity velocity){
        super(position, velocity);
    }

    /**
     * Constructor, returns an instance of the Actor class
     * @param position the position to set
     * @param velocity the image to set
     * @param angualrVelocity the angular velocity to set
     * @param angle the initial angle to set
     */
    public Actor(Point position, Velocity velocity, int angularVelocity, int angle){
        super(position, velocity, angularVelocity, angle);
    }
   
    public void paint(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(getImage(), getPosition().x, getPosition().y, null);
    }
    
    public void onMouseClick(MouseEvent e){
        if (mouseAdapter != null){
            mouseAdapter.mouseClicked(e);
        }
    }

//  <editor-fold defaultstate="collapsed" desc="Intersectable">
    @Override
    public Rectangle getObjectBoundary() {
        return new Rectangle(this.getPosition().x, this.getPosition().y, this.getImage().getWidth(null), this.getImage().getHeight(null));
    }
//  </editor-fold>

//  <editor-fold defaultstate="collapsed" desc="Property getters and setters">    
    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the mouseAdapter
     */
    public MouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }

    /**
     * @param mouseAdapter the mouseAdapter to set
     */
    public void setMouseAdapter(MouseAdapter mouseAdapter) {
        this.mouseAdapter = mouseAdapter;
    }
//  </editor-fold>

}
