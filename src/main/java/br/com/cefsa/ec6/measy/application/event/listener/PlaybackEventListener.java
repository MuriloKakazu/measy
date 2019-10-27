package br.com.cefsa.ec6.measy.application.event.listener;

import br.com.cefsa.ec6.measy.application.event.*;
import br.com.cefsa.ec6.measy.domain.playback.SpotifyPlayer;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EventListener;

@Component
public class PlaybackEventListener implements EventListener {

    @Autowired
    private SpotifyPlayer spotifyPlayer;

    @Subscribe
    private void changePlaybackContext(ChangePlaybackContextEvent event) {
        spotifyPlayer.playUri(event.getContextUri());
    }

    @Subscribe
    private void pausePlaybackEvent(PausePlaybackEvent event) {
        spotifyPlayer.pause();
    }

    @Subscribe
    private void resumePlaybackEvent(ResumePlaybackEvent event) {
        spotifyPlayer.resume();
    }

    @Subscribe
    private void skipNextPlaybackEvent(SkipNextPlaybackEvent event) {
        spotifyPlayer.nextTrack();
    }

    @Subscribe
    private void skipPreviousPlaybackEvent(SkipPreviousPlaybackEvent event) {
        spotifyPlayer.previousTrack();
    }
}
