package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.component.menu.TrackContextMenu;
import br.com.cefsa.ec6.measy.application.factory.HyperlinkFactory;
import br.com.cefsa.ec6.measy.infrastructure.util.formatter.MillisTimeFormatter;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import java.util.Collection;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TrackRowController {

  @Autowired private ClientController clientController;
  @Autowired private HyperlinkFactory hyperlinkFactory;
  @Autowired private TrackContextMenu trackContextMenu;

  @FXML private AnchorPane self;
  @FXML private Label duration;
  @FXML private HBox artistsContainer;
  @FXML private HyperlinkController trackNameController;
  @FXML private HyperlinkController albumNameController;
  @FXML private MultipleArtistsController artistsController;
  @FXML private SpotifyPlayButtonController playButtonController;

  private String trackUri;

  @FXML
  private void initialize() {
    self.setOnContextMenuRequested(event -> {
        trackContextMenu.setSelectedTrackUri(trackUri);
        trackContextMenu.getTrackMenu().show(self, event.getScreenX(), event.getScreenY());
    });
  }

  public void setArtists(Collection<ArtistSimplified> artists) {
    artistsController.clear();

    for (ArtistSimplified artist : artists) {
      artistsController.addArtist(artist);
    }
  }

  public void setTitle(String title) {
    trackNameController.setText(title);
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

  public void setTrackUri(String trackUri) {
    this.trackUri = trackUri;
    playButtonController.setSpotifyUri(trackUri);
    trackNameController.setUri(trackUri);
  }

  public String getAlbumUri() {
    return albumNameController.getUri();
  }

  public void setAlbumUri(String albumUri) {
    albumNameController.setUri(albumUri);
  }
}
