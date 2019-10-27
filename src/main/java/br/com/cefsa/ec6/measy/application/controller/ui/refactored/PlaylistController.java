package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import com.wrapper.spotify.model_objects.specification.Playlist;
import java.util.Arrays;
import javafx.fxml.FXML;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PlaylistController {

  @FXML private RecordHeaderController headerController;
  @FXML private TrackCollectionController tracksController;

  public void setPlaylist(Playlist playlist) {
    headerController.setRecordType(Playlist.class.getSimpleName());
    headerController.setTitle(playlist.getName());
    headerController.setImage(Arrays.stream(playlist.getImages()).findFirst().get().getUrl());
    headerController.setContextUri(playlist.getUri());

    Arrays.stream(playlist.getTracks().getItems())
        .forEach(playlistTrack -> tracksController.addTrack(playlistTrack.getTrack()));
  }
}
