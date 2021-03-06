package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.PlaylistController;
import br.com.cefsa.ec6.measy.domain.repository.spotify.PlaylistRepository;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import com.wrapper.spotify.model_objects.specification.Playlist;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaylistComponentFactory {

  @Autowired private PlaylistRepository playlistRepository;

  @Autowired private FXMLLoaderFactory fxmlLoaderFactory;

  public Node fromPlaylistId(@NotNull String playlistId) {
    return fromPlaylist(playlistRepository.getById(playlistId));
  }

  public Node fromPlaylist(@NotNull Playlist playlist) {
    try {

      final FXMLLoader loader = fxmlLoaderFactory.create("Playlist");
      final Node playlistNode = loader.load();
      final PlaylistController playlistController = loader.getController();

      playlistController.setPlaylist(playlist);
      return playlistNode;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
