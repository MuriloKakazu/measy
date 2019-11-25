package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.domain.repository.spotify.PlaylistRepository;
import br.com.cefsa.ec6.measy.infrastructure.util.AlertUtil;
import br.com.cefsa.ec6.measy.infrastructure.util.CacheHelper;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import java.util.Arrays;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NewPlaylistPageController {

  @Autowired private CacheHelper cacheHelper;
  @Autowired private PlaylistRepository playlistRepository;
  @Autowired private SidePanelController sidePanelController;

  @FXML private AnchorPane self;
  @FXML private TextField playlistName;

  @FXML
  private void onActionCreate(ActionEvent event) {
    Stream<PlaylistSimplified> userPlaylists =
        Arrays.stream(cacheHelper.getUserPlaylists().getItems());
    String playlistName = this.playlistName.getText();

    if (playlistName.isBlank()) {
      AlertUtil.showError("New playlist needs a name!");
      return;
    }

    if (userPlaylists.anyMatch(playlist -> playlist.getName().equals(playlistName))) {
      AlertUtil.showError("A playlist with that name already exists!");
      return;
    }

    try {
      playlistRepository.createPlaylist(playlistName);
      AlertUtil.showInfo(String.format("Playlist %s created!", playlistName));
      cacheHelper.refreshUserPlaylists();
      sidePanelController.fetchPlaylists();
      close();
    } catch (Exception e) {
      AlertUtil.showGenericServiceError();
    }
  }

  @FXML
  private void onActionCancel(ActionEvent event) {
    close();
  }

  private void close() {
    Stage stage = (Stage) self.getScene().getWindow();
    stage.close();
  }
}
