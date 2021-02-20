/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package images;

/**
 *
 * @author kevinlawrence
 */
public class SequenceElement {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * @param sequenceElementUID unique identifier for the sequence element
     * @param durationMillis duration to display this element when animating
     */
    public SequenceElement(int sequenceElementUID, long durationMillis){
        this.sequenceElementUID = sequenceElementUID;
        this.durationMillis = durationMillis;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private long durationMillis = 100;
    private int sequenceElementUID;
    
    /**
     * @return the durationMS
     */
    public long getDurationMillis() {
        return durationMillis;
    }
    
    /**
     * @param durationMillis the durationMS to set
     */
    public void setDurationMillis(long durationMillis) {
        this.durationMillis = durationMillis;
    }
    
    /**
     * @return the animationElementUID
     */
    public int getSequenceElementUID() {
        return sequenceElementUID;
    }
    
    /**
     * @param sequenceElementUID the animationElementUID to set
     */
    public void setSequenceElementUID(int sequenceElementUID) {
        this.sequenceElementUID = sequenceElementUID;
    }
//</editor-fold>
}
