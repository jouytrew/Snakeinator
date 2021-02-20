/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kevin.lawrence
 */
public class Playlist implements TrackListProviderIntf {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        tracks = new ArrayList<>();
    }
    
    public Playlist(){}

    public Playlist(ArrayList<Track> tracks){
        this.tracks = tracks;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Shuffles the playlist randomly, and returns the first audio track.
     * @return 
     */
    public Track shuffle() {
        Collections.shuffle(tracks);
        
        setIndex(0);
        return getCurrentTrack();
    }

    /**
     * Sets the current audio track to the track with the name provided.
     * @param name The name of the track
     * @return The current audio track in the list
     */
    public Track play(String name) {
        for (Track track : tracks) {
            if (track.getName().equals(name)) {
                setIndex(tracks.indexOf(track));
                break;
            }
        }

        return getCurrentTrack();
    }

    /**
     * Sets the audio track to the next track in the playlist.
     * @return the next audio track in the list
     */
    public Track next() {
        setIndex(getNextIndex());
        return getCurrentTrack();
    }

    /**
     * Sets the audio track to the previous track in the playlist.
     * @return the previous audio track in the list
     */
    public Track first() {
        setIndex(0);
        return getCurrentTrack();
    }

    /**
     * Sets the audio track to the last track in the playlist.
     * @return the last audio track in the list
     */
    public Track last() {
        setIndex(getTracks().size());
        return getCurrentTrack();
    }

    /**
     * Gets the current audio track.
     * @return the current audio track
     */
    public Track getCurrentTrack() {
        return getTracks().get(getIndex());
    }

    /**
     * Gets the next audio track, but does not change to that track - useful
     * for "preview" functionality.
     * @return the next audio track
     */
    public Track getNextTrack() {
        return getTracks().get(getNextIndex());
    }
    
    /**
     * @param track the tracks to add
     */
    private void add(Track track) {
        this.tracks.add(track);
    }

    /**
     * @param track the tracks to remove
     */
    private void remove(Track track) {
        this.tracks.remove(track);
    }
  
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Track> tracks;
    private int index;

    /**
     * @return the track
     */
    public Track getTrack(String trackName) {
        for (Track track : tracks){
            if (track.getName().equals(trackName)){
                return track;
            }
        }
        return null;
    }

    /**
     * @return the tracks
     */
    private ArrayList<Track> getTracks() {
        return tracks;
    }

    /**
     * @param tracks the tracks to set
     */
    private void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    /**
     * @return the track names
     */
    public ArrayList<String> getTrackNames() {
        ArrayList<String> trackNames = new ArrayList<>();
        for (Track track : tracks){
            trackNames.add(track.getName());
        }
        return trackNames;
    }

    /**
     * @return the index
     */
    private int getNextIndex() {
        return (index + 1) % getTracks().size();
    }

    /**
     * @return the index
     */
    private int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    private int setIndex(int index) {
        this.index = index % getTracks().size();
        return this.index;
    }
//</editor-fold>
    
}