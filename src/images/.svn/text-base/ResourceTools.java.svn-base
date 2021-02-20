/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

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

    public static Image loadImageFromResource(String resourcePath) {
        Image image = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(resourcePath);
            image = ImageIO.read(input);
        } catch (IOException e) {
        }

        return image;
    }

    public static Image loadImageFromFile(String fileSystemPath) {
        return new ImageIcon(fileSystemPath).getImage();
    }
    
    public static InputStream getResourceAsStream(String resourcePath){
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
    }
    
    public static InputStream getResourceAsStream(URL url){
        InputStream inputStream = null;
        try {
            inputStream = url.openStream();
        } catch (IOException ex) {
            Logger.getLogger(ResourceTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inputStream;
    }

//    public static InputStream getResourceAsStream(String resourcePath){
//        InputStream inputStream = null;
//        try {
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            inputStream = classLoader.getResourceAsStream(resourcePath);
////            image = ImageIO.read(input);
//        } catch (Exception e) {
//        }
//
//        return inputStream;
//    }

}    