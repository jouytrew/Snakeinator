/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

/**
 *
 * @author kevin.lawrence
 */
public class Track {
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Track(String name, Source source, String location){
        this.name = name;
        this.source = source;
        this.location = location;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private String name = "";
    private Source source = Source.RESOURCE;
    private String location = "";
    
    /**
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return name + " " + source.toString() + " Loc: " + location;
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

    /**
     * @return the source
     */
    public Source getSource() {
        return source;
    }
    
    /**
     * @param source the source to set
     */
    public void setSource(Source source) {
        this.source = source;
    }
    
    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
//</editor-fold>
    
}