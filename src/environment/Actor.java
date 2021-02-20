/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author kevinlawrence
 */
public abstract class Actor extends PhysicsObject {

//  <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Constructor, returns an instance of the Actor class
     *
     * @param image the image to set
     * @param position the position to set
     * @param velocity the image to set
     */
    public Actor(BufferedImage image, Point position, Velocity velocity) {
        super(position, velocity);
        this.image = image;
    }

    /**
     * Constructor, returns an instance of the Actor class
     *
     * @param image the image to set
     * @param position the position to set
     * @param velocity the image to set
     */
    public Actor(Image image, Point position, Velocity velocity) {
        super(position, velocity);
        setImage(image);
    }

    /**
     * Constructor, returns an instance of the Actor class
     *
     * @param position the position to set
     * @param velocity the image to set
     */
    public Actor(Point position, Velocity velocity) {
        super(position, velocity);
    }

    /**
     * Constructor, returns an instance of the Actor class
     *
     * @param position the position to set
     * @param velocity the image to set
     * @param angularVelocity the angular velocity to set
     * @param angle the initial angle to set
     */
    public Actor(Point position, Velocity velocity, int angularVelocity, int angle) {
        super(position, velocity, angularVelocity, angle);
    }
    //  </editor-fold>

//<editor-fold defaultstate="collapsed" desc="Paint">
    public void paint(Graphics graphics) {
        BufferedImage img = getImage();
        if (getAngle() != 0) {
            img = images.Toolkit.rotateImage(getImage(), getAngle());
        }
        
        if (imageSize == null) {
            graphics.drawImage(img, getPosition().x, getPosition().y, null);
        } else {
            graphics.drawImage(img, getPosition().x, getPosition().y, imageSize.width, imageSize.height, null);
        }
    }
    
    public void onMouseClick(MouseEvent e) {
        if (mouseAdapter != null) {
            mouseAdapter.mouseClicked(e);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Intersectable">
    @Override
    public Rectangle getObjectBoundary() {
        if (getImage() == null) {
            return super.getObjectBoundary();
        } else {
            return new Rectangle(getPosition().x, getPosition().y, getImage().getWidth(null), getImage().getHeight(null));
        }
    }
//  </editor-fold>

//  <editor-fold defaultstate="collapsed" desc="Properties">    
    private String name = "";
    private BufferedImage image;
    private MouseAdapter mouseAdapter;
    private Dimension imageSize;

    /**
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * @param img the image to set; this will be converted to a BufferedImage
     */
    public void setImage(Image img) {
        this.image = images.Toolkit.convertToBufferedImage(img);
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

    /**
     * @return the imageSize
     */
    public Dimension getImageSize() {
        return imageSize;
    }

    /**
     * @param imageSize the imageSize to set
     */
    public void setImageSize(Dimension imageSize) {
        this.imageSize = imageSize;
    }
//  </editor-fold>

}
