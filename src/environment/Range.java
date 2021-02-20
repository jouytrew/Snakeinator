/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package environment;

/**
 *
 * @author kevinlawrence
 */
public class Range {
    
//<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Checks if the value provided is within the boundaries of the range
     *
     * @param value value to be assessed
     * @return true if the value is within (inclusively) the range, false
     * otherwise
     */
    public boolean contains(int value){
        return ((value >= minimum) && (value <= maximum));
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * 
     * @param name the name of the range
     * @param minimum minimum value of the range
     * @param maxmimum maximum value of the range
     */
    public Range(String name, int minimum, int maxmimum){
        this.name = name;
        this.minimum = minimum;
        this.maximum = maxmimum;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private int minimum, maximum;
    private String name;
    
    /**
     * @return the minimum
     */
    public int getMinimum() {
        return minimum;
    }
    
    /**
     * @param start the minimum to set
     */
    public void setMinimum(int start) {
        this.minimum = start;
    }
    
    /**
     * @return the maximum
     */
    public int getMaximum() {
        return maximum;
    }
    
    /**
     * @param end the maximum to set
     */
    public void setMaximum(int end) {
        this.maximum = end;
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
//</editor-fold>
    
}
