/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package image;

import java.util.ArrayList;

/**
 *
 * @author kevinlawrence
 */
public class Sequence {
    private ArrayList<SequenceElement> sequenceElements = new ArrayList<SequenceElement>();
    private boolean isLooped = false;
    private int currentIndex = 0;
    private long startTimeMillis;

    public Sequence(){}

    public Sequence(ArrayList<SequenceElement> sequenceElements, boolean isLooped){
        this.sequenceElements = sequenceElements;
        this.isLooped = isLooped;
    }

    public void tick(){
        if ((System.currentTimeMillis() - this.startTimeMillis) > getCurrent().getDurationMillis())
            incrementIndex();
    }

    private void incrementIndex() {
        if (currentIndex < sequenceElements.size())
            currentIndex++;
        else if (isLooped)
            currentIndex = 0;
        else{
            //TOTO - may have to revisit this logic... could cause delays if
            //  the timer granularity is not hig enough
            setStartTimeMS(System.currentTimeMillis());
        }
    }


    public int getCurrentIndex(){
        return this.currentIndex;
    }

    public void setCurrentIndex(int currentIndex){
        this.currentIndex = currentIndex;
    }

    private SequenceElement getCurrent() {
        return this.sequenceElements.get(currentIndex);
    }

    private void setStartTimeMS(long startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }

    public SequenceElement start(){
        setStartTimeMS(System.currentTimeMillis());
        return getCurrent();
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
     * @return the isLooped
     */
    public boolean isIsLooped() {
        return isLooped;
    }

    /**
     * @param isLooped the isLooped to set
     */
    public void setIsLooped(boolean isLooped) {
        this.isLooped = isLooped;
    }

}
