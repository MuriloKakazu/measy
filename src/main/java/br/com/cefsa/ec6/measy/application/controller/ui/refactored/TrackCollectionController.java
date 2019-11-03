package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TrackCollectionController {

  @FXML private CollectionController tracksController;

  public void addTrack(Track track) {
    final FXMLLoader trackRowLoader = appendTrack();
    final TrackRowController trackRowController = trackRowLoader.getController();

    trackRowController.setTrack(track);
  }

  public void addTrack(TrackSimplified track) {
    final FXMLLoader trackRowLoader = appendTrack();
    final TrackRowController trackRowController = trackRowLoader.getController();

    trackRowController.setTrack(track);
  }

  private FXMLLoader appendTrack() {
    try {

      final FXMLLoader trackRowLoader = FXMLLoaderFactory.create("TrackRow");

      final Parent trackRowNode = trackRowLoader.load();
      tracksController.addChild(trackRowNode);

      return trackRowLoader;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
