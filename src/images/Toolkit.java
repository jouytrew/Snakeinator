/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package images;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author kevin.lawrence
 */
public class Toolkit {
    
    //<editor-fold defaultstate="collapsed" desc="Image Methods">
    
    /**
     * @param image image to be rotation-transformed
     * @param degrees the amount to rotate the image
     * @return the buffered image, rotated by in the input degrees
     */
    public static BufferedImage rotateImage(BufferedImage image, double degrees) {
        AffineTransform transform = new AffineTransform();
        
        transform.setToTranslation(0.5 * image.getWidth(), 0.5 * image.getHeight());
        transform.rotate(Math.toRadians(degrees));
        transform.translate(-0.5 * image.getHeight(), -0.5 * image.getWidth());
        
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);   
        return op.filter(image, null);
    }
    
    /**
     * @param image Image to be converted to BufferedImage class
     * @return the buffered image
     */
    public static BufferedImage convertToBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // Create a buffered image with transparency
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bufferedImage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bufferedImage;
    } 
    //</editor-fold>
    
}
