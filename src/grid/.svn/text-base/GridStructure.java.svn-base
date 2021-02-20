/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kevinlawrence
 */
public class GridStructure {

    private ArrayList<Point> structure = new ArrayList<Point>();
    private Direction direction = Direction.NORTH;
    private double speed = 0.0;
    private long lastMovementTick = 0;
    private int extendLength = 0;
    private LocationValidator locationValidator;

    public GridStructure(ArrayList<Point> structure, Direction direction) {
        this.structure = structure;
        this.direction = direction;
    }

    /*
     * Move the structure one position in the correct direction To move the
     * GridStructure, remove the tail (last element of the structure list) and
     * insert a new element in front of the head (first element in the
     * structure).
     *
     * If direction = NORTH : ( head.x, head.y-1 ) SOUTH : ( head x, head.y+1 )
     * EAST : ( head.x+1, head y ) WEST : ( head.x-1, head y )
     *
     * NOTE: This has been enhanced to allow the user to provide a
     * LocationValidtor object; if this object has been provided, the proposed
     * position will be provided, and potentially manipulated by the
     * validateLocation method.
     */
    public void move() {
        /*
         * Do not move the object if unless the velocity is greater than zero,
         * and the structure size is greater than zero (i.e. it has "cells"
         * defined.
         */
        if ((getSpeed() <= 0) || (this.getStructure().size() == 0)) {
            return;
        }


        // compute position of point to add, based on direction
        int x = 0;
        int y = 0;
        switch (this.direction) {
            case NORTH:
                x = this.structure.get(0).x;
                y = this.structure.get(0).y - 1;
                break;

            case SOUTH:
                x = this.structure.get(0).x;
                y = this.structure.get(0).y + 1;
                break;

            case EAST:
                x = this.structure.get(0).x + 1;
                y = this.structure.get(0).y;
                break;

            case WEST:
                x = this.structure.get(0).x - 1;
                y = this.structure.get(0).y;
                break;

        }

        Point newLocation = new Point(x, y);
        if (locationValidator != null) {
            newLocation = locationValidator.validateLocation(newLocation);
        }

        /*
         * add new Point to new location: this will be the "head" (first
         * position), or the position that was computed by the locationValidator
         */
        this.getStructure().add(0, newLocation);

        /*
         * If extendLength is greater than zero, the structure should grow by
         * that amount: this will be applied as one growth step per movement. If
         * extending, leave the tail alone (do not delete), but if we are not
         * extending the structure, remove the "tail" (last position).
         */
        if (getExtendLength() > 0) {
            setExtendLength(getExtendLength() - 1);
        } else {
            this.getStructure().remove(this.getStructure().size() - 1);
        }
    }

    /**
     * @return the an array list of intersection points between the two
     * structures provided as a parameters.
     *
     * If there are no intersections, the method returns an empty array list, so
     * the consumer may check the size of this structure to determine if any
     * intersections exist.
     */
    public static ArrayList<Point> getIntersections(ArrayList<Point> coordinateList1, ArrayList<Point> coordinateList2) {
        ArrayList<Point> intersections = new ArrayList<Point>();

        for (Point ps1 : coordinateList1) {
            for (Point ps2 : coordinateList2) {
                if ((ps1.x == ps2.x) && (ps1.y == ps2.y)) {
                    intersections.add(new Point(ps1.x, ps1.y));
                }
            }
        }
        return intersections;
    }

    /**
     * @return the an array list of intersection points between the structure
     * provided as a parameter, and the structure of the class instance.
     *
     * If there are no intersections, the method returns an empty array list, so
     * the consumer may check the size of this structure to determine if any
     * intersections exist.
     */
    public ArrayList<Point> getIntersections(ArrayList<Point> coordinateList) {
        return getIntersections(this.structure, coordinateList);
    }

    /*
     * @return true if the structure intersects with itself, based on the
     * points that defined the coordinates of the structure, which is 
     * provided as a parameter.
     *
     * If there are no intersections, the method returns false.

     */
    public static boolean selfHitTest(GridStructure gridStructure) {
        for (int i = 1; i < gridStructure.getStructure().size(); i++) {
            for (int j = 1; j < gridStructure.getStructure().size(); j++) {
                if ((i != j)) {  // Do not test point against itself 
                    if ((gridStructure.getStructure().get(i).x == gridStructure.getStructure().get(j).x) &&
                        (gridStructure.getStructure().get(i).y == gridStructure.getStructure().get(j).y)) {
                        return true;  // Self hit!
                    }
                }
            }
        }
        return false;
    }

    /*
     * @return true if the structure intersects with itself, based on the
     * points that defined the coordinates of the structure, which is 
     * provided as a parameter. If there are no intersections, the method 
     * returns false.
     */
    public boolean selfHitTest() {
        return selfHitTest(this);
    }

    /**
     * @return the structure
     */
    public ArrayList<Point> getStructure() {
        return structure;
    }

    /**
     * @param structure the structure to set
     */
    public void setStructure(ArrayList<Point> structure) {
        this.structure = structure;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return the lastMovementTick
     */
    public long getLastMovementTick() {
        return lastMovementTick;
    }

    /**
     * @param lastMovementTick the lastMovementTick to set
     */
    public void setLastMovementTick(long lastMovementTick) {
        this.lastMovementTick = lastMovementTick;
    }

    /**
     * @return the extendLength
     */
    public int getExtendLength() {
        return extendLength;
    }

    /**
     * @param extendLength the extendLength to set, this length will be applied
     * in increments of one unit (cell) each time the structure moves
     */
    public void setExtendLength(int extendLength) {
        this.extendLength = extendLength;
    }

    /**
     * @param additionalLength the length to add to any existing, but not yet
     * applied length
     */
    public void addLength(int additionalLength) {
        this.extendLength += additionalLength;
    }

    /**
     * @return the locationValidator
     */
    public LocationValidator getLocationValidator() {
        return locationValidator;
    }

    /**
     * @param locationValidator the locationValidator to set
     */
    public void setLocationValidator(LocationValidator locationValidator) {
        this.locationValidator = locationValidator;
    }
}
