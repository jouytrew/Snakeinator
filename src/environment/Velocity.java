/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package environment;

/**
 *
 * @author kevinlawrence
 */
public class Velocity {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Velocity(int x, int y){
        this.x = x;
        this.y = y;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return String.format("{%d, %d}", x, y);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    public int x = 0;
    public int y = 0;
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }
    
    /**
     * @param x the x position to set
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
    /**
     * @param y the y position to set
     */
    public void setY(int y) {
        this.y = y;
    }
//</editor-fold>
}
