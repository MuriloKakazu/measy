package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.factory.SidePanelButtonFactory;
import br.com.cefsa.ec6.measy.domain.repository.spotify.PlaylistRepository;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import javafx.fxml.FXML;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SidePanelController {

  @Autowired private SidePanelButtonFactory sidePanelButtonFactory;
  @Autowired private PlaylistRepository playlistRepository;

  @FXML private CollectionController playlistsController;

  public void fetchPlaylists() {
    Paging<PlaylistSimplified> userPlaylists = playlistRepository.getCurrentUserPlaylists();

    for (PlaylistSimplified playlist : userPlaylists.getItems()) {
      Node playlistButton = sidePanelButtonFactory.fromPlaylist(playlist);
      playlistsController.addChild(playlistButton);
    }
  }
}
