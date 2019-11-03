package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.infrastructure.util.formatter.ArtistFormatter;
import br.com.cefsa.ec6.measy.infrastructure.util.formatter.MillisTimeFormatter;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import java.util.Arrays;
import java.util.Collection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TrackRowController {

  @FXML private Label title;
  @FXML private Label artistsNames;
  @FXML private Label albumName;
  @FXML private Label duration;
  @FXML private SpotifyPlayButtonController playButtonController;

  public void setTrack(Track track) {
    setTitle(track.getName());
    setArtists(Arrays.asList(track.getArtists()));
    setAlbumName(track.getAlbum().getName());
    setDuration(track.getDurationMs());
    playButtonController.setSpotifyUri(track.getUri());
  }

  public void setTrack(TrackSimplified track) {
    setTitle(track.getName());
    setArtists(Arrays.asList(track.getArtists()));
    setAlbumName("");
    setDuration(track.getDurationMs());
    playButtonController.setSpotifyUri(track.getUri());
  }

  private void setTitle(String title) {
    this.title.setText(title);
  }

  private void setArtists(Collection<ArtistSimplified> artists) {
    this.artistsNames.setText(ArtistFormatter.formatSimplifiedArtistsNames(artists));
  }

  private void setAlbumName(String albumName) {
    this.albumName.setText(albumName);
  }

  private void setDuration(Integer duration) {
    this.duration.setText(MillisTimeFormatter.format(duration));
  }
}
