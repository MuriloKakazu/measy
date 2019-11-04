package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.PlaylistController;
import br.com.cefsa.ec6.measy.domain.repository.spotify.PlaylistRepository;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import com.wrapper.spotify.model_objects.specification.Playlist;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
public class PlaylistComponentFactory {

  @Autowired
  private PlaylistRepository playlistRepository;

  public Node fromPlaylistId(@NotNull String playlistId) {
    return fromPlaylist(playlistRepository.getById(playlistId));
  }

  public Node fromPlaylist(@NotNull Playlist playlist) {
    try {

      final FXMLLoader playlistLoader = FXMLLoaderFactory.create("Playlist");
      final Node playlistNode = playlistLoader.load();
      final PlaylistController playlistController = playlistLoader.getController();

      playlistController.setPlaylist(playlist);
      return playlistNode;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}