/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package images;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author kevinlawrence
 */
public class ResourceTools {

    /**
     * 
     * @param resourcePath fully qualified location of the image in the compiled 
     * binary file/library
     * @return an Image 
     */
    public static Image loadImageFromResource(String resourcePath) {
        Image image = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(resourcePath);
            image = ImageIO.read(input);
        } catch (IOException e) {
            //TODO - exception handling
        }
        return image;
    }

    /**
     * 
     * @param fileSystemPath fully qualified file system location of the image 
     * @return an Image
     */
    public static Image loadImageFromFile(String fileSystemPath) {
        return new ImageIcon(fileSystemPath).getImage();
    }
    
    /**
     * 
     * @param resourcePath fully qualified location of the resource in the compiled 
     * binary file/library
     * @return compiled resource, as stream
     */
    public static InputStream getResourceAsStream(String resourcePath){
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
    }
    
    /**
     * 
     * @param url fully qualified URL of the resource in the compiled 
     * binary file/library
     * @return compiled resource, as stream
     */
    public static InputStream getResourceAsStream(URL url){
        InputStream inputStream = null;
        try {
            inputStream = url.openStream();
        } catch (IOException ex) {
            Logger.getLogger(ResourceTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inputStream;
    }
}    