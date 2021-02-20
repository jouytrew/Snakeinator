/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package image;

import java.awt.Image;
import java.util.HashMap;

/**
 *
 * @author kevinlawrence
 */
public class ImageManager {

    private HashMap<String, Image> imageList = new HashMap<String, Image>();

//  <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Constructor - @return ImageManager
     */
    public ImageManager(){ }
        
    /**
     * Constructor - @return ImageManager
     * @param imageList to be set: a map of image names and images
     */
    public ImageManager(HashMap<String, Image> imageList){
       this.imageList = imageList;
    }
    
    /**
     * Constructor - @return ImageManager
     * @param imageNames an array of String: image names to associate with the
     * image in the same position in the images array
     * @param images an array of Images to set, which will be associates for 
     * "lookup" purposes with an imageName 
     */
    public ImageManager(String[] imageNames, Image[] images){
        for (int i = 0; i < imageNames.length; i++) {
            imageList.put(imageNames[i], images[i]);
        }
    }
//  </editor-fold>
    
//  <editor-fold defaultstate="collapsed" desc="Getters and Setters">  
    
    /**
     * @return the image, based on a String (named) @param imageName
     */
    public Image getImage(String imageName){
        return getImageList().get(imageName);
    }

    /**
     * @param imageList the imageList to set, which contains a map of String
     * identifiers and associated Images
     */
    public void addImage(String imageName, Image image){
        getImageList().put(imageName, image);
    }

    /**
     * @return the imageList: a map of image names and images
     */
    public HashMap<String, Image> getImageList() {
        return imageList;
    }

    /**
     * @param imageList to be set: a map of image names and images
     */
    public void setImageList(HashMap<String, Image> imageList) {
        this.imageList = imageList;
    }
//  </editor-fold>

}
