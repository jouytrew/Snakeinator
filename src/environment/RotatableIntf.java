/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package environment;

/**
 *
 * @author kevinlawrence
 */
public interface RotatableIntf {
    public static int MAX_ANGLE = 360;

    public static int EAST  =   0;
    public static int NORTH =  90;
    public static int WEST  = 180;
    public static int SOUTH = 270;


    public int getAngle();
    public void setAngle(int angle);

    public void setAngularVelocity(int angularVelocity);
    public int getAngularVelocity();

    /**
     * Cause the RotatableIntf object to rotate
     */
    public void rotate();

}
