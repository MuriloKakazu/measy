package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.factory.RecordCardFactory;
import br.com.cefsa.ec6.measy.domain.repository.spotify.TrackRepository;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HomeController {

  @Autowired private TrackRepository trackRepository;
  @Autowired private RecordCardFactory recordCardFactory;

  @FXML private FlowPane userFavoriteTracks;
  @FXML private FlowPane globalChartsTracks;

  public void addFavoriteTrack(Track track) {
    Node trackCard = recordCardFactory.fromTrack(track);
    userFavoriteTracks.getChildren().add(trackCard);
  }

  public void addFavoriteTrack(TrackSimplified track) {
    Track fullTrack = trackRepository.getById(track.getId());
    addFavoriteTrack(fullTrack);
  }

  public void addGlobalChartsTrack(Track track) {
    Node trackCard = recordCardFactory.fromTrack(track);
    globalChartsTracks.getChildren().add(trackCard);
  }
}
