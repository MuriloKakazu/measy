package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.component.menu.TrackContextMenu;
import br.com.cefsa.ec6.measy.domain.model.youtube.Video;
import br.com.cefsa.ec6.measy.infrastructure.util.ImagePicker;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import java.util.Arrays;
import java.util.Collection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TrackHeaderController {

  @Autowired private TrackContextMenu trackContextMenu;

  @FXML private AnchorPane self;
  @FXML private Label title;
  @FXML private ImageView image;
  @FXML private SpotifyPlayButtonController playButtonController;
  @FXML private YoutubeEmbeddedVideoController embeddedVideoController;
  @FXML private MultipleArtistsController artistsController;

  private String trackUri;

  @FXML
  private void initialize() {
    self.setOnContextMenuRequested(
        event -> {
          trackContextMenu.setSelectedTrackUri(trackUri);
          trackContextMenu.getTrackMenu().show(self, event.getScreenX(), event.getScreenY());
        });
  }

  private void setImage(@NotNull String imageUrl) {
    this.image.setImage(new Image(imageUrl));
  }

  private void setTitle(@NotNull String title) {
    this.title.setText(title);
  }

  private void setArtists(Collection<ArtistSimplified> artists) {
    artistsController.clear();

    for (ArtistSimplified artist : artists) {
      artistsController.addArtist(artist);
    }
  }

  private void setTrackUri(@NotNull String trackUri) {
    this.trackUri = trackUri;
    this.playButtonController.setSpotifyUri(trackUri);
  }

  public void setTrack(Track track) {
    final String trackName = track.getName();
    final String trackCoverUrl =
        ImagePicker.pickHighestResolutionImage(track.getAlbum().getImages()).getUrl();

    setImage(trackCoverUrl);
    setTitle(trackName);
    setTrackUri(track.getUri());
    setArtists(Arrays.asList(track.getArtists()));
  }

  public void loadVideo(@NotNull Video video) {
    embeddedVideoController.load(video.getUrl());
  }
}
