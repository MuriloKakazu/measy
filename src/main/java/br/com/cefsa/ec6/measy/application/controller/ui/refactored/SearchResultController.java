package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.factory.RecordCardFactory;
import com.wrapper.spotify.model_objects.specification.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchResultController {

  @Autowired private RecordCardFactory recordCardFactory;

  @FXML
  private FlowPane tracksPanel;

  @FXML
  private FlowPane artistsPanel;

  @FXML
  private FlowPane albumsPanel;

  @FXML
  private FlowPane playlistsPanel;

  public void addTracks(Collection<Track> tracks) {
    for (Track track : tracks) addTrack(track);
  }

  public void addArtists(Collection<Artist> artists) {
    for (Artist artist : artists) addArtist(artist);
  }

  public void addAlbums(Collection<AlbumSimplified> albums) {
    for (AlbumSimplified album : albums) addAlbum(album);
  }

  public void addPlaylists(Collection<PlaylistSimplified> playlists) {
    for (PlaylistSimplified playlist : playlists) addPlaylist(playlist);
  }

  public void addTrack(Track track) {
    Node trackCard = recordCardFactory.fromTrack(track);
    tracksPanel.getChildren().add(trackCard);
  }

  public void addArtist(Artist artist) {
    Node artistCard = recordCardFactory.fromArtist(artist);
    artistsPanel.getChildren().add(artistCard);
  }

  public void addAlbum(AlbumSimplified album) {
    Node albumCard = recordCardFactory.fromAlbum(album);
    albumsPanel.getChildren().add(albumCard);
  }

  public void addPlaylist(PlaylistSimplified playlist) {
    Node playlistCard = recordCardFactory.fromPlaylist(playlist);
    playlistsPanel.getChildren().add(playlistCard);
  }

}
