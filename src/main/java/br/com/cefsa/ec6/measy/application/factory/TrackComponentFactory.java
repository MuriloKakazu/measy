package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.TrackController;
import br.com.cefsa.ec6.measy.domain.repository.spotify.TrackRepository;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import com.wrapper.spotify.model_objects.specification.Track;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackComponentFactory {
  @Autowired private FXMLLoaderFactory fxmlLoaderFactory;
  @Autowired private TrackRepository trackRepository;

  private Node create(Track track) {
    try {

      final FXMLLoader loader = fxmlLoaderFactory.create("Track");
      Node trackComponent = loader.load();
      TrackController trackController = loader.getController();

      trackController.setTrack(track);

      return trackComponent;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Node fromTrack(Track track) {
    return create(track);
  }

  public Node fromTrackId(@NotNull String trackId) {
    return fromTrack(trackRepository.getById(trackId));
  }
}
