/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author kevinlawrence
 */
public class Animator {

    private ImageManager imageManager = new ImageManager();
    private ArrayList<String> imageNames = new ArrayList<String>();
    private int currentImageNameIndex = 0;
    private Timer animationTimer = new Timer();
    private long delayDurationMillis = 0;
    private long displayDurationMillis = 100;

//  <editor-fold defaultstate="collapsed" desc="Constructors">
    public Animator(ImageManager imageManager, ArrayList<String> imageNames, int displayDurationMillis) {
        this.imageManager = imageManager;
        this.imageNames = imageNames;
        this.displayDurationMillis = displayDurationMillis;

        initializeTimer();
    }
//  </editor-fold>

    public final void initializeTimer() {
        animationTimer.schedule(new AnimatorTimerTask(), getDelayDurationMillis(), getDisplayDurationMillis());
    }

    private class AnimatorTimerTask extends TimerTask {

        @Override
        public void run() {
            incrementImageName();
        }

        private void incrementImageName() {
            if (imageNames.size() > 0) {
                currentImageNameIndex++;
                currentImageNameIndex %= imageNames.size();
                setCurrentImageName(imageNames.get(currentImageNameIndex));
            }
        }
    }

//  <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * @return the currentImage
     */
    public Image getCurrentImage() {
        System.out.println(getCurrentImageName());
        return imageManager.getImage(getCurrentImageName());
    }

    /**
     * @return the currentImageName
     */
    public String getCurrentImageName() {
        return this.imageNames.get(currentImageNameIndex);
    }

    /**
     * @param currentImageName the currentImageName to set
     */
    public void setCurrentImageName(String currentImageName) {
        this.currentImageNameIndex = imageNames.indexOf(currentImageName);
    }

    /**
     * @return the delayDurationMillis
     */
    public long getDelayDurationMillis() {
        return delayDurationMillis;
    }

    /**
     * @param delayDurationMillis the delayDurationMillis to set
     */
    public void setDelayDurationMillis(long delayDurationMillis) {
        this.delayDurationMillis = delayDurationMillis;
    }

    /**
     * @return the displayDurationMillis
     */
    public long getDisplayDurationMillis() {
        return displayDurationMillis;
    }

    /**
     * @param displayDurationMillis the displayDurationMillis to set
     */
    public void setDisplayDurationMillis(long displayDurationMillis) {
        this.displayDurationMillis = displayDurationMillis;
    }
//  </editor-fold>  
}
