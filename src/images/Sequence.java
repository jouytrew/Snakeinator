/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package images;

import java.util.ArrayList;

/**
 *
 * @author kevinlawrence
 */
public class Sequence {
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Sequence(){}
    
    /**
     * 
     * @param sequenceElements 
     * @param loop 
     */
    public Sequence(ArrayList<SequenceElement> sequenceElements, boolean loop){
        this.sequenceElements = sequenceElements;
        this.loop = loop;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Methods">
    public void tick(){
        if ((System.currentTimeMillis() - this.startTimeMillis) > getCurrent().getDurationMillis())
            incrementIndex();
    }
    
    private void incrementIndex() {
        if (getCurrentIndex() < sequenceElements.size())
            setCurrentIndex(getCurrentIndex() + 1);
        else if (loop)
            setCurrentIndex(0);
        else{
            //TOTO - may have to revisit this logic... could cause delays if
            //  the timer granularity is not high enough
            setStartTimeMillis(System.currentTimeMillis());
        }
    }
    
    
    public SequenceElement start(){
        setStartTimeMillis(System.currentTimeMillis());
        return getCurrent();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<SequenceElement> sequenceElements = new ArrayList<SequenceElement>();
    private boolean loop = false;
    private int currentIndex = 0;
    private long startTimeMillis;

    /**
     * @return the current SequenceElement
     */
    private SequenceElement getCurrent() {
        return this.sequenceElements.get(getCurrentIndex());
    }
    
    /**
     * @return the animationSegments
     */
    public ArrayList<SequenceElement> getSequenceElementsSegments() {
        return sequenceElements;
    }
    
    /**
     * @param sequenceElements the animationSegments to set
     */
    public void setSequenceElementsSegments(ArrayList<SequenceElement> sequenceElements) {
        this.sequenceElements = sequenceElements;
    }
    
    /**
     * @return the loop
     */
    public boolean isLoop() {
        return loop;
    }
    
    /**
     * @param loop the loop to set
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    /**
     * @return the currentIndex
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * @param currentIndex the currentIndex to set
     */
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    /**
     * @param startTimeMillis the startTimeMillis to set
     */
    public void setStartTimeMillis(long startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }
//</editor-fold>

}
