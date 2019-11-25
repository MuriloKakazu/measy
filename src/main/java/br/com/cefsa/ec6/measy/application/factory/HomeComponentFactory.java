package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.ArtistController;
import br.com.cefsa.ec6.measy.application.controller.ui.refactored.HomeController;
import br.com.cefsa.ec6.measy.domain.repository.spotify.TrackRepository;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import br.com.cefsa.ec6.measy.infrastructure.util.TrackPicker;
import com.wrapper.spotify.model_objects.specification.Track;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HomeComponentFactory {

  @Autowired private FXMLLoaderFactory fxmlLoaderFactory;
  @Autowired private TrackRepository trackRepository;

  public Node create() {
    try {

      final FXMLLoader loader = fxmlLoaderFactory.create("Home");
      final Node homeNode = loader.load();
      final HomeController homeController = loader.getController();

      for (Track track : trackRepository.getMostPopularsFromCurrentUser().getItems()) {
        homeController.addFavoriteTrack(track);
      }

      for (Track track : TrackPicker.fromPlaylistTracks(trackRepository.getFromGlobalCharts())) {
        homeController.addGlobalChartsTrack(track);
      }

      return homeNode;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
