/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
public class SoundManager implements AudioEventListenerIntf {
    // keep list of audio tracks -> playlist
    // play track on demand
    // control specific track - play, stop, pause, loop (count)
    // memory, thread management
    // volume control?
    // limit number of sounds at once?
    // cache option for faster performance?
    // surface Audio events (PLAY, STOP, ERROR, COMPLETE...)

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        audioList = new ArrayList<Audio>();
    }

    public SoundManager(Playlist playlist) {
        this.playlist = playlist;
    }

    public SoundManager(Playlist playlist, AudioEventListenerIntf eventListener) {
        this.playlist = playlist;
        this.eventListener = eventListener;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Methods">
    public void play(String trackName) {
        play(trackName, Audio.LOOP_SINGLE);
    }

    public void play(String trackName, int loopCount) {
        Track track = playlist.getTrack(trackName);
        if (track != null) {
            play(new Audio(track, loopCount, this));
        } else {
            onAudioEvent(AudioEvent.ON_ERROR, String.format("Track not found in play list: '%s'", trackName));
        }
    }

    private void play(Audio audio) {
        registerAudio(audio);
        audio.start();

        cleanAudioList();
    }

    public void stop(String trackName) {
        for (Audio audio : audioList){
            if (audio.getTrackName().equals(trackName)){
                audio.quit();
            }
        }
//        System.out.println("SoundPlayer.stop() not yet implemented.");
        onAudioEvent(AudioEvent.ON_STOP, String.format("Stop track '%s'", trackName));
    }

    public void outputAudioList(){
        System.out.println("Audio Threads");
        System.out.println("-------------");
        for (Audio audio : audioList) {
            System.out.println(" " + audio.getName());
        }
    }
    
    private void cleanAudioList() {
        outputAudioList();
        try {
            for (int i = audioList.size() - 1; i >= 0; i--) {
                if (audioList.get(i).getAudioState().equals(AudioState.COMPLETED)) {
                    audioList.remove(i);
                }
            }
        } catch (Exception e) {
            //TO DO
        }
        outputAudioList();
    }

    private void registerAudio(Audio audio) {
        audioList.add(audio);
    }

//</editor-fold>   
//<editor-fold defaultstate="collapsed" desc="Properties">
    private Playlist playlist;
    private AudioEventListenerIntf eventListener;
    private ArrayList<Audio> audioList;

    /**
     * @return the playlist
     */
    public Playlist getPlaylist() {
        return playlist;
    }

    /**
     * @param playlist the playlist to set
     */
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    /**
     * @return the eventListener
     */
    public AudioEventListenerIntf getEventListener() {
        return eventListener;
    }

    /**
     * @param eventListener the eventListener to set
     */
    public void setEventListener(AudioEventListenerIntf eventListener) {
        this.eventListener = eventListener;
    }

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="AudioEventListenerIntf Interface Methods">
    @Override
    public void onAudioEvent(AudioEvent event, String trackName) {
        if (this.eventListener != null) {
            eventListener.onAudioEvent(event, trackName);
        }
        cleanAudioList();
    }
//</editor-fold>
}
