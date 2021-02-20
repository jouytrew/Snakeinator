package audio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevin.lawrence
 */
public interface AudioEventListenerIntf {
    public void onAudioEvent(AudioEvent event, String trackName);
}
