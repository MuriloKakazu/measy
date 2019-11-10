package br.com.cefsa.ec6.measy.application.builder;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.SearchResultController;
import br.com.cefsa.ec6.measy.infrastructure.builder.Builder;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import com.wrapper.spotify.model_objects.specification.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchResultBuilder implements Builder<Node> {

  @Autowired private FXMLLoaderFactory fxmlLoaderFactory;

  private Collection<Track> tracks;
  private Collection<Artist> artists;
  private Collection<AlbumSimplified> albums;
  private Collection<PlaylistSimplified> playlists;

  public SearchResultBuilder withTracks(Collection<Track> tracks) {
    this.tracks = tracks;
    return this;
  }

  public SearchResultBuilder withArtists(Collection<Artist> artists) {
    this.artists = artists;
    return this;
  }

  public SearchResultBuilder withAlbums(Collection<AlbumSimplified> albums) {
    this.albums = albums;
    return this;
  }

  public SearchResultBuilder withPlaylists(Collection<PlaylistSimplified> playlists) {
    this.playlists = playlists;
    return this;
  }

  @Override
  public Node build() {
    try {

      final FXMLLoader loader = fxmlLoaderFactory.create("SearchResult");
      final Node node = loader.load();
      final SearchResultController controller = loader.getController();

      controller.addTracks(Optional.ofNullable(tracks).orElse(Collections.emptyList()));
      controller.addArtists(Optional.ofNullable(artists).orElse(Collections.emptyList()));
      controller.addAlbums(Optional.ofNullable(albums).orElse(Collections.emptyList()));
      controller.addPlaylists(Optional.ofNullable(playlists).orElse(Collections.emptyList()));

      return node;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
