package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.factory.AlbumComponentFactory;
import br.com.cefsa.ec6.measy.application.factory.ArtistComponentFactory;
import br.com.cefsa.ec6.measy.application.factory.PlaylistComponentFactory;
import br.com.cefsa.ec6.measy.infrastructure.util.SpotifyUriHelper;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Playlist;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ClientController {

  @Autowired private AlbumComponentFactory albumComponentFactory;
  @Autowired private ArtistComponentFactory artistComponentFactory;
  @Autowired private PlaylistComponentFactory playlistComponentFactory;

  @FXML private ScrollPane scrollableContent;

  public void setContent(AnchorPane panel) {
    scrollableContent.setContent(panel);
  }

  private void setContent(Node node) {
    setContent((AnchorPane) node);
  }

  public void navigateToUri(String uri) {
    switch (SpotifyUriHelper.getType(uri)) {
      case ALBUM:
        setContent(albumComponentFactory.fromAlbumId(SpotifyUriHelper.getId(uri)));
        break;
      case ARTIST:
        setContent(artistComponentFactory.fromArtistId(SpotifyUriHelper.getId(uri)));
        break;
      case PLAYLIST:
        setContent(playlistComponentFactory.fromPlaylistId(SpotifyUriHelper.getId(uri)));
    }
  }

  public void navigateToAlbum(Album album) {
    setContent(albumComponentFactory.fromAlbum(album));
  }

  public void navigateToArtist(Artist artist) {
    setContent(artistComponentFactory.fromArtist(artist));
  }

  public void navigateToPlaylist(Playlist playlist) {
    setContent(playlistComponentFactory.fromPlaylist(playlist));
  }
}
