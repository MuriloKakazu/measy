package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.SidePanelButtonController;
import br.com.cefsa.ec6.measy.application.enums.Icon;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SidePanelButtonFactory {

  @Autowired private FXMLLoaderFactory fxmlLoaderFactory;

  public Node create(String text, String uri, Icon icon) {
    try {

      final FXMLLoader loader = fxmlLoaderFactory.create("SidePanelButton");
      final Node button = loader.load();
      final SidePanelButtonController buttonController = loader.getController();

      buttonController.setText(text);
      buttonController.setResourceUri(uri);
      buttonController.setIcon(icon);
      return button;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Node fromPlaylist(Playlist playlist) {
    return create(playlist.getName(), playlist.getUri(), Icon.PLAYLIST_PLAY_WHITE);
  }

  public Node fromPlaylist(PlaylistSimplified playlist) {
    return create(playlist.getName(), playlist.getUri(), Icon.PLAYLIST_PLAY_WHITE);
  }
}
