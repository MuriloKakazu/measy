package br.com.cefsa.ec6.measy.domain.playback;

import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import br.com.cefsa.ec6.measy.infrastructure.definitions.spotify.LoopMode;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.cefsa.ec6.measy.infrastructure.util.SpotifyResourceTypeIdentifier.isTrackUri;

@Component
public class SpotifyPlayer {
    @Autowired
    private SpotifyClient spotifyClient;

    public void play(@NotNull Artist artist) {
        spotifyClient.playContext(artist.getUri());
    }

    public void play(@NotNull Album album) {
        spotifyClient.playContext(album.getUri());
    }

    public void play(@NotNull Playlist playlist) {
        spotifyClient.playContext(playlist.getUri());
    }

    public void play(@NotNull Track track) {
        spotifyClient.playTrack(track.getUri());
    }

    public void playUri(@NotBlank String uri) {
        if (isTrackUri(uri)) spotifyClient.playTrack(uri);
        spotifyClient.playContext(uri);
    }

    public void resume() {
        spotifyClient.resumePlayback();
    }

    public void pause() {
        spotifyClient.pausePlayback();
    }

    public void nextTrack() {
        spotifyClient.nextTrack();
    }

    public void previousTrack() {
        spotifyClient.previousTrack();
    }

    public void setVolume(@Min(0) @Max(100) Integer volume) {
        spotifyClient.setVolume(volume);
    }

    public void setShuffle(Boolean shuffle) {
        spotifyClient.setShuffle(shuffle);
    }

    public void setLoop(LoopMode loopMode) {
        spotifyClient.setLoop(loopMode);
    }
}
