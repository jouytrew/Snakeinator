/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import images.Animator;
import images.ImageManager;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
public abstract class AnimatedActor extends Actor {

//<editor-fold defaultstate="collapsed" desc="Constructors, Initialization">
    {
        setStandImage(new ArrayList<String>());
        setFrontWalkImages(new ArrayList<String>());
        setBackWalkImages(new ArrayList<String>());
        setRightWalkImages(new ArrayList<String>());
        setLeftWalkImages(new ArrayList<String>());

        setImageManager(new ImageManager());
        ArrayList<String> imageNames = new ArrayList<>();

        setAnimator(new Animator(getImageManager(), imageNames, 250));
    }

    public AnimatedActor(Point position, Velocity velocity) {
        super(position, velocity);
        initializeImages();
    }

    public abstract void initializeImages();
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private ImageManager imageManager;
    private Animator animator;
    private int speed = 2;

    private ArrayList<String> frontWalkImages, backWalkImages, leftWalkImages, rightWalkImages, standImage;
    private String actionState;

    public static String ACTION_STATE_BACK_WALK = "back_walk",
            ACTION_STATE_FRONT_WALK = "front_walk",
            ACTION_STATE_RIGHT_WALK = "right_walk",
            ACTION_STATE_LEFT_WALK = "left_walk",
            ACTION_STATE_STOP = "stop";

    /**
     * @return the image
     */
    @Override
    public BufferedImage getImage() {
        if (getAnimator() == null) {
            return super.getImage();
        } else {
            return (BufferedImage) getAnimator().getCurrentImage();
        }
    }

    /**
     * @return the imageManager
     */
    public ImageManager getImageManager() {
        return imageManager;
    }

    /**
     * @param imageManager the imageManager to set
     */
    public void setImageManager(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    /**
     * @return the animator
     */
    public Animator getAnimator() {
        return animator;
    }

    /**
     * @param animator the animator to set
     */
    public void setAnimator(Animator animator) {
        this.animator = animator;
    }

    /**
     * @return the actionState
     */
    public String getActionState() {
        return actionState;
    }

    /**
     * @param actionState the state to set
     */
    public void setActionState(String actionState) {
        this.actionState = actionState;

        if (this.getActionState().equals(ACTION_STATE_BACK_WALK)) {
            animator.setImageNames(getBackWalkImages());

            getVelocity().x = 0;
            getVelocity().y = -getSpeed();
        } else if (this.getActionState().equals(ACTION_STATE_FRONT_WALK)) {
            animator.setImageNames(getFrontWalkImages());

            getVelocity().x = 0;
            getVelocity().y = getSpeed();
        } else if (this.getActionState().equals(ACTION_STATE_RIGHT_WALK)) {
            animator.setImageNames(getRightWalkImages());

            getVelocity().x = getSpeed();
            getVelocity().y = 0;
        } else if (this.getActionState().equals(ACTION_STATE_LEFT_WALK)) {
            animator.setImageNames(getLeftWalkImages());

            getVelocity().x = -getSpeed();
            getVelocity().y = 0;
        } else if (this.getActionState().equals(ACTION_STATE_STOP)) {
            animator.setImageNames(getStandImage());
            this.stop();
        }
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
//</editor-fold>

    /**
     * @return the frontWalkImages
     */
    public ArrayList<String> getFrontWalkImages() {
        return frontWalkImages;
    }

    /**
     * @param frontWalkImages the frontWalkImages to set
     */
    public void setFrontWalkImages(ArrayList<String> frontWalkImages) {
        this.frontWalkImages = frontWalkImages;
    }

    /**
     * @return the backWalkImages
     */
    public ArrayList<String> getBackWalkImages() {
        return backWalkImages;
    }

    /**
     * @param backWalkImages the backWalkImages to set
     */
    public void setBackWalkImages(ArrayList<String> backWalkImages) {
        this.backWalkImages = backWalkImages;
    }

    /**
     * @return the leftWalkImages
     */
    public ArrayList<String> getLeftWalkImages() {
        return leftWalkImages;
    }

    /**
     * @param leftWalkImages the leftWalkImages to set
     */
    public void setLeftWalkImages(ArrayList<String> leftWalkImages) {
        this.leftWalkImages = leftWalkImages;
    }

    /**
     * @return the rightWalkImages
     */
    public ArrayList<String> getRightWalkImages() {
        return rightWalkImages;
    }

    /**
     * @param rightWalkImages the rightWalkImages to set
     */
    public void setRightWalkImages(ArrayList<String> rightWalkImages) {
        this.rightWalkImages = rightWalkImages;
    }

    /**
     * @return the standImage
     */
    public ArrayList<String> getStandImage() {
        return standImage;
    }

    /**
     * @param standImage the standImage to set
     */
    public void setStandImage(ArrayList<String> standImage) {
        this.standImage = standImage;
    }

}
