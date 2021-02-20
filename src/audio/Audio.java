/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

//import static audio.AudioPlayer.LOOP_SINGLE;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.*;

/**
 *
 * @author kevin.lawrence
 */
//public class Audio implements Runnable {//extends Thread {
public class Audio extends Thread {

//    public Audio()
//<editor-fold defaultstate="collapsed" desc="Constants">
    public static final int LOOP_INFINITE = -1;
    public static final int LOOP_NEVER = 0;
    public static final int LOOP_SINGLE = 1;

    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
    private final int NO_MORE_DATA = -1;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private final AudioEventListenerIntf eventListener;
    private Track track;

    private int loopCount = LOOP_SINGLE;
    private int playCount = 0;

    private AudioPosition curPosition = AudioPosition.NORMAL;
    private AudioState audioState = AudioState.PLAYING;

    /**
     * @return the AudioState
     */
    public String getTrackName() {
        return track != null ? track.getName() : "NO TRACK";
    }

    /**
     * @return the AudioState
     */
    public AudioState getAudioState() {
        return audioState;
    }

    /**
     * @param audioState the AudioState to set
     */
    public void setAudioState(AudioState audioState) {
        boolean notify = (this.audioState != audioState);
        this.audioState = audioState;

        if (notify) {
            switch (this.audioState) {
                case PLAYING:
                    raiseEvent(AudioEvent.ON_START);
                    break;
                case STOPPED:
                    raiseEvent(AudioEvent.ON_STOP);
                    break;
                case COMPLETED:
                    raiseEvent(AudioEvent.ON_COMPLETE);
            }
        }
    }

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Methods">
//    public void pause() {
//        state = AudioState.PAUSED;
//    }
    public void quit() {
        loopCount = LOOP_NEVER;
        playCount = loopCount;
        setAudioState(AudioState.STOPPED);
    }

    public void play() {
        setAudioState(AudioState.PLAYING);
    }

    private void raiseEvent(AudioEvent event) {
        if (eventListener != null) {
            eventListener.onAudioEvent(event, (track != null) ? track.getName() : "No track provided");
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructors">
//<editor-fold defaultstate="collapsed" desc="Potential Constuctors">
//    public static void play(InputStream audio, int loopCount) {
//        new AudioPlayer(audio).start();
//    }
//
//    public static void play(InputStream audio) {
//        play(audio, LOOP_SINGLE);
//    }
//
//    public static void play(String resource) {
//        play(resource, LOOP_SINGLE);
//    }
//</editor-fold>
    public Audio(Track track, int loopCount, AudioEventListenerIntf eventListener) {
        this.loopCount = loopCount;
        this.track = track;
        this.eventListener = eventListener;

//        try {
//            InputStream inputStream = Audio.class.getResourceAsStream(resource);
//            audioInputStream = AudioSystem.getAudioInputStream(inputStream);
//        } catch (UnsupportedAudioFileException | IOException e) {
//            state = AudioState.STOPPED;
//        }
        try {
            Audio.class.getResourceAsStream(track.getLocation());
            audioState = AudioState.PLAYING;
        } catch (Exception e) {
            audioState = AudioState.STOPPED;
            raiseEvent(AudioEvent.ON_ERROR);
        }
    }
//</editor-fold>

    private AudioInputStream getAIS() {
        try {
            InputStream inputStream = Audio.class.getResourceAsStream(track.getLocation());
            return AudioSystem.getAudioInputStream(inputStream);
        } catch (UnsupportedAudioFileException | IOException e) {
            setAudioState(AudioState.STOPPED);
        }
        return null;
    }

    @Override
    public void run() {

        while ((loopCount == LOOP_INFINITE) || (playCount < loopCount)) {
            AudioInputStream audioInputStream = getAIS();

            AudioFormat audioFormat = audioInputStream.getFormat();
            SourceDataLine sourceDataLine = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

            try {
                sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
                sourceDataLine.open(audioFormat);
            } catch (LineUnavailableException e) {
            } catch (Exception e) {
            }

            if (sourceDataLine.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) sourceDataLine.getControl(FloatControl.Type.PAN);

                if (curPosition == AudioPosition.RIGHT) {
                    pan.setValue(1.0f);
                } else if (curPosition == AudioPosition.LEFT) {
                    pan.setValue(-1.0f);
                }
            }

            sourceDataLine.start();
            int bytesRead = 0;
            byte[] audioBufferData = new byte[EXTERNAL_BUFFER_SIZE];

            try {
                AudioInputStream copyAIS = audioInputStream;

                while ((bytesRead != NO_MORE_DATA) && (getAudioState() != AudioState.STOPPED)) {
//<editor-fold defaultstate="collapsed" desc="defer pause implementation">
//                while (state == AudioState.PAUSED) {
//                    try {
//                        Thread.sleep(4000);
//                    } catch (InterruptedException e) {
//                    }
//                }
//</editor-fold>
//                    System.out.println("Writing data to audio stream...");
                    bytesRead = copyAIS.read(audioBufferData, 0, audioBufferData.length);

                    if (bytesRead >= 0) {
                        sourceDataLine.write(audioBufferData, 0, bytesRead);
                    }
                }
            } catch (IOException e) {
            } finally {
                sourceDataLine.drain();
                sourceDataLine.close();
            }

            playCount++;
        }
        setAudioState(AudioState.COMPLETED);
    }

}
