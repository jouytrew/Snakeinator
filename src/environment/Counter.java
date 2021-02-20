/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

/**
 *
 * @author kevinlawrence
 */
public final class Counter {

//  <editor-fold defaultstate="Collapsed" desc="Constructors">
    /**
     *
     * @param name
     * @param minimum
     * @param maximum
     * @param value
     * @param ranges
     */
    public Counter(String name, int minimum, int maximum, int value, Range[] ranges) {
        this.name = name;
        this.minimum = minimum;
        setMaximum(maximum);
        setValue(value);
        this.ranges = ranges;
    }
//  </editor-fold>

//  <editor-fold defaultstate="Collapsed" desc="Properties">
    private String name;
    private int minimum = Integer.MIN_VALUE;
    private int maximum = Integer.MAX_VALUE;
    private int value;
    private Range[] ranges;

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

    /**
     * @return the minimum
     */
    public int getMinimum() {
        return minimum;
    }

    /**
     * @param minimum the minimum to set: minimum may not be higher than
     * maximum, and value may not be less than minimum
     */
    public void setMinimum(int minimum) {
        // don't allow minimum to be more than maximum
        if (minimum > this.maximum) {
            this.minimum = this.maximum;
        } else {
            this.minimum = minimum;
        }

        // don't allow value to be less than minimum
        if (this.value < this.minimum) {
            this.value = this.minimum;
        }
    }

    /**
     * @return the maximum
     */
    public int getMaximum() {
        return maximum;
    }

    /**
     * @param maximum the maximum to set: the maximum may not be less than the
     * minimum, and the value may not be more than the maximum
     */
    public void setMaximum(int maximum) {
        this.maximum = maximum;
        // don't allow maximum to be less than minimum
        if (maximum < this.minimum) {
            this.maximum = this.minimum;
        } else {
            this.maximum = maximum;
        }

        // don't allow value to be more than maximum
        if (this.value > this.maximum) {
            this.value = this.maximum;
        }
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set: value must be less than the maximum and
     * less than the minimum
     */
    public void setValue(int value) {
        if (value > this.maximum) {
            this.value = maximum;
        } else if (value < this.minimum) {
            this.value = minimum;
        } else {
            this.value = value;
        }
    }

    /**
     * @param increment the value to add to Value
     */
    public void incrementValue(int increment) {
        int newValue = this.value + increment;
        this.value = (newValue < this.maximum) ? newValue : this.maximum;
    }

    /**
     * @param decrement the value to subtract from Value
     */
    public void decrementValue(int decrement) {
        int newValue = this.value - decrement;
        this.value = (newValue > this.minimum) ? newValue : this.minimum;
    }

    /**
     * @return the current range, based on the value, returns null if there are
     * no ranges defined, or if the value is not within an existing range
     */
    public Range getCurrentRange() {
        if (ranges != null) {
            for (Range range : ranges) {
                if (range.contains(value)) {
                    return range;
                }
            }
        }
        return null;
    }

    /**
     * @return the label of th current range, based on the value, returns an
     * empty string if the value is not within an existing range
     */
    public String getCurrentRangeLabel() {
        Range range = getCurrentRange();
        if (range != null) {
            return range.getName();
        } else {
            return "";
        }
    }

    /**
     * @return the ranges
     */
    public Range[] getRanges() {
        return ranges;
    }

    /**
     * @param ranges the ranges to set
     */
    public void setRanges(Range[] ranges) {
        this.ranges = ranges;
    }
//  </editor-fold>

}
