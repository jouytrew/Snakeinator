/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

/**
 *
 * @author kevinlawrence
 */
public class DurationTimer {
    private long durationMillis = 1000;
    private long startMillis = System.currentTimeMillis();
    
    public DurationTimer(long durationMillis){
        this.durationMillis = durationMillis;
    }
    
    /**
     * Start the current with the current system time, using the existing 
     * duration in milliseconds
     */
    public void start(){
        this.startMillis = System.currentTimeMillis();
    }
    
    /**
     * Start the current with the current system time, using the existing 
     * duration in milliseconds
     * 
     * @param durationMillis the duration in milliseconds to set
     */
    public void start(long durationMillis){
        this.setDurationMillis(durationMillis);
        start();
    }

    /**
     * Start the current with the current system time, using the existing 
     * duration in milliseconds
     * 
     * @return true if more than the defined duration has passed since the 
     * start time
     */
    public boolean isComplete(){
        return (getRemainingDurationMillis() == 0);
    }

    /**
     * @return the remaining duration in milliseconds, will return zero
     * as a minimum value if the time has expired
     */
    public long getRemainingDurationMillis(){
        return Math.max(0, this.durationMillis - getRunDurationMillis());
    }
    
    /**
     * @return the duration in milliseconds run thus far
     */
    public long getRunDurationMillis(){
        return System.currentTimeMillis() - this.startMillis;
    }
    
    /**
     * @return the durationMillis
     */
    public long getDurationMillis() {
        return durationMillis;
    }

    /**
     * @param durationMillis the durationMillis to set
     */
    public void setDurationMillis(long durationMillis) {
        this.durationMillis = durationMillis;
    }

    /**
     * @return the startMillis
     */
    public long getStartMillis() {
        return startMillis;
    }
    
}
