package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.factory.HyperlinkFactory;
import br.com.cefsa.ec6.measy.infrastructure.util.formatter.MillisTimeFormatter;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import java.util.Arrays;
import java.util.Collection;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TrackRowController {

  @Autowired
  private ClientController clientController;
  @Autowired
  private HyperlinkFactory hyperlinkFactory;

  @FXML private Label duration;
  @FXML private HBox artistsContainer;
  @FXML private HyperlinkController trackNameController;
  @FXML private HyperlinkController albumNameController;
  @FXML private SpotifyPlayButtonController playButtonController;

  public void setTrack(Track track) {
    setTitle(track.getName());
    setTrackUri(track.getUri());

    setArtists(Arrays.asList(track.getArtists()));

    setAlbumName(track.getAlbum().getName());
    setAlbumUri(track.getAlbum().getUri());

    setDuration(track.getDurationMs());

    playButtonController.setSpotifyUri(track.getUri());
  }

  public void setTrack(TrackSimplified track) {
    setTitle(track.getName());
    setTrackUri(track.getUri());

    setArtists(Arrays.asList(track.getArtists()));

    setAlbumName("");

    setDuration(track.getDurationMs());

    playButtonController.setSpotifyUri(track.getUri());
  }

  public void setTitle(String title) {
    trackNameController.setText(title);
  }

  public void setArtists(Collection<ArtistSimplified> artists) {
    artistsContainer.getChildren().clear();

    for (ArtistSimplified artist : artists) {
      Node artistNode = hyperlinkFactory.create(artist.getName(), artist.getUri());
      artistsContainer.getChildren().add(artistNode);

      Node separator = hyperlinkFactory.create(", ", "");
      artistsContainer.getChildren().add(separator);
    }

    artistsContainer.getChildren().remove(artistsContainer.getChildren().size() - 1);
  }

  public void setAlbumName(String albumName) {
    albumNameController.setText(albumName);
  }

  public void setDuration(Integer duration) {
    this.duration.setText(MillisTimeFormatter.format(duration));
  }

  public String getTrackUri() {
    return trackNameController.getUri();
  }

  private void setTrackUri(String trackUri) {
    trackNameController.setUri(trackUri);
  }

  public String getAlbumUri() {
    return albumNameController.getUri();
  }

  private void setAlbumUri(String albumUri) {
    albumNameController.setUri(albumUri);
  }
}
