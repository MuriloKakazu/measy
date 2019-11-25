package br.com.cefsa.ec6.measy.domain.playback;

import static br.com.cefsa.ec6.measy.infrastructure.util.SpotifyUriHelper.isTrackUri;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.PlayerPanelController;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import br.com.cefsa.ec6.measy.infrastructure.definitions.spotify.LoopMode;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.model_objects.miscellaneous.Device;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.Track;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpotifyPlayer {
  @Autowired private SpotifyClient spotifyClient;
  @Autowired private PlayerPanelController playerPanelController;

  public CurrentlyPlayingContext getPlaybackInfo() {
    return spotifyClient.getPlaybackInfo();
  }

  public void play(@NotNull Artist artist) {
    playUri(artist.getUri());
  }

  public void play(@NotNull Album album) {
    playUri(album.getUri());
  }

  public void play(@NotNull Playlist playlist) {
    playUri(playlist.getUri());
  }

  public void play(@NotNull Track track) {
    playUri(track.getUri());
  }

  public void playUri(@NotNull String uri) {
    if (isTrackUri(uri)) spotifyClient.playTrack(uri);
    else spotifyClient.playContext(uri);
    playerPanelController.refresh();
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

  public LoopMode getLoop() {
    return LoopMode.getByKey(getPlaybackInfo().getRepeat_state());
  }

  public Boolean getShuffle() {
    return getPlaybackInfo().getShuffle_state();
  }

  public Boolean isPlaying() {
    return getPlaybackInfo().getIs_playing();
  }

  public Track getCurrentTrack() {
    return getPlaybackInfo().getItem();
  }

  public Device getCurrentDevice() {
    return getPlaybackInfo().getDevice();
  }

  public Boolean hasDevice() {
    return getCurrentDevice() != null;
  }
}
