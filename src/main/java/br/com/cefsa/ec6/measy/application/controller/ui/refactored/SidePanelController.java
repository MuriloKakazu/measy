package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.enums.Icon;
import br.com.cefsa.ec6.measy.domain.repository.spotify.PlaylistRepository;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SidePanelController {

  @Autowired private PlaylistRepository playlistRepository;

  @FXML private CollectionController playlistsController;

  public void fetchPlaylists() {
    Paging<PlaylistSimplified> userPlaylists = playlistRepository.getCurrentUserPlaylists();

    for (PlaylistSimplified playlist : userPlaylists.getItems()) {
      try {

        final FXMLLoader playlistButtonLoader = FXMLLoaderFactory.create("SidePanelButton");
        final Node playlistButton = playlistButtonLoader.load();
        final SidePanelButtonController playlistButtonController =
            playlistButtonLoader.getController();

        playlistButtonController.setIcon(Icon.PLAYLIST_PLAY_WHITE);
        playlistButtonController.setText(playlist.getName());
        playlistButtonController.setResourceUri(playlist.getUri());

        playlistsController.addChild(playlistButton);

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
